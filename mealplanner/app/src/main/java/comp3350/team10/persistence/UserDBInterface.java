package comp3350.team10.persistence;


import comp3350.team10.objects.User;

public interface UserDBInterface {
    public User getUser();

    public void setHeight(int userID, int newHeight) throws IllegalArgumentException;

    public void setWeight(int userID, int newWeight) throws IllegalArgumentException;

    public void setCalorieGoal(int userID, double goal) throws IllegalArgumentException;

    public void setExerciseGoal(int userID, double goal) throws IllegalArgumentException;
}