package generator.interfaces.db;

import generator.api.UserDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserDefaultResolver extends JpaRepository<UserDefaults, UserDefaults> {
}
