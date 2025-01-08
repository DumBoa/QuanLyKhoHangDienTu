package DTO;

/**
 *
 * @author Laptop
 */
public class NhaCungCapDTO{

    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "ma_NCC=" + ma_NCC + ", ten_NhaCungCap=" + ten_NhaCungCap + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", email=" + email + '}';
    }

    private String ma_NCC;
    private String ten_NhaCungCap;
    private String soDienThoai;
    private String diaChi;
    private String email;

    public NhaCungCapDTO(String ma_NCC, String ten_NhaCungCap, String soDienThoai, String diaChi, String email) {
        this.ma_NCC = ma_NCC;
        this.ten_NhaCungCap = ten_NhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getMa_NCC() {
        return ma_NCC;
    }

    public String getTen_NhaCungCap() {
        return ten_NhaCungCap;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setTen_NhaCungCap(String ten_NhaCungCap) {
        this.ten_NhaCungCap = ten_NhaCungCap;
    }

    public void setMa_NCC(String ma_NCC) {
        this.ma_NCC = ma_NCC;
    }
}
