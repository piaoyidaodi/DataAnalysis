package cc.comac.ui;

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
}
