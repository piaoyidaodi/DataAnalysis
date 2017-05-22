package cc.comac.util;

import java.awt.*;

public class DeviceProperty {
    private static double deviceWidth;
    private static double deviceHeight;

    /*
     * private static final DeviceProperty instance = new DeviceProperty();
     * 
     * private DeviceProperty() { }
     * 
     * public static DeviceProperty getDeviceProperty() { return instance; }
     */

    private static Dimension getDeviceScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        deviceWidth = screenSize.getWidth();
        deviceHeight = screenSize.getHeight();
        return screenSize;
    }

    public static double getDeviceWidth() {
        return deviceWidth = DeviceProperty.getDeviceScreenSize().getWidth();
    }

    public static double getDeviceHeight() {
        return deviceHeight = DeviceProperty.getDeviceScreenSize().getHeight();
    }

    public static Dimension Center() {
        return new Dimension((int) deviceWidth / 3, (int) deviceHeight / 3);
    }
    
    public static String getDefaultWkDir(){
        return System.getProperty("user.home");
    }
}
