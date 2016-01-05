package morelikeddd.model.shelfracks;

import commons.ValueObject;

@ValueObject
public class ShelfRackCoordinates {

    private final RackId rackId;

    private final ShelfNo no;

    ShelfRackCoordinates(RackId rackId, ShelfNo no) {

        this.rackId = rackId;
        this.no = no;
    }
}
