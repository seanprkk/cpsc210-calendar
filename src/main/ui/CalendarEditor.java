package ui;

import model.Calendar;
import model.Event;
import model.Time;
import model.Workroom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
Represents a GUI where user edits features of a chosen calendar.
 */
public class CalendarEditor implements ActionListener {

    private Calendar calendar;
    private Workroom workroom;

    private JFrame frame;
    private JPanel panel;
    private JButton editTitleButton;
    private JButton deleteCalendarButton;
    private JButton addEventButton;
    private JButton searchEventButton;
    private JButton returnButton;
    private JLabel print;

    private final int elementWidth = 300;
    private final int elementHeight = 25;

    // EFFECTS: constructs JFrame containing buttons, instantiates the subject calendar of this calendar.
    public CalendarEditor(Workroom workroom, Calendar calendar) {
        this.calendar = calendar;
        this.workroom = workroom;
        frame = new JFrame("Calendar - " + calendar.getTitle());
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        placeComponents(panel);
        frame.add(panel);
        frame.setVisible(true);
    }

    // EFFECTS: places required buttons onto given JPanel.
    private void placeComponents(JPanel panel) {
        editTitleButton = new JButton("Edit Title");
        editTitleButton.setBounds(100,80, elementWidth, elementHeight);
        editTitleButton.addActionListener(this);
        panel.add(editTitleButton);
        searchEventButton = new JButton("Events");
        searchEventButton.setBounds(100,50, elementWidth, elementHeight);
        searchEventButton.addActionListener(this);
        panel.add(searchEventButton);
        deleteCalendarButton = new JButton("Delete");
        deleteCalendarButton.setBounds(100,110, elementWidth, elementHeight);
        deleteCalendarButton.addActionListener(this);
        panel.add(deleteCalendarButton);
        addEventButton = new JButton("Add Event");
        addEventButton.setBounds(100,140, elementWidth, elementHeight);
        addEventButton.addActionListener(this);
        panel.add(addEventButton);
        returnButton = new JButton("Return");
        returnButton.setBounds(100,170, elementWidth, elementHeight);
        returnButton.addActionListener(this);
        panel.add(returnButton);
        print = new JLabel("Select an action");
        print.setBounds(10,20, elementWidth, elementHeight);
        panel.add(print);
    }

    /*
    Represents a JFrame where user searches event by date.
     */
    private class SearchEventWindow implements ActionListener {
        private JFrame searchEventFrame;
        private JPanel searchEventPanel;
        private JButton searchEventButton;
        private JTextField yearInput;
        private JTextField monthInput;
        private JTextField dayInput;

        // EFFECTS: Creates JFrame with all needed components
        public SearchEventWindow() {
            searchEventFrame = new JFrame("Calendar - " + calendar.getTitle());
            searchEventFrame.setSize(1000,500);
            searchEventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            searchEventPanel = new JPanel();
            placeComponents(searchEventPanel);
            searchEventFrame.add(searchEventPanel);
            searchEventFrame.setVisible(true);
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel panel) {
            searchEventButton = new JButton("Search Date");
            searchEventButton.setBounds(90,70, elementWidth, elementHeight);
            searchEventButton.addActionListener(this);
            panel.add(searchEventButton);
            yearInput = new JTextField("Year");
            yearInput.setBounds(10,20, elementWidth, elementHeight);
            panel.add(yearInput);
            monthInput = new JTextField("Month");
            monthInput.setBounds(10,50, elementWidth, elementHeight);
            panel.add(monthInput);
            dayInput = new JTextField("Day");
            dayInput.setBounds(10,80, elementWidth, elementHeight);
            panel.add(dayInput);
        }

        // EFFECTS: defines actions to execute when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredYear = 0;
            int enteredMonth = 0;
            int enteredDay = 0;
            try {
                enteredYear = Integer.parseInt(yearInput.getText());
                enteredMonth = Integer.parseInt(monthInput.getText());
                enteredDay = Integer.parseInt(dayInput.getText());
            } catch (NumberFormatException ex) {
                print.setText("Invalid input.");
                searchEventFrame.setVisible(false);
            }
            if (!checkValidDate(enteredYear, enteredMonth, enteredDay)) {
                print.setText("Invalid Date.");
                searchEventFrame.setVisible(false);
            }
            List<Event> searchedEvents = calendar.searchEvents(enteredYear, enteredMonth, enteredDay);
            searchEventFrame.setVisible(false);
            new DisplaySearchedEvents(searchedEvents);
        }

        // EFFECTS: returns true if given year, month and day is valid.
        private boolean checkValidDate(int year, int month, int day) {
            return validYear(year) && validMonth(month) && validDay(day);
        }

        // EFFECTS: returns true if year is greater than 0.
        private boolean validYear(int year) {
            return year >= 0;
        }

        // EFFECTS: returns true if month is within [1,12].
        private boolean validMonth(int month) {
            return month >= 1 && month <= 12;
        }

        // EFFECTS: returns true if day is within [1,31]
        private boolean validDay(int day) {
            return day >= 1 & day <= 31;
        }
    }

    /*
    Represents a event search results JFrame.
     */
    private class DisplaySearchedEvents implements ActionListener {
        JFrame displayEventsFrame;
        JPanel displayEventsPanel;
        JList displayEventsList;
        JLabel typeHereLabel;
        JTextField searchBox;
        JButton submitButton;
        List<Event> events;

        // EFFECTS: if no events, returns to CalendarEditor frame. Else creates a search result display JFrame.
        public DisplaySearchedEvents(List<Event> events) {
            if (events.size() == 0) {
                print.setText("No results.");
            } else {
                displayEventsFrame = new JFrame("Search Results");
                displayEventsFrame.setSize(1000, 500);
                displayEventsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.events = events;
                displayEventsPanel = new JPanel();
                this.placeComponents(displayEventsPanel);
                displayEventsFrame.add(displayEventsPanel);
                displayEventsFrame.setVisible(true);
            }
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel panel) {
            List<String> eventsIndexed = new ArrayList<>();
            int index = 1;
            for (Event e : this.events) {
                eventsIndexed.add(index + ") " + e.getTitle());
                index++;
            }
            this.displayEventsList = new JList(eventsIndexed.toArray());
            displayEventsList.setBounds(10,30, elementWidth, 400);
            panel.add(displayEventsList);
            typeHereLabel = new JLabel("Enter event index here:");
            typeHereLabel.setBounds(100, 30, elementWidth, elementHeight);
            panel.add(typeHereLabel);
            searchBox = new JTextField(2);
            searchBox.setBounds(100, 60, elementWidth, elementHeight);
            panel.add(searchBox);
            submitButton = new JButton("Enter");
            submitButton.setBounds(100, 110, elementWidth, elementHeight);
            submitButton.addActionListener(this);
            panel.add(submitButton);
        }

        // EFFECTS: defines actions to execute when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            int chosenIndex = -1;
            try {
                if (this.events.size() == 0) {
                    throw new NullPointerException();
                }
                chosenIndex = Integer.parseInt(searchBox.getText()) - 1;
                if (chosenIndex <= 0 || chosenIndex >= this.events.size()) {
                    throw new IndexOutOfBoundsException();
                }
            } catch (NumberFormatException ex) {
                displayEventsFrame.setVisible(false);
                print.setText("Invalid input.");
            } catch (IndexOutOfBoundsException ex) {
                displayEventsFrame.setVisible(false);
                print.setText("Invalid input.");
            } catch (NullPointerException ex) {
                displayEventsFrame.setVisible(false);
                print.setText("No Events.");
            }
            Event chosenEvent = this.events.get(chosenIndex);
            print.setText("Select an action.");
            displayEventsFrame.setVisible(false);
            new EventEditor(calendar, chosenEvent);
        }
    }

    /*
    Represents a JFrame where user submits a new title for the given calendar.
     */
    private class ChangeTitleWindow implements ActionListener {
        private JFrame editTitleFrame;
        private JPanel editTitlePanel;
        private JButton editTitleButton;
        private JTextField textInput;

        // EFFECTS: creates new JFrame with all necessary components.
        public ChangeTitleWindow() {
            editTitleFrame = new JFrame("Calendar - " + calendar.getTitle());
            editTitleFrame.setSize(1000,500);
            editTitleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editTitlePanel = new JPanel();
            placeComponents(editTitlePanel);
            editTitleFrame.add(editTitlePanel);
            editTitleFrame.setVisible(true);
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel panel) {
            editTitleButton = new JButton("Save Title");
            editTitleButton.setBounds(10,70, elementWidth, elementHeight);
            editTitleButton.addActionListener(this);
            panel.add(editTitleButton);
            textInput = new JTextField("Enter new title");
            textInput.setBounds(120,30, elementWidth, elementHeight);
            panel.add(textInput);
        }

        // EFFECTS: defines actions to execute when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            calendar.setTitle(textInput.getText());
            print.setText("Set new title to " + textInput.getText());
            editTitleFrame.setVisible(false);
        }
    }

    /*
    Represents a JFrame where user inputs the start and end times for the new event they add.
     */
    private class AddEventWindow implements ActionListener {
        private JFrame addEventFrame;
        private JPanel addEventPanel;
        private JButton addEventButton;
        private JLabel instructionLabel;

        private JTextField startYearInput;
        private JTextField startMonthInput;
        private JTextField startDayInput;
        private JTextField startHourInput;
        private JTextField startMinuteInput;
        private JTextField endYearInput;
        private JTextField endMonthInput;
        private JTextField endDayInput;
        private JTextField endHourInput;
        private JTextField endMinuteInput;

        // EFFECTS: creates new JFrame with all necessary components.
        public AddEventWindow() {
            addEventFrame = new JFrame("New Event");
            addEventFrame.setSize(1000,500);
            addEventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addEventPanel = new JPanel();
            placeComponents(addEventPanel);
            addEventFrame.add(addEventPanel);
            addEventFrame.setVisible(true);
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel panel) {
            addEventButton = new JButton("Create event");
            addEventButton.setBounds(10,70, elementWidth, elementHeight);
            addEventButton.addActionListener(this);
            addEventPanel.add(addEventButton);
            instructionLabel = new JLabel("Enter times");
            instructionLabel.setBounds(10, 20, elementWidth, elementHeight);
            addEventPanel.add(instructionLabel);
            addStartTimeButtons();
            addEndTimeButtons();
        }

        // EFFECTS: adds all text fields for start time inputs.
        private void addStartTimeButtons() {
            startYearInput = new JTextField("Start Year");
            startYearInput.setBounds(10,50, elementWidth, elementHeight);
            addEventPanel.add(startYearInput);
            startMonthInput = new JTextField("Start Month");
            startMonthInput.setBounds(10,80, elementWidth, elementHeight);
            addEventPanel.add(startMonthInput);
            startDayInput = new JTextField("Start Day");
            startDayInput.setBounds(10,110, elementWidth, elementHeight);
            addEventPanel.add(startDayInput);
            startHourInput = new JTextField("Start Hour");
            startHourInput.setBounds(10,140, elementWidth, elementHeight);
            addEventPanel.add(startHourInput);
            startMinuteInput = new JTextField("Start Minute");
            startMinuteInput.setBounds(10,170, elementWidth, elementHeight);
            addEventPanel.add(startMinuteInput);
        }

        // EFFECTS: adds all text fields for end time inputs.
        private void addEndTimeButtons() {
            endYearInput = new JTextField("End Year");
            endYearInput.setBounds(elementWidth + 10,50, elementWidth, elementHeight);
            addEventPanel.add(endYearInput);
            endMonthInput = new JTextField("End Month");
            endMonthInput.setBounds(elementWidth + 10,80, elementWidth, elementHeight);
            addEventPanel.add(endMonthInput);
            endDayInput = new JTextField("End Day");
            endDayInput.setBounds(elementWidth + 10,110, elementWidth, elementHeight);
            addEventPanel.add(endDayInput);
            endHourInput = new JTextField("End Hour");
            endHourInput.setBounds(elementWidth + 10,140, elementWidth, elementHeight);
            addEventPanel.add(endHourInput);
            endMinuteInput = new JTextField("End Minute");
            endMinuteInput.setBounds(elementWidth + 10,170, elementWidth, elementHeight);
            addEventPanel.add(endMinuteInput);
        }

        // EFFECTS: defines actions for when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            Event newEvent = new Event(new Time(0,0,0,0,0),
                    new Time(0,0,0,0,0));
            try {
                Time startTime = createStartTime();
                Time endTime = createEndTime();
                newEvent = new Event(startTime, endTime);

            } catch (NumberFormatException ex) {
                addEventFrame.setVisible(false);
                print.setText("Invalid input.");
                System.out.println("numberformatexception thrown");
            } catch (IndexOutOfBoundsException ex) {
                addEventFrame.setVisible(false);
                print.setText("Invalid input.");
                System.out.println("indexoutofboundsexception thrown");
            } catch (NullPointerException ex) {
                addEventFrame.setVisible(false);
                print.setText("No Calendars.");
                System.out.println("nullpointerexception thrown");
            }
            calendar.addEvent(newEvent);
            print.setText("Added new event.");
            System.out.println(calendar.getEvents().size());
            addEventFrame.setVisible(false);
        }

        // EFFECTS: creates start time from inputs of text fields.
        private Time createStartTime() {
            int year = Integer.parseInt(startYearInput.getText());
            int month = Integer.parseInt(startMonthInput.getText());
            int day = Integer.parseInt(startDayInput.getText());
            int hour = Integer.parseInt(startHourInput.getText());
            int minute = Integer.parseInt(startMinuteInput.getText());
            boolean validYear = year >= 0;
            boolean validMonth = month >= 1 && month <= 12;
            boolean validDay = day >= 1 && day <= 31;
            boolean validHour = hour >= 0 && hour <= 23;
            boolean validMinute = minute >= 0 && minute <= 59;
            if (validYear && validMonth && validDay && validHour && validMinute) {
                return new Time(year, month, day, hour, minute);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        // EFFECTS: creates end time from inputs of text fields.
        private Time createEndTime() {
            int year = Integer.parseInt(endYearInput.getText());
            int month = Integer.parseInt(endMonthInput.getText());
            int day = Integer.parseInt(endDayInput.getText());
            int hour = Integer.parseInt(endHourInput.getText());
            int minute = Integer.parseInt(endMinuteInput.getText());
            boolean validYear = year >= 0;
            boolean validMonth = month >= 1 && month <= 12;
            boolean validDay = day >= 1 && day <= 31;
            boolean validHour = hour >= 0 && hour <= 23;
            boolean validMinute = minute >= 0 && minute <= 59;
            if (validYear && validMonth && validDay && validHour && validMinute) {
                return new Time(year, month, day, hour, minute);
            } else {
                throw new NullPointerException();
            }
        }
    }

    // EFFECTS: defines actions for when a button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchEventButton) {
            new SearchEventWindow();
        } else if (e.getSource() == editTitleButton) {
            new ChangeTitleWindow();
        } else if (e.getSource() == deleteCalendarButton) {
            workroom.removeCalendar(calendar);
            frame.setVisible(false);
        } else if (e.getSource() == addEventButton) {
            new AddEventWindow();
        } else if (e.getSource() == returnButton) {
            frame.setVisible(false);
        }
    }
}
