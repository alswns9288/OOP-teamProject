package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMain extends JFrame {
	JPanel menu;	
	FirstGUI firstGUI;
	PrintpplGUI printpplGUI;
	PathCompareGUI pathCompareGUI;
	LoginGUI LoginGUI;
	GetInformationGUI getInformationGUI;
	
	
	public void createAndShowGUI() {
		GUIMain container = new GUIMain();
		
		container.setTitle("Corona Map");
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLocation(500, 50);
		container.setPreferredSize(new Dimension(400, 730));
	//	container.setResizable(false);
		
		setDefaultGUI(container);
		
		container.firstGUI = new FirstGUI();
		container.printpplGUI = new PrintpplGUI();
		container.pathCompareGUI = new PathCompareGUI();
		container.LoginGUI = new LoginGUI();
		container.getInformationGUI = new GetInformationGUI();
		
		container.add(container.firstGUI);
		
		container.pack();
		container.setVisible(true);
	}
	
	private void setDefaultGUI(GUIMain container) {
		container.getContentPane().setLayout(new BorderLayout(0, 1));
		container.getContentPane().setBackground(Color.black);
		container.setTopMenu();
		container.setMenuButton();
	}

	public void changeGUI(String menuName) {
		if (menuName.contains("main")) {
			getContentPane().removeAll();
			setDefaultGUI(this);
			getContentPane().add(firstGUI);
			revalidate();
			repaint();
		}
		if (menuName.contains("search1")) {
			getContentPane().removeAll();
			setDefaultGUI(this);
			getContentPane().add(printpplGUI);
			revalidate();
			repaint();
		}
		if (menuName.contains("search2")) {
			getContentPane().removeAll();
			setDefaultGUI(this);
			getContentPane().add(getInformationGUI);
			revalidate();
			repaint();
		}
		if (menuName.contains("sign in")) {
			getContentPane().removeAll();
			setDefaultGUI(this);
			getContentPane().add(LoginGUI);
			revalidate();
			repaint();
		}
	}

	private void setTopMenu() {
		menu = new JPanel();
		menu.setLayout(new GridLayout(1, 4));
		menu.setPreferredSize(new Dimension(400, 52));
		
		add(menu, BorderLayout.NORTH);
	}

	private void setMenuButton() {
		JButton main = new JButton("main");
		main.setSize(50, 100);
		hideJButton(menu, main);
		
		JButton search1 = new JButton("search1");
		search1.setSize(50, 100);
		hideJButton(menu, search1);
		
		JButton search2 = new JButton("search2");
		search2.setSize(50, 100);
		hideJButton(menu, search2);
		
		JButton signIn = new JButton("sign in");
		signIn.setSize(50, 100);
		hideJButton(menu, signIn);
		
		addActionListener(main, search1, search2, signIn);
	}
	
	private void hideJButton(JPanel panel, JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
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
			JButton button = (JButton) e.getSource();
			
			if (button.getText().contentEquals("main")) {
				changeGUI("main");
			}
			if (button.getText().contentEquals("search1")) {
				changeGUI("search1");
			}
			if (button.getText().contentEquals("search2")) {
				changeGUI("search2");
			}
			if (button.getText().contentEquals("sign in")) {
				changeGUI("sign in");
			}
		}
	}
}