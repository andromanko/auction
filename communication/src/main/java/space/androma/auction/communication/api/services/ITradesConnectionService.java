package space.androma.auction.communication.api.services;

import org.springframework.stereotype.Service;
import space.androma.auction.communication.entity.MsgDetails;

@Service
public interface ITradesConnectionService {

    //проверяется завершенность ЛОТа, и участие в нем Юзера (продаван/победитель/админ)
    boolean UserPermitCommunicate(String lotId, String userId);

    MsgDetails getLotDetails(String lotId);

    //только для теста//
    boolean ping();
}
