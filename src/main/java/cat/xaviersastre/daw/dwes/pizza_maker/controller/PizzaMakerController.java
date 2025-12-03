package cat.xaviersastre.daw.dwes.pizza_maker.controller;

import cat.xaviersastre.daw.dwes.pizza_maker.model.Ingredient;
import cat.xaviersastre.daw.dwes.pizza_maker.model.Pizza;
import cat.xaviersastre.daw.dwes.pizza_maker.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PizzaMakerController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/")
    public String formulariInicial(Model model) {
        // Agrupen els ingredients per categoria per mostrar-los macos a la vista
        Map<String, List<Ingredient>> ingredientsPerCategoria =
                pizzaService.getCataleg().stream()
                        .collect(Collectors.groupingBy(Ingredient::getCategoria));

        model.addAttribute("categories", ingredientsPerCategoria);
        return "index";
    }

    @PostMapping("/create")
    public String crearPizza(@RequestParam(name = "ingredientsSeleccionats", required = false) List<String> ingredients, Model model) {
        if (ingredients == null || ingredients.isEmpty()) {
            return "redirect:/?error=buit";
        }

        Pizza novaPizza = pizzaService.processarPizza(ingredients);
        model.addAttribute("pizza", novaPizza);
        return "receipt";
    }

    @GetMapping("/history")
    public String mostrarHistorial(Model model) {
        model.addAttribute("historial", pizzaService.getHistorial());
        return "history";
    }
}