package analytic.report.amazon.repository;

import analytic.report.amazon.model.SalesAndTrafficByAsin;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAndTrafficByAsinRepository extends MongoRepository<
        SalesAndTrafficByAsin, String> {
    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
