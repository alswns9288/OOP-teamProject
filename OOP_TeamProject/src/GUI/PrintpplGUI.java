package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import coronaMap.Person;

import java.awt.event.*;

public class PrintpplGUI extends JFrame {
	Container container = null;
	JFrame jframe = new JFrame();
	JPanel background = null;
	int matchNum = 0;
    Person per = new Person();
	public void createAndShowGUI() {
		setTitle("확진자 경로 표시");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 50);
		setPreferredSize(new Dimension(400, 730));
		setResizable(false);
		
		setMainGUI();
		pack();
		setVisible(true);
	}
	private void setMainGUI() {
		container = getContentPane();
		setTable();
	}
	
	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = {"확진자", "위치/시간", "위치/시간"};
		String contents[][] = {
				
		   };
		
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 40));
		container.add(jscrollPane, BorderLayout.PAGE_START);
	
	}
}
