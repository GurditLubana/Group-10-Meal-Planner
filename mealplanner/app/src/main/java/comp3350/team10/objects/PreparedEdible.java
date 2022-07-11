package comp3350.team10.objects;

import java.util.ArrayList;

public abstract class PreparedEdible extends Edible {
    private String instructions;     //The instructions required for the Edible to prepare

    public PreparedEdible() {
        super();

        this.instructions = "";
    }


    public void setInstructions(String newInstructions) throws IllegalArgumentException {
        if (newInstructions != null && newInstructions.length() <= Constant.ENTRY_MAX_VALUE) {
            this.instructions = newInstructions;
        } else {
            throw new IllegalArgumentException("Invalid instructions");
        }
    }

    public String getInstructions() {
        return this.instructions;
    }


    public void updateEdibleFromIngredients(ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        this.calcCalories(ingredients);
        this.calcProtein(ingredients);
        this.calcCarbs(ingredients);
        this.calcFat(ingredients);
        this.checkIfAlcoholic(ingredients);
        this.checkIfSpicy(ingredients);
        this.checkIfVegan(ingredients);
        this.checkIfVegetarian(ingredients);
        this.checkIfGlutenFree(ingredients);
    }

    private void calcCalories(ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        double calculatedCalories = 0;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                calculatedCalories += ingredients.get(i).getIngredient().getCalories();
            }

            if (calculatedCalories > Constant.ENTRY_MAX_VALUE) {
                calculatedCalories = Constant.ENTRY_MAX_VALUE;
            }

            this.setCalories(calculatedCalories);
        }
    }

    private void calcProtein(ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        int calculatedProtein = 0;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                calculatedProtein += ingredients.get(i).getIngredient().getProtein();
            }

            if (calculatedProtein > Constant.ENTRY_MAX_VALUE) {
                calculatedProtein = Constant.ENTRY_MAX_VALUE;
            }

            this.setProtein(calculatedProtein);
        }
    }

    private void calcCarbs(ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        int calculatedCarbs = 0;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                calculatedCarbs += ingredients.get(i).getIngredient().getCarbs();
            }

            if (calculatedCarbs > Constant.ENTRY_MAX_VALUE) {
                calculatedCarbs = Constant.ENTRY_MAX_VALUE;
            }

            this.setCarbs(calculatedCarbs);
        }
    }

    private void calcFat(ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        int calculatedFat = 0;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                calculatedFat += ingredients.get(i).getIngredient().getFat();
            }

            if (calculatedFat > Constant.ENTRY_MAX_VALUE) {
                calculatedFat = Constant.ENTRY_MAX_VALUE;
            }

            this.setFat(calculatedFat);
        }
    }

    private void checkIfAlcoholic(ArrayList<Ingredient> ingredients) {
        boolean isAlcoholic = false;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size() && !isAlcoholic; i++) {
                if (ingredients.get(i).getIngredient().getIsAlcoholic()) {
                    isAlcoholic = true;
                }
            }

            this.setAlcoholic(isAlcoholic);
        }
    }

    private void checkIfSpicy(ArrayList<Ingredient> ingredients) {
        boolean isSpicy = false;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size() && !isSpicy; i++) {
                if (ingredients.get(i).getIngredient().getIsSpicy()) {
                    isSpicy = true;
                }
            }

            this.setSpicy(isSpicy);
        }
    }

    private void checkIfVegan(ArrayList<Ingredient> ingredients) {
        boolean isVegan = true;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size() && isVegan; i++) {
                if (!ingredients.get(i).getIngredient().getIsVegan()) {
                    isVegan = false;
                }
            }

            this.setVegan(isVegan);
        }
    }

    private void checkIfVegetarian(ArrayList<Ingredient> ingredients) {
        boolean isVegetarian = true;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size() && isVegetarian; i++) {
                if (!ingredients.get(i).getIngredient().getIsVegetarian()) {
                    isVegetarian = false;
                }
            }

            this.setVegetarian(isVegetarian);
        }
    }

    private void checkIfGlutenFree(ArrayList<Ingredient> ingredients) {
        boolean isGlutenFree = true;

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size() && isGlutenFree; i++) {
                if (!ingredients.get(i).getIngredient().getIsGlutenFree()) {
                    isGlutenFree = false;
                }
            }

            this.setGlutenFree(isGlutenFree);
        }
    }
}