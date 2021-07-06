package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.trades.api.service.IUserService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    IUserService userService;

    @GetMapping()
    public String main(Principal principal) {

        log.info("TradesController working/ 8080/  ");



        String username = "anonimous";
        if (principal != null) {
            username = userService.newUserFromSocial(principal);
        }
        return "Main Page TRADES. User: " + username;
    }
}

