/**
 * 
 * A generic class that represents a pair of two values.
 * 
 * @param <T> the type of the first value
 * 
 * @param <U> the type of the second value
 */
public class MyPair<T, U> {
    private T first;
    private U second;

    /**
     * 
     * Constructs a new MyPair object with the specified first and second values.
     * 
     * @param first  the first value of the pair
     * @param second the second value of the pair
     */
    public MyPair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 
     * Returns the first value of the pair.
     * 
     * @return the first value of the pair
     */
    public T getFirst() {
        return first;
    }

    /**
     * 
     * Sets the first value of the pair to the specified value.
     * 
     * @param first the new value of the first element of the pair
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * 
     * Returns the second value of the pair.
     * 
     * @return the second value of the pair
     */
    public U getSecond() {
        return second;
    }

    /**
     * 
     * Sets the second value of the pair to the specified value.
     * 
     * @param second the new value of the second element of the pair
     */
    public void setSecond(U second) {
        this.second = second;
    }
}
