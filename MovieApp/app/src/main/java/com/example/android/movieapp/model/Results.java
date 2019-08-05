package com.example.android.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class Results implements Parcelable {

    @Ignore
    @SerializedName("vote_count")
    private int voteCount;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private float voteAvarage;

    @SerializedName("title")
    private String tittle;

    @Ignore
    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTittle;

    @SerializedName("overview")
    private String synopsisMovie;

    @SerializedName("release_date")
    private String releaseDate;

    public Results(){}

    public Results(Parcel parcel) {
        this.voteCount = parcel.readInt();
        this.id = parcel.readInt();
        this.voteAvarage = parcel.readFloat();
        this.tittle = parcel.readString();
        this.popularity = parcel.readFloat();
        this.posterPath = parcel.readString();
        this.originalLanguage = parcel.readString();
        this.originalTittle = parcel.readString();
        this.synopsisMovie = parcel.readString();
        this.releaseDate = parcel.readString();
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public float getVoteAvarage() {
        return voteAvarage;
    }

    public String getTittle() {
        return tittle;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTittle() {
        return originalTittle;
    }

    public String getSynopsisMovie() {
        return synopsisMovie;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoteAvarage(float voteAvarage) {
        this.voteAvarage = voteAvarage;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTittle(String originalTittle) {
        this.originalTittle = originalTittle;
    }

    public void setSynopsisMovie(String synopsisMovie) {
        this.synopsisMovie = synopsisMovie;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeInt(voteCount);
       parcel.writeInt(id);
       parcel.writeFloat(voteAvarage);
       parcel.writeString(tittle);
       parcel.writeFloat(popularity);
       parcel.writeString(posterPath);
       parcel.writeString(originalLanguage);
       parcel.writeString(originalTittle);
       parcel.writeString(synopsisMovie);
       parcel.writeString(releaseDate);
    }

    public static Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>(){

        @Override
        public Results createFromParcel(Parcel parcel) {
            return new Results(parcel);
        }

        @Override
        public Results[] newArray(int i) {
            return new Results[0];
        }
    };
}
