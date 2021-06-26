package space.androma.auction.payment.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.api.services.IPaymentService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    IPaymentService paymentService;

    //рпочитать всё
    @GetMapping()
    public List<PaymentDto> getAllPayments()
    {
        return paymentService.getAllPayments();
    }

    @GetMapping(value = "/user/{userId}")
    public List<PaymentDto> getUserPayments(@PathVariable String userId)
    {
        return paymentService.getPaymentsByUser(userId);
    }

    @GetMapping(value = "/lot/{lotId}")
    public PaymentDto getLotPayments(@PathVariable String lotId)
    {
        return paymentService.getInfoByLot(lotId);
    }

    //запостить касаемо ЛОТа
    @PostMapping()
    public boolean payForLot( @RequestBody  PaymentDto paymentDto)
    {
       return paymentService.payForLot(paymentDto);
    }

}
