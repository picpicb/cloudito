package esipe.fr.customer.services;

import esipe.fr.customer.entities.Shop;
import esipe.fr.customer.repositories.ShopRepository;
import esipe.fr.customer.repositories.ShopRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ServiceImpl<Shop>{
    @Autowired
    private ShopRepositoryInMemory shopRepository;


    @Override
    public Shop save(Shop shop) {
        return null;
    }

    @Override
    public Optional<Shop> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Shop shop) {

    }

    @Override
    public List<Shop> findAll() {
        return this.shopRepository.getAllShop();
    }
}
