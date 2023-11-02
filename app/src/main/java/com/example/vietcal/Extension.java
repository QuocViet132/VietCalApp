package com.example.vietcal;

import java.io.Serializable;

public class Extension implements Serializable {
    private String nameExtension;
    private int imageResource;

    public String getNameExtension() {
        return nameExtension;
    }


    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setNameExtension(String nameExtension) {
        this.nameExtension = nameExtension;
    }

    public Extension(String nameExtension, int imageResource) {
        this.nameExtension = nameExtension;
        this.imageResource = imageResource;
    }
}
