package io.taech;

import java.util.Collection;
import java.util.List;

/**
 * 완전탐색
 */
public interface CompleteSearch <T> {

    void search(T t);

    T get(int index);

    void addNeighbours(int index, List<T> nodes);
}
