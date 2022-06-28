package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.*;

import java.util.ArrayList;
import java.util.List;

public class RVATrends extends RecyclerViewAdapter {
    ArrayList<DataFrame> dataSet = null;
    FragToTrends send;

    public RVATrends(ArrayList<DataFrame> dataSet) {
        super(null);
        this.dataSet = dataSet;
    }

//    public ArrayList<DataFrame> getDataSet() {
//        return this.dataSet;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewHolder viewHolder = null;
        Context context = null;
        View view = null;

//        switch (viewType) {
//            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_trend_chart, viewGroup, false);
//                break;
//        }

        context = view.getContext();
        if (context instanceof FragToTrends) {
            this.send = (FragToTrends) context;
        }

        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

//        switch (viewHolder.getItemViewType()) {
//            default:
                setChartData(viewHolder, position);
                //setCardListeners(viewHolder, position);
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    @Override
    public int getItemViewType(int pos) {
        return 0;
    }

    private void setCardListeners(ViewHolder viewHolder, int position) {
//        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sendToRecipeBook != null) {
//                    sendToRecipeBook.showContextUI(position);
//                }
//            }
//        });
    }

    private void setChartData(ViewHolder viewHolder, int position) {
        GraphView graph = (GraphView) viewHolder.getView().findViewById(R.id.graph);
        DataFrame dataFrame = this.dataSet.get(position);
        DataPoint[] dataPointArray = new DataPoint[dataFrame.size()];
        ArrayList<Double> dataArray = dataFrame.getData();
        for(int i =0; i < dataArray.size(); i++){
            dataPointArray[i] = new DataPoint(i, dataArray.get(i).doubleValue());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointArray);
        graph.addSeries(series);
    }

    private void setCardSelectionListeners(ViewHolder viewHolder, int position) {
//        Button viewButton = viewHolder.getView().findViewById(R.id.viewBtn2);
//        Button backButton = viewHolder.getView().findViewById(R.id.btnBackRecipe);
//        Button addButton = viewHolder.getView().findViewById(R.id.addToPlannerBtn2);
//        ImageView addIcon = viewHolder.getView().findViewById(R.id.addIcon);
//        String launcher = "";
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sendToRecipeBook != null) {
//                    sendToRecipeBook.addToMealDiary();
//                }
//            }
//        });
//
//        if (this.sendToRecipeBook != null) {
//
//            launcher = this.sendToRecipeBook.getIntentExtra("Source");
//            if (launcher != null && launcher.equals("NAV")) {
//                viewHolder.getView().findViewById(R.id.addToPlannerBtn2).setVisibility(View.GONE);
//                viewHolder.getView().findViewById(R.id.addIcon).setVisibility(View.GONE);
//            }
//        }
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = viewHolder.getAbsoluteAdapterPosition();
//
//                if (sendToRecipeBook != null) {
//                    sendToRecipeBook.showContextUI(position);
//                }
//            }
//        });
    }
}
