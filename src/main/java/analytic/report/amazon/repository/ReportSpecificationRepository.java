package analytic.report.amazon.repository;

import analytic.report.amazon.model.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSpecificationRepository extends MongoRepository<
        ReportSpecification, String> {
    ReportSpecification findFirstByOrderByIdAsc();
}
