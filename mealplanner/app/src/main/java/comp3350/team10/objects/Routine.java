package comp3350.team10.objects;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Routine {
    private String goalType;
    private Workout dailyWorkouts[];

    public Routine(String goalType, Workout[] dailyWorkouts) {
        this.goalType = goalType;
        this.dailyWorkouts = dailyWorkouts;
    }

    public String getGoalType() {
        return this.goalType;
    }

    public Workout getTodaysWorkout() {
        int currDay = LocalDate.now().getDayOfWeek().getValue();

        return dailyWorkouts[currDay];
    }
}
