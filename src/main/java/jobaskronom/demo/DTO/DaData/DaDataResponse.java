package jobaskronom.demo.DTO.DaData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
@Getter
public class DaDataResponse {

    @JsonProperty("suggestions")
    private List<DaDataCompany> daDataCompanies;


}
