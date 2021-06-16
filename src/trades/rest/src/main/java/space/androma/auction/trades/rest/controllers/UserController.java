package space.androma.auction.trades.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.service.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
     UserService userService;

    @GetMapping()
    public getUserInfo(Principal principal) {
        return null;
    }

    @PutMapping(value = "/upd")
    public RedirectView updateUser(UserDto user, @RequestParam(value = "file", required = false) MultipartFile file) {

        return null;
    }

    @PostMapping(value = "/upd")
    public RedirectView updateUser(UserDto user, @RequestParam(value = "file", required = false) MultipartFile file) {

        return null;
    }


}
