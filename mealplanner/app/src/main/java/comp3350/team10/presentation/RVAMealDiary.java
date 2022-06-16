package comp3350.team10.presentation;

import android.content.Context;
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
    private LinkedList<Edible> localDataSet;            // the list Recyclerview renders
    private int selectedPos = RecyclerView.NO_POSITION; // tracks the last clicked item
    private FragToMealDiary sendToMealDiary;            // interface to pass data to mealdiary
    private ListItem saved;                             // var to save a meal entry when we show context UI

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

    public RVAMealDiary(LinkedList<Edible> dataSet) {
        this.localDataSet = dataSet;
    }

    public void changeData(LinkedList<Edible> newData) {
        this.localDataSet = newData;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        ViewHolder viewHolder;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card, viewGroup, false);
                break;
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card_context, viewGroup, false);
                break;
            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_add_log, viewGroup, false);
                break;
            default:
                view = null;
                break;
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

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
        }
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }

    private void setDiaryEntryData(ViewHolder viewHolder, final int position) {
        TextView itemName = (TextView) viewHolder.getView().findViewById(R.id.itemNameBox);
        TextView itemQty = (TextView) viewHolder.getView().findViewById(R.id.itemQtyBox);
        TextView itemUnit = (TextView) viewHolder.getView().findViewById(R.id.itemUnitBox);
        TextView itemCals = (TextView) viewHolder.getView().findViewById(R.id.itemCalsBox);
        ImageView itemImage = (ImageView) viewHolder.getView().findViewById(R.id.itemImage);

        Edible currentItem = localDataSet.get(position);
        itemName.setText(currentItem.getName()); //had two take two lines below out to make it work
        itemQty.setText(String.format("%3d", currentItem.getQuantity()));
        itemUnit.setText(currentItem.getBaseUnit().name());
        itemCals.setText(String.format("%3d", currentItem.getCalories()));
        itemImage.setImageResource(currentItem.getIconPath());
    }


    private void setDiaryEntryListeners(ViewHolder viewHolder) {
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

    private void setDiaryContextListeners(ViewHolder viewHolder) {
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
                    sendToMealDiary.editItem();
                }
            }
        });
    }

    private void setDiaryAddListeners(ViewHolder viewHolder) {
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
