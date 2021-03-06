package cc.comac.util;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import cc.comac.controller.WestPaneTreeController;
import cc.comac.data.DataSave;
import cc.comac.ui.dialog.AboutDialog;
import cc.comac.ui.dialog.FileChooserDialog;
import cc.comac.ui.dialog.ThemeChooserDialog;
import cc.comac.ui.mainlayout.DataDrawPanel;
import cc.comac.ui.mainlayout.MainFrameCenterPane;

public class ActionFactory {
    public static AbstractAction action=null;
    
    public static AbstractAction getOpenFileAction(JComponent parent){
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.init();
                fileChooserDialog.setFileFilter(new FileNameExtensionFilter("Data Files(.txt)","txt"));
                fileChooserDialog.setCurrentDirectory(new File(Context.getInstance().getTargetDir()));
                //TODO Choose Target Data
                int result=fileChooserDialog.showOpenDialog(parent);
                
                if(result==JFileChooser.APPROVE_OPTION){
                    Context.getInstance().setTarget(fileChooserDialog.getSelectedFile().getAbsolutePath());
                    WestPaneTreeController.getInstance().updateDataModel();
//                    DataPreProcess dataPreProcess=new DataPreProcess();
//                    dataPreProcess.initFile();
                }
                    
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
                
                FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                fileChooserDialog.init();
                fileChooserDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooserDialog.setCurrentDirectory(new File(Context.getInstance().getTargetDir()));
                int result=fileChooserDialog.showOpenDialog(parent);
                
                if(result==JFileChooser.APPROVE_OPTION){
                    Context.getInstance().setTarget(fileChooserDialog.getSelectedFile().getAbsolutePath());
                    WestPaneTreeController.getInstance().updateDataModel();
                            
                }
            }
        };
        action.putValue(Action.NAME, "Open Directory...");
        action.putValue(Action.SHORT_DESCRIPTION, "Open a Data Directory");
        // TODO OpenFile Icon
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        
        return action;
    }

    public static AbstractAction getSaveImageAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        JSplitPane splitPane=(JSplitPane)Context.getInstance().getMainFrameCenterPane().getSelectedComponent();
                        DataDrawPanel panel=(DataDrawPanel)splitPane.getLeftComponent();
                        File tmp=new File(panel.getController().getTargetLabelZipFilePath());
                        File targetFolder=new File(tmp.getParent()+File.separator+"image");
                        if (!targetFolder.exists()) {
                            targetFolder.mkdirs();
                        }
                        File target=new File(targetFolder+File.separator+panel.getController().getLabelName()+".png");
                        if (target.exists()) {
                            target.delete();
                        }
                        
                        BufferedImage image=new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2=image.createGraphics();
                        panel.paint(g2);
                        try {
                            ImageIO.write(image, "png", target);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        action.putValue(Action.NAME, "Save Image...");
        action.putValue(Action.SHORT_DESCRIPTION, "Save Image");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        return action;
    }

    public static AbstractAction getSaveCsvAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JSplitPane splitPane=(JSplitPane)Context.getInstance().getMainFrameCenterPane().getSelectedComponent();
                DataDrawPanel panel=(DataDrawPanel)splitPane.getLeftComponent();
                File tmp=new File(panel.getController().getTargetLabelZipFilePath());
                File targetFolder=new File(tmp.getParent()+File.separator+"data_csv_format");
                if (!targetFolder.exists()) {
                    targetFolder.mkdirs();
                }
                File targetFile=new File(targetFolder+File.separator+panel.getController().getLabelName()+".csv");
                if (targetFile.exists()) {
                    targetFile.delete();
                }
                
                DataSave dataSave=new DataSave(panel, targetFile);
                new Thread(dataSave).start();
            }
        };
        action.putValue(Action.NAME, "Save As CSV...");
        action.putValue(Action.SHORT_DESCRIPTION, "Save File As CSV");
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

    public static AbstractAction getWorkSpaceAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        FileChooserDialog fileChooserDialog=FileChooserDialog.getInstance();
                        fileChooserDialog.init();
                        fileChooserDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fileChooserDialog.setCurrentDirectory(new File("."));
                        //TODO Choose Dialog
                        fileChooserDialog.showOpenDialog(parent);
                    }
                });
            }
        };
        action.putValue(Action.NAME, "Setting WorkSpace");
        action.putValue(Action.SHORT_DESCRIPTION, "Set WorkSpace");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_W);
        return action;
    }

    public static AbstractAction getDrawAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        WestPaneTreeController.getInstance().updateLabelFileModel();
                    }
                });
            }
        };
        action.putValue(Action.NAME, "Draw");
        action.putValue(Action.SHORT_DESCRIPTION, "Graphics For Data");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        return action;
    }

    public static AbstractAction getOpenInExplorerAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        String targetLabel=Context.getInstance().getTargetLabelZipFilePath();
                        File targetLabelParentDirFile=new File(targetLabel).getParentFile();
                        if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                            try {
                                Desktop.getDesktop().open(targetLabelParentDirFile);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                
            }
        };
        action.putValue(Action.NAME, "Open In Explorer");
        action.putValue(Action.SHORT_DESCRIPTION, "Open In Explorer");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_W);
        return action;
    }

    public static AbstractAction getCloseTabAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        MainFrameCenterPane centerPane=Context.getInstance().getMainFrameCenterPane();
                        System.out.println(centerPane.getSelectedIndex());
                        centerPane.removeTabAt(centerPane.getSelectedIndex());
                    }
                });
                
            }
        };
        action.putValue(Action.NAME, "Close");
        action.putValue(Action.SHORT_DESCRIPTION, "Close this Graphic");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
        return action;
    }

    public static AbstractAction getMouseSelectAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Context.getInstance().setSelectCursor(true);
                Context.getInstance().getMainFrameToolbar().getComponentAtIndex(6).setEnabled(false);
                Context.getInstance().getMainFrameToolbar().getComponentAtIndex(7).setEnabled(true);
            }
        };
        action.putValue(Action.NAME, "Select");
        action.putValue(Action.SHORT_DESCRIPTION, "Select the Figure");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        return action;
    }
    
    public static AbstractAction getMouseHandAction(JComponent parent) {
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Context.getInstance().setSelectCursor(false);
                Context.getInstance().getMainFrameToolbar().getComponentAtIndex(6).setEnabled(true);
                Context.getInstance().getMainFrameToolbar().getComponentAtIndex(7).setEnabled(false);
            }
        };
        action.putValue(Action.NAME, "Hand");
        action.putValue(Action.SHORT_DESCRIPTION, "Hand the Figure");
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
        return action;
    }

    public static AbstractAction getPanelSettingAction(JComponent parent) {
        String title="Setting Panel Property";
        action=new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        DataDrawPanel panel=(DataDrawPanel)parent;
                        panel.getDialog().updatedata();
                        panel.getDialog().setVisible(true);
                    }
                });
                
            }
        };
        action.putValue(Action.NAME, "Settings");
        action.putValue(Action.SHORT_DESCRIPTION, title);
        action.putValue(Action.SMALL_ICON, null);
        action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        return action;
    }

}
