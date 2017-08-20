package cc.comac.ui.mainlayout;

import java.io.File;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainFrameDirLabelTree extends JTree {
    private String workSpace=null;
    private DefaultTreeModel dTreeModel=null;
    private HashMap<String, File> name2File=null;
    
    public MainFrameDirLabelTree() {
        super();
    }

    public MainFrameDirLabelTree(JComponent parent,File workSpace) {
        this.workSpace=workSpace.getAbsolutePath();
        initTreeModel(parent);
    }
    
    public void initTreeModel(JComponent parent){
        
        name2File=new HashMap<>();
        File workSpaceFile = new File(this.workSpace);
        name2File.put(workSpaceFile.getName(),workSpaceFile);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(workSpaceFile.getName()+"(WorkSpace)");

        this.loopBuildTree(workSpaceFile, root);
        this.dTreeModel = new DefaultTreeModel(root);
        this.dTreeModel.setAsksAllowsChildren(true);

        this.setModel(dTreeModel);
    }
    
    private void loopBuildTree(File dir, DefaultMutableTreeNode parent) {
        File[] fileList = dir.listFiles();

        for (File subFile : fileList) {
            if (!name2File.containsKey(subFile.getAbsolutePath().substring(this.workSpace.length()+1)))
                name2File.put(subFile.getAbsolutePath().substring(this.workSpace.length()+1),subFile);
            else {
                System.out.println(subFile.getAbsolutePath().substring(this.workSpace.length()+1)+"   "+subFile.getAbsolutePath());
                System.out.println(subFile.getAbsolutePath().substring(this.workSpace.length()+1)+"   "+name2File.get(subFile.getName()));
            }
            if (subFile.isDirectory()) {
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(subFile.getName(),true);
                parent.add(defaultMutableTreeNode);
                loopBuildTree(subFile, defaultMutableTreeNode);
            }
            else{
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(subFile.getName(),false);
                parent.add(defaultMutableTreeNode);
            }
        }
    }
    
}
