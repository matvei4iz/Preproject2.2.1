package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "select u from User u inner join u.car " +
                "where u.car.model = :model and u.car.series = :series")
                .setParameter("model", model)
                .setParameter("series", series);
        return query.getResultList();
    }

//    @Override
//    public List<User> getUserByCar(String model, int series) {
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
//        Root<Car> carRoot = criteriaQuery.from(Car.class);
//        Car car = session.createQuery(criteriaQuery
//                .where(criteriaBuilder
//                        .equal(carRoot.get("model"), model))
//                .where(criteriaBuilder
//                        .equal(carRoot.get("series"), series))).getSingleResult();
//        Long carID = car.getId();
//        CriteriaQuery<User> criteriaQuery1 = criteriaBuilder.createQuery(User.class);
//        Root<User> userRoot = criteriaQuery1.from(User.class);
//        return session.createQuery(criteriaQuery1
//                .where(criteriaBuilder
//                        .equal(userRoot.get("car"), carID))).getResultList();
//    }
}
