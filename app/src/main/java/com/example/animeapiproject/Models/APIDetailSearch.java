package com.example.animeapiproject.Models;

import java.util.List;

// getters and setters of all the information I need from the getAnimeByID GET method from my API
public class APIDetailSearch {
    public DataObj data;

    public class DataObj{
        int mal_id = 0;
        String title = "";
        String rating="";
        String status ="";
        String synopsis="";
        String episodes="";
        String duration="";
        String background="";
        Image images;
        Aired aired; // status
        List<Studios> studios;
        List<Genres> genres;

        public int getMal_id() {
            return mal_id;
        }

        public void setMal_id(int mal_id) {
            this.mal_id = mal_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public Image getImages() {
            return images;
        }

        public void setImages(Image images) {
            this.images = images;
        }

        public Aired getAired() {
            return aired;
        }

        public void setAired(Aired aired) {
            this.aired = aired;
        }

        public class Aired {
            String string;

            public String getString() {
                return string;
            }
        }

        public class Image {
            ItemObject.Image.Jpg jpg;

            public ItemObject.Image.Jpg getJpg() {
                return jpg;
            }

            public void setJpg(ItemObject.Image.Jpg jpg) {
                this.jpg = jpg;
            }

            public class Jpg {

                String large_image_url;

                public String getImage_url() {
                    return large_image_url;
                }

                public void setImage_url(String large_image_url) {
                    this.large_image_url = large_image_url;
                }
            }
        }

        public String getEpisodes() {
            return episodes;
        }

        public void setEpisodes(String episodes) {
            this.episodes = episodes;
        }

        public class Studios {
            String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }

        public List<Studios> getStudio() {
            return studios;
        }

        public void setStudio(List<Studios> studios) {
            this.studios = studios;
        }

        public class Genres {
            String name;
            int count;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public List<Genres> getGenres() {
            return genres;
        }

        public void setGenres(List<Genres> genres) {
            this.genres = genres;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }






    }


}
