package cc.comac.ui.mainlayout;

import java.io.File;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainFrameFileLabelTree extends JTree {
    private String target=null;
    private DefaultTreeModel dTreeModel=null;
    private HashMap<String, File> name2File=null;
    
    public MainFrameFileLabelTree() {
        super();
    }

    public MainFrameFileLabelTree(JComponent parent,File targetFile) {
        this.target=targetFile.getAbsolutePath();
        initTreeModel(parent);
    }
    
    public void initTreeModel(JComponent parent){
        
        name2File=new HashMap<>();
        File targetFile = new File(this.target);
        name2File.put(getNameNoPFix(targetFile)+".zip",targetFile);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(getNameNoPFix(targetFile)+".zip"+"(File)");

        File targetFileParentDir=targetFile.getParentFile();
        File[] fileList=targetFileParentDir.listFiles();
        for (File file : fileList) {
            if (file.isDirectory()&&file.getName().equals(getNameNoPFix(targetFile))) {
                this.loopBuildTree(file, root);
            }
        }

        
        this.dTreeModel = new DefaultTreeModel(root);
        this.dTreeModel.setAsksAllowsChildren(true);

        this.setModel(dTreeModel);
    }
    
    private void loopBuildTree(File dir, DefaultMutableTreeNode parent) {
        File[] fileList = dir.listFiles();

        for (File subFile : fileList) {
            if (!name2File.containsKey(subFile.getAbsolutePath().substring(this.target.length()+1)))
                name2File.put(subFile.getAbsolutePath().substring(this.target.length()+1),subFile);
            else {
                System.out.println(subFile.getAbsolutePath().substring(this.target.length()+1)+"   "+subFile.getAbsolutePath());
                System.out.println(subFile.getAbsolutePath().substring(this.target.length()+1)+"   "+name2File.get(subFile.getName()));
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
    
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }
    
}

