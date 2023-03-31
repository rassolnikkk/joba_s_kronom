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
public class DaDataCompanyData {

    private String kpp;

    private String ogrn;

    private String inn;
    @JsonProperty("management")
    private DaDataCompanyManagement daDataCompanyManagement;
    @JsonProperty("address")
    private DaDataCompanyAddress daDataCompanyAddress;

}
