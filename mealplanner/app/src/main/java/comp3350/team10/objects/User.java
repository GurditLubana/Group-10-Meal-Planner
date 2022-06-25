package comp3350.team10.objects;

public class User {    
    private String name;        //the users name
    private int userID;         //the users id
    private int height;         //the users height
    private int weight;         //the users weight
    private int calorieGoal;    //the users calorie goal
    private int exerciseGoal;   //the users exercise goal

    public User(String name, int userID, int height, int weight, int calorieGoal, int exerciseGoal) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.userID = userID;
        this.calorieGoal = calorieGoal;
        this.exerciseGoal = exerciseGoal;
    }


    public void setName(String newName) {
        this.name = newName;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void setCalorieGoal(int newCalorieGoal) {
        this.calorieGoal = newCalorieGoal;
    }

    public void setExerciseGoal(int newExerciseGoal) {
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

    public int getCalorieGoal() {
        return this.calorieGoal;
    }

    public int getExerciseGoal() {
        return this.exerciseGoal;
    }
}