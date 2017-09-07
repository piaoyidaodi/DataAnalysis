package cc.comac.ui.popupmenu;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import cc.comac.ui.menu.MenuItemsFactory;
import cc.comac.util.Context;

public class DefaultCenterPanePopupMenu extends JPopupMenu {

    public DefaultCenterPanePopupMenu(JComponent parent) {
        super();
        addMenuItem(parent,this);
    }

    private void addMenuItem(JComponent parent, JPopupMenu menu,Action...actions) {
        JMenuItem closeTabItem=MenuItemsFactory.getCloseTabItem(parent);
        JMenuItem panelSettingItem=MenuItemsFactory.getPanelSettingItem(parent);
        
        // Radio Button
        JRadioButtonMenuItem selectCursorItem=MenuItemsFactory.getSelectCursorItem(parent);
        JRadioButtonMenuItem handCursorItem=MenuItemsFactory.getHandCursorItem(parent);
        ButtonGroup group=new ButtonGroup();
        group.add(selectCursorItem);
        group.add(handCursorItem);
        if (Context.getInstance().isSelectCursor()) {
            selectCursorItem.setSelected(true);
        }else {
            handCursorItem.setSelected(true);
        }
        // Add MenuItem to PopMenu
        this.add(selectCursorItem);
        this.add(handCursorItem);
        this.addSeparator();
        this.add(panelSettingItem);
        this.add(closeTabItem);
    }
}
