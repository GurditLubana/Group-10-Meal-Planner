package comp3350.team10.application;

public class Main {
    public static final String dbName="foodlog";

    public static void main(String[] args)
    {
        startUp();

        //CLI.run();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        //Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        //Services.closeDataAccess();
    }
}
