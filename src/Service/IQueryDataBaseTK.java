/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DTO.TaiKhoanDTO;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseTK {

    List<TaiKhoanDTO> getTaiKhoanFromDataBase();

    boolean authenticateUser(String username, String password);
    
    List<TaiKhoanDTO> timKiemTaiKhoan(String keyword);
}
