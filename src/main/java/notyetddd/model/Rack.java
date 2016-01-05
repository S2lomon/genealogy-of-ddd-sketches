package notyetddd.model;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import commons.DomainEvent;
import commons.DomainEvents;
import commons.Entity;

@Entity
public class Rack {
    
    private final long id;

    private final List<Shelf> shelves;
    
    Rack(long id, Integer noOfShelves){
        this.id = id;
        shelves = Stream.generate(Shelf::new).limit(noOfShelves).collect(toList());
    }
    
    public int findShelfNoForItem(Item item) {
        Optional<Shelf> alreadyUsedShelf = shelves.stream().filter(shelf -> shelf.contains(item)).findAny();
        Optional<Shelf> anyEmptyShelf = shelves.stream().filter(Shelf::isEmpty).findAny();
        Shelf shelf = alreadyUsedShelf.orElse(anyEmptyShelf.get());
        return shelves.indexOf(shelf);
    }
    
    public void add(int shelfNo, Item item, int itemLimit) {
        Shelf shelf = getShelf(shelfNo);
        shelf.add(item);
        if(shelf.getQuantity() == itemLimit) {
            DomainEvents.send(new ShelfLimitReached(id, shelfNo));
        }
    }

    private Shelf getShelf(int shelfNo) {
        return shelves.get(shelfNo);
    }

    public void remove(int shelfNo, Item item) {
        Shelf shelf = getShelf(shelfNo);
        shelf.remove(item);
        if(shelf.getQuantity() == 0) {
            DomainEvents.send(new ShelfReleased(id, shelfNo));
        }
    }

    @DomainEvent
    public class ShelfLimitReached {

        public ShelfLimitReached(long id, int shelfNo) {
        }
    }

    @DomainEvent
    public class ShelfReleased {

        public ShelfReleased(long id, int shelfNo) {
        }
    }
}
