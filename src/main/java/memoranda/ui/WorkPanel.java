package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;

/**
 * Copyright (c) 2003 Memoranda Team.
 * http://memoranda.sf.net
 */

/*$Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class WorkPanel extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	JToolBar toolBar = new JToolBar();
	JPanel panel = new JPanel();
	CardLayout cardLayout1 = new CardLayout();
//**********************************************REMOVE vvvvvvvvvv
	//public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
	public MapPanel mapPanel = new MapPanel();
	public RoutePanel routePanel = new RoutePanel();
	public BusAndDriverPanel busAndDriverPanel = new BusAndDriverPanel();
	public JButton busAndDriverButton = new JButton();
	public JButton routeButton = new JButton();
	public JButton mapButton = new JButton();
	JButton currentButton = null;
	Border border1;

	/**
	 * General Constructor.
	 */
	public WorkPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	/**
	 * Default initialization for the application interface.
	 * @throws Exception when exception thrown.
	 */
	void jbInit() throws Exception {
		//Set the border
		border1 =
			BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(
					BevelBorder.LOWERED,
					Color.white,
					Color.white,
					new Color(124, 124, 124),
					new Color(178, 178, 178)),
				BorderFactory.createEmptyBorder(0, 2, 0, 0));
		this.setLayout(borderLayout1);
		//Set up the toolbar on the left side
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setBackground(Color.white);
		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		//Add the toolbar to the layout
		panel.setLayout(cardLayout1);
		//Set the preferredSize of the window
		this.setPreferredSize(new Dimension(1073, 300));

		// ################################ BUTTONS ################################
		//Route Button
		routeButton.setBackground(Color.white);
		routeButton.setMaximumSize(new Dimension(60, 80));
		routeButton.setMinimumSize(new Dimension(30, 30));
		routeButton.setFont(new java.awt.Font("Dialog", 1, 10));
		routeButton.setPreferredSize(new Dimension(50, 50));
		routeButton.setBorderPainted(false);
		routeButton.setContentAreaFilled(false);
		routeButton.setFocusPainted(false);
		routeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		routeButton.setText(Local.getString("Routes"));
		routeButton.setVerticalAlignment(SwingConstants.TOP);
		routeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		routeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				routeButton_actionPerformed(e);
			}
		});
		routeButton.setIcon(
				new ImageIcon(
						main.java.memoranda.ui.AppFrame.class.getResource(
								"/ui/icons/routes.png")));
		routeButton.setOpaque(false);
		routeButton.setMargin(new Insets(0, 0, 0, 0));

		//Bus and Driver button
		busAndDriverButton.setSelected(true);
		busAndDriverButton.setFont(new java.awt.Font("Dialog", 1, 9));
		busAndDriverButton.setMargin(new Insets(0, 0, 0, 0));
		busAndDriverButton.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/bus-driver.png")));
		busAndDriverButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		busAndDriverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busDriverButton_actionPerformed(e);
			}
		});
		busAndDriverButton.setVerticalAlignment(SwingConstants.TOP);
		busAndDriverButton.setText(Local.getString("<html><center>Buses &amp;<br>Drivers</center></html>"));
		busAndDriverButton.setHorizontalTextPosition(SwingConstants.CENTER);
		busAndDriverButton.setFocusPainted(false);
		busAndDriverButton.setBorderPainted(false);
		busAndDriverButton.setContentAreaFilled(false);
		busAndDriverButton.setPreferredSize(new Dimension(50, 50));
		busAndDriverButton.setMinimumSize(new Dimension(30, 30));
		busAndDriverButton.setOpaque(false);
		busAndDriverButton.setMaximumSize(new Dimension(60, 80));
		busAndDriverButton.setBackground(Color.white);
		
		//Map Button
		mapButton.setSelected(true);
		mapButton.setMargin(new Insets(0, 0, 0, 0));
		mapButton.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/map.png")));
		mapButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		mapButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapButton_actionPerformed(e);
			}
		});
		mapButton.setFont(new java.awt.Font("Dialog", 1, 10));
		mapButton.setVerticalAlignment(SwingConstants.TOP);
		mapButton.setText(Local.getString("Map"));
		mapButton.setHorizontalTextPosition(SwingConstants.CENTER);
		mapButton.setFocusPainted(false);
		mapButton.setBorderPainted(false);
		mapButton.setContentAreaFilled(false);
		mapButton.setPreferredSize(new Dimension(50, 50));
		mapButton.setMinimumSize(new Dimension(30, 30));
		mapButton.setOpaque(false);
		mapButton.setMaximumSize(new Dimension(60, 80));
		mapButton.setBackground(Color.white);
		this.add(toolBar, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);
		panel.add(mapPanel, "MAP");
		panel.add(routePanel,"Routes");
		panel.add(busAndDriverPanel, "Buses/Drivers");
		toolBar.add(routeButton, null);
		toolBar.add(busAndDriverButton, null);
		toolBar.add(mapButton, null);
		currentButton = routeButton;

		// Default blue color for the current button
		currentButton.setBackground(new Color(215, 225, 250));
		currentButton.setOpaque(true);
		//Set borders to null
		toolBar.setBorder(null);
		panel.setBorder(null);
		mapPanel.setBorder(null);
	}

	/**
	 * Selects the panel to display.
	 * @param panel the panel label
	 */
	public void selectPanel(String panel) {
		if (panel != null) {
			if (panel.equals("EVENTS"))
				routeButton_actionPerformed(null);
			else if (panel.equals("MAP"))
				mapButton_actionPerformed(null);
			else if (panel.equals("BUSDRIVERS"))
				busDriverButton_actionPerformed(null);
		}
	}

	/**
	 * Actions taken when busDriverButton selected.
	 * @param e the event
	 */
	public void busDriverButton_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "Buses/Drivers"); //switches to the correct window
		setCurrentButton(busAndDriverButton);
		Context.put("CURRENT_PANEL", "Buses/Drivers");
	}

	/**
	 * Actions taken when routeButton selected.
	 * @param e the event
	 */
	public void routeButton_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "Routes");
		setCurrentButton(routeButton);
		Context.put("CURRENT_PANEL", "Routes");
	}

	/**
	 * Actions taken when mapButton selected.
	 * @param e the event
	 */
	public void mapButton_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "MAP");
		setCurrentButton(mapButton);
		Context.put("CURRENT_PANEL", "MAP");
	}

	/**
	 * Sets the currentButton button.
	 * @param currentButton currentButton button
	 */
	void setCurrentButton(JButton currentButton) {
		this.currentButton.setBackground(Color.white);
		this.currentButton.setOpaque(false);
		this.currentButton = currentButton;
		// Default color blue
		this.currentButton.setBackground(new Color(215, 225, 250));
		this.currentButton.setOpaque(true);
	}
}