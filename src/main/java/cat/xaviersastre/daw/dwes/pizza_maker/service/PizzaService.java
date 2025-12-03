package cat.xaviersastre.daw.dwes.pizza_maker.service;

import cat.xaviersastre.daw.dwes.pizza_maker.model.Ingredient;
import cat.xaviersastre.daw.dwes.pizza_maker.model.Pizza;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private final List<Ingredient> catalegIngredients;
    // Històric en memòria (LinkedList per facilitar eliminar el primer)
    private final LinkedList<Pizza> historial = new LinkedList<>();

    public PizzaService() {
        // Inicialitzem les dades
        catalegIngredients = new ArrayList<>();
        // Bases
        catalegIngredients.add(new Ingredient("Clàssica", 5.0, "Bases", "🥖"));
        catalegIngredients.add(new Ingredient("Integral", 6.0, "Bases", "🌾"));
        catalegIngredients.add(new Ingredient("Sense Gluten", 7.0, "Bases", "🚫"));
        // Salses
        catalegIngredients.add(new Ingredient("Tomate", 1.0, "Salses", "🍅"));
        catalegIngredients.add(new Ingredient("Pesto", 1.5, "Salses", "🌿"));
        catalegIngredients.add(new Ingredient("Barbacoa", 1.5, "Salses", "🔥"));
        // Formatges
        catalegIngredients.add(new Ingredient("Mozzarella", 1.5, "Formatges", "🧀"));
        catalegIngredients.add(new Ingredient("Cheddar", 1.5, "Formatges", "🟧"));
        catalegIngredients.add(new Ingredient("Gorgonzola", 2.0, "Formatges", "🦠"));
        // Proteïnes
        catalegIngredients.add(new Ingredient("Pepperoni", 2.0, "Proteïnes", "🍕"));
        catalegIngredients.add(new Ingredient("Pollo", 2.0, "Proteïnes", "🍗"));
        catalegIngredients.add(new Ingredient("Veganas", 2.5, "Proteïnes", "🌱"));
        // Vegetals
        catalegIngredients.add(new Ingredient("Pimientos", 1.0, "Vegetals", "🫑"));
        catalegIngredients.add(new Ingredient("Champiñones", 1.0, "Vegetals", "🍄"));
        // Extras
        catalegIngredients.add(new Ingredient("Orégano", 0.5, "Extras", "🌿"));
        catalegIngredients.add(new Ingredient("Piñones", 1.0, "Extras", "🌲"));
    }

    public List<Ingredient> getCataleg() {
        return catalegIngredients;
    }

    public Pizza processarPizza(List<String> nomsIngredients) {
        Pizza pizza = new Pizza();

        // 1. Generar ID: PIZZA-YYYYMMDD-HHMMSS
        String id = "PIZZA-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        pizza.setId(id);

        // 2. Recuperar objectes Ingredient a partir dels noms rebuts del formulari
        List<Ingredient> seleccionats = catalegIngredients.stream()
                .filter(ing -> nomsIngredients.contains(ing.getNom()))
                .collect(Collectors.toList());
        pizza.setIngredients(seleccionats);

        // 3. Calcular Preu
        double total = seleccionats.stream().mapToDouble(Ingredient::getPreu).sum();

        // 4. Aplicar Descompte
        if (total > 12.0) {
            total = total * 0.90; // 10% descompte
            pizza.setDescompteAplicat(true);
        } else {
            pizza.setDescompteAplicat(false);
        }
        // Arrodonim a 2 decimals
        pizza.setPreuTotal(Math.round(total * 100.0) / 100.0);

        // 5. Calcular Temps: Base 5 min + 1 min per ingredient extra
        // Assumim que la base (massa) compta com ingredient, així que la lògica simple és size + 5?
        // O base fixa de 5 + 1 minut per CADA ingredient afegit? Farem 5 fix + 1 per ingredient.
        int temps = 5 + seleccionats.size();
        pizza.setTempsPrep(temps);

        // 6. Guardar a l'historial (Màxim 5)
        historial.addFirst(pizza);
        if (historial.size() > 5) {
            historial.removeLast();
        }

        return pizza;
    }

    public List<Pizza> getHistorial() {
        return historial;
    }
}