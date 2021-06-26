package space.androma.auction.payment.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.payment.api.dao.IPaymentRepo;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.api.mappers.PaymentMapper;
import space.androma.auction.payment.api.services.IPaymentService;

import java.util.List;


@Slf4j
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    IPaymentRepo repo;

    @Override
    public boolean payForLot(PaymentDto paymentDto) {
        //запрос в базу на lotId, если не нашел - занести в базу
        if (repo.findByLotId(paymentDto.getLotId())!=null) {
            //TODO пока не учитываем cancelled Payment
            return false;}
        else {
            repo.save(PaymentMapper.mapPayment(paymentDto));
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

    @Override
    public List<PaymentDto> getAllPayments() {
        return PaymentMapper.mapPaymentDtos(repo.findAll());
    }
}
