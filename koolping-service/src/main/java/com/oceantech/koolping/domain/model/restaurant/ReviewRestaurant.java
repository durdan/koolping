package com.oceantech.koolping.domain.model.restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 04/08/13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class ReviewRestaurant {

    @Getter
    @Setter
    @Id
    private ReviewRestaurantPK reviewRestaurantPK;

    @Getter
    @Setter
    private String IpAddress;

    @Getter
    @Setter
    private DateTime reviewDate;

    @Getter
    @Setter
    private String ReviewRatingId;

}
