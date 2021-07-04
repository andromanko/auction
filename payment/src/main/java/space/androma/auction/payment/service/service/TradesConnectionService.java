package space.androma.auction.payment.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.androma.auction.payment.api.services.ITradesConnectionService;

@Service
public class TradesConnectionService implements ITradesConnectionService {

    //TODO - разробраться откуда будет это имя? Из контейнеров?
    public static final String TRD_SERVER_URL = "http://127.0.0.1";

    @Autowired
    protected RestTemplate restTemplate;

    @Override
    public boolean UserPermitPay(String lotId, String userId) {
        String url = TRD_SERVER_URL+":8080"+
                "/ext/pay/"+lotId+"/"+userId;
        return  restTemplate.getForObject(url,Boolean.class ,lotId,  userId);


/*        URI uri = URI.create(url);
        HttpRequest.Builder httpReq = HttpRequest.newBuilder(uri);
        client.send(httpReq,responseHandler().);*/

/*
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .retrieve();
*/




    }
}
