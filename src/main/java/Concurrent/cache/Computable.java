package Concurrent.cache;

public interface Computable<A, V> {
    V compute(A a) throws InterruptedException;
}
