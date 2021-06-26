package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.api.service.ILotService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lot")
public class LotController {

    @Autowired
    ILotService lotService;

    //TODO PAGEABLE?
    @GetMapping()
    public List<LotDto> getAllLots() {
        return lotService.getLots();
    }

    @GetMapping(value = "/{id}")
    public LotDto getLot(@PathVariable String id) {
        LotDto lotDto = lotService.getLotById(id);
        return lotDto;
    }

    @PostMapping(value = "/give")
    public boolean givePriceForLot(@RequestBody String lotId, long proposedPrice,String userId) {

        return lotService.givePriceForLot(lotId,userId,proposedPrice);
    }

    @PostMapping(value = "/add")
    public String addLot( @RequestBody LotDto lot) {

        return lotService.addLot(lot);
    }

    @PostMapping(value = "/upd")
    public RedirectView updateLot(Principal principal, LotDto lot,
                                  @RequestParam(value = "file", required = false)
                                          MultipartFile file) {
        return null;
    }
}
