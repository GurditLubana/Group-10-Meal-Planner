package comp3350.team10.business;

import comp3350.team10.objects.User;
import comp3350.team10.persistence.SharedDB;
import comp3350.team10.persistence.UserDBInterface;

public class UserDataOps {
    private UserDBInterface db;     //References the current database
    private User currUser;          //The current user's details

    public UserDataOps() {
        this.db = SharedDB.getUserDB();
        this.currUser = db.getUser();
    }


    public User getUser() {
        return this.currUser;
    }

    public void updateHeight(int newHeight) {
        currUser.setHeight(newHeight);
        this.db.setHeight(this.currUser.getUserID(), newHeight);
    }

    public void updateWeight(int newWeight) {
        currUser.setWeight(newWeight);
        this.db.setWeight(this.currUser.getUserID(), newWeight);
    }

    public void updateCalorieGoal(double newCalorieGoal) {
        currUser.setCalorieGoal(newCalorieGoal);
        this.db.setCalorieGoal(this.currUser.getUserID(), newCalorieGoal);
    }

    public void updateExerciseGoal(double newExerciseGoal) {
        currUser.setExerciseGoal(newExerciseGoal);
        this.db.setExerciseGoal(this.currUser.getUserID(), newExerciseGoal);
    }
}