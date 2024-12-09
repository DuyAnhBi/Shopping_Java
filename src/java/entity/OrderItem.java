/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author TRANG
 */
public class OrderItem {
    private int order_id,item_id,product_id,quantity;
    private double list_price,discount;

    public OrderItem() {
    }

    public OrderItem(int order_id, int item_id, int product_id, int quantity, double list_price, double discount) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.list_price = list_price;
        this.discount = discount;
    }

 

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "order_id=" + order_id + ", item_id=" + item_id + ", product_id=" + product_id + ", quantity=" + quantity + ", list_price=" + list_price + ", discount=" + discount + '}';
    }
    
    
}
