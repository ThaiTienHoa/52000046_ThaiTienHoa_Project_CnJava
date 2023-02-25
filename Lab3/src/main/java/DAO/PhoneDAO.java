package DAO;

import POJO.Phone;
import database.HibernateUtils;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {


    public Phone add(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return phone;
    }

    public Phone getPhoneById(int id) {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            Phone phone = session.get(Phone.class, id);
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Phone> getAllPhones() {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            List<Phone> phones = session.createQuery("from Phone", Phone.class).list();
            return phones;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void remove(Phone phone) {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(phone);
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

    public void update(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Phone getPhoneWithHighestSellingPrice() {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        Phone phone = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder(); // tao doi tuong tu sesstion
            CriteriaQuery<Phone> query = builder.createQuery(Phone.class); //Tao query tu phone
            Root<Phone> root = query.from(Phone.class); // tao root luu tru van
            query.orderBy(builder.desc(root.get("price")));
            phone = session.createQuery(query).setMaxResults(1).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return phone;
    }

    public List<Phone> getPhonesSortedByCountryAndPrice() {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        List<Phone> phones = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Phone> query = builder.createQuery(Phone.class);
            Root<Phone> root = query.from(Phone.class);
            query.orderBy(builder.asc(root.get("country")), builder.desc(root.get("price")));
            phones = session.createQuery(query).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return phones;
    }

    public boolean isAnyPhonePricedAbove50Million() {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<Phone> root = query.from(Phone.class);
            query.select(builder.count(root));
            query.where(builder.greaterThan(root.get("price"), 50000000));
            Long count = session.createQuery(query).getSingleResult();
            result = count > 0;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public Phone getFirstPinkPhoneOver15Million() {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = null;
        Phone result = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Phone> query = builder.createQuery(Phone.class);
            Root<Phone> root = query.from(Phone.class);
            Predicate colorPredicate = builder.equal(root.get("color"), "Pink");
            Predicate pricePredicate = builder.greaterThan(root.get("price"), 15000000);
            Predicate combinedPredicate = builder.and(colorPredicate, pricePredicate);
            query.select(root).where(combinedPredicate);
            result = session.createQuery(query).setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }


}
