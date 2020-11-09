package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstGUI extends JPanel {
	GUIMain container;
	JPanel background;
	JButton previousDay;
	JButton nextDay;
	ImageIcon image = new ImageIcon("background.png");

	public FirstGUI(GUIMain container) {
		this.container = container;
		setLayout(null);
		setButtonAndText();
		
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
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image.getImage(), 0, 0, null);
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
		add(field);
	}

	private void hideJButton(JPanel panel, JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		add(button);
	}
}
