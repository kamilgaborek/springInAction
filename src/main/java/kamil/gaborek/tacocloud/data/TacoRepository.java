package kamil.gaborek.tacocloud.data;

import kamil.gaborek.tacocloud.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
