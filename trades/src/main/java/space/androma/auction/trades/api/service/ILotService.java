package space.androma.auction.trades.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.rest.otherData.MsgDetails;

import java.util.List;

@Service
public interface ILotService {

    LotDto getLotById(String id);

    boolean givePriceForLot(String lotId, String userId, long proposedPrice);

    boolean addLot(LotDto lotDto, String username);//TODO username заменить на e-mail

    void updateLot(String id, LotDto lotDto, MultipartFile file);

    void deleteLot(String id);

    List<LotDto> getLots();

    boolean getUserPermitCommunicate(String lotId, String userId);

    boolean getUserPayForLot(String lotId, String userId);

    //todo external!!!
    MsgDetails getLotInfoById(String lotId);

}
