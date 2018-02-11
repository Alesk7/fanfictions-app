package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
