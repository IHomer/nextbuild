package nl.ihomer.nextbuild.backend.api.rest;

import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.api.ShoppingCartQueryService;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;
import nl.ihomer.nextbuild.backend.api.rest.messages.RegisterShoppingCartRequest;
import nl.ihomer.nextbuild.backend.api.rest.messages.ShoppingCartItemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartResource.class);

    @Autowired
    private ShoppingCartCommandService shoppingCartCommandService;

    @Autowired
    private ShoppingCartQueryService shoppingCartQueryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerShoppingCart(@RequestBody RegisterShoppingCartRequest request) {
        LOG.debug("Register shopping cart");
        shoppingCartCommandService.registerShoppingCart(request.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/add", method = RequestMethod.PUT)
    public ResponseEntity<?> addShoppingCartItem(@PathVariable UUID id, @RequestBody ShoppingCartItemRequest request) {
        LOG.debug("Add shopping cart item");
        shoppingCartCommandService.addShoppingCartItem(id, request.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.PUT)
    public ResponseEntity<?> removeShoppingCartItem(@PathVariable UUID id, @RequestBody ShoppingCartItemRequest request) {
        LOG.debug("Remove shopping cart item");
        shoppingCartCommandService.removeShoppingCartItem(id, request.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/checkout", method = RequestMethod.PUT)
    public ResponseEntity<?> checkoutShoppingCart(@PathVariable UUID id) {
        LOG.debug("Checkout shopping cart");
        shoppingCartCommandService.checkoutShoppingCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.PUT)
    public ResponseEntity<?> cancelShoppingCartCheckout(@PathVariable UUID id) {
        LOG.debug("Cancel shopping cart checkout");
        shoppingCartCommandService.cancelShoppingCartCheckout(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/accept", method = RequestMethod.PUT)
    public ResponseEntity<?> acceptShoppingCart(@PathVariable UUID id) {
        LOG.debug("Accept shopping cart");
        shoppingCartCommandService.acceptShoppingCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/reject", method = RequestMethod.PUT)
    public ResponseEntity<?> rejectShoppingCart(@PathVariable UUID id) {
        LOG.debug("Reject shopping cart");
        shoppingCartCommandService.rejectShoppingCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getShoppingCarts() {
        LOG.debug("List shopping carts");
        List<ShoppingCart> carts = shoppingCartQueryService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
