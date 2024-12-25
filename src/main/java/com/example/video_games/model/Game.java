package com.example.video_games.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Game {
    //clé primaire
    @Id
    private int id;
    private int price;
    private String category;

    // Default constructor (required by Hibernate)
    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Game(int id, int price, String category) {
        this.id = id;
        this.price = price;
        this.category = category;
    }
}
