package API.Refresh;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*Example for Refresh Button on Dashboard Page...need to modify depends on the main Frame of GUI */

public class Refresher extends JButton {

   public Refresher(InfoUpdater infoUpdater) {
    super("Refresh ");
        //ImageIcon refreshIcon = new ImageIcon("C:\\Users\\Test\\AppData\\Local\\Temp\\69a78785-3fa5-40ed-ba0e-9f90386e2834_icons8-17387-0-73139-refresh-links-32-pfeile-ios-16-glyph.zip.834\\icons8-17387-0-73139-refresh-links-32-pfeile-60.png");
        //setIcon(refreshIcon);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, trigger refresh in the InfoUpdater
                infoUpdater.performRefresh();
                // Perform any additional actions after refresh if needed
                JOptionPane.showMessageDialog(null, "Data is refreshed!");
            }
        });
    }

    public static void main(String[] args) {
        // Create an instance of InfoUpdater
        InfoUpdater infoUpdater = new InfoUpdater();

        // Create a JFrame (Dashboard) for testing
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of Refresher and pass the InfoUpdater instance
        Refresher refresher = new Refresher(infoUpdater);

        frame.getContentPane().add(refresher);

        frame.setVisible(true);
    }
}

