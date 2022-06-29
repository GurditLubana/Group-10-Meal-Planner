package comp3350.team10.persistence;

import comp3350.team10.objects.DataFrame;

public interface TrendsDBInterface {

    public DataFrame getData(DataFrame.DataType dataType, DataFrame.Span span);
}
