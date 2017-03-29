package MyFirstproject;

import javax.accessibility.Accessible;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JFrame;

public class OpenButton extends JFrame implements Accessible {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

		File selectedfile = fc.getSelectedFile();

		int result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			System.out.println("Selected file:" + selectedfile.getAbsolutePath());
		}

	}
}
