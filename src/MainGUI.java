import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame{
    private JPanel PANEL;
    private List<JPanel> panelList = new ArrayList<>();
    private String[] pola = {"a8", "a7", "a6", "a5", "a4", "a3", "a2", "a1",
            "b8", "b7", "b6", "b5", "b4", "b3", "b2", "b1"};

    public void setPanelColor(Container parent) //Działa i to się liczy
    {
        for(Component c : parent.getComponents())
        {
            if(c instanceof Container)
            {
                if(c instanceof JPanel)
                {
                    c.setBackground(Color.BLACK);
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
        for(int i=0; i<16; i++){
            JPanel x = new JPanel();
            JLabel lab = new JLabel(pola[i]);
            x.setName(pola[i]);
            x.add(lab);
            if(colorcheck%2 == colorswap%2){
                x.setBackground(Color.WHITE);
            }
            else{
                x.setBackground(Color.BLACK);
            }
            if(colorcheck == 8){
                colorcheck = 1;
                colorswap += 1;
            }
            add(x);
        }


        pack();
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
