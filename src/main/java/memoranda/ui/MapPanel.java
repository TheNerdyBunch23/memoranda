package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import main.java.memoranda.*;
import main.java.memoranda.EventsManager;
import main.java.memoranda.EventsScheduler;
import main.java.memoranda.History;
import main.java.memoranda.MapGenerator;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.*;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

public class
MapPanel extends JPanel {
	
	BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane scrollPane = new JScrollPane();
    JsonHandler jsonHandler = new JsonHandler();
    MapGenerator mapGen;
    Route route;

    public MapPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        
        jsonHandler.readNodesFromJSON("nodes1.json");
        mapGen = new MapGenerator(jsonHandler.nodes);
        route = new Route(5.0);

        this.setLayout(borderLayout1);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.getViewport().add(mapGen);
        mapGen.repaint();
        JScrollBar vertScrollBar = scrollPane.getVerticalScrollBar();
        vertScrollBar.setUnitIncrement(25);
        vertScrollBar.setBlockIncrement(50);
        this.add(scrollPane, BorderLayout.CENTER);

        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
    }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {

            }
        }
    }
}