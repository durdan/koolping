package com.oceantech.koolping.api.resource;

/**
 * Describes a single rated item
 *
 * @author Sanjoy Roy
 */
public class RatedItemResource extends ItemResource {

    private String myRating = "";
    private int noOfGreenRatingByFriends;
    private int noOfRedRatingByFriends;
    private int noOfNeutralRatingByFriends;
    private int totalGreenRating;
    private int totalRedRating;
    private int totalNeutralRating;

    public String getMyRating() {
        return myRating;
    }

    public void setMyRating(String myRating) {
        this.myRating = myRating;
    }

    public int getNoOfGreenRatingByFriends() {
        return noOfGreenRatingByFriends;
    }

    public void setNoOfGreenRatingByFriends(int noOfGreenRatingByFriends) {
        this.noOfGreenRatingByFriends = noOfGreenRatingByFriends;
    }

    public int getNoOfRedRatingByFriends() {
        return noOfRedRatingByFriends;
    }

    public void setNoOfRedRatingByFriends(int noOfRedRatingByFriends) {
        this.noOfRedRatingByFriends = noOfRedRatingByFriends;
    }

    public int getNoOfNeutralRatingByFriends() {
        return noOfNeutralRatingByFriends;
    }

    public void setNoOfNeutralRatingByFriends(int noOfNeutralRatingByFriends) {
        this.noOfNeutralRatingByFriends = noOfNeutralRatingByFriends;
    }

    public int getTotalGreenRating() {
        return totalGreenRating;
    }

    public void setTotalGreenRating(int totalGreenRating) {
        this.totalGreenRating = totalGreenRating;
    }

    public int getTotalRedRating() {
        return totalRedRating;
    }

    public void setTotalRedRating(int totalRedRating) {
        this.totalRedRating = totalRedRating;
    }

    public int getTotalNeutralRating() {
        return totalNeutralRating;
    }

    public void setTotalNeutralRating(int totalNeutralRating) {
        this.totalNeutralRating = totalNeutralRating;
    }

    @Override
    public String toString() {
        return "RatedItemResource{" +
                "myRating='" + myRating + '\'' +
                ", noOfGreenRatingByFriends=" + noOfGreenRatingByFriends +
                ", noOfRedRatingByFriends=" + noOfRedRatingByFriends +
                ", noOfNeutralRatingByFriends=" + noOfNeutralRatingByFriends +
                ", totalGreenRating=" + totalGreenRating +
                ", totalRedRating=" + totalRedRating +
                ", totalNeutralRating=" + totalNeutralRating +
                '}';
    }
}
