package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ban_phim")
public class BanPhim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_ban_phim")
    private String tenBanPhim;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "so_luong")
    private Integer soLuong;
}