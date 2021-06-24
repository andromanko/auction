package space.androma.auction.communication.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.communication.entity.Message;


import java.util.List;

public interface IMsgRepo extends MongoRepository<Message, String> {

    @Query("{'lotId' : ?0}")
    List<Message> findByLotId(String lotId);

    //TODO ТЕСТИТЬ! ФИКСИТЬ! @query не уверен
    @Query("{'buyerId' OR 'sellerId: ?0}")
    List<Message> findByUserId(String userId);
}