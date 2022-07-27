package comp3350.team10.utils;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;


public class Utils {

    public static Matcher<View> matchesDate(final int year, final int month, final int day) {
        return new BoundedMatcher<View, DatePicker>(DatePicker.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("matches date:");
            }

            @Override
            protected boolean matchesSafely(DatePicker item) {
                return (year == item.getYear() && month == item.getMonth() && day == item.getDayOfMonth());
            }
        };
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) { // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    @NonNull
    public static ViewAction selectTabAtPosition(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }

            @Override
            public String getDescription() {
                return "with tab at index" + String.valueOf(position);
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabLayout = (TabLayout) view;
                    TabLayout.Tab tab = tabLayout.getTabAt(position);

                    if (tab != null) {
                        tab.select();
                    }
                }
            }
        };
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child) && child.getVisibility() == View.VISIBLE) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
