package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
