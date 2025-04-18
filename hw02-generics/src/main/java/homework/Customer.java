package homework;


public class Customer implements Comparable<Customer> {
    private final long id;
    private String name;
    private long scores;


    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public Customer(Customer c) {
        this(c.getId(), c.getName(), c.getScores());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", scores=" + scores + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null
                || getClass() != o.getClass())
            return false;

        return id == ((Customer) o).id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public int compareTo(Customer c) {
        return Long.compare(getScores(), c.getScores());
    }
}
