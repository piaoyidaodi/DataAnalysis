package cc.comac.ui.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;

public class MainFrameMenu extends JMenu {
    public MainFrameMenu(JFrame parent) {

        // MenuBar of the MainFrame
        MainFrameMenuBar mainFrameMenuBar = new MainFrameMenuBar(parent,"File","Edit","Help");
        parent.setJMenuBar(mainFrameMenuBar);

    }

}
