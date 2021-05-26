package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Transport;
import com.mycompany.myapp.repository.TransportRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Transport}.
 */
@Service
@Transactional
public class TransportService {

    private final Logger log = LoggerFactory.getLogger(TransportService.class);

    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    /**
     * Save a transport.
     *
     * @param transport the entity to save.
     * @return the persisted entity.
     */
    public Transport save(Transport transport) {
        log.debug("Request to save Transport : {}", transport);
        return transportRepository.save(transport);
    }

    /**
     * Partially update a transport.
     *
     * @param transport the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Transport> partialUpdate(Transport transport) {
        log.debug("Request to partially update Transport : {}", transport);

        return transportRepository
            .findById(transport.getId())
            .map(
                existingTransport -> {
                    if (transport.getTransportName() != null) {
                        existingTransport.setTransportName(transport.getTransportName());
                    }
                    if (transport.getMaxWeight() != null) {
                        existingTransport.setMaxWeight(transport.getMaxWeight());
                    }
                    if (transport.getSpeed() != null) {
                        existingTransport.setSpeed(transport.getSpeed());
                    }
                    if (transport.getDeckSize() != null) {
                        existingTransport.setDeckSize(transport.getDeckSize());
                    }

                    return existingTransport;
                }
            )
            .map(transportRepository::save);
    }

    /**
     * Get all the transports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Transport> findAll(Pageable pageable) {
        log.debug("Request to get all Transports");
        return transportRepository.findAll(pageable);
    }

    /**
     * Get one transport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Transport> findOne(Long id) {
        log.debug("Request to get Transport : {}", id);
        return transportRepository.findById(id);
    }

    /**
     * Delete the transport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transport : {}", id);
        transportRepository.deleteById(id);
    }
}
