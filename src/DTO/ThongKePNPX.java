/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ThongKePNPX {

    public int getDemSLPN() {
        return demSLPN;
    }

    public void setDemSLPN(int demSLPN) {
        this.demSLPN = demSLPN;
    }

    public int getDemSLPX() {
        return demSLPX;
    }

    public void setDemSLPX(int demSLPX) {
        this.demSLPX = demSLPX;
    }
    private String maPhieu;
    private String nguoiTao;
    private Timestamp thoiGianTao;
    private BigDecimal tongTien;
    private int demSLPN;
    private int demSLPX;
    
    public ThongKePNPX(String maPhieu, String nguoiTao, Timestamp thoiGianTao, BigDecimal tongTien, int demSLPN, int demSLPX) {
        this.maPhieu = maPhieu;
        this.nguoiTao = nguoiTao;
        this.thoiGianTao = thoiGianTao;
        this.tongTien = tongTien;
        this.demSLPN = demSLPN;
        this.demSLPX = demSLPX;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }
}

