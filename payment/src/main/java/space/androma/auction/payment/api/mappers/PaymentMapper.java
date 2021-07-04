package space.androma.auction.payment.api.mappers;

import lombok.experimental.UtilityClass;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.entity.Payment;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PaymentMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//TODO тут тернарник. Возможен null. подумать что делать с такой практикой
    public Payment mapPayment (PaymentDto source) {
        return source!=null ? Payment.builder()
                .id(source.getId())
                .userId(source.getUserId())
                .lotId(source.getLotId())
                .sum(source.getSum())
                .build() : null;
    }

    public PaymentDto mapPaymentDto(Payment source) {
        return PaymentDto.builder()
                .id(source.getId())
                .userId(source.getUserId())
                .lotId(source.getLotId())
                .sum(source.getSum())
                .build();
    }


    public List<Payment> mapPayments(List<PaymentDto> source) {
        return source.stream().map(PaymentMapper::mapPayment).collect(Collectors.toList());
    }

    public List<PaymentDto> mapPaymentDtos(List<Payment> source) {
        return source.stream().map(PaymentMapper::mapPaymentDto).collect(Collectors.toList());
    }
}
