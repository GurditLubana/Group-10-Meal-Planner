package comp3350.team10.business;

import java.util.ArrayList;

import comp3350.team10.objects.DataFrame;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.LogDBInterface;

public class TrendsOps {
    private LogDBInterface db;
    private DataFrame.Span span;

    public TrendsOps() throws NullPointerException {
        if (DBSelector.getSharedDB() != null) {
            this.db = DBSelector.getLogDB();
        } else {
            throw new NullPointerException("TrendsOps Database cannot be null");
        }
    }


    public ArrayList<DataFrame> getDataFrames(DataFrame.Span span) throws NullPointerException {
        ArrayList<DataFrame> dataFrames;
        DataFrame dataFrame = null;

        if (span != null) {
            dataFrames = new ArrayList<DataFrame>();
            this.span = span;

            for (int i = 0; i < DataFrame.DataType.values().length; i++) {

                dataFrame = getDataFromDB(DataFrame.DataType.values()[i]);

                if (dataFrame != null) {
                    dataFrames.add(dataFrame);
                }
            }
        } else {
            throw new NullPointerException("TrendsOps getDataFrames Span cannot be null");
        }
        return dataFrames;
    }

    private DataFrame getDataFromDB(DataFrame.DataType dataType) {
        DataFrame dataFrame = new DataFrame(dataType, this.span);
        dataFrame.setData(this.db.getDataFrame(dataType, DataFrame.numDays[this.span.ordinal()]));

        return dataFrame;
    }

}
