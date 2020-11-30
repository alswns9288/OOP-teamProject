package GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
	ArrayList<String> pathAndTimeList = new ArrayList<String>();
	PeopleManagement peopleManagement = PeopleManagement.getInstance();
	DefaultTableModel model;
	JTable table;
	JButton addButton;
	JButton doneButton;
	JTextField timeField;
	JTextField placeField;
	boolean pre;

	public void createAndShow() {
		setTitle("관리");
		setPreferredSize(new Dimension(360, 600));
		setLocation(520, 100);

		setTable();
		showRegisteredDate();
		setInputArae();
		setTableDataCenter();
		pre = false;
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		addActionListener(addButton, doneButton);

		pack();
		setVisible(true);
	}

	private void setInputArae() {
		JPanel bottom = new JPanel();
		bottom.setLayout(null);
		bottom.setPreferredSize(new Dimension(360, 100));

		setButtons(bottom);

		add(bottom, BorderLayout.SOUTH);
	}

	private void setButtons(JPanel bottom) {
		placeField = new JTextField();
		timeField = new JTextField();
		addButton = new JButton("추가");
		doneButton = new JButton("완료");

		placeField.setBounds(3, 50, 125, 35);
		timeField.setBounds(130, 50, 85, 35);
		addButton.setBounds(217, 50, 63, 35);
		doneButton.setBounds(282, 50, 63, 35);

		bottom.add(placeField);
		bottom.add(timeField);
		bottom.add(addButton);
		bottom.add(doneButton);
	}

	private void showRegisteredDate() {
		ArrayList<Manageable> list = peopleManagement.getList();
		ArrayList<Person> personList = new ArrayList<Person>();

		for (Manageable m : list) {
			Person p = (Person) m;
			personList.add(p);
		}

		for (Person p : personList) {
			pre = false;
			pathList = p.pathList;
			timeList = p.timeList;
			for (int i = 0; i < pathList.size(); i++) {
				String path = pathList.get(i) + "/" + timeList.get(i);
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
		jscrollPane.setPreferredSize(new Dimension(360, 500));
		add(jscrollPane, BorderLayout.NORTH);
	}

	private void setTableDataCenter() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();

		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	private void addNewPath() {
		String pathAndTime = null;
		int targetNumber = peopleManagement.number + 1;

		pathAndTime = placeField.getText() + "/" + timeField.getText();
		pathAndTimeList.add(pathAndTime);

		addTableRow(targetNumber, pathAndTime);
		clearTextField();
		pre = true;
	}

	public void addNewPositives() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		pre = false;
		peopleManagement.addInformation(pathAndTimeList, FirstGUI.getDate().format(formatter));
		pathAndTimeList.clear();
		peopleManagement.readAll();
	}

	private void clearTextField() {
		System.out.println("3");
		placeField.setText("");
		timeField.setText("");
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
			if (e.getSource() == doneButton) {
				JOptionPane.showMessageDialog(null, "추가 되었습니다.");
				addNewPositives();
			}
		}
	}
}