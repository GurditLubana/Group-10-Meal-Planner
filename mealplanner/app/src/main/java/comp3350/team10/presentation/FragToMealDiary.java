package comp3350.team10.presentation;

public interface FragToMealDiary {
    public void showContextUI(int pos);
    public void selectDate();
    public void prevDate();
    public void nextDate();
    public void setGoal();
    public void removeItem(int pos);
    public void editItem(int pos);
    public void setExercise();
    public void addEntry(int pos);
}
