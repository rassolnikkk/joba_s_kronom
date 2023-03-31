package jobaskronom.demo.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jobaskronom.demo.DTO.DaData.DaDataCompany;
import jobaskronom.demo.DTO.DaData.DaDataResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DaDataService {


    private ObjectMapper objectMapper;
    private String baseUrl;

    private String token;

    public DaDataService(@Value("${dadata.uri}")String baseUrl, @Value("${dadata.token}")String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }
    public List<DaDataCompany> getDaDataCompaniesByInns(List<String> inns)  {
        WebClient client = WebClient.create();
        ArrayList<DaDataCompany> listOfCompanies = new ArrayList<>();
        for (String inn : inns) {
            DaDataResponse responseBody = null;
            try {
                responseBody = client
                        .get()
                        .uri(baseUrl + "?query=" + inn + "&count=1")
                        .header("Authorization", token)
                        .retrieve()
                        .toEntity(DaDataResponse.class)
                        .toFuture()
                        .get()
                        .getBody();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            DaDataCompany daDataCompany = responseBody.getSuggestions().get(0);
            listOfCompanies.add(daDataCompany);
        }
        return listOfCompanies;
    }
}
