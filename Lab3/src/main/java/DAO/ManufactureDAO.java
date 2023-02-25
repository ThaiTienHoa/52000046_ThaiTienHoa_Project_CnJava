package DAO;

import POJO.Manufacture;
import database.HibernateUtils;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



import java.util.List;

public class ManufactureDAO {
    public static Manufacture add(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return manufacture;
    }

    public Manufacture getManufactureById(int id_manu) {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            Manufacture manufacture = session.get(Manufacture.class, id_manu);
            return manufacture;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Manufacture> getAllManufactures() {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            List<Manufacture> manufactures = session.createQuery("from Manufacture", Manufacture.class).list();
            return manufactures;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeById(int id_manu) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id_manu);
            if (manufacture != null) {
                session.delete(manufacture);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void remove(Manufacture manufacture) {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(manufacture);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static boolean checkAbove100E() {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Manufacture> root = criteriaQuery.from(Manufacture.class);
            criteriaQuery.select(builder.count(root));
            criteriaQuery.where(builder.greaterThanOrEqualTo(root.get("employee"), 100));
            Long count = session.createQuery(criteriaQuery).getSingleResult();
            List<Manufacture> manufactures = ManufactureDAO.getAllManufactures();
            return count == (long) manufactures.size();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int sumOfAllEmployees() {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Manufacture> manufactures = session.createQuery("FROM Manufacture").list();
        transaction.commit();

        int sum = 0;
        for (Manufacture manufacture : manufactures) {
            sum += manufacture.getEmployee();
        }

        return sum;
    }



    public static Manufacture getLastUSManufacturer() {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Manufacture> manufactures = session.createQuery("FROM Manufacture WHERE location = 'USA' ORDER BY id DESC").setMaxResults(1).list();
            transaction.commit();
            if (manufactures.isEmpty()) {
                throw new IllegalArgumentException("No US-based manufacturers found.");
            } else {
                return manufactures.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }


}
