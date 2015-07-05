package view;

import javax.swing.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Startscreen extends JFrame {
    /*private JPanel ContentPane = null;
    private JButton start;
    private JButton end;

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
            ContentPane.setBorder(new EmptyBorder(120, 0, 0, 0));

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new FlowLayout());
            JLabel label = new JLabel("Name des Spiels");
            labelPanel.add(label);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            start = new JButton("Spiel starten");
            start.addActionListener(this);
            buttonPanel.add(start);
            end = new JButton("Spiel verlassen");
            end.addActionListener(this);
            buttonPanel.add(end);

            ContentPane.add(labelPanel, BorderLayout.PAGE_START);
            ContentPane.add(buttonPanel, BorderLayout.CENTER);
        }
        return ContentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            JOptionPane.showMessageDialog(null, "test");
            Gui gui = new Gui();
            Model model = new Model(gui.getWidth(), gui.getHeight());
            Controller controller = new Controller();
        }else if(e.getSource() == end){
            System.exit(0);
        }


    }*/
}
