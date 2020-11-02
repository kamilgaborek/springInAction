package kamil.gaborek.tacocloud.web;

import kamil.gaborek.tacocloud.Ingredient;
import kamil.gaborek.tacocloud.Ingredient.Type;
import kamil.gaborek.tacocloud.Order;
import kamil.gaborek.tacocloud.Taco;
import kamil.gaborek.tacocloud.data.IngredientRepository;
import kamil.gaborek.tacocloud.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository jdbcIngredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = jdbcIngredientRepository;
        this.tacoRepository = tacoRepository;
    }


    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid  Taco design,
            Errors errors,
            @ModelAttribute Order order){

        if(errors.hasErrors()) return "design";
        log.info("Processing design" + design);

        Taco savedTaco = tacoRepository.save(design);
        order.addDesign(savedTaco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter( ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
