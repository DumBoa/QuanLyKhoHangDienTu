/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DTO.*;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseSP {

    List<SanPhamDTO> getSanPhamFromDatabase();

    void ThemSP(SanPhamDTO sp);

    void SuaSP(SanPhamDTO sp);

    void XoaSP(String maSanPham);

    List<SanPhamDTO> timKiemSanPham(String keyword);

    BigDecimal TongTienSP();
}
