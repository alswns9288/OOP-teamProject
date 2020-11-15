package GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class FirstGUI extends JPanel {
	JPanel background;
	JButton previousDay;
	JButton nextDay;
	JButton number;
	ImageIcon image = new ImageIcon("background.png");
	LocalDate date = LocalDate.now();
	DateTimeFormatter toString = DateTimeFormatter.ofPattern("Y / M / d");
	
	public FirstGUI() {
		setLayout(null);
		setButtonAndText();
		setInfectedNumber();
		
		previousDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				previousDay.setIcon(new ImageIcon("arrow_left_clicked.png"));
				date = date.minusDays(1);
				System.out.println(date);
				modifyNumber();
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
				date = date.plusDays(1);
				System.out.println(date);
				modifyNumber();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				nextDay.setIcon(new ImageIcon("arrow_right.png"));
			}
		});
	}
	
	private void modifyNumber() {
		// TODO Auto-generated method stub
		
	}

	private void setInfectedNumber() {
		number = new JButton("xx", new ImageIcon("showNumber.png"));
		number.setLocation(150, 300);
		number.setSize(90, 70);
		number.setHorizontalTextPosition(JButton.HORIZONTAL);
		number.setForeground(Color.white);
		number.setFont(new Font("∞ÌµÒ√º", Font.BOLD, 20));
		hideJButton(number);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image.getImage(), 0, 0, null);
	}

	private void setButtonAndText() {
		previousDay = new JButton(new ImageIcon("arrow_left.png"));
		previousDay.setLocation(10, 10);
		previousDay.setSize(50, 50);
		hideJButton(previousDay);

		nextDay = new JButton(new ImageIcon("arrow_right.png"));
		nextDay.setLocation(330, 10);
		nextDay.setSize(50, 50);
		hideJButton( nextDay);

		JTextField field = new JTextField(date.format(toString));
		field.setLocation(150, 10);
		field.setSize(100, 50);
		add(field);
	}

	private void hideJButton(JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		add(button);
	}
}
