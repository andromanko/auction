package space.androma.auction.trades.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dao.ILotRepo;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.api.mappers.LotMapper;
import space.androma.auction.trades.api.service.ILotService;
import space.androma.auction.trades.entity.Lot;

import java.util.List;

import static java.lang.Boolean.FALSE;

@Slf4j
@Service
public class LotService implements ILotService {

    @Autowired
    ILotRepo lotRepo;

    public List<LotDto> getAll() {
        return LotMapper.INSTANCE.mapLotDtos(lotRepo.findAll());
    }



    @Override
    public LotDto getLotById(String id) {
        log.info("srv-getLotByID called");
        Lot lot = lotRepo.findById(id).orElse(null);
        return LotMapper.INSTANCE.mapLotDto(lot);
    }

    @Override
    public boolean addLot(LotDto lotDto) {
        //TODO CHECK if Lot exists?
        log.info("srv-addLot called");
        lotRepo.save(LotMapper.INSTANCE.mapLot(lotDto));
        //TODO
        return FALSE;
    }

    @Override
    public void updateLot(String id, LotDto lotDto, MultipartFile file) {
//TODO
    }

    @Override
    public void deleteLot(String id) {
//TODO
    }


}
