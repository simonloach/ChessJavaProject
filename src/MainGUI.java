import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame{
    private JPanel PANEL;
    private List<JPanel> panelList = new ArrayList<>();
    private String[] pola = {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
            "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
            "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
            "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
            "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
            "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
            "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
            "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"};

    public void setPanelColor(Container parent) //Działa i to się liczy
    {
        for(Component c : parent.getComponents())
        {
            if(c instanceof Container)
            {
                if(c.getName() == "a8")
                {
                    c.setBackground(Color.RED);
                }

                setPanelColor((Container)c);
            }
        }
    }

    public MainGUI() {
        GridLayout grid = new GridLayout(8,8);
        setLayout(grid);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int colorcheck = 1;
        int colorswap = 1;
        for(int i=0; i<64; i++){
            panelList.add(new JPanel());
            JLabel lab = new JLabel(pola[i]);
            panelList.get(i).setName(pola[i]);

            if(colorcheck%2 == colorswap%2){
                panelList.get(i).setBackground(Color.WHITE);
                lab.setForeground(Color.BLACK);
            }
            else{
                panelList.get(i).setBackground(Color.BLACK);
                lab.setForeground(Color.WHITE);
            }
            if(colorcheck == 8){
                colorcheck = 1;
                colorswap += 1;
            }
            else {
                colorcheck += 1;
            }
            panelList.get(i).add(lab);
            add(panelList.get(i));
        }

        //setPanelColor(this);
        pack();
    }

    private abstract class PanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
            /* source is the object that got clicked
             *
             * If the source is actually a JPanel,
             * then will the object be parsed to JPanel
             * since we need the setBackground() method
             */
            Object source = event.getSource();
            if (source instanceof JPanel) {
                JPanel panelPressed = (JPanel) source;
                panelPressed.setBackground(Color.blue);
            }
        }
    }

    public static void main(String[] args){
        /*JFrame frame = new JFrame("CHESS");
        frame.setContentPane(new MainGUI().PANEL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/

        new MainGUI().setVisible(true);
    }
}
