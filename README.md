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
https://github.com/DaneHarrison/Eat-Dix

# Dev Log
The team dev log is kept as an md file in the github repository  
# Packages
## Business
Business classes handle program logic and calculations related to their activities.  
### Meal Diary Ops
The meal diary ops class handles logic and operations related to the main app activity which is the logging of a users meals. This class handles requesting of meal logs from persistence classes based on the date the user wants to view. This class sends new entries to persistence classes for addition into the daily log and the database.  
  
### Recipe Book Ops
This class handles operations related to the app recipe book. This class handles requesting of food, drink, and meal recipe lists from persistence classes. This class sends new entries to persistence classes for addition into food, drink, and meal recipes databases.  

### Unit Converter
This class handles unit conversions for meal diary entries.  

## Objects  
Object classes are objects that are shared by Business, Presentation, and Persistence classes.    
### ListItem
This class is an interface of thing required for objects to be displayed in the different app screens. The app uses views that rely on lists of items.  

### Edible
This is an Abstract class at the top of the heirarchy of food items. This class defines essential attributes and methods that all fod items should have.  

### 
## Persistence
Persistence classes interact directly with databases and is used as an interface by business classes to retrieve and store information.  
### DataAccessStub
This class provides methods that allow the retrieval and storage of data into a database.    
### SharedDB
This is a static class that allows the sharing of in memory data storage accross activities.    
## Presentation
Presentation classes handle all UI activities. Drawing new elements on the screen, displaying new information, accepting user entries, handling of user gestures, etc.  

### Activity Meal Diary
This class handles the UI for the meal diary activity. This is the User interface for logging food consumed. This activity shows a users daily progress at glance and allows the user add their days exercise activity.  

### Activity Recipe Book
This class handles the UI for the Recipe Book.    

### FragToMealDiary
This is an interface of methods that allow fragments that comprise the meal diary to send and recieve infromation from the parent activity.    

### FragToRecipeBook
This is an interface of methods that allow fragments that comprise the recipe book to send and recieve infromation from the parent activity.  

### RVAMealDiary
This is a recycler view adapter for the meal diary activity. This adapter handles the display of different fragment layouts in a recycler view.  

### RVARecipeBook
This is a recycler view adapter for the recipe book activity. This adapter handles the display of different fragment layouts in a recycler view.  

### MealDiaryLiveData
This is a class that allows the use of the observer design pattern in Android Activities. UI elements can be set to observe this class for changes and allow all observers to update their own elements when this class is updated.  

### Fragments
Fragments are reusable UI components that perform specific functions in activities. The most important example is the navigation fragment which is shared across all activities.  

# Feature List
The main page of the Meal Planner application display's the food items that the user plans to consume on a specific Day. The bar depicts the Calorie goal for the day, the number of calories consumed via Food, calories burnt via Exercise, and the net calories that can make. The user can set the Calorie goal for the day, and calories burnt in the exercise routine and will be able to track the progress made. The user can edit the list of Food items that need to be consumed. They can add Food items, Meals, and Drinks to the Food list along with the amount that they are consuming for each item. Also, we can remove the items on the list to cut off the unnecessary calorie intake. The user can set the Calorie goal for the day and will be able to track the number of calories that can still be consumed to reach the target. Moreover, users are able to change the amount of each item they did intake. The users can also add new food recipes.They need to enter the name of the Dish and the calorie count of the dish, the quantity of the dish.

Features:

1. The users can track their daily food items, meals, and drinks.
Description:
This feature is on the MealDiary activity. The users can scroll up and down to view all the food items and the amount they are consuming for the day.

2. The users can set the daily goal for their calorie intake.
Description:
This feature is on the Meal Diary activity. The users can add the number of calories they want to consume for the day by clicking the budget button on the top left and letting the application keep track of them.

3. The users can add the number of calories burnt 

# Environments Used in Testing

Our team used Android Studio Chipmunk 2021.2.1 Patch 1 for development on Windows 10. We tested our code both on a Nexus 7 device and several Android virtual devices created in Android studio. Android virtual devices used, had SDK 23 for testing and SDK 29 for debugging.  



