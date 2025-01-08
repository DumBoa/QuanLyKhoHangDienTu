/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietXuatHangDTO;
import DTO.XuatHangDTO;
import Service.IQueryDataBaseXH;
//import DTO.PhieuNhapDTO;
import Service.JDBCConnection;
import java.sql.*;
/**
 *
 * @author Laptop
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XuatHangDAO implements IQueryDataBaseXH {

    @Override
    public List<XuatHangDTO> getXuatHangFromDataBase() {
        List<XuatHangDTO> xuatHangList = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_XUAT, MA_NCC, NGUOI_TAO, SOLUONG, DON_GIA, THOI_GIAN_TAO, MA_SANPHAM, TEN_SANPHAM FROM XUAT_HANG";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                XuatHangDTO xuatHang = new XuatHangDTO(
                        rs.getString("MA_PHIEU_XUAT"),
                        rs.getString("MA_NCC"),
                        rs.getString("NGUOI_TAO"),
                        rs.getInt("SOLUONG"),
                        rs.getBigDecimal("DON_GIA"),
                        rs.getTimestamp("THOI_GIAN_TAO"),
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM")
                //                                                rs.getBigDecimal("TONG_TIEN")
                );
                xuatHangList.add(xuatHang);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu xuất Hàng: " + e.getMessage());
            e.printStackTrace();
        }
        return xuatHangList;
    }

    @Override
    public void ThemXH(String maPhieuXuat, String maNCC, String nguoiNhap, List<ChiTietXuatHangDTO> danhSachChiTiet){
        String sql = """
        INSERT INTO XUAT_HANG (MA_PHIEU_XUAT, MA_NCC, NGUOI_TAO, MA_SANPHAM, TEN_SANPHAM, SOLUONG, DON_GIA, THOI_GIAN_TAO, TONG_TIEN)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (ChiTietXuatHangDTO chiTiet : danhSachChiTiet) {
                ps.setString(1, maPhieuXuat);
                ps.setString(2, maNCC);
                ps.setString(3, nguoiNhap);
                ps.setString(4, chiTiet.getMaSanPham());
                ps.setString(5, chiTiet.getTenSanPham());
                ps.setInt(6, chiTiet.getSoLuong());
                ps.setBigDecimal(7, chiTiet.getDonGia());
                ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                ps.setBigDecimal(9, chiTiet.getTongTien());

                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực hiện xuất hàng: ");
        }
    }
    public static BigDecimal tinhtongTienXH() {
        String sql = "SELECT SUM(SOLUONG * DON_GIA) AS TongTien FROM XUAT_HANG";
        BigDecimal tongTien = BigDecimal.ZERO;
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                tongTien = rs.getBigDecimal("TongTien");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tính tổng tiền nhập hàng: " + e.getMessage());
        }
        return tongTien;
    }

    @Override
    public List<XuatHangDTO> getTongQuanPhieuXuat() {
        List<XuatHangDTO> danhSachPhieuXuat = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_XUAT, NGUOI_TAO, COUNT(DISTINCT MA_SANPHAM) AS TONG_SO_SAN_PHAM, "
                + "SUM(SOLUONG * DON_GIA) AS TONG_TIEN,"
                + "MAX(THOI_GIAN_TAO) AS THOI_GIAN_TAO "
                + "FROM XUAT_HANG "
                + "GROUP BY MA_PHIEU_XUAT,NGUOI_TAO";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
            
                XuatHangDTO phieuXuat = new XuatHangDTO(
                        rs.getString("MA_PHIEU_XUAT"),
                        null, 
                        rs.getString("NGUOI_TAO"), 
                        rs.getInt("TONG_SO_SAN_PHAM"), 
                        null, 
                        rs.getTimestamp("THOI_GIAN_TAO"), 
                        null,
                        null
                );
                phieuXuat.setDonGia(rs.getBigDecimal("TONG_TIEN"));
                danhSachPhieuXuat.add(phieuXuat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachPhieuXuat;
    }

    @Override
    public String taoMaPhieuXuatMoi(){
        String sql = "SELECT MAX(MA_PHIEU_XUAT) FROM XUAT_HANG";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getString(1) != null) {
                String mpnCuoi = rs.getString(1);
                int nextId = Integer.parseInt(mpnCuoi.substring(2)) + 1;

                return String.format("PX%03d", nextId);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi tạo mã phiếu nhập");
        }
        return "PX001";
    }
    
    @Override
    public List<XuatHangDTO> getChiTietXuatHang(String maPhieuXuat) {
        List<XuatHangDTO> chiTietList = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_XUAT, MA_NCC, NGUOI_TAO, SOLUONG, DON_GIA, THOI_GIAN_TAO, MA_SANPHAM, TEN_SANPHAM FROM XUAT_HANG WHERE MA_PHIEU_XUAT = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuXuat);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                XuatHangDTO xuatHang = new XuatHangDTO(
                        rs.getString("MA_PHIEU_XUAT"),
                        rs.getString("MA_NCC"),
                        rs.getString("NGUOI_TAO"),
                        rs.getInt("SOLUONG"),
                        rs.getBigDecimal("DON_GIA"),
                        rs.getTimestamp("THOI_GIAN_TAO"),
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM")
                );
                chiTietList.add(xuatHang);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu chi tiết xuất Hàng: " + e.getMessage());
        }
        return chiTietList;
    }
    
    @Override
    public List<XuatHangDTO> TimKiemPX(String keyword) {
        List<XuatHangDTO> danhSachPXTQ = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_XUAT, NGUOI_TAO, COUNT(MA_SANPHAM) AS TongSoSP, SUM(SOLUONG * DON_GIA) AS TongDonGia, MAX(THOI_GIAN_TAO) AS THOI_GIAN_TAO "
                + "FROM XUAT_HANG "
                + "WHERE MA_PHIEU_XUAT LIKE ? OR NGUOI_TAO LIKE ? "
                + "GROUP BY MA_PHIEU_XUAT, NGUOI_TAO";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    XuatHangDTO sp = new XuatHangDTO(
                            rs.getString("MA_PHIEU_XUAT"),
                            null,
                            rs.getString("NGUOI_TAO"),
                            rs.getInt("TongSoSP"),
                            rs.getBigDecimal("TongDonGia"),
                            rs.getTimestamp("THOI_GIAN_TAO"),
                            null,
                            null
                    );
                    danhSachPXTQ.add(sp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm phiếu nhập: " + e.getMessage());
        }

        return danhSachPXTQ;
    }
    @Override
    public void XoaXH(String maPhieuXuat) {
        String deleteSQL = "DELETE FROM XUAT_HANG WHERE MA_PHIEU_XUAT = ?";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
            stmt.setString(1, maPhieuXuat);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa phiếu xuất thành công.");
            } else {
                throw new SQLException("Không tìm thấy phiếu xuất để xóa! Mã: " + maPhieuXuat);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phiếu nhập: " + e.getMessage());
            throw new RuntimeException("Không thể xóa phiếu xuất. Vui lòng kiểm tra lại!");
        }
    }

}
