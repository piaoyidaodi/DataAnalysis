package cc.comac.ui.menu;

import javax.swing.JComponent;
import javax.swing.JMenu;

public class MainFrameMenuOption {

    public static JMenu creatMenu(JComponent parent,String menuName) {
        JMenu menu = null;
        switch (menuName) {
        case "File":
            menu = new FileMenu(parent);
            break;

        case "Edit":
            menu = new EditMenu(parent);
            break;
            
        case "Help":
            menu = new HelpMenu(parent);
            break;

        default:
            menu = new SelfDefineMenu(menuName);
            break;
        }
        return menu;
    }

}
