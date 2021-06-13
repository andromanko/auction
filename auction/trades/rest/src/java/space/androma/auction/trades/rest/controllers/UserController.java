package space.androma.auction.trades.rest.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

    /*@GetMapping()
    public getUserInfo(Principal principal) {
        return null;
    }*/

    @PostMapping(value = "/upd")
    public RedirectView updateUser(UserDto user, @RequestParam(value = "file", required = false) MultipartFile file) {

        return null;
    }


}
