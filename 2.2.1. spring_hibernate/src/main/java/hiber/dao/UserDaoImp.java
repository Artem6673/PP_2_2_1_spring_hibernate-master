package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public List<User> getUserOwnerCar(String model, int series) {
      List users = null;

      TypedQuery jpqlQuery = null;
      try {
         users = sessionFactory.getCurrentSession().createQuery("FROM User WHERE car.model=:model and car.series=:series").setParameter("model",model).setParameter("series",series).list();


      } catch (HibernateException e) {
         e.printStackTrace();
      }
      return users;
   }

}
