package repository;

import jakarta.persistence.Query;
import model.GiangVien;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class GiangVienRepository {
    private Session session = null;

    public GiangVienRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<GiangVien> getAll() {
        return session.createQuery("SELECT gv FROM GiangVien gv").list();
    }

    public GiangVien getByID(Integer id){
        return session.find(GiangVien.class, id);
    }

    public void themGiangVien(GiangVien gv){
        try {
            session.getTransaction().begin();
            session.save(gv);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void suaGiangVien(GiangVien gv){
        try {
            session.getTransaction().begin();
            session.merge(gv);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void xoaGiangVien(Integer id){
        try {
            session.getTransaction().begin();
            GiangVien giangVien = session.find(GiangVien.class, id);
            if(giangVien != null){
                session.remove(giangVien);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<GiangVien> searchByTen(String ten){ // ten vs ten khac nhau
        Query query = session.createQuery("SELECT gv FROM  GiangVien  gv WHERE gv.tenGiangVien Like :ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public List<GiangVien> phanTrang( int page, int size){
        Query query = session.createQuery("select gv FROM  GiangVien  gv");
        query.setFirstResult(page * size); //kqua dau tien
        query.setMaxResults(size);
        return query.getResultList();
    }

}