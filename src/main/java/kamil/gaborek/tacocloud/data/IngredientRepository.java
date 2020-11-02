package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
