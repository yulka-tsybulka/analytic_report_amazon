package analytic.report.amazon.repository;

import analytic.report.amazon.model.SalesAndTrafficByDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAndTrafficByDateRepository extends MongoRepository<
        SalesAndTrafficByDate, String> {
    List<SalesAndTrafficByDate> findByDate(String date);

    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<SalesAndTrafficByDate> findByDateRange(String startDate, String endDate);
}
