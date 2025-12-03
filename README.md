<img src="https://docencia.xaviersastre.cat/imatges/logosxs/logo_xaviersastre_v3_1.webp" alt="drawing" width="50%"/>


# Exercici: Pizza Maker Interactiu

Crea una aplicació web on els usuaris puguin dissenyar la seva pròpia pizza personalitzada de forma visual i interactiva.

## Requeriments específics

**Ingredients disponibles:**

```
Bases: Clàssica, Integral, Sense Glutten
Salses: Tomate, Pesto, Barbacoa
Formatges: Mozzarella, Cheddar, Gorgonzola
Proteïnes: Pepperoni, Pollastre, Cuixot, Veganes
Vegetals: Pebres, Ceba, Xampinyons, Olives
Extras: Moraduix, All en pols, Pinyons
```

**Funcionalitat:**

- Selector visual d'ingredients (checklist o botons)
- Vista prèvia de la pizza (mostra preus acumulats i llista d'ingredients)
- Càlcul automàtic del preu total segons combinacions
- Botó "Cuinar Pizza" que genera un " rebut" amb:
    - Llista completa d'ingredients
    - Preu final amb descompte si > 12€
    - Temps estimat de preparació
    - "ID de comanda" únic
- Històric de les últimes 5 pizzes creades (en memòria)


## Detalls tècnics creatius

```
Controllers:
- GET / → PizzaMakerController (formulari inicial)
- POST /create → processa ingredients i crea pizza
- GET /history → mostra historial recent

Models:
- Ingredient (nom, preu, categoria)
- Pizza (id, ingredients[], preuTotal, tempsPrep)

Vistes Thymeleaf:
- index.html: Selector ingredients + preview
- receipt.html: Rebut detallat de la pizza
- history.html: Mini-historial de pizzes

Lògica especial:
- Descompte 10% si >12€
- Temps prep: base(5min) + 1min/extra
- ID comanda: format "PIZZA-YYYYMMDD-HHMMSS"
```

**Bonus creatiu:** Afegeix emojis als ingredients al rebut (🍕🍄🧀🌶️) per fer-ho més visual!


