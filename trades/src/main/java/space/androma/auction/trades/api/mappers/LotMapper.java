package space.androma.auction.trades.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.entity.Lot;

import java.util.List;

@Mapper
public interface LotMapper {

    LotMapper INSTANCE = Mappers.getMapper(LotMapper.class);

    Lot mapLot(LotDto source);

    LotDto mapLotDto(Lot source);

    List<Lot> mapLot(List<LotDto> sources);

    List<LotDto> mapLotDtos(List<Lot> sources);
}
