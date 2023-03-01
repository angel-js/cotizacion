package com.saavedraconstructora.cotizacion.model;

import java.util.List;

public class ItemForm {
    private List<Item> items;
    public List<Item> getItems() {
        return this.items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "items=" + items +
                '}';
    }
}

