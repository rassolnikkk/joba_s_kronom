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
public class DaDataCompanyManagement {

    @JsonProperty("name")
    private String managerName;

    private String post;
}
