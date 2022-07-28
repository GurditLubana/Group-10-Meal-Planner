package comp3350.team10.business;

import comp3350.team10.objects.User;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.UserDBInterface;

public class UserDataOps {
    private UserDBInterface db;
    private User currUser;

    public UserDataOps() {
        this.db = DBSelector.getUserDB();
        this.currUser = db.getUser(1);
    }

    public User getUser(int id) throws IllegalArgumentException {
        try {
            this.currUser = db.getUser(id);
        } catch (IllegalArgumentException e) {
            System.out.println("UserDataOps the user does not exist " + e);
            System.exit(1);
        }
        return this.currUser;
    }

    public void updateUser() {
        try {
            this.db.updateUser(this.currUser);
        } catch (IllegalArgumentException e) {
            System.out.println("UserDataOps the user is invalid " + e);
            System.exit(1);
        }
    }

    public int getUserID() {
        return this.currUser.getUserID();
    }
}