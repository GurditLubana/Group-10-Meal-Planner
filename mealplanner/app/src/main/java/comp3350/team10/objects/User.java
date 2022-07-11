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


    public User init(int userID, String name, int height, int weight, int calorieGoal, int exerciseGoal) throws IllegalArgumentException {
        this.setUserID(userID);
        this.setName(name);
        this.setHeight(height);
        this.setWeight(weight);
        this.setCalorieGoal(calorieGoal);
        this.setExerciseGoal(exerciseGoal);

        return this;
    }

    private void setUserID(int newUserID) throws IllegalArgumentException {
        if (newUserID >= 0) {
            this.userID = newUserID;
        } else {
            throw new IllegalArgumentException("User ID must be > 0");
        }
    }

    public void setName(String newName) throws IllegalArgumentException {

        if (newName != null && newName != "" && newName.length() <= Constant.ENTRY_MAX_VALUE) {
            this.name = newName;
        } else {
            throw new IllegalArgumentException("User's Name can't be null or empty");
        }

    }

    public void setHeight(int newHeight) throws IllegalArgumentException {

        if (newHeight > Constant.ENTRY_MIN_VALUE && newHeight <= Constant.ENTRY_MAX_VALUE) {
            this.height = newHeight;
        } else {
            throw new IllegalArgumentException("New Height requires values " + Constant.ENTRY_MIN_VALUE + "<= value <= " + Constant.ENTRY_MAX_VALUE);
        }

    }

    public void setWeight(int newWeight) throws IllegalArgumentException {

        if (newWeight > Constant.ENTRY_MIN_VALUE && newWeight <= Constant.ENTRY_MAX_VALUE) {
            this.weight = newWeight;
        } else {
            throw new IllegalArgumentException("New Weight requires values " + Constant.ENTRY_MIN_VALUE + "<= value <= " + Constant.ENTRY_MAX_VALUE);
        }
    }

    public void setCalorieGoal(double newCalorieGoal) throws IllegalArgumentException {

        if (newCalorieGoal >= Constant.ENTRY_MIN_VALUE && newCalorieGoal <= Constant.ENTRY_MAX_VALUE) {
            this.calorieGoal = newCalorieGoal;
        } else {
            throw new IllegalArgumentException("New Weight requires values " + Constant.ENTRY_MIN_VALUE + "<= value <= " + Constant.ENTRY_MAX_VALUE);
        }
    }


    public void setExerciseGoal(double newExerciseGoal) throws IllegalArgumentException {

        if (newExerciseGoal >= Constant.ENTRY_MIN_VALUE && newExerciseGoal <= Constant.ENTRY_MAX_VALUE) {

            this.exerciseGoal = newExerciseGoal;
        } else {
            throw new IllegalArgumentException("New Weight requires values " + Constant.ENTRY_MIN_VALUE + "<= value <= " + Constant.ENTRY_MAX_VALUE);
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