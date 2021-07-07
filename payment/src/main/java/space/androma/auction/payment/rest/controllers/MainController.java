package space.androma.auction.payment.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.payment.api.services.ITradesConnectionService;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    ITradesConnectionService tradesConnectionService;

    @GetMapping()
    public String main() {
        log.info("PaymentService working/ 8081/ mapping! ");

        return "Main Page PayController. Connect with Trades:" + tradesConnectionService.ping();
    }

}
