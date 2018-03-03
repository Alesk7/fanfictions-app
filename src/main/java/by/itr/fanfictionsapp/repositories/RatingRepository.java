package by.itr.fanfictionsapp.repositories;

import by.itr.fanfictionsapp.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    @Query(value = "SELECT rate FROM rating r INNER JOIN fanfiction_rates c ON r.id=c.rates_id WHERE r.user_account_id= :userId AND c.fanfiction_id= :fanfictionId",
           nativeQuery = true)
    Integer getUserRate(@Param("userId") Long userId, @Param("fanfictionId") Long fanfictionId);


    @Query(value = "SELECT AVG(rate) FROM rating r INNER JOIN fanfiction_rates c ON r.id = c.rates_id WHERE c.fanfiction_id = :fanfictionId",
           nativeQuery = true)
    Double getAverageRateByFanfictionId(@Param("fanfictionId") Long fanfictionId);

}
