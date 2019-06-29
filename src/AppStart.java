import java.awt.EventQueue;

import view.LoginFrame;
import view.TabPanFrame;

/**
 * 
 * @author Williyam
 * 
 */
public class AppStart {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabPanFrame frame = new TabPanFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
