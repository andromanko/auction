package space.androma.auction.trades.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dto.LotDto;

import java.util.List;

@Service
public interface ILotService {

    LotDto getLotById(String id);

    boolean givePriceForLot(String lotId, String userId, long proposedPrice);

    String addLot(LotDto lotDto);

    void updateLot(String id, LotDto lotDto, MultipartFile file);

    void deleteLot(String id);

    List<LotDto> getLots();

}
