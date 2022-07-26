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
        try {
            Validator.atLeastZero(newUserID, "User newUserID");
            this.userID = newUserID;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void setName(String newName) throws IllegalArgumentException {
        try {
            Validator.validStringInputatLeastOne(newName, "User setName");
            this.name = newName;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void setHeight(int newHeight) throws IllegalArgumentException {
        try {
            Validator.atLeastOne(newHeight, "User setHeight");
            this.height = newHeight;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void setWeight(int newWeight) throws IllegalArgumentException {
        try {
            Validator.atLeastOne(newWeight, "User setWeight");
            this.weight = newWeight;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void setCalorieGoal(double newCalorieGoal) throws IllegalArgumentException {
        try {
            Validator.atLeastZero(newCalorieGoal, "User setCalorieGoal");
            this.calorieGoal = newCalorieGoal;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public void setExerciseGoal(double newExerciseGoal) throws IllegalArgumentException {
        try {
            Validator.atLeastZero(newExerciseGoal, "User setExerciseGoal");
            this.exerciseGoal = newExerciseGoal;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
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