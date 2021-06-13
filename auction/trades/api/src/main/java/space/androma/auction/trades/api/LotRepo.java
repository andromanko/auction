package space.androma.auction.trades.api;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import space.androma.auction.trades.entity.Lot;

public interface LotRepo extends CrudRepository<Lot, Long> {
    @Query("{'name' : ?0}")
    public Iterable<Lot> searchByName(String name);

}