package space.androma.auction.trades.rest.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.LotDto;

import java.security.Principal;

@RestController
@RequestMapping("/lots")
public class LotController {

    @GetMapping()
    public ModelAndView getAllLots() {
        return null;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getLot(long id) {
        return null;
    }

    @PostMapping(value = "/give")
    public ModelAndView givePriceForLot(long lotId, long userPrice, Principal principal) {
        return null;
    }

    @PostMapping(value = "/add")
    public ModelAndView createLot(LotDto lot) {
//        ModelAndView modelAndView = new ModelAndView("lotPage");
//        modelAndView.addObject("lot", this.lotService.createLot(lot));
        return null;
    }

    @PostMapping(value = "/upd")
    public RedirectView updateLot(Principal principal, LotDto lot, @RequestParam(value = "file", required = false) MultipartFile file) {
        return null;
    }
}
