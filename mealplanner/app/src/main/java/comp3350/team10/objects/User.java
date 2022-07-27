package comp3350.team10.objects;

public class User {
    private int userID;               //the users id
    private String name;              //the users name
    private int height;               //the users height
    private int weight;               //the users weight
    private double calorieGoal;       //the users calorie goal
    private double exerciseGoal;      //the users exercise goal


    public User() {
        this.userID = -1;
        this.name = null;
        this.height = -1;
        this.weight = -1;
        this.calorieGoal = -1;
        this.exerciseGoal = -1;
    }


    public User init(int userID, String name, int height, int weight, double calorieGoal, double exerciseGoal) throws IllegalArgumentException {
        this.setUserID(userID);
        this.setName(name);
        this.setHeight(height);
        this.setWeight(weight);
        this.setCalorieGoal(calorieGoal);
        this.setExerciseGoal(exerciseGoal);

        return this;
    }

    public User clone() {
        User copy = new User();
        copy.init(this.userID, this.name, this.height, this.weight, this.calorieGoal, this.exerciseGoal);
        return copy;
    }

    private void setUserID(int newUserID) throws IllegalArgumentException {
        Validator.atLeastZero(newUserID, "User - newUserID");
        this.userID = newUserID;
    }

    public void setName(String newName) throws IllegalArgumentException {
        Validator.validStringInputatLeastOne(newName, "User - setName");
        this.name = newName;
    }

    public void setHeight(int newHeight) throws IllegalArgumentException {
        Validator.atLeastOne(newHeight, "User - setHeight");
        this.height = newHeight;
    }

    public void setWeight(int newWeight) throws IllegalArgumentException {
        Validator.atLeastOne(newWeight, "User - setWeight");
        this.weight = newWeight;
    }

    public void setCalorieGoal(double newCalorieGoal) throws IllegalArgumentException {
        Validator.atLeastZero(newCalorieGoal, "User - setCalorieGoal");
        this.calorieGoal = newCalorieGoal;
    }


    public void setExerciseGoal(double newExerciseGoal) throws IllegalArgumentException {
        Validator.atLeastZero(newExerciseGoal, "User - setExerciseGoal");
        this.exerciseGoal = newExerciseGoal;
    }

    public String getName() {
        return this.name;
    }

    public int getUserID() {
        return this.userID;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public double getCalorieGoal() {
        return this.calorieGoal;
    }

    public double getExerciseGoal() {
        return this.exerciseGoal;
    }
}