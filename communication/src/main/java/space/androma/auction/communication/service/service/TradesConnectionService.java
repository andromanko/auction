package space.androma.auction.communication.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import space.androma.auction.communication.api.services.ITradesConnectionService;

@Service
@Transactional
public class TradesConnectionService implements ITradesConnectionService {

    //TODO - разробраться откуда будет это имя? Из контейнеров?
    public static final String TRD_SERVER_URL = "http://trades-service";

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Override
    public boolean UserPermitCommunicate(String lotId, String userId) {

         return  restTemplate.getForObject(
                TRD_SERVER_URL+"/lot/{lotId}/{userId}",
                 Boolean.class ,lotId,  userId);

    }


}