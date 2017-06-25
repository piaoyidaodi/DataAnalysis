package cc.comac.util;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import cc.comac.ui.dialog.AboutDialog;
import cc.comac.ui.dialog.FileChooserDialog;
import cc.comac.ui.dialog.ThemeChooserDialog;

public class ActionFactory {
    public static AbstractAction action=null;
    
    public static AbstractAction getOpenFileAction(JComponent parent){
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileFilter(new FileNameExtensionFilter("Data Files", ".txt",".zip"));
                fileChooserDialog.setCurrentDirectory(new File("."));
                //TODO Choose Target Data
                fileChooserDialog.showOpenDialog(parent);

            }
        };
        action.putValue(Action.NAME, "Open File...");
        action.putValue(Action.SHORT_DESCRIPTION, "Open a Data File");
        // TODO OpenFile Icon
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
        
        return action;
    }

    public static AbstractAction getOpenDirectoryAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooserDialog.setCurrentDirectory(new File("."));
                //TODO Choose Dialog
                fileChooserDialog.showOpenDialog(parent);
                
                }
        };
        action.putValue(Action.NAME, "Open Directory...");
        action.putValue(Action.SHORT_DESCRIPTION, "Open a Data Directory");
        // TODO OpenFile Icon
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        
        return action;
    }

    public static AbstractAction getSaveAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Complete the SaveAction
                
            }
        };
        action.putValue(Action.NAME, "Save");
        action.putValue(Action.SHORT_DESCRIPTION, "Save File");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        return action;
    }

    public static AbstractAction getSaveAsAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Refine the CurrentDirectory
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.resetChoosableFileFilters();
                fileChooserDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooserDialog.setCurrentDirectory(new File("."));
                
                fileChooserDialog.showSaveDialog(parent);

            }
        };
        action.putValue(Action.NAME, "Save As...");
        action.putValue(Action.SHORT_DESCRIPTION, "Save File As...");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        
        return action;
    }

    public static AbstractAction getExitAction(JComponent parent) {
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

    public static AbstractAction getAboutAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        AboutDialog aboutDialog=new AboutDialog(parent);
                        aboutDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                        aboutDialog.pack();
                        aboutDialog.setResizable(false);
                        aboutDialog.setVisible(true);                        
                    }
                });
                                
            }
        };
        action.putValue(Action.NAME, "About...");
        action.putValue(Action.SHORT_DESCRIPTION, "About The Author");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        return action;
    }

    public static AbstractAction getThemeAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        ThemeChooserDialog themeChooserDialog=new ThemeChooserDialog(parent);
                        themeChooserDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                        themeChooserDialog.pack();
                        themeChooserDialog.setVisible(true);
                        
                    }
                });
            }
        };
        action.putValue(Action.NAME, "Theme...");
        action.putValue(Action.SHORT_DESCRIPTION, "Change App Theme");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
        return action;
    }
}
