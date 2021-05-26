package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrderInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {}
