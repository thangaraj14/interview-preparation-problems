package lld.moviebooking;

import java.util.Date;

public class Show {

    private int showId;
    private Date createdOn;
    private Date startTime;
    private Date endTime;
    private CinemaHall playedAt;
    private Movie movie;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CinemaHall getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(CinemaHall playedAt) {
        this.playedAt = playedAt;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

