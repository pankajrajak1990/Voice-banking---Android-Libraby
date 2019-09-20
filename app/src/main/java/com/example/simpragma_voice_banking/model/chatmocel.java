package com.example.simpragma_voice_banking.model;

public class chatmocel {

    public  String title;
    public  int image_url;

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public chatmocel(String title, int image_url) {
        this.title = title;
        this.image_url=image_url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
