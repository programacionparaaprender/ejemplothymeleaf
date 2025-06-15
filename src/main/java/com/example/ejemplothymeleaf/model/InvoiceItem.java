package com.example.ejemplothymeleaf.model;

import java.util.Objects;


public class InvoiceItem {
    private final String name;
    private final int quantity;
    private final double price;

    public InvoiceItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Método para calcular el total
    public double getTotal() {
        return quantity * price;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return quantity == that.quantity && 
               Double.compare(that.price, price) == 0 && 
               Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }

    // toString
    @Override
    public String toString() {
        return "InvoiceItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
