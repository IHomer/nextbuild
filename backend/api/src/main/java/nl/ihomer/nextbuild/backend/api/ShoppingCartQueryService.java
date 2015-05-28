package nl.ihomer.nextbuild.backend.api;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;

import java.util.List;
import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public interface ShoppingCartQueryService {
    List<ShoppingCart> findAll();
    ShoppingCart findOne(UUID id);
}
