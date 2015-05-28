package nl.ihomer.nextbuild.backend.api;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public interface ShoppingCartCommandService {

    void registerShoppingCart(String name);

    void addShoppingCartItem(UUID id, String item);

    void removeShoppingCartItem(UUID id, String item);

    void checkoutShoppingCart(UUID id);

    void cancelShoppingCartCheckout(UUID id);

    void acceptShoppingCart(UUID id);

    void rejectShoppingCart(UUID id);

}
