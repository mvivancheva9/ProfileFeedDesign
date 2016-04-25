package com.example.admin.designtask.models;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Admin on 4/25/2016.
 */
public class PostModel{
    private Integer id;
    private Integer authorId;
    private String postText;
    private String postLocation;
    private Double postLatitude;
    private Double postLongitude;
    private Integer postLocationZoom;
    private String pictureUrl;
    private String videoUrl;
    private Integer likes;
    private String likeStatus;
    private Integer commentsCount;

    public PostModel(Integer id, Integer authorId, String postText, String postLocation, Double postLatitude, Integer postLocationZoom, Double postLongitude, String pictureUrl, String videoUrl, Integer likes, String likeStatus, Integer commentsCount) {
        this.id = id;
        this.authorId = authorId;
        this.postText = postText;
        this.postLocation = postLocation;
        this.postLatitude = postLatitude;
        this.postLocationZoom = postLocationZoom;
        this.postLongitude = postLongitude;
        this.pictureUrl = pictureUrl;
        this.videoUrl = videoUrl;
        this.likes = likes;
        this.likeStatus = likeStatus;
        this.commentsCount = commentsCount;
    }

    public PostModel(Integer id, Integer authorId, Integer likes, String likeStatus, Integer commentsCount) {
        this.id = id;
        this.authorId = authorId;
        this.likes = likes;
        this.likeStatus = likeStatus;
        this.commentsCount = commentsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public Double getPostLatitude() {
        return postLatitude;
    }

    public void setPostLatitude(Double postLatitude) {
        this.postLatitude = postLatitude;
    }

    public Integer getPostLocationZoom() {
        return postLocationZoom;
    }

    public void setPostLocationZoom(Integer postLocationZoom) {
        this.postLocationZoom = postLocationZoom;
    }

    public Double getPostLongitude() {
        return postLongitude;
    }

    public void setPostLongitude(Double postLongitude) {
        this.postLongitude = postLongitude;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }
}
