package esipe.fr.customer.controllers;


import esipe.fr.customer.entities.Shop;
import esipe.fr.customer.services.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {
    @Autowired
    private ShopServiceImpl shopService;


    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    @ResponseBody
    public List<Shop> getAllShops() {return this.shopService.findAll();}
}
