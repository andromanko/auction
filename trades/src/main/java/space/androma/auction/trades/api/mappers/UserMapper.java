package space.androma.auction.trades.api.mappers;

import lombok.experimental.UtilityClass;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public User mapUser(UserDto source) {
        return source!=null ?  User.builder().id(source.getId()).username(source.getName()).email(source.getEmail())
                .build(): null;
    }

    public UserDto mapUserDto(User source) {
        return source!=null ?  UserDto.builder()
                .id(source.getId())
                .name(source.getUsername())
                .email(source.getEmail())
                .build() : null;
    }

    public List<User> mapUsers(List<UserDto> source) {
        return source.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    public List<UserDto> mapUserDtos(List<User> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toList());
    }
}
