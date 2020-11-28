package GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import GUI.LoginGUI.MyActionListener;
import coronaMap.PeopleManagement;
import coronaMap.Person;
import coronaMap.PlaceManagement;
import coronaMap.User;
import manager.Manageable;

public class ManagerModeGUI extends JFrame {
	ArrayList<String> pathList;
	ArrayList<LocalTime> timeList;
	PeopleManagement peopleManagement = PeopleManagement.getInstance();
	DefaultTableModel model;
	JTable table;
	JButton addButton;
	JTextField numberButton;
	JTextField timeButton;
	JTextField placeButton;
	boolean pre;

	public void createAndShow() {
		setTitle("관리");
		setPreferredSize(new Dimension(350, 600));
		setLocation(525, 100);

		setTable();
		showRegisteredDate();
		setInputArae();
		setTableDateCenter();
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);

		pack();
		setVisible(true);
	}

	private void setInputArae() {
		JPanel bottom = new JPanel();
		bottom.setLayout(null);
		bottom.setPreferredSize(new Dimension(350, 100));

		setButtons(bottom);

		add(bottom, BorderLayout.SOUTH);
	}

	private void setButtons(JPanel bottom) {
		numberButton = new JTextField();
		placeButton = new JTextField();
		timeButton = new JTextField();
		addButton = new JButton("추가");

		numberButton.setBounds(7, 50, 45, 35);
		placeButton.setBounds(55, 50, 125, 35);
		timeButton.setBounds(183, 50, 85, 35);
		addButton.setBounds(271, 50, 60, 35);

		bottom.add(numberButton);
		bottom.add(placeButton);
		bottom.add(timeButton);
		bottom.add(addButton);
	}

	private void showRegisteredDate() {
		ArrayList<Manageable> list = peopleManagement.getList();
		ArrayList<Person> personList = new ArrayList<Person>();

		for (Manageable m : list) {
			Person p = (Person) m;
			personList.add(p);
		}

		System.out.println(personList.size());
		for (Person p : personList) {
			pre = false;
			pathList = p.pathList;
			timeList = p.timeList;
			for (int i = 0; i < pathList.size(); i++) {
				String path = "   " + pathList.get(i) + " / " + timeList.get(i);
				addTableRow(p.number, path);
				pre = true;
			}
		}
	}

	private void addTableRow(int number, String path) {
		if (pre) {
			Object[] row = { null, path };
			model.addRow(row);
			return;
		}
		Object[] row = { number, path };
		model.addRow(row);
	}

	private void setTable() {
		String header[] = { "확진자 번호", "위치/시간" };
		String contents[][] = new String[0][0];

		model = new DefaultTableModel(contents, header);
		table = new JTable(model);

		JScrollPane jscrollPane = new JScrollPane(table);
		jscrollPane.setPreferredSize(new Dimension(350, 500));
		add(jscrollPane, BorderLayout.NORTH);
	}

	private void setTableDateCenter() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();

		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	private void addNewPath() {
		int lastNumber = 0;
		

		clearTextField();
		addNewRow();
	}
	
	private void addNewRow() {
		
	}

	private void clearTextField() {
		placeButton.setText("");
		timeButton.setText("");
	}

	private void addActionListener(JButton... buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new MyActionListener());
		}
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addButton) {
				addNewPath();
			}
		}
	}
}
