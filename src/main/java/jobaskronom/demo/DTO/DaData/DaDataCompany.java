package jobaskronom.demo.DTO.DaData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
public class DaDataCompany {
    @JsonProperty("value")
    private String name;

    @JsonProperty("data")
    private DaDataCompanyData daDataCompanyData;

    public String getInn(){
        return daDataCompanyData.getInn();
    }

    public String getKpp(){
        return daDataCompanyData.getKpp();
    }

    public String getOgrn(){
        return daDataCompanyData.getOgrn();
    }

    public String getFio(){
        return daDataCompanyData.getDaDataCompanyManagement().getManagerName();
    }

    public String getPost(){
        return daDataCompanyData.getDaDataCompanyManagement().getPost();
    }

    public String getAddress(){
        return daDataCompanyData.getDaDataCompanyAddress().getAddress();
    }
}
