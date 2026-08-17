package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.UserN;

public interface UserNRepository extends JpaRepository<UserN, Long> {
    // This custom method completely bypasses the null-constraint mismatch error!
    @EntityGraph(value = "UserN.orders", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT  u from UserN u")
    List<UserN> findAllUserNsWithOrders();

}