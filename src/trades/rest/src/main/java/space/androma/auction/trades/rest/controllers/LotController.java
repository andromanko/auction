package space.androma.auction.trades.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.service.services.LotService;

import java.security.Principal;

@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @GetMapping()
    public ModelAndView getAllLots() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lotsPage");
        modelAndView.addObject("lotsList", lotService.getAll());
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getLot(String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lotsPage");
        modelAndView.addObject("lot", lotService.getLot(id));
        return modelAndView;
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
