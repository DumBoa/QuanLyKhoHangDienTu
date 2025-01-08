
package GUI;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import DTO.SanPhamDTO;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import DAO.*;
import DTO.*;
import DTO.NhaCungCapDTO;
import DTO.ThongKeDTO;
import DAO.ThongKeDAO;
import DTO.ChiTietNhapHangDTO;
import DTO.NhapHangDTO;
import DTO.TaiKhoanDTO;
import DTO.TimKiemSP;
import Service.IQueryDataBaseNCC;
import Service.IQueryDataBaseNH;
import java.math.BigDecimal;
import java.util.ArrayList;
import Service.IQueryDataBaseSP;
import Service.IQueryDataBaseThongKe;
import java.text.DecimalFormat;

public final class GUITONG extends javax.swing.JPanel {

    public GUITONG() {
        initComponents();
        loadTableNCC();
        loadTableThongKE();
        loadTablePhieuNhap();
        loadTable1NhapHang();
        loadTable();
        loadTableThongKeTK();
        loadTableXuatHang();
        loadTablePhieuXuat();
        loadPNPX();
        loadPNTK();
        loadTonKho();
        loadPXTK();
    }

    public void loadTable() {
        SanPhamDAO SPDAO = new SanPhamDAO();
        List<SanPhamDTO> sanPhamList = SPDAO.getSanPhamFromDatabase();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá", "Xuất Xứ", "Loại Sản Phẩm", "Số Lượng"
        }, 0);

        for (SanPhamDTO sanPham : sanPhamList) {
            tableModel.addRow(new Object[]{
                sanPham.getMa_SanPham(),
                sanPham.getTen_SanPham(),
                sanPham.getDonGia(),
                sanPham.getXuatXu(),
                sanPham.getLoai_SanPham(),
                sanPham.getSoLuong()});
        }

        TableSanPham.setModel(tableModel);
    }

    public void loadTableNCC() {
        NhaCungCapDAO NCCDAO = new NhaCungCapDAO();
        List<NhaCungCapDTO> nccList = NCCDAO.getNhaCungCapFromDataBase();

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "Địa Chỉ", "Email"
        }, 0);

        for (NhaCungCapDTO ncc : nccList) {
            tableModel.addRow(new Object[]{
                ncc.getMa_NCC(),
                ncc.getTen_NhaCungCap(),
                ncc.getSoDienThoai(),
                ncc.getDiaChi(),
                ncc.getEmail()});

        }

        Table_NCC.setModel(tableModel);
    }

    public void loadTableThongKE() {
        ThongKeDAO thongKeDAO = new ThongKeDAO();
        List<ThongKeDTO> tkList = thongKeDAO.getThongKeFromDatabase();

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "STT", "Mã Hàng", "Tên Hàng", "Số Lượng Nhập", "Số Lượng Xuất"
        }, 0);

        for (ThongKeDTO tk : tkList) {
            tableModel.addRow(new Object[]{
                tk.getStt(),
                tk.getMa_SanPham(),
                tk.getTen_SanPham(),
                tk.getSoLuongNhap(),
                tk.getSoLuongXuat()});
        }
        TableThongKe.setModel(tableModel);
    }

    public void loadTableThongKeTK() {
        LoginDAO LGDAO = new LoginDAO();
        List<TaiKhoanDTO> taiKhoanList = LGDAO.getTaiKhoanFromDataBase();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Tên Đăng Nhập", "Mật Khẩu", "Email", "Họ Và Tên"
        }, 0);
        for (TaiKhoanDTO tk : taiKhoanList) {
            tableModel.addRow(new Object[]{
                tk.getTenDangNhap(),
                tk.getMatkhau(),
                tk.getEmail(),
                tk.getHovaten()});
        }

        TableCTTK.setModel(tableModel);
    }

    public void loadTablePhieuNhap() {
        NhapHangDAO PNDAO = new NhapHangDAO();
        List<NhapHangDTO> phieuNhapList = PNDAO.getTongQuanPhieuNhap();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Phiếu Nhập", "Người Tạo", "Số Sản Phẩm Được Chọn", "Tổng Tiền"
        }, 0);
        for (NhapHangDTO phieuNhap : phieuNhapList) {
            tableModel.addRow(new Object[]{
                phieuNhap.getMa_Phieu_Nhap(),
                phieuNhap.getNguoi_Tao(),
                phieuNhap.getSoLuong(),
                phieuNhap.getDonGia()
            });
        }
        TablePhieuNhap.setModel(tableModel);
    }

    public void loadTable1NhapHang() {

        SanPhamDAO SPDAO = new SanPhamDAO();
        List<SanPhamDTO> sanPhamList = SPDAO.getSanPhamFromDatabase();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Sản Phẩm", "Tên Hàng", "Số Lượng", "Đơn Giá"
        }, 0);
        for (SanPhamDTO sanPham : sanPhamList) {
            tableModel.addRow(new Object[]{
                sanPham.getMa_SanPham(),
                sanPham.getTen_SanPham(),
                sanPham.getSoLuong(),
                sanPham.getDonGia()
            });
        }
        TableNhapHang1.setModel(tableModel);
    }

    public void loadTablePhieuXuat() {
        XuatHangDAO PXDAO = new XuatHangDAO();
        List<XuatHangDTO> phieuXuatList = PXDAO.getTongQuanPhieuXuat();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Phiếu Xuất", "Người Tạo", "Tổng Số Sản Phẩm", "Tổng Tiền"
        }, 0);
        for (XuatHangDTO phieuXuat : phieuXuatList) {
            tableModel.addRow(new Object[]{
                phieuXuat.getMa_Phieu_Xuat(),
                phieuXuat.getNguoi_Tao(),
                phieuXuat.getSoLuong(),
                phieuXuat.getDonGia()
            });
        }
        TablePhieuXuat.setModel(tableModel);
    }

    public void loadTableXuatHang() {
        SanPhamDAO SPDAO = new SanPhamDAO();
        List<SanPhamDTO> sanPhamList = SPDAO.getSanPhamFromDatabase();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Sản Phẩm", "Tên Hàng", "Số Lượng", "Đơn Giá"
        }, 0);
        for (SanPhamDTO sanPham : sanPhamList) {
            tableModel.addRow(new Object[]{
                sanPham.getMa_SanPham(),
                sanPham.getTen_SanPham(),
                sanPham.getSoLuong(),
                sanPham.getDonGia()
            });
        }
        TableXuatHang.setModel(tableModel);
    }

    public void loadPNPX() {
        ThongKeDAO tkDAO = new ThongKeDAO();
        List<ThongKePNPX> danhSachPhieu = tkDAO.getPhieuNhapVaXuat();

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Phiếu", "Người Tạo", "Thời Gian Tạo", "Tổng Tiền"
        }, 0);
        for (ThongKePNPX phieu : danhSachPhieu) {
            tableModel.addRow(new Object[]{
                phieu.getMaPhieu(),
                phieu.getNguoiTao(),
                phieu.getThoiGianTao(),
                phieu.getTongTien()
            });
        }
        PNPX.setModel(tableModel);
    }

    public void loadPNTK() {
        NhapHangDAO PNDAO = new NhapHangDAO();
        List<NhapHangDTO> phieuNhapList = PNDAO.getTongQuanPhieuNhap();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Phiếu Nhập", "Người Tạo", "Thời Gian Tạo", "Tổng Tiền"
        }, 0);
        for (NhapHangDTO phieuNhap : phieuNhapList) {
            tableModel.addRow(new Object[]{
                phieuNhap.getMa_Phieu_Nhap(),
                phieuNhap.getNguoi_Tao(),
                phieuNhap.getThoi_Gian_Tao(),
                phieuNhap.getDonGia()
            });
        }
        PNPX.setModel(tableModel);
    }

    public void loadPXTK() {
        XuatHangDAO PXDAO = new XuatHangDAO();
        List<XuatHangDTO> phieuXuatList = PXDAO.getTongQuanPhieuXuat();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Phiếu Xuất", "Người Tạo", "Thời Gian Tạo", "Tổng Tiền"
        }, 0);
        for (XuatHangDTO phieuXuat : phieuXuatList) {
            tableModel.addRow(new Object[]{
                phieuXuat.getMa_Phieu_Xuat(),
                phieuXuat.getNguoi_Tao(),
                phieuXuat.getThoi_Gian_Tao(),
                phieuXuat.getDonGia()
            });
        }
        PNPX.setModel(tableModel);
    }

    public void loadTonKho() {
        TonKhoDAO TKDAO = new TonKhoDAO();
        List<TonKhoDTO> tonKhoList = TKDAO.getTonKhoFromDatabase();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá", "Xuất Xứ", "Loại Sản Phẩm", "Số Lượng"
        }, 0);
        for (TonKhoDTO tonkho : tonKhoList) {
            tableModel.addRow(new Object[]{
                tonkho.getMa_SanPham(),
                tonkho.getTen_SanPham(),
                tonkho.getDonGia(),
                tonkho.getXuatXu(),
                tonkho.getLoai_SanPham(),
                tonkho.getSoLuong()});
        }

        TabbleTonKho.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftPanel = new javax.swing.JPanel();
        dangxuat = new javax.swing.JButton();
        tonkho = new javax.swing.JButton();
        phieuxuat = new javax.swing.JButton();
        xuathang = new javax.swing.JButton();
        phieunhap = new javax.swing.JButton();
        nhaphang = new javax.swing.JButton();
        NCCButton = new javax.swing.JButton();
        sanpham = new javax.swing.JButton();
        thongke = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TenTK = new javax.swing.JLabel();
        TabTong = new javax.swing.JTabbedPane();
        TabSanPham = new javax.swing.JPanel();
        SanPhamGUI4 = new javax.swing.JPanel();
        ChucNangSP = new javax.swing.JPanel();
        ThemSP = new javax.swing.JButton();
        SuaSP = new javax.swing.JButton();
        XoaSP = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        TimKiemSP = new javax.swing.JPanel();
        TimKiem = new javax.swing.JTextField();
        LoadSP = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableSanPham = new javax.swing.JTable();
        MaSanPham = new javax.swing.JTextField();
        LoaiSanPham = new javax.swing.JTextField();
        DonGia = new javax.swing.JTextField();
        SoLuongSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        XuatXu = new javax.swing.JTextField();
        TenSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        TabNCC = new javax.swing.JPanel();
        NCC_GUI = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        Them_NCC = new javax.swing.JButton();
        Sua_NCC = new javax.swing.JButton();
        Xoa_NCC = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_NCC = new javax.swing.JTable();
        MNCC_NCC = new javax.swing.JTextField();
        SDT_NCC = new javax.swing.JTextField();
        TNCC_NCC = new javax.swing.JTextField();
        DiaChi_NCC = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        TimKiemSP3 = new javax.swing.JPanel();
        TimKiemNCC = new javax.swing.JTextField();
        LoadNCC = new javax.swing.JButton();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        Email_NCC = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        TabNhapHang = new javax.swing.JPanel();
        SanPhamGUI5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableNhapHang1 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        TimKiemSP4 = new javax.swing.JPanel();
        TimKiemNH = new javax.swing.JTextField();
        Load5 = new javax.swing.JButton();
        jSeparator24 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        SoLuongtxt = new javax.swing.JTextField();
        ThemHangVaoTabbleNH = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        MaNCCtxt = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableNhapHang2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        XoaSPNH = new javax.swing.JButton();
        NHButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        NguoiNhaptxt = new javax.swing.JTextField();
        SuaSLNH = new javax.swing.JButton();
        TabXuatHang = new javax.swing.JPanel();
        TabXuatHang1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableXuatHang = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        MaNCCtxt1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        NguoiNhaptxt1 = new javax.swing.JTextField();
        MaPhieuXuattxt = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableXuatHang1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        XSP = new javax.swing.JButton();
        XH = new javax.swing.JButton();
        SSLXH = new javax.swing.JButton();
        TimKiemSP5 = new javax.swing.JPanel();
        TimKiemNH1 = new javax.swing.JTextField();
        Load6 = new javax.swing.JButton();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        ThemXuathangTable = new javax.swing.JButton();
        SLtxt = new javax.swing.JTextField();
        TabPhieuXuat = new javax.swing.JPanel();
        NhapHangGUI1 = new javax.swing.JPanel();
        ChucNangSP3 = new javax.swing.JPanel();
        ThemPN1 = new javax.swing.JButton();
        SuaPN1 = new javax.swing.JButton();
        XoaPN1 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TablePhieuXuat = new javax.swing.JTable();
        TimKiemSP2 = new javax.swing.JPanel();
        TextTimKiemPN1 = new javax.swing.JTextField();
        Load2 = new javax.swing.JButton();
        jSeparator28 = new javax.swing.JSeparator();
        TabTonKho = new javax.swing.JPanel();
        NCC_GUI1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TabbleTonKho = new javax.swing.JTable();
        TimKiemSP6 = new javax.swing.JPanel();
        TimKiemTonKho = new javax.swing.JTextField();
        LoadNCC1 = new javax.swing.JButton();
        jSeparator29 = new javax.swing.JSeparator();
        TabThongKe = new javax.swing.JPanel();
        SpNext = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        HienThiSLSP = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        NCCnext = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        HienThiSLNCC = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        TKnext = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        HienThiSLTK = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        TabbedTK = new javax.swing.JTabbedPane();
        TableCTSP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableThongKe = new javax.swing.JTable();
        TableCTNCC = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        PNPX = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        TongTienPNPX = new javax.swing.JLabel();
        TextTPNPX = new javax.swing.JLabel();
        HienThiPhieu = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        XemChiTietPNPX = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        TimKiemPNTK = new javax.swing.JTextField();
        SelectPNPXBox = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        ngayBatDau = new javax.swing.JTextField();
        NgayKetThuc = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        GiaKetThuc = new javax.swing.JTextField();
        GiaBatDau = new javax.swing.JTextField();
        TableTaiKhoan = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableCTTK = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        TimKiemPNTK1 = new javax.swing.JTextField();
        SelectPNPXBox1 = new javax.swing.JComboBox<>();
        PanelNCC = new javax.swing.JPanel();
        PNPXnext = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        HienThiTongSoPhieu = new javax.swing.JLabel();
        TabPhieuNhap = new javax.swing.JPanel();
        NhapHangGUI = new javax.swing.JPanel();
        ChucNangSP2 = new javax.swing.JPanel();
        ThemPN = new javax.swing.JButton();
        SuaPN = new javax.swing.JButton();
        XoaPN = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablePhieuNhap = new javax.swing.JTable();
        TimKiemSP1 = new javax.swing.JPanel();
        TextTimKiemPN = new javax.swing.JTextField();
        Load1 = new javax.swing.JButton();
        jSeparator25 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeftPanel.setBackground(new java.awt.Color(89, 168, 104));

        dangxuat.setBackground(new java.awt.Color(89, 168, 104));
        dangxuat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dangxuat.setForeground(new java.awt.Color(255, 255, 255));
        dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/poweroff.png"))); // NOI18N
        dangxuat.setText("Đăng Xuất");
        dangxuat.setBorderPainted(false);
        dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dangxuatMouseClicked(evt);
            }
        });
        dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangxuatActionPerformed(evt);
            }
        });

        tonkho.setBackground(new java.awt.Color(89, 168, 104));
        tonkho.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tonkho.setForeground(new java.awt.Color(255, 255, 255));
        tonkho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/kho16x16.png"))); // NOI18N
        tonkho.setText("Tồn Kho");
        tonkho.setBorderPainted(false);
        tonkho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tonkho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tonkhoMouseClicked(evt);
            }
        });

        phieuxuat.setBackground(new java.awt.Color(89, 168, 104));
        phieuxuat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        phieuxuat.setForeground(new java.awt.Color(255, 255, 255));
        phieuxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1731814591430.png"))); // NOI18N
        phieuxuat.setText("Phiếu Xuất");
        phieuxuat.setBorderPainted(false);
        phieuxuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phieuxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phieuxuatMouseClicked(evt);
            }
        });

        xuathang.setBackground(new java.awt.Color(89, 168, 104));
        xuathang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        xuathang.setForeground(new java.awt.Color(255, 255, 255));
        xuathang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/xh16x16.png"))); // NOI18N
        xuathang.setText("Xuất Hàng");
        xuathang.setBorderPainted(false);
        xuathang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        xuathang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xuathangMouseClicked(evt);
            }
        });

        phieunhap.setBackground(new java.awt.Color(89, 168, 104));
        phieunhap.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        phieunhap.setForeground(new java.awt.Color(255, 255, 255));
        phieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1731814577351.png"))); // NOI18N
        phieunhap.setText("Phiếu Nhập");
        phieunhap.setBorderPainted(false);
        phieunhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phieunhapMouseClicked(evt);
            }
        });

        nhaphang.setBackground(new java.awt.Color(89, 168, 104));
        nhaphang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nhaphang.setForeground(new java.awt.Color(255, 255, 255));
        nhaphang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/NhapHang.png"))); // NOI18N
        nhaphang.setText("Nhập Hàng");
        nhaphang.setBorderPainted(false);
        nhaphang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nhaphang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhaphangMouseClicked(evt);
            }
        });

        NCCButton.setBackground(new java.awt.Color(89, 168, 104));
        NCCButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NCCButton.setForeground(new java.awt.Color(255, 255, 255));
        NCCButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/NCC.png"))); // NOI18N
        NCCButton.setText("Nhà Cung Cấp");
        NCCButton.setBorderPainted(false);
        NCCButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NCCButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NCCButtonMouseClicked(evt);
            }
        });
        NCCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NCCButtonActionPerformed(evt);
            }
        });

        sanpham.setBackground(new java.awt.Color(89, 168, 104));
        sanpham.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sanpham.setForeground(new java.awt.Color(255, 255, 255));
        sanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/SP16x16.png"))); // NOI18N
        sanpham.setText("Sản Phẩm");
        sanpham.setBorderPainted(false);
        sanpham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanphamMouseClicked(evt);
            }
        });
        sanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanphamActionPerformed(evt);
            }
        });

        thongke.setBackground(new java.awt.Color(89, 168, 104));
        thongke.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        thongke.setForeground(new java.awt.Color(255, 255, 255));
        thongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/tk16x16.png"))); // NOI18N
        thongke.setText("Thống Kê");
        thongke.setBorderPainted(false);
        thongke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        thongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongkeMouseClicked(evt);
            }
        });
        thongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongkeActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Wellcome Back !");

        TenTK.setBackground(new java.awt.Color(255, 255, 255));
        TenTK.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TenTK.setText("Admin");

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(TenTK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thongke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tonkho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phieuxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xuathang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NCCButton, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(sanpham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TenTK)
                .addGap(66, 66, 66)
                .addComponent(sanpham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NCCButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nhaphang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phieunhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xuathang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phieuxuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tonkho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongke)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(dangxuat)
                .addGap(21, 21, 21))
        );

        add(LeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TabTong.setBackground(new java.awt.Color(255, 255, 255));

        TabSanPham.setBackground(new java.awt.Color(255, 255, 255));

        SanPhamGUI4.setBackground(new java.awt.Color(255, 255, 255));
        SanPhamGUI4.setToolTipText("");
        SanPhamGUI4.setName(""); // NOI18N

        ChucNangSP.setBackground(new java.awt.Color(255, 255, 255));
        ChucNangSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        ChucNangSP.setToolTipText("");
        ChucNangSP.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        ThemSP.setBackground(new java.awt.Color(255, 255, 255));
        ThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732076940676.png"))); // NOI18N
        ThemSP.setBorder(null);
        ThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemSPMouseClicked(evt);
            }
        });
        ThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSPActionPerformed(evt);
            }
        });

        SuaSP.setBackground(new java.awt.Color(255, 255, 255));
        SuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        SuaSP.setBorder(null);
        SuaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaSPMouseClicked(evt);
            }
        });

        XoaSP.setBackground(new java.awt.Color(255, 255, 255));
        XoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        XoaSP.setBorder(null);
        XoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaSPMouseClicked(evt);
            }
        });

        jLabel23.setText("Thêm");

        jLabel29.setText("Sửa");

        jLabel30.setText("Xóa");

        javax.swing.GroupLayout ChucNangSPLayout = new javax.swing.GroupLayout(ChucNangSP);
        ChucNangSP.setLayout(ChucNangSPLayout);
        ChucNangSPLayout.setHorizontalGroup(
            ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSPLayout.createSequentialGroup()
                .addGroup(ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChucNangSPLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(ThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(ChucNangSPLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addGap(33, 33, 33)))
                .addGroup(ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChucNangSPLayout.createSequentialGroup()
                        .addComponent(XoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSPLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(37, 37, 37))))
        );
        ChucNangSPLayout.setVerticalGroup(
            ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSPLayout.createSequentialGroup()
                .addGroup(ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThemSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(XoaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ChucNangSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)))
        );

        TimKiemSP.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TimKiem.setBackground(new java.awt.Color(255, 255, 255));
        TimKiem.setBorder(null);
        TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemActionPerformed(evt);
            }
        });
        TimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemKeyTyped(evt);
            }
        });

        LoadSP.setBackground(new java.awt.Color(255, 255, 255));
        LoadSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        LoadSP.setText(" Làm mới");
        LoadSP.setBorder(null);
        LoadSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadSPMouseClicked(evt);
            }
        });
        LoadSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadSPActionPerformed(evt);
            }
        });

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSPLayout = new javax.swing.GroupLayout(TimKiemSP);
        TimKiemSP.setLayout(TimKiemSPLayout);
        TimKiemSPLayout.setHorizontalGroup(
            TimKiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator18)
                    .addComponent(TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(LoadSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemSPLayout.setVerticalGroup(
            TimKiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSPLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(TimKiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiem)
                    .addComponent(LoadSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TableSanPham.setAutoCreateRowSorter(true);
        TableSanPham.setBackground(new java.awt.Color(255, 255, 255));
        TableSanPham.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Phiếu Nhập", "Mã Nhà Cung Cấp", "Người Tạo", "Thời Gian Tạo", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSanPham.setGridColor(new java.awt.Color(204, 204, 204));
        TableSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TableSanPhamMouseMoved(evt);
            }
        });
        TableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableSanPham);

        MaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        MaSanPham.setBorder(null);

        LoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        LoaiSanPham.setBorder(null);

        DonGia.setBackground(new java.awt.Color(255, 255, 255));
        DonGia.setBorder(null);
        DonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DonGiaActionPerformed(evt);
            }
        });

        SoLuongSP.setBackground(new java.awt.Color(255, 255, 255));
        SoLuongSP.setBorder(null);

        jLabel2.setText("Mã Sản Phẩm");

        jLabel4.setText("Đơn Giá");

        jLabel6.setText("Loại Sản Phẩm");

        jLabel8.setText("Số Lượng Sản Phẩm");

        XuatXu.setBackground(new java.awt.Color(255, 255, 255));
        XuatXu.setBorder(null);

        TenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        TenSanPham.setBorder(null);
        TenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenSanPhamActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên Sản Phẩm");

        jLabel5.setText("Xuất Xứ");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout SanPhamGUI4Layout = new javax.swing.GroupLayout(SanPhamGUI4);
        SanPhamGUI4.setLayout(SanPhamGUI4Layout);
        SanPhamGUI4Layout.setHorizontalGroup(
            SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                        .addComponent(ChucNangSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(TimKiemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(TenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel2)
                            .addComponent(jSeparator5))
                        .addGap(70, 70, 70)
                        .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(XuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jSeparator2)
                            .addComponent(DonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jSeparator6))
                        .addGap(70, 70, 70)
                        .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SoLuongSP)
                            .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 41, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        SanPhamGUI4Layout.setVerticalGroup(
            SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChucNangSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimKiemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamGUI4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SanPhamGUI4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(DonGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(MaSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LoaiSanPham)
                    .addComponent(SoLuongSP))
                .addGap(6, 6, 6)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SanPhamGUI4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabSanPhamLayout = new javax.swing.GroupLayout(TabSanPham);
        TabSanPham.setLayout(TabSanPhamLayout);
        TabSanPhamLayout.setHorizontalGroup(
            TabSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(SanPhamGUI4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabSanPhamLayout.setVerticalGroup(
            TabSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SanPhamGUI4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab1", TabSanPham);

        NCC_GUI.setBackground(new java.awt.Color(255, 255, 255));
        NCC_GUI.setToolTipText("");
        NCC_GUI.setName(""); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setToolTipText("");
        jPanel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        Them_NCC.setBackground(new java.awt.Color(255, 255, 255));
        Them_NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732076940676.png"))); // NOI18N
        Them_NCC.setBorder(null);
        Them_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Them_NCCMouseClicked(evt);
            }
        });
        Them_NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Them_NCCActionPerformed(evt);
            }
        });

        Sua_NCC.setBackground(new java.awt.Color(255, 255, 255));
        Sua_NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        Sua_NCC.setBorder(null);
        Sua_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sua_NCCMouseClicked(evt);
            }
        });
        Sua_NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sua_NCCActionPerformed(evt);
            }
        });

        Xoa_NCC.setBackground(new java.awt.Color(255, 255, 255));
        Xoa_NCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        Xoa_NCC.setBorder(null);
        Xoa_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Xoa_NCCMouseClicked(evt);
            }
        });

        jLabel31.setText("Thêm");

        jLabel32.setText("Sửa");

        jLabel33.setText("Xóa");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(Them_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel31)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sua_NCC, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Xoa_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(36, 36, 36))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Them_NCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(Xoa_NCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sua_NCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)))
        );

        Table_NCC.setBackground(new java.awt.Color(255, 255, 255));
        Table_NCC.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Table_NCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "Địa Chỉ"
            }
        ));
        Table_NCC.setGridColor(new java.awt.Color(204, 204, 204));
        Table_NCC.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Table_NCCMouseMoved(evt);
            }
        });
        Table_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_NCCMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Table_NCC);

        MNCC_NCC.setBackground(new java.awt.Color(255, 255, 255));
        MNCC_NCC.setBorder(null);

        SDT_NCC.setBackground(new java.awt.Color(255, 255, 255));
        SDT_NCC.setBorder(null);

        TNCC_NCC.setBackground(new java.awt.Color(255, 255, 255));
        TNCC_NCC.setBorder(null);
        TNCC_NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNCC_NCCActionPerformed(evt);
            }
        });

        DiaChi_NCC.setBackground(new java.awt.Color(255, 255, 255));
        DiaChi_NCC.setBorder(null);

        jLabel14.setText("Mã Nhà Cung Cấp");

        jLabel16.setText("Tên Nhà Cung Cấp");

        jLabel17.setText("Số Điện Thoại");

        jLabel18.setText("Địa Chỉ");

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        TimKiemSP3.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TimKiemNCC.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemNCC.setBorder(null);
        TimKiemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemNCCActionPerformed(evt);
            }
        });
        TimKiemNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemNCCKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemNCCKeyTyped(evt);
            }
        });

        LoadNCC.setBackground(new java.awt.Color(255, 255, 255));
        LoadNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        LoadNCC.setText(" Làm mới");
        LoadNCC.setBorder(null);
        LoadNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadNCCMouseClicked(evt);
            }
        });
        LoadNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadNCCActionPerformed(evt);
            }
        });

        jSeparator23.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP3Layout = new javax.swing.GroupLayout(TimKiemSP3);
        TimKiemSP3.setLayout(TimKiemSP3Layout);
        TimKiemSP3Layout.setHorizontalGroup(
            TimKiemSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator23)
                    .addComponent(TimKiemNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(LoadNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemSP3Layout.setVerticalGroup(
            TimKiemSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(TimKiemSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemNCC)
                    .addComponent(LoadNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel41.setText("Email");

        Email_NCC.setBackground(new java.awt.Color(255, 255, 255));
        Email_NCC.setBorder(null);

        jSeparator21.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator21.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout NCC_GUILayout = new javax.swing.GroupLayout(NCC_GUI);
        NCC_GUI.setLayout(NCC_GUILayout);
        NCC_GUILayout.setHorizontalGroup(
            NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NCC_GUILayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(TimKiemSP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
            .addGroup(NCC_GUILayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(MNCC_NCC, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jSeparator8))
                    .addComponent(jLabel14))
                .addGap(70, 70, 70)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TNCC_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SDT_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NCC_GUILayout.createSequentialGroup()
                        .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addComponent(DiaChi_NCC)))
            .addGroup(NCC_GUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(Email_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(662, Short.MAX_VALUE))
        );
        NCC_GUILayout.setVerticalGroup(
            NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NCC_GUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimKiemSP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14)
                    .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)))
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NCC_GUILayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TNCC_NCC, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                            .addComponent(MNCC_NCC)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NCC_GUILayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DiaChi_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SDT_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NCC_GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator11))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Email_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabNCCLayout = new javax.swing.GroupLayout(TabNCC);
        TabNCC.setLayout(TabNCCLayout);
        TabNCCLayout.setHorizontalGroup(
            TabNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabNCCLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(NCC_GUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabNCCLayout.setVerticalGroup(
            TabNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NCC_GUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab2", TabNCC);

        SanPhamGUI5.setBackground(new java.awt.Color(255, 255, 255));
        SanPhamGUI5.setToolTipText("");
        SanPhamGUI5.setName(""); // NOI18N

        TableNhapHang1.setBackground(new java.awt.Color(255, 255, 255));
        TableNhapHang1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TableNhapHang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá"
            }
        ));
        TableNhapHang1.setGridColor(new java.awt.Color(204, 204, 204));
        TableNhapHang1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TableNhapHang1MouseMoved(evt);
            }
        });
        TableNhapHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableNhapHang1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableNhapHang1);
        if (TableNhapHang1.getColumnModel().getColumnCount() > 0) {
            TableNhapHang1.getColumnModel().getColumn(0).setMinWidth(50);
            TableNhapHang1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel28.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Mã Phiếu Nhập");

        TimKiemSP4.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TimKiemNH.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemNH.setBorder(null);
        TimKiemNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemNHActionPerformed(evt);
            }
        });
        TimKiemNH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemNHKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemNHKeyTyped(evt);
            }
        });

        Load5.setBackground(new java.awt.Color(255, 255, 255));
        Load5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        Load5.setText(" Làm mới");
        Load5.setBorder(null);
        Load5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load5MouseClicked(evt);
            }
        });
        Load5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Load5ActionPerformed(evt);
            }
        });

        jSeparator24.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator24.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP4Layout = new javax.swing.GroupLayout(TimKiemSP4);
        TimKiemSP4.setLayout(TimKiemSP4Layout);
        TimKiemSP4Layout.setHorizontalGroup(
            TimKiemSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TimKiemNH, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jSeparator24))
                .addGap(27, 27, 27)
                .addComponent(Load5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(61, 61, 61))
        );
        TimKiemSP4Layout.setVerticalGroup(
            TimKiemSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(TimKiemSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimKiemNH)
                    .addComponent(Load5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SoLuongtxt.setBackground(new java.awt.Color(255, 255, 255));
        SoLuongtxt.setForeground(new java.awt.Color(0, 0, 0));
        SoLuongtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SoLuongtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        SoLuongtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoLuongtxtActionPerformed(evt);
            }
        });

        ThemHangVaoTabbleNH.setBackground(new java.awt.Color(89, 168, 104));
        ThemHangVaoTabbleNH.setText("Thêm");
        ThemHangVaoTabbleNH.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ThemHangVaoTabbleNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemHangVaoTabbleNHMouseClicked(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Số Lượng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(SoLuongtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ThemHangVaoTabbleNH, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(402, 402, 402))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ThemHangVaoTabbleNH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SoLuongtxt))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Mã Nhà Cung Cấp");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Người Nhập");

        MaNCCtxt.setBackground(new java.awt.Color(255, 255, 255));
        MaNCCtxt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        MaNCCtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaNCCtxtActionPerformed(evt);
            }
        });

        TableNhapHang2.setBackground(new java.awt.Color(255, 255, 255));
        TableNhapHang2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TableNhapHang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá"
            }
        ));
        TableNhapHang2.setGridColor(new java.awt.Color(204, 204, 204));
        TableNhapHang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableNhapHang2MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TableNhapHang2);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        XoaSPNH.setBackground(new java.awt.Color(255, 255, 255));
        XoaSPNH.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        XoaSPNH.setForeground(new java.awt.Color(0, 0, 0));
        XoaSPNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trash16x16.png"))); // NOI18N
        XoaSPNH.setText("Xóa Sản Phẩm");
        XoaSPNH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        XoaSPNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaSPNHMouseClicked(evt);
            }
        });

        NHButton.setBackground(new java.awt.Color(89, 168, 104));
        NHButton.setForeground(new java.awt.Color(255, 255, 255));
        NHButton.setText("Nhập Hàng");
        NHButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        NHButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NHButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(NHButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(XoaSPNH, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(NHButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(XoaSPNH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("PN...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        NguoiNhaptxt.setBackground(new java.awt.Color(204, 204, 204));
        NguoiNhaptxt.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        NguoiNhaptxt.setForeground(new java.awt.Color(0, 0, 0));
        NguoiNhaptxt.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NguoiNhaptxt, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NguoiNhaptxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        SuaSLNH.setBackground(new java.awt.Color(255, 255, 255));
        SuaSLNH.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        SuaSLNH.setForeground(new java.awt.Color(0, 0, 0));
        SuaSLNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pencil16x16.png"))); // NOI18N
        SuaSLNH.setText("Sửa Số Lượng");
        SuaSLNH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        SuaSLNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaSLNHMouseClicked(evt);
            }
        });
        SuaSLNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaSLNHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SanPhamGUI5Layout = new javax.swing.GroupLayout(SanPhamGUI5);
        SanPhamGUI5.setLayout(SanPhamGUI5Layout);
        SanPhamGUI5Layout.setHorizontalGroup(
            SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(TimKiemSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                        .addComponent(SuaSLNH, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                                .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addGap(5, 5, 5)))
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MaNCCtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                        .addGap(30, 30, 30))
                    .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SanPhamGUI5Layout.setVerticalGroup(
            SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MaNCCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SanPhamGUI5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SuaSLNH, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                    .addGroup(SanPhamGUI5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(TimKiemSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout TabNhapHangLayout = new javax.swing.GroupLayout(TabNhapHang);
        TabNhapHang.setLayout(TabNhapHangLayout);
        TabNhapHangLayout.setHorizontalGroup(
            TabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabNhapHangLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(SanPhamGUI5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabNhapHangLayout.setVerticalGroup(
            TabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SanPhamGUI5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab3", TabNhapHang);

        TabXuatHang.setBackground(new java.awt.Color(255, 255, 255));

        TabXuatHang1.setBackground(new java.awt.Color(255, 255, 255));

        TableXuatHang.setBackground(new java.awt.Color(255, 255, 255));
        TableXuatHang.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TableXuatHang.setForeground(new java.awt.Color(0, 0, 0));
        TableXuatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá"
            }
        ));
        TableXuatHang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TableXuatHangMouseMoved(evt);
            }
        });
        TableXuatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableXuatHangMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(TableXuatHang);

        jLabel42.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Mã Phiếu Nhập");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Mã Nhà Cung Cấp");

        MaNCCtxt1.setBackground(new java.awt.Color(255, 255, 255));
        MaNCCtxt1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        MaNCCtxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaNCCtxt1ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Người Nhập");

        NguoiNhaptxt1.setBackground(new java.awt.Color(204, 204, 204));
        NguoiNhaptxt1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        NguoiNhaptxt1.setBorder(null);

        MaPhieuXuattxt.setBackground(new java.awt.Color(204, 204, 204));
        MaPhieuXuattxt.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        MaPhieuXuattxt.setText("PX..");
        MaPhieuXuattxt.setBorder(null);
        MaPhieuXuattxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaPhieuXuattxtActionPerformed(evt);
            }
        });

        TableXuatHang1.setBackground(new java.awt.Color(255, 255, 255));
        TableXuatHang1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TableXuatHang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableXuatHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableXuatHang1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(TableXuatHang1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        XSP.setBackground(new java.awt.Color(255, 255, 255));
        XSP.setForeground(new java.awt.Color(0, 0, 0));
        XSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trash16x16.png"))); // NOI18N
        XSP.setText("Xóa Sản Phẩm");
        XSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        XSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XSPMouseClicked(evt);
            }
        });
        XSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XSPActionPerformed(evt);
            }
        });

        XH.setBackground(new java.awt.Color(89, 168, 104));
        XH.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        XH.setText("Xuất Hàng");
        XH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        XH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XHMouseClicked(evt);
            }
        });

        SSLXH.setBackground(new java.awt.Color(255, 255, 255));
        SSLXH.setForeground(new java.awt.Color(0, 0, 0));
        SSLXH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pencil16x16.png"))); // NOI18N
        SSLXH.setText("Sửa Số Lượng");
        SSLXH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        SSLXH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SSLXHMouseClicked(evt);
            }
        });

        TimKiemSP5.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TimKiemNH1.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemNH1.setBorder(null);
        TimKiemNH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemNH1ActionPerformed(evt);
            }
        });
        TimKiemNH1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemNH1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemNH1KeyTyped(evt);
            }
        });

        Load6.setBackground(new java.awt.Color(255, 255, 255));
        Load6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        Load6.setText(" Làm mới");
        Load6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Load6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load6MouseClicked(evt);
            }
        });
        Load6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Load6ActionPerformed(evt);
            }
        });

        jSeparator26.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP5Layout = new javax.swing.GroupLayout(TimKiemSP5);
        TimKiemSP5.setLayout(TimKiemSP5Layout);
        TimKiemSP5Layout.setHorizontalGroup(
            TimKiemSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TimKiemNH1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jSeparator26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Load6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TimKiemSP5Layout.setVerticalGroup(
            TimKiemSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(TimKiemSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimKiemNH1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Load6))
                .addGap(2, 2, 2)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout TabXuatHang1Layout = new javax.swing.GroupLayout(TabXuatHang1);
        TabXuatHang1.setLayout(TabXuatHang1Layout);
        TabXuatHang1Layout.setHorizontalGroup(
            TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TabXuatHang1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TimKiemSP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TabXuatHang1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(SSLXH, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(XH, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(XSP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TabXuatHang1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabXuatHang1Layout.createSequentialGroup()
                                .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MaNCCtxt1)
                                    .addComponent(NguoiNhaptxt1)
                                    .addComponent(MaPhieuXuattxt)))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        TabXuatHang1Layout.setVerticalGroup(
            TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabXuatHang1Layout.createSequentialGroup()
                .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TabXuatHang1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(TimKiemSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(TabXuatHang1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(MaPhieuXuattxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaNCCtxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NguoiNhaptxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabXuatHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(XH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SSLXH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(XSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel27.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Số Lượng:");

        ThemXuathangTable.setBackground(new java.awt.Color(89, 168, 104));
        ThemXuathangTable.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ThemXuathangTable.setText("Thêm");
        ThemXuathangTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ThemXuathangTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemXuathangTableMouseClicked(evt);
            }
        });

        SLtxt.setBackground(new java.awt.Color(255, 255, 255));
        SLtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout TabXuatHangLayout = new javax.swing.GroupLayout(TabXuatHang);
        TabXuatHang.setLayout(TabXuatHangLayout);
        TabXuatHangLayout.setHorizontalGroup(
            TabXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabXuatHangLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SLtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(ThemXuathangTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(TabXuatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabXuatHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabXuatHangLayout.setVerticalGroup(
            TabXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabXuatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabXuatHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TabXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SLtxt)
                    .addComponent(ThemXuathangTable, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TabTong.addTab("tab5", TabXuatHang);

        NhapHangGUI1.setBackground(new java.awt.Color(255, 255, 255));
        NhapHangGUI1.setToolTipText("");
        NhapHangGUI1.setName(""); // NOI18N

        ChucNangSP3.setBackground(new java.awt.Color(255, 255, 255));
        ChucNangSP3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        ChucNangSP3.setToolTipText("");
        ChucNangSP3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        ThemPN1.setBackground(new java.awt.Color(255, 255, 255));
        ThemPN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1733302522470.png"))); // NOI18N
        ThemPN1.setBorder(null);
        ThemPN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemPN1MouseClicked(evt);
            }
        });
        ThemPN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemPN1ActionPerformed(evt);
            }
        });

        SuaPN1.setBackground(new java.awt.Color(255, 255, 255));
        SuaPN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        SuaPN1.setBorder(null);
        SuaPN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaPN1MouseClicked(evt);
            }
        });

        XoaPN1.setBackground(new java.awt.Color(255, 255, 255));
        XoaPN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        XoaPN1.setBorder(null);
        XoaPN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaPN1MouseClicked(evt);
            }
        });

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Xem Chi Tiết");

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Xem Thống Kê");

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Xóa");

        javax.swing.GroupLayout ChucNangSP3Layout = new javax.swing.GroupLayout(ChucNangSP3);
        ChucNangSP3.setLayout(ChucNangSP3Layout);
        ChucNangSP3Layout.setHorizontalGroup(
            ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSP3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThemPN1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SuaPN1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGroup(ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChucNangSP3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XoaPN1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ChucNangSP3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel47)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ChucNangSP3Layout.setVerticalGroup(
            ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSP3Layout.createSequentialGroup()
                .addGroup(ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThemPN1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(XoaPN1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuaPN1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ChucNangSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)))
        );

        TablePhieuXuat.setBackground(new java.awt.Color(255, 255, 255));
        TablePhieuXuat.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TablePhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Xuất", "Mã Nhà Cung Cấp", "Người Tạo", "Tổng Tiền"
            }
        ));
        TablePhieuXuat.setGridColor(new java.awt.Color(204, 204, 204));
        TablePhieuXuat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TablePhieuXuatMouseMoved(evt);
            }
        });
        TablePhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(TablePhieuXuat);

        TimKiemSP2.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TextTimKiemPN1.setBackground(new java.awt.Color(255, 255, 255));
        TextTimKiemPN1.setBorder(null);
        TextTimKiemPN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextTimKiemPN1ActionPerformed(evt);
            }
        });
        TextTimKiemPN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextTimKiemPN1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextTimKiemPN1KeyTyped(evt);
            }
        });

        Load2.setBackground(new java.awt.Color(255, 255, 255));
        Load2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        Load2.setText(" Làm mới");
        Load2.setBorder(null);
        Load2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load2MouseClicked(evt);
            }
        });
        Load2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Load2ActionPerformed(evt);
            }
        });

        jSeparator28.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP2Layout = new javax.swing.GroupLayout(TimKiemSP2);
        TimKiemSP2.setLayout(TimKiemSP2Layout);
        TimKiemSP2Layout.setHorizontalGroup(
            TimKiemSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator28)
                    .addComponent(TextTimKiemPN1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(Load2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemSP2Layout.setVerticalGroup(
            TimKiemSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(TimKiemSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextTimKiemPN1)
                    .addComponent(Load2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout NhapHangGUI1Layout = new javax.swing.GroupLayout(NhapHangGUI1);
        NhapHangGUI1.setLayout(NhapHangGUI1Layout);
        NhapHangGUI1Layout.setHorizontalGroup(
            NhapHangGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHangGUI1Layout.createSequentialGroup()
                .addComponent(ChucNangSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(TimKiemSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane11)
        );
        NhapHangGUI1Layout.setVerticalGroup(
            NhapHangGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHangGUI1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhapHangGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChucNangSP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimKiemSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabPhieuXuatLayout = new javax.swing.GroupLayout(TabPhieuXuat);
        TabPhieuXuat.setLayout(TabPhieuXuatLayout);
        TabPhieuXuatLayout.setHorizontalGroup(
            TabPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabPhieuXuatLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(NhapHangGUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabPhieuXuatLayout.setVerticalGroup(
            TabPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NhapHangGUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab6", TabPhieuXuat);

        NCC_GUI1.setBackground(new java.awt.Color(255, 255, 255));
        NCC_GUI1.setToolTipText("");
        NCC_GUI1.setName(""); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setToolTipText("");
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(256, 84));

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Thêm");

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Sửa");

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Xóa");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732076940676.png"))); // NOI18N
        jLabel12.setEnabled(false);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        jLabel13.setEnabled(false);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        jLabel25.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel55)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(37, 37, 37))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)))
        );

        TabbleTonKho.setBackground(new java.awt.Color(255, 255, 255));
        TabbleTonKho.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TabbleTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "Địa Chỉ"
            }
        ));
        TabbleTonKho.setGridColor(new java.awt.Color(204, 204, 204));
        TabbleTonKho.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TabbleTonKhoMouseMoved(evt);
            }
        });
        TabbleTonKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabbleTonKhoMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(TabbleTonKho);

        TimKiemSP6.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TimKiemTonKho.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemTonKho.setBorder(null);
        TimKiemTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemTonKhoActionPerformed(evt);
            }
        });
        TimKiemTonKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemTonKhoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemTonKhoKeyTyped(evt);
            }
        });

        LoadNCC1.setBackground(new java.awt.Color(255, 255, 255));
        LoadNCC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        LoadNCC1.setText(" Làm mới");
        LoadNCC1.setBorder(null);
        LoadNCC1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadNCC1MouseClicked(evt);
            }
        });
        LoadNCC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadNCC1ActionPerformed(evt);
            }
        });

        jSeparator29.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP6Layout = new javax.swing.GroupLayout(TimKiemSP6);
        TimKiemSP6.setLayout(TimKiemSP6Layout);
        TimKiemSP6Layout.setHorizontalGroup(
            TimKiemSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator29)
                    .addComponent(TimKiemTonKho, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(LoadNCC1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemSP6Layout.setVerticalGroup(
            TimKiemSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(TimKiemSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemTonKho)
                    .addComponent(LoadNCC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout NCC_GUI1Layout = new javax.swing.GroupLayout(NCC_GUI1);
        NCC_GUI1.setLayout(NCC_GUI1Layout);
        NCC_GUI1Layout.setHorizontalGroup(
            NCC_GUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NCC_GUI1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(TimKiemSP6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane12)
        );
        NCC_GUI1Layout.setVerticalGroup(
            NCC_GUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NCC_GUI1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NCC_GUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(TimKiemSP6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout TabTonKhoLayout = new javax.swing.GroupLayout(TabTonKho);
        TabTonKho.setLayout(TabTonKhoLayout);
        TabTonKhoLayout.setHorizontalGroup(
            TabTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabTonKhoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(NCC_GUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabTonKhoLayout.setVerticalGroup(
            TabTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NCC_GUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab7", TabTonKho);

        TabThongKe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SpNext.setBackground(new java.awt.Color(255, 204, 0));
        SpNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SpNextMouseClicked(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 204, 0));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TỔNG SẢN PHẨM");

        HienThiSLSP.setBackground(new java.awt.Color(255, 204, 0));
        HienThiSLSP.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        HienThiSLSP.setForeground(new java.awt.Color(255, 255, 255));
        HienThiSLSP.setText("-");

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/PC.png"))); // NOI18N

        javax.swing.GroupLayout SpNextLayout = new javax.swing.GroupLayout(SpNext);
        SpNext.setLayout(SpNextLayout);
        SpNextLayout.setHorizontalGroup(
            SpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SpNextLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addGroup(SpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HienThiSLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        SpNextLayout.setVerticalGroup(
            SpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SpNextLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(HienThiSLSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        TabThongKe.add(SpNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 99));

        NCCnext.setBackground(new java.awt.Color(0, 204, 203));
        NCCnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NCCnextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NCCnextMouseEntered(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(0, 204, 203));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("NHÀ CUNG CẤP");

        HienThiSLNCC.setBackground(new java.awt.Color(0, 204, 203));
        HienThiSLNCC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        HienThiSLNCC.setForeground(new java.awt.Color(255, 255, 255));
        HienThiSLNCC.setText("-");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/human.png"))); // NOI18N

        javax.swing.GroupLayout NCCnextLayout = new javax.swing.GroupLayout(NCCnext);
        NCCnext.setLayout(NCCnextLayout);
        NCCnextLayout.setHorizontalGroup(
            NCCnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NCCnextLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addGroup(NCCnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HienThiSLNCC)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        NCCnextLayout.setVerticalGroup(
            NCCnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NCCnextLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(HienThiSLNCC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(24, 24, 24))
        );

        TabThongKe.add(NCCnext, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 200, 99));

        TKnext.setBackground(new java.awt.Color(254, 102, 0));
        TKnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKnextMouseClicked(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(254, 102, 0));
        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("TÀI KHOẢN");

        HienThiSLTK.setBackground(new java.awt.Color(254, 102, 0));
        HienThiSLTK.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        HienThiSLTK.setForeground(new java.awt.Color(255, 255, 255));
        HienThiSLTK.setText("-");

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/staff.png"))); // NOI18N

        javax.swing.GroupLayout TKnextLayout = new javax.swing.GroupLayout(TKnext);
        TKnext.setLayout(TKnextLayout);
        TKnextLayout.setHorizontalGroup(
            TKnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TKnextLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(TKnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HienThiSLTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        TKnextLayout.setVerticalGroup(
            TKnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TKnextLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(HienThiSLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        TabThongKe.add(TKnext, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 220, 99));

        TabbedTK.setBackground(new java.awt.Color(255, 255, 255));
        TabbedTK.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        TableCTSP.setBackground(new java.awt.Color(255, 255, 255));
        TableCTSP.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        TableThongKe.setBackground(new java.awt.Color(255, 255, 255));
        TableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableThongKe.setFocusCycleRoot(true);
        TableThongKe.setFocusTraversalPolicyProvider(true);
        TableThongKe.setGridColor(new java.awt.Color(204, 204, 204));
        TableThongKe.setOpaque(false);
        TableThongKe.setShowGrid(true);
        jScrollPane1.setViewportView(TableThongKe);

        javax.swing.GroupLayout TableCTSPLayout = new javax.swing.GroupLayout(TableCTSP);
        TableCTSP.setLayout(TableCTSPLayout);
        TableCTSPLayout.setHorizontalGroup(
            TableCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
        );
        TableCTSPLayout.setVerticalGroup(
            TableCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        TabbedTK.addTab("tab1", TableCTSP);

        TableCTNCC.setBackground(new java.awt.Color(255, 255, 255));

        PNPX.setBackground(new java.awt.Color(255, 255, 255));
        PNPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PNPX.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane6.setViewportView(PNPX);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("TỔNG TIỀN:");
        jLabel22.setToolTipText("");

        TongTienPNPX.setBackground(new java.awt.Color(255, 255, 255));
        TongTienPNPX.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TongTienPNPX.setForeground(new java.awt.Color(255, 51, 51));
        TongTienPNPX.setText("-");

        TextTPNPX.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        TextTPNPX.setForeground(new java.awt.Color(0, 0, 0));
        TextTPNPX.setText("Tổng Số Phiếu:");

        HienThiPhieu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        HienThiPhieu.setForeground(new java.awt.Color(0, 0, 0));
        HienThiPhieu.setText("-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextTPNPX)
                .addGap(18, 18, 18)
                .addComponent(HienThiPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TongTienPNPX, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TongTienPNPX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(TextTPNPX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(HienThiPhieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        XemChiTietPNPX.setBackground(new java.awt.Color(255, 255, 255));
        XemChiTietPNPX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1733302522470.png"))); // NOI18N
        XemChiTietPNPX.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        XemChiTietPNPX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XemChiTietPNPXMouseClicked(evt);
            }
        });
        XemChiTietPNPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XemChiTietPNPXActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Xem Chi Tiết");

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        jLabel52.setEnabled(false);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        jLabel53.setEnabled(false);

        jLabel60.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Sửa");
        jLabel60.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel65.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Xóa");
        jLabel65.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(XemChiTietPNPX, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(XemChiTietPNPX, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        jButton2.setText("Làm Mới");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TimKiemPNTK.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemPNTK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TimKiemPNTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemPNTKActionPerformed(evt);
            }
        });

        SelectPNPXBox.setBackground(new java.awt.Color(255, 255, 255));
        SelectPNPXBox.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        SelectPNPXBox.setForeground(new java.awt.Color(0, 0, 0));
        SelectPNPXBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Phiếu Nhập", "Phiếu Xuất" }));
        SelectPNPXBox.setAutoscrolls(true);
        SelectPNPXBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        SelectPNPXBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectPNPXBoxMouseEntered(evt);
            }
        });
        SelectPNPXBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectPNPXBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SelectPNPXBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TimKiemPNTK, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelectPNPXBox)
                    .addComponent(TimKiemPNTK)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel13.setForeground(new java.awt.Color(0, 0, 0));

        ngayBatDau.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDau.setForeground(new java.awt.Color(0, 0, 0));
        ngayBatDau.setText("yyyy-mm-dd");
        ngayBatDau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ngayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ngayBatDauFocusGained(evt);
            }
        });
        ngayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayBatDauActionPerformed(evt);
            }
        });

        NgayKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        NgayKetThuc.setForeground(new java.awt.Color(0, 0, 0));
        NgayKetThuc.setText("yyyy-mm-dd");
        NgayKetThuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        NgayKetThuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NgayKetThucFocusGained(evt);
            }
        });
        NgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NgayKetThucActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Từ");

        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Đến");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator7)
                    .addComponent(ngayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator12)
                    .addComponent(NgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngayBatDau, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc theo ...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel14.setForeground(new java.awt.Color(0, 0, 0));

        jLabel64.setBackground(new java.awt.Color(204, 204, 204));
        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(204, 204, 204));
        jLabel64.setText("Đến");

        jLabel63.setBackground(new java.awt.Color(204, 204, 204));
        jLabel63.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(204, 204, 204));
        jLabel63.setText("Từ");

        GiaKetThuc.setBackground(new java.awt.Color(204, 204, 204));
        GiaKetThuc.setForeground(new java.awt.Color(0, 0, 0));
        GiaKetThuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        GiaKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GiaKetThucActionPerformed(evt);
            }
        });

        GiaBatDau.setBackground(new java.awt.Color(204, 204, 204));
        GiaBatDau.setForeground(new java.awt.Color(0, 0, 0));
        GiaBatDau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        GiaBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GiaBatDauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(GiaBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GiaKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GiaKetThuc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(GiaBatDau))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout TableCTNCCLayout = new javax.swing.GroupLayout(TableCTNCC);
        TableCTNCC.setLayout(TableCTNCCLayout);
        TableCTNCCLayout.setHorizontalGroup(
            TableCTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TableCTNCCLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6)
            .addGroup(TableCTNCCLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        TableCTNCCLayout.setVerticalGroup(
            TableCTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TableCTNCCLayout.createSequentialGroup()
                .addGroup(TableCTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(TableCTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        TabbedTK.addTab("tab2", TableCTNCC);

        TableTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        TableCTTK.setBackground(new java.awt.Color(255, 255, 255));
        TableCTTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableCTTK.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane7.setViewportView(TableCTTK);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel66.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Xem Chi Tiết");

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        jLabel58.setEnabled(false);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        jLabel67.setEnabled(false);

        jLabel68.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Sửa");
        jLabel68.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Xóa");
        jLabel69.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1733302522470.png"))); // NOI18N
        jLabel70.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel66)
                    .addComponent(jLabel68)))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        jButton4.setText("Làm Mới");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        TimKiemPNTK1.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemPNTK1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TimKiemPNTK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemPNTK1ActionPerformed(evt);
            }
        });

        SelectPNPXBox1.setBackground(new java.awt.Color(255, 255, 255));
        SelectPNPXBox1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        SelectPNPXBox1.setForeground(new java.awt.Color(0, 0, 0));
        SelectPNPXBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        SelectPNPXBox1.setAutoscrolls(true);
        SelectPNPXBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        SelectPNPXBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectPNPXBox1MouseEntered(evt);
            }
        });
        SelectPNPXBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectPNPXBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SelectPNPXBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TimKiemPNTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelectPNPXBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(TimKiemPNTK1)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout TableTaiKhoanLayout = new javax.swing.GroupLayout(TableTaiKhoan);
        TableTaiKhoan.setLayout(TableTaiKhoanLayout);
        TableTaiKhoanLayout.setHorizontalGroup(
            TableTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
            .addGroup(TableTaiKhoanLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TableTaiKhoanLayout.setVerticalGroup(
            TableTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TableTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(TableTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TabbedTK.addTab("tab3", TableTaiKhoan);

        javax.swing.GroupLayout PanelNCCLayout = new javax.swing.GroupLayout(PanelNCC);
        PanelNCC.setLayout(PanelNCCLayout);
        PanelNCCLayout.setHorizontalGroup(
            PanelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        PanelNCCLayout.setVerticalGroup(
            PanelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );

        TabbedTK.addTab("tab4", PanelNCC);

        TabThongKe.add(TabbedTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 100, 930, 430));

        PNPXnext.setBackground(new java.awt.Color(204, 204, 255));
        PNPXnext.setForeground(new java.awt.Color(102, 255, 102));
        PNPXnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PNPXnextMouseClicked(evt);
            }
        });

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/3298601_document_new_new document_plus_icon.png"))); // NOI18N

        jLabel54.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("TỔNG QUAN PHIẾU");

        HienThiTongSoPhieu.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        HienThiTongSoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        HienThiTongSoPhieu.setText("-");

        javax.swing.GroupLayout PNPXnextLayout = new javax.swing.GroupLayout(PNPXnext);
        PNPXnext.setLayout(PNPXnextLayout);
        PNPXnextLayout.setHorizontalGroup(
            PNPXnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNPXnextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addGroup(PNPXnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(HienThiTongSoPhieu))
                .addContainerGap())
        );
        PNPXnextLayout.setVerticalGroup(
            PNPXnextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNPXnextLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(HienThiTongSoPhieu)
                .addGap(6, 6, 6)
                .addComponent(jLabel54)
                .addGap(23, 23, 23))
        );

        TabThongKe.add(PNPXnext, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 200, 99));

        TabTong.addTab("tab8", TabThongKe);

        NhapHangGUI.setBackground(new java.awt.Color(255, 255, 255));
        NhapHangGUI.setToolTipText("");
        NhapHangGUI.setName(""); // NOI18N

        ChucNangSP2.setBackground(new java.awt.Color(255, 255, 255));
        ChucNangSP2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        ChucNangSP2.setToolTipText("");
        ChucNangSP2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        ChucNangSP2.setPreferredSize(new java.awt.Dimension(263, 72));

        ThemPN.setBackground(new java.awt.Color(255, 255, 255));
        ThemPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1733302522470.png"))); // NOI18N
        ThemPN.setBorder(null);
        ThemPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThemPNMouseClicked(evt);
            }
        });
        ThemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemPNActionPerformed(evt);
            }
        });

        SuaPN.setBackground(new java.awt.Color(255, 255, 255));
        SuaPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/imresizer-1732077019905.png"))); // NOI18N
        SuaPN.setBorder(null);
        SuaPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuaPNMouseClicked(evt);
            }
        });

        XoaPN.setBackground(new java.awt.Color(255, 255, 255));
        XoaPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/trashcan32x32.png"))); // NOI18N
        XoaPN.setBorder(null);
        XoaPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaPNMouseClicked(evt);
            }
        });

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Xem Chi Tiết");

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Xem Thống Kê");

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Xóa");

        javax.swing.GroupLayout ChucNangSP2Layout = new javax.swing.GroupLayout(ChucNangSP2);
        ChucNangSP2.setLayout(ChucNangSP2Layout);
        ChucNangSP2Layout.setHorizontalGroup(
            ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSP2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGroup(ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChucNangSP2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(SuaPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(ChucNangSP2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(XoaPN, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ChucNangSP2Layout.setVerticalGroup(
            ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangSP2Layout.createSequentialGroup()
                .addGroup(ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThemPN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(XoaPN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuaPN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ChucNangSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)))
        );

        TablePhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        TablePhieuNhap.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TablePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Nhà Cung Cấp", "Người Tạo", "Tổng Tiền"
            }
        ));
        TablePhieuNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TablePhieuNhapMouseMoved(evt);
            }
        });
        TablePhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablePhieuNhap);

        TimKiemSP1.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemSP1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TextTimKiemPN.setBackground(new java.awt.Color(255, 255, 255));
        TextTimKiemPN.setBorder(null);
        TextTimKiemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextTimKiemPNActionPerformed(evt);
            }
        });
        TextTimKiemPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextTimKiemPNKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextTimKiemPNKeyTyped(evt);
            }
        });

        Load1.setBackground(new java.awt.Color(255, 255, 255));
        Load1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/reload.png"))); // NOI18N
        Load1.setText(" Làm mới");
        Load1.setBorder(null);
        Load1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load1MouseClicked(evt);
            }
        });
        Load1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Load1ActionPerformed(evt);
            }
        });

        jSeparator25.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout TimKiemSP1Layout = new javax.swing.GroupLayout(TimKiemSP1);
        TimKiemSP1.setLayout(TimKiemSP1Layout);
        TimKiemSP1Layout.setHorizontalGroup(
            TimKiemSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimKiemSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator25)
                    .addComponent(TextTimKiemPN, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(Load1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );
        TimKiemSP1Layout.setVerticalGroup(
            TimKiemSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimKiemSP1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(TimKiemSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextTimKiemPN)
                    .addComponent(Load1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout NhapHangGUILayout = new javax.swing.GroupLayout(NhapHangGUI);
        NhapHangGUI.setLayout(NhapHangGUILayout);
        NhapHangGUILayout.setHorizontalGroup(
            NhapHangGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHangGUILayout.createSequentialGroup()
                .addComponent(ChucNangSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(TimKiemSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
        );
        NhapHangGUILayout.setVerticalGroup(
            NhapHangGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHangGUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhapHangGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChucNangSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimKiemSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabPhieuNhapLayout = new javax.swing.GroupLayout(TabPhieuNhap);
        TabPhieuNhap.setLayout(TabPhieuNhapLayout);
        TabPhieuNhapLayout.setHorizontalGroup(
            TabPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabPhieuNhapLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(NhapHangGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TabPhieuNhapLayout.setVerticalGroup(
            TabPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NhapHangGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabTong.addTab("tab4", TabPhieuNhap);

        add(TabTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -30, 880, 550));
        TabTong.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void dangxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuatMouseClicked

        JFrame frame = new JFrame("Quản Lý Kho Hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GUI_LOGIN());

        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_dangxuatMouseClicked

    private void dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangxuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dangxuatActionPerformed


    private void NCCButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NCCButtonMouseClicked
        switchToTabNCC();
    }//GEN-LAST:event_NCCButtonMouseClicked
    private void switchToTabNCC() {
        TabTong.setSelectedComponent(TabNCC);
    }

    private void switchToTabSanPham() {
        TabTong.setSelectedComponent(TabSanPham);
    }

    private void switchToTabNhapHang() {
        TabTong.setSelectedComponent(TabNhapHang);
    }

    private void switchToTabPhieuNhap() {
        TabTong.setSelectedComponent(TabPhieuNhap);
    }

    private void switchToTabXuatHang() {
        TabTong.setSelectedComponent(TabXuatHang);
    }

    private void switchToTabPhieuXuat() {
        TabTong.setSelectedComponent(TabPhieuXuat);
    }

    private void switchToTabTonKho() {
        TabTong.setSelectedComponent(TabTonKho);
    }

    private void switchToTabThongKe() {
        TabTong.setSelectedComponent(TabThongKe);
    }

    private void switchToTabThongKeSP() {
        TabbedTK.setSelectedComponent(TableCTSP);
    }

//    private void switchToTabThongKeNCC() {
//        TabbedTK.setSelectedComponent(TableCTNCC);
//    }
    private void switchToTabThongKeTK() {
        TabbedTK.setSelectedComponent(TableTaiKhoan);
    }

    private void switchToTabThongKePNPX() {
        TabbedTK.setSelectedComponent(TableCTNCC);
    }

    private void swtichToPanelThongKeNCC() {
        TabbedTK.setSelectedComponent(PanelNCC);
    }
    private void NCCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NCCButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NCCButtonActionPerformed

    private void sanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanphamActionPerformed

    }//GEN-LAST:event_sanphamActionPerformed

    private void thongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thongkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thongkeActionPerformed
    private final IQueryDataBaseSP sanPhamDAO = new SanPhamDAO();
    private final IQueryDataBaseNCC nhaCungCapDAO = new NhaCungCapDAO();
    private final IQueryDataBaseNH nhapHangDAO = new NhapHangDAO();
    private final IQueryDataBaseThongKe thongKeDAO = new ThongKeDAO();
    private void ThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemSPMouseClicked
        String maSP = MaSanPham.getText();
        String tenSP = TenSanPham.getText();
        String dongia = DonGia.getText();
        String xuatXu = XuatXu.getText();
        String loaiSanPham = LoaiSanPham.getText();
        String soLuong = SoLuongSP.getText();

        if (maSP.isEmpty() || tenSP.isEmpty() || dongia.isEmpty() || xuatXu.isEmpty()
                || loaiSanPham.isEmpty() || soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if(maSP.equals(maSP)){
//            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được trùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if(tenSP.equals(tenSP)){
//            JOptionPane.showMessageDialog(this, "Tên Sản phẩm này đã có!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        try {
            Integer soLuongValue = Integer.valueOf(soLuong);
            BigDecimal donGiaValue = new BigDecimal(dongia);
            SanPhamDTO sp = new SanPhamDTO(
                    maSP,
                    tenSP,
                    donGiaValue,
                    xuatXu,
                    loaiSanPham,
                    soLuongValue
            );
            sanPhamDAO.ThemSP(sp);
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá và cân nặng phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ThemSPMouseClicked

    private void ThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThemSPActionPerformed

    private void SuaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaSPMouseClicked
        int selectedRow = TableSanPham.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maSanPham = TableSanPham.getValueAt(selectedRow, 0).toString();
        String tenSanPham = TableSanPham.getValueAt(selectedRow, 1).toString();
        String donGiaStr = TableSanPham.getValueAt(selectedRow, 2).toString();
        String xuatXu = TableSanPham.getValueAt(selectedRow, 3).toString();
        String loaiSanPham = TableSanPham.getValueAt(selectedRow, 4).toString();
        String soLuongSP = TableSanPham.getValueAt(selectedRow, 5).toString();

        BigDecimal donGia;
        int soLuong;
        try {
            donGia = new BigDecimal(donGiaStr);
            soLuong = Integer.parseInt(soLuongSP);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Đơn giá hoặc số lượng không hợp lệ! Vui lòng kiểm tra lại.", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel editPanel = new JPanel(new GridLayout(7, 2));
        JTextField txtTenSanPham = new JTextField(tenSanPham);
        JTextField txtDonGia = new JTextField(String.valueOf(donGia));
        JTextField txtXuatXu = new JTextField(xuatXu);
        JTextField txtLoaiSanPham = new JTextField(loaiSanPham);
        JTextField txtSoLuong = new JTextField(String.valueOf(soLuong));

        editPanel.add(new JLabel("Tên sản phẩm:"));
        editPanel.add(txtTenSanPham);
        editPanel.add(new JLabel("Đơn giá:"));
        editPanel.add(txtDonGia);
        editPanel.add(new JLabel("Xuất xứ:"));
        editPanel.add(txtXuatXu);
        editPanel.add(new JLabel("Loại sản phẩm:"));
        editPanel.add(txtLoaiSanPham);
        editPanel.add(new JLabel("Số lượng:"));
        editPanel.add(txtSoLuong);

        int option = javax.swing.JOptionPane.showConfirmDialog(
                this, editPanel, "Sửa Thông Tin Sản Phẩm", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE
        );

        if (option == javax.swing.JOptionPane.OK_OPTION) {
            try {
                if (txtTenSanPham.getText().trim().isEmpty()
                        || txtXuatXu.getText().trim().isEmpty()
                        || txtLoaiSanPham.getText().trim().isEmpty()
                        || txtSoLuong.getText().trim().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Các trường thông tin không được để trống!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                BigDecimal newDonGia = new BigDecimal(txtDonGia.getText().trim());
                if (newDonGia.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Đơn giá phải là số không âm!");
                }

                int newSoLuong = Integer.parseInt(txtSoLuong.getText().trim());
                if (newSoLuong < 0) {
                    throw new IllegalArgumentException("Số lượng phải là số không âm!");
                }
                SanPhamDTO sp = new SanPhamDTO(
                        maSanPham,
                        txtTenSanPham.getText().trim(),
                        newDonGia,
                        txtXuatXu.getText().trim(),
                        txtLoaiSanPham.getText().trim(),
                        newSoLuong
                );
                sanPhamDAO.SuaSP(sp);
                loadTable();

                javax.swing.JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Đơn giá hoặc số lượng không hợp lệ!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_SuaSPMouseClicked

    private void XoaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaSPMouseClicked

        int selectedRow = TableSanPham.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maSanPham = TableSanPham.getValueAt(selectedRow, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa sản phẩm này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {

                sanPhamDAO.XoaSP(maSanPham);

                loadTable();

                javax.swing.JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_XoaSPMouseClicked

    private void TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemActionPerformed

        String chuoi = TimKiem.getText().trim();
        List<SanPhamDTO> danhSachTimKiem = sanPhamDAO.timKiemSanPham(chuoi);
        DefaultTableModel model = (DefaultTableModel) TableSanPham.getModel();
        model.setRowCount(0);
        for (SanPhamDTO sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getMa_SanPham(),
                sp.getTen_SanPham(),
                sp.getDonGia(),
                sp.getXuatXu(),
                sp.getLoai_SanPham(),
                sp.getSoLuong()});
        }

    }//GEN-LAST:event_TimKiemActionPerformed

    private void LoadSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadSPMouseClicked
        MaSanPham.setText("");
        TenSanPham.setText("");
        DonGia.setText("");
        XuatXu.setText("");
        LoaiSanPham.setText("");
        SoLuongSP.setText("");
        TimKiem.setText("");
        loadTable();
    }//GEN-LAST:event_LoadSPMouseClicked

    private void LoadSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadSPActionPerformed

    private void TableSanPhamMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSanPhamMouseMoved

    }//GEN-LAST:event_TableSanPhamMouseMoved

    private void TableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSanPhamMouseClicked
        TableSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = TableSanPham.getSelectedRow();
                if (row >= 0) {
                    HienThiThongTinKhiBamChuot(row);
                }
            }
        });
    }//GEN-LAST:event_TableSanPhamMouseClicked

    private void DonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DonGiaActionPerformed

    private void TenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenSanPhamActionPerformed

    private void sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanphamMouseClicked
        switchToTabSanPham();
        loadTable();
    }//GEN-LAST:event_sanphamMouseClicked

    private void nhaphangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphangMouseClicked
        switchToTabNhapHang();
        loadTable1NhapHang();
//        loadTableNhapHang2();
    }//GEN-LAST:event_nhaphangMouseClicked

    private void phieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phieunhapMouseClicked
        switchToTabPhieuNhap();
        loadTablePhieuNhap();
    }//GEN-LAST:event_phieunhapMouseClicked

    private void xuathangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xuathangMouseClicked
        switchToTabXuatHang();
    }//GEN-LAST:event_xuathangMouseClicked

    private void phieuxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phieuxuatMouseClicked
        switchToTabPhieuXuat();
        loadTablePhieuXuat();
    }//GEN-LAST:event_phieuxuatMouseClicked

    private void tonkhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tonkhoMouseClicked
        switchToTabTonKho();
    }//GEN-LAST:event_tonkhoMouseClicked

    private void thongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkeMouseClicked
        switchToTabThongKe();
        loadTableThongKE();
        updateHienThiSLSP();
        updateHienThiSLNCC();
        updateHienThiSLTK();
//        updateHienThiTongTien();
        updateHienThiTongSoPhieu();
        loadPNPX();
        loadPNTK();
        loadPXTK();
        loadTableThongKeTK();
    }//GEN-LAST:event_thongkeMouseClicked

    private void TNCC_NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNCC_NCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNCC_NCCActionPerformed

    private void Table_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_NCCMouseClicked
        Table_NCC.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {

                int row = Table_NCC.getSelectedRow();
                if (row >= 0) {
                    HienThiThongTinKhiBamChuotTab2(row); // Gọi phương thức hiển thị thông tin
                }
            }
        });
    }//GEN-LAST:event_Table_NCCMouseClicked

    private void Table_NCCMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_NCCMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Table_NCCMouseMoved

    private void ThemPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemPNMouseClicked
        int selectedRow = TablePhieuNhap.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maPhieuNhap = TablePhieuNhap.getValueAt(selectedRow, 0).toString();
        if (maPhieuNhap == null || maPhieuNhap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phiếu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ChiTietNhapHang chiTietForm = new ChiTietNhapHang(maPhieuNhap);
        JFrame frame = new JFrame("Quản Lý Kho Hàng");
        frame.setContentPane(new ChiTietNhapHang(maPhieuNhap));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }//GEN-LAST:event_ThemPNMouseClicked

    private void ThemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThemPNActionPerformed

    private void SuaPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaPNMouseClicked

        switchToTabThongKe();
        switchToTabThongKePNPX();
        loadPNPX();
    }//GEN-LAST:event_SuaPNMouseClicked

    private void XoaPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaPNMouseClicked
        int selectedRow = TablePhieuNhap.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maPN = TablePhieuNhap.getValueAt(selectedRow, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa phiếu nhập này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {

                nhapHangDAO.XoaNH(maPN);
                loadTablePhieuNhap();
                javax.swing.JOptionPane.showMessageDialog(this, "Xóa Phiếu nhập thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_XoaPNMouseClicked

    private void TablePhieuNhapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePhieuNhapMouseMoved

    }//GEN-LAST:event_TablePhieuNhapMouseMoved

    private void TablePhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePhieuNhapMouseClicked
        int selectedRow = TablePhieuNhap.getSelectedRow();
        if (selectedRow != -1) {
            String maPhieu = TablePhieuNhap.getValueAt(selectedRow, 0).toString();
//            String ncc = TablePhieuNhap.getValueAt(selectedRow, 1).toString();
//            String nguoiTao = TablePhieuNhap.getValueAt(selectedRow, 2).toString();
//            String thoiGian = TablePhieuNhap.getValueAt(selectedRow, 3).toString();
//            String tongTien = TablePhieuNhap.getValueAt(selectedRow, 4).toString();
        }

    }//GEN-LAST:event_TablePhieuNhapMouseClicked

    private void TableNhapHang1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNhapHang1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_TableNhapHang1MouseMoved

    private void TableNhapHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNhapHang1MouseClicked
        TableNhapHang1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                // Lấy chỉ số hàng đang di chuột
                int row = TableNhapHang1.getSelectedRow();
                if (row >= 0) {
                    HienThiThongTinKhiBamChuotNH(row); // Gọi phương thức hiển thị thông tin
                }
            }
        });
    }//GEN-LAST:event_TableNhapHang1MouseClicked

    private void Xoa_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Xoa_NCCMouseClicked

        int selectedRow = Table_NCC.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maNCC = Table_NCC.getValueAt(selectedRow, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa nhà cung cấp này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {

                nhaCungCapDAO.XoaNCC(maNCC);
                loadTableNCC();
                javax.swing.JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Xoa_NCCMouseClicked

    private void Sua_NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sua_NCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sua_NCCActionPerformed

    private void Sua_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sua_NCCMouseClicked
        int selectedRow = Table_NCC.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maNCC = Table_NCC.getValueAt(selectedRow, 0).toString();
        String tNCC = Table_NCC.getValueAt(selectedRow, 1).toString();
        String sDTNCC = Table_NCC.getValueAt(selectedRow, 2).toString();
        String dcNCC = Table_NCC.getValueAt(selectedRow, 3).toString();
        String emailNCC = Table_NCC.getValueAt(selectedRow, 4).toString();

        JPanel editPanel = new JPanel(new GridLayout(6, 2));
        JLabel mNCC = new JLabel(maNCC);
        JTextField tenNCC = new JTextField(tNCC);
        JTextField sdt = new JTextField(sDTNCC);
        JTextField dc = new JTextField(dcNCC);
        JTextField email = new JTextField(emailNCC);
        editPanel.add(new JLabel("Mã Nhà Cung Cấp:"));
        editPanel.add(mNCC);
        editPanel.add(new JLabel("Tên Nhà Cung Cấp:"));
        editPanel.add(tenNCC);
        editPanel.add(new JLabel("Số Điện Thoại:"));
        editPanel.add(sdt);
        editPanel.add(new JLabel("Địa Chỉ:"));
        editPanel.add(dc);
        editPanel.add(new JLabel("Email:"));
        editPanel.add(email);

        int option = javax.swing.JOptionPane.showConfirmDialog(this, editPanel, "Sửa Thông Tin Nhà Cung Cấp", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (option == javax.swing.JOptionPane.OK_OPTION) {
            try {

                if (mNCC.getText().isEmpty() || tenNCC.getText().isEmpty() || sdt.getText().isEmpty() || dc.getText().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Các trường thông tin không được để trống!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NhaCungCapDTO ncc = new NhaCungCapDTO(mNCC.getText(), tenNCC.getText(), sdt.getText(), dc.getText(), email.getText());

                nhaCungCapDAO.SuaNCC(ncc);
                loadTableNCC();

                javax.swing.JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Sua_NCCMouseClicked

    private void Them_NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Them_NCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Them_NCCActionPerformed

    private void Them_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Them_NCCMouseClicked
        String mNCC = MNCC_NCC.getText();
        String tNCC = TNCC_NCC.getText();
        String sDT = SDT_NCC.getText();
        String xXu = DiaChi_NCC.getText();
        String email = Email_NCC.getText();
        if (mNCC.isEmpty() || tNCC.isEmpty() || sDT.isEmpty() || xXu.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if(mNCC.equals(mNCC)){
//            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không được trùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if(sDT.equals(sDT)){
//            JOptionPane.showMessageDialog(this, "Bị trùng số điện thoại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if(email.equals(email)){
//            JOptionPane.showMessageDialog(this, "Bị trùng Email", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        try {
            NhaCungCapDTO ncc = new NhaCungCapDTO(mNCC, tNCC, sDT, xXu, email);
            nhaCungCapDAO.ThemNCC(ncc);
            JOptionPane.showMessageDialog(this, "Thêm Nhà Cung Cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTableNCC();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Them_NCCMouseClicked

    private void TimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemKeyTyped

    }//GEN-LAST:event_TimKiemKeyTyped

    private void TimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemKeyPressed

    }//GEN-LAST:event_TimKiemKeyPressed

    private void TimKiemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemNCCActionPerformed
        String Key = TimKiemNCC.getText().trim();
        List<NhaCungCapDTO> danhSachTimKiem = nhaCungCapDAO.timKiemNhaCungCap(Key);
        DefaultTableModel model = (DefaultTableModel) Table_NCC.getModel();
        model.setRowCount(0);
        for (NhaCungCapDTO sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getMa_NCC(),
                sp.getTen_NhaCungCap(),
                sp.getSoDienThoai(),
                sp.getDiaChi(),
                sp.getEmail(),});
        }

    }//GEN-LAST:event_TimKiemNCCActionPerformed

    private void TimKiemNCCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNCCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNCCKeyPressed

    private void TimKiemNCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNCCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNCCKeyTyped

    private void LoadNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadNCCMouseClicked
        MNCC_NCC.setText("");
        TNCC_NCC.setText("");
        SDT_NCC.setText("");
        DiaChi_NCC.setText("");
        TimKiemNCC.setText("");
        Email_NCC.setText("");
        loadTableNCC();
    }//GEN-LAST:event_LoadNCCMouseClicked

    private void LoadNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadNCCActionPerformed

    private void TimKiemNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemNHActionPerformed
        String chuoi = TimKiemNH.getText().trim();

        List<TimKiemSP> danhSachTimKiem = SanPhamDAO.TimKiemSanPhamNH(chuoi);

        DefaultTableModel model = (DefaultTableModel) TableNhapHang1.getModel();
        model.setRowCount(0);
        for (TimKiemSP sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getMa_SanPham(),
                sp.getTen_SanPham(),
                sp.getSoLuong(),
                sp.getDonGia()});
        }
    }//GEN-LAST:event_TimKiemNHActionPerformed

    private void TimKiemNHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNHKeyPressed

    }//GEN-LAST:event_TimKiemNHKeyPressed

    private void TimKiemNHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNHKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNHKeyTyped

    private void Load5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Load5MouseClicked
        TimKiemNH.setText("");
        SoLuongtxt.setText("");
        MaNCCtxt.setText("");
        NguoiNhaptxt.setText("");
        loadTable1NhapHang();
        DefaultTableModel model = (DefaultTableModel) TableNhapHang2.getModel();
        model.setRowCount(0);

    }//GEN-LAST:event_Load5MouseClicked

    private void Load5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Load5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Load5ActionPerformed

    private void TextTimKiemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextTimKiemPNActionPerformed
        String chuoi = TextTimKiemPN.getText().trim();

        List<NhapHangDTO> danhSachTimKiem = nhapHangDAO.TimKiemPN(chuoi);

        DefaultTableModel model = (DefaultTableModel) TablePhieuNhap.getModel();
        model.setRowCount(0);
        for (NhapHangDTO sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getMa_Phieu_Nhap(),
                sp.getNguoi_Tao(),
                sp.getSoLuong() == 0 ? "N/A" : sp.getSoLuong(),
                sp.getDonGia() == null ? "N/A" : sp.getDonGia()
            });
        }
    }//GEN-LAST:event_TextTimKiemPNActionPerformed

    private void TextTimKiemPNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTimKiemPNKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTimKiemPNKeyPressed

    private void TextTimKiemPNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTimKiemPNKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTimKiemPNKeyTyped

    private void Load1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Load1MouseClicked
        TextTimKiemPN.setText("");
        loadTablePhieuNhap();
    }//GEN-LAST:event_Load1MouseClicked

    private void Load1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Load1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Load1ActionPerformed

    private void SpNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpNextMouseClicked
        switchToTabThongKeSP();
    }//GEN-LAST:event_SpNextMouseClicked

    private void NCCnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NCCnextMouseClicked

//        swtichToPanelThongKeNCC();
    }//GEN-LAST:event_NCCnextMouseClicked

    private void TKnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKnextMouseClicked
        switchToTabThongKeTK();
        loadTableThongKeTK();
    }//GEN-LAST:event_TKnextMouseClicked

    private void MaNCCtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaNCCtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaNCCtxtActionPerformed

    private void SuaSLNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaSLNHActionPerformed

    }//GEN-LAST:event_SuaSLNHActionPerformed

    private void SoLuongtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SoLuongtxtActionPerformed

    }//GEN-LAST:event_SoLuongtxtActionPerformed

    private void ThemHangVaoTabbleNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemHangVaoTabbleNHMouseClicked
        int selectedRow = TableNhapHang1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm!");
            return;
        }
        String maSanPham = (String) TableNhapHang1.getValueAt(selectedRow, 0);
        String tenSanPham = (String) TableNhapHang1.getValueAt(selectedRow, 1);
        if (SoLuongtxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số lượng");
            return;
        }
        int soLuongNhap = Integer.parseInt(SoLuongtxt.getText());
        BigDecimal donGia = (BigDecimal) TableNhapHang1.getValueAt(selectedRow, 3);
        DefaultTableModel modelNhapHang = (DefaultTableModel) TableNhapHang2.getModel();

        for (int i = 0; i < modelNhapHang.getRowCount(); i++) {
            if (modelNhapHang.getValueAt(i, 0).equals(maSanPham)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm!");
                return;
            }
        }
        modelNhapHang.addRow(new Object[]{maSanPham, tenSanPham, soLuongNhap, donGia});
        SoLuongtxt.setText("");
    }//GEN-LAST:event_ThemHangVaoTabbleNHMouseClicked

    private void TableNhapHang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNhapHang2MouseClicked

    }//GEN-LAST:event_TableNhapHang2MouseClicked

    private void NHButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NHButtonMouseClicked
        String maPhieuNhap = null;
        try {
            maPhieuNhap = nhapHangDAO.taoMaPhieuNhapMoi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo mã phiếu nhập mới: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        String maNCC = MaNCCtxt.getText();
        String nguoiNhap = NguoiNhaptxt.getText();
        if (maNCC.isEmpty() || nguoiNhap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin phiếu nhập!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) TableNhapHang2.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách nhập hàng trống!");
            return;
        }
        List<ChiTietNhapHangDTO> danhSachChiTiet = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maSanPham = (String) model.getValueAt(i, 0);
            String tenSanPham = (String) model.getValueAt(i, 1);
            int soLuong = (int) model.getValueAt(i, 2);
            BigDecimal donGia = (BigDecimal) model.getValueAt(i, 3);

            ChiTietNhapHangDTO chiTiet = new ChiTietNhapHangDTO(maSanPham, tenSanPham, soLuong, donGia);
            danhSachChiTiet.add(chiTiet);
        }

        NhapHangDAO nhapHangDAO = new NhapHangDAO();
        try {
            nhapHangDAO.ThemNH(maPhieuNhap, maNCC, nguoiNhap, danhSachChiTiet);
            JOptionPane.showMessageDialog(this, "Nhập hàng thành công!");
            model.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi nhập hàng: " + e.getMessage());
        }
        MaNCCtxt.setText("");
        NguoiNhaptxt.setText("");
    }//GEN-LAST:event_NHButtonMouseClicked

    private void XoaSPNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaSPNHMouseClicked
        int selectedRow = TableNhapHang2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm!");
            return;
        }
        DefaultTableModel modelNhapHang = (DefaultTableModel) TableNhapHang2.getModel();
        modelNhapHang.removeRow(selectedRow);
    }//GEN-LAST:event_XoaSPNHMouseClicked

    private void Load2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Load2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Load2ActionPerformed

    private void Load2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Load2MouseClicked
    TextTimKiemPN1.setText("");
    loadTablePhieuXuat();
    }//GEN-LAST:event_Load2MouseClicked

    private void TextTimKiemPN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTimKiemPN1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTimKiemPN1KeyTyped

    private void TextTimKiemPN1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTimKiemPN1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTimKiemPN1KeyPressed

    private void TextTimKiemPN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextTimKiemPN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTimKiemPN1ActionPerformed

    private void TablePhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePhieuXuatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablePhieuXuatMouseClicked

    private void TablePhieuXuatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePhieuXuatMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_TablePhieuXuatMouseMoved

    private void XoaPN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaPN1MouseClicked
        int selectedRow = TablePhieuXuat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu xuất!");
            return;
        }
        String maPX = TablePhieuXuat.getValueAt(selectedRow, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa phiếu xuất này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {

                xuatHangDAO.XoaXH(maPX);
                loadTablePhieuXuat();
                javax.swing.JOptionPane.showMessageDialog(this, "Xóa Phiếu nhập thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException e) {
                javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_XoaPN1MouseClicked

    private void SuaPN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaPN1MouseClicked
        switchToTabThongKe();
        switchToTabThongKePNPX();
        loadPNPX();    
    }//GEN-LAST:event_SuaPN1MouseClicked

    private void ThemPN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemPN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThemPN1ActionPerformed

    private void ThemPN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemPN1MouseClicked
        int selectedRow = TablePhieuXuat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maPhieuXuat = TablePhieuXuat.getValueAt(selectedRow, 0).toString();
        if (maPhieuXuat == null || maPhieuXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChiTietXuatHang chiTietForm = new ChiTietXuatHang(maPhieuXuat);
        JFrame frame = new JFrame("Quản Lý Kho Hàng");
        frame.setContentPane(new ChiTietXuatHang(maPhieuXuat));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }//GEN-LAST:event_ThemPN1MouseClicked

    private void TableXuatHangMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableXuatHangMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_TableXuatHangMouseMoved

    private void TableXuatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableXuatHangMouseClicked

        TableXuatHang.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = TableXuatHang.getSelectedRow();
                if (row >= 0) {
                    HienThiThongTinKhiBamChuotNH(row);
                }
            }
        });
    }//GEN-LAST:event_TableXuatHangMouseClicked

    private void TimKiemNH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemNH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNH1ActionPerformed

    private void TimKiemNH1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNH1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNH1KeyPressed

    private void TimKiemNH1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemNH1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemNH1KeyTyped

    private void Load6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Load6MouseClicked
    SLtxt.setText("");
    TimKiemNH1.setText("");
    MaNCCtxt1.setText("");
    NguoiNhaptxt1.setText("");
    loadTableXuatHang();
    }//GEN-LAST:event_Load6MouseClicked

    private void Load6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Load6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Load6ActionPerformed

    private void MaNCCtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaNCCtxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaNCCtxt1ActionPerformed

    private void TableXuatHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableXuatHang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableXuatHang1MouseClicked

    private void ThemXuathangTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThemXuathangTableMouseClicked
        int selectedRow = TableXuatHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm!");
            return;
        }
        if(SLtxt.getText().isEmpty()){
             JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm!");
            return;
        }
        String maSanPham = (String) TableXuatHang.getValueAt(selectedRow, 0);
        String tenSanPham = (String) TableXuatHang.getValueAt(selectedRow, 1);
        int soLuongNhap = Integer.parseInt(SLtxt.getText());
        BigDecimal donGia = (BigDecimal) TableXuatHang.getValueAt(selectedRow, 3);
        DefaultTableModel modelXuatHang = (DefaultTableModel) TableXuatHang1.getModel();
        for (int i = 0; i < modelXuatHang.getRowCount(); i++) {
            if (modelXuatHang.getValueAt(i, 0).equals(maSanPham)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm!");
                return;
            }
        }
        modelXuatHang.addRow(new Object[]{maSanPham, tenSanPham, soLuongNhap, donGia});
        SLtxt.setText("");
    }//GEN-LAST:event_ThemXuathangTableMouseClicked
    private final XuatHangDAO xuatHangDAO = new XuatHangDAO();
    private void XHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XHMouseClicked

        String maPhieuXuat = null;
        try {
            maPhieuXuat = xuatHangDAO.taoMaPhieuXuatMoi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo mã phiếu xuất mới: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        String maNCC = MaNCCtxt1.getText();
        String nguoiNhap = NguoiNhaptxt1.getText();

        if (maNCC.isEmpty() || nguoiNhap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin phiếu nhập!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) TableXuatHang1.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách Xuất hàng trống!");
            return;
        }
        List<ChiTietXuatHangDTO> danhSachChiTiet = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maSanPham = (String) model.getValueAt(i, 0);
            String tenSanPham = (String) model.getValueAt(i, 1);
            int soLuong = (int) model.getValueAt(i, 2);
            BigDecimal donGia = (BigDecimal) model.getValueAt(i, 3);

            ChiTietXuatHangDTO chiTiet = new ChiTietXuatHangDTO(maSanPham, tenSanPham, soLuong, donGia);
            danhSachChiTiet.add(chiTiet);
        }

        XuatHangDAO xuatHangDAO = new XuatHangDAO();
        try {
            xuatHangDAO.ThemXH(maPhieuXuat, maNCC, nguoiNhap, danhSachChiTiet);
            JOptionPane.showMessageDialog(this, "Xuất hàng thành công!");
            model.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất hàng: " + e.getMessage());
            e.printStackTrace();
        }
        MaNCCtxt1.setText("");
        NguoiNhaptxt1.setText("");
    }//GEN-LAST:event_XHMouseClicked

    private void XSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XSPMouseClicked
        int selectedRow = TableXuatHang1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm!");
            return;
        }
        DefaultTableModel modelNhapHang = (DefaultTableModel) TableXuatHang1.getModel();
        modelNhapHang.removeRow(selectedRow);
    }//GEN-LAST:event_XSPMouseClicked

    private void XSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XSPActionPerformed

    }//GEN-LAST:event_XSPActionPerformed

    private void TabbleTonKhoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabbleTonKhoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_TabbleTonKhoMouseMoved

    private void TabbleTonKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabbleTonKhoMouseClicked

    }//GEN-LAST:event_TabbleTonKhoMouseClicked

    private void TimKiemTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemTonKhoActionPerformed
        String chuoi = TimKiemTonKho.getText().trim();
        List<SanPhamDTO> danhSachTimKiem = sanPhamDAO.timKiemSanPham(chuoi);
        DefaultTableModel model = (DefaultTableModel) TabbleTonKho.getModel();
        model.setRowCount(0);
        for (SanPhamDTO sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getMa_SanPham(),
                sp.getTen_SanPham(),
                sp.getDonGia(),
                sp.getXuatXu(),
                sp.getLoai_SanPham(),
                sp.getSoLuong()});
        }

    }//GEN-LAST:event_TimKiemTonKhoActionPerformed

    private void TimKiemTonKhoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemTonKhoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemTonKhoKeyPressed

    private void TimKiemTonKhoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemTonKhoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemTonKhoKeyTyped

    private void LoadNCC1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadNCC1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadNCC1MouseClicked

    private void LoadNCC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadNCC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoadNCC1ActionPerformed

    private void XemChiTietPNPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XemChiTietPNPXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_XemChiTietPNPXActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void XemChiTietPNPXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XemChiTietPNPXMouseClicked

        int selectedRow = PNPX.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maPhieuNhap = PNPX.getValueAt(selectedRow, 0).toString();

        if (maPhieuNhap == null || maPhieuNhap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phiếu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedItem = (String) SelectPNPXBox.getSelectedItem();
        if ("Phiếu Nhập".equals(selectedItem)) {

            ChiTietNhapHang chiTietForm = new ChiTietNhapHang(maPhieuNhap);
            JFrame frame = new JFrame("Chi Tiết Phiếu Nhập");
            frame.setContentPane(chiTietForm);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
        } else if ("Phiếu Xuất".equals(selectedItem)) {
            ChiTietXuatHang chiTietForm = new ChiTietXuatHang(maPhieuNhap);
            JFrame frame = new JFrame("Chi Tiết Phiếu Xuất");
            frame.setContentPane(chiTietForm);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Phiếu Nhập hoặc Phiếu Xuất để xem chi tiết!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_XemChiTietPNPXMouseClicked

    private void SelectPNPXBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectPNPXBoxMouseEntered

    }//GEN-LAST:event_SelectPNPXBoxMouseEntered

    private void SelectPNPXBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectPNPXBoxActionPerformed
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String selectedItem = (String) SelectPNPXBox.getSelectedItem();
        if ("Tất Cả".equals(selectedItem)) {
            TextTPNPX.setText("Tổng Phiếu:");
            HienThiPhieu.setText(thongKeDAO.demPNPX() + "");
            BigDecimal tongTienPXPX = thongKeDAO.tongTienPNPX();
            TongTienPNPX.setText(decimalFormat.format(tongTienPXPX) + " VND");
            loadPNPX();

        } else if ("Phiếu Nhập".equals(selectedItem)) {

            TextTPNPX.setText("Tổng Phiếu Nhập:");
            HienThiPhieu.setText(thongKeDAO.demSLPN() + "");
            BigDecimal tongTienPN = thongKeDAO.tongTienPN();
            TongTienPNPX.setText(decimalFormat.format(tongTienPN) + " VND");
            loadPNTK();
        } else if ("Phiếu Xuất".equals(selectedItem)) {
            HienThiPhieu.setText(thongKeDAO.demSLPX() + "");
            TextTPNPX.setText("Tổng Phiếu Xuất:");
            BigDecimal tongTienPX = thongKeDAO.tongTienPX();
            TongTienPNPX.setText(decimalFormat.format(tongTienPX) + " VND");
            loadPXTK();
        }
    }//GEN-LAST:event_SelectPNPXBoxActionPerformed

    private void TimKiemPNTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemPNTKActionPerformed
        String chuoi = TimKiemPNTK.getText().trim();
        String selectedItem = (String) SelectPNPXBox.getSelectedItem();

        if ("Tất Cả".equals(selectedItem)) {

            List<NhapHangDTO> danhSachTimKiemPN = nhapHangDAO.TimKiemPN(chuoi);
            List<XuatHangDTO> danhSachTimKiemPX = xuatHangDAO.TimKiemPX(chuoi);

            DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
            model.setRowCount(0);
            for (NhapHangDTO sp : danhSachTimKiemPN) {
                model.addRow(new Object[]{
                    sp.getMa_Phieu_Nhap(),
                    sp.getNguoi_Tao(),
                    sp.getThoi_Gian_Tao(),//getSoLuong() == 0 ? "N/A" : sp.getSoLuong(),
                    sp.getDonGia() == null ? "N/A" : sp.getDonGia()
                });
            }

            for (XuatHangDTO sp : danhSachTimKiemPX) {
                model.addRow(new Object[]{
                    sp.getMa_Phieu_Xuat(),
                    sp.getNguoi_Tao(),
                    sp.getThoi_Gian_Tao(),//getSoLuong() == 0 ? "N/A" : sp.getSoLuong(),
                    sp.getDonGia() == null ? "N/A" : sp.getDonGia()
                });
            }
        } else if ("Phiếu Nhập".equals(selectedItem)) {
            List<NhapHangDTO> danhSachTimKiem = nhapHangDAO.TimKiemPN(chuoi);
            DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
            model.setRowCount(0);
            for (NhapHangDTO sp : danhSachTimKiem) {
                model.addRow(new Object[]{
                    sp.getMa_Phieu_Nhap(),
                    sp.getNguoi_Tao(),
                    sp.getSoLuong() == 0 ? "N/A" : sp.getSoLuong(),
                    sp.getDonGia() == null ? "N/A" : sp.getDonGia()
                });
            }
        } else if ("Phiếu Xuất".equals(selectedItem)) {
            List<XuatHangDTO> danhSachTimKiem = xuatHangDAO.TimKiemPX(chuoi);
            DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
            model.setRowCount(0);
            for (XuatHangDTO sp : danhSachTimKiem) {
                model.addRow(new Object[]{
                    sp.getMa_Phieu_Xuat(),
                    sp.getNguoi_Tao(),
                    sp.getSoLuong() == 0 ? "N/A" : sp.getSoLuong(),
                    sp.getDonGia() == null ? "N/A" : sp.getDonGia()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Phiếu Nhập, Phiếu Xuất hoặc Tất Cả để tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TimKiemPNTKActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        TimKiemPNTK.setText("");
        loadPNPX();
    }//GEN-LAST:event_jButton2MouseClicked

    private void ngayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayBatDauActionPerformed

    }//GEN-LAST:event_ngayBatDauActionPerformed

    private void NgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgayKetThucActionPerformed
        String nBatDau = ngayBatDau.getText().trim();
        String nKetThuc = NgayKetThuc.getText().trim();
        String loaiPhieu = (String) SelectPNPXBox.getSelectedItem();

        if (nBatDau.isEmpty() || nKetThuc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập cả ngày bắt đầu và ngày kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!nBatDau.matches("\\d{4}-\\d{2}-\\d{2}") || !nKetThuc.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày phải theo định dạng yyyy-MM-dd!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
        model.setRowCount(0);

        if ("Phiếu Nhập".equals(loaiPhieu)) {
            List<ThongKePNPX> danhSachPhieuNhap = thongKeDAO.timKiemPhieuNhapTheoNgay(nBatDau, nKetThuc);

            for (ThongKePNPX phieu : danhSachPhieuNhap) {

                model.addRow(new Object[]{
                    phieu.getMaPhieu(),
                    phieu.getNguoiTao(),
                    phieu.getThoiGianTao(),
                    phieu.getTongTien()
                });
            }
        } else if ("Phiếu Xuất".equals(loaiPhieu)) {
            List<ThongKePNPX> danhSachPhieuXuat = thongKeDAO.timKiemPhieuXuatTheoNgay(nBatDau, nKetThuc);

            for (ThongKePNPX phieuXuat : danhSachPhieuXuat) {
                model.addRow(new Object[]{
                    phieuXuat.getMaPhieu(),
                    phieuXuat.getNguoiTao(),
                    phieuXuat.getThoiGianTao(),
                    phieuXuat.getTongTien()
                    
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Phiếu Nhập hoặc Phiếu Xuất để tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            loadPNPX();
        }

    }//GEN-LAST:event_NgayKetThucActionPerformed

    private void SuaSLNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuaSLNHMouseClicked
        int selectedRow = TableNhapHang2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm!");
            return;
        }
        String maSanPham = TableNhapHang2.getValueAt(selectedRow, 0).toString();

        String soLuongMoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng mới:", "Sửa số lượng", JOptionPane.PLAIN_MESSAGE);

        if (soLuongMoiStr == null || soLuongMoiStr.trim().isEmpty()) {
            return;
        }
        int soLuongMoi;
        try {
            soLuongMoi = Integer.parseInt(soLuongMoiStr);
            if (soLuongMoi < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TableNhapHang2.setValueAt(soLuongMoi, selectedRow, 2);
        JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công!");
    }//GEN-LAST:event_SuaSLNHMouseClicked

    private void ngayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ngayBatDauFocusGained
        ngayBatDau.setText("");
    }//GEN-LAST:event_ngayBatDauFocusGained

    private void NgayKetThucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NgayKetThucFocusGained
        NgayKetThuc.setText("");
    }//GEN-LAST:event_NgayKetThucFocusGained

    private void NCCnextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NCCnextMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NCCnextMouseEntered

    private void PNPXnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNPXnextMouseClicked
        switchToTabThongKePNPX();
        loadPNPX();
    }//GEN-LAST:event_PNPXnextMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TimKiemPNTK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemPNTK1ActionPerformed
        LoginDAO lg = new LoginDAO();
        String chuoi = TimKiemPNTK1.getText().trim();
        List<TaiKhoanDTO> danhSachTimKiem = lg.timKiemTaiKhoan(chuoi);
        DefaultTableModel model = (DefaultTableModel) TableCTTK.getModel();
        model.setRowCount(0);
        for (TaiKhoanDTO sp : danhSachTimKiem) {
            model.addRow(new Object[]{
                sp.getTenDangNhap(),
                sp.getMatkhau(),
                sp.getEmail(),
                sp.getHovaten()
          });
        }
    
    }//GEN-LAST:event_TimKiemPNTK1ActionPerformed

    private void SelectPNPXBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectPNPXBox1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectPNPXBox1MouseEntered

    private void SelectPNPXBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectPNPXBox1ActionPerformed

    }//GEN-LAST:event_SelectPNPXBox1ActionPerformed

    private void SSLXHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SSLXHMouseClicked
        int selectedRow = TableXuatHang1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm!");
            return;
        }
        String maSanPham = TableXuatHang1.getValueAt(selectedRow, 0).toString();

        String soLuongMoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng mới:", "Sửa số lượng", JOptionPane.PLAIN_MESSAGE);

        if (soLuongMoiStr == null || soLuongMoiStr.trim().isEmpty()) {
            return;
        }
        int soLuongMoi;
        try {
            soLuongMoi = Integer.parseInt(soLuongMoiStr);
            if (soLuongMoi < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TableXuatHang1.setValueAt(soLuongMoi, selectedRow, 2);
        JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công!");
    }//GEN-LAST:event_SSLXHMouseClicked

    private void GiaBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GiaBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GiaBatDauActionPerformed

    private void GiaKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GiaKetThucActionPerformed
        //
        //        String gBatDau = GiaBatDau.getText().trim();
        //        String gKetThuc = GiaKetThuc.getText().trim();
        //        String loaiPhieu = (String) SelectPNPXBox.getSelectedItem();
        //
        //        if (gBatDau.isEmpty() || gKetThuc.isEmpty()) {
            //            JOptionPane.showMessageDialog(this, "Vui lòng nhập cả giá đầu và giá kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            //            return;
            //        }
        //
        //        DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
        //        model.setRowCount(0);
        //
        //        if ("Phiếu Nhập".equals(loaiPhieu)) {
            //            List<ThongKePNPX> danhSachPhieuNhap = thongKeDAO.timKiemPhieuNhapTheoNgay(gBatDau, gKetThuc);
            //
            //            for (ThongKePNPX phieu : danhSachPhieuNhap) {
                //                model.addRow(new Object[]{
                    //                    phieu.getMaPhieu(),
                    //                    phieu.getNguoiTao(),
                    //                    phieu.getThoiGianTao(),
                    //                    phieu.getTongTien()
                    //                });
            //            }
        //        } else {
        //            JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ tìm kiếm Phiếu Nhập trong chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        //        }

        String gBatDau = GiaBatDau.getText().trim();
        String gKetThuc = GiaKetThuc.getText().trim();
        String loaiPhieu = (String) SelectPNPXBox.getSelectedItem();
        if (gBatDau.isEmpty() || gKetThuc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập cả giá đầu và giá kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) PNPX.getModel();
        model.setRowCount(0);

        if ("Phiếu Nhập".equals(loaiPhieu)) {
            List<ThongKePNPX> danhSachPhieuNhap = thongKeDAO.timKiemPhieuNhapTheoNgay(gBatDau, gKetThuc);

            for (ThongKePNPX phieu : danhSachPhieuNhap) {
                model.addRow(new Object[]{
                    phieu.getMaPhieu(),
                    phieu.getNguoiTao(),
                    phieu.getThoiGianTao(),
                    phieu.getTongTien()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ tìm kiếm Phiếu Nhập trong chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_GiaKetThucActionPerformed

    private void MaPhieuXuattxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaPhieuXuattxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaPhieuXuattxtActionPerformed
    private void HienThiThongTinKhiBamChuot(int row) {
        if (row < 0 || row >= TableSanPham.getRowCount()) {
            return;
        }
        String maSanPham = TableSanPham.getValueAt(row, 0).toString();
        String tenSanPham = TableSanPham.getValueAt(row, 1).toString();
        String donGia = TableSanPham.getValueAt(row, 2).toString();
        String xuatXu = TableSanPham.getValueAt(row, 3).toString();
        String loaiSanPham = TableSanPham.getValueAt(row, 4).toString();
        String soLuong = TableSanPham.getValueAt(row, 5).toString();
        MaSanPham.setText(maSanPham);
        TenSanPham.setText(tenSanPham);
        DonGia.setText(donGia);
        XuatXu.setText(xuatXu);
        LoaiSanPham.setText(loaiSanPham);
        SoLuongSP.setText(soLuong);
    }

    private void HienThiThongTinKhiBamChuotTab2(int row) {
        if (row < 0 || row >= Table_NCC.getRowCount()) {
            return;
        }
        String maNhaCungCap = Table_NCC.getValueAt(row, 0).toString();
        String tenNhaCungCap = Table_NCC.getValueAt(row, 1).toString();
        String soDienThoaiNCC = Table_NCC.getValueAt(row, 2).toString();
        String DiaChiNCC = Table_NCC.getValueAt(row, 3).toString();
        String EmailNCC = Table_NCC.getValueAt(row, 4).toString();

        MNCC_NCC.setText(maNhaCungCap);
        TNCC_NCC.setText(tenNhaCungCap);
        SDT_NCC.setText(soDienThoaiNCC);
        DiaChi_NCC.setText(DiaChiNCC);
        Email_NCC.setText(EmailNCC);
    }

//    private void HienThiThongTinKhiBamChuotPN(int row) {
//        if (row < 0 || row >= TablePhieuNhap.getRowCount()) {
//            return;
//        }
//
//        String mPN = TablePhieuNhap.getValueAt(row, 0).toString();
//        String NTAO = TablePhieuNhap.getValueAt(row, 2).toString();
//        String mNCC = TablePhieuNhap.getValueAt(row, 1).toString();
//        String tongTien = TablePhieuNhap.getValueAt(row, 4).toString();
//
//        TextMaPhieuNhap.setText(mPN);
//        TextNguoiTao.setText(NTAO);
//        MNCCNP.setText(mNCC);
//        TextTongTien.setText(tongTien);
//    }
    private void HienThiThongTinKhiBamChuotNH(int row) {
        if (row < 0 || row >= TableNhapHang1.getRowCount()) {
            return;
        }

        String mSP = TableNhapHang1.getValueAt(row, 0).toString();
        String tSP = TableNhapHang1.getValueAt(row, 1).toString();
        String soLuong = TableNhapHang1.getValueAt(row, 2).toString();
        String donGia = TableNhapHang1.getValueAt(row, 3).toString();

    }

    private void updateHienThiSLSP() {
        int soLuongSanPham = thongKeDAO.demSLSP();
        HienThiSLSP.setText("" + soLuongSanPham);
    }

    private void updateHienThiSLNCC() {
        int slNCC = thongKeDAO.demSLNCC();
        HienThiSLNCC.setText("" + slNCC);
    }

    private void updateHienThiSLTK() {
        int slTK = thongKeDAO.demSLTK();
        HienThiSLTK.setText("" + slTK);
    }

    private void updateHienThiTongSoPhieu() {
        int slTQ = thongKeDAO.demPNPX();
        HienThiTongSoPhieu.setText("" + slTQ);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNangSP;
    private javax.swing.JPanel ChucNangSP2;
    private javax.swing.JPanel ChucNangSP3;
    private javax.swing.JTextField DiaChi_NCC;
    private javax.swing.JTextField DonGia;
    private javax.swing.JTextField Email_NCC;
    private javax.swing.JTextField GiaBatDau;
    private javax.swing.JTextField GiaKetThuc;
    private javax.swing.JLabel HienThiPhieu;
    private javax.swing.JLabel HienThiSLNCC;
    private javax.swing.JLabel HienThiSLSP;
    private javax.swing.JLabel HienThiSLTK;
    private javax.swing.JLabel HienThiTongSoPhieu;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JButton Load1;
    private javax.swing.JButton Load2;
    private javax.swing.JButton Load5;
    private javax.swing.JButton Load6;
    private javax.swing.JButton LoadNCC;
    private javax.swing.JButton LoadNCC1;
    private javax.swing.JButton LoadSP;
    private javax.swing.JTextField LoaiSanPham;
    private javax.swing.JTextField MNCC_NCC;
    private javax.swing.JTextField MaNCCtxt;
    private javax.swing.JTextField MaNCCtxt1;
    private javax.swing.JTextField MaPhieuXuattxt;
    private javax.swing.JTextField MaSanPham;
    private javax.swing.JButton NCCButton;
    private javax.swing.JPanel NCC_GUI;
    private javax.swing.JPanel NCC_GUI1;
    private javax.swing.JPanel NCCnext;
    private javax.swing.JButton NHButton;
    private javax.swing.JTextField NgayKetThuc;
    private javax.swing.JTextField NguoiNhaptxt;
    private javax.swing.JTextField NguoiNhaptxt1;
    private javax.swing.JPanel NhapHangGUI;
    private javax.swing.JPanel NhapHangGUI1;
    private javax.swing.JTable PNPX;
    private javax.swing.JPanel PNPXnext;
    private javax.swing.JPanel PanelNCC;
    private javax.swing.JTextField SDT_NCC;
    private javax.swing.JTextField SLtxt;
    private javax.swing.JButton SSLXH;
    private javax.swing.JPanel SanPhamGUI4;
    private javax.swing.JPanel SanPhamGUI5;
    private javax.swing.JComboBox<String> SelectPNPXBox;
    private javax.swing.JComboBox<String> SelectPNPXBox1;
    private javax.swing.JTextField SoLuongSP;
    private javax.swing.JTextField SoLuongtxt;
    private javax.swing.JPanel SpNext;
    private javax.swing.JButton SuaPN;
    private javax.swing.JButton SuaPN1;
    private javax.swing.JButton SuaSLNH;
    private javax.swing.JButton SuaSP;
    private javax.swing.JButton Sua_NCC;
    private javax.swing.JPanel TKnext;
    private javax.swing.JTextField TNCC_NCC;
    private javax.swing.JPanel TabNCC;
    private javax.swing.JPanel TabNhapHang;
    private javax.swing.JPanel TabPhieuNhap;
    private javax.swing.JPanel TabPhieuXuat;
    private javax.swing.JPanel TabSanPham;
    private javax.swing.JPanel TabThongKe;
    private javax.swing.JPanel TabTonKho;
    protected javax.swing.JTabbedPane TabTong;
    private javax.swing.JPanel TabXuatHang;
    private javax.swing.JPanel TabXuatHang1;
    private javax.swing.JTabbedPane TabbedTK;
    private javax.swing.JTable TabbleTonKho;
    private javax.swing.JPanel TableCTNCC;
    private javax.swing.JPanel TableCTSP;
    private javax.swing.JTable TableCTTK;
    public javax.swing.JTable TableNhapHang1;
    private javax.swing.JTable TableNhapHang2;
    public javax.swing.JTable TablePhieuNhap;
    public javax.swing.JTable TablePhieuXuat;
    public javax.swing.JTable TableSanPham;
    private javax.swing.JPanel TableTaiKhoan;
    private javax.swing.JTable TableThongKe;
    public javax.swing.JTable TableXuatHang;
    private javax.swing.JTable TableXuatHang1;
    private javax.swing.JTable Table_NCC;
    private javax.swing.JTextField TenSanPham;
    private javax.swing.JLabel TenTK;
    private javax.swing.JLabel TextTPNPX;
    private javax.swing.JTextField TextTimKiemPN;
    private javax.swing.JTextField TextTimKiemPN1;
    private javax.swing.JButton ThemHangVaoTabbleNH;
    private javax.swing.JButton ThemPN;
    private javax.swing.JButton ThemPN1;
    private javax.swing.JButton ThemSP;
    private javax.swing.JButton ThemXuathangTable;
    private javax.swing.JButton Them_NCC;
    private javax.swing.JTextField TimKiem;
    private javax.swing.JTextField TimKiemNCC;
    private javax.swing.JTextField TimKiemNH;
    private javax.swing.JTextField TimKiemNH1;
    private javax.swing.JTextField TimKiemPNTK;
    private javax.swing.JTextField TimKiemPNTK1;
    private javax.swing.JPanel TimKiemSP;
    private javax.swing.JPanel TimKiemSP1;
    private javax.swing.JPanel TimKiemSP2;
    private javax.swing.JPanel TimKiemSP3;
    private javax.swing.JPanel TimKiemSP4;
    private javax.swing.JPanel TimKiemSP5;
    private javax.swing.JPanel TimKiemSP6;
    private javax.swing.JTextField TimKiemTonKho;
    private javax.swing.JLabel TongTienPNPX;
    private javax.swing.JButton XH;
    private javax.swing.JButton XSP;
    private javax.swing.JButton XemChiTietPNPX;
    private javax.swing.JButton XoaPN;
    private javax.swing.JButton XoaPN1;
    private javax.swing.JButton XoaSP;
    private javax.swing.JButton XoaSPNH;
    private javax.swing.JButton Xoa_NCC;
    private javax.swing.JTextField XuatXu;
    private javax.swing.JButton dangxuat;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField ngayBatDau;
    private javax.swing.JButton nhaphang;
    private javax.swing.JButton phieunhap;
    private javax.swing.JButton phieuxuat;
    private javax.swing.JButton sanpham;
    private javax.swing.JButton thongke;
    private javax.swing.JButton tonkho;
    private javax.swing.JButton xuathang;
    // End of variables declaration//GEN-END:variables
}
