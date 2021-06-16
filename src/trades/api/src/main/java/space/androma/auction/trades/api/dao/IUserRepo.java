package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.trades.entity.User;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

  //пока не надо
    @Query("{'email' : ?0}")
    Optional<User> findByEmail (String email);

}