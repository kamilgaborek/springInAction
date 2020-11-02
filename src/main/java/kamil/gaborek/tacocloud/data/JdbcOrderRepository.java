package kamil.gaborek.tacocloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import kamil.gaborek.tacocloud.Order;
import kamil.gaborek.tacocloud.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderTacoInsert;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Insert_Taco");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {

        order.setPlacedAt(new Date());
        long orderId = savedOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for(Taco taco: tacos){
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco",taco.getId());

        orderTacoInsert.execute(values);
    }

    private long savedOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());

        return orderInsert
                .executeAndReturnKey(values)
                .longValue();
    }
}
