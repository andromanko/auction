package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.trades.api.service.IUserService;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    IUserService userService;

    @GetMapping()
    public String main() {
        log.info("TradesController working/ 8080/ mapping! ");
        //UserDto userDto = UserDto.builder().name("name3").email("blange@mail.ru").build();
        //userService.addUser(userDto);
        return "Main Page TRADES"; //userService.getUserByEmail("blange@mail.ru");//principal.getName());
    }
}
