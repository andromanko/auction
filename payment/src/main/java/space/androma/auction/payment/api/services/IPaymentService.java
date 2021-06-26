package space.androma.auction.payment.api.services;

import org.springframework.stereotype.Service;
import space.androma.auction.payment.api.dto.PaymentDto;


import java.util.List;

@Service
public interface IPaymentService {

    //заплатить от Юзера Сумму за Лот
    boolean payForLot(PaymentDto paymentDto);

    //выдать инфо по лоту - одна запись //если делать с отменами или частичными оплатами - то List
    PaymentDto getInfoByLot(String LotId);

    //выдать инфо по юзеру //пока без pageable
    List<PaymentDto> getPaymentsByUser(String userId);

    boolean cancelPayment(Long paymentId);

    List<PaymentDto> getAllPayments();
}
