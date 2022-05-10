package io.taech;

import java.util.Collection;

public abstract class AbstractCompleteSearch implements CompleteSearch<Node> {

    abstract Collection<Node> once(Integer... nodes);

}
