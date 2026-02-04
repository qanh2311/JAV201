package repository;

import model.BanPhim;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class BanPhimRepository {

    private Session session;

    public BanPhimRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<BanPhim> getAll() {
        return session.createQuery("FROM BanPhim", BanPhim.class).list();
    }

    public BanPhim getById(Integer id) {
        return session.find(BanPhim.class, id);
    }

    public void them(BanPhim bp) {
        try {
            session.getTransaction().begin();
            session.save(bp);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void sua(BanPhim bp) {
        try {
            session.getTransaction().begin();
            session.merge(bp);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void xoa(Integer id) {
        try {
            session.getTransaction().begin();
            session.delete(getById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}