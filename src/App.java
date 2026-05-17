import javax.swing.SwingUtilities;

import ui.DashboardFrame;
import ui.LoginFrame;

public class App {
    public static void main(String[] args)  throws Exception {
        SwingUtilities.invokeLater(LoginFrame::new);
        // SwingUtilities.invokeLater(DashboardFrame::new);
    }
}
