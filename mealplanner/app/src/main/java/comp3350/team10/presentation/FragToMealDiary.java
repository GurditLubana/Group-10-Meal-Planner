package comp3350.team10.presentation;

public interface FragToMealDiary {
    public void showContextUI(int pos);
    public void selectDate();
    public void prevDate();
    public void nextDate();
    public void showGoalEntryDialog();
    public void removeItem(int pos);
    public void editItem(int pos);
    public void showExerciseEntryDialog();
    public void addEntry(int pos);
}
