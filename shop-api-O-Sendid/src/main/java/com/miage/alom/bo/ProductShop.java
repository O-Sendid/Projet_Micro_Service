package com.miage.alom.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ProductShop {
    @Column
    private int price;
    @Id
    private String id;
    @Column
    private String label;
    
    @Column
    private String sprites;
    

    public ProductShop(int price, String id, String label,String sprites) {
        this.price = price;
        this.id = id;
        this.label = label;
        this.sprites = sprites;
    }

    public ProductShop() {
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getSprites() {
        return sprites;
    }

    public void setSprites(String sprites) {
        this.sprites = sprites;
    }
}
