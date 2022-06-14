package comp3350.team10.objects;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Routine {
    private String goalType;            //Represents the goal a user wants to achieve
    private Workout dailyWorkouts[];    //Represents the workout routine for the given user throughout a week

    public Routine(String goalType, Workout[] dailyWorkouts) {
        this.goalType = goalType;
        this.dailyWorkouts = dailyWorkouts;
    }

    public String getGoalType() {
        return this.goalType;
    }

    public Workout getTodaysWorkout() {
        int currDay = LocalDate.now().getDayOfWeek().getValue();    //gets the current day

        return dailyWorkouts[currDay];  //gets the workout based on the position of the array
    }
}