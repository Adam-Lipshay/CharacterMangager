package Main.CharacterManager;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Main extends JFrame implements ItemListener{
    static String[] Characters = new String[5];
    static JPanel Panel = new JPanel();
    static JButton UseChar = new JButton("Use Character");
    static JButton NewChar = new JButton("New Character");
    static JLabel Chars = new JLabel("Characters");
    @SuppressWarnings({ "rawtypes", "unchecked" })
    JComboBox CharSel = new JComboBox(Characters);
    static String Character = Characters[0];

    public Main() {

        setTitle("Character Manager");
        setSize(200, 150);
        CharSel.addItemListener(this);
        add(Panel);
        Panel.add(UseChar);
        Panel.add(NewChar);
        Panel.add(Chars);
        Panel.add(CharSel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            Character = e.getItem().toString();
        }
    }

    public static void CharChange(){
        UseChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    FileIO.CharChange(Character+"/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void NewChar(){
        NewChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    FileIO.output(JOptionPane.showInputDialog("Name"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) throws IOException{
        FileIO.input();
        CharChange();
        NewChar();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
