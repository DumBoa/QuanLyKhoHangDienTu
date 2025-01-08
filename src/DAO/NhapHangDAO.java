/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietNhapHangDTO;
import DTO.NhapHangDTO;
import Service.IQueryDataBaseNH;
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

public class NhapHangDAO implements IQueryDataBaseNH {

    @Override
    public List<NhapHangDTO> getNhapHangFromDataBase() {
        List<NhapHangDTO> nhapHangList = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_NHAP, MA_NCC, NGUOI_TAO, SOLUONG, DON_GIA, THOI_GIAN_TAO, MA_SANPHAM, TEN_SANPHAM FROM NHAP_HANG";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhapHangDTO nhapHang = new NhapHangDTO(
                        rs.getString("MA_PHIEU_NHAP"),
                        rs.getString("MA_NCC"),
                        rs.getString("NGUOI_TAO"),
                        rs.getInt("SOLUONG"),
                        rs.getBigDecimal("DON_GIA"),
                        rs.getTimestamp("THOI_GIAN_TAO"),
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM")
                //      rs.getBigDecimal("TONG_TIEN")
                );
                nhapHangList.add(nhapHang);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu Nhập Hàng: " + e.getMessage());
        }
        return nhapHangList;
    }

    @Override
    public void ThemNH(String maPhieuNhap, String maNCC, String nguoiNhap, List<ChiTietNhapHangDTO> danhSachChiTiet) {
        String sql = """
        INSERT INTO NHAP_HANG (MA_PHIEU_NHAP, MA_NCC, NGUOI_TAO, MA_SANPHAM, TEN_SANPHAM, SOLUONG, DON_GIA, THOI_GIAN_TAO, TONG_TIEN)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (ChiTietNhapHangDTO chiTiet : danhSachChiTiet) {
                ps.setString(1, maPhieuNhap);
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
            System.out.println(e.getMessage() + "Lỗi khi thực hiện nhập hàng: ");
        }
    }

    public static BigDecimal tinhtongTienNH() {
        String sql = "SELECT SUM(SOLUONG * DON_GIA) AS TongTien FROM NHAP_HANG";
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
    public List<NhapHangDTO> getTongQuanPhieuNhap() {
        List<NhapHangDTO> danhSachPhieuNhap = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_NHAP, NGUOI_TAO, COUNT(DISTINCT MA_SANPHAM) AS TONG_SO_SAN_PHAM, "
                + "SUM(SOLUONG * DON_GIA) AS TONG_TIEN, "
                + "MAX(THOI_GIAN_TAO) AS THOI_GIAN_TAO "
                + "FROM NHAP_HANG "
                + "GROUP BY MA_PHIEU_NHAP, NGUOI_TAO";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhapHangDTO phieuNhap = new NhapHangDTO(
                        rs.getString("MA_PHIEU_NHAP"),
                        null, // Mã NCC
                        rs.getString("NGUOI_TAO"),
                        rs.getInt("TONG_SO_SAN_PHAM"),
                        null, // Đơn giá
                        rs.getTimestamp("THOI_GIAN_TAO"), 
                        null, // Mã sản phẩm
                        null // Tên sản phẩm
                );
                phieuNhap.setDonGia(rs.getBigDecimal("TONG_TIEN"));
                danhSachPhieuNhap.add(phieuNhap);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
        }

        return danhSachPhieuNhap;
    }

    @Override
    public String taoMaPhieuNhapMoi() {
        String sql = "SELECT MAX(MA_PHIEU_NHAP) FROM NHAP_HANG";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getString(1) != null) {
                String mpnCuoi = rs.getString(1);
                int nextId = Integer.parseInt(mpnCuoi.substring(2)) + 1;

                return String.format("PN%03d", nextId);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi tạo mã phiếu nhập");
        }
        return "PN001";
    }

    @Override
    public void XoaNH(String maPhieuNhap) {
        String deleteSQL = "DELETE FROM NHAP_HANG WHERE MA_PHIEU_NHAP = ?";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
            stmt.setString(1, maPhieuNhap);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa phiếu nhập thành công.");
            } else {
                throw new SQLException("Không tìm thấy phiếu nhập để xóa! Mã: " + maPhieuNhap);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phiếu nhập: " + e.getMessage());
            throw new RuntimeException("Không thể xóa phiếu nhập. Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public List<NhapHangDTO> getChiTietNhapHang(String maPhieuNhap) {
        List<NhapHangDTO> chiTietList = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_NHAP, MA_NCC, NGUOI_TAO, SOLUONG, DON_GIA, THOI_GIAN_TAO, MA_SANPHAM, TEN_SANPHAM FROM NHAP_HANG WHERE MA_PHIEU_NHAP = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhieuNhap);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                NhapHangDTO nhapHang = new NhapHangDTO(
                        rs.getString("MA_PHIEU_NHAP"),
                        rs.getString("MA_NCC"),
                        rs.getString("NGUOI_TAO"),
                        rs.getInt("SOLUONG"),
                        rs.getBigDecimal("DON_GIA"),
                        rs.getTimestamp("THOI_GIAN_TAO"),
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM")
                );
                chiTietList.add(nhapHang);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu chi tiết Nhập Hàng: " + e.getMessage());
        }
        return chiTietList;
    }

    @Override
    public List<NhapHangDTO> TimKiemPN(String keyword) {
        List<NhapHangDTO> danhSachPNTQ = new ArrayList<>();
    String sql = "SELECT MA_PHIEU_NHAP, NGUOI_TAO, COUNT(MA_SANPHAM) AS TongSoSP, SUM(SOLUONG * DON_GIA) AS TongDonGia, MAX(THOI_GIAN_TAO) AS THOI_GIAN_TAO "
               + "FROM NHAP_HANG "
               + "WHERE MA_PHIEU_NHAP LIKE ? OR NGUOI_TAO LIKE ? "
               + "GROUP BY MA_PHIEU_NHAP, NGUOI_TAO";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhapHangDTO sp = new NhapHangDTO(
                            rs.getString("MA_PHIEU_NHAP"),
                            null,
                            rs.getString("NGUOI_TAO"),
                            rs.getInt("TongSoSP"),
                            rs.getBigDecimal("TongDonGia"),
                            rs.getTimestamp("THOI_GIAN_TAO"),
                            null,
                            null
                    );
                    danhSachPNTQ.add(sp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm phiếu nhập: " + e.getMessage());
        }

        return danhSachPNTQ;
    }

}
