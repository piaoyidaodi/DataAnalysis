package cc.comac.data;

public class TargetDataPair {

    private String targetLabelZipFilePath=null;
    private String[] timeLabelValue=null;
    private Double[] dataLabelValue=null;
    
    public TargetDataPair(String targetLabelZipFilePath,String[] timeLabelValue, Double[] dataLabelValue) {
        this.targetLabelZipFilePath=targetLabelZipFilePath;
        this.timeLabelValue=(String[])timeLabelValue.clone();
        this.dataLabelValue=(Double[])dataLabelValue.clone();
    }

    public String getTargetLabelZipFilePath() {
        return targetLabelZipFilePath;
    }

    public String[] getTimeLabelValue() {
        return timeLabelValue;
    }

    public Double[] getDataLabelValue() {
        return dataLabelValue;
    }

}
