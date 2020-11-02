package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
