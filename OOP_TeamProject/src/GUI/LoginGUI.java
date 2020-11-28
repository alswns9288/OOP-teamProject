package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

import GUI.GUIMain.MyActionListener;
import coronaMap.CoronaMapMain;
import manager.UserManager;

public class LoginGUI extends JPanel {
	UserManager userManager = UserManager.getInstance();
	JPanel background;
	JButton signUpButton;
	JButton loginButton;
	JButton logoutButton;
	JButton management;
	JTextField Username;
	JPasswordField Password;
	
	public LoginGUI() {
		createAndShow();
	}
	
	private void createAndShow() {
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
				handleLogin();
			}
		});
	}
	
	protected void handleLogin() {
		boolean state = false;
		
		state = userManager.login(Username.getText(), Password.getText());
		setFieldClear();
		
		if (state) {
			JOptionPane.showMessageDialog(null, "로그인 되었습니다");
			userManager.readMemberPath();
			
			setLoginedGUI();
			return;
		}
		JOptionPane.showMessageDialog(null, "로그인 실패!");
		userManager.setID(null);
	}

	private void setLoginedGUI() {
		removeAll();

		logoutButton = new JButton("로그아웃");
		logoutButton.setBounds(50, 400, 290, 50);
		add(logoutButton);
		addActionListener(logoutButton);
		if (userManager.isAdmin()) {
			management = new JButton("관리");
			management.setBounds(50, 460, 290, 50);
			add(management);
			addActionListener(management);
		}
		revalidate();
		repaint();
	}
	
	private void addActionListener(JButton... buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new MyActionListener());
		}
	}

	private void setFieldClear() {
		Username.setText("");
		Password.setText("");
	}

	private void createTextField() {
		Username = new JTextField();
		Username.setLocation(150, 55);
		Username.setSize(150, 30);
		add(Username);
	
		Password = new JPasswordField();
		Password.setLocation(150, 105);
		Password.setSize(150, 30);
		add(Password);	
		
		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(229, 154, 104, 29);
		add(signUpButton);
		
		loginButton = new JButton("로그인");
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
	
	
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == logoutButton) {
				removeAll();
				userManager.userList.clear(); // 로그아웃했다가 다시 로그인하면 정보 비우기
				userManager.setID(null);
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
				createAndShow();
				revalidate();
				repaint();
			}
			
			if (e.getSource() == management) {
				ManagerModeGUI management = new ManagerModeGUI();
				management.createAndShow();
			}
		}
	}
}