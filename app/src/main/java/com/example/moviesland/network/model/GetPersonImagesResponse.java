package com.example.moviesland.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPersonImagesResponse {

    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public class Profile {

        @SerializedName("iso_639_1")
        @Expose
        private Object iso6391;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("vote_average")
        @Expose
        private Float voteAverage;
        @SerializedName("file_path")
        @Expose
        private String filePath;
        @SerializedName("aspect_ratio")
        @Expose
        private Float aspectRatio;

        public Object getIso6391() {
            return iso6391;
        }

        public void setIso6391(Object iso6391) {
            this.iso6391 = iso6391;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public Float getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(Float aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

    }

}
