package space.androma.auction.payment.api.services;

import org.springframework.stereotype.Service;

@Service
public interface ITradesConnectionService {

    //проверяется завершенность ЛОТа, и участие в нем Юзера (продаван/победитель/админ)
    boolean UserPermitPay(String lotId, String userId);

}
