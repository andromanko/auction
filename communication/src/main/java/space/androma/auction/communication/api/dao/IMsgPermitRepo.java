package space.androma.auction.communication.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.communication.entity.MsgPermit;


public interface IMsgPermitRepo extends MongoRepository<MsgPermit, String> {

    @Query("{'lotId' : ?0}")
    MsgPermit findByLotId(String lotId);

    //TODO ТЕСТИТЬ! ФИКСИТЬ! @query не уверен
    @Query("{'buyerId' OR 'sellerId: ?0}")
    MsgPermit findByUserId(String userId);
}