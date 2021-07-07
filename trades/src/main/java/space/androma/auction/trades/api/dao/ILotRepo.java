package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.trades.entity.Lot;

import java.util.Optional;

public interface ILotRepo extends MongoRepository<Lot, String> {
    @Query("{'name' : ?0}")
    Optional<Lot> findByName(String name);
}