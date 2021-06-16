package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.trades.entity.User;

public interface UserRepo extends MongoRepository<User, String> {

    @Query("{'name' : ?0}")
    Iterable<User> searchByName(String name);

}