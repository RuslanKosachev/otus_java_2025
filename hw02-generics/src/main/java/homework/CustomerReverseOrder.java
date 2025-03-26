package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    private final Deque<Customer> orders = new ArrayDeque<>();

    public void add(Customer customer) {
        orders.push(customer);
    }

    public Customer take() {
        return orders.pop();
    }
}
