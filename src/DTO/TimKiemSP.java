/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;

/**
 *
 * @author Laptop
 */
public class TimKiemSP {
    private final String ma_SanPham;
    private final String ten_SanPham;
    private final int soLuong;
    private final BigDecimal donGia;
    public TimKiemSP(String ma_SanPham, String ten_SanPham,int soLuong, BigDecimal donGia){
        this.ma_SanPham = ma_SanPham;
        this.ten_SanPham = ten_SanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMa_SanPham() {
        return ma_SanPham;
    }

    public String getTen_SanPham() {
        return ten_SanPham;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }
     
} 
