package com.dicoding.intifada.sm5.mymodel;

import android.os.Parcel;
import android.os.Parcelable;

public class FollowersItems implements Parcelable {
    private int id;
    private String title;
    private String original_title;
    private String release_date;
    private Double vote_average;
    private String vote_count;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    /**/
    private String login;
    private String avatar_url;
    private String followers_url;
    private String following_url;

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }


    public String getVote_count() {
        return vote_count;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeString(this.release_date);
        dest.writeValue(this.vote_average);
        dest.writeString(this.vote_count);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);

        dest.writeString(this.login);
        dest.writeString(this.avatar_url);
        dest.writeString(this.followers_url);
        dest.writeString(this.following_url);
    }

    private FollowersItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.original_title = in.readString();
        this.release_date = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.vote_count = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();

        this.login = in.readString();
        this.avatar_url = in.readString();
        this.followers_url = in.readString();
        this.following_url = in.readString();
    }

    public static final Creator<FollowersItems> CREATOR = new Creator<FollowersItems>() {
        @Override
        public FollowersItems createFromParcel(Parcel source) {
            return new FollowersItems(source);
        }

        @Override
        public FollowersItems[] newArray(int size) {
            return new FollowersItems[size];
        }
    };
}
