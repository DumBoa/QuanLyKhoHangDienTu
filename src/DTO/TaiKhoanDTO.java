/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Laptop
 */
public class TaiKhoanDTO {

    private String tenDangNhap;
    private String matkhau;
    private String email;
    private String hovaten;

    public TaiKhoanDTO(String tenDangNhap, String matKhau, String email, String hovaten) {
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matKhau;
        this.email = email;
        this.hovaten = hovaten;
    }

    public String getEmail() {
        return email;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

}
