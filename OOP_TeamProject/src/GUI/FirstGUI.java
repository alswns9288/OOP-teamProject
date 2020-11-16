package GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import GUI.GUIMain.MyActionListener;
import coronaMap.CoronaMapMain;

public class FirstGUI extends JPanel {
	JPanel background;
	JTextField dateField;
	JButton previousDay;
	JButton nextDay;
	JButton number;
	ImageIcon image = new ImageIcon("background.png");
	LocalDate date = LocalDate.now();
	DateTimeFormatter toString = DateTimeFormatter.ofPattern("Y / M / d");
	int infectedNumber;
	
	public FirstGUI() {
		setLayout(null);
		setButtonAndText();
		setInfectedNumber();
		
		previousDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				previousDay.setIcon(new ImageIcon("arrow_left_clicked.png"));
				date = date.minusDays(1);
				modifyNumberAndDate();
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
				modifyNumberAndDate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				nextDay.setIcon(new ImageIcon("arrow_right.png"));
			}
		});
	}
	
	private void modifyNumberAndDate() {
		dateField.setText(date.format(toString));
		infectedNumber = CoronaMapMain.peopleManagement.searchNumber(date.format(DateTimeFormatter.ofPattern("M/d")));
		number.setText(infectedNumber + "∏Ì");
	}

	private void setInfectedNumber() {
		number = new JButton(infectedNumber + "∏Ì", new ImageIcon("showNumber.png"));
		number.setLocation(150, 350);
		number.setSize(90, 70);
		number.setHorizontalTextPosition(JButton.HORIZONTAL);
		number.setForeground(Color.white);
		number.setFont(new Font("∞ÌµÒ√º", Font.BOLD, 18));
		hideJButton(number);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image.getImage(), 0, 0, null);
	}

	private void setButtonAndText() {
		previousDay = new JButton("pre", new ImageIcon("arrow_left.png"));
		previousDay.setFont(new Font("∞ÌµÒ√º", Font.BOLD, 0));
		previousDay.setLocation(10, 10);
		previousDay.setSize(55, 50);
		hideJButton(previousDay);

		nextDay = new JButton("next", new ImageIcon("arrow_right.png"));
		nextDay.setFont(new Font("∞ÌµÒ√º", Font.BOLD, 0));
		nextDay.setLocation(325, 10);
		nextDay.setSize(55, 50);
		hideJButton( nextDay);

		dateField = new JTextField(date.format(toString));
		dateField.setLocation(150, 10);
		dateField.setSize(100, 50);
		add(dateField);
	}

	private void hideJButton(JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		add(button);
	}
}
