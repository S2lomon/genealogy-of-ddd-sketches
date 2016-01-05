package morelikeddd.applicaton;

import morelikeddd.model.location.LocationService;
import morelikeddd.model.shelfracks.Item;
import morelikeddd.model.shelfracks.RacksTracker;
import morelikeddd.model.shelfracks.ShelfRackCoordinates;

public class ShelfRacksService {

    private final RacksTracker racksTracker;

    private final LocationService locationService;

    ShelfRacksService(RacksTracker tracker, LocationService locationService) {
        this.racksTracker = tracker;
        this.locationService = locationService;
    }
    
    void storeItem(Item toStore) {
        ShelfRackCoordinates allocation = racksTracker.allocateItemOnRacks(toStore);
        locationService.storeItem(allocation, toStore);
    }
}
