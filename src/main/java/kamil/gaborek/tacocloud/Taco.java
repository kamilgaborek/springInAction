package kamil.gaborek.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
public class Taco {

    private Long id;
    private Date createdAt;
    @NotNull
    @Size(min=5, message = "Nazwa musi skaldac sie z przynajmniej 5 znakow")
    private String name;
    @Size(min=1, message = "Musisz wybrac przynajmniej jeden skladnik")
    private List<Ingredient> ingredients;
}
