package comp3350.team10.objects;

public class User { 
    private final int userID;         //the users id
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
        this.calorieGoal = calorieGoal;
        this.exerciseGoal = exerciseGoal;
    }


    public void setName(String newName) throws IllegalArgumentException{

            if (newName != null) {

                this.name = newName;
            }
            else {
                throw new IllegalArgumentException("User Name can't be null");
            }


    }

    public void setHeight(int newHeight) throws IllegalArgumentException{

        if (newHeight <= Constant.MAX_HEIGHT_VALUE && newHeight >= Constant.MIN_HEIGHT_VALUE) {

            this.height= newHeight;
        }
        else {
            throw new IllegalArgumentException("New Height requires values " + Constant.MIN_HEIGHT_VALUE + "<= value <=" + Constant.MAX_HEIGHT_VALUE);
        }

    }

    public void setWeight(int newWeight) throws IllegalArgumentException{

            if (newWeight <= Constant.MAX_WEIGHT_VALUE && newWeight >= Constant.MIN_WEIGHT_VALUE) {

                this.weight= newWeight;
            }
            else {
                throw new IllegalArgumentException("New Weight requires values " + Constant.MIN_WEIGHT_VALUE + "<= value <=" + Constant.MAX_WEIGHT_VALUE);
            }
    }

    public void setCalorieGoal(double newCalorieGoal) throws IllegalArgumentException{

        if (newCalorieGoal <= Constant.ENTRY_MAX_VALUE && newCalorieGoal >= Constant.ENTRY_MIN_VALUE) {

            this.calorieGoal = newCalorieGoal;
        }
        else {
            throw new IllegalArgumentException("New Weight requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
        }
    }


    public void setExerciseGoal(double newExerciseGoal) throws IllegalArgumentException {

        if (newExerciseGoal <= Constant.ENTRY_MAX_VALUE && newExerciseGoal >= Constant.ENTRY_MIN_VALUE) {

            this.exerciseGoal = newExerciseGoal;
        }
        else {
            throw new IllegalArgumentException("New Weight requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
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