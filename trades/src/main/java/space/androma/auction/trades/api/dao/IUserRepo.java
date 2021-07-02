package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.trades.entity.AuUser;

import java.util.Optional;

public interface IUserRepo extends MongoRepository<AuUser, String> {

    //пока не надо
    @Query("{'email' : ?0}")
    Optional<AuUser> findByEmail(String email);

    @Query("{'name' : ?0}")
    Optional<AuUser> findByName(String name);

}