package jobaskronom.demo.repostitories;

import jobaskronom.demo.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT c.inn from Company as c")
    List<String> getAllInns();

    Optional<Company> findCompanyByInn(String inn);
}


