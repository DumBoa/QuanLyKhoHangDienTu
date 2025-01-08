package DAO;

import DTO.TonKhoDTO;
import Service.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TonKhoDAO {

    public List<TonKhoDTO> getTonKhoFromDatabase() {
        List<TonKhoDTO> tonKhoList = new ArrayList<>();
        String sql = "SELECT Ma_SanPham, Ten_SanPham, Don_Gia, Xuat_Xu, Loai_SanPham, SOLUONG FROM sanpham";

        try (Connection conn = JDBCConnection.JDBCConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TonKhoDTO tk = new TonKhoDTO(
                        rs.getString("Ma_SanPham"),
                        rs.getString("Ten_SanPham"),
                        rs.getBigDecimal("Don_Gia"),
                        rs.getString("Xuat_Xu"),
                        rs.getString("Loai_SanPham"),
                        rs.getInt("SOLUONG")
                );
                tonKhoList.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tonKhoList;
    }
     public static List<TonKhoDTO> timKiemTonKho(String keyword) {
    List<TonKhoDTO> danhSachTonKho = new ArrayList<>();
    String sql = "SELECT * FROM SANPHAM WHERE TEN_SANPHAM LIKE ? OR MA_SANPHAM LIKE ? OR XUAT_XU LIKE ? OR LOAI_SANPHAM LIKE ?";

    try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setString(4, searchPattern);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TonKhoDTO tk = new TonKhoDTO(
                    rs.getString("Ma_SanPham"),
                    rs.getString("Ten_SanPham"),
                    rs.getBigDecimal("Don_Gia"),
                    rs.getString("Xuat_Xu"),
                    rs.getString("Loai_SanPham"),
                    rs.getInt("SOLUONG")
            );
            danhSachTonKho.add(tk);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
    }
    return danhSachTonKho;
}
     
}
