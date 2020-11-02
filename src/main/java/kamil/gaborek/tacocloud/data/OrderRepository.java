package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
