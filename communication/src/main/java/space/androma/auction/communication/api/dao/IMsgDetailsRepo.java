package space.androma.auction.communication.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.communication.entity.MsgDetails;

import java.util.Optional;


public interface IMsgDetailsRepo extends MongoRepository<MsgDetails, String> {

    @Query("{'lotId' : ?0}")
    Optional<MsgDetails> findByLotId(String lotId);

}