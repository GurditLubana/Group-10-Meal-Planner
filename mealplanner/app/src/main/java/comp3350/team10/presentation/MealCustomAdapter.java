package comp3350.team10.presentation;
import comp3350.team10.objects.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import comp3350.team10.R;
import comp3350.team10.objects.DiaryItem;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MealCustomAdapter extends RecyclerView.Adapter<MealCustomAdapter.ViewHolder> {
    private LinkedList<ListItem> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final FragmentContainerView fragmentView;
        private final CardView fragmentView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //fragmentView = (FragmentContainerView) view.findViewById(R.id.btnMealLog);
            fragmentView = (CardView) view.findViewById(R.id.btnMealLog);
        }

        //public FragmentContainerView getFragmentView() {
        public CardView getView() {
            return fragmentView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MealCustomAdapter(LinkedList<ListItem> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_log_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.getFragmentView().setText(localDataSet[position]);
        viewHolder.getView();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return localDataSet.length;
        return 8;
    }
}
