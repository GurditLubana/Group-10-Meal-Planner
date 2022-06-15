package comp3350.team10.objects;

public interface ListItem {
    public static enum FragmentType {diaryEntry, diaryModify, diaryAdd, recipe, cardSelection, noType}

    ;

    public FragmentType getFragmentType();

    public static boolean validFragEnum(FragmentType fragName) {
        boolean results = false;

        for (FragmentType curr : FragmentType.values()) {
            if (curr.equals(fragName))
                results = true;
        }

        return results;
    }
}
