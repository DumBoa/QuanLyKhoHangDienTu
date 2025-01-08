/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DTO.NhapHangDTO;
import DTO.ThongKeDTO;
import DTO.ThongKePNPX;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseThongKe {

    public List<ThongKeDTO> getThongKeFromDatabase();

    int demSLNCC();

    int demSLSP();

    int demSLTK();
    
    public List<ThongKePNPX> timKiemPhieuNhapTheoNgay(String ngayBatDau, String ngayKetThuc);
    
    public int demPNPX();
    
    public int demSLPN();
    
    public int demSLPX();
    
    public BigDecimal tongTienPN();
    
    public BigDecimal tongTienPX();
    
    public BigDecimal tongTienPNPX();   
    
    public List<ThongKePNPX> timKiemTheoGia(BigDecimal getMin, BigDecimal getMax);
    
    public List<ThongKePNPX> getPhieuNhapVaXuat();
    
    List<ThongKePNPX> timKiemPhieuXuatTheoNgay(String ngayBatDau, String ngayKetThuc);
    
}
