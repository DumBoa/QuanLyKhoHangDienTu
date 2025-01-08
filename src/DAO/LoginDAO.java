/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.TaiKhoanDTO;
import Service.IQueryDataBaseTK;
import java.sql.*;
import Service.JDBCConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laptop
 */
public class LoginDAO implements IQueryDataBaseTK{
    @Override
    public List<TaiKhoanDTO> getTaiKhoanFromDataBase() {
        List<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        String sql = "SELECT TENDANGNHAP, MATKHAU , EMAIL, HOVATEN FROM TAIKHOAN";

        try (Connection conn = JDBCConnection.JDBCConnection();
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(
                        rs.getString("TENDANGNHAP"),
                        rs.getString("MATKHAU"),
                        rs.getString("EMAIL"),
                        rs.getString("HOVATEN")
                );
                taiKhoanList.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoanList;
    }
    
    @Override
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM taikhoan WHERE tendangnhap = ? AND matkhau = ?";
        try (Connection conn = JDBCConnection.JDBCConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
             

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối");
        }
        return false; 
    }
    
    @Override
    public List<TaiKhoanDTO> timKiemTaiKhoan(String keyword) {
    List<TaiKhoanDTO> danhSachSanPham = new ArrayList<>();
    String sql = "SELECT * FROM TAIKHOAN WHERE TENDANGNHAP LIKE ? OR HOVATEN LIKE ? OR EMAIL LIKE ?";

    try (Connection conn = JDBCConnection.JDBCConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TaiKhoanDTO sp = new TaiKhoanDTO(
                        rs.getString("TENDANGNHAP"),
                        rs.getString("MATKHAU"),
                        rs.getString("EMAIL"),
                        rs.getString("HOVATEN")
                );
                danhSachSanPham.add(sp);
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
    }

    return danhSachSanPham;
}
    
}

