package space.androma.auction.trades.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dao.ILotRepo;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.api.mappers.LotMapper;
import space.androma.auction.trades.api.service.ILotService;
import space.androma.auction.trades.entity.Lot;
import space.androma.auction.trades.entity.User;
import space.androma.auction.trades.rest.otherData.MsgDetails;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class LotService implements ILotService {

    @Autowired
    ILotRepo lotRepo;
    @Autowired
    IUserRepo userRepo;

    public List<LotDto> getLots() {
        return LotMapper.mapLotDtos(lotRepo.findAll());
    }

    @Override
    public boolean getUserPermitCommunicate(String lotId, String userId) {
        Lot lot = lotRepo.findById(lotId).orElse(null);
        if (lot !=null) {
            //TODO возможен баг в условии
            if (LocalDateTime.now().isAfter( lot.getDateTimeEnd())) {
                if (lot.isPaymentDone()) {
                    if ((lot.getSellerId().equals(userId)) || lot.getWinnerId().equals(userId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean getUserPayForLot(String lotId, String userId) {
    //TODO вынести дублированый код
        Lot lot = lotRepo.findById(lotId).orElse(null);
        if (lot !=null) {
            if (!lot.isPaymentDone()) {
                //TODO возможен баг в условии
                if (LocalDateTime.now().isAfter(lot.getDateTimeEnd())) {
                    if (lot.getWinnerId().equals(userId)) {
                        //предполагаем, что ющерИД у нас уже есть если прошли условие
                        User winner = userRepo.findById(userId).orElse(null);
                        if (winner.getBalance() < lot.getPriceCurrent()) {
                            log.info("user balance is too low =(");
                            return false;
                        }
                        winner.setBalance(winner.getBalance() - lot.getPriceCurrent());
                        userRepo.save(winner);
                        //TODO здесь может быть еще проверка суммы
                        //но в нашей симуляции делаем якобы все ок
                        lot.setPaymentDone(true);
                        lotRepo.save(lot);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public LotDto getLotById(String id) {
        Lot lot = lotRepo.findById(id).orElse(null);
        return LotMapper.mapLotDto(lot);
    }

    @Override
    public boolean addLot(LotDto lotDto,String username) {
        //TODO CHECK if Lot(name) exists?
        Lot newLot = LotMapper.mapLot(lotDto);

        //check if Lot with this name exists

        if (lotRepo.findByName(lotDto.getName()).isPresent()) {
            log.info("Lot Add Service. Lot with this name exists in DB. Lotname: "+lotDto.getName());
            return false;
        }
        User seller = userRepo.findByUsername(username).orElse(null);
        if (seller==null) {
            log.info("Lot Add Service. User does not exist in DB. Username: "+username);
            return false;
        }
        newLot.setSellerId(seller.getId()); //Google?! TODO
         if (lotDto.getDateTimeEnd() == null) {
                newLot.setDateTimeEnd(LocalDateTime.now().plusMonths(1));
            }
            if (lotDto.getPriceCurrent() == null) {
                newLot.setPriceCurrent(0L);
            }
            lotRepo.save(newLot);
            return true;
    }

    //TODO
    @Override
    public void updateLot(String id, LotDto lotDto, MultipartFile file) {
    }

    //TODO
    @Override
    public void deleteLot(String id) {

    }

    @Override
    public boolean givePriceForLot(String lotId, String userId, long proposedPrice) {

        Lot lot = lotRepo.findById(lotId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        if ((lot != null)&(user != null)) {
            if (lot.getDateTimeEnd().isAfter(LocalDateTime.now())) {  //если срок еще не истек
                if ((lot.getPriceCurrent() < proposedPrice) & (lot.getPriceStart() < proposedPrice)) {
                    lot.setPriceCurrent(proposedPrice);
                    lot.setWinnerId(user.getId());
                    lotRepo.save(lot);
                    return true;
                }
            }
        }
        return false;
    }

    public MsgDetails getLotInfoById(String lotId) {
        Lot lot = lotRepo.findById(lotId).orElse(null);
        if (lot != null) {
            User seller = userRepo.findById(lot.getSellerId()).orElse(null);
            User buyer = userRepo.findById(lot.getWinnerId()).orElse(null);
            if ((buyer != null) & (seller != null)) {
                return MsgDetails.builder()
                        .lotId(lotId)
                        .buyerId(buyer.getId())
                        .buyerEmail((buyer.getEmail()))
                        .sellerId(seller.getId())
                        .sellerEmail(seller.getEmail())
                        .build();
            }
        }
        log.info("not correct info from Msg Service LotId: " + lotId);
        return null;
    }
}