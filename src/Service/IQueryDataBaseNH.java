/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DTO.ChiTietNhapHangDTO;
import DTO.NhapHangDTO;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseNH {

    List<NhapHangDTO> getNhapHangFromDataBase();

    void ThemNH(String maPhieuNhap, String maNCC, String nguoiNhap, List<ChiTietNhapHangDTO> danhSachChiTiet);

    void XoaNH(String maPN);

    List<NhapHangDTO> getTongQuanPhieuNhap();

    String taoMaPhieuNhapMoi();
    
    List<NhapHangDTO> getChiTietNhapHang(String maPhieuNhap);
    
    List<NhapHangDTO> TimKiemPN(String keyword);
}
