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

    private String name;

    private String kpp;

    private String fio;

    private String post;

    private String ogrn;

    private String companyName;

    private String address;

    @Column(unique = true)
    private String inn;

}
