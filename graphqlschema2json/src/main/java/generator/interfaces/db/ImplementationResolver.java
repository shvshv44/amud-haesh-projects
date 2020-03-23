package generator.interfaces.db;

import generator.api.GraphqlImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplementationResolver extends JpaRepository<GraphqlImplementation,String> {

    @Override
    GraphqlImplementation save(GraphqlImplementation graphqlImplementation);

    @Override
    List<GraphqlImplementation> findAll();
}
