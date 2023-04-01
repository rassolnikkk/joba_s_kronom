package jobaskronom.demo.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import jobaskronom.demo.DTO.CompanyRequest;
import jobaskronom.demo.DTO.DaData.DaDataCompany;
import jobaskronom.demo.mocks.repository.CompanyMockRepository;
import jobaskronom.demo.models.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CompanyTests {

    @Autowired
    private CompanyMockRepository companyMockRepository;

    private List<DaDataCompany> daDataCompanies = new ArrayList<>();

    @Autowired
    private ObjectMapper objectMapper;

    private CompanyRequest companyRequest;

    private Company company;


    @BeforeEach
    public void injectCompanyAndCompanyRequest(){
        companyRequest = CompanyRequest.builder()
                .companyName("OOO Сбер")
                .inn("7707083893")
                .build();
        company = Company.builder()
                .id(1L)
                .kpp("773601001")
                .managerName("Греф Герман Оскарович")
                .companyName(companyRequest.getCompanyName())
                .address("г Москва, ул Вавилова, д 19")
                .inn(companyRequest.getInn())
                .post("ПРЕЗИДЕНТ, ПРЕДСЕДАТЕЛЬ ПРАВЛЕНИЯ")
                .ogrn("1027700132195")
                .daDataName("ПАО СБЕРБАНК")
                .build();
        try {
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/alfa.json"), DaDataCompany.class));
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/tinek.json"), DaDataCompany.class));
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/sber.json"), DaDataCompany.class));
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/gazprom.json"), DaDataCompany.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    public void emptyMockTable(){
        companyMockRepository.emptyMockTable();
    }


    @Test
    public void givenCompanyRequest_thenAddCompany_shouldContain(){
        Company company = companyMockRepository.saveCompanyFromCompanyRequest(companyRequest);
        assertThat(companyMockRepository.checkIfExists(company)).isTrue();
    }

    @Test
    public void givenDaDataCompaniesAndCompanyRequest_thenAddDaDataCompanies_shouldNotHaveNullFields(){
        Map<String, DaDataCompany> innToDaDataCompany = daDataCompanies.stream().collect(Collectors.toMap(
                DaDataCompany::getInn, daDataCompany -> daDataCompany
        ));
        Company companyForCheck;
        for (var company: companyMockRepository.getMockTable()){
            DaDataCompany daDataCompany = innToDaDataCompany.get(company.getInn());
            company.setManagerName(daDataCompany.getFio());
            company.setDaDataName(daDataCompany.getName());
            company.setKpp(daDataCompany.getKpp());
            company.setOgrn(daDataCompany.getOgrn());
            company.setPost(daDataCompany.getPost());
            company.setAddress(daDataCompany.getAddress());
            companyForCheck = company;
            assertThat(companyMockRepository.getMockTable().contains(companyForCheck));
            assertThat(companyForCheck.getManagerName().equals("Греф  Герман Оскарович"));
        }

    }


}
