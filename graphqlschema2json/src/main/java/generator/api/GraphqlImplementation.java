package generator.api;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "implementation")
public class GraphqlImplementation implements Serializable {
    @Id private String implementationName;
    private String interfaceName;
}
