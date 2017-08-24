package cc.comac.ui.mainlayout;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import cc.comac.util.Context;

public class DefaultMainFrameWestPane extends JTabbedPane {
    
    private HashSet<String> workSpaces=null;
    private HashMap<File, JTree> file2Tree=new HashMap<>();
    
    public DefaultMainFrameWestPane() {
        super();
    }

    public DefaultMainFrameWestPane(JSplitPane mainPane) {
        super();
        
        init(mainPane);
    }
    
    public void init(JSplitPane mainPane) {
        this.removeAll();
        workSpaces=Context.getInstance().getWorkSpaceTreeSet();
        for (String workSpace : workSpaces) {
            File file=new File(workSpace);
            JTree targeTree=null;
            if (file.isDirectory()) {
                targeTree=new MainFrameDirLabelTree(mainPane,file);
                JScrollPane scrollPane=new JScrollPane(targeTree);
                this.addTab(file.getName(), scrollPane);
            } else {
                targeTree=new MainFrameFileLabelTree(mainPane,file);
                JScrollPane scrollPane=new JScrollPane(targeTree);
                this.addTab(getNameNoPFix(file), scrollPane);
            }
            file2Tree.put(file, targeTree);
            
        }
    }
    
    public void update(JSplitPane mainPane) {
        init(mainPane);
    }
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }
}
