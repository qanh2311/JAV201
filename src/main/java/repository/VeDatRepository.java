package repository;

import jakarta.persistence.Query;
import model.VeDat;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class VeDatRepository {
    private Session session = null;

    public VeDatRepository(){
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<VeDat> getAll(){
        return session.createQuery("SELECT vd FROM VeDat vd").list();
    }

    public VeDat getAllById(Integer id){
        return session.find(VeDat.class, id);
    }

    public void themVeDat(VeDat vd){
        try {
            session.getTransaction().begin();
            session.save(vd);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void suaVeDat(VeDat vd){
        try {
            session.getTransaction().begin();
            session.merge(vd);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void xoaVeDat(Integer id){
        try {
            session.getTransaction().begin();
            VeDat veDat = session.find(VeDat.class, id);
            if(veDat != null){
                session.remove(veDat);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public List<VeDat> searchByTen(String ten){
        Query query = session.createQuery("SELECT vd FROM VeDat vd where vd.nguoiDat LIKE :ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public List<VeDat> phanTrang(int size, int page){
        Query query = session.createQuery("SELECT vd FROM VeDat vd");
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
