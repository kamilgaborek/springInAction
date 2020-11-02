package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
