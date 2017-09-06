package cc.comac.ui.toolbar;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import cc.comac.util.ActionFactory;
import cc.comac.util.Context;

public class DefaultToolBar extends JToolBar {

    public DefaultToolBar() {
        super();
    }

    public DefaultToolBar(JFrame parent) {
        super();
        init();        
    }

    private void init() {
        Dimension commonSepaSize=new Dimension(2, 25);
        Dimension speSepaSize=new Dimension(6, 25);
        this.add(ActionFactory.getOpenFileAction(this));
        this.addSeparator(commonSepaSize);
        this.add(ActionFactory.getOpenDirectoryAction(this));
        this.addSeparator(commonSepaSize);
        this.add(ActionFactory.getSaveAction(this));
        this.addSeparator(commonSepaSize);
        // Add cursor action
        JButton btnSelect=this.add(ActionFactory.getMouseSelectAction(this));
        JButton btnHand=this.add(ActionFactory.getMouseHandAction(this));
        
        if (Context.getInstance().isSelectCursor()) {
            btnSelect.setEnabled(false);
        }else {
            btnHand.setEnabled(false);
        }
        
        this.addSeparator(speSepaSize);
        this.add(ActionFactory.getAboutAction(this));
        
        this.setPreferredSize(new Dimension(400, 25));

    }

}
