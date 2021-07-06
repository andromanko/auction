package space.androma.auction.trades.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dto.UserDto;

import java.security.Principal;
import java.util.List;

@Service
public interface IUserService {

    UserDto getUserById(String id);

    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);

    String addUser(UserDto userDto);

    void updateUser(String login, UserDto userDto, MultipartFile file);

    //TODO по этому методу юзер должен помечаться как "удален" (добавить в Энтити). И удаляться "навсегда" через какое-то время
    void deleteUser(String id);

    List<UserDto> getUsers();

    String newUserFromSocial(Principal principal);

}
