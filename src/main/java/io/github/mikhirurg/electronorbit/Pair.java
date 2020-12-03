package io.github.mikhirurg.electronorbit;

public class Pair<E, P> {
    private final E first;
    private final P second;

    public Pair(E first, P second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public P getSecond() {
        return second;
    }
}
