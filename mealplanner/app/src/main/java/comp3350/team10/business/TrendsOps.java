package comp3350.team10.business;

import java.util.ArrayList;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

public class TrendsOps {
    private ArrayList<DataFrame> dataFrames;
    private DataAccessStub db;
    private DataFrame.Span span;

    public TrendsOps(DataAccessStub db) throws NullPointerException{
        if (db != null){
            this.db = db;
        }
        else{
            throw new NullPointerException("TrendsOps Database cannot be null");
        }
    }

    public ArrayList<DataFrame> getDataFrames(DataFrame.Span span) throws NullPointerException{
        DataFrame dataFrame = null;
        if(span != null) {
            this.dataFrames = new ArrayList<DataFrame>();
            this.span = span;

            for(int i = 0; i < DataFrame.DataType.values().length; i++){

                dataFrame = getDataFromDB(DataFrame.DataType.values()[i]);

                if(dataFrame != null){
                    this.dataFrames.add(dataFrame);
                }
            }
        }
        else{
            throw new NullPointerException("TrendsOps getDataFrames Span cannot be null");
        }
        return this.dataFrames;
    }

    private DataFrame getDataFromDB(DataFrame.DataType dataType){
        return null;
    }
}
