package comp3350.team10.objects;

public interface ListItem {
    public static enum FragmentType {diaryEntry, diaryModify, diaryAdd, recipe, cardSelection, noType}; //Valid view types

    public FragmentType getFragmentType();
}
