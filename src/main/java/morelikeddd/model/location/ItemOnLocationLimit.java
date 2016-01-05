package morelikeddd.model.location;

import commons.ValueObject;

@ValueObject
public class ItemOnLocationLimit {

    private final int limit;

    ItemOnLocationLimit(int limit) {

        this.limit = limit;
    }

    public boolean isReachedOn(Location location) {
        return limit == location.getItemQuantity();
    }
}
