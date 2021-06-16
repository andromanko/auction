package space.androma.auction.trades.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dto.UserDto;

import java.util.List;

@Service
public interface ILotService {

	UserDto findUser(long id);

//    UserDto findUserByEmail(String email);

	UserDto createUser(UserDto user);

	void updateUser(String login, UserDto userDto, MultipartFile file);

	void deleteUser(long id);

	List<UserDto> getUsers();

}
