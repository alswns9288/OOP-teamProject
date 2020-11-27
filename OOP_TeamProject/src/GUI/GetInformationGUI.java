package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import coronaMap.*;
import manager.*;

public class GetInformationGUI extends JPanel implements Split {
	UserManager userManager = UserManager.getInstance();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
	JPanel inner = new JPanel();
	JTextField placeField = new JTextField();
	JTextField timeField = new JTextField();
	JTextField cityField = new JTextField();
	JButton addButton = new JButton("add");
	JButton searchButton = new JButton("search");
	DefaultTableModel model;
	JTable table;
	String time, place;
	String city = "영통구";
	String date = FirstGUI.getDate().format(formatter);
	User user;

	public GetInformationGUI() {

		setLayout(new BorderLayout());
		setUserArea();
		setShowArea();
		setTableDateCenter();
		if (userManager.getID() != null) {
			showRegisteredData();
		}
		addActionListener(addButton,searchButton);
	}

	private void showRegisteredData() {
		model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		
		ArrayList<String> pathAndTime = userManager.getPath(date);
		if (pathAndTime == null) {
			return;
		}

		setMemberPath(pathAndTime);
	}

	public void split(String information) {
		String[] registerdArray = null;
		String[] temp = null;

		registerdArray = information.split("-");
		for (String string : registerdArray) {
			temp = string.split("/");
			place = temp[0];
			time = temp[1]; // x:xx로 나타낸 시간을 time format으로 변경
			Object[] row = { city, place, time };
			model.addRow(row);
		}
	}

	private void setMemberPath(ArrayList<String> pathAndTime) {
		for (String information : pathAndTime) {
			System.out.println(information);
			model = (DefaultTableModel) table.getModel();
			split(information);
		}
	}

	private void setShowArea() {
		String header[] = { "City", "Place", "Time" };
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
			objects[i].setPreferredSize(new Dimension(120, 30));
			;
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
		model = (DefaultTableModel) table.getModel();
		Object[] row = { city, place, time };
		model.addRow(row);
	}

	private void clearTextField() {
		cityField.setText("");
		timeField.setText("");
		placeField.setText("");
	}
	
	private void setTableDateCenter() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			if (button.getText().contentEquals("add")) {
				addUserPath();
			}
			if (button.getText().contentEquals("search")) {
				removeAll();
				PathCompareGUI pathCompareGUI = new PathCompareGUI();
				add(pathCompareGUI);
				revalidate();
				repaint();
			}
		}
	}
}
