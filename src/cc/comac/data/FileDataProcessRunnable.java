package cc.comac.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cc.comac.controller.WestPaneTreeController;

public class FileDataProcessRunnable implements Runnable{
    
    private String targetFile=null;
    public FileDataProcessRunnable(){
        
    }
    
    public FileDataProcessRunnable(String targetFile){
        this.targetFile=targetFile;
    }
    
    public void run(){
        doProcess();
        WestPaneTreeController.getInstance().updateUI();
        
    }
    

    public void doProcess() {
        File file=new File(targetFile);
        if (file.exists()) {
            doResolve(file);
            doZip(file);
            file.delete();
        }
    }
    
    private void doZip(File file) {
        final int BUFSIZE=4096;
        int length;
        String path=file.getAbsolutePath();
        String name=file.getName();
        String postfix="zip";

        String genZipDirPath=path.substring(0,path.lastIndexOf(File.separator)+1);
        File genZipDir=new File(genZipDirPath);

/*
        if (!genZipDir.exists()) {
            genZipDir.mkdir();            
        }else {
            File[] list=genZipDir.listFiles();
            for (File itemFile:list){
                if (getNameNoPFix(file).equals(getNameNoPFix(itemFile))){
                    return;
                }
            }
        }
*/
        
        String genZipFilePath=genZipDir.getAbsolutePath()+File.separator+name.substring(0,name.lastIndexOf(".")+1)+postfix;
        File genZipFile=new File(genZipFilePath);

        try {
            BufferedInputStream bufIS = new BufferedInputStream(new FileInputStream(file));

            ZipOutputStream zipOS = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(genZipFile)));
            ZipEntry entry = new ZipEntry(name);

            byte[] buf = new byte[BUFSIZE];
            zipOS.putNextEntry(entry);

            while ((length = bufIS.read(buf)) != -1) {
                zipOS.write(buf, 0, length);
            }

            bufIS.close();
            zipOS.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void doResolve(File file){

        String postfix="jbin";
        String labelName=null;

        String path=file.getAbsolutePath();
        ArrayList<String> labelArray=new ArrayList<String>();
        ArrayList<String> timeLabelArray=new ArrayList<String>();
        ArrayList<Double>[] eachLabelArray=null;

        int labelSize=0;
        int STEP=50;
        int head=0;
        int tail=STEP;

        String genZipDirPath=path.substring(0,path.lastIndexOf(File.separator)+1)+getNameNoPFix(file)+File.separator;
        File genZipDir=new File(genZipDirPath);
        if (!genZipDir.exists()) {
            genZipDir.mkdir();
        }

        Scanner fScanner= null;
        ObjectOutputStream objOS=null;
        try {
            fScanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fScanner.hasNextLine()){

            String line=fScanner.nextLine();
            String[] lineParas=line.split("\\s+");

            labelSize=lineParas.length;
            // labelArray line write to labelArray
            for (String alabel : lineParas) {
                if (alabel.contains("\\")) {
                    System.out.println(alabel);
                    alabel=alabel.replace("\\", "_");
                }
                labelArray.add(alabel);
            }
            eachLabelArray=new ArrayList[labelSize];
            for (int i=1;i<labelSize;i++){
                eachLabelArray[i]=new ArrayList<Double>();
            }
        }
        fScanner.close();

        boolean flag=true;

        if (tail>labelSize){
            try {
                fScanner = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (fScanner.hasNextLine()) fScanner.nextLine();
            while (fScanner.hasNextLine()){

                String line=fScanner.nextLine();
                String[] lineParas=line.split("\\s+");
                for (int i=0;i<labelSize;i++){
                    if (i==0){
                        timeLabelArray.add(lineParas[i]);
                    }else {
                        eachLabelArray[i].add(Double.parseDouble(lineParas[i]));

                    }
                }
            }

                for (int i=0;i<labelSize;i++){
                    labelName=labelArray.get(i);

                    String labelFilePath = getParentPath(file) + File.separator + getNameNoPFix(file);
                    File labelDataFile = new File(labelFilePath + File.separator + labelName + "." + postfix);

                    try {
                        objOS = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(labelDataFile)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                    try {
                        if (i==0) {
                            objOS.writeObject(timeLabelArray);
                            timeLabelArray.clear();
                        }
                        else {
                            objOS.writeObject(eachLabelArray[i]);
                            eachLabelArray[i].clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objOS.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fScanner.close();
                    
                    doZip(labelDataFile);
                    labelDataFile.delete();
                }
                
        }
        else {
            while (tail<=labelSize){

                try {
                    fScanner = new Scanner(new BufferedReader(new FileReader(file)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (fScanner.hasNextLine()) fScanner.nextLine();
                while (fScanner.hasNextLine()){

                    String line=fScanner.nextLine();
                    String[] lineParas=line.split("\\s+");
                    for (int i=head;i<tail;i++){
                        if (i==0){
                            timeLabelArray.add(lineParas[i]);
                        }else {
                            eachLabelArray[i].add(Double.parseDouble(lineParas[i]));
                        }
                    }
                }

                for (int i=head;i<tail;i++){
                    labelName=labelArray.get(i);

                    String labelFilePath = getParentPath(file) + File.separator + getNameNoPFix(file);
                    File labelDataFile = new File(labelFilePath + File.separator + labelName + "." + postfix);

                    try {
                        objOS = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(labelDataFile)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                    try {
                        if (i==0) {
                            objOS.writeObject(timeLabelArray);
                            timeLabelArray.clear();
                        }
                        else {
                            objOS.writeObject(eachLabelArray[i]);
                            eachLabelArray[i].clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        objOS.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fScanner.close();
                    
                    doZip(labelDataFile);
                    labelDataFile.delete();
                }

                head+=STEP;
                tail+=STEP;
                if (tail>=labelSize&&flag){
                    flag=false;
                    tail=labelSize;
                }
            }
        }
    }
    
    private String getParentPath(File file){
        if (file.isDirectory()){
            return file.getAbsolutePath();
        }
        else
            return file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator));
    }
    
    private String getNameNoPFix(File file){
        if (!file.isDirectory())
            return file.getName().substring(0,file.getName().lastIndexOf("."));
        else return file.getName();
    }

}
