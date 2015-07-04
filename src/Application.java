import view.Startscreen;
import java.awt.EventQueue;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Application {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Startscreen sc = new Startscreen();
                sc.setVisible(true);
            }
        });
    }



}
