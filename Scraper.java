import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.*;

public class Scraper extends JFrame {

    JTextField urlTextField; 
    
    public Scraper() {
        super("Scrape the application");

        setLayout(new BorderLayout());

        urlTextField = new JTextField();
        add(urlTextField, BorderLayout.NORTH);

        setLocation(500, 500);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
}
