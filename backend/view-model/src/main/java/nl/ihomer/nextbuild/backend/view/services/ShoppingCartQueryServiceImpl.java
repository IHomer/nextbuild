package nl.ihomer.nextbuild.backend.view.services;

import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntityRepository;
import nl.ihomer.nextbuild.backend.api.ShoppingCartQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bvangameren on 26/05/15.
 */
@Service
public class ShoppingCartQueryServiceImpl implements ShoppingCartQueryService {

    @Autowired
    private ShoppingCartEntityRepository shoppingCartEntityRepository;


}
