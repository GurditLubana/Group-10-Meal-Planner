package comp3350.team10.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.application.Main;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class HSqlDB implements LogDBInterface, RecipeDBInterface, UserDBInterface {
    private final String dbPath = "jdbc:hsqldb:file:" + Main.getDBPathName() + ";shutdown=true"; // stored on disk mode
    private static final String SHUTDOWN_CMD = "shutdown compact";
    private static final String SAVE_CMD = "CHECKPOINT";
    private final String dbType = "HSQL";

    private Connection currConn;
    private Statement reqHandler;
    private String dbName = "HSQLDB";

    public HSqlDB() {
        try {
            this.open();
            this.reqHandler = currConn.createStatement();
        } catch (Exception e) {
            System.out.println("HSqlDB Constructor " + e);
        }
    }


    public void save() {
        try {
            reqHandler.executeQuery(SAVE_CMD);
        } catch (Exception e) {
            System.out.println("HSqlDB Save " + e);

        }
    }

    public void open() {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            currConn = DriverManager.getConnection(dbPath, "SA", "");
            //System.out.println("Opened " + this.dbType + " database named " + this.dbName + " @dbPath " + this.dbPath);
        } catch (Exception e) {
            System.out.println("HSqlDB Open " + e);
        }
    }

    public String getDBType() {
        return this.dbType;
    }

    public void close() {
        try {
            if (currConn != null) {
                reqHandler.executeQuery(SHUTDOWN_CMD);
                currConn.close();
            }
        } catch (Exception e) {
            System.out.println("HSqlDB Close " + e);
        }
    }


    public ArrayList<Edible> getFoodRecipes() {
        ArrayList<Edible> foodList = new ArrayList<Edible>();
        try {
            PreparedStatement getFoodList = currConn.prepareStatement("SELECT * FROM Food INNER JOIN EDIBLE ON EDIBLE.EDIBLEID = FOOD.EDIBLEID");
            PreparedStatement getCustomFoodList = currConn.prepareStatement("SELECT * FROM CustomFood INNER JOIN CUSTOMEDIBLE ON CUSTOMEDIBLE.CUSTOMEDIBLEID = CUSTOMFOOD.CUSTOMEDIBLEID");
            ResultSet results = getFoodList.executeQuery();
            boolean ediblesRemain = results.next();

            while (ediblesRemain) {
                foodList.add(readEdible(results, false));
                ediblesRemain = results.next();
            }

            results.close();

            results = getCustomFoodList.executeQuery();
            ediblesRemain = results.next();

            while (ediblesRemain) {
                foodList.add(readEdible(results, true));
                ediblesRemain = results.next();
            }

            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB GetFoodRecipes " + e);
        }

        return foodList;
    }

    public Edible findIngredientByKey(int key, boolean isCustom) {
        EdibleLog edible = this.findEdibleByKey(key, isCustom);
        Edible currEdible = null;

        if (this.isMeal(key, isCustom)) {
            currEdible = new Meal();
        } else if (this.isDrink(key, isCustom)) {
            currEdible = new Drink();
        }

        currEdible.initDetails(edible.getDbkey(), edible.getName(), edible.getDescription(), edible.getQuantity(), edible.getUnit());
        currEdible.initNutrition(edible.getCalories(), edible.getProtein(), edible.getCarbs(), edible.getFat());
        currEdible.initCategories(edible.getIsAlcoholic(), edible.getIsSpicy(), edible.getIsVegan(), edible.getIsVegetarian(), edible.getIsGlutenFree());
        currEdible.initMetadata(edible.getIsCustom(), edible.getPhoto());

        if (currEdible instanceof Meal) {
            ((Meal) currEdible).setInstructions(this.getInstructions(currEdible));
            ((Meal) currEdible).setIngredients(this.getMealIngredients(currEdible));
        } else if (currEdible instanceof Drink) {
            ((Drink) currEdible).setInstructions(this.getInstructions(currEdible));
            ((Drink) currEdible).setIngredients(this.getDrinkIngredients(currEdible));
        }

        return currEdible;
    }

    private Edible readEdible(ResultSet results, boolean isCustom) {
        Edible currEdible = new Edible();

        String name, description, unit, photo;
        int id, quantity, calories, protein, carbs, fat;
        boolean isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree;

        try {
            if (isCustom) {
                id = results.getInt("CUSTOMEDIBLEID");
            } else {
                id = results.getInt("EDIBLEID");
            }

            name = results.getString("NAME");
            description = results.getString("Description");
            quantity = results.getInt("Quantity");
            unit = results.getString("Unit");
            calories = results.getInt("Calories");
            protein = results.getInt("Protein");
            carbs = results.getInt("Carbs");
            fat = results.getInt("Fat");
            isAlcoholic = results.getBoolean("IsAlcoholic");
            isSpicy = results.getBoolean("IsSpicy");
            isVegan = results.getBoolean("IsVegan");
            isVegetarian = results.getBoolean("IsVegetarian");
            isGlutenFree = results.getBoolean("IsGlutenFree");
            photo = results.getString("Photo");

            currEdible.initDetails(id, name, description, quantity, this.findUnit(unit));
            currEdible.initNutrition(calories, protein, carbs, fat);
            currEdible.initCategories(isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree);
            currEdible.initMetadata(isCustom, photo);
        } catch (Exception e) {
            System.out.println("HSqlDB ReadEdible " + e);

        }

        return currEdible;
    }

    private Edible.Unit findUnit(String enumName) {
        Edible.Unit currUnit = null;

        for (Edible.Unit unit : Edible.Unit.values()) {
            if (unit.toString().equals(enumName)) {
                currUnit = unit;
                break;
            }
        }

        return currUnit;
    }

    public ArrayList<Edible> getMealRecipes() {
        ArrayList<Edible> mealList = new ArrayList<Edible>();
        try {
            PreparedStatement getMeals = this.currConn.prepareStatement("SELECT * FROM Meal INNER JOIN Edible ON Meal.EdibleID = Edible.EdibleID");
            PreparedStatement getCustomMeals = this.currConn.prepareStatement("SELECT * FROM CustomMeal INNER JOIN CustomEdible ON CustomEdible.CustomEdibleID = CustomMeal.CustomEdibleID");
            ResultSet results;
            Meal currMeal;
            Edible currEdible;

            results = getCustomMeals.executeQuery();

            while (results.next()) {
                currEdible = this.readEdible(results, true);
                currMeal = new Meal();
                currMeal.initDetails(currEdible.getDbkey(), currEdible.getName(), currEdible.getDescription(), currEdible.getQuantity(), currEdible.getUnit());
                currMeal.initCategories(currEdible.getIsAlcoholic(), currEdible.getIsSpicy(), currEdible.getIsVegan(), currEdible.getIsVegetarian(), currEdible.getIsGlutenFree());
                currMeal.initNutrition(currEdible.getCalories(), currEdible.getProtein(), currEdible.getCarbs(), currEdible.getFat());
                currMeal.initMetadata(true, currEdible.getPhoto());
                currMeal.setIngredients(this.getMealIngredients(currMeal));
                currMeal.setInstructions(this.getInstructions(currMeal));
                mealList.add(currMeal);
            }
            results.close();

            //Query regular
            results = getMeals.executeQuery();

            while (results.next()) {
                currEdible = this.readEdible(results, false);
                currMeal = new Meal();
                currMeal.initDetails(currEdible.getDbkey(), currEdible.getName(), currEdible.getDescription(), currEdible.getQuantity(), currEdible.getUnit());
                currMeal.initCategories(currEdible.getIsAlcoholic(), currEdible.getIsSpicy(), currEdible.getIsVegan(), currEdible.getIsVegetarian(), currEdible.getIsGlutenFree());
                currMeal.initNutrition(currEdible.getCalories(), currEdible.getProtein(), currEdible.getCarbs(), currEdible.getFat());
                currMeal.initMetadata(false, currEdible.getPhoto());
                currMeal.setIngredients(this.getMealIngredients(currMeal));
                currMeal.setInstructions(this.getInstructions(currMeal));
                mealList.add(currMeal);
            }
            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB GetMealRecipes " + e);

        }

        return mealList;
    }

    public ArrayList<Edible> getDrinkRecipes() {
        ArrayList<Edible> drinkList = new ArrayList<Edible>();
        try {
            PreparedStatement getDrinks = this.currConn.prepareStatement("SELECT * FROM Drink INNER JOIN Edible ON Drink.EdibleID = Edible.EdibleID");
            PreparedStatement getCustomDrinks = this.currConn.prepareStatement("SELECT * FROM CustomDrink INNER JOIN CustomEdible ON CustomEdible.CustomEdibleID = CustomDrink.CustomEdibleID");
            ResultSet results;
            Drink currDrink;
            Edible currEdible;

            //Query custom
            results = getCustomDrinks.executeQuery();

            while (results.next()) {
                currEdible = this.readEdible(results, true);
                currDrink = new Drink();
                currDrink.initDetails(currEdible.getDbkey(), currEdible.getName(), currEdible.getDescription(), currEdible.getQuantity(), currEdible.getUnit());
                currDrink.initCategories(currEdible.getIsAlcoholic(), currEdible.getIsSpicy(), currEdible.getIsVegan(), currEdible.getIsVegetarian(), currEdible.getIsGlutenFree());
                currDrink.initNutrition(currEdible.getCalories(), currEdible.getProtein(), currEdible.getCarbs(), currEdible.getFat());
                currDrink.initMetadata(true, currEdible.getPhoto());
                currDrink.setIngredients(this.getDrinkIngredients(currDrink));
                currDrink.setInstructions(this.getInstructions(currDrink));
                drinkList.add(currDrink);
            }
            results.close();

            //Query regular
            results = getDrinks.executeQuery();

            while (results.next()) {
                currEdible = this.readEdible(results, false);
                currDrink = new Drink();
                currDrink.initDetails(currEdible.getDbkey(), currEdible.getName(), currEdible.getDescription(), currEdible.getQuantity(), currEdible.getUnit());
                currDrink.initCategories(currEdible.getIsAlcoholic(), currEdible.getIsSpicy(), currEdible.getIsVegan(), currEdible.getIsVegetarian(), currEdible.getIsGlutenFree());
                currDrink.initNutrition(currEdible.getCalories(), currEdible.getProtein(), currEdible.getCarbs(), currEdible.getFat());
                currDrink.initMetadata(false, currEdible.getPhoto());
                currDrink.setIngredients(this.getDrinkIngredients(currDrink));
                currDrink.setInstructions(this.getInstructions(currDrink));
                drinkList.add(currDrink);
            }
            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB GetDrinkRecipes " + e);

        }

        return drinkList;
    }

    private String getInstructions(Edible currEdible) {
        String instructions = "";
        try {
            PreparedStatement getInstructions = this.currConn.prepareStatement("SELECT Instructions FROM Meal, Drink WHERE EdibleID = ?");
            PreparedStatement getCustomInstructions = this.currConn.prepareStatement("SELECT Instructions FROM CustomMeal, CustomDrink WHERE CustomEdibleID = ?");
            ResultSet results;
            getInstructions.setInt(1, currEdible.getDbkey());
            String foundInstructions;

            if (currEdible.getIsCustom()) {
                results = getCustomInstructions.executeQuery();
            } else {
                results = getInstructions.executeQuery();
            }

            results.next();

            foundInstructions = results.getString("Instructions");
            results.close();

            if (foundInstructions != null) {
                instructions = foundInstructions;
            }
        } catch (Exception e) {
            System.out.println("HSqlDB GetInstructions " + e);
        }

        return instructions;
    }

    public int getNextKey() {
        return 1;
    }

    public void addFoodToRecipeBook(Edible newFood) {
        try {
            PreparedStatement addFood = currConn.prepareStatement("INSERT INTO Food (?)");
            PreparedStatement addCustomFood = currConn.prepareStatement("INSERT INTO CustomFood (?)");
            boolean isCustom = newFood.getIsCustom();
            int edibleID;

            if (isCustom) {
                edibleID = this.addEdible(newFood, true);
                addFood.setInt(1, edibleID);
                addFood.executeQuery();
            } else {
                edibleID = this.addEdible(newFood, false);
                addCustomFood.setInt(1, edibleID);
                addCustomFood.executeQuery();
            }
        } catch (Exception e) {
            System.out.println("HSqlDB AddFoodToRecipeBook " + e);

        }
    }

    private int addEdible(Edible newEdible, boolean isCustom) {
        int edibleID = -1;
        try {
            PreparedStatement addEdible = currConn.prepareStatement("INSERT INTO Edible (Name, Description, Quantity, " +
                    "Unit, Calories, Protein, Carbs, Fat, Photo, Alcoholic, Spicy, Vegan, Vegetarian, GluteFree) VALUES (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement addCustomEdible = currConn.prepareStatement("INSERT INTO CustomEdible (Name, Description, " +
                    "Quantity, Unit, Calories, Protein, Carbs, Fat, Photo, Alcoholic, Spicy, Vegan, Vegetarian, GluteFree) VALUES (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ResultSet results = null;

            if (isCustom) {
                addCustomEdible.setString(1, newEdible.getName());
                addCustomEdible.setString(2, newEdible.getDescription());
                addCustomEdible.setDouble(3, newEdible.getQuantity());
                addCustomEdible.setString(4, newEdible.getUnit().toString());
                addCustomEdible.setDouble(5, newEdible.getCalories());
                addCustomEdible.setInt(6, newEdible.getProtein());
                addCustomEdible.setInt(7, newEdible.getCarbs());
                addCustomEdible.setInt(8, newEdible.getFat());
                addCustomEdible.setString(9, newEdible.getPhoto());
                addCustomEdible.setBoolean(10, newEdible.getIsAlcoholic());
                addCustomEdible.setBoolean(11, newEdible.getIsSpicy());
                addCustomEdible.setBoolean(12, newEdible.getIsVegan());
                addCustomEdible.setBoolean(13, newEdible.getIsVegetarian());
                addCustomEdible.setBoolean(14, newEdible.getIsGlutenFree());
                addCustomEdible.executeQuery();
                results.getRowId("CustomEdibleID");
            } else {
                addEdible.setString(1, newEdible.getName());
                addEdible.setString(2, newEdible.getDescription());
                addEdible.setDouble(3, newEdible.getQuantity());
                addEdible.setString(4, newEdible.getUnit().toString());
                addEdible.setDouble(5, newEdible.getCalories());
                addEdible.setInt(6, newEdible.getProtein());
                addEdible.setInt(7, newEdible.getCarbs());
                addEdible.setInt(8, newEdible.getFat());
                addEdible.setString(9, newEdible.getPhoto());
                addEdible.setBoolean(10, newEdible.getIsAlcoholic());
                addEdible.setBoolean(11, newEdible.getIsSpicy());
                addEdible.setBoolean(12, newEdible.getIsVegan());
                addEdible.setBoolean(13, newEdible.getIsVegetarian());
                addEdible.setBoolean(14, newEdible.getIsGlutenFree());
                addEdible.executeQuery();
                results.getRowId("EdibleID");
            }

            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB AddEdible " + e);

        }

        return edibleID;
    }

    public void addMealToRecipeBook(Meal newMeal) {
        try {
            PreparedStatement addMeal = currConn.prepareStatement("INSERT INTO Meal VALUES (?, ?)");
            PreparedStatement addCustomMeal = currConn.prepareStatement("INSERT INTO CustomMeal VALUES (?, ?)");
            PreparedStatement addIngredient = currConn.prepareStatement("INSERT INTO MealIngredient (PreparedID, " +
                    "EdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");
            PreparedStatement addCustomIngredientToMeal = currConn.prepareStatement("INSERT INTO MealIngredient " +
                    "(PreparedID, CustomEdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");
            PreparedStatement addCustomIngredientToCustomMeal = currConn.prepareStatement("INSERT INTO MealIngredient " +
                    "(CustomPreparedID, CustomEdibleID, Quantity, Unit), VALUES (?, ?, ?, ?)");

            Meal currMeal;
            ArrayList<Ingredient> currIngredients;
            boolean isCustom = newMeal.getIsCustom();
            int edibleID = this.addEdible(newMeal, isCustom);

            if (isCustom) {
                addCustomMeal.setInt(1, edibleID);
                addCustomMeal.setString(2, newMeal.getInstructions());
                addCustomMeal.executeQuery();

                currIngredients = newMeal.getIngredients();
                for (int i = 0; i < currIngredients.size(); i++) {
                    currMeal = (Meal) currIngredients.get(i).getIngredient();

                    if (!currMeal.getIsCustom()) {
                        addCustomIngredientToMeal.setInt(1, edibleID);
                        addCustomIngredientToMeal.setInt(2, currMeal.getDbkey());
                        addCustomIngredientToMeal.setDouble(3, currMeal.getQuantity());
                        addCustomIngredientToMeal.setString(4, currMeal.getUnit().toString());
                        addCustomIngredientToMeal.executeQuery();
                    } else {
                        addCustomIngredientToCustomMeal.setInt(1, edibleID);
                        addCustomIngredientToCustomMeal.setInt(2, currMeal.getDbkey());
                        addCustomIngredientToCustomMeal.setDouble(3, currMeal.getQuantity());
                        addCustomIngredientToCustomMeal.setString(4, currMeal.getUnit().toString());
                        addCustomIngredientToCustomMeal.executeQuery();
                    }
                }
            } else {
                addMeal.setInt(1, edibleID);
                addMeal.setString(2, newMeal.getInstructions());
                addMeal.executeQuery();

                currIngredients = newMeal.getIngredients();
                for (int i = 0; i < currIngredients.size(); i++) {
                    currMeal = (Meal) currIngredients.get(i).getIngredient();

                    addIngredient.setInt(1, edibleID);
                    addIngredient.setInt(2, currMeal.getDbkey());
                    addIngredient.setDouble(3, currMeal.getQuantity());
                    addIngredient.setString(4, currMeal.getUnit().toString());
                    addIngredient.executeQuery();
                }
            }
        } catch (Exception e) {
            System.out.println("HSqlDB AddMealToRecipeBook " + e);

        }
    }

    public void addDrinkToRecipeBook(Drink newDrink) {
        try {
            PreparedStatement addDrink = currConn.prepareStatement("INSERT INTO Drink VALUES (?, ?)");
            PreparedStatement addCustomDrink = currConn.prepareStatement("INSERT INTO CustomDrink VALUES (?, ?)");
            PreparedStatement addIngredient = currConn.prepareStatement("INSERT INTO DrinkIngredient (PreparedID, " +
                    "EdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");
            PreparedStatement addCustomIngredientToDrink = currConn.prepareStatement("INSERT INTO DrinkIngredient " +
                    "(PreparedID, CustomEdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");
            PreparedStatement addCustomIngredientToCustomDrink = currConn.prepareStatement("INSERT INTO DrinkIngredient " +
                    "(CustomPreparedID, CustomEdibleID, Quantity, Unit, Substitute), VALUES (?, ?, ?, ?, ?)");

            DrinkIngredient currIngredient;
            ArrayList<Ingredient> currIngredients = new ArrayList<Ingredient>();
            boolean isCustom = newDrink.getIsCustom();
            int edibleID = this.addEdible(newDrink, isCustom);

            if (isCustom) {
                addCustomDrink.setInt(1, edibleID);
                addCustomDrink.setString(2, newDrink.getInstructions());
                addCustomDrink.executeQuery();

                for (int i = 0; i < newDrink.getIngredients().size(); i++) {
                    currIngredients.add((Ingredient) newDrink.getIngredients().get(i));
                }

                for (int i = 0; i < currIngredients.size(); i++) {
                    currIngredient = (DrinkIngredient) currIngredients.get(i);

                    if (!currIngredient.getIngredient().getIsCustom()) {
                        addCustomIngredientToDrink.setInt(1, edibleID);
                        addCustomIngredientToDrink.setInt(2, currIngredient.getIngredient().getDbkey());
                        addCustomIngredientToDrink.setDouble(3, currIngredient.getQuantity());
                        addCustomIngredientToDrink.setString(4, currIngredient.getQuantityUnits().toString());
                        addCustomIngredientToDrink.setBoolean(5, currIngredient.getIsSubstitute());
                        addCustomIngredientToDrink.executeQuery();
                    } else {
                        addCustomIngredientToCustomDrink.setInt(1, edibleID);
                        addCustomIngredientToCustomDrink.setInt(2, currIngredient.getIngredient().getDbkey());
                        addCustomIngredientToCustomDrink.setDouble(3, currIngredient.getQuantity());
                        addCustomIngredientToCustomDrink.setString(4, currIngredient.getQuantityUnits().toString());
                        addCustomIngredientToCustomDrink.setBoolean(5, currIngredient.getIsSubstitute());
                        addCustomIngredientToCustomDrink.executeQuery();
                    }
                }
            } else {
                addDrink.setInt(1, edibleID);
                addDrink.setString(2, newDrink.getInstructions());
                addDrink.executeQuery();

                for (int i = 0; i < newDrink.getIngredients().size(); i++) {
                    currIngredients.add((Ingredient) newDrink.getIngredients().get(i));
                }

                for (int i = 0; i < currIngredients.size(); i++) {
                    currIngredient = (DrinkIngredient) currIngredients.get(i);

                    addIngredient.setInt(1, edibleID);
                    addIngredient.setInt(2, currIngredient.getIngredient().getDbkey());
                    addIngredient.setDouble(3, currIngredient.getQuantity());
                    addIngredient.setString(4, currIngredient.getQuantityUnits().toString());
                    addIngredient.setBoolean(5, currIngredient.getIsSubstitute());
                    addIngredient.executeQuery();
                }
            }
        } catch (Exception e) {
            System.out.println("HSqlDB AddDrinkToRecipeBook " + e);

        }
    }

    public DailyLog searchFoodLogByDate(int userID, Calendar date) throws IllegalArgumentException {
        DailyLog log = null;
        try {
            if (userID == 0 && date != null) {
                PreparedStatement findLog = currConn.prepareStatement("SELECT * FROM HISTORY INNER JOIN USER ON USER.USERID = HISTORY.USERID WHERE USERID = ? AND DATE = ?");
                ResultSet results;
                int exerciseActual;
                ArrayList<Edible> edibleLog;
                User currUser = null;

                findLog.setInt(1, userID);
                String hi = this.convertDateToString(date);
                findLog.setString(2, hi);
                results = findLog.executeQuery();

                if (results.next()) {
                    edibleLog = this.getEdibleLog(results.getInt("HISTORYID"));
                    exerciseActual = this.getExerciseActual(results.getInt("HISTORYID"));
                    log = new DailyLog().init(date, edibleLog, results.getInt("CalorieGoal"), results.getInt("EXERCISEGOAL"),
                            exerciseActual);
                } else {
                    currUser = this.getUser();
                    log = new DailyLog().init(date, new ArrayList<Edible>(), currUser.getCalorieGoal(), currUser.getExerciseGoal(), 0);
                    this.addLog(currUser.getUserID(), log);

                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB SearchFoodLogByDate " + e);

        }
        return log;
    }

    private ArrayList<DrinkIngredient> getDrinkIngredients(Edible currEdible) { //
        ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
        try {
            PreparedStatement getIngredients = currConn.prepareStatement("SELECT * FROM DrinkIngredient INNER JOIN Edible " +
                    "ON Edible.EdibleID = DrinkIngredient.EdibleID WHERE PreparedID = ? OR PreparedCustomID = ?");
            ResultSet results;

            DrinkIngredient currIngredient;
            Edible.Unit unit;

            getIngredients.setInt(1, currEdible.getDbkey());
            getIngredients.setInt(2, currEdible.getDbkey());
            results = getIngredients.executeQuery();

            while (results.next()) {
                unit = this.findUnit(results.getString("Unit"));
                currIngredient = new DrinkIngredient();
                currIngredient.init(currEdible, results.getInt("Quantity"), unit);
                currIngredient.setSubstitute(results.getBoolean("Substitute"));
                ingredients.add(currIngredient);
            }
        } catch (Exception e) {
            System.out.println("HSqlDB GetDrinkIngredients " + e);

        }

        return ingredients;
    }

    private ArrayList<Ingredient> getMealIngredients(Edible currEdible) {   //save all as things without ingredients
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        try {
            PreparedStatement getIngredients = currConn.prepareStatement("SELECT * FROM MealIngredient INNER JOIN Edible " +
                    "ON Edible.EdibleID = MealIngredient.EdibleID WHERE PreparedID = ? OR PreparedCustomID = ?");
            ResultSet results;

            Ingredient currIngredient;
            Edible.Unit unit;

            getIngredients.setInt(1, currEdible.getDbkey());
            getIngredients.setInt(2, currEdible.getDbkey());
            results = getIngredients.executeQuery();

            while (results.next()) {
                unit = this.findUnit(results.getString("Unit"));
                currIngredient = new Ingredient().init(currEdible, results.getInt("Quantity"), unit);
                ingredients.add(currIngredient);
            }
        } catch (Exception e) {
            System.out.println("HSqlDB GetMealIngredients " + e);

        }

        return ingredients;
    }

    private ArrayList<Edible> getEdibleLog(int historyID) {
        ArrayList<Edible> edibleLog = new ArrayList<Edible>();
        try {
            PreparedStatement fineEdibles = currConn.prepareStatement("SELECT * FROM EdibleHistory " +
                    "INNER JOIN EDIBLE ON Edible.EdibleID = EdibleHistory.EDIBLEID WHERE HistoryID = ?");
            PreparedStatement findCustomEdibles = currConn.prepareStatement("SELECT * FROM EdibleHistory " +
                    "INNER JOIN CUSTOMEDIBLE ON CUSTOMEDIBLE.CUSTOMEDIBLEID = EdibleHistory.CUSTOMEDIBLEID WHERE HistoryID = ?");
            PreparedStatement getBase = currConn.prepareStatement("SELECT Unit, Quantity FROM Edible WHERE EdibleID = ?");
            PreparedStatement getCustomBase = currConn.prepareStatement("SELECT Unit, Quantity FROM CustomEdible WHERE CustomEdibleID = ?");
            ResultSet results;
            ResultSet origBase;

            Edible currEdible;
            EdibleLog currLog;
            Edible.Unit unit;

            fineEdibles.setInt(1, historyID);
            results = fineEdibles.executeQuery();

            //non custom
            while (results.next()) {
                currEdible = this.readEdible(results, false); //this will return true when id = 0 on first one
                getBase.setInt(1, currEdible.getDbkey());
                origBase = getBase.executeQuery();
                origBase.next();
                currEdible.setBaseQuantity(origBase.getInt("Quantity"));
                currEdible.setBaseUnit(this.findUnit(origBase.getString("Unit")));
                unit = this.findUnit(results.getString("Unit"));
                currLog = new EdibleLog(currEdible).init(results.getInt("Quantity"), unit);
                edibleLog.add(currLog);
                origBase.close();
            }
            results.close();

            findCustomEdibles.setInt(1, historyID);
            results = findCustomEdibles.executeQuery();
            //custom
            while (results.next()) {
                currEdible = this.readEdible(results, true); //this will return true when id = 0 on first one
                getCustomBase.setInt(1, currEdible.getDbkey());
                origBase = getCustomBase.executeQuery();
                origBase.next();
                currEdible.setBaseQuantity(origBase.getInt("Quantity"));
                currEdible.setBaseUnit(this.findUnit(origBase.getString("Unit")));
                unit = this.findUnit(results.getString("Unit"));
                currLog = new EdibleLog(currEdible).init(results.getInt("Quantity"), unit);
                edibleLog.add(currLog);
                origBase.close();
            }
            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB GetEdibleLog " + e);

        }

        return edibleLog;
    }

    private int getExerciseActual(int historyID) {
        int exerciseActual = 0;
        try {
            PreparedStatement getExerciseActual = currConn.prepareStatement("SELECT * FROM WorkoutHistory WHERE HistoryID = ?");
            ResultSet results;

            getExerciseActual.setInt(1, historyID);
            results = getExerciseActual.executeQuery();

            if (results.next()) {
                exerciseActual = results.getInt("ExerciseActual");
            }
            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB GetExerciseActual " + e);

        }

        return exerciseActual;
    }

    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom) {
        EdibleLog edibleLog = null;

        try {
            PreparedStatement findEdibleByKey = currConn.prepareStatement("SELECT * FROM Edible WHERE EdibleID = ?");
            PreparedStatement findCustomEdibleByKey = currConn.prepareStatement("SELECT * FROM CustomEdible WHERE CustomEdibleID = ?");
            ResultSet results;

            if (isCustom) {
                findCustomEdibleByKey.setInt(1, dbkey);
                results = findCustomEdibleByKey.executeQuery();
            } else {
                findEdibleByKey.setInt(1, dbkey);
                results = findEdibleByKey.executeQuery();
            }

            results.next();

            edibleLog = new EdibleLog(this.readEdible(results, isCustom));
        } catch (Exception e) {
            System.out.println("HSqlDB findEdibleByKey " + e);
        }

        return edibleLog;
    }

    private boolean isMeal(int dbkey, boolean isCustom) {
        boolean found = false;
        try {
            PreparedStatement checkIfMeal = currConn.prepareStatement("SELECT * FROM Meal WHERE EdibleID = ?");
            PreparedStatement checkIfCustomMeal = currConn.prepareStatement("SELECT * FROM CustomMeal WHERE CustomEdibleID = ?");
            found = false;
            ResultSet results;

            if (isCustom) {
                checkIfCustomMeal.setInt(1, dbkey);
                results = checkIfCustomMeal.executeQuery();
            } else {
                checkIfMeal.setInt(1, dbkey);
                results = checkIfMeal.executeQuery();
            }

            if (results.next()) {
                found = true;
            }

            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB isMeal " + e);
        }

        return found;
    }

    private boolean isDrink(int dbkey, boolean isCustom) {
        boolean found = false;
        try {
            PreparedStatement checkIfDrink = currConn.prepareStatement("SELECT * FROM Drink WHERE EdibleID = ?");
            PreparedStatement checkIfCustomDrink = currConn.prepareStatement("SELECT * FROM CustomDrink WHERE CustomEdibleID = ?");
            ResultSet results;

            if (isCustom) {
                checkIfCustomDrink.setInt(1, dbkey);
                results = checkIfCustomDrink.executeQuery();
            } else {
                checkIfDrink.setInt(1, dbkey);
                results = checkIfDrink.executeQuery();
            }

            if (results.next()) {
                found = true;
            }

            results.close();
        } catch (Exception e) {
            System.out.println("HSqlDB isDrink " + e);
        }

        return found;
    }

    public void replaceLog(int userID, DailyLog newLog) throws IllegalArgumentException {
        if (userID == 0 && newLog != null) {
            this.deleteLog(userID, newLog.getDate());
            this.addLog(userID, newLog);
        } else {
            throw new IllegalArgumentException("HSqlDB replaceLog userID must exist and log cannot be null");
        }
    }

    public void addLog(int userID, DailyLog newLog) {
        try {
            PreparedStatement addHistory = currConn.prepareStatement("INSERT INTO History (UserID, Date, CalorieGoal, " +
                    "CalorieActual) VALUES (?, ?, ?, 0)");
            PreparedStatement addEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
                    "EdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");
            PreparedStatement addCustomEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
                    "CustomEdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");

            ArrayList<Edible> edibles = newLog.getEdibleList();
            Edible currEdible;
            int historyID;

            addHistory.setInt(1, userID);
            String hi = this.convertDateToString(newLog.getDate());
            addHistory.setString(2, hi);
            addHistory.setDouble(3, newLog.getCalorieGoal());
            addHistory.executeUpdate();
            historyID = getHistoryID(newLog, userID);

            for (int i = 0; i < edibles.size(); i++) {
                currEdible = edibles.get(i);
                //System.out.println(currEdible.getName());
                if (currEdible.getIsCustom()) {
                    addCustomEdibleHistory.setInt(1, historyID);
                    addCustomEdibleHistory.setInt(2, currEdible.getDbkey());
                    addCustomEdibleHistory.setDouble(3, currEdible.getQuantity());
                    addCustomEdibleHistory.setString(4, currEdible.getUnit().toString());
                    addCustomEdibleHistory.executeUpdate();
                } else {
                    addEdibleHistory.setInt(1, historyID);
                    addEdibleHistory.setInt(2, currEdible.getDbkey());
                    addEdibleHistory.setDouble(3, currEdible.getQuantity());
                    addEdibleHistory.setString(4, currEdible.getUnit().toString());
                    addEdibleHistory.executeUpdate();
                }
            }

            if (newLog.getExerciseActual() >= 0) {
                this.setExerciseActual(userID, newLog.getExerciseActual(), newLog.getDate());
            }
        } catch (Exception e) {
            System.out.println("HSqlDB addLog " + e);
        }
    }

    public void deleteLog(int userID, Calendar date) {
        try {
            PreparedStatement deleteLog = currConn.prepareStatement("DELETE FROM History WHERE Date = ? AND UserID = ?");

            deleteLog.setString(1, this.convertDateToString(date));
            deleteLog.setInt(2, userID);
            deleteLog.executeUpdate();
        } catch (Exception e) {
            System.out.println("HSqlDB deleteLog " + e);
        }
    }

    public void setExerciseActual(int userID, double newExercise, Calendar date) throws IllegalArgumentException {
        try {
            if (userID == 0 && date != null) {
                if (newExercise >= Constant.ENTRY_MIN_VALUE && newExercise <= Constant.ENTRY_MAX_VALUE) {
                    ResultSet results;
                    int id = 0;
                    String dateString = this.convertDateToString(date);
                    PreparedStatement getId = currConn.prepareStatement("SELECT * FROM History WHERE Date = ?");
                    PreparedStatement checkExists = currConn.prepareStatement("SELECT * FROM History INNER JOIN WorkoutHistory ON WorkoutHistory.HISTORYID = HISTORY.HISTORYID WHERE Date = ?");
                    PreparedStatement update = currConn.prepareStatement("UPDATE WorkoutHistory SET ExerciseActual = ? WHERE HistoryID = ?");
                    PreparedStatement insert = currConn.prepareStatement("INSERT INTO WorkoutHistory VALUES (?, ?)");

                    getId.setString(1, dateString);
                    results = getId.executeQuery();
                    results.next();
                    id = results.getInt("HISTORYID");

                    checkExists.setString(1, dateString);
                    results = checkExists.executeQuery();

                    if (results.next()) {
                        update.setDouble(1, newExercise);
                        update.setInt(2, id);
                        update.executeUpdate();
                    } else {
                        insert.setInt(1, id);
                        insert.setDouble(2, newExercise);
                        insert.executeUpdate();
                    }
                    results.close();

                } else {
                    throw new IllegalArgumentException("HSqlDB setExerciseActual requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB setExerciseActual userID must exist and date cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB SetExerciseActual " + e);
        }
    }

    private int getHistoryID(DailyLog currLog, int userID) {
        int historyID = -1;
        try {
            PreparedStatement getHistoryID = currConn.prepareStatement("SELECT HistoryID FROM History WHERE UserID = ? AND Date = ?");
            ResultSet results;
            Calendar date = currLog.getDate();
            String currDate = convertDateToString(date);
            getHistoryID.setString(2, currDate);
            getHistoryID.setInt(1, userID);
            results = getHistoryID.executeQuery();

            if (results.next()) {
                historyID = results.getInt("HistoryID");
            }
        } catch (Exception e) {
            System.out.println("HSqlDB GetHistoryID " + e);
        }
        return historyID;
    }

    public void addUser(String name, int height, int weight) {
        try {
            PreparedStatement addUser = currConn.prepareStatement("INSERT * INTO User (Name, Height, Weight) VALUES (?, ?, ?)");

            addUser.setString(1, name);
            addUser.setInt(1, height);
            addUser.setInt(1, weight);
            addUser.executeQuery();
        } catch (Exception e) {
            System.out.println("HSqlDB addUser " + e);
        }
    }

    public User getUser() {
        //System.out.println("YEs");
        User currUser = null;
        try {
            PreparedStatement getUser = currConn.prepareStatement("SELECT * FROM USER");
            ResultSet results = getUser.executeQuery();

            results.next();
            currUser = new User().init(results.getInt("USERID"), results.getString("NAME"), results.getInt("HEIGHT"),
                    results.getInt("WEIGHT"), results.getInt("CALORIEGOAL"), results.getInt("EXERCISEGOAL"));
        } catch (Exception e) {
            System.out.println("HSqlDB GetUser " + e);
        }

        return currUser;
    }

    public void setHeight(int userID, int newHeight) {
        try {
            if (newHeight >= 1 && newHeight <= Constant.ENTRY_MAX_VALUE) {
            PreparedStatement setHeight = currConn.prepareStatement("Update User SET Height = ? WHERE UserID = ?");

            setHeight.setInt(1, newHeight);
            setHeight.setInt(2, userID);
            setHeight.executeUpdate();
            } else {
                throw new IllegalArgumentException("HSqlDB setHeight requires values " + 1 + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (Exception e) {
            System.out.println("HSqlDB setHeight " + e);

        }
    }

    public void setWeight(int userID, int newWeight) {
        try {
            if (newWeight >= 1 && newWeight <= Constant.ENTRY_MAX_VALUE) {
            PreparedStatement setWeight = currConn.prepareStatement("Update User SET Weight = ? WHERE UserID = ?");

            setWeight.setInt(1, newWeight);
            setWeight.setInt(2, userID);
            setWeight.executeUpdate();
            } else {
                throw new IllegalArgumentException("HSqlDB setWeight requires values " + 1 + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (Exception e) {
            System.out.println("HSqlDB setWeight " + e);

        }
    }

    private String convertDateToString(Calendar date) {
        return date.get(date.YEAR) + "-" + (date.get(date.MONTH) + 1) + "-" + date.get(date.DAY_OF_MONTH);
    }

    private Calendar convertStringToDate(String date) {
        Calendar calendarDate = Calendar.getInstance();
        String[] entries = date.split("-");

        calendarDate.set(Integer.parseInt(entries[0]), Integer.parseInt(entries[1]), Integer.parseInt(entries[2]));

        return calendarDate;
    }

    public void setLogCalorieGoal(int userID, double goal, Calendar date) throws IllegalArgumentException {
        try {
            if (userID == 0 && date != null) {
                if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                    PreparedStatement setCalorieGoal = currConn.prepareStatement("UPDATE History SET CalorieGoal = ? " +
                            "WHERE UserID = ? AND Date = ?");

                    setCalorieGoal.setDouble(1, goal);
                    setCalorieGoal.setInt(2, userID);
                    setCalorieGoal.setString(3, this.convertDateToString(date));
                    setCalorieGoal.executeUpdate();

                } else {
                    throw new IllegalArgumentException("HSqlDB setLogCalorieGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB setExerciseActual userID must exist and date cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB setLogCalorieGoal " + e);

        }
    }

    public void setLogExerciseGoal(int userID, double goal, Calendar date) throws IllegalArgumentException {
        try {
            if (userID == 0 && date != null) {
                if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                    PreparedStatement setExerciseGoal = currConn.prepareStatement("UPDATE History SET ExerciseGoal = ? " +
                            "WHERE UserID = ? AND Date = ?");

                    setExerciseGoal.setDouble(1, goal);
                    setExerciseGoal.setInt(2, userID);
                    setExerciseGoal.setString(3, this.convertDateToString(date));
                    setExerciseGoal.executeUpdate();

                } else {
                    throw new IllegalArgumentException("HSqlDB setLogExerciseGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB setExerciseActual userID must exist and date cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB setLogExerciseGoal " + e);
        }
    }


    public void setCalorieGoal(int userID, double goal) throws IllegalArgumentException {
        try {
            if (userID == 0) {
                if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                    PreparedStatement setCalorieGoal = currConn.prepareStatement("UPDATE USER SET CalorieGoal = ? WHERE UserID = ?");
                    setCalorieGoal.setInt(1, userID);
                    setCalorieGoal.setInt(2, (int) goal);
                    setCalorieGoal.executeUpdate();

                } else {
                    throw new IllegalArgumentException("HSqlDB setExerciseActual requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB setExerciseGoal userID must exist");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB setCalorieGoal " + e);
        }
    }

    public void setExerciseGoal(int userID, double goal) throws IllegalArgumentException {
        try {
            if (userID == 0) {
                if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                    PreparedStatement setExerciseGoal = currConn.prepareStatement("UPDATE USER SET ExerciseGoal = ? WHERE UserID = ?");
                    setExerciseGoal.setInt(1, userID);
                    setExerciseGoal.setInt(2, (int) goal);
                    setExerciseGoal.executeUpdate();

                } else {
                    throw new IllegalArgumentException("HSqlDB setExerciseGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB setExerciseGoal userID must exist");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("HSqlDB setExerciseGoal " + e);
        }
    }

    public ArrayList<Double> getDataFrame(DataFrame.DataType dataType, int days) throws IllegalArgumentException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.MONTH, 9);
        today.set(Calendar.DAY_OF_MONTH, 10);
        ArrayList<Double> result = new ArrayList<>();
        Double value = new Double(0);
        if (dataType != null) {
            if (days >= DataFrame.numDays[DataFrame.Span.Week.ordinal()]) {
                for (int i = 0; i < days; i++) {
                    today.add(Calendar.DAY_OF_YEAR, -1);
                    switch (dataType.ordinal()) {
                        case 0:
                            value = searchFoodLogByDate(0, today).getEdibleCalories();
                            break;

                        case 1:
                            value = searchFoodLogByDate(0, today).getCalorieNet();
                            break;

                        case 2:
                            value = searchFoodLogByDate(0, today).getExerciseActual();
                            break;

                        default:
                            value = 160.0;
                    }

                    result.add(value);
                }
            } else {
                throw new IllegalArgumentException("HSqlDB getDataFrame must be >= " + DataFrame.numDays[DataFrame.Span.Week.ordinal()]);
            }
        } else {
            throw new IllegalArgumentException("HSqlDB getDataFrame dataType cannot be null");
        }

        return result;
    }
}
