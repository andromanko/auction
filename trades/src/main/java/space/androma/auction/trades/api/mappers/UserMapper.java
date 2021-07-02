package space.androma.auction.trades.api.mappers;

import lombok.experimental.UtilityClass;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.entity.AuUser;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public AuUser mapUser(UserDto source) {
        return AuUser.builder().id(source.getId()).name(source.getName()).email(source.getEmail())
                .build();
    }

    public UserDto mapUserDto(AuUser source) {
        return UserDto.builder().id(source.getId()).name(source.getName()).email(source.getEmail())
                .build();
    }

    public List<AuUser> mapUsers(List<UserDto> source) {
        return source.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    public List<UserDto> mapUserDtos(List<AuUser> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toList());
    }
}
