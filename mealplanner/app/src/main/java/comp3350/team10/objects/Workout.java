package comp3350.team10.objects;

public class Workout { //put into array based on what day they go on
    private boolean complete;
    private Exercise[] exercises;

    public Workout(Exercise[] exercises) {
        this.exercises = exercises;
        this.complete = false;
    }

    public modifyComplete(boolean newVal) {
        this.complete = newVal;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public int getCaloriesBurnt() {
        int totalCalories = 0;

        for(int i = 0; i < this.exercises.length; i++) {
            totalCalories += this.exercises[i].getCalories();
        }

        return totalCalories;
    }
}
