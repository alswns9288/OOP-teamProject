package GUI;

import javax.swing.*;

import coronaMap.CoronaMapMain;
import manager.UserManager;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class GUIMain extends JFrame {
	JPanel menu;
	FirstGUI firstGUI;
	PrintpplGUI printpplGUI;
	PathCompareGUI pathCompareGUI;
	LoginGUI LoginGUI;
	GetInformationGUI getInformationGUI;
	JButton main;
	JButton search1;
	JButton search2;
	JButton signIn;
	LocalDate date;

	public void createAndShowGUI() {
		GUIMain container = new GUIMain();

		container.setTitle("Corona Map");
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLocation(500, 50);
		container.setPreferredSize(new Dimension(400, 730));
		container.setResizable(false);

		setDefaultGUI(container);

		container.firstGUI = new FirstGUI();
		container.LoginGUI = new LoginGUI();

		container.add(container.firstGUI);

		container.pack();
		container.setVisible(true);
	}

	private static void setDefaultGUI(GUIMain container) {
		container.getContentPane().setLayout(new BorderLayout(0, 1));
		container.getContentPane().setBackground(Color.black);
		container.setTopMenu();
		container.setMenuButton();
	}

	public void changeGUI(String menuName) {
		UserManager userManager = UserManager.getInstance();
		
		if (menuName.contains("main")) {
			getContentPane().removeAll();
			setDefaultGUI(this);
			getContentPane().add(firstGUI);
		}
		if (menuName.contains("확진자")) {
			printpplGUI = new PrintpplGUI();
			getContentPane().removeAll();
			date = FirstGUI.getDate();
			setDefaultGUI(this);
			getContentPane().add(printpplGUI);
		}
		if (menuName.contains("경로비교")) {
			getInformationGUI = new GetInformationGUI();
			userManager.readMemberPath();
			getContentPane().removeAll();
			date = FirstGUI.getDate();
			setDefaultGUI(this);
			getContentPane().add(getInformationGUI);
		}
		if (menuName.contains("로그인")) {
			getContentPane().removeAll();
			date = FirstGUI.getDate();
			setDefaultGUI(this);
			getContentPane().add(LoginGUI);
		}
		revalidate();
		repaint();
	}

	private void setTopMenu() {
		menu = new JPanel();
		menu.setLayout(new GridLayout(1, 4));
		menu.setPreferredSize(new Dimension(400, 52));
		add(menu, BorderLayout.NORTH);
	}

	private void setMenuButton() {
		UserManager userManager = UserManager.getInstance();
		String userID = userManager.getID();

		if (date == null) {
			main = new JButton("main");
		} else {
			main = new JButton(date.toString());
		}
		main.setSize(50, 100);
		hideJButton(menu, main);

		search1 = new JButton("확진자");
		search1.setSize(50, 100);
		hideJButton(menu, search1);

		search2 = new JButton("경로비교");
		search2.setSize(50, 100);
		hideJButton(menu, search2);

		if (userID == null) {
			signIn = new JButton("로그인");
		} else {
			signIn = new JButton(userManager.getID());
		}
		signIn.setSize(50, 100);
		hideJButton(menu, signIn);

		addActionListener(main, search1, search2, signIn);
	}

	private void hideJButton(JPanel panel, JButton button) {
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		panel.add(button);
	}

	private void addActionListener(JButton... buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new MyActionListener());
		}
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == main) {
				changeGUI("main");
				main.setBackground(Color.LIGHT_GRAY);
			}
			if (e.getSource() == search1) {
				changeGUI("확진자");
				search1.setBackground(Color.LIGHT_GRAY);
			}
			if (e.getSource() == search2) {
				changeGUI("경로비교");
				search2.setBackground(Color.LIGHT_GRAY);
				
			}
			if (e.getSource() == signIn) {
				changeGUI("로그인");
				signIn.setBackground(Color.LIGHT_GRAY);
			}
		}
	}
}