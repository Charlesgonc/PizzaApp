/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oe.pizzaapp.model;

import java.util.ArrayList;


public class Pizza {
    private int size;
    private ArrayList<Topping> toppings;

    public Pizza(int size) {
        this.size = size;
        toppings=new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }
    
    public void addTopping(Topping t){
        toppings.add(t);
    }
    
    public void removeTopping(Topping t){
        // It is possible to order multiple servings of one topping, therefore we will remove just one
        toppings.remove(t);
    }
    
    public int totalPrice(){
        //Let base price be proportional to size
        int price=size*50;
        for (Topping topping : toppings) {
            price+=topping.getPrice();
        }
        return price;
    }
}
