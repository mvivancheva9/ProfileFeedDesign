package com.example.admin.designtask.models;

import java.util.Date;

/**
 * Created by Admin on 4/25/2016.
 */
public class CommentModel {
    private Integer id;
    private Integer postId;
    private Integer authorId;
    private String commentText;
    private Date dateTime;

    public CommentModel(Integer id, Integer postId, Integer authorId, String commentText, Date dateTime) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.commentText = commentText;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
