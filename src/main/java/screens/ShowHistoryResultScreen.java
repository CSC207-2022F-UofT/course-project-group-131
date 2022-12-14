package screens;
// Packages to import

import javax.swing.*;

public class ShowHistoryResultScreen {

    // frame
    JFrame f;
    // Table
    JTable j;


    // Constructor
    public ShowHistoryResultScreen(String[][] data) {
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("History");

        // Data to be displayed in the JTable
        //String[][] data = {
        // { "Kundan Kumar Jha", "4031", "CSE" },
        //     { "Anand Jha", "6014", "IT" }
        //};

        // Column Names
        String[] columnNames = {"DateTime", "Username", "Action", "ItemName", "Quantity", "Serinum"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(40, 50, 300, 400);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }
}




