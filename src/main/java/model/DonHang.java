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
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "khach_hang")
    private String khachHang;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "da_giao")
    private Boolean daGiao;

    @ManyToOne
    @JoinColumn(name = "id_mon_an", referencedColumnName = "id")
    private MonAn monAn;
}
