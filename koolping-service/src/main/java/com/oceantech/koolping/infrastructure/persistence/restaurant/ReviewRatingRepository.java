package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.domain.model.common.ReviewRating;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 05/08/13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public interface ReviewRatingRepository extends CrudRepository<ReviewRating,Long> {
}
