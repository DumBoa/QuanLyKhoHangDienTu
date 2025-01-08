/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DTO.ChiTietXuatHangDTO;
import DTO.XuatHangDTO;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseXH {

    List<XuatHangDTO> getXuatHangFromDataBase();

    void ThemXH(String maPhieuXuat, String maNCC, String nguoiNhap, List<ChiTietXuatHangDTO> danhSachChiTiet);

//    void XoaXH(String maPN);

    List<XuatHangDTO> getTongQuanPhieuXuat();

    String taoMaPhieuXuatMoi();
    
    List<XuatHangDTO> getChiTietXuatHang(String maPhieuXuat);
    
    void XoaXH(String maPhieuXuat);
    List<XuatHangDTO> TimKiemPX(String keyword);
}
