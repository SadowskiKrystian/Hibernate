package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarDao {
    public void save(Car car) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(car);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Car> find(CarFilter filter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "from Car ";
            if (filter != null) {
                query += "where 1=1 ";
                if (filter.getBrand() != null) {
                    query += "and brand like '%" + filter.getBrand() + "%'";
                }
                if (filter.getModel() != null) {
                    query += "and model = '" + filter.getModel() + "'";
                }
                if (filter.getColour() != null){
                    query += "and colour = '" + filter.getColour() +"'";
                }
                if (filter.getStartDate() != null || filter.getFinishDate() != null){
                    query += "and createDate between '" + filter.getStartDate() + "'"
                            + " and '" + filter.getFinishDate() + "'";
                }
                return session.createQuery(query, Car.class).list();
            }

        }
        return null;
    }

    public List<Car> findUsingCriteriaBuilder(CarFilter carFilter){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            List<Predicate> predicates = new ArrayList<>();
            if (carFilter.getBrand() != null){
                predicates.add(criteriaBuilder.equal(root.get("brand"), carFilter.getBrand()));
            }
            if (carFilter.getModel() != null){
                predicates.add(criteriaBuilder.equal(root.get("model"), carFilter.getModel()));
            }
            Predicate[] predicatesArray = new Predicate[predicates.size()];
            Predicate predicateAnd = criteriaBuilder.and(predicates.toArray(predicatesArray));
            criteriaQuery
                    .select(root)
                    .where(predicateAnd);

            Query<Car> query = session.createQuery(criteriaQuery);
            return query.getResultList();

        }
    }
}