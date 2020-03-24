package generator.interfaces.db;

import generator.api.GraphqlImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface GraphqlImplementationResolver extends JpaRepository<GraphqlImplementation,String> {
}
