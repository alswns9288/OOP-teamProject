package coronaMap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame {

	private JPanel background;
	private JTextField TextFieldUsername;
	private JTextField TextFieldPassword;
	private JButton signUpButton;
	private JButton loginButton;

	public static void main(String[] args) {
		LoginGUI login = new LoginGUI();
		login.creatAndShowGUI();
	}

	public void creatAndShowGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("로그인");
		setPreferredSize(new Dimension(400, 300));
		setLocation(500, 50);
		background = new JPanel();
		setContentPane(background);
		background.setLayout(null);
		setResizable(false);
		
		setMainGUI();
		pack();
		setVisible(true);
		
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
				JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
				dispose();
			}
		});
	}

	private void setMainGUI() {
		createLabel();
		createTextField();
	}

	private void createTextField() {
		TextFieldUsername = new JTextField();
		TextFieldUsername.setBounds(157, 52, 176, 35);
		background.add(TextFieldUsername);
		
		TextFieldPassword = new JTextField();
		TextFieldPassword.setBounds(157, 103, 176, 35);
		background.add(TextFieldPassword);	
		
		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(229, 154, 104, 29);
		background.add(signUpButton);
		
		loginButton = new JButton("로그인");
		loginButton.setBounds(108, 154, 106, 29);
		background.add(loginButton);	
	}

	private void createLabel() {
		JLabel LabelLogin = new JLabel("Username");
		LabelLogin.setBounds(41, 52, 69, 35);
		background.add(LabelLogin);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setBounds(41, 103, 69, 35);
		background.add(LabelPassword);	
	}	
}