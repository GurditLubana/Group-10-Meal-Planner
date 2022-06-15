package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;

public class RVARecipeBook extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    private LinkedList<Edible> localDataSet;          // the list Recyclerview renders
    private int selectedPos = RecyclerView.NO_POSITION; // tracks the last clicked item
    private FragToRecipeBook sendToRecipeBook;          // interface to pass data to recipebook
    private Edible saved;                             // var to save a meal entry when we show context UI

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
            // Define click listener for the ViewHolder's View
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
    public RVARecipeBook(LinkedList<Edible> dataSet) {
        localDataSet = dataSet;
    }

    public void changeData(LinkedList<Edible> newData) {
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
            case 4:    //add thing to make it show this card/buttons thingy add enum
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card_context, viewGroup, false);
                break;
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card, viewGroup, false);
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

        switch (viewHolder.getItemViewType()) {
            case 4:
                setCardSelectionListeners(viewHolder, position);
                break;
            default:
                setRecipeData(viewHolder, position);    //this is what puts information into dbl cards
                setCardListeners(viewHolder, position);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //modify card type to be of type card select
    private void setCardListeners(ViewHolder viewHolder, int position) {
        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //localDataSet.get(position)
                Context context = view.getContext();

                //System.out.println("...");
                if (context != null) {
                    sendToRecipeBook = (FragToRecipeBook) context;
                    sendToRecipeBook.showContextUI(position);
                }
            }
        });
    }

    private void setRecipeData(ViewHolder viewHolder, int position){
        ImageView itemImage = (ImageView) viewHolder.getView().findViewById(R.id.mealImage);
        TextView textDesc = (TextView) viewHolder.getView().findViewById(R.id.mealDesc);
        TextView mealCalories = (TextView) viewHolder.getView().findViewById(R.id.mealCals);

        Edible currentFood = (Edible) localDataSet.get(position);

        itemImage.setImageResource(currentFood.getIconPath());
        textDesc.setText(currentFood.getName());
        mealCalories.setText(Integer.toString(currentFood.getCalories()));
    }
    private void setCardSelectionListeners(ViewHolder viewHolder, int position) {
        Button viewButton = (Button) viewHolder.getView().findViewById(R.id.viewBtn2);
        Button addButton = (Button) viewHolder.getView().findViewById(R.id.addToPlannerBtn2);
        Button backButton = (Button) viewHolder.getView().findViewById(R.id.btnBackRecipe);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                if (context != null) {
                    sendToRecipeBook = (FragToRecipeBook) context;
                    sendToRecipeBook.addToMealDiary();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToRecipeBook = (FragToRecipeBook) context;
                    sendToRecipeBook.showContextUI(selectedPos);
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //need to pass tab information, and edible
//                Intent intent = new Intent(view.getContext(), ActivityViewEdible.class);
//                String currActivityName = view.getContext().getClass().getName();
//
//                intent.putExtra(ActivityViewEdible.RETURN_ACTIVITY_NAME, currActivityName);
//                //intent.putExtra("edibleItem", (Parcelable)localDataSet.get(position));
//
//                view.getContext().startActivity(intent);
            }
        });
    }
}
