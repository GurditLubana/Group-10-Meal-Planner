package comp3350.team10.objects;

public class Exercise {
    private String name;
    private String description;
    private int reps;
    private int sets;
    private int calories;

    
    public Exercise(String name, String description, int reps, int sets, int calories) {
        this.name = name;
        this.description = description;
        this.reps = reps;
        this.sets = sets;
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getReps() {
        return this.reps;
    }

    public int getSets() {
        return this.sets;
    }

    public int getCalories() {
        return this.calories;
    }
}