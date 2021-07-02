package space.androma.auction.trades.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.api.mappers.UserMapper;
import space.androma.auction.trades.api.service.IUserService;
import space.androma.auction.trades.entity.AuUser;

import java.util.List;


@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepo repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        List<AuUser> auUsers = repository.findAll();
        List<UserDto> userDtos = UserMapper.mapUserDtos(auUsers);
        return userDtos;
    }

    public UserDto getUserByEmail(String email) {
        //TODO что будет если null?
        log.info("srv-getUserByEmail called");
        return UserMapper.mapUserDto(repository.findByEmail(email).orElse(null));
    }

    @Override
    public String addUser(UserDto userDto) {

        AuUser auUser = UserMapper.mapUser(userDto);
        auUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//TODO validate Email ?
        /*AuUser userByEmail = repository.findByEmail(userDto.getEmail()).orElse(null);
        if (userByEmail != null) {
            log.error("Failed to registerUser. The Login or Email already exists");
            // TODO переделать
            return "USER with this e-mail EXIST";
        }*/
        AuUser newAuUser = repository.insert(auUser);
        return newAuUser.getId();

    }

    @Override
    public UserDto getUserById(String id) {

        return UserMapper.mapUserDto(repository.findById(id).orElse(null));
    }

    @Override
    public void updateUser(String login, UserDto userDto, MultipartFile file) {
//TODO updUser
    }

    @Override
    public void deleteUser(String id) {
//TODO delUser MARK AS DELETED!!!
    }
}
