package nl.ihomer.nextbuild.backend.view.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public interface ShoppingCartEntityRepository extends JpaRepository<ShoppingCartEntity, UUID> {

}
