package space.androma.auction.payment.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.payment.api.dao.IPaymentRepo;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.api.mappers.PaymentMapper;
import space.androma.auction.payment.api.services.IPaymentService;
import space.androma.auction.payment.entity.Payment;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class PayService implements IPaymentService {

    @Autowired
    IPaymentRepo repo;

    @Override
    public boolean payForLot(String userId, String lotId, Long sum) {
        //запрос в базу на lotId, если не нашел - занести в базу
        if (repo.findByLotId(lotId)!=null) {
            //TODO пока не учитываем cancelled Payment
            return false;}
        else {
            repo.save(Payment.builder()
                    .userId(userId)
                    .lotId(lotId)
                    .sum(sum)
                    .date(LocalDateTime.now())
                    .build());
            return true;
        }
    }

    @Override
    public PaymentDto getInfoByLot(String lotId) {
        return PaymentMapper.mapPaymentDto(repo.findByLotId(lotId));
    }

    @Override
    public List<PaymentDto> getPaymentsByUser(String userId) {
        return PaymentMapper.mapPaymentDtos(repo.findByUserId(userId));
    }

    @Override
    public boolean cancelPayment(Long paymentId) {
        return false;
    }
}
