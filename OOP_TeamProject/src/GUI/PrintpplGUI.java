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

	private void setTable() { // 1 ȭ���Ĵ� ������/12:20-����/13:30
		String header[] = { "Ȯ����", "��ġ/�ð�", "��ġ/�ð�" };
		String contents[][] = {

		};

		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(370, 480));
		add(jscrollPane, BorderLayout.CENTER);
		
	}
}
