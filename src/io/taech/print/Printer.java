package io.taech.print;

import java.util.Collection;

public interface Printer {

    void print(Object obj) throws Exception;

    void print(Collection<? extends Object> collection) throws Exception;
}
