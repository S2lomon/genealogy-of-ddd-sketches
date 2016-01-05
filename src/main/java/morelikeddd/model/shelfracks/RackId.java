package morelikeddd.model.shelfracks;

import commons.ValueObject;

@ValueObject
public class RackId {

    private final String id;

    RackId(String id) {
        this.id = id;
    }
}
