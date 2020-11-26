package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import coronaMap.PeopleManagement;
import coronaMap.Person;
import manager.Manageable;


public class PrintpplGUI extends JPanel {
	PeopleManagement pmanage = PeopleManagement.getInstance();
    public ArrayList<String> path = new ArrayList<>();
	public ArrayList<LocalTime> time = new ArrayList<>();
    ArrayList<String> data = new ArrayList<String>();
    int num = pmanage.searchNumber(FirstGUI.getDate().format(DateTimeFormatter.ofPattern("M/d")));
    String currentDate = null;
	public PrintpplGUI() {
		setTable();
	}
  

	private void setTable() { 
		String header[] = { "확진자", "위치/시간"};
		String contents[][] = new String[num][2];
		DefaultTableModel myTable = new DefaultTableModel(contents, header);
		
		for (int i=0; i<num; i++) {
			  contents[i][0]=Integer.toString(i);
			  currentDate=FirstGUI.getDate().format(DateTimeFormatter.ofPattern("MM/dd"));
		      contents[i][1]=pmanage.find(currentDate); 
		}
		
		JTable shownTable = new JTable(myTable);
		JScrollPane jscrollPane = new JScrollPane(shownTable);
		jscrollPane.setPreferredSize(new Dimension(370, 480));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
