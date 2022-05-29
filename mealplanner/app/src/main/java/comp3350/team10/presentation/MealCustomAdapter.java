package comp3350.team10.presentation;

import comp3350.team10.objects.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import comp3350.team10.R;
import comp3350.team10.objects.DiaryItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.objects.*;

import java.util.LinkedList;

public class MealCustomAdapter extends RecyclerView.Adapter<MealCustomAdapter.ViewHolder> {
    private LinkedList<ListItem> localDataSet;
    private int selectedPos = RecyclerView.NO_POSITION;
    private FragToParent parentComm;
    private ListItem saved;

    @Override
    public int getItemViewType(int pos) {
        return localDataSet.get(pos).getFragmentType().ordinal();
        //return 0;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final FragmentContainerView fragmentView;
        //private final CardView fragmentView;
        private final FrameLayout fragmentView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //fragmentView = (FragmentContainerView) view.findViewById(R.id.btnMealLog);
            //fragmentView = (CardView) view.findViewById(R.id.btnMealLog);
            fragmentView = (FrameLayout) view.findViewById(R.id.frame_container);


        }

        //public FragmentContainerView getFragmentView() {
        //public CardView getView() {
        public FrameLayout getView() {
            return fragmentView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public MealCustomAdapter(LinkedList<ListItem> dataSet) {
        localDataSet = dataSet;
    }

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
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.getFragmentView().setText(localDataSet[position]);
        //viewHolder.getView();
        //viewHolder.getView().setId(View.generateViewId());

        if (viewHolder.getItemViewType() == 0) {
            ((TextView) viewHolder.getView().findViewById(R.id.itemNameBox)).setText(((DiaryItem) localDataSet.get(position)).getName());
            ((TextView) viewHolder.getView().findViewById(R.id.itemQtyBox)).setText(String.format("%3d", ((DiaryItem) localDataSet.get(position)).getQuantity()));
            ((TextView) viewHolder.getView().findViewById(R.id.itemUnitBox)).setText(((DiaryItem) localDataSet.get(position)).getUnit().name());
            ((TextView) viewHolder.getView().findViewById(R.id.itemCalsBox)).setText(String.format("%3d", ((DiaryItem) localDataSet.get(position)).getCalories()));
        }

        switch (viewHolder.getItemViewType()) {
            case 0:
                setDiaryEntryListeners(viewHolder);
                break;
            case 1:
                setDiaryContextListeners(viewHolder);
                break;
            case 2:
                setDiaryAddListeners(viewHolder);
            default:
                //view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return localDataSet.length;
        //return 8;
        return localDataSet.size();
    }

    private void setDiaryEntryListeners(ViewHolder viewHolder){
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                //selectedPos = position;
                Context context = view.getContext();

                if (context != null) {
                    parentComm = (FragToParent) context;
                    parentComm.showContextUI(selectedPos);
                }

            }
        });
    }

    private void setDiaryContextListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnBackMealLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    parentComm = (FragToParent) context;
                    parentComm.showContextUI(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnDeleteMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    //parentComm = (FragToParent) context;
                    //parentComm.showContextUI(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnModifyMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    //parentComm = (FragToParent) context;
                    //parentComm.showContextUI(selectedPos);
                }
            }
        });
    }

    private void setDiaryAddListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnAddMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    //parentComm = (FragToParent) context;
                    //parentComm.showContextUI(selectedPos);
                }
            }
        });
    }
}
