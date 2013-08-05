package com.oceantech.koolping.domain.model.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 04/08/13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class ReviewRating {

    @Id
    @Getter
    @Setter
    private String ReviewRatingId;

    @Getter
    @Setter
    private String ReviewDescription;
}
