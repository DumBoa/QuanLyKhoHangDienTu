
package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO cho bảng NHAP_HANG
 */
public class XuatHangDTO extends BaseEntity{

        private String ma_Phieu_Xuat;
        private String ma_NCC;
        private String nguoi_Tao;
        private int soLuong;
        private BigDecimal donGia;
        private Timestamp thoi_Gian_Tao;
        private String maSanPham;
        private String tenSanPham;
//        private BigDecimal tong_Tien;
    public XuatHangDTO(String ma_Phieu_Xuat,
                        String ma_NCC,
                        String nguoi_Tao,
                        int soLuong,
                        BigDecimal donGia,
                        Timestamp thoi_Gian_Tao,
                        String maSanPham,
                        String tenSanPham
                        ) {
        this.ma_Phieu_Xuat = ma_Phieu_Xuat;
        this.ma_NCC = ma_NCC;
        this.nguoi_Tao = nguoi_Tao;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thoi_Gian_Tao = thoi_Gian_Tao;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
//                this.tong_Tien = tong_Tien;
    }

    public String getMa_Phieu_Xuat() {
        return ma_Phieu_Xuat;
    }

    public void setMa_Phieu_Xuat(String ma_Phieu_Xuat) {
        this.ma_Phieu_Xuat = ma_Phieu_Xuat;
    }

    public String getMa_NCC() {
        return ma_NCC;
    }

    public void setMa_NCC(String ma_NCC) {
        this.ma_NCC = ma_NCC;
    }

    public String getNguoi_Tao() {
        return nguoi_Tao;
    }

    public void setNguoi_Tao(String nguoi_Tao) {
        this.nguoi_Tao = nguoi_Tao;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Timestamp getThoi_Gian_Tao() {
        return thoi_Gian_Tao;
    }

    public void setThoi_Gian_Tao(Timestamp thoi_Gian_Tao) {
        this.thoi_Gian_Tao = thoi_Gian_Tao;
    }

//    public BigDecimal getTong_Tien() {
//        return tong_Tien;
//    }
//
//    public void setTong_Tien(BigDecimal tong_Tien) {
//        this.tong_Tien = tong_Tien;
//    }

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

    @Override
    public String toString() {
        return "PhieuXuatDTO{" +
                "ma_Phieu_Xuat='" + ma_Phieu_Xuat + '\'' +
                ", ma_NCC='" + ma_NCC + '\'' +
                ", nguoi_Tao='" + nguoi_Tao + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thoi_Gian_Tao=" + thoi_Gian_Tao +
//                ", tong_Tien=" + tong_Tien +
                ", maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                '}';
    }
}

