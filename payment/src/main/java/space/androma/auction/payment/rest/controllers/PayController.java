package space.androma.auction.payment.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.androma.auction.payment.api.dto.PaymentDto;
import space.androma.auction.payment.api.services.IPaymentService;
import space.androma.auction.payment.api.services.ITradesConnectionService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    IPaymentService paymentService;

    @Autowired
    ITradesConnectionService tradesConnectionService;

    //рпочитать всё
    @GetMapping()
    public List<PaymentDto> getAllPayments()
    {
        return paymentService.getAllPayments();
    }

    @GetMapping(value = "/user/{userId}")
    public List<PaymentDto> getUserPayments(@PathVariable String userId)
    {
        log.info("getUserPayments controller");
        return paymentService.getPaymentsByUser(userId);
    }

    @GetMapping(value = "/lot/{lotId}")
    public PaymentDto getLotPayments(@PathVariable String lotId)
    {
        log.info("getLotPaymenT controller");
        return paymentService.getInfoByLot(lotId);
    }

    //запостить касаемо ЛОТа
    @PostMapping()
    public boolean payForLot( @RequestBody  PaymentDto paymentDto)
    {

        return paymentService.payForLot(paymentDto);
    }
    //тлллллььььььькооооооооо дяя ттттттесссссстааааааааа//

    @GetMapping(value = "/questToTrade/{lotId}/{userId}")
    public boolean questionToTrades(@PathVariable String lotId,@PathVariable String userId) {
        return tradesConnectionService.UserPermitPay(lotId, userId);
    }

}
