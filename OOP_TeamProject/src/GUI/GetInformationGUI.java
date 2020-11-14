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
	JButton searchButton = new JButton("search");
	DefaultTableModel model;
	JTable table;
	String date, time, place;
	User user;
	
	public GetInformationGUI() {
		setLayout(new BorderLayout());
		setUserArea();
		setShowArea();
		addActionListener(addButton);
		addActionListener(searchButton);
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
		inner.setPreferredSize(new Dimension(400, 80));
		addButton.setPreferredSize(new Dimension(80, 30));
		
		setSizeAndAdd(dateField, placeField, timeField);
		
		inner.add(addButton);
		searchButton.setPreferredSize(new Dimension(80, 30));
		inner.add(searchButton);
	}

	private void setSizeAndAdd(JComponent... objects) {
		for (int i = 0; i < objects.length; i++) {
			objects[i].setPreferredSize(new Dimension(120, 30));;
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

		if (dateField.getText().length() == 0) { // 날짜에 아무것도 입력하지 않으면 이전 날짜에 추가
			user.addInformation(placeField.getText(), timeField.getText());
			clearTextField();
			addTableRow();
			return;
		}
		
		date = dateField.getText();
		if (CoronaMapMain.userManager.fineUser(date) == null) {
			user = new User(date);
			user.addInformation(placeField.getText(), timeField.getText());
			CoronaMapMain.userManager.addList(user);
		} else {
			System.out.println("1");
			user = CoronaMapMain.userManager.fineUser(date);
			user.addInformation(placeField.getText(), timeField.getText());
		}
		
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
			if (button.getText().contentEquals("search")) {
				
			}
		}
	}
}
	

