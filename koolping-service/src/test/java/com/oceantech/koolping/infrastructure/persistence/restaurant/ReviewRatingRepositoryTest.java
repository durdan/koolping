package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.common.ReviewRating;
import com.oceantech.koolping.infrastructure.persistence.restaurant.ReviewRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 05/08/13
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class ReviewRatingRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private ReviewRatingRepository reviewRatingRepository;


    @Test
    public void shouldSaveReviewRating(){
        ReviewRating reviewRating= new ReviewRating();
        reviewRating.setReviewDescription("LikeIt");
        reviewRating.setReviewRatingId("L");
    }
}
