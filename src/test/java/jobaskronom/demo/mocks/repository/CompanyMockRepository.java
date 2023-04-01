package jobaskronom.demo.mocks.repository;

import jobaskronom.demo.DTO.CompanyRequest;
import jobaskronom.demo.models.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMockRepository extends MockRepository<Company> {

    public Company saveCompanyFromCompanyRequest(CompanyRequest companyRequest){
        var company = Company.builder()
                .companyName(companyRequest.getCompanyName())
                .inn(companyRequest.getInn())
                .build();
        super.save(company);
        return company;
    }

    public List<Company> getMockTable(){
        return super.mockTable;
    }

}
