package space.androma.auction.communication.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.communication.api.services.ITradesConnectionService;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    ITradesConnectionService tradesConnectionService;

    @GetMapping()
    public String main() {
        log.info("CommunicationService working/ 8082/ mapping! ");
        //UserDto userDto = UserDto.builder().name("name3").email("blange@mail.ru").build();
        //userService.addUser(userDto);

        return "Main Page MsgController started. Connect with Trades:"+ tradesConnectionService.ping(); //userService.getUserByEmail("blange@mail.ru");//principal.getName());
    }
}
