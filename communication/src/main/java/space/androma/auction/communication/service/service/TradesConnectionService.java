package space.androma.auction.communication.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.androma.auction.communication.api.services.ITradesConnectionService;

@Service
public class TradesConnectionService implements ITradesConnectionService {

    //TODO - разробраться откуда будет это имя? Из контейнеров?
    public static final String TRD_SERVER_URL = "http://127.0.0.1:8080/ext";

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Override
    public boolean UserPermitCommunicate(String lotId, String userId) {
        String url = TRD_SERVER_URL+
                "/msg/"+lotId+"/"+userId;
         return  restTemplate.getForObject(url,Boolean.class ,lotId,  userId);

    }

    @Override
    public boolean ping()
    {
       try {
            return restTemplate.getForObject(TRD_SERVER_URL, Boolean.class);
        }
        catch (Exception ConnectException) {
            return false;
         }

    }
}



