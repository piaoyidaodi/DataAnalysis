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

public class MainFrameDirLabelTree extends JTree {
    private String workSpace=null;
    private DefaultTreeModel dTreeModel=null;

    
    public MainFrameDirLabelTree() {
        super();
    }

    public MainFrameDirLabelTree(JComponent parent,File workSpace) {
        this.workSpace=workSpace.getAbsolutePath();
        initTreeModel(parent);
    }
    
    public void initTreeModel(JComponent parent){
        
        File workSpaceFile = new File(this.workSpace);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(workSpaceFile);

        this.loopBuildTree(workSpaceFile, root);
        this.dTreeModel = new DefaultTreeModel(root);
        this.dTreeModel.setAsksAllowsChildren(true);

        this.setModel(dTreeModel);
        this.addListener();
    }
    
    private void addListener(){
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0) {
                    DefaultWestPanePopupMenu menu=new DefaultWestPanePopupMenu(MainFrameDirLabelTree.this);
                    menu.show(MainFrameDirLabelTree.this, e.getX(), e.getY());
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
                Context.getInstance().setTargetLabel(targetLabelPath.toString());
            }
        });

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
                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(subFile.getName(),false);
                parent.add(defaultMutableTreeNode);
            }
        }
    }
    
}
