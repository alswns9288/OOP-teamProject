package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import coronaMap.*;
import manager.UserManager;


public class GetInformationGUI extends JPanel {
	JPanel inner = new JPanel();
	JTextField placeField = new JTextField("place");
	JTextField timeField = new JTextField("time");
	JTextField cityField = new JTextField("city");
	JButton addButton = new JButton("add");
	JButton searchButton = new JButton("search");
	DefaultTableModel model;
	JTable table;
	String time, place;
	LocalDate date;
	User user;
	
	public GetInformationGUI() {
		setLayout(new BorderLayout());
		setUserArea();
		setShowArea();
		addActionListener(addButton);
		addActionListener(searchButton);
	}

	private void setShowArea() {
		String header[] = {"City", "Place", "Time"};
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
		
		setSizeAndAdd(cityField, placeField, timeField);
		
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
		PlaceManagement placeManagement = PlaceManagement.getInstance();
		UserManager userManager = UserManager.getInstance();
		date = FirstGUI.getDate();
		time = timeField.getText();
		place = placeField.getText();

		if (!placeManagement.search(place)) {
			JOptionPane.showMessageDialog(null, "Unregistered place! " + place);
			clearTextField();
			return;
		}
		
		if (userManager.fineUser(date) == null) {
			user = new User(date);
			user.addInformation(placeField.getText(), timeField.getText());
			userManager.addList(user);
		} else {
			user = userManager.fineUser(date);
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
		cityField.setText("");
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
	

