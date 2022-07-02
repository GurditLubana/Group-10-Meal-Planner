package comp3350.team10.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class HSqlDB  implements LogDBInterface, RecipeDBInterface, UserDBInterface {
    private static final String SHUTDOWN_CMD = "shutdown compact";
    private Connection currConn;
    private Statement reqHandler;
    private String dbPath = "jdbc:hsqldb:file:database/db"; // stored on disk mode
    private String dbName;
    private String dbType = "HSQL";

    public HSqlDB() {
        try {
            this.open();
            this.reqHandler = currConn.createStatement();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("constructor");
            System.exit(1);
        }
    }


    public void open() {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            currConn = DriverManager.getConnection(dbPath, "user", "pass");
            System.out.println("Opened " + this.dbType + " database named " + this.dbName + " @dbPath " + this.dbPath);
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("open");
            System.exit(1);
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("close");
            System.exit(1);
        }
    }


    public ArrayList<Edible> getFoodRecipes() {
        ArrayList<Edible> foodList = new ArrayList<Edible>();
        try {
            PreparedStatement getFoodList = currConn.prepareStatement("GET * FROM Food");
            PreparedStatement getCustomFoodList = currConn.prepareStatement("GET * FROM CustomFood");
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
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("getFoodRecipes");
            System.exit(1);
        }


        return foodList;
    }

    private Edible readEdible(ResultSet results, boolean isCustom) {
        Edible currEdible = new Edible();

        String name, description, unit;
        int id, quantity, calories, protein, carbs, fat;
        byte[] photo;
        boolean isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree;

        try {
            id = results.getInt("EdibleID");
            name = results.getString("Name");
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
            photo = results.getBytes("Photo");

            currEdible.initDetails(id, name, description, quantity, this.findUnit(unit));
            currEdible.initNutrition(calories, protein, carbs, fat);
            currEdible.initCategories(isAlcoholic, isSpicy, isVegan, isVegetarian, isGlutenFree);
            currEdible.initMetadata(isCustom, photo);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Read edible");
            System.exit(1);
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
            PreparedStatement getMeals = this.currConn.prepareStatement("SELECT * FROM ?");
            ResultSet results;
            Meal currMeal;

            //Query custom
            getMeals.setString(1, "CustomMeal");
            results = getMeals.executeQuery();

            while (results.next()) {
                currMeal = (Meal)this.readEdible(results, true);
                currMeal.setIngredients(this.getMealIngredients(currMeal));
                currMeal.setInstructions(this.getInstructions(currMeal));
                mealList.add(currMeal);
            }
            results.close();

            //Query regular
            getMeals.setString(1, "Meal");
            results = getMeals.executeQuery();

            while (results.next()) {
                currMeal = (Meal)this.readEdible(results, false);
                currMeal.setIngredients(this.getMealIngredients(currMeal));
                currMeal.setInstructions(this.getInstructions(currMeal));
                mealList.add(currMeal);
            }
            results.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getMealRecipes");
            System.exit(1);
        }

        return mealList;
    }

    public ArrayList<Edible> getDrinkRecipes() {
        ArrayList<Edible> drinkList = new ArrayList<Edible>();
        try {
            PreparedStatement getDrinks = this.currConn.prepareStatement("SELECT * FROM ?");
            ResultSet results;
            Drink currDrink;

            //Query custom
            getDrinks.setString(1, "CustomDrink");
            results = getDrinks.executeQuery();

            while (results.next()) {
                currDrink = (Drink)this.readEdible(results, true);
                currDrink.setIngredients(this.getDrinkIngredients(currDrink));
                currDrink.setInstructions(this.getInstructions(currDrink));
                drinkList.add(currDrink);
            }
            results.close();

            //Query regular
            getDrinks.setString(1, "Drink");
            results = getDrinks.executeQuery();

            while (results.next()) {
                currDrink = (Drink)this.readEdible(results, false);
                currDrink.setIngredients(this.getDrinkIngredients(currDrink));
                currDrink.setInstructions(this.getInstructions(currDrink));
                drinkList.add(currDrink);
            }
            results.close();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("getDrinkRecipes");
            System.exit(1);
        }

        return drinkList;
    }

    private String getInstructions(Edible currEdible) {
        String instructions = "";
        try {
            PreparedStatement getInstructions = this.currConn.prepareStatement("SELECT Instructions FROM ?, ?, WHERE ? = ?");
            ResultSet results;

            String foundInstructions;

            if (currEdible.getIsCustom()) {
                getInstructions.setString(1, "CustomMeal");
                getInstructions.setString(2, "CustomDrink");
                getInstructions.setString(3, "CustomEdibleID");
            } else {
                getInstructions.setString(1, "Meal");
                getInstructions.setString(2, "Drink");
                getInstructions.setString(3, "EdibleID");
            }

            getInstructions.setInt(4, currEdible.getDbkey());
            results = getInstructions.executeQuery();
            foundInstructions = results.getString("Instructions");
            results.close();

            if (foundInstructions != null) {
                instructions = foundInstructions;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getInstructions");
            System.exit(1);
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addFoodToRecipeBook");
            System.exit(1);
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
                addCustomEdible.setBytes(9, newEdible.getPhotoBytes());
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
                addEdible.setBytes(9, newEdible.getPhotoBytes());
                addEdible.setBoolean(10, newEdible.getIsAlcoholic());
                addEdible.setBoolean(11, newEdible.getIsSpicy());
                addEdible.setBoolean(12, newEdible.getIsVegan());
                addEdible.setBoolean(13, newEdible.getIsVegetarian());
                addEdible.setBoolean(14, newEdible.getIsGlutenFree());
                addEdible.executeQuery();
                results.getRowId("EdibleID");
            }

            results.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addEdible");
            System.exit(1);
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
                    currMeal = (Meal)currIngredients.get(i).getIngredient();

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
                    currMeal = (Meal)currIngredients.get(i).getIngredient();

                    addIngredient.setInt(1, edibleID);
                    addIngredient.setInt(2, currMeal.getDbkey());
                    addIngredient.setDouble(3, currMeal.getQuantity());
                    addIngredient.setString(4, currMeal.getUnit().toString());
                    addIngredient.executeQuery();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addMealToRecipeBook");
            System.exit(1);
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

                for(int i = 0; i < newDrink.getIngredients().size(); i++) {
                    currIngredients.add((Ingredient)newDrink.getIngredients().get(i));
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

                for(int i = 0; i < newDrink.getIngredients().size(); i++) {
                    currIngredients.add((Ingredient)newDrink.getIngredients().get(i));
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addDrinkToRecipeBook");
            System.exit(1);
        }
    }

    public DailyLog searchFoodLogByDate(Calendar date, int userID) {
        DailyLog log = null;
        try {
            PreparedStatement findLog = currConn.prepareStatement("GET * FROM History, WHERE UserID = ? AND Date = ?");
            ResultSet results;
            int exerciseActual;
            ArrayList<Edible> edibleLog;

            findLog.setInt(1, userID);
            findLog.setString(2, this.convertDateToString(date));
            results = findLog.executeQuery();

            if (results.next()) {
                edibleLog = this.getEdibleLog(results.getInt("HistoryID"));
                exerciseActual = this.getExerciseActual(results.getInt("HistoryID"));
                log = new DailyLog().init(date, edibleLog, results.getInt("CalorieGoal"), results.getInt("ExerciseGoal"),
                        exerciseActual);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("searchFoodLogByDate");
            System.exit(1);
        }

        return log;
    }

    private ArrayList<DrinkIngredient> getDrinkIngredients(Edible currEdible) { //
        ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
        try {
            PreparedStatement getIngredients = currConn.prepareStatement("SELECT * FROM DrinkIngredient INNER JOIN Edible " +
                    "ON Edible.EdibleID = DrinkIngredient.EdibleID, WHERE PreparedID = ? OR CustomPreparedID = ?");
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getDrinkIngredients");
            System.exit(1);
        }

        return ingredients;
    }

    private ArrayList<Ingredient> getMealIngredients(Edible currEdible) {   //save all as things without ingredients
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        try {
            PreparedStatement getIngredients = currConn.prepareStatement("SELECT * FROM Ingredient INNER JOIN Edible " +
                    "ON Edible.EdibleID = Ingredient.EdibleID, WHERE PreparedID = ? OR CustomPreparedID = ?");
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getMealIngredients");
            System.exit(1);
        }

        return ingredients;
    }

    private ArrayList<Edible> getEdibleLog(int historyID) {
        ArrayList<Edible> edibleLog = new ArrayList<Edible>();
        try {
            PreparedStatement findLog = currConn.prepareStatement("GET EdibleID, CustomEdibleID FROM EdibleHistory, " +
                    "WHERE HistoryID = ?");
            ResultSet results;

            Edible currEdible;
            EdibleLog currLog;
            Edible.Unit unit;

            findLog.setInt(1, historyID);
            results = findLog.executeQuery();

            while (results.next()) {
                currEdible = this.readEdible(results, results.getInt("EdibleID") == 0);
                unit = this.findUnit(results.getString("Unit"));
                currLog = new EdibleLog(currEdible).init(results.getInt("Quantity"), unit);
                edibleLog.add(currLog);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getEdibleLog");
            System.exit(1);
        }

        return edibleLog;
    }

    private int getExerciseActual(int HistoryID) {
        int exerciseActual = 0;
        try {
            PreparedStatement getExerciseActual = currConn.prepareStatement("GET * FROM WorkoutHistory, WHERE HistoryID = ?");
            ResultSet results;

            getExerciseActual.setInt(1, HistoryID);
            results = getExerciseActual.executeQuery();

            if (results.next()) {
                exerciseActual = results.getInt("ExerciseActual");
            }
            results.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getExerciseActual");
            System.exit(1);
        }

        return exerciseActual;
    }

    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom) {
        EdibleLog edibleLog = null;
        try {
            PreparedStatement findEdibleByKey = currConn.prepareStatement("GET * FROM ?, WHERE EdibleID = ?");
            ResultSet results;

            if (isCustom) {
                findEdibleByKey.setString(1, "CustomEdibleID");
            } else {
                findEdibleByKey.setString(1, "EdibleID");
            }

            findEdibleByKey.setInt(2, dbkey);
            results = findEdibleByKey.executeQuery();
            edibleLog = new EdibleLog(this.readEdible(results, isCustom));
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("findEdibleByKey");
            System.exit(1);
        }

        return edibleLog;
    }

    private boolean isMeal(int dbkey, boolean isCustom) {
        boolean found = false;
        try {
            PreparedStatement checkIfMeal = currConn.prepareStatement("GET * FROM Meal, WHERE EdibleID = ?");
            PreparedStatement checkIfCustomMeal = currConn.prepareStatement("GET * FROM CustomMeal, WHERE CustomEdibleID = ?");
            found = false;
            ResultSet results;

            if (isCustom) {
                checkIfCustomMeal.setInt(1, dbkey);
                results = checkIfCustomMeal.executeQuery();
                found = results.next();
            } else {
                checkIfMeal.setInt(1, dbkey);
                results = checkIfMeal.executeQuery();
                found = results.next();
            }
            results.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("isMeal");
            System.exit(1);
        }

        return found;
    }

    private boolean isDrink(int dbkey, boolean isCustom) {
        boolean found = false;
        try {
            PreparedStatement checkIfDrink = currConn.prepareStatement("GET * FROM Drink, WHERE EdibleID = ?");
            PreparedStatement checkIfCustomDrink = currConn.prepareStatement("GET * FROM CustomDrink, WHERE CustomEdibleID = ?");
            ResultSet results;

            if (isCustom) {
                checkIfCustomDrink.setInt(1, dbkey);
                results = checkIfCustomDrink.executeQuery();
                found = results.next();
            } else {
                checkIfDrink.setInt(1, dbkey);
                results = checkIfDrink.executeQuery();
                found = results.next();
            }
            results.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("isDrink");
            System.exit(1);
        }

        return found;
    }

    public void addLog(DailyLog newLog, int userID) {
        try {
            PreparedStatement addHistory = currConn.prepareStatement("INSERT INTO History (UserID, Date, CalorieGoal, " +
                    "CalorieActual) VALUES (?, ?, ?, 0)");
            PreparedStatement addEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
                    "EdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");
            PreparedStatement addCustomEdibleHistory = currConn.prepareStatement("INSERT INTO EdibleHistory (HistoryID, " +
                    "CustomEdibleID, Quantity, Unit) VALUES (?, ?, ?, ?)");

            ArrayList<Edible> edibles = newLog.getEdibleList();
            Edible currEdible;
            ResultSet results;
            int historyID;

            addHistory.setInt(1, userID);
            addHistory.setString(2, this.convertDateToString(newLog.getDate()));
            addHistory.setDouble(3, newLog.getCalorieGoal());
            results = addHistory.executeQuery();
            historyID = results.getRow();
            results.close();

            for (int i = 0; i < edibles.size(); i++) {
                currEdible = edibles.get(i);

                if (currEdible.getIsCustom()) {
                    addCustomEdibleHistory.setInt(1, historyID);
                    addCustomEdibleHistory.setInt(2, currEdible.getDbkey());
                    addCustomEdibleHistory.setDouble(3, currEdible.getQuantity());
                    addCustomEdibleHistory.setString(4, currEdible.getUnit().toString());
                    addCustomEdibleHistory.executeQuery();
                } else {
                    addEdibleHistory.setInt(1, historyID);
                    addEdibleHistory.setInt(2, currEdible.getDbkey());
                    addEdibleHistory.setDouble(3, currEdible.getQuantity());
                    addEdibleHistory.setString(4, currEdible.getUnit().toString());
                    addEdibleHistory.executeQuery();
                }
            }

            if (newLog.getExerciseActual() != 0) {
                this.setExerciseActual(newLog.getExerciseActual(), newLog, userID);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addLog");
            System.exit(1);
        }
    }

    public void deleteLog(DailyLog delLog, int userID) {
        try {
            PreparedStatement setExerciseActual = currConn.prepareStatement("DELETE History WHERE Date = ? AND UserID = ?");

            setExerciseActual.setString(1, this.convertDateToString(delLog.getDate()));
            setExerciseActual.setInt(2, userID);
            setExerciseActual.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("deleteLog");
            System.exit(1);
        }
    }

    public void setExerciseActual(double newExercise, DailyLog currLog, int userID) {
        try {
            int logID = getHistoryID(currLog, userID);
            PreparedStatement setExerciseActual = currConn.prepareStatement("CASE " +
                    "WHEN COUNT(SELECT * FROM WorkoutHistory WHERE HistoryID = ?) = 0 THEN INSERT INTO WorkoutHistory VALUES (?, ?) " +
                    "ELSE UPDATE WorkoutHistory SET ExerciseActual = ?, WHERE HistoryID = ?");

            setExerciseActual.setInt(1, logID);
            setExerciseActual.setInt(2, logID);
            setExerciseActual.setDouble(3, newExercise);
            setExerciseActual.setDouble(4, newExercise);
            setExerciseActual.setInt(5, logID);
            setExerciseActual.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("setExerciseActual");
            System.exit(1);
        }
    }

    private int getHistoryID(DailyLog currLog, int userID) {
        int historyID = -1;
        try {
            PreparedStatement getHistoryID = currConn.prepareStatement("GET HistoryID FROM History WHERE UserID = ? AND Date = ?");
            ResultSet results;

            getHistoryID.setString(1, convertDateToString(currLog.getDate()));
            getHistoryID.setInt(1, userID);
            results = getHistoryID.executeQuery();
            historyID = results.getInt("HistoryID");
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getHistoryID");
            System.exit(1);
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
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("addUser");
            System.exit(1);
        }
    }

    public User getUser() {
        User currUser = null;
        try {
            PreparedStatement getUser = currConn.prepareStatement("GET * FROM User");
            ResultSet results = getUser.executeQuery();

            int userID, height, weight, calorieGoal, exerciseGoal;
            String name;

            currUser = new User(results.getInt("UserID"), results.getString("Name"), results.getInt("Height"),
                    results.getInt("Weight"), results.getInt("CalorieGoal"), results.getInt("ExerciseGoal"));
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getUser");
            System.exit(1);
        }

        return currUser;
    }

    public void setHeight(int userID, int newHeight) {
        try {
            PreparedStatement setHeight = currConn.prepareStatement("Update User SET Height = ?, WHERE UserID = ?");

            setHeight.setInt(1, newHeight);
            setHeight.setInt(2, userID);
            setHeight.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("setHeight");
            System.exit(1);
        }
    }

    public void setWeight(int userID, int newWeight) {
        try {
            PreparedStatement setWeight = currConn.prepareStatement("Update User SET Weight = ?, WHERE UserID = ?");

            setWeight.setInt(1, newWeight);
            setWeight.setInt(2, userID);
            setWeight.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("setWeight");
            System.exit(1);
        }
    }

    private String convertDateToString(Calendar date) {
        return date.get(date.YEAR) + "-" + date.get(date.MONTH) + "-" + date.get(date.DATE);
    }

    private Calendar convertStringToDate(String date) {
        Calendar calendarDate = Calendar.getInstance();
        String[] entries = date.split("-");

        calendarDate.set(Integer. parseInt(entries[0]), Integer. parseInt(entries[1]), Integer. parseInt(entries[2]));

        return calendarDate;
    }

    public void setCalorieGoal(int userID, double goal, Calendar date) {
        try {
            PreparedStatement setCalorieGoal = currConn.prepareStatement("UPDATE History SET CalorieGoal = ? " +
                    "WHERE UserID = ? AND Date >= ?");

            setCalorieGoal.setDouble(1, goal);
            setCalorieGoal.setInt(2, userID);
            setCalorieGoal.setString(2, this.convertDateToString(date));
            setCalorieGoal.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("setCalorieGoal");
            System.exit(1);
        }//cal.getTimeInMillis()
    }

    public void setExerciseGoal(int userID, double goal, Calendar date) {
        try {
            PreparedStatement setExerciseGoal = currConn.prepareStatement("UPDATE History SET ExerciseGoal = ? " +
                    "WHERE UserID = ? AND Date >= ?");

            setExerciseGoal.setDouble(1, goal);
            setExerciseGoal.setInt(2, userID);
            setExerciseGoal.setString(2, this.convertDateToString(date));
            setExerciseGoal.executeQuery();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("setExerciseGoal");
            System.exit(1);
        }
    }

}
//    private void seedDB() { //just do this easy in
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //Used to add data to each respective table
//        ContentValues edible = new ContentValues();
//        ContentValues customEdible = new ContentValues();
//        ContentValues user = new ContentValues();
//        ContentValues history = new ContentValues();
//        ContentValues workoutHistory = new ContentValues();
//        ContentValues edibleHistory = new ContentValues();
//        ContentValues food = new ContentValues();
//        ContentValues meal = new ContentValues();
//        ContentValues drink = new ContentValues();
//        ContentValues customFood = new ContentValues();
//        ContentValues customMeal = new ContentValues();
//        ContentValues ingredient = new ContentValues();
//        ContentValues drinkIngredient = new ContentValues();
//
//        //The saved ID's of seeded entries (for later use as foreign keys)
//        long pineappleID, mushroomID, eggID, walnutID, milkID, onionID, wannabePinaColadaID, scrambledEggsID;
//        long pickleID, scrambledEggsWithPickleID;
//        long userID;
//        long firstDayHistoryID, secondDayHistoryID;
//
//
//        //creates new edible entries then add them to the DB
//        edible.put("Name", "Pineapple");
//        edible.put("Quantity", 79);
//        edible.put("Unit", "oz");
//        edible.put("Calories", 10);
//        edible.put("Protein", 20);
//        edible.put("Carbs", 30);
//        edible.put("Fat", 30);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", true);
//        edible.put("IsVegetarian", true);
//        edible.put("IsGlutenFree", true);
//        pineappleID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Mushroom");
//        edible.put("Quantity", 20);
//        edible.put("Unit", "ml");
//        edible.put("Calories", 5);
//        edible.put("Protein", 200);
//        edible.put("Carbs", 31);
//        edible.put("Fat", 30);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", true);
//        mushroomID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Egg");
//        edible.put("Quantity", 9);
//        edible.put("Unit", "oz");
//        edible.put("Calories", 15);
//        edible.put("Protein", 10);
//        edible.put("Carbs", 10);
//        edible.put("Fat", 10);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        eggID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Walnut");
//        edible.put("Quantity", 93);
//        edible.put("Unit", "serving");
//        edible.put("Calories", 43);
//        edible.put("Protein", 2);
//        edible.put("Carbs", 2);
//        edible.put("Fat", 3);
//        edible.put("IsAlcoholic", true);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        walnutID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Milk");
//        edible.put("Quantity", 10);
//        edible.put("Unit", "cups");
//        edible.put("Calories", 40);
//        edible.put("Protein", 100);
//        edible.put("Carbs", 2);
//        edible.put("Fat", 3);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        milkID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Onion");
//        edible.put("Quantity", 10);
//        edible.put("Unit", "g");
//        edible.put("Calories", 2);
//        edible.put("Protein", 1);
//        edible.put("Carbs", 1);
//        edible.put("Fat", 1);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        onionID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Wannabe Pina-Colada");
//        edible.put("Quantity", 10);
//        edible.put("Unit", "g");
//        edible.put("Calories", 2);
//        edible.put("Protein", 1);
//        edible.put("Carbs", 1);
//        edible.put("Fat", 1);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        wannabePinaColadaID = db.insert("Edible", null, edible);
//
//        edible.put("Name", "Scrambled Eggs");
//        edible.put("Quantity", 10);
//        edible.put("Unit", "g");
//        edible.put("Calories", 20);
//        edible.put("Protein", 60);
//        edible.put("Carbs", 1);
//        edible.put("Fat", 1);
//        edible.put("IsAlcoholic", false);
//        edible.put("IsSpicy", false);
//        edible.put("IsVegan", false);
//        edible.put("IsVegetarian", false);
//        edible.put("IsGlutenFree", false);
//        scrambledEggsID = db.insert("Edible", null, edible);
//
//
//        //creates customEdibles then adds them to the DB
//        customEdible.put("Name", "Pickle");
//        customEdible.put("Quantity", 11);
//        customEdible.put("Unit", "g");
//        customEdible.put("Calories", 5);
//        customEdible.put("Protein", 5);
//        customEdible.put("Carbs", 5);
//        customEdible.put("Fat", 5);
//        customEdible.put("IsAlcoholic", false);
//        customEdible.put("IsSpicy", false);
//        customEdible.put("IsVegan", false);
//        customEdible.put("IsVegetarian", true);
//        customEdible.put("IsGlutenFree", true);
//        pickleID = db.insert("CustomEdible", null, customEdible);
//
//        customEdible.put("Name", "Scrambled Eggs With A Pickle");
//        customEdible.put("Quantity", 10);
//        customEdible.put("Unit", "g");
//        customEdible.put("Calories", 25);
//        customEdible.put("Protein", 65);
//        customEdible.put("Carbs", 1);
//        customEdible.put("Fat", 1);
//        customEdible.put("IsAlcoholic", false);
//        customEdible.put("IsSpicy", false);
//        customEdible.put("IsVegan", false);
//        customEdible.put("IsVegetarian", false);
//        customEdible.put("IsGlutenFree", false);
//        scrambledEggsWithPickleID = db.insert("CustomEdible", null, customEdible);
//
//
//        //creates new user then adds the user to the database
//        user.put("Name", "BestUser");
//        user.put("Height", 190);  //cm
//        user.put("Weight", 160);  //pounds
//        user.put("CalorieGoal", 4000);
//        user.put("ExerciseGoal", 100);
//        userID = db.insert("User", null, user);
//
//
//        //creates history for the newly added user and adds it to the database
//        history.put("UserID", userID);
//        history.put("Date", Calendar.getInstance().toString());
//        history.put("CalorieGoal", 5000);
//        history.put("CalorieActual", 6000);
//        firstDayHistoryID = db.insert("History", null, history);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 1); //incrase the day by 1
//
//        history.put("UserID", userID);
//        history.put("Date", calendar.getTime().toString());
//        history.put("CalorieGoal", 4000);
//        history.put("CalorieActual", -1000);
//        secondDayHistoryID = db.insert("History", null, history);
//
//
//        //creates workout history for the newly added user and adds it to the database
//        workoutHistory.put("HistoryID", firstDayHistoryID);
//        workoutHistory.put("ExerciseActual", 100);
//        db.insert("WorkoutHistory", null, workoutHistory);
//
//        workoutHistory.put("HistoryID", secondDayHistoryID);
//        workoutHistory.put("ExerciseActual", 1000);
//        db.insert("WorkoutHistory", null, workoutHistory);
//
//
//        //creates edible history for the newly added user and adds it to the database
//        edibleHistory.put("HistoryID", firstDayHistoryID);
//        edibleHistory.put("EdibleID", pineappleID);
//        edibleHistory.put("Quantity", 1);
//        edibleHistory.put("Unit", "cups");
//        db.insert("EdibleHistory", null, edibleHistory);
//
//        edibleHistory.put("HistoryID", firstDayHistoryID);
//        edibleHistory.put("EdibleID", walnutID);
//        edibleHistory.put("Quantity", 2);
//        edibleHistory.put("Unit", "serving");
//        db.insert("EdibleHistory", null, edibleHistory);
//
//        edibleHistory.put("HistoryID", firstDayHistoryID);
//        edibleHistory.put("EdibleID", wannabePinaColadaID);
//        edibleHistory.put("Quantity", 3);
//        edibleHistory.put("Unit", "serving");
//        db.insert("EdibleHistory", null, edibleHistory);
//
//        edibleHistory.put("HistoryID", firstDayHistoryID);
//        edibleHistory.put("EdibleID", scrambledEggsWithPickleID);
//        edibleHistory.put("Quantity", 10);
//        edibleHistory.put("Unit", "g");
//        db.insert("EdibleHistory", null, edibleHistory);
//
//
//        //fills food table out with already created edibles
//        food.put("EdibleID", pineappleID);
//        db.insert("Food", null, food);
//
//        food.put("EdibleID", mushroomID);
//        db.insert("Food", null, food);
//
//        food.put("EdibleID", eggID);
//        db.insert("Food", null, food);
//
//        food.put("EdibleID", walnutID);
//        db.insert("Food", null, food);
//
//        food.put("EdibleID", onionID);
//        db.insert("Food", null, food);
//
//
//        //fills meal table out with already created edibles
//        meal.put("EdibleID", scrambledEggsID);
//        meal.put("Instructions", "git guud");
//        db.insert("Meal", null, meal);
//
//
//        //fills drink table out with already created edibles
//        drink.put("EdibleID", wannabePinaColadaID);
//        drink.put("Instructions", "git guud");
//        db.insert("Drink", null, drink);
//
//
//        //fills custom food table out with already created edibles
//        customFood.put("EdibleID", pickleID);
//        db.insert("CustomFood", null, customFood);
//
//
//        //fills custom food table out with already created edibles
//        customMeal.put("EdibleID", scrambledEggsWithPickleID);
//        drink.put("Instructions", "this one is different!");
//        db.insert("CustomFood", null, customMeal);
//
//
//        //fills ingredient table out with already created edibles
//        ingredient.put("PreparedID", scrambledEggsID);
//        ingredient.put("EdibleID", eggID);
//        ingredient.put("Quantity", 5);
//        ingredient.put("Unit", "ml");
//        db.insert("Ingredient", null, ingredient);
//
//        ingredient.put("PreparedID", scrambledEggsID);
//        ingredient.put("EdibleID", onionID);
//        ingredient.put("Quantity", 5);
//        ingredient.put("Unit", "ml");
//        db.insert("Ingredient", null, ingredient);
//
//        ingredient = new ContentValues();
//        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
//        ingredient.put("EdibleID", eggID);
//        ingredient.put("Quantity", 5);
//        ingredient.put("Unit", "ml");
//        db.insert("Ingredient", null, ingredient);
//
//        ingredient = new ContentValues();
//        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
//        ingredient.put("EdibleID", onionID);
//        ingredient.put("Quantity", 5);
//        ingredient.put("Unit", "ml");
//        db.insert("Ingredient", null, ingredient);
//
//        ingredient = new ContentValues();
//        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
//        ingredient.put("CustomEdibleID", pickleID);
//        ingredient.put("Quantity", 5);
//        ingredient.put("Unit", "ml");
//        db.insert("Ingredient", null, ingredient);
//
//
//        //fills ingredient table out with already created edibles
//        drinkIngredient.put("PreparedID", wannabePinaColadaID);
//        drinkIngredient.put("EdibleID", milkID);
//        drinkIngredient.put("Quantity", 5);
//        drinkIngredient.put("Unit", "ml");
//        db.insert("DrinkIngredient", null, drinkIngredient);
//
//        drinkIngredient.put("PreparedID", wannabePinaColadaID);
//        drinkIngredient.put("EdibleID", pineappleID);
//        drinkIngredient.put("Quantity", 10);
//        drinkIngredient.put("Unit", "ml");
//        db.insert("DrinkIngredient", null, drinkIngredient);
//    }
//}