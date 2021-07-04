package space.androma.auction.trades.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dao.ILotRepo;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.api.mappers.LotMapper;
import space.androma.auction.trades.api.service.ILotService;
import space.androma.auction.trades.entity.Lot;
import space.androma.auction.trades.entity.User;

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

    @Override
    public boolean getUserPermitPayForLot(String lotId, String userId) {
    //TODO вынести дублированый код
        Lot lot = lotRepo.findById(lotId).orElse(null);
        if (lot !=null) {
            //TODO возможен баг в условии
            if (LocalDateTime.now().isAfter( lot.getDateTimeEnd())) {
                if (lot.getWinnerId().equals(userId)) {
                    return true;
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
        User user = userRepo.findByUsername(username).orElse(null);
        if (user != null) {
            newLot.setSellerId(user.getId());
            //если дата не задана - то 1 месяц на продажу =)
            if (lotDto.getDateTimeEnd() == null) {
                newLot.setDateTimeEnd(LocalDateTime.now().plusMonths(1));
            }
            if (lotDto.getPriceCurrent() == null) {
                newLot.setPriceCurrent(0L);
            }
            lotRepo.save(newLot);
            return true;
        }
        return false;
    }

    @Override
    public void updateLot(String id, LotDto lotDto, MultipartFile file) {
//TODO
    }

    @Override
    public void deleteLot(String id) {
//TODO
    }

    @Override
    public boolean givePriceForLot(String lotId, String userId, long proposedPrice) {

        Lot lot = lotRepo.findById(lotId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        if ((lot != null)&(user != null))  {
            if (lot.getDateTimeEnd().isAfter(LocalDateTime.now())) {  //если срок еще не истек
                if (lot.getPriceCurrent() < proposedPrice) {
                        lot.setPriceCurrent(proposedPrice);
                        lot.setWinnerId(user.getId());
                        lotRepo.save(lot);
                        return true;
                    }
                }
            }
        return false;
    }


}