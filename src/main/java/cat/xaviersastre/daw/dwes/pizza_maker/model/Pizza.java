package cat.xaviersastre.daw.dwes.pizza_maker.model;

import java.util.List;

public class Pizza {
    private String id;
    private List<Ingredient> ingredients;
    private double preuTotal;
    private int tempsPrep;
    private boolean descompteAplicat;

    // Constructor buit i complet
    public Pizza() {}

    // Getters i Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
    public double getPreuTotal() { return preuTotal; }
    public void setPreuTotal(double preuTotal) { this.preuTotal = preuTotal; }
    public int getTempsPrep() { return tempsPrep; }
    public void setTempsPrep(int tempsPrep) { this.tempsPrep = tempsPrep; }
    public boolean isDescompteAplicat() { return descompteAplicat; }
    public void setDescompteAplicat(boolean descompteAplicat) { this.descompteAplicat = descompteAplicat; }
}