package GUI;

import java.awt.*;
import javax.swing.*;
import coronaMap.Person;

public class PrintpplGUI extends JPanel {
	Person per = new Person();
	GUIMain container;
	int matchNum;

	public PrintpplGUI(GUIMain container) {
		this.container = container;
		setTable();
	}

	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = { "확진자", "위치/시간", "위치/시간" };
		String contents[][] = {

		};

		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		add(jscrollPane, BorderLayout.CENTER);
	}
}
