package esipe.fr.customer.repositories;

import esipe.fr.customer.entities.Shop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopRepositoryInMemory {
    private ArrayList<Shop> shops;

    public ShopRepositoryInMemory() {
        this.shops = new ArrayList<>();
        this.shops.add(new Shop("decat","9h-20h"));
        this.shops.add(new Shop("FNAC","9h-20h"));
    }


    public List<Shop> getAllShop(){
        return this.shops;
    }

}
