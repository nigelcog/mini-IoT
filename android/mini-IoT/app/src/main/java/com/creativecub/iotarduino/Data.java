package com.creativecub.iotarduino;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Data {
    private String title, genre, year;
    private Bitmap ivIcon;
    private boolean toggle;
 
    public Data() {
    }
 
    public Data(String title, String genre, String year, Bitmap ivIcon, boolean toggle) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.ivIcon = ivIcon;
        this.toggle = toggle;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String name) {
        this.title = name;
    }
 
    public String getYear() {
        return year;
    }
 
    public void setYear(String year) {
        this.year = year;
    }
 
    public String getGenre() {
        return genre;
    }
 
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Bitmap getIvIcon() {
        return ivIcon;
    }

    public void setIvIcon(Bitmap imageView) {
        this.ivIcon = imageView;
    }

    public boolean gettoggle() {
        return toggle;
    }

    public void setToggle(boolean imageView) {
        this.toggle = imageView;
    }

}