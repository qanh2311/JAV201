package repository;

import model.MonAn;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class MonAnRepository {
    private Session session = null;
     public MonAnRepository(){
         session = HibernateConfig.getFACTORY().openSession();
     }

     public List<MonAn> getAll(){
         return session.createQuery("SELECT ma FROM MonAn ma").list();
     }

     public MonAn getAllById(Integer id){
         return session.find(MonAn.class, id);
     }
}
