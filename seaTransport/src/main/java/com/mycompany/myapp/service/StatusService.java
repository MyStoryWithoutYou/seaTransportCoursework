package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Status;
import com.mycompany.myapp.repository.StatusRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Status}.
 */
@Service
@Transactional
public class StatusService {

    private final Logger log = LoggerFactory.getLogger(StatusService.class);

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * Save a status.
     *
     * @param status the entity to save.
     * @return the persisted entity.
     */
    public Status save(Status status) {
        log.debug("Request to save Status : {}", status);
        return statusRepository.save(status);
    }

    /**
     * Partially update a status.
     *
     * @param status the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Status> partialUpdate(Status status) {
        log.debug("Request to partially update Status : {}", status);

        return statusRepository
            .findById(status.getId())
            .map(
                existingStatus -> {
                    if (status.getStatusName() != null) {
                        existingStatus.setStatusName(status.getStatusName());
                    }
                    if (status.getDescription() != null) {
                        existingStatus.setDescription(status.getDescription());
                    }

                    return existingStatus;
                }
            )
            .map(statusRepository::save);
    }

    /**
     * Get all the statuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Status> findAll(Pageable pageable) {
        log.debug("Request to get all Statuses");
        return statusRepository.findAll(pageable);
    }

    /**
     * Get one status by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Status> findOne(Long id) {
        log.debug("Request to get Status : {}", id);
        return statusRepository.findById(id);
    }

    /**
     * Delete the status by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Status : {}", id);
        statusRepository.deleteById(id);
    }
}
