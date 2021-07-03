package space.androma.auction.payment.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String main() {
        log.info("PaymentService working/ 8081/ mapping! ");
        //UserDto userDto = UserDto.builder().name("name3").email("blange@mail.ru").build();
        //userService.addUser(userDto);
        return "Main Page PayController"; //userService.getUserByEmail("blange@mail.ru");//principal.getName());
    }
}