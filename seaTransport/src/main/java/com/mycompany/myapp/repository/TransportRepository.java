package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Transport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Transport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {}
