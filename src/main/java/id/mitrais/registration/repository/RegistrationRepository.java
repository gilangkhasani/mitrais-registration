package id.mitrais.registration.repository;

import id.mitrais.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gpk
 */
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
