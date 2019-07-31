package utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MyDialog extends JDialog {
	
	JLabel label;
	JTextField textField;
	JButton jButton;
	
	
	public MyDialog(JFrame frame){	
		super(frame, "GAME OVER", true);
		init();
	}
	
	public void init() {
		
		label = new JLabel("Enter your name");
		label.setFont(new Font("Century Gothic", Font.BOLD, 30));
		label.setHorizontalAlignment(JLabel.CENTER);
		
		textField = new JTextField();
		textField.setFont(new Font("Century Gothic", Font.BOLD, 20));
		
		
		jButton = new JButton("enter");
		jButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
		jButton.setPreferredSize(new Dimension(50, 35));
		jButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				}
			);
		
		
		add(label, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);
		add(jButton , BorderLayout.SOUTH);
		
		
		setSize(270, 150);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, 
				    (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	public String showMyDialog() {
		setVisible(true);
		if(textField.getText().equals(null))
			return null;
		if(textField.getText().length() >= 15)
			return textField.getText().substring(0, 14);
		return textField.getText();
	}
		
}

