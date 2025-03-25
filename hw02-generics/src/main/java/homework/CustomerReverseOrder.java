package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private Deque<Customer> orders = new ArrayDeque<>();

    public void add(Customer customer) {
        orders.push(customer);
    }

    public Customer take() {
        return orders.pop();
    }
}
