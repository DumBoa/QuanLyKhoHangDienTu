
package Service;

import DTO.NhaCungCapDTO;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IQueryDataBaseNCC {
    
    List<NhaCungCapDTO> getNhaCungCapFromDataBase();

    void ThemNCC(NhaCungCapDTO sp);

    void SuaNCC(NhaCungCapDTO sp);

    void XoaNCC(String maSanPham);

    List<NhaCungCapDTO> timKiemNhaCungCap(String keyword);
    
}
