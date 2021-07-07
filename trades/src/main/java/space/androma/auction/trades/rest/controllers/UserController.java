package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.api.service.IUserService;

import java.security.Principal;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers(Principal principal)
    {
        return userService.getUsers();
    }

    //TODO подумать что делать с ЮзерИнфо. ЧТо вытягиваем из principal?
    //если у нас будут админы - то для них доступны все юзеры
    //для юзера доступен только он сам - т.е. principal
    @GetMapping(value = "/{id}")
    public UserDto getUserInfo(@PathVariable String id)
    {
        return userService.getUserById(id);
    }



    @PutMapping(value = "/upd")
    public RedirectView updateUser(UserDto user, @RequestParam(value = "file", required = false) MultipartFile file) {
//TODO updUser
        return null;
    }

    //TODO PAGEABLE? GET ALL USERS

}
