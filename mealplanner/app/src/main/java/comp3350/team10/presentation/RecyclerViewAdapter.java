package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;


import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    public enum FragmentType {
        noType, diaryAdd, diaryModify, recipeModify
    };

    private ArrayList<Edible> localDataSet; // the list Recyclerview renders
    private int viewType;

    @Override
    public int getItemViewType(int pos) {
        int result = -1;
        String identifier = localDataSet.get(pos).getName();
        if ( identifier.equals(FragmentType.diaryAdd.name()) ) {
            result = FragmentType.diaryAdd.ordinal();
        } else if ( identifier.equals(FragmentType.diaryModify.name()) ) {
            result = FragmentType.diaryModify.ordinal();
        } else if ( identifier.equals(FragmentType.recipeModify.name()) ){
            result = FragmentType.recipeModify.ordinal();
        } else {
            result = FragmentType.noType.ordinal();
        }
        this.viewType = result;
        return result;
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

    public ArrayList<Edible> getDataSet() {
        return this.localDataSet;
    }

    public int getViewType(){
        return this.viewType;
    }
}
