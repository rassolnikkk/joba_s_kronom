package jobaskronom.demo.DTO.DaData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
public class DaDataCompanyAddress {

    private String value;
}
