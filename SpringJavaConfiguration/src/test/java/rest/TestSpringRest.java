package rest;

import com.sxm.spring.domain.Customer;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class TestSpringRest {
    /**
     * Health Check service, to insert a dummy data in the Employees data storage
     */
    private static final String DUMMY_EMP = "/rest/emp/dummy";
    /**
     * To create the Employee object and store it
     */
    private static final String CREATE_EMP = "/customers";
    /**
     * To get the Employee object based on the id
     */
    private static final String GET_EMP = "/customers/{id}";
    /**
     * To get the list of all the Employees in the data store
     */
    private static final String GET_ALL_EMP = "/customers";
    /**
     * To delete the Employee object from the data storage based on the id
     */
    private static final String DELETE_EMP = "/customers/{id}";

    private static final String SERVER_URI = "http://localhost";

    public static void main(String args[]) {

        testGetDummyCustomer();
        System.out.println("*****");

        testCreateCustomer();
        System.out.println("*****");

        testGetCustomer();
        System.out.println("*****");

        testGetAllCustomer();
    }

    private static void testGetDummyCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(SERVER_URI + DUMMY_EMP, Customer.class);
        printCustomerData(customer);
    }

    private static void testCreateCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Pankaj Kumar");
        Customer response = restTemplate.postForObject(SERVER_URI + CREATE_EMP, customer, Customer.class);
        printCustomerData(response);
    }

    private static void testGetCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(SERVER_URI + "/customers/1", Customer.class);
        printCustomerData(customer);
    }

    private static void testGetAllCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        // we can't get List<Employee> because JSON convertor doesn't know the type of
        // object in the list and hence convert it to default JSON object type LinkedHashMap
        List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI + GET_ALL_EMP, List.class);
        System.out.println(emps.size());
        for (LinkedHashMap map : emps) {
            System.out.println("ID=" + map.get("id") + ",Name=" + map.get("name") + ",CreatedDate=" + map.get("createdDate"));
            ;
        }
    }

    public static void printCustomerData(Customer customer) {
        System.out.println(customer);
    }
}
