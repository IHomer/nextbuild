package nl.ihomer.nextbuild.backend.view.services;

import nl.ihomer.nextbuild.backend.api.ShoppingCartQueryService;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bvangameren on 26/05/15.
 */
@Service
public class ShoppingCartQueryServiceImpl implements ShoppingCartQueryService {

    @Autowired
    private ShoppingCartEntityRepository shoppingCartEntityRepository;

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> carts = new ArrayList<>();
        carts.addAll(shoppingCartEntityRepository.findAll());
        return carts;
    }
}
