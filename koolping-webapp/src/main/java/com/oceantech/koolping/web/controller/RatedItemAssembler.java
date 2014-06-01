package com.oceantech.koolping.web.controller;


import com.oceantech.koolping.api.resource.RatedItemResource;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.service.PersonService;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Sanjoy Roy
 */
public final class RatedItemAssembler {

    private RatedItemAssembler() {
        throw new AssertionError();
    }

    public static List<RatedItemResource> toResources(final Person person, final PersonService personService) {
        List<RatedItemResource> ratedItemResources = new ArrayList<>();
        for (Item item : person.getRatedItems()) {
            ratedItemResources.add(toResource(person, item, personService));
        }
        return ratedItemResources;
    }

    public static RatedItemResource toResource(final Person person, final Item item, final PersonService personService) {
        RatedItemResource resource = new RatedItemResource();
        if (item != null) {

            resource.setItemRef("urn:items:" + item.getId());
            resource.setPlaceId(item.getPlaceId());
            if (!item.getCategories().isEmpty()) {
                Set<String> categories = new LinkedHashSet<>(item.getCategories().size());
                for (Category itemCategory : item.getCategories()) {
                    categories.add(itemCategory.getName());
                }
                resource.setCategories(categories);
            }

            resource.setMyRating(personService.findMyRating(person, item));
            resource.setNoOfGreenRatingByFriends(personService.findMyFriendsRating(person, item, "green"));
            resource.setNoOfNeutralRatingByFriends(personService.findMyFriendsRating(person, item, "neutral"));
            resource.setNoOfRedRatingByFriends(personService.findMyFriendsRating(person, item, "red"));
            resource.setTotalGreenRating(item.getTotalGreen());
            resource.setTotalNeutralRating(item.getTotalNeutral());
            resource.setTotalRedRating(item.getTotalRed());
        }

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItem(item.getId()))
                .withSelfRel());

        return resource;
    }
}
