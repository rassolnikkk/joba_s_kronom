package jobaskronom.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jobaskronom.demo.DTO.DaData.DaDataCompany;
import jobaskronom.demo.DemoApplicationTests;
import jobaskronom.demo.models.Company;
import jobaskronom.demo.repostitories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CompanyServiceTest extends DemoApplicationTests {

    @Autowired
    private CompanyService companyService;
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;


    private List<Company> data_companies_Mock = new ArrayList<>();


    @Test
    public void updateCompaniesTest() {
        when(companyRepository.findAll()).thenReturn(data_companies_Mock);
        when(companyRepository.saveAll(null)).thenAnswer(c -> data_companies_Mock.addAll((List<Company>) c));
        List<DaDataCompany> daDataCompanies = new ArrayList<>();

        try {
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/alfa.json"), DaDataCompany.class));
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/tinek.json"), DaDataCompany.class));
            daDataCompanies.add(objectMapper.readValue(new File("src/test/resources/templates/dadatacompanies_json/sber.json"), DaDataCompany.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        data_companies_Mock.add(new Company("ООО СБЕР", "7707083893"));
        data_companies_Mock.add(new Company("ООО АЛЬФАБАНК", "7728168971"));
        data_companies_Mock.add(new Company("ГАЗПРОМ БАНК", "7744001497"));


        List<Company> listik = companyRepository.findAll();
        companyService.updateCompanies(daDataCompanies);
        System.out.println(data_companies_Mock);
        assertThat(data_companies_Mock.get(0).getKpp()).isEqualTo("773601001");
    }

}

