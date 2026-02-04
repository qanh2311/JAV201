package repository;

import model.Phim;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class PhimRepository {
    private Session session = null;

    public PhimRepository(){
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<Phim> getAll(){
        return session.createQuery("SELECT p FROM  Phim p").list();
    }

    public Phim getAllById(Integer id){
        return  session.find(Phim.class, id);
    }
}
