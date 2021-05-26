package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Port;
import com.mycompany.myapp.repository.PortRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Port}.
 */
@Service
@Transactional
public class PortService {

    private final Logger log = LoggerFactory.getLogger(PortService.class);

    private final PortRepository portRepository;

    public PortService(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    /**
     * Save a port.
     *
     * @param port the entity to save.
     * @return the persisted entity.
     */
    public Port save(Port port) {
        log.debug("Request to save Port : {}", port);
        return portRepository.save(port);
    }

    /**
     * Partially update a port.
     *
     * @param port the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Port> partialUpdate(Port port) {
        log.debug("Request to partially update Port : {}", port);

        return portRepository
            .findById(port.getId())
            .map(
                existingPort -> {
                    if (port.getLoadingPortLatitude() != null) {
                        existingPort.setLoadingPortLatitude(port.getLoadingPortLatitude());
                    }
                    if (port.getLoadingPortLongitude() != null) {
                        existingPort.setLoadingPortLongitude(port.getLoadingPortLongitude());
                    }
                    if (port.getShipmentPortLatitude() != null) {
                        existingPort.setShipmentPortLatitude(port.getShipmentPortLatitude());
                    }
                    if (port.getShipmentPortLongitude() != null) {
                        existingPort.setShipmentPortLongitude(port.getShipmentPortLongitude());
                    }

                    return existingPort;
                }
            )
            .map(portRepository::save);
    }

    /**
     * Get all the ports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Port> findAll(Pageable pageable) {
        log.debug("Request to get all Ports");
        return portRepository.findAll(pageable);
    }

    /**
     * Get one port by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Port> findOne(Long id) {
        log.debug("Request to get Port : {}", id);
        return portRepository.findById(id);
    }

    /**
     * Delete the port by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Port : {}", id);
        portRepository.deleteById(id);
    }
}
