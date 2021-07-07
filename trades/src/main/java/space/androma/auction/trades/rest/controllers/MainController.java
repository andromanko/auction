package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.androma.auction.security.Result;
import space.androma.auction.trades.api.service.IUserService;

import java.security.Principal;

import static space.androma.auction.security.Result.run;

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
//---------forSequr
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestParam(value = "login") final String login,
                                @RequestParam(value = "password") final String password) {
        return run(() -> userService.login(login, password));
    }


}

