package space.androma.auction.trades.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.api.dto.UserDto;
import space.androma.auction.trades.entity.Role;
import space.androma.auction.trades.entity.User;

import java.util.Collections;

@RestController
public class SignupController {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String registration()
    {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@RequestBody UserDto userDto)
    {
    //TODO выкинуть в userService!!!
        User user = new User();
        user.setUsername(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setBalance(userDto.getBalance());
        //user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));  //TODO захардкожена роль

        userRepo.save(user);

        return "redirect:/login";
    }
}
