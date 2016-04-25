package com.example.admin.designtask.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.designtask.models.PostModel;

import java.util.ArrayList;
import java.util.Date;

public class SqLiteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FeedPosts.db";

    private static final String AUTHOR_TABLE_NAME = "authors";
    private static final String AUTHOR_COLUMN_NAME = "name";
    private static final String AUTHOR_COLUMN_AVATAR = "avatar";

    private static final String POST_TABLE_NAME = "posts";
    private static final String POST_COLUMN_AUTHOR_ID = "authorId";
    private static final String POST_COLUMN_TEXT = "postText";
    private static final String POST_COLUMN_LOCATION = "postLocation";
    private static final String POST_COLUMN_LATITUDE = "postLatitude";
    private static final String POST_COLUMN_LONGITUDE = "postLongitude";
    private static final String POST_COLUMN_LOCATION_ZOOM = "postLocationZoom";
    private static final String POST_COLUMN_PICTURE_URL = "postPictureUrl";
    private static final String POST_COLUMN_VIDEO_URL = "postVideoUrl";
    private static final String POST_COLUMN_LIKES = "postLikes";
    private static final String POST_COLUMN_LIKE_STATUS = "postLikeStatus";
    private static final String POST_COLUMN_COMMENTS_COUNT = "postCommentsCount";


    private static final String COMMENT_TABLE_NAME = "comments";
    private static final String COMMENT_COLUMN_POST_ID = "postId";
    private static final String COMMENT_COLUMN_TEXT = "commentText";
    private static final String COMMENT_COLUMN_AUTHOR_ID = "authorId";
    private static final String COMMENT_COLUMN_DATETIME = "datetime";

    private static final String KEY_ID = "id";

    private static final String CREATE_TABLE_AUTHORS = "CREATE TABLE "
            + AUTHOR_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + AUTHOR_COLUMN_NAME + " TEXT,"
            + AUTHOR_COLUMN_AVATAR + " TEXT" + ")";

    private static final String CREATE_TABLE_POSTS = "CREATE TABLE "
            + POST_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + POST_COLUMN_AUTHOR_ID + " INTEGER,"
            + POST_COLUMN_TEXT + " TEXT,"
            + POST_COLUMN_LOCATION + " TEXT,"
            + POST_COLUMN_LATITUDE + " DOUBLE,"
            + POST_COLUMN_LONGITUDE + " DOUBLE,"
            + POST_COLUMN_LOCATION_ZOOM + " INTEGER,"
            + POST_COLUMN_PICTURE_URL + " Text,"
            + POST_COLUMN_VIDEO_URL + " Text,"
            + POST_COLUMN_LIKES + " INTEGER,"
            + POST_COLUMN_LIKE_STATUS + " TEXT,"
            + POST_COLUMN_COMMENTS_COUNT + " INTEGER" + ")";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE "
            + COMMENT_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + COMMENT_COLUMN_POST_ID + " INTEGER,"
            + COMMENT_COLUMN_AUTHOR_ID + " INTEGER,"
            + COMMENT_COLUMN_TEXT + " TEXT,"
            + COMMENT_COLUMN_DATETIME + " TEXT" + ")";


    public SqLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_AUTHORS);
        db.execSQL(CREATE_TABLE_POSTS);
        db.execSQL(CREATE_TABLE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + AUTHOR_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + POST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMMENT_TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    public void insertAuthors() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("select * from authors", null);
        if (cursor.getCount()== 0){
            for (int i = 0; i < 10; i++){
                contentValues.put("name", "user " + i);
                contentValues.put("avatar", "http://www.codrosu.ro/wp-content/uploads/2010/01/avatar-fantomitza-funny.png");
                db.insert("authors", null, contentValues);
                contentValues.clear();
            }
        }
    }

    public void insertPosts(){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("select * from posts", null);
        if (cursor.getCount()== 0) {
            for (int i = 0; i < 20; i++){
                int authorId = (int)(Math.random() * (10));
                int likes = (int)(Math.random() * (100));
                if ( i % 2 == 0){
                    contentValues.put("postText", "Some random text " + i);
                    contentValues.put("postLocation", "Sunny Beach");
                    contentValues.put("postLatitude", 42.694170);
                    contentValues.put("postLongitude", 27.714979);
                    contentValues.put("postLocationZoom", 10);
                    contentValues.put("postPictureUrl", "http://sunnybeachlunapark.com/pic/slide1.jpg");
                    contentValues.put("postVideoUrl", "http://www.revolutiondisco.net/videogals/2.png");
                    contentValues.put("postLikeStatus", "Liked");
                }
                contentValues.put("authorId", authorId);
                contentValues.put("postLikeStatus", "NotLiked");
                contentValues.put("postLikes", likes);
                contentValues.put("postCommentsCount", 0);
                db.insert("posts", null, contentValues);
                contentValues.clear();
            }
        }
    }

    public void insertComments(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("select * from comments", null);
        if (cursor.getCount()== 0) {
            for (int i = 0; i < 20; i++) {
                int authorId = (int) (Math.random() * (10));
                int postId = (int) (Math.random() * (20));
                Date commentDateTime = new Date();
                String commentText = "Some Random comment " + i;

                contentValues.put("authorId", authorId);
                contentValues.put("postId", postId);
                contentValues.put("commentText", commentText);
                contentValues.put("datetime", String.valueOf(commentDateTime));
                db.insert("comments", null, contentValues);
                contentValues.clear();

                addCommentCountToPost(postId);
            }
        }
    }

    private void addCommentCountToPost(int postId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        String selectQuery = "select * from posts where id=" + "'" + postId + "'";

        Cursor cursor;

        try {
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {


                contentValues.put("postCommentsCount", +1);
                db.update("posts", contentValues, "id="+postId, null);

                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllPosts(){
        ArrayList<PostModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from posts", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            PostModel postModel = new PostModel(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getInt(cursor.getColumnIndex(POST_COLUMN_AUTHOR_ID)),
                    cursor.getString(cursor.getColumnIndex(POST_COLUMN_TEXT)),
                    cursor.getString(cursor.getColumnIndex(POST_COLUMN_LOCATION)),
                    cursor.getDouble(cursor.getColumnIndex(POST_COLUMN_LATITUDE)),
                    cursor.getInt(cursor.getColumnIndex(POST_COLUMN_LOCATION_ZOOM)),
                    cursor.getDouble(cursor.getColumnIndex(POST_COLUMN_LONGITUDE)),
                    cursor.getString(cursor.getColumnIndex(POST_COLUMN_PICTURE_URL)),
                    cursor.getString(cursor.getColumnIndex(POST_COLUMN_VIDEO_URL)),
                    cursor.getInt(cursor.getColumnIndex(POST_COLUMN_LIKES)),
                    cursor.getString(cursor.getColumnIndex(POST_COLUMN_LIKE_STATUS)),
                    cursor.getInt(cursor.getColumnIndex(POST_COLUMN_COMMENTS_COUNT)));

            array_list.add(postModel);
            cursor.moveToNext();
        }
        cursor.close();
    }
//    public void insertParty(String title, Integer duration, Double latitude, Double longitude) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("partyTitle", title);
//        contentValues.put("partyDuration", duration);
//        contentValues.put("partyLatitude", latitude);
//        contentValues.put("partyLongitude", longitude);
//
//        long partyId = db.insert("party", null, contentValues);
//
//        insertUserParty(userForPartyId, partyId);
//    }
//
//    private void insertUserParty(long userId, long partyId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("userId", userId);
//        contentValues.put("partyId", partyId);
//        db.insert("userParty", null, contentValues);
//    }
//
//    public ArrayList<UserSqliteModel> getAllUsers() {
//        ArrayList<UserSqliteModel> array_list = new ArrayList<>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from users", null);
//        res.moveToFirst();
//
//        while (!res.isAfterLast()) {
//            UserSqliteModel user = new UserSqliteModel();
//            user.setId(res.getInt(res.getColumnIndex(KEY_ID)));
//            user.setUsername(res.getString(res.getColumnIndex(USERS_COLUMN_USERNAME)));
//            user.setToken(res.getString(res.getColumnIndex(USERS_COLUMN_TOKEN)));
//
//            array_list.add(user);
//            res.moveToNext();
//        }
//        res.close();
//        return array_list;
//    }
//
//    @SuppressWarnings("UnusedReturnValue")
//    public ArrayList<Party> getAllPartiesForUser() {
//        ArrayList<Party> array_list = new ArrayList<>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from userParty up" +
//                " INNER JOIN userForParty u on up.userId=u.id " +
//                " INNER JOIN party p on up.partyId=p.id" +
//                " WHERE up.userId=" + userForPartyId, null);
//        res.moveToFirst();
//
//        while (!res.isAfterLast()) {
//            Party party = new Party();
//            party.setTitle(res.getString(res.getColumnIndex(PARTY_COLUMN_TITLE)));
//            party.setDuration(res.getInt(res.getColumnIndex(PARTY_COLUMN_DURATION)));
//            party.setLatitude(res.getDouble(res.getColumnIndex(PARTY_COLUMN_LATITUDE)));
//            party.setLongitude(res.getDouble(res.getColumnIndex(PARTY_COLUMN_LONGITUDE)));
//            array_list.add(party);
//            res.moveToNext();
//        }
//        res.close();
//        return array_list;
//    }
//
//    private void insertUserForParty(String email) {
//        UserSqliteModel user = getUserByUsername(email);
//        if ((user != null ? user.getUsername() : null) == null) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("username", email);
//            userForPartyId = db.insert("userForParty", null, contentValues);
//        }
//    }
//
//    private UserSqliteModel getUserByUsername(String username) {
//        UserSqliteModel user = new UserSqliteModel();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "select * from userForParty where username=" + "'" + username + "'";
//
//        Cursor cursor;
//
//        try {
//            cursor = db.rawQuery(selectQuery, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//                user.setUsername((cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME))));
//                userForPartyId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
//
//                cursor.moveToNext();
//            }
//            cursor.close();
//            //userForPartyId=user.getId();
//            return user;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
