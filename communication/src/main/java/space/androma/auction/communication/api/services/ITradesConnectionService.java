package space.androma.auction.communication.api.services;

import org.springframework.stereotype.Service;

@Service
public interface ITradesConnectionService {

    //проверяется завершенность ЛОТа, и участие в нем Юзера (продаван/победитель/админ)
    boolean UserPermitCommunicate(String lotId, String userId);

}
