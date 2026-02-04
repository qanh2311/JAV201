package repository;

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
            session.delete(this.getAllById(id));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }


}
