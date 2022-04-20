import view.Login;
import java.awt.EventQueue;

public class App {

    public static void main(String[] args) {
			try {
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
    }

}
