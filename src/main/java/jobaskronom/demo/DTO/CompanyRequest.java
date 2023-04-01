package jobaskronom.demo.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {

    private String companyNameDaData;

    private String kpp;

    private String managerName;

    private String post;

    private String ogrn;

    @NotEmpty
    private String companyName;

    private String address;

    @NotEmpty
    private String inn;
}
