package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem;

import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    private ArrayList<Edible> localDataSet;            // the list Recyclerview renders
    private Edible saved;                               // var to save a meal entry when we show context UI

    @Override
    public int getItemViewType(int pos) {
        return localDataSet.get(pos).getFragmentType().ordinal();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout fragmentView;

        public ViewHolder(View view) {
            super(view);

            fragmentView = (FrameLayout) view.findViewById(R.id.frame_container);
        }

        public FrameLayout getView() {
            return fragmentView;
        }
    }

    public RecyclerViewAdapter(ArrayList<Edible> dataSet) {
        this.localDataSet = dataSet;
    }

    public void changeData(ArrayList<Edible> newData) {
        this.localDataSet = newData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }

    public ArrayList<Edible> getDataset() {
        return this.localDataSet;
    }
}
