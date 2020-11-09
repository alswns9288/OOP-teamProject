package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMain extends JFrame {
	static JPanel background = null;
	static Container container = null;
	
	MyMouseListener eventHandler = new MyMouseListener();
	JPanel menu = null;
	JButton previousDay = null;
	JButton nextDay = null;
	
	public void createAndShowGUI() {
		setTitle("Corona Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 50);
		setPreferredSize(new Dimension(400, 730));
		setResizable(false);
		
		container = getContentPane(); // ±âº»: BorderLayout
		container.setLayout(new BorderLayout(0, 1));
		container.setBackground(Color.black);
		setTopMenu();
		setBackImage();
		setButtonAndText();
		setMenuButton();
		
		previousDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				previousDay.setIcon(new ImageIcon("arrow_left_clicked.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				previousDay.setIcon(new ImageIcon("arrow_left.png"));
			}

		});
		nextDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				nextDay.setIcon(new ImageIcon("arrow_right_clicked.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				nextDay.setIcon(new ImageIcon("arrow_right.png"));
			}

		});
		
		pack();
		setVisible(true);
	}

	private void setButtonAndText() {
		previousDay = new JButton(new ImageIcon("arrow_left.png"));
		previousDay.setLocation(10, 10);
		previousDay.setSize(50, 50);
		hideJButton(background, previousDay);
		
		nextDay = new JButton(new ImageIcon("arrow_right.png"));
		nextDay.setLocation(330, 10);
		nextDay.setSize(50, 50);
		hideJButton(background, nextDay);
		
		JTextField field = new JTextField("test");
		field.setLocation(145, 10);
		field.setSize(100, 50);
		background.add(field);
	}

	private void hideJButton(JPanel panel, JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		panel.add(button);
	}

	private void setTopMenu() {
		menu = new JPanel();
		menu.setLayout(new GridLayout(1, 4));
		menu.setPreferredSize(new Dimension(400, 52));
		
		container.add(menu, BorderLayout.NORTH);
	}

	private void setMenuButton() {
		JButton main = new JButton("main");
		main.setSize(50, 100);
		main.addActionListener(eventHandler);
		hideJButton(menu, main);
		
		JButton search1 = new JButton("search1");
		search1.setSize(50, 100);
		search1.addActionListener(eventHandler);
		hideJButton(menu, search1);
		
		JButton search2 = new JButton("search2");
		search2.setSize(50, 100);
		search2.addActionListener(eventHandler);
		hideJButton(menu, search2);
		
		JButton signIn = new JButton("sign in");
		signIn.setSize(50, 100);
		signIn.addActionListener(eventHandler);
		hideJButton(menu, signIn);
	}

	public void setBackImage() {
		ImageIcon image = new ImageIcon("background.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				super.paintComponents(g);
			}
		};
		background.setLayout(null);
		container.add(background, BorderLayout.CENTER);
	}
	
	public static void removeMainCenter() {
		container.remove(background);
	}
}
