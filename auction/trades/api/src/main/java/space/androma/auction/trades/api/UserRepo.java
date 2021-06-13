package space.androma.auction.trades.api;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import space.androma.auction.trades.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {

    @Query("{'name' : ?0}")
    Iterable<User> searchByName(String name);

}