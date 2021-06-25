package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.api.service.IUserService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers()
    {
        List<UserDto> users = userService.getUsers();
        return users;
    }

    //TODO подумать что делать с ЮзерИнфо. ЧТо вытягиваем из principal?
    //если у нас будут админы - то для них доступны все юзеры
    //для юзера доступен только он сам - т.е. principal
    @GetMapping(value = "/{id}")
    public UserDto getUserInfo(@PathVariable String id)
    {
        log.info("rest-getUser called");
        UserDto userDto = userService.getUserById(id);
        System.out.println(userDto);
        return userDto;//    .getUserByEmail("blange@mail.ru");//principal.getName());
    }

    @PostMapping(value = "/add")
    public String addUser( @RequestBody  UserDto userDto) {
        log.info("rest-PutAddUser called");
       // UserDto userDto = UserDto.builder().name(name).build();
        userService.addUser(userDto);
        String txt = userDto.getName();
        return txt;
    }

 /*   @GetMapping(value = "/add")
    public UserDto addUser(UserDto userDto) {
        log.info("rest-GetAddUser called");

        return userService.addUser(userDto);
    }*/

    @PutMapping(value = "/upd")
    public RedirectView updateUser(UserDto user, @RequestParam(value = "file", required = false) MultipartFile file) {
//TODO updUser
        return null;
    }

    //TODO PAGEABLE? GET ALL USERS
}
