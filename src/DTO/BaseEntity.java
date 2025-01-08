package DTO;

/**
 *
 * @author Laptop
 */
public abstract class BaseEntity {

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public BaseEntity(String ID, String ten, String maNCC) {
        this.ID = ID;
        this.name = ten;
        this.maNCC = maNCC;
    }

    private String ID;
    private String name;
    private String maNCC;

    public BaseEntity() {
    }

}
