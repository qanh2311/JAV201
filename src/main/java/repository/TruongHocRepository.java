package repository;


import model.TruongHoc;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class TruongHocRepository {
    private Session session = null;

    public TruongHocRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<TruongHoc> getAll() {
        return session.createQuery("SELECT th FROM TruongHoc th").list();
    }

    public TruongHoc getById(Integer id) {
        return session.find(TruongHoc.class, id);
    }

    public void themTruongHoc(TruongHoc truongHoc) {
        try {
            session.getTransaction().begin();
            session.save(truongHoc);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void suaTruongHoc(TruongHoc truongHoc) {
        try {
            session.getTransaction().begin();
            session.merge(truongHoc);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void xoaTruongHoc(Integer id) {
        try {
            session.getTransaction().begin();
            TruongHoc truongHoc = session.find(TruongHoc.class, id);
            if (truongHoc != null) {
                session.remove(truongHoc);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}