package cc.comac.ui.dialog;

import javax.swing.JFileChooser;

public class FileChooserDialog extends JFileChooser {

    public static void main(String[] args) {
        
    }

    private static final FileChooserDialog instance = new FileChooserDialog();

    private FileChooserDialog() {
        super();
    }

    public static FileChooserDialog getInstance() {
        return instance;
    }
    
    public void init(){
        this.resetChoosableFileFilters();
        this.setFileSelectionMode(FileChooserDialog.FILES_AND_DIRECTORIES);
    }
}
