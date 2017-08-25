package com.sxm.spring.dao;

import com.sxm.spring.domain.Customer;

import java.util.List;

public interface CustomerDAO {

    /**
     * Returns list of customers from dummy database.
     *
     * @return list of customers
     */
    public List list();

    /**
     * Return customer object for given id from dummy database. If customer is
     * not found for id, returns null.
     *
     * @param id customer id
     * @return customer object for given id
     */
    public Customer get(Long id);

    /**
     * Create new customer in dummy database. Updates the id and insert new
     * customer in list.
     *
     * @param customer Customer object
     * @return customer object with updated id
     */
    public Customer create(Customer customer);

    /**
     * Delete the customer object from dummy database. If customer not found for
     * given id, returns null.
     *
     * @param id the customer id
     * @return id of deleted customer object
     */
    public Long delete(Long id);

    /**
     * Update the customer object for given id in dummy database. If customer
     * not exists, returns null
     *
     * @param id
     * @param customer
     * @return customer object with id
     */
    public Customer update(Long id, Customer customer);

}