package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import coronaMap.*;


public class GetInformationGUI extends JPanel {
	JPanel inner = new JPanel();
	JTextField placeField = new JTextField();
	JTextField dateField = new JTextField();
	JTextField timeField = new JTextField();
	JButton addButton = new JButton("add");
	String date;
	User user;
	
	public GetInformationGUI() {
		setLayout(new BorderLayout());
		setUserArea();
		setShowArea();
		addActionListener(addButton);
	}

	private void setShowArea() {
		String header[] = {"Date", "Place", "Time"};
		String contents[][] = {};
		
		JTable table = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(table);
		
		jscrollPane.setPreferredSize(new Dimension(400, 500));
		add(jscrollPane, BorderLayout.CENTER);
	}

	private void setUserArea() {
		inner.setPreferredSize(new Dimension(400, 40));
		addButton.setPreferredSize(new Dimension(60, 30));
		
		setSizeAndAdd(dateField, placeField, timeField);
		inner.add(addButton);
	}

	private void setSizeAndAdd(JComponent... objects) {
		for (int i = 0; i < objects.length; i++) {
			objects[i].setPreferredSize(new Dimension(100, 30));;
			inner.add(objects[i]);
		}	
		add(inner, BorderLayout.NORTH);
	}
	
	private void addActionListener(JButton... buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new MyActionListener());
		}
	}

	public void addUserPath() {
		if (!CoronaMapMain.placeManagement.search(placeField.getText())) {
			JOptionPane.showMessageDialog(null, "Unregistered place! " + placeField.getText());
			clearTextField();
			return;
		}
		
		if (dateField.getText().length() == 0) { // 날짜를 입력하지 않으면, 마지막 날짜로 선택됨
			user.addInformation(placeField.getText(), timeField.getText());
			return;
		}
		user = new User(dateField.getText());
		user.addInformation(placeField.getText(), timeField.getText());
		CoronaMapMain.userManager.addList(user);
		clearTextField();
	}
	
	private void clearTextField() {
		dateField.setText("");
		timeField.setText("");
		placeField.setText("");
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			
			if (button.getText().contentEquals("add")) {
				addUserPath();
			}
		}
	}
}
	

