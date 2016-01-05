package morelikeddd.model.location;

import commons.DomainEvent;
import commons.DomainEvents;
import commons.Entity;
import morelikeddd.model.shelfracks.Item;
import morelikeddd.model.shelfracks.ShelfRackCoordinates;

@Entity
public class Location {

    private final ShelfRackCoordinates itemCoordinates;

    private Item itemStoredInThisLocation;

    private ItemOnLocationLimit itemLimit;

    private int itemQuantity = 0;

    Location(ShelfRackCoordinates itemCoordinates) {

        this.itemCoordinates = itemCoordinates;
    }

    void allocateTo(Item item, ItemOnLocationLimit itemLimit) {
        checkWhetherCanBeAllocated(item);
        this.itemStoredInThisLocation = item;
        this.itemLimit = itemLimit;
    }

    private void checkWhetherCanBeAllocated(Item item) {
        if(isAllocated()) {
            throw new IllegalStateException("Location can't be allocated to new Item, when other items are already stored here");
        }
    }

    boolean isAllocated() {
        return itemStoredInThisLocation != null;
    }

    void store(Item item) {
        checkItemAllocation(item);
        storeItem();
        informOthersWhenLimitIsReached();
    }

    private void checkItemAllocation(Item item) {
        if (!itemStoredInThisLocation.equals(item)) {
            throw new IllegalStateException("Item stored on Location with wrong allocation");
        }
    }

    private void storeItem() {
        itemQuantity++;
    }

    private void informOthersWhenLimitIsReached() {
        if (itemLimit.isReachedOn(this)) {
            DomainEvents.send(new LocationLimitReached(itemCoordinates));
        }
    }

    void remove(Item item) {
        checkItemAllocation(item);
        removeItem();
        freeLocationWhenNoLongerAnItemIsStored();
    }

    private void removeItem() {
        itemQuantity--;
    }

    private void freeLocationWhenNoLongerAnItemIsStored() {
        if (itemQuantity == 0) {
            makeTheLocationFree();
        }
    }

    private void makeTheLocationFree() {
        itemStoredInThisLocation = null;
        itemLimit = null;   
        DomainEvents.send(new LocationReleased(itemCoordinates));
    }

    int getItemQuantity() {
        return itemQuantity;
    }

    @DomainEvent
    public class LocationLimitReached {

        public LocationLimitReached(ShelfRackCoordinates itemCoordinates) {
        }
    }

    @DomainEvent
    public class LocationReleased {

        public LocationReleased(ShelfRackCoordinates itemCoordinates) {
        }
    }
}
