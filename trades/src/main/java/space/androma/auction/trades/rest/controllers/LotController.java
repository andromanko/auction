package space.androma.auction.trades.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import space.androma.auction.trades.api.dto.LotDto;
import space.androma.auction.trades.api.service.ILotService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    ILotService lotService;

    //TODO PAGEABLE?
    @GetMapping()
    public List<LotDto> getAllLots() {
        return lotService.getAll();
    }

    @GetMapping(value = "/{id}")
    public LotDto getLot(String id) {
        LotDto lotDto = lotService.getLotById(id);
        return lotDto;
    }

    @PostMapping(value = "/give")
    public Long givePriceForLot(long lotId, long userPrice) {
        return null;// lotService.updateLot(lotId, userPrice);
    }

    @PostMapping(value = "/add")
    public ModelAndView createLot(LotDto lot) {
        ModelAndView modelAndView = new ModelAndView("lotPage");
        modelAndView.addObject("lot", this.lotService.addLot(lot));
        return null;
    }

    @PostMapping(value = "/upd")
    public RedirectView updateLot(Principal principal, LotDto lot, @RequestParam(value = "file", required = false) MultipartFile file) {
        return null;
    }
}
