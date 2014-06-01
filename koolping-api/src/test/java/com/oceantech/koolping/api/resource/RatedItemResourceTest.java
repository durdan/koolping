package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class RatedItemResourceTest {

    @Test
    public void shouldConstructItemResource() {
        RatedItemResource resource = new RatedItemResource();
        resource.setItemRef("urn:items:1");
        resource.setPlaceId("42377700f964a52024201fe3");
        resource.setMyRating("green");
        resource.setNoOfGreenRatingByFriends(2);
        resource.setNoOfNeutralRatingByFriends(3);
        resource.setNoOfRedRatingByFriends(2);
        resource.setTotalGreenRating(3);
        resource.setTotalRedRating(2);
        resource.setTotalNeutralRating(3);

        assertThat(resource).isNotNull();
        assertThat(resource.getItemRef()).isEqualTo("urn:items:1");
        assertThat(resource.getPlaceId()).isEqualTo("42377700f964a52024201fe3");
        assertThat(resource.getMyRating()).isEqualTo("green");
        assertThat(resource.getNoOfGreenRatingByFriends()).isEqualTo(2);
        assertThat(resource.getNoOfNeutralRatingByFriends()).isEqualTo(3);
        assertThat(resource.getNoOfRedRatingByFriends()).isEqualTo(2);
        assertThat(resource.getTotalGreenRating()).isEqualTo(3);
        assertThat(resource.getTotalRedRating()).isEqualTo(2);
        assertThat(resource.getTotalNeutralRating()).isEqualTo(3);
    }
}
