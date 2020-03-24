package generator.api;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "implementation")
public class GraphqlImplementation {
    @Id private String implementationName;
    private String interfaceName;
}
