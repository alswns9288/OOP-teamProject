package GUI;

import java.awt.*;
import javax.swing.*;
import coronaMap.Person;

public class PrintpplGUI extends JPanel {
	Person per = new Person();
	int matchNum;

	public PrintpplGUI() {
		setTable();
	}

	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = { "확진자", "위치/시간", "위치/시간" };
		String contents[][] = {

		};

		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(370, 480));
		add(jscrollPane, BorderLayout.CENTER);
		
	}
}
