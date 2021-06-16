package space.androma.auction.trades.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import space.androma.auction.trades.api.dao.UserRepo;
import space.androma.auction.trades.entity.User;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepo repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getUser(String id) {
        return repository.findById(id).orElse(null);
    }

    public void addUser(User user) {
        repository.insert(user);
    }
}
