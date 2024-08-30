# My Personal Project: Calendar application

## Description

The application in this file will be a calendar keeper (or multiple calendars), which **records and stores** new 
events (with a specific *date*, *duration of time*, *title*, and *notes (if any)*) into the specified calendar. 
Within a calendar, the user will be able to **search** for any date (which will 
produce a summary of the events on that day), or search for events on the calendar 
(which will display the specifics of that event). Users will be able to **add**, 
**modify** or **delete** an arbitrary number of these events.

Any desktop user requiring an event tracker or a simple calendar can use this application.

I chose this idea since I felt like the application would actually be useful to me, since I
use my Calendar and Reminders applications often. It also felt more purposeful than other suggestions
 such as fake Shopping List application or Game Organizer. The structure of the program works well with
the criteria of the project as well, as the user will be able to add an arbitrary number of events
to a calendar, and the user will be able to create an arbitrary number of calendars.

## User Stories

- As a user, I want to be able to add and remove events from my calendar.
- As a user, I want to be able to search up any pre-existing event on my calendar.
- As a user, I want to be able to search up a date and time to see if there is any event registered. If there is, 
I want to be able to see the specific event.
- As a user, I want to be able to produce a list of all the events for a given day or month.
- As a user, I want to be able to be able to categorize my events into different calendars (ex. work, school, 
personal, etc.)
- As a user, I want to be able to save all the data of all my calendars to file upon the user's request.
- As a user, I want to be able to load my saved calendars from file upon the user's request.

Some other user stories that I may add in the future (I do not yet know if I will be able to do these in the future)

- As a user, I want to be able to overview an entire months worth of events in a traditional calendar format.
- As a user, I do not want to be able to produce a new event if that event has a time conflict with another event.

## Instructions for Grader

##### Visual component
There is a calendar icon displayed when initially loading the JFrame.

##### Entering a calendar (the object where other objects are added to)
You can start with no calendars, or you can load the demo workroom which you can access by 
clicking the "load data" button.
You can add a calendar by pressing "add calendar", then access it by clicking the "select calendar" button
and entering the integer index of the specified calendar in the displayed list.

##### Adding an event to a calendar and entering it.
You can add an event to the calendar by clicking the "add event" button in the selected calnedar's frame 
(continuing from instructions above). You can view the specific event by clicking the "select event" button, and 
searching up a date that overlaps with your event's date. Enter the index of the event you want to enter by entering
its index in the shown list.

##### Required actions related to adding events to calendar.
In the frame with the selected event, you can:
- delete the event from the calendar, by clicking on the "delete" button.
- change the title of the event, by clicking on the "change title" button, after which you will be given a text field to
enter the new title and a button to submit.

You can view these changes by re-searching the same date that you previously used to search you event.

##### Saving and Loading
You can save and load the data in the initial frame; there are buttons "save data" 
(which saves the state of the application), and "load data" (which reloads the state of the application). 

If you are in a frame with a specified calendar or event, press the "return button" until you reach the initial frame 
(the one with the calendar icon) that contains the buttons.

## Phase 4: Task 2

The following sample of events demonstrates all possible events related to 
adding, deleting, and editing calendars and their events in this program.

Wed Apr 12 23:31:50 PDT 2023
Added new calendar New Calendar to workroom.

Wed Apr 12 23:31:52 PDT 2023
Added new calendar New Calendar to workroom.

Wed Apr 12 23:32:11 PDT 2023
Set calendar "New Calendar"'s title to "demoCalendar"

Wed Apr 12 23:32:38 PDT 2023
Event "New Event" added to calendar "demoCalendar"

Wed Apr 12 23:33:05 PDT 2023
Event "New Event" added to calendar "demoCalendar"

Wed Apr 12 23:33:28 PDT 2023
Set event "New Event"'s title to "hello"

Wed Apr 12 23:33:37 PDT 2023
Set event "hello"'s description to "changed description."

Wed Apr 12 23:33:41 PDT 2023
Set event "hello"'s priority to "3"

Wed Apr 12 23:34:21 PDT 2023
Set event "New Event"'s title to "toBeRemoved"

Wed Apr 12 23:34:22 PDT 2023
Event "toBeRemoved" removed from calendar "demoCalendar"

Wed Apr 12 23:34:42 PDT 2023
Set calendar "New Calendar"'s title to "calendarToBeRemoved"

Wed Apr 12 23:34:48 PDT 2023
Event "New Event" added to calendar "calendarToBeRemoved"

Wed Apr 12 23:34:55 PDT 2023
Removed calendar "calendarToBeRemoved" from workroom.
Wed Apr 12 23:31:50 PDT 2023
Added new calendar New Calendar to workroom.

Wed Apr 12 23:31:52 PDT 2023
Added new calendar New Calendar to workroom.

Wed Apr 12 23:32:11 PDT 2023
Set calendar "New Calendar"'s title to "demoCalendar"

Wed Apr 12 23:32:38 PDT 2023
Event "New Event" added to calendar "demoCalendar"

Wed Apr 12 23:33:05 PDT 2023
Event "New Event" added to calendar "demoCalendar"

Wed Apr 12 23:33:28 PDT 2023
Set event "New Event"'s title to "hello"

Wed Apr 12 23:33:37 PDT 2023
Set event "hello"'s description to "changed description."

Wed Apr 12 23:33:41 PDT 2023
Set event "hello"'s priority to "3"

Wed Apr 12 23:34:21 PDT 2023
Set event "New Event"'s title to "toBeRemoved"

Wed Apr 12 23:34:22 PDT 2023
Event "toBeRemoved" removed from calendar "demoCalendar"

Wed Apr 12 23:34:42 PDT 2023
Set calendar "New Calendar"'s title to "calendarToBeRemoved"

Wed Apr 12 23:34:48 PDT 2023
Event "New Event" added to calendar "calendarToBeRemoved"

Wed Apr 12 23:34:55 PDT 2023
Removed calendar "calendarToBeRemoved" from workroom.

## Phase 4: Task 3

By creating the UML class diagram, I was able to identify the aspects of my program that have caused inefficiencies 
and/or design problems. For example, I now recognize that it may have been better to implement the UI classes in a 
hierarchy such that I did not have to pass on private fields from one UI class to another. This would reduce the amount
of associations with the model classes while giving the code a more organized design overall.

Additionally, although not shown in the UML Diagram, I have lots of classes that are dependent on classes that seem 
unrelated externally. If I had more time, I would try to reduce these dependencies, as most of them do not seem 
very necessary. For example, the Calendar class depends on the Time class to set the time for the new event that
it creates. If I had more time, I would figure out a way to remove this dependency (as it seems inefficient), such that
we can set the new times for an event without ever needing to call the Time class in any method of the Calendar class.

## References

The persistence model of the JSONSerializationDemo project by Paul Carter (2022) has been referenced in building 
the persistence model of this project:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git