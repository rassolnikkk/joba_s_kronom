package jobaskronom.demo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "public", name = "data_companies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String daDataName;

    private String kpp;

    @Column(name = "fio")
    private String managerName;

    private String post;

    private String ogrn;

    private String companyName;

    private String address;

    @Column(unique = true)
    private String inn;

    public Company(String companyName, String inn) {
        this.companyName = companyName;
        this.inn = inn;
    }
}
