package com.oceantech.koolping.domain.model.movie;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieGenre {

    @Id
    private String genreId;
    private String genreDescription;

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieGenre)) return false;

        MovieGenre that = (MovieGenre) o;

        if (genreDescription != null ? !genreDescription.equals(that.genreDescription) : that.genreDescription != null)
            return false;
        if (genreId != null ? !genreId.equals(that.genreId) : that.genreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genreId != null ? genreId.hashCode() : 0;
        result = 31 * result + (genreDescription != null ? genreDescription.hashCode() : 0);
        return result;
    }
}
