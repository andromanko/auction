package space.androma.auction.payment.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.androma.auction.payment.api.services.ITradesConnectionService;

@Service
public class TradesConnectionService implements ITradesConnectionService {

    @Value("${space.androma.auction.trades.uri}")
    private String tradesUri;

    @Autowired
    protected RestTemplate restTemplate;

    @Override
    public boolean UserPermitPay(String lotId, String userId) {
        String url = tradesUri +
                "/ext/pay/" + lotId + "/" + userId;
        return restTemplate.getForObject(url, Boolean.class, lotId, userId);
    }

    @Override
    public boolean ping() {
        try {
            return restTemplate.getForObject(tradesUri, Boolean.class);
        } catch (Exception ConnectException) {
            return false;
        }

    }
}
