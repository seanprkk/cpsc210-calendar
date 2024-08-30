package ui;

import model.Calendar;
import model.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents a GUI editor that edits the list of events stored in a calendar.
 */
public class EventEditor implements ActionListener {

    private Event event;
    private Calendar calendar;

    private JFrame frame;
    private JPanel panel;
    private JButton editTitleButton;
    private JButton deleteEventButton;
    private JButton editDescriptionButton;
    private JButton editPriorityButton;
    private JButton returnButton;
    private JLabel descriptionLabel;
    private JLabel print;

    private final int elementWidth = 300;
    private final int elementHeight = 25;

    // EFFECTS: specifies which Calendar object editor is editing, as well as CalendarApp object to return.
    //          Directs to browse events menu.
    public EventEditor(Calendar calendar, Event event) {
        this.event = event;
        this.calendar = calendar;
        frame = new JFrame("Event - " + event.getTitle());
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        descriptionLabel = new JLabel(event.getDescription() + " (" + event.getPriority() + "). From "
        + event.getStartTime().getStringRep() + " to " + event.getEndTime().getStringRep());
        descriptionLabel.setBounds(120, 170, 100, elementHeight);
        panel.add(descriptionLabel);
        placeComponents(panel);
        frame.add(panel);
        frame.setVisible(true);
    }

    // EFFECTS: places required buttons onto given JPanel.
    private void placeComponents(JPanel panel) {
        editTitleButton = new JButton("Edit Title");
        editTitleButton.setBounds(10, 50, elementWidth, elementHeight);
        editTitleButton.addActionListener(this);
        panel.add(editTitleButton);
        editDescriptionButton = new JButton("Edit Description");
        editDescriptionButton.setBounds(10, 20, elementWidth, elementHeight);
        editDescriptionButton.addActionListener(this);
        panel.add(editDescriptionButton);
        editPriorityButton = new JButton("Edit Priority");
        editPriorityButton.setBounds(10, 80, elementWidth, elementHeight);
        editPriorityButton.addActionListener(this);
        panel.add(editPriorityButton);
        deleteEventButton = new JButton("Delete Event");
        deleteEventButton.setBounds(10, 110, elementWidth, elementHeight);
        deleteEventButton.addActionListener(this);
        panel.add(deleteEventButton);
        returnButton = new JButton("Return");
        returnButton.setBounds(10, 140, elementWidth, elementHeight);
        returnButton.addActionListener(this);
        panel.add(returnButton);
        print = new JLabel("Select an action");
        print.setBounds(120, 170, elementWidth, elementHeight);
        panel.add(print);
    }

    /*
    Represents a JFrame where user creates new title for given event.
     */
    private class ChangeTitleWindow implements ActionListener {
        private JFrame editTitleFrame;
        private JPanel editTitlePanel;
        private JButton editTitleButton;
        private JTextField textInput;

        // EFFECTS: constructs a JFrame with all needed components.
        public ChangeTitleWindow() {
            editTitleFrame = new JFrame("Event - " + event.getTitle());
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

        // EFFECTS: defines actions for when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            event.setTitle(textInput.getText());
            print.setText("Set new title to " + textInput.getText());
            editTitleFrame.setVisible(false);
        }
    }

    /*
    Represents a JFrame where user determines new description for given event.
     */
    private class ChangeDescriptionWindow implements ActionListener {
        private JFrame editDescriptionFrame;
        private JPanel editDescriptionPanel;
        private JButton editDescriptionButton;
        private JTextField textInput;

        // EFFECTS: constructs new JFrame with all needed components
        public ChangeDescriptionWindow() {
            editDescriptionFrame = new JFrame("Event - " + event.getTitle());
            editDescriptionFrame.setSize(1000,500);
            editDescriptionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editDescriptionPanel = new JPanel();
            placeComponents(editDescriptionPanel);
            editDescriptionFrame.add(editDescriptionPanel);
            editDescriptionFrame.setVisible(true);
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel panel) {
            editDescriptionButton = new JButton("Save Description");
            editDescriptionButton.setBounds(10,70, elementWidth, elementHeight);
            editDescriptionButton.addActionListener(this);
            panel.add(editDescriptionButton);
            textInput = new JTextField("Enter new description");
            textInput.setBounds(120,30, elementWidth, elementHeight);
            panel.add(textInput);
        }

        // EFFECTS: defines actions for when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            event.setDescription(textInput.getText());
            print.setText("Set new description to " + textInput.getText());
            editDescriptionFrame.setVisible(false);
        }
    }

    /*
    Represents a JFrame where user detemrines new priority of event.
     */
    private class ChangePriorityWindow implements ActionListener {
        JFrame editPriorityFrame;
        JPanel editPriorityPanel;
        JLabel typeHereLabel;
        JTextField textInput;
        JButton submitButton;

        public ChangePriorityWindow() {
            editPriorityFrame = new JFrame("Change Priority");
            editPriorityPanel = new JPanel();
            editPriorityFrame.setSize(1000,500);
            editPriorityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.placeComponents(editPriorityPanel);
            editPriorityFrame.add(editPriorityPanel);
            editPriorityFrame.setVisible(true);
        }

        // EFFECTS: places required buttons onto given JPanel.
        private void placeComponents(JPanel searcherPanel) {
            typeHereLabel = new JLabel("Set new priority (1-5):");
            typeHereLabel.setBounds(10, 30, elementWidth, elementHeight);
            searcherPanel.add(typeHereLabel);
            textInput = new JTextField(1);
            textInput.setBounds(10, 60, elementWidth, elementHeight);
            searcherPanel.add(textInput);
            submitButton = new JButton("Enter");
            submitButton.setBounds(10, 90, elementWidth, elementHeight);
            submitButton.addActionListener(this);
            searcherPanel.add(submitButton);
        }

        // EFFECTS: defines actions for when a button is pressed.
        @Override
        public void actionPerformed(ActionEvent e) {
            int newPriority = -1;
            try {
                newPriority = Integer.parseInt(textInput.getText());
                if (newPriority < 0 || newPriority > 6) {
                    throw new IndexOutOfBoundsException();
                }
            } catch (NumberFormatException ex) {
                editPriorityFrame.setVisible(false);
                print.setText("Invalid input.");
            } catch (IndexOutOfBoundsException ex) {
                editPriorityFrame.setVisible(false);
                print.setText("Input has to be between 1-5..");
            }
            print.setText("Set Event priority to " + textInput.getText());
            event.setPriority(newPriority);
            editPriorityFrame.setVisible(false);
        }
    }

    // EFFECTS: defines actions for when a button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editTitleButton) {
            new ChangeTitleWindow();
        } else if (e.getSource() == editDescriptionButton) {
            new ChangeDescriptionWindow();
        } else if (e.getSource() == editPriorityButton) {
            new ChangePriorityWindow();
        } else if (e.getSource() == deleteEventButton) {
            calendar.deleteEvent(event);
            frame.setVisible(false);
        } else if (e.getSource() == returnButton) {
            frame.setVisible(false);
        }
    }
}
