package me.ning.picapiget.image.bean;

public class ImageExifInfo {

    //未知标签
    private String unknown;


    //设备制造商
    private String make;

    //设备型号
    private String model;


    //宽度
    private float width;

    //高度
    private float height;

    //分辨率单位
    private String resolutionUnit;

    //修图软件
    private String software;

    //拍摄时间
    private String dateTime;
    //色相定位    CbCr Positioning"
    private String cbcrPositioning;

    //方向
    private String orientation;


    public String getUnknown() {
        return unknown;
    }

    public void setUnknown(String unknown) {
        this.unknown = unknown;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getResolutionUnit() {
        return resolutionUnit;
    }

    public void setResolutionUnit(String resolutionUnit) {
        this.resolutionUnit = resolutionUnit;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCbcrPositioning() {
        return cbcrPositioning;
    }

    public void setCbcrPositioning(String cbcrPositioning) {
        this.cbcrPositioning = cbcrPositioning;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }


    @Override
    public String toString() {
        return "图片信息：ImageExifInfo{" +
                "unknown='" + unknown + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", resolutionUnit='" + resolutionUnit + '\'' +
                ", software='" + software + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", cbcrPositioning='" + cbcrPositioning + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }
}
