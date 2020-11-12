package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import coronaMap.*;


public class GetInformationGUI extends JPanel {
	JPanel inner = new JPanel();
	JTextField placeField = new JTextField();
	JTextField dateField = new JTextField();
	JTextField timeField = new JTextField();
	JButton addButton = new JButton("add");
	DefaultTableModel model;
	JTable table;
	String date, time, place;
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
		
		model = new DefaultTableModel(contents, header);
		table = new JTable(model);
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
		time = timeField.getText();
		place = placeField.getText();
		
		if (!CoronaMapMain.placeManagement.search(place)) {
			JOptionPane.showMessageDialog(null, "Unregistered place! " + place);
			clearTextField();
			return;
		}
		
		if (dateField.getText().length() != 0) {
			date = dateField.getText(); // 받은 날짜를 저장 (새로운 객체 생성 방지로도 사용)	
		} else { // 날짜를 입력하지 않으면: 마지막 날짜로 선택됨
			user.addInformation(placeField.getText(), timeField.getText());
			clearTextField();
			addTableRow();
			return;
		}
		
		user = new User(date);
		user.addInformation(placeField.getText(), timeField.getText());
		CoronaMapMain.userManager.addList(user);
		clearTextField();
		addTableRow();
	}

	private void addTableRow() {
		model = (DefaultTableModel)table.getModel();
		Object[] row = { date, place, time };
		model.addRow(row);
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
	

