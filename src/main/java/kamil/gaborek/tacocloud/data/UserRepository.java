package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
