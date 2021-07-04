package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.trades.api.service.ILotService;


@Slf4j
@RestController
@RequestMapping("/ext")
public class ExtController {

    @Autowired
    ILotService lotService;

    @GetMapping(value = "/msg/{lotId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public boolean userPermitCommunicate(@PathVariable String lotId, @PathVariable String userId)
    {
        return lotService.getUserPermitCommunicate(lotId, userId);
    }

    @GetMapping(value = "/pay/{lotId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public boolean userPermitToPayForLot(@PathVariable String lotId, @PathVariable String userId)
    {
        return lotService.getUserPermitPayForLot(lotId, userId);
    }

    @GetMapping()
    public boolean ping() {
        return true;
    }
}
