package MyFirstproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow {
	
	private JFrame frame;
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	private JLabel label1;
	private JLabel label2;
	
	public MainWindow() {

		createFrame();
		BorderLayout();
		createLabel();
		createLabelClose();
		createButton();
		createButton2();
		createPanel();
		
		frame.setVisible(true);
	}



	private void BorderLayout() {
		
		
	}

	public void createFrame() {
		
		frame = new JFrame("My First Java Program");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		
	}
	
	public void createPanel() {
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		GridBagConstraints a = new GridBagConstraints();
		a.insets = new Insets(20,20,20,20);
		GridBagConstraints b = new GridBagConstraints();
		b.insets = new Insets(20,20,20,20);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,20,20,20);
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(20,20,20,20);
	   
	   
		
		
		a.gridx = 0;
		a.gridy = 0;
		panel.add(label1, a);
		b.gridx = 0;
		b.gridy = 1;
		panel.add(label2, b);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(button1, c);
		d.gridx = 1;
		d.gridy = 1;
		panel.add(button2, d);
		frame.add(panel);
	}
	
	public void createButton() {
		
		button1 = new JButton("Open");
	}
		
	public void createButton2() {
		
		button2 = new JButton("Close");
	}
	
	public void createLabelClose() {
		
		label1 = new JLabel("Open text file:");
		
	}
	  
	public void createLabel() {
		
		label2 = new JLabel("Exit application:");
	}
	 
	
	public static void main(String[] args) {
	
	new MainWindow();
	
	}
}
