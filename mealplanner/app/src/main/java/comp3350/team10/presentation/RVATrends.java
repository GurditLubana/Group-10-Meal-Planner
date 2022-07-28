package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.DataFrame;

public class RVATrends extends RecyclerViewAdapter {
    ArrayList<DataFrame> dataSet;
    Context context;

    public RVATrends(ArrayList<DataFrame> dataSet) {
        super(null);
        this.dataSet = dataSet;
    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        ViewHolder viewHolder;

        this.context = viewGroup.getContext();
        if (context instanceof ActivityTrends) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_trend_chart, viewGroup, false);
        } else if (context instanceof ActivityDailyProgress) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_daily_progress, viewGroup, false);
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (context instanceof ActivityTrends) {
            setTrendData(viewHolder, position);
        } else if (context instanceof ActivityDailyProgress) {
            setDailyData(viewHolder, position);
        }
    }


    public int getItemCount() {
        return this.dataSet.size();
    }


    public int getItemViewType(int pos) {
        return 0;
    }

    public void changeDataSet(ArrayList<DataFrame> newData) {
        this.dataSet = newData;
        this.notifyDataSetChanged();
    }

    private void setTrendData(ViewHolder viewHolder, int position) {
        GraphView graph = viewHolder.getView().findViewById(R.id.graph);
        TextView title = viewHolder.getView().findViewById(R.id.trendsTitle);
        TextView span = viewHolder.getView().findViewById(R.id.trendsSpan);
        TextView valueFirst = viewHolder.getView().findViewById(R.id.trendsValueFirst);
        TextView valueLast = viewHolder.getView().findViewById(R.id.trendsValueLast);
        DataFrame dataFrame = this.dataSet.get(position);
        DataPoint[] dataPointArray = new DataPoint[dataFrame.size()];
        ArrayList<Double> dataArray = dataFrame.getData();
        double chartMin = -DataFrame.numDays[dataFrame.getSpan().ordinal()];

        for (int i = 0; i < dataArray.size(); i++) {
            dataPointArray[i] = new DataPoint(i - dataArray.size(), dataArray.get(i).doubleValue());
        }
        valueFirst.setText(Integer.toString(dataArray.get(0).intValue()));
        valueLast.setText(Integer.toString(dataArray.get(dataArray.size() - 1).intValue()));

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointArray);
        LineGraphSeries<DataPoint> seriesTrend = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(chartMin, dataFrame.getTrendPointA()),
                new DataPoint(0, dataFrame.getTrendPointB())
        });

        seriesTrend.setColor(Color.LTGRAY);
        graph.removeAllSeries();
        graph.addSeries(series);
        graph.addSeries(seriesTrend);
        graph.setTitle(dataFrame.getDataType().name());
        title.setText(dataFrame.getDataType().name());
        span.setText(dataFrame.getSpan().name());
        graph.setTitleTextSize(72);
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setGridColor(Color.GRAY);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(1);
        graph.getViewport().setMinX(chartMin);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(dataFrame.getMaxVal() + (dataFrame.getMaxVal() * 0.05));
        graph.getViewport().setMinY(dataFrame.getMinVal() - (dataFrame.getMaxVal() * 0.05));
    }

    private void setDailyData(ViewHolder viewHolder, int position) {
        GraphView graph = viewHolder.getView().findViewById(R.id.graph2);
        TextView progressPercent = viewHolder.getView().findViewById(R.id.progressPercentage);
        TextView title = viewHolder.getView().findViewById(R.id.dailyProgressTitle);
        TextView valueFirst = viewHolder.getView().findViewById(R.id.dailyProgressValueFirst);
        TextView valueLast = viewHolder.getView().findViewById(R.id.dailyProgressValueLast);
        CircularProgressIndicator progressCircle = viewHolder.getView().findViewById(R.id.progressCircle);
        viewHolder.getView().findViewById(R.id.graph);
        DataFrame dataFrame = this.dataSet.get(position);
        DataPoint[] dataPointArray = new DataPoint[dataFrame.size()];
        ArrayList<Double> dataArray = dataFrame.getData();
        double chartMin = -DataFrame.numDays[dataFrame.getSpan().ordinal()];
        int progress = (int) (dataFrame.getProgress() * 100);
        double goal = 0;

        for (int i = 0; i < dataArray.size(); i++) {
            dataPointArray[i] = new DataPoint(i - dataArray.size(), dataArray.get(i).doubleValue());
        }
        valueFirst.setText(Integer.toString(dataArray.get(0).intValue()));
        valueLast.setText(Integer.toString(dataArray.get(dataArray.size() - 1).intValue()));

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPointArray);
        if (dataFrame.getDataType().name().equals("ConsumedCalories")) {
            goal = 2100.0;
        } else if (dataFrame.getDataType().name().equals("ExerciseCalories")) {
            goal = 200.0;
        }
        LineGraphSeries<DataPoint> seriesTrend = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(chartMin - 1, goal),
                new DataPoint(1, goal)
        });

        series.setSpacing(5);
        series.setColor(Color.parseColor("#C3E0E5"));
        seriesTrend.setColor(Color.RED);
        graph.setTitle(dataFrame.getDataType().name());
        title.setText(dataFrame.getDataType().name());
        graph.setTitleTextSize(72);
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setGridColor(Color.GRAY);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(chartMin - 1);
        graph.addSeries(series);
        graph.addSeries(seriesTrend);

        progressCircle.setProgress(progress);
        progressPercent.setText(Integer.toString(progress) + "%");
    }
}
