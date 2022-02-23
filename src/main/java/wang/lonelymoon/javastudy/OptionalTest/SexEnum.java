package wang.lonelymoon.javastudy.OptionalTest;

/**
 * @author lonelymoon
 */
public enum SexEnum {

    /**
     * 男性
     */
    MAN(1,"男")
    /**
     * 女性
     */
    ,WOMEN(2,"女")
    /**
     * 未知性别
     */
    ,UNKNOWN(3,"未知");

    SexEnum() {
    }

    SexEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    private Integer index;
    private String desc;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
