package GUI;

import java.awt.*;
import javax.swing.*;
import coronaMap.Person;

public class PrintpplGUI extends JFrame {
	JFrame jframe = new JFrame();
	JPanel background = null;
	int matchNum = 0;
	Person per = new Person();

	public void createAndShowGUI() {
		GUIMain.removeMainCenter();
		setTable();
		GUIMain.container.revalidate();
		GUIMain.container.repaint();
	}

	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = { "확진자", "위치/시간", "위치/시간" };
		String contents[][] = {

		};

		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		//jscrollPane.setPreferredSize(new Dimension(400, 40));
		GUIMain.container.add(jscrollPane, BorderLayout.CENTER);

	}
}
