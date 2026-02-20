package com.datamart.repository;

import com.datamart.model.Product;
import jakarta.persistence.*;

import java.util.List;

public class ProductRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProductRepository() {
        emf = Persistence.createEntityManagerFactory("inventoryPU");
        em = emf.createEntityManager();
    }

    public void save(Product p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
    }

    public Product findById(int id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    public void update(Product p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(p);
        tx.commit();
    }

    public void delete(int id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Product p = em.find(Product.class, id);
        if (p != null) {
            em.remove(p);
        }
        tx.commit();
    }

    public void close() {
        em.close();
        emf.close();
    }
}