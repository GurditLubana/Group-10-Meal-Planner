package comp3350.team10.presentation;

import comp3350.team10.objects.Edible;

public interface FragToMealDiary {
    public enum EntryMode {GOAL_CALORIE, GOAL_EXERCISE, ACTUAL_EXERCISE, EDIT_QTY}

    public void showContextUI(int pos);

    public void selectDate();

    public void prevDate();

    public void nextDate();

    public void showGoalEntryDialog();

    public void removeItem(int pos);

    public void editItem();

    public void showExerciseEntryDialog();

    public void addEntry(int pos);

    public String getEntryQty();

    public Edible.Unit getEntryUnit();

    public void setEntryQty(Double amount, String unit);

    public String getExerciseCalories();

    public void setExerciseCalories(Double value);

    public String getGoalCalories();

    public void setGoalCalories(Double value);

    public EntryMode getEntryMode();
}
