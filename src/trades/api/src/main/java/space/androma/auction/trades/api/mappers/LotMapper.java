package space.androma.auction.trades.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.entity.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User mapUser(UserDto source);

	UserDto mapUserDto(User source);

	List<User> mapUser(List<UserDto> sources);

	List<UserDto> mapUserDtos(List<User> sources);
}
