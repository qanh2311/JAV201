package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ve_dat")
public class VeDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nguoi_dat")
    private String nguoiDat;

    @Column(name = "so_luong_ve")
    private Integer soLuongVe;

    @Column(name = "ngay_chieu")
    private LocalDate ngayChieu;

    @Column(name = "da_thanh_toan")
    private Boolean daThanhToan;

    @ManyToOne
    @JoinColumn(name = "id_phim", referencedColumnName = "id")
    private Phim phim;
}
