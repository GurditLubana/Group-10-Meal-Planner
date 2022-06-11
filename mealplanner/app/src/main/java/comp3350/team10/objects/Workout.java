package comp3350.team10.objects;

public class Workout {
    private boolean complete;       //Represents whether the workout was completed by the user
    private Exercise[] exercises;   //Represents the exercises for a given workout

    public Workout(Exercise[] exercises) {
        this.exercises = exercises;
        this.complete = false;
    }

    public void modifyComplete(boolean newVal) {
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

    public void modCompletionStatus(boolean newStatus) {
        this.complete = newStatus;
    }

    public boolean getCompletionStatus() {
        return this.complete;
    }
}