package comp3350.team10.persistence;

import comp3350.team10.application.Main;
import comp3350.team10.objects.*;
import comp3350.team10.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<ListItem> currentFoodLog;
    private ArrayList recipeTest;
    //private ArrayList<Course> courses;
    //private ArrayList<SC> scs;
    private ArrayList<Routine> routines;
    private ArrayList<Drink> drink;
    private ArrayList<Food> food;
    private ArrayList<Meal> meal;

    private String dbType = "stub";
    private String dbName;


    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String dbName) {
        DiaryItem mealEntry;
        RecipeBookItem recipeEntry;
        //Course course;
        //SC mySC;

        System.out.println("before calandar");
        Calendar calendar = Calendar.getInstance();
        System.out.println("after calandar");
        System.out.println("TIME: " + calendar.getTime());

        //Meal entries
        currentFoodLog = new ArrayList<ListItem>();
        currentFoodLog.add(new DiaryItem(new Food("Banana", "myIcon", 100), calendar.getTime(), 100));
        currentFoodLog.add(new DiaryItem(new Food("Salad", "myIcon", 50), calendar.getTime(), 101));
        currentFoodLog.add(new DiaryItem(new Food("Hamburglar", "myIcon", 150), calendar.getTime(), 102));
        currentFoodLog.add(new DiaryItem(new Food("Notfries", "myIcon", 100), calendar.getTime(), 103));
        currentFoodLog.add(new DiaryItem(new Food("Banana", "myIcon", 1000), calendar.getTime(), 104));
        currentFoodLog.add(new DiaryItem(new Food("Salad", "myIcon", 50), calendar.getTime(), 105));
        currentFoodLog.add(new DiaryItem(new Food("Salad", "myIcon", 100), calendar.getTime(), 106));
        currentFoodLog.add(new DiaryItem(new Food("Hamburglar", "myIcon", 10), calendar.getTime(), 107));
        currentFoodLog.add(new DiaryItem(new Food("Notfries", "myIcon", 100), calendar.getTime(), 108));
        currentFoodLog.add(new DiaryItem(new Food("Salad", "myIcon", 100), calendar.getTime(), 109));
        currentFoodLog.add(new DiaryItem(ListItem.FragmentType.diaryAdd, new Food("Salad", "myIcon", 100), calendar.getTime(), 110));
        System.out.println("added: " + currentFoodLog.size());
        //mealEntry = new DiaryItem(103, ListItem.FragmentType.diaryModify, "Notfries", 500, ListItem.Unit.g, 30, "myIcon");
        //currentFoodLog.add(mealEntry);    //this is something Josef was doing before dont wana mess with it

        recipeTest = new ArrayList<RecipeBookItem>();
        recipeEntry = new RecipeBookItem(100, ListItem.FragmentType.recipe, "Banana", 100, ListItem.Unit.g, 50, R.drawable.food);
        recipeTest.add(recipeEntry);
        recipeEntry = new RecipeBookItem(101, ListItem.FragmentType.recipe, "Salad", 50, ListItem.Unit.g, 50, R.drawable.food2);
        recipeTest.add(recipeEntry);
        recipeEntry = new RecipeBookItem(102, ListItem.FragmentType.recipe, "Hamburglar", 700, ListItem.Unit.g, 400, R.drawable.food3);
        recipeTest.add(recipeEntry);
        recipeEntry = new RecipeBookItem(103, ListItem.FragmentType.recipe, "Notfries", 500, ListItem.Unit.g, 30, R.drawable.food4);
        recipeTest.add(recipeEntry);
        recipeEntry = new RecipeBookItem(104, ListItem.FragmentType.recipe, "Banana", 100, ListItem.Unit.g, 50, R.drawable.drinks);
        recipeTest.add(recipeEntry);


//        courses = new ArrayList<Course>();
//        course = new Course("COMP3010", "Distributed Computing");
//        courses.add(course);
//        course = new Course("COMP3020", "Human-Computer Interaction");
//        courses.add(course);
//        course = new Course("COMP3350", "Software Development");
//        courses.add(course);
//        course = new Course("COMP3380", "Databases");
//        courses.add(course);
//
//        scs = new ArrayList<SC>();
//        mySC = new SC("100", "COMP3010", "Gary Chalmers", "Distributed Computing", "C+");
//        scs.add(mySC);
//        mySC = new SC("200", "COMP3010", "Selma Bouvier", "Distributed Computing", "A+");
//        scs.add(mySC);
//        mySC = new SC("100", "COMP3350", "Gary Chalmers", "Software Development", "A");
//        scs.add(mySC);
//        mySC = new SC("300", "COMP3350", "Arnie Pye", "Software Development", "B");
//        scs.add(mySC);
//        mySC = new SC("100", "COMP3380", "Gary Chalmers", "Databases", "A");
//        scs.add(mySC);
//        mySC = new SC("200", "COMP3380", "Selma Bouvier", "Databases", "B");
//        scs.add(mySC);

        //Workouts
        routines = new ArrayList<Routine>();
        Workout chestWorkout = new Workout(new Exercise[] {
            new Exercise("Incline dumbell press", "Put the bench at 45 degrees", 9, 3, 5),
            new Exercise("Lat pull down", "Lead with elbows and go slow", 9, 3, 4),
            new Exercise("Benchpress", "ego lifting is bad", 9, 3, 8),
            new Exercise("Bent over rows", "keep that back flat", 8, 3, 4),
            new Exercise("Chest fly", "Move arms accross chest, dont go too low", 9, 3, 3)
        });

        Workout armWorkout = new Workout(new Exercise[] {
            new Exercise("Dumbbell waiter curls", "keep those elbows in", 8, 4, 5),
            new Exercise("Cable tricep pulldowns", "focus on pushing with triceps", 8, 4, 4),
            new Exercise("Cheat curls", "dont go toooo crazy", 6, 4, 5),
            new Exercise("Banded tricep extensions", "use a close grip", 12, 3, 4),
            new Exercise("Dumbell curls", "keep those elbows in", 8, 4, 5)
        });

        Workout legWorkout = new Workout(new Exercise[] {
            new Exercise("Leg press", "focus on pressing into the machine/ground", 16, 3, 7),
            new Exercise("Fire hydrants", "keep your knee in", 20, 3, 7),
            new Exercise("Squat", "focus on pressing into the machine/ground", 16, 3, 8),
            new Exercise("Donkey kicks", "try to point your toes and get them up", 20, 3, 7),
            new Exercise("Calf extensions", "you still need to be able to walk to get home", 12, 3, 8)
        });

        Workout cardio = new Workout(new Exercise[] {
            new Exercise("Eliptical", "its better to sprint for a little than walk for awhile", 20, 3, 100)
        });

        Workout rest = new Workout(new Exercise[] {
            new Exercise("Rest", "take a break you deserve it... hopefully", 0, 0, 0)
        });

        routines.add(new Routine("build muscle", new Workout[] {chestWorkout, armWorkout, legWorkout, rest, chestWorkout, legWorkout, rest}));
        routines.add(new Routine("lose weight", new Workout[] {cardio, cardio, cardio, cardio, cardio, cardio, cardio}));
        routines.add(new Routine("tone", new Workout[] {chestWorkout, cardio, armWorkout, cardio, legWorkout, cardio, rest}));

        System.out.println("added workouts...");

        //Create Drinks
        drink = new ArrayList<Drink>();
        drink.add(new Drink("Mojito", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("White Rum", 1.5, "oz", false, true),
                new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
                new DrinkIngredient("Lime", 1.2, "lime", false, false),
                new DrinkIngredient("Club soda", 0, "fill", true, false),
            }
        ));

        System.out.println("added first drink...");

        drink.add(new Drink("Mai-tai", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Coconut Malibu", 1.5, "oz", true, false),
                new DrinkIngredient("Rum", 1.5, "oz", true, false),
                new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                new DrinkIngredient("Orange juice", 2, "oz", true, false),
                new DrinkIngredient("Grenadine", 1, "dash", false, false),
            }
        ));

        drink.add(new Drink("Red Headed \"friend\"", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Jagermeister", 1, "oz", false, true),
                new DrinkIngredient("Cranberry juice", 1, "oz", true, false),
                new DrinkIngredient("Peach Schnapps", 1, "oz", false, true),
            }
        ));

        drink.add(new Drink("Fun On The Beach", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Vodka", 1.5, "oz", false, true),
                new DrinkIngredient("Peach Schnapps", 0.5, "oz", false, true),
                new DrinkIngredient("Chamboard", 1/2, "oz", false, true),
                new DrinkIngredient("Orange juice", 1.5, "oz", true, false),
                new DrinkIngredient("Cranberry juice", 1.5, "oz", true, false),
            }
        ));

        drink.add(new Drink("Non Alcoholic Moscow Mule", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Lime juice", 1, "TBSP", false, false),
                new DrinkIngredient("Ginger beer", 4, "oz", false, false),
                new DrinkIngredient("Club soda", 0, "fill", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
            }
        ));

        drink.add(new Drink("Blue Hawaiian", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Pineapple juice", 2, "oz", true, false),
                new DrinkIngredient("Light rum", 1, "oz", false, true),
                new DrinkIngredient("Blue Curacao", 1, "oz", false, true),
                new DrinkIngredient("Coconut Malibu", 1, "oz", false, true),
            }
        ));

        drink.add(new Drink("French Martini", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                new DrinkIngredient("Chamboard", 1, "oz", false, true),
            }
        ));

        drink.add(new Drink("Non Alcoholic Mojito", "myIcon", new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
                new DrinkIngredient("Lime", 1/2, "lime", false, false),
                new DrinkIngredient("Club soda", 0, "fill", false, false),
            }
        ));

        System.out.println("added drinks...");

        food = new ArrayList<Food>();
        food.add(new Food("apple", "myIcon"));
        food.add(new Food("pear", "myIcon"));
        food.add(new Food("cracker", "myIcon"));
        food.add(new Food("grain of rice", "myIcon"));
        food.add(new Food("walnut", "myIcon"));
        food.add(new Food("molasse", "myIcon"));
        food.add(new Food("cereal", "myIcon"));
        food.add(new Food("nutella", "myIcon"));
        food.add(new Food("steak", "myIcon"));

        System.out.println("Added food");

        //adding meals
        meal = new ArrayList<Meal>();
        meal.add(new Meal("soup", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("broth", "myIcon")),
            new MealIngredient(5, "cups", new Food("onion", "myIcon")),
            new MealIngredient(5, "cups", new Food("brocoli", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("salad", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("lettuce", "myIcon")),
            new MealIngredient(5, "cups", new Food("tomato", "myIcon")),
            new MealIngredient(5, "cups", new Food("onion", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("yogurt parfait", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("yogurt", "myIcon")),
            new MealIngredient(5, "cups", new Food("oats", "myIcon")),
            new MealIngredient(5, "cups", new Food("Stawberry", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("smoothie", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("milk", "myIcon")),
            new MealIngredient(5, "cups", new Food("oats", "myIcon")),
            new MealIngredient(5, "cups", new Food("banana", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("rice pilaf", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("cucumber", "myIcon")),
            new MealIngredient(5, "cups", new Food("rice", "myIcon")),
            new MealIngredient(5, "cups", new Food("bread", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("sushi", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("rice", "myIcon")),
            new MealIngredient(5, "cups", new Food("cream cheese", "myIcon")),
            new MealIngredient(5, "cups", new Food("nori", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("wrap", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("steak", "myIcon")),
            new MealIngredient(5, "cups", new Food("pesto", "myIcon")),
            new MealIngredient(5, "cups", new Food("lettuce", "myIcon"))
        }, new String[] {"Get", "Good"}));

        meal.add(new Meal("shrimp tacos", "myIcon", 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("shrimp", "myIcon")),
            new MealIngredient(5, "cups", new Food("taco shell", "myIcon")),
            new MealIngredient(5, "cups", new Food("cheese", "myIcon")),
            new MealIngredient(5, "cups", new Food("lettuce", "myIcon"))
        }, new String[] {"Get", "Good"}));
        System.out.println("Added meals");


        //caches database recipes into memory






        System.out.println("Cached recipes");

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public ArrayList<ListItem> getToday() {
        return currentFoodLog;
    }

    public LinkedList<ListItem> getRecipe(int edibleType) {   //String currTab
        System.out.println("this is good");
        LinkedList<ListItem> currEdibles = new LinkedList<ListItem>();

        if(edibleType == 0) {   //food tab send cachedFood
            for(int i = 0; i < food.size(); i++) {//food.get(i) - RecipeBookItem needs Edible item, int image, int key
                currEdibles.add(new RecipeBookItem(food.get(i), R.drawable.food, i)); //i is not a unique key
            }
        }
        else if(edibleType == 1) {  //meal tab send cachedMeal
            for(int i = 0; i < drink.size(); i++) {
                currEdibles.add(new RecipeBookItem(drink.get(i), R.drawable.food2, i));
            }
        }
        else if(edibleType == 2) {  //drink tab send cachedDrink
            for(int i = 0; i < meal.size(); i++) {
                currEdibles.add(new RecipeBookItem(meal.get(i), R.drawable.food3, i));
            }
        }

        System.out.println("exiting getRecipe...");

        return currEdibles;
    }

    public void addRecipeToLog(DiaryItem item) { //Needs date added to this later, only adds to current
        System.out.println("Before: " + currentFoodLog.size());
        currentFoodLog.add(item);
        System.out.println("After: " + currentFoodLog.size());
    }
//
//    public ArrayList<Student> getStudentRandom(Student currentStudent)
//    {
//        ArrayList<Student> newStudents;
//        int index;
//
//        newStudents = new ArrayList<Student>();
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            newStudents.add(students.get(index));
//        }
//        return newStudents;
//    }
//
//    public String insertStudent(Student currentStudent)
//    {
//        // don't bother checking for duplicates
//        students.add(currentStudent);
//        return null;
//    }
//
//    public String updateStudent(Student currentStudent)
//    {
//        int index;
//
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            students.set(index, currentStudent);
//        }
//        return null;
//    }
//
//    public String deleteStudent(Student currentStudent)
//    {
//        int index;
//
//        index = students.indexOf(currentStudent);
//        if (index >= 0)
//        {
//            students.remove(index);
//        }
//        return null;
//    }
//
//    public String getCourseSequential(List<Course> courseResult)
//    {
//        courseResult.addAll(courses);
//        return null;
//    }
//
//    public ArrayList<Course> getCourseRandom(Course currentCourse)
//    {
//        ArrayList<Course> newCourses;
//        int index;
//
//        newCourses = new ArrayList<Course>();
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            newCourses.add(courses.get(index));
//        }
//        return newCourses;
//    }
//
//    public String insertCourse(Course currentCourse)
//    {
//        // don't bother checking for duplicates
//        courses.add(currentCourse);
//        return null;
//    }
//
//    public String updateCourse(Course currentCourse)
//    {
//        int index;
//
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            courses.set(index, currentCourse);
//        }
//        return null;
//    }
//
//    public String deleteCourse(Course currentCourse)
//    {
//        int index;
//
//        index = courses.indexOf(currentCourse);
//        if (index >= 0)
//        {
//            courses.remove(index);
//        }
//        return null;
//    }
//
//    public ArrayList<SC> getSC(SC currentSC)
//    {
//        ArrayList<SC> newSCs;
//        SC sc;
//        int counter;
//
//        // get the SC objects with the same studentID as currentSC
//        newSCs = new ArrayList<SC>();
//        for (counter=0; counter<scs.size(); counter++)
//        {
//            sc = scs.get(counter);
//            if (sc.getStudentID().equals(currentSC.getStudentID()))
//            {
//                newSCs.add(scs.get(counter));
//            }
//        }
//        return newSCs;
//    }
//
//    public ArrayList<SC> getCS(SC currentSC)
//    {
//        ArrayList<SC> newSCs;
//        SC cs;
//        int counter;
//
//        // get the SC objects with the same courseID as currentSC
//        newSCs = new ArrayList<SC>();
//        for (counter=0; counter<scs.size(); counter++)
//        {
//            cs = scs.get(counter);
//            if (cs.getCourseID().equals(currentSC.getCourseID()))
//            {
//                newSCs.add(scs.get(counter));
//            }
//        }
//        return newSCs;
//    }
}
