package cc.comac.ui.mainlayout;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import cc.comac.ui.popupmenu.DefaultWestPanePopupMenu;
import cc.comac.util.Context;

public class MainFrameFileLabelTree extends JTree {
    private String target=null;
    private DefaultTreeModel dTreeModel=null;

    
    public MainFrameFileLabelTree() {
        super();
    }

    public MainFrameFileLabelTree(JComponent parent,File targetFile) {
        this.target=targetFile.getAbsolutePath();
        initTreeModel(parent);
    }
    
    public void initTreeModel(JComponent parent){
        
        File targetFile = new File(this.target);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(targetFile.getParent()+File.separator+getNameNoPFix(targetFile));

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
        this.addListener();
    }
    
    private void loopBuildTree(File dir, DefaultMutableTreeNode parent) {
        File[] fileList = dir.listFiles();

        for (File subFile : fileList) {
            if (subFile.isDirectory()) {
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(subFile.getName(),true);
                parent.add(defaultMutableTreeNode);
                loopBuildTree(subFile, defaultMutableTreeNode);
            }
            else{
                if(subFile.getName().endsWith(".zip")||subFile.getName().endsWith(".md")){
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(subFile.getName(),false);
                parent.add(defaultMutableTreeNode);
                }
            }
        }
    }
    
    private void addListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0) {
                    DefaultWestPanePopupMenu menu=new DefaultWestPanePopupMenu(MainFrameFileLabelTree.this);
                    menu.show(MainFrameFileLabelTree.this, e.getX(), e.getY());
                }

            }
        });

        this.addTreeSelectionListener(new TreeSelectionListener() {
            
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath path=e.getPath();
                String pathString=path.toString();
                StringBuilder targetLabelPath=new StringBuilder();
                String targetPathString=pathString.substring(1, pathString.length()-1);
                String[] targetLabelPieces=targetPathString.split(",");
                for (String part : targetLabelPieces) {
                    part=part.trim();
                    if (part.contains(".")) {
                        targetLabelPath.append(part);
                    }else{
                        targetLabelPath.append(part).append(File.separator);
                    }
                }
                System.out.println(targetLabelPath.toString());
                Context.getInstance().setTargetLabelZipFilePath(targetLabelPath.toString());
            }
        });

    }
    
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }
    
}

