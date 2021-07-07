package space.androma.auction.payment.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import space.androma.auction.payment.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentRepo extends MongoRepository<Payment, String> {

    @Query("{'lotId' : ?0}")
    Optional<Payment> findByLotId(String lotId);

    //TODO ТЕСТИТЬ! ФИКСИТЬ! @query не уверен
    @Query("{'userId' : ?0}")
    List<Payment> findByUserId(String userId);
}