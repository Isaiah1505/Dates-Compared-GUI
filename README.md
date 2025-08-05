# Date Comparison GUI
A solo project made with Java using the JavaFX framework for a GUI that takes in two dates from the user in a dd/mm/yyyy format.
This data is then converted into a actual date using the java.time API library and calculates the amount of time between the dates (both with or without the end date included) and displays it in multiple units of time:

* Years, Months and Days
* Years
* Months
* Weeks
* Days
* Hours
* Minutes
* Seconds

Year, month and week values are rounded to the 3rd decimal respectively for better appeal, or truncated if numbers are whole.