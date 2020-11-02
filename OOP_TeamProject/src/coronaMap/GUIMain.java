package coronaMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMain extends JFrame {
	Container container = null;
	JPanel background = null;
	JButton previousDay = null;
	JButton nextDay = null;
	
	public void creatAndShowGUI() {
		setTitle("Corona Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 50);
		setPreferredSize(new Dimension(400, 730));
		setResizable(false);
		
		setMainGUI();
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
		
		pack();
		setVisible(true);
	}

	private void setButtonAndText() {
		previousDay = new JButton(new ImageIcon("arrow_left.png"));
		previousDay.setLocation(10, 10);
		hideJButton(previousDay);
		nextDay = new JButton(new ImageIcon("arrow_right.png"));
		nextDay.setLocation(330, 10);
		hideJButton(nextDay);
		
		JTextField field = new JTextField("test");
		field.setLocation(145, 10);
		field.setSize(100, 50);
		background.add(field);
	}

	private void hideJButton(JButton button) {
		button.setSize(50, 50);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		background.add(button);
	}

	private void setMainGUI() {
		container = getContentPane();
		setTopButton();		
		setBackImage();
	}

	private void setTopButton() {
		JPanel test = new JPanel();
		test.setLayout(new BorderLayout());
		
		JButton button1 = new JButton("검색");
		button1.setPreferredSize(new Dimension(128, 50));
		JButton button2 = new JButton("검색");
		button2.setPreferredSize(new Dimension(128, 50));
		JButton button3 = new JButton("로그인");
		button3.setPreferredSize(new Dimension(128, 50));
		
		test.add(button1, BorderLayout.WEST);
		test.add(button2, BorderLayout.CENTER);
		test.add(button3, BorderLayout.EAST);
		
		container.add(test, BorderLayout.NORTH);
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
}
