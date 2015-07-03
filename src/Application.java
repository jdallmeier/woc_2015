import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 * Created by Jonas on 03.07.2015.
 */
public class Application extends JFrame {


    public Application() {

        initUI();
    }

    private void initUI() {

        setTitle("Name des Spiels");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }



}
