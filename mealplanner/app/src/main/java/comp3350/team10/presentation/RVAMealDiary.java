package comp3350.team10.presentation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;

public class RVAMealDiary extends RecyclerView.Adapter<RVAMealDiary.ViewHolder> {
    private LinkedList<ListItem> localDataSet;          // the list Recyclerview renders
    private int selectedPos = RecyclerView.NO_POSITION; // tracks the last clicked item
    private FragToMealDiary sendToMealDiary;            // interface to pass data to mealdiary
    private ListItem saved;                             // var to save a meal entry when we show context UI

    /**
     * getItemViewType
     * get the layout/fragment we want to use for a particular list item
     * required to show different layouts inside recyclerview
     * @param pos - position of data in the dataset
     * @return int - the numeric value of the Fragment type enum of the ListItem class
     */
    @Override
    public int getItemViewType(int pos) {
        return localDataSet.get(pos).getFragmentType().ordinal();
    }

    /**
     * TODO maybe we want a generic view inside every fragment frame layout so it's less awkward
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
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

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet LinkedList<ListItem> containing the data to populate views to be used
     *                by RecyclerView.
     */
    public RVAMealDiary(LinkedList<ListItem> dataSet) {
        localDataSet = dataSet;
    }

    public void changeData(LinkedList<ListItem> newData) {
        localDataSet = newData;
        this.notifyDataSetChanged();
    }

    /**
     * Method where we assign the layout of a ViewHolder being created based on the Fragment Type enum
     * assigned to the ListItem object being "rendered"
     * @param viewGroup
     * @param viewType - the int returned by getItemViewType(int)
     * @return ViewHolder
     */
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view;
        ViewHolder viewHolder;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_log_card, viewGroup, false);
                break;
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_modify_log, viewGroup, false);
                break;
            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
                break;
            default:
                view = null;
                break;
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Here we set the data of a fragment being created and set onclick listeners based on the Fragment Type enum
     * assigned to the ListItem object being "rendered"
     * @param viewHolder
     * @param position
     */
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        switch (viewHolder.getItemViewType()) {
            case 0:
                setDiaryEntryData(viewHolder, position);
                setDiaryEntryListeners(viewHolder);
                break;
            case 1:
                setDiaryContextListeners(viewHolder);
                break;
            case 2:
                setDiaryAddListeners(viewHolder);
                break;
            default:
                //view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //set the data of a meal entry fragment
    private void setDiaryEntryData(ViewHolder viewHolder, final int position){
        TextView itemName = (TextView) viewHolder.getView().findViewById(R.id.itemNameBox);
        TextView itemQty = (TextView) viewHolder.getView().findViewById(R.id.itemQtyBox);
        TextView itemUnit = (TextView) viewHolder.getView().findViewById(R.id.itemUnitBox);
        TextView itemCals = (TextView) viewHolder.getView().findViewById(R.id.itemCalsBox);
        ImageView itemImage = (ImageView) viewHolder.getView().findViewById(R.id.itemImage);

        Edible currentItem = (Edible) localDataSet.get(position);
        itemName.setText(currentItem.getName()); //had two take two lines below out to make it work
        itemQty.setText(String.format("%3d", currentItem.getQuantity()));
        itemUnit.setText(currentItem.getBaseUnit().name());
        itemCals.setText(String.format("%3d", currentItem.getCalories()));
        itemImage.setImageResource(currentItem.getIconPath());
    }

    //meal log card fragment click listeners
    private void setDiaryEntryListeners(ViewHolder viewHolder){
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.showContextUI(selectedPos);
                }
            }
        });
    }

    //modify meal fragment click listeners
    private void setDiaryContextListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnBackMealLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.showContextUI(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnDeleteMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.removeItem(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnModifyMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.editItem(selectedPos);
                }
            }
        });
    }

    //add meal entry fragment click listener
    private void setDiaryAddListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnAddMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.addEntry(selectedPos);
                }
            }
        });
    }

}
