package space.androma.auction.trades.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import space.androma.auction.trades.api.dto.UserDto;

@Service
public interface IUserService {

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);

    String addUser(UserDto userDto);

    void updateUser(String login, UserDto userDto, MultipartFile file);

    //TODO по этому методу юзер должен помечаться как "удален" (добавить в Энтити). И удаляться "навсегда" через какое-то время
    void deleteUser(String id);

    List<UserDto> getUsers();

}
