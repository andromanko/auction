package space.androma.auction.communication.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.androma.auction.communication.api.services.ITradesConnectionService;

@PropertySource("classpath:application.properties")
@Service
public class TradesConnectionService implements ITradesConnectionService {

    @Value("${space.androma.auction.trades.uri}")
    private String tradesUri;

    @Autowired
    //@LoadBalanced
    protected RestTemplate restTemplate;

    @Override
    public boolean UserPermitCommunicate(String lotId, String userId) {
        String url = tradesUri+
                "/msg/"+lotId+"/"+userId;
         return  restTemplate.getForObject(url,Boolean.class ,lotId,  userId);

    }

    @Override
    public boolean ping()
    {
       try {
            return restTemplate.getForObject(tradesUri, Boolean.class);
        }
        catch (Exception ConnectException) {
            return false;
         }

    }
}



