package com.oceantech.koolping.domain.model.restaurant;

import com.oceantech.koolping.domain.model.identity.Address;
import com.oceantech.koolping.domain.model.identity.Email;
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
    private Long restaurantId;

    @Getter
    @Setter
    private String restaurantName;

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
