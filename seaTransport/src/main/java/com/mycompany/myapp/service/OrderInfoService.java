package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OrderInfo;
import com.mycompany.myapp.repository.OrderInfoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderInfo}.
 */
@Service
@Transactional
public class OrderInfoService {

    private final Logger log = LoggerFactory.getLogger(OrderInfoService.class);

    private final OrderInfoRepository orderInfoRepository;

    public OrderInfoService(OrderInfoRepository orderInfoRepository) {
        this.orderInfoRepository = orderInfoRepository;
    }

    /**
     * Save a orderInfo.
     *
     * @param orderInfo the entity to save.
     * @return the persisted entity.
     */
    public OrderInfo save(OrderInfo orderInfo) {
        log.debug("Request to save OrderInfo : {}", orderInfo);
        return orderInfoRepository.save(orderInfo);
    }

    /**
     * Partially update a orderInfo.
     *
     * @param orderInfo the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderInfo> partialUpdate(OrderInfo orderInfo) {
        log.debug("Request to partially update OrderInfo : {}", orderInfo);

        return orderInfoRepository
            .findById(orderInfo.getId())
            .map(
                existingOrderInfo -> {
                    if (orderInfo.getOrder() != null) {
                        existingOrderInfo.setOrder(orderInfo.getOrder());
                    }
                    if (orderInfo.getLength() != null) {
                        existingOrderInfo.setLength(orderInfo.getLength());
                    }
                    if (orderInfo.getWidth() != null) {
                        existingOrderInfo.setWidth(orderInfo.getWidth());
                    }
                    if (orderInfo.getHeight() != null) {
                        existingOrderInfo.setHeight(orderInfo.getHeight());
                    }
                    if (orderInfo.getVolume() != null) {
                        existingOrderInfo.setVolume(orderInfo.getVolume());
                    }
                    if (orderInfo.getWeight() != null) {
                        existingOrderInfo.setWeight(orderInfo.getWeight());
                    }

                    return existingOrderInfo;
                }
            )
            .map(orderInfoRepository::save);
    }

    /**
     * Get all the orderInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderInfo> findAll(Pageable pageable) {
        log.debug("Request to get all OrderInfos");
        return orderInfoRepository.findAll(pageable);
    }

    /**
     * Get one orderInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderInfo> findOne(Long id) {
        log.debug("Request to get OrderInfo : {}", id);
        return orderInfoRepository.findById(id);
    }

    /**
     * Delete the orderInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderInfo : {}", id);
        orderInfoRepository.deleteById(id);
    }
}
