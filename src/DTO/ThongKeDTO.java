/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Laptop
 */
public class ThongKeDTO {

    private int stt;
    private String maSanPham;
    private String tenSanPham;
    private int soLuongNhap;
    private int soLuongXuat;

    public ThongKeDTO(int stt,
            String maSanPham,
            String tenSanPham,
            int soLuongNhap,
            int soLuongXuat
    ) {
        this.stt = stt;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongNhap = soLuongNhap;
        this.soLuongXuat = soLuongXuat;

    }

    public String getTen_SanPham() {
        return tenSanPham;
    }

    public String getMa_SanPham() {
        return maSanPham;
    }

    public int getStt() {
        return stt;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setMa_SanPham(String ma_SanPham) {
        this.maSanPham = ma_SanPham;
    }

    public void setTen_Hang(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }
    public String toString() {
        return "dd{" +
                "stt=" + stt +
                ", maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", soLuongNhap=" + soLuongNhap +
                ", soLuongXuat=" + soLuongXuat +
                '}';
    }
    
}
