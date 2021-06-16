package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.trades.entity.Lot;

public interface LotRepo extends MongoRepository<Lot, String> {
    @Query("{'name' : ?0}")
    public Iterable<Lot> searchByName(String name);

}