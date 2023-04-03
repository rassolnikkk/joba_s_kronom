package jobaskronom.demo.service;


import jobaskronom.demo.DTO.DaData.DaDataCompany;
import jobaskronom.demo.DTO.DaData.DaDataResponse;
import jobaskronom.demo.infranstructure.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DaDataService {
    private final HttpClient httpClient;
    private final String baseUrl;
    private final String token;

    public DaDataService(
            HttpClient httpClient, @Value("${dadata.uri}") String baseUrl,
            @Value("${dadata.token}") String token
    ) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public List<DaDataCompany> getDaDataCompaniesByInns(List<String> inns) {
        ArrayList<DaDataCompany> listOfCompanies = new ArrayList<>();

        for (String inn : inns) {
            var url = baseUrl + "?query=" + inn + "&count=1";
            var responseBody = httpClient.doPost(
                    url,
                    DaDataResponse.class,
                    httpHeaders -> httpHeaders.add("Authorization", token)
            );

            DaDataCompany daDataCompany = responseBody.getDaDataCompanies().get(0);
            listOfCompanies.add(daDataCompany);
        }
        return listOfCompanies;
    }
}
