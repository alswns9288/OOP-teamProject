package GUI;

import java.awt.event.*;
import javax.swing.*;

public class MyMouseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button.getText().contentEquals("search1")) {
			System.out.println("1");
			PrintpplGUI ppl = new PrintpplGUI();
			ppl.createAndShowGUI();

		}
	}
}
