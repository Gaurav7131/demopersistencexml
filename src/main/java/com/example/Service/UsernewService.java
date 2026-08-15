package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.Usernew;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class UsernewService {
    @PersistenceContext
    private EntityManager entityManager;

    // TypedQuery example:Typed-Safe
    public List<Usernew> getUserByAgeGreaterThan(int age) {
        String jpql = "SELECT u from Usernew u where u.age>:minage";
        // TypesQuery-Typed Safed query,Elimiated manual casting error & classCastExcpt
        // ensure we get expcted List<Usernew> return as a datatype
        TypedQuery<Usernew> query = entityManager.createQuery(jpql, Usernew.class);
        query.setParameter("minage", age);
        return query.getResultList();

    }

    // NativeQuery example-let u bypass jpql gives superpower to performed
    // db-specific dynamic query generation(Raw sql)
    public List<Usernew> getAllUser(String name) {
        // Requires sql
        String sql = "select * from UserNew";
        //// Passing Usernew.class maps the raw table rows directly into User objects
        Query query = entityManager.createNativeQuery(sql, Usernew.class);

        return query.getResultList();
    }

    // NamedQuery-calling predefined query from Entity class
    public Usernew getUserByEmailUsingNamedQuery(String email) {
        TypedQuery<Usernew> query = entityManager.createNamedQuery("Usernew.findByEmailNamed", Usernew.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

    // Criteria APi-helps to build query programmatically elimiting the conflict of
    // typos,misspelled,prevent from appln crashing
    public List<Usernew> getAllUsersByCriteria(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usernew> cq = cb.createQuery(Usernew.class);
        Root<Usernew> userRoot = cq.from(Usernew.class);

        // Building the WHERE clause programmatically: WHERE name = :name
        cq.select(userRoot).where(cb.equal(userRoot.get("name"), name));

        // query
        TypedQuery<Usernew> query = entityManager.createQuery(cq);
        return query.getResultList();

    }
}
