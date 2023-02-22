# My Personal Project

## Course Registration

This application will resemble a database to keep track of all data regarding courses that is expected in a university.
Similar to the UBC SSC system. For example the application may keep track of and/or do
- **Courses** 
- **Professors**
- **Students** 

This application will be used by students wanting to enroll in courses 
This project is of interest to me because I have been fascinated with how information is stored and utilized, 
I hope this project will help me reach a deeper understanding regarding how 
databases work.

## User Stories
- As A user, I want to be able to add courses to a list
- As A user, I want to be able to remove courses from a list
- As A user, I want to be able to determine the cost of tuition
- As A user, I want to be able to view the current list of courses
- As A user, I want to be able to save the currently registered list of courses
- As A user, I want to be able to load the registered list of courses

**Method of persistence was taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git**

## Instructions for Grader
- You can generate the first required event related to adding Xs to a Y by selecting a course from the available courses, then 
by clicking the add course button to add it onto a list of courses
- You can generate the second required event related to removing Xs from a Y by selecting a course from the available courses, then clicking on the remove course button to remove the course from the courses
- You can locate my visual component by encountering errors while operating the program (ex. adding a course when the course is already added, or removing a course when the course does not have that course registered)
- You can save the state of my application by clicking on the save button
- You can reload the state of my application by either clicking on the load button to load courses that have been saved before or the small button in the middle of the GUI which clears all registered courses.
- Additionally, you can click the Calculate Tuition button to calculate the tuition for your current registered courses

## Phase 4: Task 2
Thu Dec 01 18:22:20 PST 2022
Added CPSC 100.

Thu Dec 01 18:22:22 PST 2022
Added CPSC 110.

Thu Dec 01 18:22:25 PST 2022
Removed CPSC 100.

Thu Dec 01 18:22:27 PST 2022
Calculated tuition, total is: 80.

Thu Dec 01 18:22:29 PST 2022
Calculated tuition, total is: 80.

Thu Dec 01 18:22:29 PST 2022
Saved to file.

Thu Dec 01 18:22:35 PST 2022
Cleared courses.

Thu Dec 01 18:22:35 PST 2022
Loaded file.

Thu Dec 01 18:22:35 PST 2022
Added CPSC 110.

## Phase 4: Task 3
- I could refactor popups in the ui to be a subclass of a superclass popup since there are duplicate lines of code
- There are many lines of code where I have commented out which I could delete for the sake of clarity of the code
- In both the GUI and the console UI addCourse and removeCourse could be written clearly rather than individually testing each case of the specific course
