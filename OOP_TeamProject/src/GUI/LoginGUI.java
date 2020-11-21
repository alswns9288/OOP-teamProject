package GUI;

import java.awt.event.*;
import javax.swing.*;

import coronaMap.CoronaMapMain;
import Manager.UserManager;

public class LoginGUI extends JPanel {
	UserManager userManager = UserManager.getInstance();
	JPanel background;
	JButton signUpButton;
	JButton loginButton;
	JTextField Username;
	JTextField Password;
	
	public LoginGUI() {
		setLayout(null);
		createLabel();
		createTextField();
		
		signUpButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpGUI signup = new SignUpGUI();
				signup.creatAndShowGUI();
			}
		});
		
		loginButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				userManager.login(Username.getText());
				Username.setText("");
				Password.setText("");
				JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
			}
		});
	}
	
	private void createTextField() {
		Username = new JTextField();
		Username.setLocation(150, 55);
		Username.setSize(150, 30);
		add(Username);
	
		Password = new JTextField();
		Password.setLocation(150, 105);
		Password.setSize(150, 30);
		add(Password);	
		
		signUpButton = new JButton("ȸ������");
		signUpButton.setBounds(229, 154, 104, 29);
		add(signUpButton);
		
		loginButton = new JButton("�α���");
		loginButton.setBounds(108, 154, 106, 29);
		add(loginButton);	
	}

	private void createLabel() {
		JLabel LabelLogin = new JLabel("Username");
		LabelLogin.setBounds(41, 52, 69, 35);
		add(LabelLogin);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setBounds(41, 103, 69, 35);
		add(LabelPassword);	
	}	
}