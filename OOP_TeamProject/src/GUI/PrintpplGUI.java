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

	private void setTable() { // 1 ȭ���Ĵ� ������/12:20-����/13:30
		String header[] = { "Ȯ����", "��ġ/�ð�", "��ġ/�ð�" };
		String contents[][] = {

		};

		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		add(jscrollPane, BorderLayout.CENTER);
	}
}
