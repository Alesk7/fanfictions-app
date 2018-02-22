package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.Fanfiction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FanfictionsRepository extends PagingAndSortingRepository<Fanfiction, Long> {

    @Query(value = "select f from Fanfiction f inner join f.userAccount u where u.id = ?1",
           countQuery = "select count(f) from Fanfiction f inner join f.userAccount u where u.id = ?1")
    Page<Fanfiction> findByUserAccountId(Long id, Pageable pageable);

    @Query(value = "select f from Fanfiction f inner join f.userAccount u where u.email = ?1",
           countQuery = "select count(f) from Fanfiction f inner join f.userAccount u where u.email = ?1")
    Page<Fanfiction> findByUserAccountEmail(String email, Pageable pageable);

    @Query(value = "select f from Fanfiction f order by f.creationDate",
            countQuery = "select count(f) from Fanfiction f order by f.creationDate")
    Page<Fanfiction> findFresh(Pageable pageable);

}
