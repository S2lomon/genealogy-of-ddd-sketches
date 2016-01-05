package notyetddd.model;

import commons.Entity;

@Entity
public class Shelf {

    private int quantity = 0;

    private Item item = null;

    public void add(Item item) {
        this.item = item;
        quantity++;
    }

    boolean contains(Item item) {
        return this.item.equals(item);
    }

    public int getQuantity() {
        return quantity;
    }

    public void remove(Item item) {
        quantity--;
        if (isEmpty()) {
            this.item = null;
        }
    }

    public boolean isEmpty() {
        return quantity == 0;
    }
}
