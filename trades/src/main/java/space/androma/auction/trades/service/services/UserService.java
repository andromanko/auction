package space.androma.auction.trades.service.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.androma.auction.security.TokenData;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.api.mappers.UserMapper;
import space.androma.auction.trades.api.service.IUserService;
import space.androma.auction.trades.entity.User;

import java.security.Principal;
import java.util.*;

@PropertySource("classpath:application.properties")
@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepo repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = repository.findAll();
        List<UserDto> userDtos = UserMapper.mapUserDtos(users);
        return userDtos;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        //TODO что будет если null?
        log.info("srv-getUserByEmail called");
        return UserMapper.mapUserDto(repository.findByEmail(email).orElse(null));
    }

    @Override
    public UserDto getUserByUsername(String username){
        //TODO что будет если null?
        log.info("srv-getUserByUserName called");
        return UserMapper.mapUserDto(repository.findByUsername(username).orElse(null));
    }

    @Override
    public String addUser(UserDto userDto) {

        User user = UserMapper.mapUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//TODO validate Email ?
        /*User userByEmail = repository.findByEmail(userDto.getEmail()).orElse(null);
        if (userByEmail != null) {
            log.error("Failed to registerUser. The Login or Email already exists");
            // TODO переделать
            return "USER with this e-mail EXIST";
        }*/
        User newUser = repository.insert(user);
        return newUser.getId();

    }

    @Override
    public String newUserFromSocial(Principal principal) {

        try {  //TODO RECODE IT!!!
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) principal;
            OAuth2AuthenticatedPrincipal authenticatedPrincipal = authenticationToken.getPrincipal();

            String email = authenticatedPrincipal.getAttribute("email");
            User user = repository.findByEmail(email).orElse(null);
            if (user != null) return user.getUsername();
            String username = authenticatedPrincipal.getAttribute("name");

            this.addUser(UserDto.builder()
                    .name(username)
                    .email(email)
                    .password(principal.getName())
                    .build());

            return username;
        }
        catch (Exception ClassCastException) {
            return  principal.getName();
        }
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
/// for other services---------------------------
@Value("${space.androma.auction.key}")
private String key;

    @Override
    public String login(final String username, final String password) {
        User user = repository.findByUsername(username).orElse(null);
        //дальше проверяется password! и если норм - выдается токен
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return getToken(user);
        }
        throw new RuntimeException("Error, не тот username/password");
    }

    private String getToken(final User user) {
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenData.ID.getValue(), user.getId());
        tokenData.put(TokenData.LOGIN.getValue(), user.getUsername());
        tokenData.put(TokenData.EMAIL.getValue(), user.getEmail());
        tokenData.put(TokenData.GROUP.getValue(), new SimpleGrantedAuthority("user"));//user.getGroup());
        tokenData.put(TokenData.CREATE_DATE.getValue(), new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1); //tokenDaysAlive);
        tokenData.put(TokenData.EXPIRATION_DATE.getValue(), calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String jwt = jwtBuilder.signWith(SignatureAlgorithm.HS512,key).compact();

         return jwt;
    }

}
