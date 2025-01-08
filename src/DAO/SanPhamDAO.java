/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
import DTO.TimKiemSP;
import Service.JDBCConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import Service.IQueryDataBaseSP;
import java.math.BigDecimal;

/**
 *
 * @author Laptop
 */
public class SanPhamDAO implements IQueryDataBaseSP {

    @Override
    public List<SanPhamDTO> getSanPhamFromDatabase() {
        List<SanPhamDTO> sanPhamList = new ArrayList<>();
        String sql = "SELECT Ma_SanPham, Ten_SanPham, Don_Gia, Xuat_Xu, Loai_SanPham, SOLUONG FROM sanpham";

        try (Connection conn = JDBCConnection.JDBCConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SanPhamDTO sanPham = new SanPhamDTO(
                        rs.getString("Ma_SanPham"),
                        rs.getString("Ten_SanPham"),
                        rs.getBigDecimal("Don_Gia"),
                        rs.getString("Xuat_Xu"),
                        rs.getString("Loai_SanPham"),
                        rs.getInt("SOLUONG")
                );
                sanPhamList.add(sanPham);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
        return sanPhamList;
    }

    @Override
    public void ThemSP(SanPhamDTO sp) {
        String sql = "INSERT INTO SANPHAM (MA_SANPHAM, TEN_SANPHAM, DON_GIA, XUAT_XU, LOAI_SANPHAM, SOLUONG) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getMa_SanPham());
            ps.setString(2, sp.getTen_SanPham());
            ps.setBigDecimal(3, sp.getDonGia());
            ps.setString(4, sp.getXuatXu());
            ps.setString(5, sp.getLoai_SanPham());
            ps.setInt(6, sp.getSoLuong());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public void SuaSP(SanPhamDTO sp) {
        String sql = "UPDATE sanpham SET Ten_SanPham = ?, Don_Gia = ?, Xuat_Xu = ?, Loai_SanPham = ?, SOLUONG = ? WHERE Ma_SanPham = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTen_SanPham());
            ps.setBigDecimal(2, sp.getDonGia());
            ps.setString(3, sp.getXuatXu());
            ps.setString(4, sp.getLoai_SanPham());
            ps.setInt(5, sp.getSoLuong());
            ps.setString(6, sp.getMa_SanPham());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi khi sửa sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public void XoaSP(String maSanPham) {
        String sql = "DELETE FROM sanpham WHERE Ma_SanPham = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSanPham);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    @Override
   public List<SanPhamDTO> timKiemSanPham(String keyword) {
    List<SanPhamDTO> danhSachSanPham = new ArrayList<>();
    String sql = "SELECT * FROM SANPHAM WHERE TEN_SANPHAM LIKE ? OR MA_SANPHAM LIKE ? OR XUAT_XU LIKE ? OR LOAI_SANPHAM LIKE ?";

    try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setString(4, searchPattern);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM"),
                        rs.getBigDecimal("DON_GIA"),
                        rs.getString("XUAT_XU"),
                        rs.getString("LOAI_SANPHAM"),
                        rs.getInt("SOLUONG")
                );
                danhSachSanPham.add(sp);
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
    }

    return danhSachSanPham;
}


    public static List<TimKiemSP> TimKiemSanPhamNH(String keyword) {
        List<TimKiemSP> danhSachSanPham = new ArrayList<>();
        String sql = "SELECT * FROM SANPHAM WHERE TEN_SANPHAM LIKE ? OR MA_SANPHAM LIKE ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TimKiemSP sp = new TimKiemSP(
                        rs.getString("Ma_SanPham"),
                        rs.getString("Ten_SanPham"),
                        rs.getInt("SOLUONG"),
                        rs.getBigDecimal("DON_GIA")
                );
                danhSachSanPham.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
        }
        return danhSachSanPham;
    }

    @Override
    public BigDecimal TongTienSP() {
        String sql = "SELECT SUM(DON_GIA) AS TongTien FROM SANPHAM";
        BigDecimal tongTien = BigDecimal.ZERO;

        try (Connection conn = JDBCConnection.JDBCConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                tongTien = rs.getBigDecimal("TongTien");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tính tổng tiền sản phẩm: " + e.getMessage());
        }

        return tongTien;
    }

}
