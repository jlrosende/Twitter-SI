package si.interfaz;

import si.agentes.TwitterListerner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class Interfaz extends JFrame {

    private String ENTER = "TwitterAgent";
    public JButton enterButton;
    public JTextArea output;
    public JTextField input;
    public JPanel panel;

    private TwitterListerner listener;

    public Interfaz(TwitterListerner listener) {

        this.listener = listener;
        this.setTitle("TwitterAgent");
        createFrame();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.pack();
        this.setLocationByPlatform(true);
        // Center of screen
        // this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(true);
    }

    public void createFrame()
    {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);

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
        enterButton.addActionListener(e -> {
            String username = input.getText();
            output.setText("Buscando Tweets de @" + username + "\n");
            listener.getUserTimeline(username);
        });

        DefaultCaret caret = (DefaultCaret) output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scroller);
        inputpanel.add(input);
        inputpanel.add(enterButton);
        panel.add(inputpanel);

        input.requestFocus();
    }
}



