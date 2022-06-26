package comp3350.team10.business;

import comp3350.team10.objects.User;
import comp3350.team10.persistence.DataAccessStub;

public class UserDataOps {
    private DataAccessStub db;      //References the current database
    private boolean infoAvailible;  //Can be used so that if vital user is not availible a prompt will appear
    private User currUser;          //The current user's details

    public UserDataOps(DataAccessStub db) {
        this.db = db;
        this.currUser = db.getUser();
        this.infoAvailible = currUser != null;
    }


    public User getUser() {
        return this.currUser;
    }

    public void updateName(String newName) {
        currUser.setName(newName);
        //update db
    }

    public void updateHeight(int newHeight) {
        currUser.setHeight(newHeight);
        //update db
    }

    public void updateWeight(int newWeight) {
        currUser.setWeight(newWeight);
        //update db
    }

    public void updateCalorieGoal(Integer newCalorieGoal) {
        currUser.setCalorieGoal(newCalorieGoal);
        this.db.setCalorieGoal(newCalorieGoal);
    }

    public void updateExerciseGoal(int newExerciseGoal) {
        currUser.setExerciseGoal(newExerciseGoal);
        //update db
    }
}