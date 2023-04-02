package jobaskronom.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jobaskronom.demo.DTO.DaData.DaDataCompany;
import jobaskronom.demo.DTO.DaData.DaDataResponse;
import jobaskronom.demo.DemoApplication;
import jobaskronom.demo.DemoApplicationTests;
import jobaskronom.demo.infranstructure.HttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DaDataServiceTest extends DemoApplicationTests {

    private final HttpClient httpClient = Mockito.mock(HttpClient.class);

    @Autowired
    private DaDataService daDataService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void test() {
        try {
            when(httpClient.doPost(null, null, null)).thenReturn(objectMapper.readValue(new File("src/test/resources/templates/DaDataResponse.json"), DaDataResponse.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<DaDataCompany> daDataCompanies = daDataService.getDaDataCompaniesByInns(List.of("7744001497"));
        assertThat(daDataCompanies.size()).isEqualTo(1);
        assertThat(daDataCompanies.get(0).getKpp()).isEqualTo("772801001");
    }

}
