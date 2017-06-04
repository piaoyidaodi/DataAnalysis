package cc.comac.ui.menu;

import javax.swing.JMenu;

public class MainFrameMenuOption {

    public static JMenu creatMenu(String menuName) {
        JMenu menu = null;
        switch (menuName) {
        case "File":
            menu = new FileMenu();
            break;

        case "Edit":
            menu = new EditMenu();
            break;
            
        case "Help":
            menu = new HelpMenu();
            break;

        default:
            menu = new SelfDefineMenu(menuName);
            break;
        }
        return menu;
    }

}
