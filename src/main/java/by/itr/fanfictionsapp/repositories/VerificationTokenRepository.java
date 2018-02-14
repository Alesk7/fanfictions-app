package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
