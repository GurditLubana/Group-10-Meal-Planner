# Comp 3350 Team 10

# Contents
[Team 10](#group-members)
- [Github Repository](#repo-url)
- [Dev Log](#dev-log)
- [Feature List](#feature-list)
- [Testing Environments](#environments-used-in-testing)

[Packages](#packages)
- [Business](#business)
- [Objects](#objects)
- [Persistence](#persistence)
- [Presentation](#presentation)

# Team 10
## Group members
Dane Wanke - wanked@myumanitoba.ca  
Gurdit Singh- singhg78@myumanitoba.ca  
Joseffus Santos - umsant28@myumanitoba.ca  
Manraj Singh – singhm53@myumanitoba.ca  
Zhihou Zhou- zhouz2@myumanitoba.ca  

# Repo URL
https://github.com/DaneHarrison/Group-10-Meal-Planner

# Dev Log
The team dev log is kept as an md file in the github repository. The group has meetings to determine dev tasks and  
tasks are selected on a volunteer basis to accommodate varying dev availabilities and to allow the group to be  
flexible in the completion of tasks with interdependencies. Minutes of meeting discussions and decisions are kept   
in this log.  
# Packages
## Business
Business classes handle program logic and calculations related to their activities.  
### Meal Diary Ops
The meal diary ops class handles logic and operations related to the main app activity which is the  
logging of a users meals. This class handles requesting of meal logs from persistence classes based  
on the date the user wants to view. This class sends new entries to persistence classes for addition  
into the daily log and the database.  
  
### Recipe Book Ops
This class handles operations related to the app recipe book. This class handles requesting of food,  
drink, and meal recipe lists from persistence classes. This class sends new entries to persistence classes  
for addition into food, drink, and meal recipes databases.  

### Trends Ops
This class handles operations 

### User Data Ops
This class handles operations 

### Unit Converter
This class handles unit conversions for daily log entries.   

## Objects  
Object classes are objects that are shared by Business, Presentation, and Persistence classes.    
### ListItem
This class is an interface of thing required for objects to be displayed in the different app screens.  
The app uses views that rely on lists of items.  

### Edible
This is an Abstract class at the top of the heirarchy of food items. This class defines essential  
attributes and methods that all food items should have.  

### 
## Persistence
Persistence classes interact directly with databases and is used as an interface by business classes to  
retrieve and store information.  
### DataAccessStub
This class provides methods that allow the retrieval and storage of data into a database.    
### SharedDB
This is a static class that allows the sharing of in memory data storage accross activities.    
## Presentation
Presentation classes handle all UI activities. Drawing new elements on the screen, displaying new information,  
accepting user entries, handling of user gestures, etc.  

### Activity Meal Diary
This class handles the UI for the meal diary activity. This is the User interface for logging food consumed.  
This activity shows a users daily progress at glance and allows the user add their days exercise activity.  

### Activity Recipe Book
This class handles the UI for the Recipe Book.    

### Activity Daily Progress
This class handles  

### Activity Trends
This class handles    

### FragToMealDiary
This is an interface of methods that allow fragments that comprise the meal diary to send and  
recieve infromation from the parent activity.    

### FragToRecipeBook
This is an interface of methods that allow fragments that comprise the recipe book to send and  
recieve infromation from the parent activity.  

### RVAMealDiary
This is a recycler view adapter for the meal diary activity. This adapter handles the display  
of different fragment layouts in a recycler view.  

### RVARecipeBook
This is a recycler view adapter for the recipe book activity. This adapter handles the display  
of different fragment layouts in a recycler view.  

### MealDiaryLiveData
This is a class that allows the use of the observer design pattern in Android Activities. UI elements  
can be set to observe this class for changes and allow all observers to update their own elements  
when this class is updated.  

### Fragments
Fragments are reusable UI components that perform specific functions in activities. The most important  
example is the navigation fragment which is shared across all activities.  

# Feature List

## Users can track their daily consumed food items, meals, and drinks   
This feature is on the initial app activity the meal diary screen. This screen can be accessed by clicking on the  
cutlery icon on the bottom navigation bar. Users can scroll up and down to view all the food items and the amount  
they are consuming for the day.  

## Users can set and modify the daily goal for their calorie intake   
This feature is on the meal diary screen. Users can set the number of calories they want to consume for the  
day by clicking the Budget text below the progress bar.  

## Users can add the number of calories burnt for the day  
This feature is on the meal diary screen. Users can add an estimate of the calories they burn in a day by  
clicking the Exercise text below the progress bar.  

## Users can see the total amount of calories consumed on a single day  
This feature is on the meal diary screen. Users can see at a glance the total calories from food numerically  
and as a graphic progress relative o the goal.  

## Users can see instant progress updates  
This feature is on the meal diary screen. Users can instantly see graphically and numerically on the progress  
bar the effect of any changes to the meal entries, the actual exercise calories and the calorie goal.   

## Users can add new entries
This feature is on the meal diary screen. Users can add new meal entries from a food, drink and meal database  
by clicking the green plus button at the bottom of their daily log. This launches a screen which allows the  
user to select a list of food, meals, or drinks. The user is shown a selection of items with images, names and  
calorie count for each item. The user can then click on the item cards to show a context UI and can add the  
selected item by clicking the green plus button.    

## Users can modify meal diary entries
This feature is on the meal diary screen. Users can can click on any entry in the meal diary to show a context  
UI. The user can click on the red trash can to delete an entry or the pencil to modify the quantity of an entry.  
Modification allows users to alter the log so that it reflects the actual consumption of the user.  

## Users can view previous day diaries and plan for future consumption
This feature is on the meal diary screen. Users can click on arrows on the top center of the screen above the  
progress bar date traversal 1 day at a time. Users can click on the date to show a date selector for more efficient  
selection of further out dates.   

## Users can browse food, drink and meal databases  
This feature is a separate screen the recipe book. This screen can be accessed by clicking on the  
Book icon on the bottom navigation bar. Users are presented a scrolling list of cards with item images,  
names, and calories. Users can switch between lists by selecting the desired tab in the middle of the screen.  

## Users can add new food, drink, or meal items to the database 
This feature can be accessed from the recipe book. Users can click the floating action button then the plus icon  
to show a new item entry screen. Users can add food by selecting the food tab then the action button. Similarly  
Users can click the meal tab or the drinks tab then the action button to add items to those lists. Users can enter  
food details and add the item using the OK button. The user is notified visually if input does not meet the app  
requirements. 

## Users can see goal progress for the week 
This feature is in the daily progress activity screen. This can be accessed via the leftmost nav button. 

## Users can see long term trend charts of their goal progress with trendlines 
This feature is in the trends activity screen. This can be accessed via the fourth nav button from the left. 

## User data is persistent between startups 
This can be enabled in the DBSelector class by commenting the appropriate lines in the constructor lines 23-28



# Environments Used in Testing

Our team used Android Studio Chipmunk 2021.2.1 Patch 1 for development on Windows 10. We tested our code both on a   
Nexus 7 device and several Android virtual devices created in Android studio. Android virtual devices used, had   
SDK 23 for testing and SDK 30 for debugging.  














