/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author TRANG
 */
public class Stock {
    private int store_id;
    private int product_id;
    private int quantity;

    public Stock() {
    }

    public Stock(int store_id, int product_id, int quantity) {
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" + "store_id=" + store_id + ", product_id=" + product_id + ", quantity=" + quantity + '}';
    }

    
    
}
