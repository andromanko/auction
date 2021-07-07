package space.androma.auction.trades.api.mappers;

import lombok.experimental.UtilityClass;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.entity.Lot;

import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class LotMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public Lot mapLot(LotDto source) {
        return Lot.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .priceStart(source.getPriceStart())
                .priceCurrent(source.getPriceCurrent())
                .dateTimeEnd(source.getDateTimeEnd())
                .build();
    }

    public LotDto mapLotDto(Lot source) {
        return LotDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .priceStart(source.getPriceStart())
                .priceCurrent(source.getPriceCurrent())
                .dateTimeEnd(source.getDateTimeEnd())
                .winner(source.getWinnerId())
                .paymentDone(source.isPaymentDone())
                .build();
    }

    public List<Lot> mapLots(List<LotDto> source) {
        return source.stream().map(LotMapper::mapLot).collect(Collectors.toList());
    }

    public List<LotDto> mapLotDtos(List<Lot> source) {
        return source.stream().map(LotMapper::mapLotDto).collect(Collectors.toList());
    }
}
