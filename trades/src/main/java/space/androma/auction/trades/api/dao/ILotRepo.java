package space.androma.auction.trades.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import space.androma.auction.trades.entity.Lot;

public interface ILotRepo extends MongoRepository<Lot, String> {
  /*  @Query("{'name' : ?0}")
    public Iterable<Lot> searchByName(String name);
*/
}