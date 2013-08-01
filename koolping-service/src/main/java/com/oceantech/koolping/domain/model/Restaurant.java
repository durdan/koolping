package com.oceantech.koolping.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 29/07/13
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */

@ToString(callSuper = true, exclude = "Id")
@EqualsAndHashCode(callSuper = false)
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long RestaurantId;

    @Getter
    @Setter
    private String RestaurantName;

    @Getter
    @Setter
    @Embedded
    @Column(name = "RESTAURANT_EMAIL")
    private Email restaurantEmail;

    @Getter
    @Setter
    @Embedded
    private Address address;

    @Getter
    @Setter
    private String RestaurantPhone;

    @Getter
    @Setter
    private String RestaurantWebsiteText;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToOne(targetEntity = RestaurantCuisineType.class)
    @JoinColumn(name = "RESTAURANT_CUISINE_TYPE_ID")
    private RestaurantCuisineType restaurantCuisineType;


}
