package com.oceantech.koolping.domain.model.restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 04/08/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ReviewRestaurantPK implements Serializable {

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter

    private long restaurantId;

    public ReviewRestaurantPK(long userId, long restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public ReviewRestaurantPK() {
    }
}
