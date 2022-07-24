package comp3350.team10.persistence;

public interface DataAccess extends LogDBInterface, RecipeDBInterface, UserDBInterface {

    public void save();

    public void close();

    public void removeTestData();
}
