package comp3350.team10.objects;

public class User { 
    private int userID;         //the users id   
    private String name;        //the users name
    private int height;         //the users height
    private int weight;         //the users weight
    private double calorieGoal;    //the users calorie goal
    private double exerciseGoal;   //the users exercise goal

    public User(int userID, String name, int height, int weight, int calorieGoal, int exerciseGoal) {
        this.userID = userID;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.weight = weight;
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

    public void setCalorieGoal(double newCalorieGoal) {
        this.calorieGoal = newCalorieGoal;
    }

    public void setExerciseGoal(double newExerciseGoal) {
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