package com.Henry.poppinsmarter.HelperClasses.HomeAdapter;

import android.graphics.drawable.Drawable;

public class CategoriesHelperClass {

    Drawable gradient;
    int image;
    String titile;

    public CategoriesHelperClass(Drawable gradient, int image, String titile) {
        this.gradient = gradient;
        this.image = image;
        this.titile = titile;
    }

    public Drawable getGradient() {
        return gradient;
    }

    public int getImage() {
        return image;
    }

    public String getTitile() {
        return titile;
    }
}
