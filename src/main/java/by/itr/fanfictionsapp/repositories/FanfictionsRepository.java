package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.Fanfiction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FanfictionsRepository extends CrudRepository<Fanfiction, Long> {

    @Query("from Fanfiction f inner join fetch f.userAccount where f.userAccount.id = :id")
    Iterable<Fanfiction> findByUserAccountId(@Param("id") Long id);

    @Query("from Fanfiction f inner join fetch f.userAccount where f.userAccount.email = :email")
    Iterable<Fanfiction> findByUserAccountEmail(@Param("email") String email);

}
