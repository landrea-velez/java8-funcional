package function;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

//@Data
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer idClient;

    //@ToString.Include
    private String name;
    private String job;
    private LocalDate birthDate;
    private double salary;
    private String country;

}
