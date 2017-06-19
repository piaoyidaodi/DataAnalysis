package cc.comac.util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import cc.comac.ui.FileChooserDialog;

public class ActionFactory {
    public static AbstractAction action=null;
    
    public static AbstractAction getOpenFileAction(){
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileFilter(new FileNameExtensionFilter("Data Files", ".txt",".zip"));
                fileChooserDialog.setCurrentDirectory(new File("."));
                //TODO Choose Target Data
                fileChooserDialog.showOpenDialog(null);

            }
        };
        action.putValue(Action.NAME, "Open File...");
        action.putValue(Action.SHORT_DESCRIPTION, "Open a Data File");
        // TODO OpenFile Icon
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
        
        return action;
    }

    public static AbstractAction getOpenDirectoryAction() {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooserDialog.setCurrentDirectory(new File("."));
                //TODO Choose Dialog
                fileChooserDialog.showOpenDialog(null);
                
                }
        };
        action.putValue(Action.NAME, "Open Directory...");
        action.putValue(Action.SHORT_DESCRIPTION, "Open a Data Directory");
        // TODO OpenFile Icon
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        
        return action;
    }

    public static AbstractAction getSaveAction() {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        };
        action.putValue(Action.NAME, "Save");
        action.putValue(Action.SHORT_DESCRIPTION, "Save File");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        return action;
    }

    public static AbstractAction getSaveAsAction() {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooserDialog.setCurrentDirectory(new File("."));
                
                fileChooserDialog.showSaveDialog(null);

            }
        };
        action.putValue(Action.NAME, "Save As...");
        action.putValue(Action.SHORT_DESCRIPTION, "Save File As...");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        
        return action;
    }

    public static AbstractAction getExitAction() {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                
            }
        };
        action.putValue(Action.NAME, "Exit");
        action.putValue(Action.SHORT_DESCRIPTION, "Exit");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        return action;
    }

    public static AbstractAction getAboutAction() {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            // My Information
                                
            }
        };
        action.putValue(Action.NAME, "About");
        action.putValue(Action.SHORT_DESCRIPTION, "About The App");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        return action;
    }
}
