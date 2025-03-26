package homework;


import java.util.Optional;
import java.util.Map;
import java.util.TreeMap;
import java.util.NavigableMap;
import java.util.AbstractMap;

public class CustomerService {

    private final NavigableMap<Customer, String> namesByCustomer = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = namesByCustomer.firstEntry();
        return copy(entry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = namesByCustomer.higherEntry(customer);
        return copy(entry);
    }

    public void add(Customer customer, String data) {
        namesByCustomer.put(customer, data);
    }

    private Map.Entry<Customer, String> copy(Map.Entry<Customer, String> entry) {
        return Optional.ofNullable(entry)
                .map((e) -> new AbstractMap.SimpleImmutableEntry<>(new Customer(e.getKey()), e.getValue()))
                .orElse(null);
    }
}
