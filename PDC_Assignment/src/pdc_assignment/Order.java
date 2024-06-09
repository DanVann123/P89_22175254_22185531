/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

/**
 *
 * @author xuyan
 */
public class Order {
    private int orderNumber;
    private String date;
    private double total;

    public Order(int orderNumber, String date, double total) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.total = total;
    }

    public int getOrderNumber() { return orderNumber; }
    public String getDate() { return date; }
    public double getTotal() { return total; }
}