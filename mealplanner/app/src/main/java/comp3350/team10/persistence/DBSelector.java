package comp3350.team10.persistence;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;

public class DBSelector {
    private HSqlDB hsql;                //An active instance of the HSQL database
    private DataAccessStub stub;        //An active instance of the DataAccessStub database

    private UserDBInterface userDB;     //The database we would like to process user operations on
    private DiaryDBInterface diaryDB;   //The database we would like to process diary operations on
    private RecipeDBInterface recipeDB; //The database we would like to process recipe operations on
    

    DBSelector(Context context) { //Creates both databases then points all interfaces towards hsql
        this.hsql = new HSqlDB(context);
        this.stub = new DataAccessStub("stub");

        this.userDB = this.hsql;
        this.diaryDB = this.hsql;
        this.recipeDB = this.hsql;
    }


    void moveToStubDB() { //Point all interfaces towards the Stub
        this.userDB = this.stub;
        this.diaryDB = this.stub;
        this.recipeDB = this.stub;
    }
}
