package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import main.java.memoranda.Bus;
import main.java.memoranda.BusList;
import main.java.memoranda.Driver;
import main.java.memoranda.DriverList;
import main.java.memoranda.JsonHandler;
import main.java.memoranda.util.Local;

public class BusAndDriverPanel extends JPanel {
    JButton createDriverButton, createBusButton;
    JScrollPane driverScrollPane, busScrollPane;
    JPanel columnPane, listPane, driverPane, busPane, driverColHeaders, busColHeaders;
    JsonHandler jsonHandler = new JsonHandler();
    DriverList driverList;
    BusList busList;
    Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);


    /**
     * Default Constructor.
     */
    public BusAndDriverPanel() {
        try {
            panelInitialization();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Initializes the panel and its respective lists.
     */
    private void panelInitialization() {
        //Import the drivers from the json file and populate driverList
        String fileName = "nodes1.json";
    	jsonHandler.readDriversFromJSON(fileName);
        driverList = new DriverList(jsonHandler.driverList);
        jsonHandler.readBusesFromJSON(fileName);
        busList = new BusList(jsonHandler.busList);

        //set the main panel layout to be a borderlayout
        this.setLayout(new BorderLayout());

        //########################################## Top Panel ##########################################
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(getWidth(), 82));
        topPanel.setBackground(Color.WHITE);

        //Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        //Create Driver Button
        createDriverButton = new JButton();
        createDriverButton.setIcon(
                new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Add_Driver.png")));
        createDriverButton.setMaximumSize(new Dimension(40, 50));
        createDriverButton.setToolTipText(Local.getString("Create a New Driver"));
        createDriverButton.setPreferredSize(new Dimension(40, 50));
        createDriverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createDriverButton_ActionPerformed(e);
            }
        });

        //Create Bus Button
        createBusButton = new JButton();
        createBusButton.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Add_Bus.png")));
        createBusButton.setMaximumSize(new Dimension(40, 50));
        createBusButton.setToolTipText(Local.getString("Create a New Bus"));
        createBusButton.setPreferredSize(new Dimension(40, 50));
        createBusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createBusButton_ActionPerformed(e);
            }
        });

        //Add buttons to buttonPanel
        buttonPanel.add(createDriverButton);
        buttonPanel.add(createBusButton);

        //Add button panel to the top panel
        topPanel.add(buttonPanel, BorderLayout.WEST);

        //########################################## Columns ##########################################
        //displayPane the columnPanes **NONE OF THIS COLUMN STUFF WORKS YET**
        columnPane = new JPanel();
        columnPane.setLayout(new BoxLayout(columnPane, BoxLayout.X_AXIS));
        columnPane.setPreferredSize(new Dimension(getWidth(), 25));
        columnPane.setMinimumSize(new Dimension(getWidth(), 25));
        columnPane.setBackground(Color.WHITE);

        //Set up the columnPanes
        driverColHeaders = new JPanel();
        driverColHeaders.setLayout(new BorderLayout());
        driverColHeaders.setPreferredSize(new Dimension(getWidth(), getHeight()));
        
        busColHeaders = new JPanel();
        busColHeaders.setLayout(new BorderLayout());
        busColHeaders.setPreferredSize(new Dimension(getWidth(), getHeight()));

        //set up the columns
        JPanel idPane = new JPanel();
        idPane.setLayout(new GridLayout(1, 1));
        JLabel driverID = new JLabel("Driver ID");
        idPane.add(driverID);
        driverID.setHorizontalAlignment(SwingConstants.CENTER);
        idPane.setPreferredSize(new Dimension(idPane.getWidth() + 83, idPane.getHeight()));

        JPanel driverInfoPane = new JPanel();
        driverInfoPane.setLayout(new GridLayout());
        JLabel driverName = new JLabel("Driver Name");
        driverName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel driverPhone = new JLabel("Driver Phone");
        driverPhone.setHorizontalAlignment(SwingConstants.CENTER);
        driverInfoPane.add(driverName);
        driverInfoPane.add(driverPhone);

        JPanel bufferPane = new JPanel();
        bufferPane.setPreferredSize(new Dimension(bufferPane.getWidth() + 92, bufferPane.getHeight()));

        JPanel borderPane = new JPanel();
        borderPane.setLayout(new BorderLayout());
        borderPane.add(idPane, BorderLayout.WEST);
        borderPane.add(driverInfoPane, BorderLayout.CENTER);
        borderPane.add(bufferPane, BorderLayout.EAST);

        //Add columns to driverColumns pane
        driverColHeaders.add(borderPane);

        JLabel busID = new JLabel("Bus ID");

        JLabel busSeats = new JLabel("Bus Seats");
        
        busColHeaders.add(busID);
        busColHeaders.add(busSeats);

        //Add columnPanes to displayPane
        columnPane.add(driverColHeaders);
        columnPane.add(busColHeaders);
        topPanel.add(columnPane, BorderLayout.SOUTH);


        //########################################## Lists ##########################################
        //listPane is set as a horizontal boxlayout
        listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS));
        listPane.setBackground(Color.WHITE);

        //Driver List
        driverPane = new JPanel();
        driverPane.setLayout(new GridLayout(50, 1));
        driverPane.setBackground(Color.WHITE);

        //Bus List
        busPane = new JPanel();
        busPane.setLayout(new GridLayout(50, 1));
        busPane.setBackground(Color.WHITE);

        //Update list and add both bus and driver panes to their respective lists
        updateList();
        driverScrollPane  = new JScrollPane(driverPane);
        driverScrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
        busScrollPane = new JScrollPane(busPane);
        busScrollPane.setPreferredSize((new Dimension(getWidth(), getHeight())));

        //Edit the scroll bars for each scrollpane
        JScrollBar driverVertScrollBar = driverScrollPane.getVerticalScrollBar();
        driverVertScrollBar.setUnitIncrement(25);
        driverVertScrollBar.setBlockIncrement(50);
        JScrollBar busVertScrollBar = busScrollPane.getVerticalScrollBar();
        busVertScrollBar.setUnitIncrement(25);
        busVertScrollBar.setBlockIncrement(50);

        //Add lists to the listPanel
        listPane.add(driverScrollPane);
        listPane.add(busScrollPane);



        //add top panel and lists to main panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(listPane);
    }

    /**
     * Opens a pop-up window to create a bus.
     * @param e Action Event
     */
    private void createBusButton_ActionPerformed(ActionEvent e) {
        BusDialog dialogBox = new BusDialog(App.getFrame(), Local.getString("New Bus"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dialogBox.setLocation((frmSize.width - dialogBox.getSize().width) / 2 + loc.x, (frmSize.height - dialogBox.getSize().height) / 2 + loc.y);
        dialogBox.setVisible(true);
        if (dialogBox.CANCELLED)
            return;
        if(busList.hasBus(dialogBox.tempBus.getID()))
            return; //temp solution so no duplicate IDs are made
        busList.addBus(dialogBox.tempBus);

        updateList();
    }

    /**
     * Opens a pop-up window to create a driver.
     * @param e ActionEvent
     */
    private void createDriverButton_ActionPerformed(ActionEvent e) {
        DriverDialog dialogBox = new DriverDialog(App.getFrame(), Local.getString("New Driver"));
        //Driver object has (int) ID, (String) Name, (String) Phone Number

        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dialogBox.setLocation((frmSize.width - dialogBox.getSize().width) / 2 + loc.x, (frmSize.height - dialogBox.getSize().height) / 2 + loc.y);
        dialogBox.setVisible(true);
        if (dialogBox.CANCELLED)
            return;
        if(driverList.hasDriver(dialogBox.tempDriver.getID()))
            return; //temp solution so no duplicate IDs are made
        driverList.addDriver(dialogBox.tempDriver);

        updateList();
    }

    /**
     * Internal class to associate a delete button with a driver.
     */
    public class DeleteButton extends JButton {
        private final Driver driver;
        private final Bus bus;

        public DeleteButton(Driver driver) {
            this.setBackground(Color.red);
            this.setPreferredSize(new Dimension(75, 25));
            this.setText("Delete");
            this.driver = driver;
            this.bus = null;
            this.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteButton_ActionPerformed(e);
                }
            });
        }

        public DeleteButton(Bus bus) {
            this.setBackground(Color.red);
            this.setPreferredSize(new Dimension(75, 25));
            this.setText("Delete");
            this.bus = bus;
            this.driver = null;
            this.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteButton_ActionPerformed(e);
                }
            });
        }

        private void deleteButton_ActionPerformed(ActionEvent e) {
            if(this.bus == null)
                driverList.removeDriver(this.driver);
            else
                busList.removeBus(this.bus);
            updateList();
        }
    }

    /**
     * Updates the displayed list(s).
     */
    private void updateList() {
        //Remove all items from both lists
        driverPane.removeAll();
        busPane.removeAll();

        //Update the driverPane
        for(Driver driver : driverList) {
            //Create a temporary panel to hold all the driver information
            JPanel borderPane = new JPanel();
            borderPane.setLayout(new BorderLayout());

            //Create the individual sections for the driver information and the delete button
            JLabel driverID = new JLabel(driver.getID() + "  ");
            driverID.setFont(font);
            driverID.setBackground(Color.WHITE); // Set background color
            driverID.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set cell border

            JLabel driverName = new JLabel(" " + driver.getName());
            driverName.setFont(font);
            driverName.setBackground(Color.WHITE); // Set background color
            driverName.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set cell border
            
            JLabel driverPhone = new JLabel(driver.getPhone());
            driverPhone.setFont(font);
            driverPhone.setBackground(Color.WHITE); // Set background color
            driverPhone.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set cell border
            
            // Create a panel for the delete button
            JPanel idPane = new JPanel();
            idPane.setLayout(new GridLayout(1, 1));
            idPane.add(driverID);
            
            // Add the driver information to the temporary pane
            JPanel driverInfoPane = new JPanel();
            driverInfoPane.setLayout(new GridLayout());

            driverInfoPane.add(driverName);
            driverInfoPane.add(driverPhone);

            borderPane.add(idPane, BorderLayout.WEST);
            borderPane.add(driverInfoPane, BorderLayout.CENTER);
            
            DeleteButton deleteButton = new DeleteButton(driver);
            borderPane.add(deleteButton, BorderLayout.EAST);
            
            driverPane.add(borderPane);
        }
        driverPane.revalidate();
        driverPane.repaint();

        //Update the busPane
        for(Bus bus : busList) {
            //Create a temporary panel to hold all the driver information
            JPanel tempPane = new JPanel();
            tempPane.setLayout(new GridBagLayout());

            //Create the individual sections for the driver information and the delete button
            JLabel busID = new JLabel(String.valueOf(bus.getID()));
            JLabel busSeats = new JLabel(String.valueOf(bus.getSeats()));
            DeleteButton deleteButton = new DeleteButton(bus);

            //Update the properties of each item
            busID.setPreferredSize(new Dimension(100, 25));
            busSeats.setPreferredSize(new Dimension(200,25));

            //Add the items to the temporary pane
            tempPane.add(busID, createConstraints(0,0));
            tempPane.add(busSeats, createConstraints(1,0));
            tempPane.add(deleteButton, createConstraints(2,0));

            //Add the tempPane to the driverPane
            busPane.add(tempPane);
            busPane.setAlignmentX(tempPane.LEFT_ALIGNMENT);
        }
        busPane.revalidate();
        busPane.repaint();
    }

    /**
     * Generages GridBagConstraints for a given gid location.
     * @param x column parameter
     * @param y row parameter
     * @return
     */
    private GridBagConstraints createConstraints(int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        int gap = 3;
        constraints.insets = new Insets(gap, gap + 2 * gap * x, gap, gap);
        return constraints;
    }
}
