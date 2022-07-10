package comp3350.team10.persistence;

import java.util.Calendar;

import comp3350.team10.objects.User;

public interface UserDBInterface {
    public void addUser(String name, int height, int weight);
    public User getUser();

    public void setHeight(int userID, int newHeight);
    public void setWeight(int userID, int newWeight);
    public void setCalorieGoal(int userID, double goal);
    public void setExerciseGoal(int userID, double goal);
}