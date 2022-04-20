import view.Login;

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
