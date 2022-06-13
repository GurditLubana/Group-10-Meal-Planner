package comp3350.team10.objects;

public class Exercise {
    private int reps;           //The number of repetitions for a set of an exercise
    private int sets;           //The number of sets for a given exercise
    private String name;        //The name of the exercise
    private int calories;       //The number of calories this exercise burns
    private String description; //A brief description of the exercise

    
    public Exercise(String name, String description, int reps, int sets, int calories) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.calories = calories;
        this.description = description;
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