package com.example.animeapiproject.Models;

import android.provider.MediaStore;

// all things needed from the getAnimeBySearch GET method from the API (image, title, id only because this is after the search is done, not a whole lot of infromation is displayed)
public class ItemObject {
    String title = "";
    Image images;
    int mal_id = 0;

    public class Image {
        Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }

        public void setJpg(Jpg jpg) {
            this.jpg = jpg;
        }

        public class Jpg {

            String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return images;
    }

    public void setImage() {
        this.images = images;
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

}
