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
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

@Slf4j
@Service
public class LotService implements ILotService {

    @Autowired
    ILotRepo lotRepo;
    @Autowired
    IUserRepo userRepo;

    public List<LotDto> getLots() {
        List<Lot> a = lotRepo.findAll();
        List<LotDto> b = LotMapper.mapLotDtos(a);
        return b;
    }

    @Override
    public boolean getUserPermitCommunicate(String lotId, String userId) {
        Lot lot = lotRepo.findById(lotId).orElse(null);
        if (lot !=null) {
            //TODO возможен баг в условии
            if (LocalDateTime.now().isAfter((ChronoLocalDateTime) lot.getDateTimeEnd())) {
                if (lot.isPaymentDone()) {
                    if ((lot.getSeller().getId() == userId) || lot.getWinner().getId() == userId) {
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
            if (LocalDateTime.now().isAfter((ChronoLocalDateTime) lot.getDateTimeEnd())) {
                if (lot.getWinner().getId() == userId) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public LotDto getLotById(String id) {
        log.info("srv-getLotByID called");
        Lot lot = lotRepo.findById(id).orElse(null);
        return LotMapper.mapLotDto(lot);
    }

    @Override
    public String addLot(LotDto lotDto) {
        //TODO CHECK if Lot(name) exists?
        Lot newLot = lotRepo.save(LotMapper.mapLot(lotDto));
        return newLot.getId();
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
        if (lot != null) {
            if (lot.getPriceCurrent() < proposedPrice) {
                User user = userRepo.findById(userId).orElse(null);
                if (user != null) {
                    lot.setPriceCurrent(proposedPrice);
                    lot.setWinner(user);
                    return true;
                }
            }
        }
        return false;
    }


}