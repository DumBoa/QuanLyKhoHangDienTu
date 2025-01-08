/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKeDTO;
import DTO.ThongKePNPX;
import Service.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Service.IQueryDataBaseThongKe;
import java.math.BigDecimal;

/**
 *
 * @author Laptop
 */
public class ThongKeDAO implements IQueryDataBaseThongKe {

    @Override
    public List<ThongKeDTO> getThongKeFromDatabase() {
        List<ThongKeDTO> thongKeList = new ArrayList<>();
        String sql = """
        SELECT 
            ROW_NUMBER() OVER (ORDER BY sp.MA_SANPHAM) AS STT,
            sp.MA_SANPHAM,
            sp.TEN_SANPHAM,
            COALESCE(SUM(nh.SOLUONG), 0) AS TONG_SOLUONG_NHAP,
            COALESCE(SUM(xh.SOLUONG), 0) AS TONG_SOLUONG_XUAT
        FROM 
            SANPHAM sp
        LEFT JOIN 
            NHAP_HANG nh ON sp.MA_SANPHAM = nh.MA_SANPHAM
        LEFT JOIN 
            XUAT_HANG xh ON sp.MA_SANPHAM = xh.MA_SANPHAM
        GROUP BY 
            sp.MA_SANPHAM, sp.TEN_SANPHAM;
    """;

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Tạo đối tượng ThongKe từ dữ liệu trả về
                ThongKeDTO thongKe = new ThongKeDTO(
                        rs.getInt("STT"),
                        rs.getString("MA_SANPHAM"),
                        rs.getString("TEN_SANPHAM"),
                        rs.getInt("TONG_SOLUONG_NHAP"),
                        rs.getInt("TONG_SOLUONG_XUAT")
                );
                thongKeList.add(thongKe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thongKeList;
    }

    @Override
    public int demSLSP() {
        String sql = "SELECT COUNT(MA_SANPHAM) AS Total FROM SANPHAM";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm sản phẩm: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int demSLNCC() {
        String sql = "SELECT COUNT(MA_NCC) AS SLNC FROM NHACUNGCAP";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("SLNC");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm nhà cung cấp: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int demSLTK() {
        String sql = "SELECT COUNT(TENDANGNHAP) AS SLTK FROM TAIKHOAN";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("SLTK");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm tài khoản: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public List<ThongKePNPX> getPhieuNhapVaXuat() {
        List<ThongKePNPX> danhSachPhieu = new ArrayList<>();
String sql = "SELECT MA_PHIEU_NHAP AS MaPhieu, NGUOI_TAO AS NguoiTao, THOI_GIAN_TAO AS ThoiGianTao, SUM(SOLUONG * DON_GIA) AS TongTien "
                + "FROM nhap_hang "
                + "GROUP BY MA_PHIEU_NHAP, NGUOI_TAO, THOI_GIAN_TAO "
                + "UNION ALL "
                + "SELECT MA_PHIEU_XUAT AS MaPhieu, NGUOI_TAO AS NguoiTao, THOI_GIAN_TAO AS ThoiGianTao, TONG_TIEN AS TongTien "
                + "FROM xuat_hang";


        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThongKePNPX phieu = new ThongKePNPX(
                        rs.getString("MaPhieu"),
                        rs.getString("NguoiTao"),
                        rs.getTimestamp("ThoiGianTao"),
                        rs.getBigDecimal("TongTien"),
                        0,
                        0
                );
                danhSachPhieu.add(phieu);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách phiếu: " + e.getMessage());
        }

        return danhSachPhieu;
    }

    @Override
    public List<ThongKePNPX> timKiemPhieuNhapTheoNgay(String ngayBatDau, String ngayKetThuc) {
        List<ThongKePNPX> danhSachPhieu = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_NHAP, NGUOI_TAO, THOI_GIAN_TAO, TONG_TIEN FROM nhap_hang WHERE THOI_GIAN_TAO BETWEEN ? AND ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ThongKePNPX phieu = new ThongKePNPX(
                            rs.getString("MA_PHIEU_NHAP"),
                            rs.getString("NGUOI_TAO"),
                            rs.getTimestamp("THOI_GIAN_TAO"),
                            rs.getBigDecimal("TONG_TIEN"),
                            0,
                            0
                    );
                    danhSachPhieu.add(phieu);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm phiếu nhập: " + e.getMessage());
        }

        return danhSachPhieu;
    }
    
    @Override
    public int demSLPN() {
        String sql = "SELECT COUNT(DISTINCT MA_PHIEU_NHAP) FROM NHAP_HANG";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đếm phiếu nhập: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int demSLPX() {

        String sql = "SELECT COUNT(DISTINCT MA_PHIEU_XUAT) FROM XUAT_HANG";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đếm phiếu xuất: " + e.getMessage());
        }
        return 0;

    }

    @Override
    public int demPNPX() {
        int tongSL;
        tongSL = demSLPN() + demSLPX();
        return tongSL;
    }

    @Override
    public BigDecimal tongTienPN() {
        String sql = "SELECT SUM(TONG_TIEN) FROM NHAP_HANG";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                BigDecimal PN = rs.getBigDecimal(1);
                return PN;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal tongTienPX() {
        String sql = "SELECT SUM(TONG_TIEN) FROM XUAT_HANG";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                BigDecimal PX = rs.getBigDecimal(1);
                return PX;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal tongTienPNPX() {
        tongTienPN();
        tongTienPX();
        return tongTienPN().add(tongTienPX());
    }

    @Override
    public List<ThongKePNPX> timKiemTheoGia(BigDecimal getMin, BigDecimal getMax) {
        ArrayList<ThongKePNPX> gia = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_NHAP, NGUOI_TAO, THOI_GIAN_TAO, TONG_TIEN FROM nhap_hang WHERE TONG_TIEN BETWEEN ? AND ?";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, getMin);
            ps.setBigDecimal(2, getMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ThongKePNPX phieu = new ThongKePNPX(
                            rs.getString("MA_PHIEU_NHAP"),
                            rs.getString("NGUOI_TAO"),
                            rs.getTimestamp("THOI_GIAN_TAO"),
                            rs.getBigDecimal("TONG_TIEN"),
                            0,
                            0
                    );
                    gia.add(phieu);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm giá: " + e.getMessage());
        }

        return gia;
    }
    
    @Override
    public List<ThongKePNPX> timKiemPhieuXuatTheoNgay(String ngayBatDau, String ngayKetThuc) {
        List<ThongKePNPX> danhSachPhieu = new ArrayList<>();
        String sql = "SELECT MA_PHIEU_XUAT, NGUOI_TAO, THOI_GIAN_TAO, TONG_TIEN FROM XUAT_HANG WHERE THOI_GIAN_TAO BETWEEN ? AND ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ThongKePNPX phieu = new ThongKePNPX(
                            rs.getString("MA_PHIEU_XUAT"),
                            rs.getString("NGUOI_TAO"),
                            rs.getTimestamp("THOI_GIAN_TAO"),
                            rs.getBigDecimal("TONG_TIEN"),
                            0,
                            0
                    );
                    danhSachPhieu.add(phieu);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm phiếu xuất: " + e.getMessage());
        }

        return danhSachPhieu;
    }
}
