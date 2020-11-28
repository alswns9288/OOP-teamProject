package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUpGUI extends JFrame {

	private JPanel background;
	private JLabel labelJoin;
	private JButton joinCompleteButton;
	private JTextField TextFieldID;
	private JTextField TextFieldPassword;
	private JTextField TextFieldName;
	private JTextField TextFieldEmail;
	private JTextField TextFieldPhone;

	public void creatAndShowGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("회원가입");
		setPreferredSize(new Dimension(380, 500));
		setLocation(510, 150);
		background = new JPanel();
		setContentPane(background);
		background.setLayout(null);
		setResizable(false);
		
		setMainGUI();
		pack();
		setVisible(true);
		
		joinCompleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				dispose();			
			}
		});
	}

	private void setMainGUI() {
		createLabel();
		createTextField();
	}

	private void createTextField() {
		TextFieldID = new JTextField();
		TextFieldID.setBounds(159, 106, 186, 35);
		background.add(TextFieldID);
		
		TextFieldPassword = new JTextField();
		TextFieldPassword.setBounds(159, 156, 186, 35);
		background.add(TextFieldPassword);
		
		TextFieldName = new JTextField();
		TextFieldName.setBounds(159, 203, 186, 35);
		background.add(TextFieldName);
		
		TextFieldEmail = new JTextField();
		TextFieldEmail.setBounds(159, 250, 186, 35);
		background.add(TextFieldEmail);
		
		TextFieldPhone = new JTextField();
		TextFieldPhone.setBounds(159, 297, 186, 35);
		background.add(TextFieldPhone);
		
		joinCompleteButton = new JButton("회원가입완료");
		joinCompleteButton.setBounds(206, 363, 139, 29);
		background.add(joinCompleteButton);	
	}

	private void createLabel() {
		labelJoin = new JLabel("회원가입");
		labelJoin.setBounds(160, 41, 101, 20);
		background.add(labelJoin);
		
		JLabel labelID = new JLabel("ID");
		labelID.setBounds(69, 113, 69, 20);
		background.add(labelID);
		
		JLabel labelpassword = new JLabel("Password");
		labelpassword.setBounds(69, 163, 69, 20);
		background.add(labelpassword);
		
		JLabel labelName = new JLabel("Name");
		labelName.setBounds(69, 210, 69, 20);
		background.add(labelName);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(69, 257, 69, 20);
		background.add(labelEmail);
		
		JLabel labelPhone = new JLabel("Phone");
		labelPhone.setBounds(69, 304, 69, 20);
		background.add(labelPhone);	
	}
}