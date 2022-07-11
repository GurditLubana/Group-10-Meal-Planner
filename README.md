# <center> Comp 3350 Team 10 <br> EATEN </center>

<center>

### Eaten is a meal planning app, that aims to help its users reach their health goals through curated recipes, automated calorie counting and progress tracking thats easy to read and share with your loved ones. 
<br>
</center>
<hr>



<h2>Contents</h2>

[Documentation](#team-10)
- [Github Repository](#repo-url)
- [Dev Log](#dev-log)
- [Feature List](#feature-list)
- [Testing Environments](#environments-used-in-testing)

[Packages](#packages)
- [Business](#business)
- [Objects](#objects)
- [Persistence](#persistence)
- [Presentation](#presentation)

[Patch notes](#patch-notes)
- [Iteration 2](#iteration-2)

<br>
<br>

# Team 10
## Group members
Dane Wanke - wanked@myumanitoba.ca  
Gurdit Singh- singhg78@myumanitoba.ca  
Joseffus Santos - umsant28@myumanitoba.ca  
Manraj Singh – singhm53@myumanitoba.ca  
Zhihou Zhou- zhouz2@myumanitoba.ca  

<br>

# Repo URL
https://github.com/DaneHarrison/Group-10-Meal-Planner

<br>

# Dev Log
The team dev log is kept as a markdown file in the github repository. The group has meetings to determine the project's direction, progress task priority, and dev task assignment. Tasks are selected on a volunteer basis to accommodate varying dev availability and to encourage flexibility. Length of meetings and discussions are also kept in this log.  

<br>

# Feature List

## Users can track their daily consumed food items, meals, and drinks   
This feature is on the initial app activity the meal diary screen. This screen can be accessed by clicking on the cutlery icon on the bottom navigation bar. Users can scroll up and down to view all the food items and the amount they are consuming for the day.  

## Users can set and modify the daily goal for their calorie intake   
This feature is on the meal diary screen. Users can set the number of calories they want to consume for the day by clicking the Budget text below the progress bar.  

## Users can add the number of calories burnt for the day  
This feature is on the meal diary screen. Users can add an estimate of the calories they burn in a day by clicking the Exercise text below the progress bar.  

## Users can see the total amount of calories consumed on a single day  
This feature is on the meal diary screen. Users can see at a glance the total calories from food numerically and as a graphic progress relative o the goal.  

## Users can see instant progress updates  
This feature is on the meal diary screen. Users can instantly see graphically and numerically on the progress bar the effect of any changes to the meal entries, the actual exercise calories and the calorie goal.   

## Users can add new entries
This feature is on the meal diary screen. Users can add new meal entries from a food, drink and meal database by clicking the green plus button at the bottom of their daily log. This launches a screen which allows the user to select a list of food, meals, or drinks. The user is shown a selection of items with images, names and calorie count for each item. The user can then click on the item cards to show a context UI and can add the selected item by clicking the green plus button.    

## Users can modify meal diary entries
This feature is on the meal diary screen. Users can can click on any entry in the meal diary to show a context UI. The user can click on the red trash can to delete an entry or the pencil to modify the quantity of an entry.  Modification allows users to alter the log so that it reflects the actual consumption of the user.  

## Users can view previous day diaries and plan for future consumption
This feature is on the meal diary screen. Users can click on arrows on the top center of the screen above the progress bar date traversal 1 day at a time. Users can click on the date to show a date selector for more efficient selection of further out dates.   

## Users can browse food, drink and meal databases  
This feature is a separate screen the recipe book. This screen can be accessed by clicking on the book icon on the bottom navigation bar. Users are presented a scrolling list of cards with item images, names, and calories. Users can switch between lists by selecting the desired tab in the middle of the screen.  

## Users can add new food, drink, or meal items to the database 
This feature can be accessed from the recipe book. Users can click the floating action button then the plus icon to show a new item entry screen. users can add food by selecting the food tab then the action button. Similarly users can click the meal tab or the drinks tab then the action button to add items to those lists. Users can enter food details and add the item using the OK button. The user is notified visually if input does not meet the app requirements. 

## Users can flag new recipes for pre defined categories and dietary restrictions
This feature can be accessed from the recipe book when adding a new recipe. Users can select up to 5 flags to further describe the recipe they are adding. Flags consist of alcoholic, spicy, vegan, vegetarian and gluten free

## Users can see goal progress for the week 
This feature can be accessed from the daily progress activity screen. User's can scroll through 4 different bar charts corresponding to their calorie intake, net calories, burnt calories and weight. This can be accessed via the leftmost nav button. 

## Users can see long term trend charts of their goal progress with trendlines 
This feature is in the trends activity screen. User's can scroll through 4 different graphs corresponding to their calorie intake, net calories, burnt calories and weight. Results have the option to span the previous week, month, last 3 months, last 6 months and year. This can be accessed via the fourth nav button from the left. 

## User data is persistent between startups 
This can be enabled in the DBSelector class by commenting the appropriate lines in the constructor lines 23-28

<br>

# Environments Used in Testing

Our team used Android Studio Chipmunk 2021.2.1 Patch 1 for development on Windows 10. We tested our code both on a Nexus 7 device and several Android virtual devices created in Android studio. Android virtual devices used had SDK 23 for testing and SDK 30 for debugging.  

<br>
<br>

# Packages
## Application
Application classes handle program maintenance through the course of its life cycle

<br>

<h3>Main</h3>
Main handles database and device related maintenance and path handling

<br>

#
## Business
Business classes handle program logic related to their activities. 

<br>

<h3>MealDiaryOps</h3>
The meal diary ops class handles logic and operations related to the main app activity; logging users meals. This class handles meal log requests and updates to and from persistence and date manipulation to display the uesr's history for a given date they'd like to view.

<br>

<h3>RecipeBookOps</h3>
The recipe book ops handles logic and operations related to the recipe book and curated recipe functionality. This class handles the retrieval and storage of food, drink, and meal recipeto and from persistence classes. 

<br>

<h3>TrendsOps</h3>
The trend ops handles the retrieval of a user's history to create meaningful and informative graphs

<br>

<h3>UserDataOps</h3>
The user data ops handles logic and operations related to the active user.  This class handles the retrieval, storage and updating of a user's personal data. In future updates, it will also detect when vital information is missing thus prompting the user to enter their information.

<br>


#
## Objects  
Object classes represent the meal planner's well defined entities that are known and shared by Business, Presentation, and Persistence.    

<br>

<h3>Edible</h3>
This is the base class at the top of the edible heirarchy that defines essential attributes and functionality all edible items should have.  

<br>

<h3>PreparedEdible</h3>
Inside of the edible hierarchy, below edible, this abstract class defines additional properties and functionality more complex Edibles require.

<br>

<h3>Drink</h3>
Inside of the edible hierarchy, below prepared edible, this class defines essential attributes and functionality for all drinks, with or without ingredients

<br>

<h3>Meal</h3>
Inside of the edible hierarchy, below prepared edible, this class defines essential attributes and functionality for all meals

<br>

<h3>EdibleLog</h3>
Inside of the edible hierarchy, below edible, this class defines essential attributes and functionality for all edibles that appear in the edible log such as maintaining their calories with respect to the original edibles base measurements.

<br>

<h3>DailyLog</h3>
This class defines essential attributes and functionality for each instance of a user's diary history

<br>

<h3>Ingredient</h3>
This class defines essential attributes and functionality for all edibles that appear as an ingredient in a meal

<br>

<h3>DrinkIngredient</h3>
This class defines essential attributes and functionality for all edibles that appear as an ingredient in a drink. In future updates, this class will be imperative for the redistributing of ingredients when its alcohol contents are modified

<br>

<h3>Constant</h3>
This class is responsible for holding relevant constants throughout the codebase   

<br>

<h3>User</h3>
This class defines essential attributes and functionality for all individual users

<br>

<h3>DataFrame</h3>
This class defines the collection of data requested by the buisness class trendsops. Dataframe provides summary statistics of the data contained within

<br>

<h3>UnitConverter</h3>
This class handles calorie conversions given base measurements of an edible and a seperate set of measurements to convert to.   

<br>

#
## Persistence
Persistence classes interact directly with databases through buisness classes and use well defined interfaces to retrieve and store information.  

<br>

<h3>LogDBInterface</h3>
An interface that defines database operations in relation to the logs and a user's history

<br>

<h3>UserDBInterface</h3>
An interface that defines database operations in relation to its users data

<br>

<h3>RecipeDBInterface</h3>
An interface that defines database operations in relation to recipes

<br>

<h3>SharedDB</h3>
This is a static class that allows the sharing of the active database accross all buisness classes

<br>

<h3>DataAccessStub</h3>
This class provides methods that allow the retrieval and storage of data into a in-memory based database (does not persist through restarts).    

<br>

<h3>HSQLDB</h3>
This class provides methods that allow the retrieval and storage of data into a HSQL database (persists through restarts). It is worth mentioning, most tables have a "custom" counterpart.  This will be more relevant in future updates to accomodate a user's desire to add their own recipes and acts as a seperation between the provided curated recipes and the recipes users may add. 

<br>

<h3>Selector</h3>
This class provides easy toggleability between the data access stub and HSQL DB using dependency injection (see lines 24 and 25 in DBSelector.java - whichever line is uncommented is the active database and handles all processes)

<br>

#
## Presentation
Presentation classes handle all UI activities. Drawing new elements on the screen, displaying information, accepting user input, handling user gestures, etc.  

<br>

<h3>ActivityMealDiary</h3>
This class handles the UI for the meal diary activity. This is the user interface for logging food consumed. This activity shows a users daily progress at glance and allows the user add their days exercise activity.  

<br>

<h3>ActivityRecipeBook</h3>
This class handles the UI for the recipe book. This is the user interface to view the availible food, meal and drink recipes. In future updates, user's will be able to select one of these recipes to view more in-depth details and add their own custom recipes.

<br>

<h3>ActivityDailyProgress</h3>
This class handles the UI for the daily progress. This is the user interface to view the week's current progress through various scrollable bar charts.

<br>

<h3>ActivityTrends</h3>
This class handles the UI for the user trends. This is the user interface to view the user's progress and history trends through various scrollable graphs over a user selected time span.

<br>

<h3>FragToMealDiary</h3>
This is an interface of methods that allow fragments that comprise the meal diary to send and recieve infromation from the parent activity.    

<br>

<h3>FragToRecipeBook</h3>
This is an interface of methods that allow fragments that comprise the recipe book to send and recieve infromation from the parent activity.  

<br>

<h3>RVAMealDiary</h3>
This is a recycler view adapter for the meal diary activity. This adapter handles the display of different fragment layouts in a recycler view.  

<br>

<h3>RVARecipeBook</h3>
This is a recycler view adapter for the recipe book activity. This adapter handles the display of different fragment layouts in a recycler view.  

<br>

<h3>MealDiaryLiveData</h3>
This is a class that allows the use of the observer design pattern in Android Activities. UI elements can be set to observe this class for changes and allow all observers to update their own elements when this class is updated.  

<br>

<h3>Fragments</h3>
Fragments are reusable UI components that perform specific functions in activities. The most important example is the navigation fragment which is shared across all activities.  

<br>
<br>

# Patch notes
## Iteration 2
### Major changes:
- All objects have been overhauled
- Moved around logic and processing to comply with seperation of concern
- Took persistance out of the presentation layers
- Created around 500 new tests (based on objects buisness and persistance)
- Trends and Daily progress are now functional (1st and 4th tabs)
- Edibles in a log can now be modified without side effects (only affects the current edibleLog)

### Unresolved issues:
- Code base is not up to standards in terms of the law of demeter
- Adding images to new ingredients is not set up properly yet
- Ingredients added to new meals and drinks are patched (not proper data)
- UserDataOps is not entirely necessary right now because we did not get to implementing the logic to prompt user's for their data if it does not exist in the app