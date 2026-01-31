package repository;

import jakarta.persistence.Query;
import model.DonHang;
import model.GiangVien;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class DonHangRepository {
    private Session session = null;

    public DonHangRepository(){
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<DonHang> getAll(){
        return session.createQuery("SELECT dh FROM DonHang dh").list();
    }

    public DonHang getAllById(Integer id){
        return session.find(DonHang.class, id);
    }

    public void themDonHang(DonHang dh){
        try {
            session.getTransaction().begin();
            session.save(dh);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void suaDonHang(DonHang dh){
        try {
            session.getTransaction().begin();
            session.merge(dh);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    public void xoaDonHang(Integer id){
        try {
            session.getTransaction().begin();
            DonHang donHang = session.find(DonHang.class, id);
            if(donHang != null){
                session.remove(donHang);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    // Lọc theo trạng thái đã giao / chưa giao
    public List<DonHang> searchByTrangThai(Boolean daGiao) {
        String hql = "SELECT dh FROM DonHang dh WHERE dh.daGiao = :daGiao";
        Query query = session.createQuery(hql);
        query.setParameter("daGiao", daGiao);
        return query.getResultList();
    }

    public List<DonHang> searchBySoLuong(Integer minSL, Integer maxSL) {
        String hql = "SELECT dh FROM DonHang dh WHERE dh.soLuong BETWEEN :min AND :max";
        Query query = session.createQuery(hql);
        query.setParameter("min", minSL);
        query.setParameter("max", maxSL);
        return query.getResultList();
    }


    public List<DonHang> phanTrang(int page, int size){
        Query query = session.createQuery("select dh FROM  DonHang  dh");
        query.setFirstResult(page * size); //kqua dau tien
        query.setMaxResults(size);
        return query.getResultList();
    }
}
