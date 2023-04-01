package jobaskronom.demo.service;

import jobaskronom.demo.DTO.CompanyRequest;
import jobaskronom.demo.DTO.DaData.*;
import jobaskronom.demo.models.Company;
import jobaskronom.demo.repostitories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final DaDataService daDataService;

    @Transactional
    public void updateCompanies(List<DaDataCompany> daDataCompanies){
        Map<String, DaDataCompany> innToDaDataCompany = daDataCompanies.stream()
                .collect(Collectors.toMap(
                        DaDataCompany::getInn, daDataCompany -> daDataCompany
                ));

        List<Company> allCompanies = companyRepository.findAll();
        for (var company : allCompanies) {
            DaDataCompany daDataCompany = innToDaDataCompany.get(company.getInn());

            if (daDataCompany == null) {
                System.out.println("В dadat'e нет компании с inn = " + company.getInn());
                continue;
            }

            company.setManagerName(daDataCompany.getFio());
            company.setDaDataName(daDataCompany.getName());
            company.setKpp(daDataCompany.getKpp());
            company.setOgrn(daDataCompany.getOgrn());
            company.setPost(daDataCompany.getPost());
            company.setAddress(daDataCompany.getAddress());
        }

        companyRepository.saveAll(allCompanies);
    }


    public void updateAllExistingCompaniesWithDaData()  {
        List<String> allInns = companyRepository.getAllInns();
        List<DaDataCompany> companies = daDataService.getDaDataCompaniesByInns(allInns);
        updateCompanies(companies);
    }

    public Company saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setInn(companyRequest.getInn());

        company.setDaDataName(companyRequest.getCompanyNameDaData());
        company.setKpp(companyRequest.getKpp());
        company.setManagerName(companyRequest.getManagerName());
        company.setPost(companyRequest.getPost());
        company.setOgrn(companyRequest.getOgrn());
        company.setAddress(companyRequest.getAddress());

        return companyRepository.save(company);
    }
}
// тесты с моками
// liquibase
