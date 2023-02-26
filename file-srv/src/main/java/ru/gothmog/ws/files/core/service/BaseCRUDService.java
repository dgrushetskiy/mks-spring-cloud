package ru.gothmog.ws.files.core.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseCRUDService<T> {

    /**
     * Create entity at database
     *
     * @param dto - current entity for creation
     * @return created entity
     */
    T create(T dto);

    /**
     * Find entity at database
     *
     * @param id = uniq id at db for specific entity
     * @return entity
     */
    T getById(long id);

    /**
     * Delete entity from DB
     *
     * @param id = uniq id at db for specific entity
     * @return deleted entity
     */
    T delete(long id);

    /**
     * Update specify entity
     *
     * @param id  = uniq id at db for specific entity
     * @param dto = entity for update
     * @return updated entity
     */
    T update(long id, T dto);

    /**
     * @param page  = quantity page
     * @param limit = number of units displayed
     * @return page from entity
     */
    Page<T> getPageToLimit(int page, int limit);

    /**
     * Receive all entities by specific type
     *
     * @return list from entity
     */
    List<T> getList();
}
