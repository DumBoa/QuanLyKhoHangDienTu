/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;

import Service.JDBCConnection;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Service.*;

/**
 *
 * @author Laptop
 */
public class NhaCungCapDAO implements IQueryDataBaseNCC {

    @Override
    public List<NhaCungCapDTO> getNhaCungCapFromDataBase(){
        List<NhaCungCapDTO> nccList = new ArrayList<>();

        String sql = "SELECT MA_NCC, TEN_NHACUNGCAP, SODIENTHOAI, DIACHI, EMAIL FROM NHACUNGCAP";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO(
                        rs.getString("MA_NCC"),
                        rs.getString("TEN_NHACUNGCAP"),
                        rs.getString("SODIENTHOAI"),
                        rs.getString("DIACHI"),
                        rs.getString("EMAIL")
                );
                nccList.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nccList;
    }

    @Override
    public void ThemNCC(NhaCungCapDTO ncc) {
        String sql = "INSERT INTO NHACUNGCAP (MA_NCC, TEN_NHACUNGCAP, SODIENTHOAI, DIACHI, EMAIL) " + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getMa_NCC());
            ps.setString(2, ncc.getTen_NhaCungCap());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setString(4, ncc.getDiaChi());
            ps.setString(5, ncc.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public void SuaNCC(NhaCungCapDTO ncc) {
        String sql = "UPDATE NHACUNGCAP SET TEN_NHACUNGCAP = ?, SODIENTHOAI = ?, DIACHI = ?, EMAIL = ? WHERE MA_NCC = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getTen_NhaCungCap());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(5, ncc.getMa_NCC());
            ps.setString(4, ncc.getEmail());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sửa nhà cung cấp thành công!");
            } else {
                throw new SQLException("Không tìm thấy nhà cung cấp với mã: " + ncc.getMa_NCC());
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa nhà cung cấp: " + e.getMessage());
            throw new RuntimeException("Mã nhà cung cấp phải có trước đó. Vui lòng kiểm tra dữ liệu hoặc kết nối cơ sở dữ liệu.");
        }
    }

    @Override
    public void XoaNCC(String maNhaCungCap) {
        String deleteQuery = "DELETE FROM nhacungcap WHERE MA_NCC = ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setString(1, maNhaCungCap);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Xóa nhà cung cấp thành công.");
            } else {
                throw new SQLException("Không tìm thấy mã nhà cung cấp Mã: " + maNhaCungCap);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa nhà cung cấp: " + e.getMessage());
            throw new RuntimeException("Không thể sửa nhà cung cấp. Vui lòng kiểm tra dữ liệu hoặc kết nối cơ sở dữ liệu.");
        }
    }

    @Override
    public List<NhaCungCapDTO> timKiemNhaCungCap(String keyword) {
        List<NhaCungCapDTO> danhSachNCC = new ArrayList<>();
        String sql = "SELECT * FROM NHACUNGCAP WHERE MA_NCC LIKE ? OR TEN_NHACUNGCAP LIKE ? OR DIACHI LIKE ? OR EMAIL LIKE ?";

        try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO(
                        rs.getString("MA_NCC"),
                        rs.getString("TEN_NHACUNGCAP"),
                        rs.getString("SODIENTHOAI"),
                        rs.getString("DIACHI"),
                        rs.getString("EMAIL")
                );
                danhSachNCC.add(ncc);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
        }
        return danhSachNCC;
    }
}
