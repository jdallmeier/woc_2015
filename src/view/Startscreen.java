package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Startscreen extends JFrame{
    private JPanel ContentPane = null;

    public Startscreen() {
        super();
        initialize();
    }
    private void initialize() {
        this.setTitle("Wurst");
        this.setSize(600, 400);
        this.setContentPane(getJContentPane());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel getJContentPane(){
        if(ContentPane == null){
            ContentPane = new JPanel();
            ContentPane.setLayout(new BorderLayout());
            ContentPane.setBorder(new EmptyBorder(20,20,20,20));

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new FlowLayout());
            JLabel label = new JLabel("Name des Spiels");
            labelPanel.add(label);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton start = new JButton("Spiel starten");
            buttonPanel.add(start);
            JButton end = new JButton("Spiel verlassen");
            buttonPanel.add(end);

            ContentPane.add(labelPanel, BorderLayout.NORTH);
            ContentPane.add(buttonPanel, BorderLayout.CENTER);
        }
        return ContentPane;
    }
}
