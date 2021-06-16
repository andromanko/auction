package space.androma.auction.trades.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import space.androma.auction.trades.api.dao.LotRepo;
import space.androma.auction.trades.entity.Lot;

import java.util.List;

public class LotService {

    @Autowired
    private LotRepo repository;

    public List<Lot> getAll() {
        return repository.findAll();
    }

    public Lot getLot(String id) {
        return repository.findById(id).orElse(null);
    }

    public void addLot (Lot lot) {
        repository.save(lot);
    }
}
