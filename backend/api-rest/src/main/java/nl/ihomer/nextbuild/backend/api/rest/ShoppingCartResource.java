package nl.ihomer.nextbuild.backend.api.rest;

import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.api.rest.messages.RegisterShoppingCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartResource.class);

    @Autowired
    private ShoppingCartCommandService shoppingCartCommandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerShoppingCart(@RequestBody RegisterShoppingCartRequest request) {
        LOG.debug("Register shopping cart");
        shoppingCartCommandService.registerShoppingCart(request.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getShoppingCarts() {
        LOG.debug("List shopping carts");
//        Page<Person> persons = personService.findPersons(pageable, searchFields);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
