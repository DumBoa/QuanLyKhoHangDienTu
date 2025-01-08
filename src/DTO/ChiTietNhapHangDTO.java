package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ChiTietNhapHangDTO {

    private String maPhieuNhap;
    private String nguoiTao;
    private Timestamp thoiGianTao;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private BigDecimal tongTien;

    public ChiTietNhapHangDTO(String maSanPham, String tenSanPham, int soLuong, BigDecimal tongTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getDonGia() {
        if (soLuong == 0) {
            return BigDecimal.ZERO;
        }
        return tongTien;
    }

}
