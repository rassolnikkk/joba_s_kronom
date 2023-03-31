package jobaskronom.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jobaskronom.demo.DTO.CompanyRequest;
import jobaskronom.demo.DTO.DaData.*;
import jobaskronom.demo.models.Company;
import jobaskronom.demo.repostitories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final DaDataService daDataService;

    //получить список всех компаний из бд
    //вытащить список инн по компаниям с бд
    //получить список компаний из дадаты
    //соотнести по инну и обновить компании бдщные компании из дадтаты
    @Transactional
    public void updateCompanies(List<DaDataCompany> daDataCompanies){
        Map<String, DaDataCompany> daDataMap = daDataCompanies.stream().collect(Collectors.toMap(DaDataCompany::getInn, daDataCompany -> daDataCompany));
        List<Company> allCompanies = companyRepository.findAll();
        Map<String, Company> companyMap = allCompanies.stream().collect(Collectors.toMap(Company::getInn, c -> c));
        HashMap<String, Company> companyHashMap = new HashMap<>(companyMap);
        Set<Map.Entry<String, DaDataCompany>> daDataEntrySet = daDataMap.entrySet();
        for (Map.Entry<String, DaDataCompany> daDataEntry: daDataEntrySet){
            Company company = companyHashMap.get(daDataEntry.getKey());
            DaDataCompany daDataEntryValue = daDataEntry.getValue();
            company.setFio(daDataEntryValue.getFio());
            company.setName(daDataEntryValue.getName());
            company.setKpp(daDataEntryValue.getKpp());
            company.setOgrn(daDataEntryValue.getOgrn());
            company.setPost(daDataEntryValue.getPost());
            company.setAddress(daDataEntryValue.getAddress());
            companyRepository.save(company);
        }

//        for (DaDataCompany daDataCompany: daDataCompanies) {
//            Company company = new Company();
//            String companyName = daDataCompany.getName();
//            DaDataCompanyData daDataCompanyData = daDataCompany.getDaDataCompanyData();
//            String kpp = daDataCompanyData.getKpp();
//            String ogrn = daDataCompanyData.getOgrn();
//            company.setOgrn(ogrn);
//            company.setKpp(kpp);
//            DaDataCompanyManagement daDataCompanyManagement = daDataCompanyData.getDaDataCompanyManagement();
//            String managementName = daDataCompanyManagement.getName();
//            String managementPost = daDataCompanyManagement.getPost();
//            company.setFio(managementName);
//            company.setPost(managementPost);
//            DaDataCompanyAddress daDataCompanyAddress = daDataCompanyData.getDaDataCompanyAddress();
//            String companyAddress = daDataCompanyAddress.getValue();
//            company.setName(companyName);
//            company.setAddress(companyAddress);// тут можно сделать селект для оптимизации обращений к бд
//            Company persistedCompany = companyRepository.findCompanyByInn(daDataCompanyData.getInn()).get();
//            persistedCompany.setAddress(company.getAddress());
//            persistedCompany.setName(company.getName());
//            persistedCompany.setOgrn(company.getOgrn());
//            persistedCompany.setKpp(company.getKpp());
//            persistedCompany.setFio(company.getFio());
//            persistedCompany.setPost(company.getPost());
//            companyRepository.save(persistedCompany);
//        }
    }




    public Company createCompanyWithInnAndCompanyName(String companyName, String inn){
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setInn(inn);
        return company;
    }

    public List<String> getAllInns(){
        return companyRepository.getAllInns();
    }

    public void updateAllExistingCompanies()  {
        List<String> allInns = getAllInns();
        List<DaDataCompany> companies = daDataService.getDaDataCompaniesByInns(allInns);
        updateCompanies(companies);
    }

    public Company saveNewCompanyFromCompanyRequest(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setInn(companyRequest.getInn());
        companyRepository.save(company);
        return company;
    }
}
// тесты с моками
// liquibase
