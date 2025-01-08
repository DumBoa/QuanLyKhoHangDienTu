/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;

public class SanPhamDTO{

    private String ma_SanPham;
    private String ten_SanPham;
    private BigDecimal donGia;
    private String xuatXu;
    private String loai_SanPham;
    private int soLuong;

    public SanPhamDTO(String ma_SanPham,
            String ten_SanPham,
            BigDecimal donGia,
            String xuatXu,
            String loai_SanPham,
            int soLuong) {
        this.ma_SanPham = ma_SanPham;
        this.ten_SanPham = ten_SanPham;
        this.donGia = donGia;
        this.xuatXu = xuatXu;
        this.loai_SanPham = loai_SanPham;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTen_SanPham() {
        return ten_SanPham;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public String getLoai_SanPham() {
        return loai_SanPham;
    }

    public String getMa_SanPham() {
        return ma_SanPham;
    }

    public void setLoai_SanPham(String loai_SanPham) {
        this.loai_SanPham = loai_SanPham;
    }

    public void setMa_SanPham(String ma_SanPham) {
        this.ma_SanPham = ma_SanPham;
    }

    public void setTen_SanPham(String ten_SanPham) {
        this.ten_SanPham = ten_SanPham;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

}
