import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Scraper extends JFrame {

    JTextField urlTextField; 
    DefaultTableModel tableModel;
    JTable jTable;
    JComboBox<String> regexComboBox;
    JButton btn;
    JButton resetbtn;
    HashSet<String> matches = new HashSet<String>();

    
    public Scraper() {
        super("Scrape the application");

        setLayout(new BorderLayout());


        urlTextField = new JTextField("Enter URL");
        add(urlTextField, BorderLayout.NORTH);

        // String columns[] = {"ID", "NAME", "SALARY"};
        // String data[][] = {{"1", "Melissa", "30000"}, {"2", "Peter", "45700"}, {"3", "Sky", "70000"}};

        tableModel = new DefaultTableModel();
        jTable = new JTable(tableModel);

        tableModel.addColumn("Line #");
        tableModel.addColumn("RESULT");

        // jtable = new JTable(data, columns);
        // // add(jtable);
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane);

        JPanel southJPanel = new JPanel();

        regexComboBox = new JComboBox<>();
        regexComboBox.addItem("\\d{3}-\\d{3}\\-\\d{4}");
        regexComboBox.addItem("[0-9]");
        regexComboBox.addItem("[A-Za-z0-9\\.]+\\@[A-Za-z0-9]+\\.[A-Za-z0-9]+");

        // regexTextField = new JTextField("Enter a REGEX", 20);

        southJPanel.add(regexComboBox);

        btn = new JButton("Click me!");
        btn.addActionListener(this::SearchPage);
        // btn.addActionListener(e -> SearchPage(e)); //this is works in place of the above line
    
        resetbtn = new JButton("Reset");
        resetbtn.addActionListener(this::Reset);
        southJPanel.add(btn);
        southJPanel.add(resetbtn);

        add(southJPanel, BorderLayout.SOUTH);

        setLocation(500, 500);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public void Reset(ActionEvent e) {
        tableModel.setRowCount(0);
        matches.clear();
    }
    public void SearchPage(ActionEvent e) {
        //tableModel.setRowCount(0);
        try{  //wrapped it in a try catch, because it was not handling an exception.
            URL url = new URL(urlTextField.getText()); //the constructor we are using, 'URL', is deprecated and subject to removal in the future..
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = null;



            while((line = bufferedReader.readLine()) != null) { //while we have lines to read... 
                // regex pattern matching
                Pattern pattern = Pattern.compile(regexComboBox.getSelectedItem().toString());
                java.util.regex.Matcher match = pattern.matcher(line);

                // add to our table
                if(match.find()){
                    if(matches.contains(match.group())){
                    //do nothing
                } else {
                    var grouping = match.group();
                    matches.add(match.group());
                    tableModel.addRow(new Object[]{String.valueOf(tableModel.getRowCount() + 1), match.group()});
                }
                }

            }

        }catch(Exception exception){
            //
        }

    }

}
//   \d{3}-\d{3}\-\d{4}


/// [A-Za-z0-9\.]+\@[A-Za-z0-9]+\.[A-Za-z0-9]+