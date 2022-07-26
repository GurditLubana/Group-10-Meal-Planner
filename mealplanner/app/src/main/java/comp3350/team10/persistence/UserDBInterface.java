package comp3350.team10.persistence;

import comp3350.team10.objects.User;

public interface UserDBInterface {
    public User getUser(int userID) throws IllegalArgumentException;

    public void updateUser(User user) throws IllegalArgumentException;
}