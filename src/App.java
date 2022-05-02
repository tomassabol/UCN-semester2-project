import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import view.Login;

public class App {

    public static void main(String[] args) {
			FlatLightLaf.setup();
    		try {
				UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
    		
			try {
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
    }

}
