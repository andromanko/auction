package space.androma.auction.trades.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import space.androma.auction.security.Result;
import space.androma.auction.trades.api.service.ILotService;
import space.androma.auction.trades.api.service.IUserService;

import static space.androma.auction.security.Result.run;


@Slf4j
@RestController
@RequestMapping("/ext")
public class ExtController {

    @Autowired
    ILotService lotService;

    @Autowired
    IUserService userService;

    @GetMapping(value = "/msg/{lotId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public boolean userPermitCommunicate(@PathVariable String lotId, @PathVariable String userId)
    {
        return lotService.getUserPermitCommunicate(lotId, userId);
    }

    @GetMapping(value = "/pay/{lotId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public boolean userPermitToPayForLot(@PathVariable String lotId, @PathVariable String userId)
    {
        return lotService.getUserPayForLot(lotId, userId);
    }
//---only for test-connect =)
    @GetMapping()
    public boolean ping() {
        return true;
    }

    //---------forSequr
/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestParam(value = "login") final String login,
                                @RequestParam(value = "password") final String password) {
        return run(() -> userService.login(login, password));
    }*/

    //@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE )
    @GetMapping(value = "/login/{username}")
    public Result<String> login (@PathVariable String username){//, @RequestBody String password) {
        //String login = "user1";
        String password=username; //TODO
        return run(() -> userService.login(username, password));
    }



}
