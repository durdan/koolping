package com.oceantech.koolping.api;

/**
 * Defines media type identifier and application protocol elements for the Hypermedia API
 *
 * @author Sanjoy Roy
 */
public abstract class ApplicationProtocol {

    private ApplicationProtocol() {
    }

    public static final String RELS = "rels";
    public static final String PERSONS = "persons";
    public static final String PERSON = "person";
    public static final String PERSON_FORM = "person-form";
    public static final String CREATE_PERSON = "create-person";
    public static final String DELETE_PERSON = "delete-person";
    public static final String FRIENDS = "friends";
    public static final String FRIEND_OF = "friend-of";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";
    public static final String ITEM_FORM = "item-form";
    public static final String CREATE_ITEM = "create-item";
    public static final String DELETE_ITEM = "delete-item";
    public static final String CATEGORIES = "categories";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_FORM = "category-form";
    public static final String CREATE_CATEGORY = "create-category";
    public static final String DELETE_CATEGORY = "delete-category";

    public static final String ADD_CATEGORY = "add-category";

    public static final String SELECT_ACTION = "select-action";
    public static final String UNSELECT_ACTION = "unselect-action";

    public static final String RATE_ITEM = "rate-item";
    public static final String ONE_STAR = "one-star";
    public static final String TWO_STAR = "two-star";
    public static final String THREE_STAR = "three-star";
    public static final String FOUR_STAR = "four-star";
    public static final String FIVE_STAR = "five-star";
}
