package si.twitter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class Interfaz extends JFrame{
	
	private static String ENTER = "test";
    static JButton enterButton;
    public static JTextArea output;
    public static JTextField input;
    static JFrame frame;
    static JPanel panel;
    public static String testString = "test";

    public static void main(String... args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        createFrame();
    }

    public static void createFrame()
    {
        frame = new JFrame("Bulo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        ButtonListener buttonListener = new ButtonListener();
        output = new JTextArea(15, 50);
        output.setWrapStyleWord(true);
        output.setEditable(false);
        JScrollPane scroller = new JScrollPane(output);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        input = new JTextField(20);
        enterButton = new JButton("Comprueba");
        enterButton.setActionCommand(ENTER);
        enterButton.addActionListener((ActionListener) buttonListener);
        // enterButton.setEnabled(false);
        input.setActionCommand(ENTER);
        input.addActionListener(buttonListener);
        DefaultCaret caret = (DefaultCaret) output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scroller);
        inputpanel.add(input);
        inputpanel.add(enterButton);
        panel.add(inputpanel);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        // Center of screen
        // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        input.requestFocus();
    }

    public static class ButtonListener implements ActionListener
    {

        public void actionPerformed(final ActionEvent ev)
        {
//            if (!input.getText().trim().equals(""))
//            {
//                String cmd = ev.getActionCommand();
//                if (ENTER.equals(cmd))
//                {
//                    output.append(input.getText());
//                    if (input.getText().trim().equals(testString)) output.append(" = " + testString);
//                    else output.append(" != " + testString);
//                    output.append("\n");
//                }
//            }
            input.setText("");
            input.requestFocus();
        }
    }
	
	
	
//	private JFrame frame;
//	private Container panel;
//	private JButton b;
//	private static TextField Text;
//	
//	
//	public static void main(String[] args) {  
//		
//		try {				
//		JFrame f=new JFrame("Bulos");  
//	    JButton b=new JButton("Click");  
//	  
//	    
//	    b.setBounds(50,150,95,30);  
//	    f.add(b);  
//	    f.setSize(600,400);  
//	    
//	   
//	    
//	   
//	
//	    
//	  
//	    JLabel etiquetaNombre = new JLabel("@: ", JLabel.RIGHT);
//	 
//	    JTextField campoNombre = new JTextField();
//	    f.add(etiquetaNombre);
//	    f.setVisible(true); 
//		}
//	    
//		
//		catch (Exception e) {
//			e.printStackTrace();
//		}
	}  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//	
//	public Interfaz(){
//		initialize();
//	
//		
//	}
//	public static void main(String args[]) {
//		try {
//		Interfaz aplicacion = new Interfaz();
//		
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	private void initialize() {
//		frame = new JFrame("comprueba");
//		b= new JButton("Comprueba");
//		
//
//	    b.setBounds(50, 100, 95, 30);
//	  
//	    frame.add(b);
//	    frame.setSize(600,400);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
//



