import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.*;

public class Scraper extends JFrame {

    JTextField urlTextField; 
    JTable jtable;
    
    public Scraper() {
        super("Scrape the application");

        setLayout(new BorderLayout());


        urlTextField = new JTextField();
        add(urlTextField, BorderLayout.NORTH);

        String columns[] = {"ID", "NAME", "SALARY"};
        String data[][] = {{"1", "Melissa", "30000"}, {"2", "Peter", "45700"}, {"3", "Sky", "70000"}};

        jtable = new JTable(data, columns);
        // add(jtable);
        JScrollPane scrollPane = new JScrollPane(jtable);
        add(scrollPane);

        setLocation(500, 500);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
}
