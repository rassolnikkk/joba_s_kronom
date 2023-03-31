package jobaskronom.demo.DTO.DaData;

import jobaskronom.demo.DTO.DaData.DaDataCompany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
@Getter
public class DaDataResponse {

    private List<DaDataCompany> suggestions;


}
