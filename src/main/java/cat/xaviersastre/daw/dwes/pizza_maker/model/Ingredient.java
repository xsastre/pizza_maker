package cat.xaviersastre.daw.dwes.pizza_maker.model;

public class Ingredient {
    private String nom;
    private double preu;
    private String categoria; // Base, Salsa, Formatge...
    private String emoji;

    public Ingredient(String nom, double preu, String categoria, String emoji) {
        this.nom = nom;
        this.preu = preu;
        this.categoria = categoria;
        this.emoji = emoji;
    }

    // Getters i Setters
    public String getNom() { return nom; }
    public double getPreu() { return preu; }
    public String getCategoria() { return categoria; }
    public String getEmoji() { return emoji; }
}