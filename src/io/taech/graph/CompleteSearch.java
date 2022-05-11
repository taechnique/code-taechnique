package io.taech.graph;

import java.util.List;

/**
 * 완전탐색
 */
public interface CompleteSearch <T> {

    void search(T t);

    T get(Integer index);

    List<T> once(Integer... nodes);

    void addNeighbours(Integer index, List<T> nodes);


    void searchWithoutRecursive(T v);
}
