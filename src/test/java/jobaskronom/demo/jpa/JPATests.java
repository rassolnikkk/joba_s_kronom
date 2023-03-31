package jobaskronom.demo.jpa;

import jobaskronom.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JPATests {

    @Autowired
    private CompanyService companyService;


}
