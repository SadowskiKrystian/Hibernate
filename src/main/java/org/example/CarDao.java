package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {
    public void save(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(car);
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
                if (filter.getBrand() != null) {
                    query += "where brand like '%" + filter.getBrand() + "%'";
                }
                if (filter.getModel() != null) {
                    query += "where model = '" + filter.getModel() + "'";
                }
                System.out.println(query);
                return session.createQuery(query, Car.class).list();
            }
        }
    }
