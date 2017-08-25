package com.sxm.spring.service.impl;

import com.sxm.spring.dao.CustomerDAO;
import com.sxm.spring.domain.Customer;
import com.sxm.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/5/14 23:47
 * @since 0.1
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    /**
     * Returns list of customers from dummy database.
     *
     * @return list of customers
     */
    public List list() {
        return customerDAO.list();
    }

    /**
     * Return customer object for given id from dummy database. If customer is
     * not found for id, returns null.
     *
     * @param id
     *            customer id
     * @return customer object for given id
     */
    public Customer get(Long id) {

        return customerDAO.get(id);
    }

    /**
     * Create new customer in dummy database. Updates the id and insert new
     * customer in list.
     *
     * @param customer
     *            Customer object
     * @return customer object with updated id
     */
    public Customer create(Customer customer) {
        return customerDAO.create(customer);
    }

    /**
     * Delete the customer object from dummy database. If customer not found for
     * given id, returns null.
     *
     * @param id
     *            the customer id
     * @return id of deleted customer object
     */
    public Long delete(Long id) {

        return customerDAO.delete(id);
    }

    /**
     * Update the customer object for given id in dummy database. If customer
     * not exists, returns null
     *
     * @param id
     * @param customer
     * @return customer object with id
     */
    public Customer update(Long id, Customer customer) {

        return customerDAO.update(id,customer);
    }
}
