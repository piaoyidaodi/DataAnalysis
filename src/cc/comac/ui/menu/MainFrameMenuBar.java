package cc.comac.ui.menu;

import javax.swing.JMenuBar;

public class MainFrameMenuBar extends JMenuBar {
    
    public MainFrameMenuBar() {
        super();
    }
    
    public MainFrameMenuBar(String...menuOptionName){
        super();
        if (menuOptionName.length>0){
            for(String menu:menuOptionName){
                MainFrameMenuOption.creatMenu(menu);
            }
        }
    }
    
}
