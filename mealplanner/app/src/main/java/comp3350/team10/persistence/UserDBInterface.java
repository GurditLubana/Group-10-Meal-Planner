package comp3350.team10.persistence;

import comp3350.team10.objects.User;

public interface UserDBInterface {
    public void addUser(String name, int height, int weight);
    public User getUser();

    public void setHeight(int newHeight);
    public void setWeight(int newWeight);
    public void setCalorieGoal(int goal);
    public void setExerciseGoal(int goal);
}