package ru.gothmog.ws.auth.core.service;

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
     * @param id = uniq entity from DB
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
     * @return list from entity
     */
    List<T> getListPageToLimit(int page, int limit);

    /**
     * Receive all entities by specific type
     *
     * @return list from entity
     */
    List<T> getList();
}
