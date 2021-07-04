package space.androma.auction.payment.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.payment.api.dao.IPaymentRepo;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.api.mappers.PaymentMapper;
import space.androma.auction.payment.api.services.IPaymentService;
import space.androma.auction.payment.api.services.ITradesConnectionService;

import java.util.List;


@Slf4j
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    IPaymentRepo repo;

    @Autowired
    ITradesConnectionService tradesConnectionService;

    @Override
    public boolean payForLot(PaymentDto paymentDto) {
        //делаем платеж. Если вернули true - значит все ок,
        // для теста - на "той" стороне сразу выставляем "платеж прошел". По факту, конечно так делать не будем.
        if (tradesConnectionService.UserPermitPay(paymentDto.getLotId(), paymentDto.getUserId())) {

/*            if (repo.findByLotId(paymentDto.getLotId()) != null) {
                //TODO пока не учитываем cancelled Payment
                return false;
            } else {*/
                repo.save(PaymentMapper.mapPayment(paymentDto));
                log.info("PaymentSaved");
                return true;
   //         }
        }
        return false;
    }

    @Override
    public PaymentDto getInfoByLot(String lotId)
    {
        log.info("PaymentGet for lot "+ lotId);
        return PaymentMapper.mapPaymentDto(repo.findByLotId(lotId).orElse(null));
    }

    @Override
    public List<PaymentDto> getPaymentsByUser(String userId) {
        log.info("PaymentGet for user "+ userId);
        return PaymentMapper.mapPaymentDtos(repo.findByUserId(userId));
    }

    @Override
    public boolean cancelPayment(Long paymentId) {
        return false;
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return PaymentMapper.mapPaymentDtos(repo.findAll());
    }
}
