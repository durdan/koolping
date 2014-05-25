package com.oceantech.koolping.api.resource;

import com.oceantech.koolping.api.support.Query;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Describes a generic item collection resource for the KOOLPING domain.
 *
 * @param <T> the generic type of the underlying items
 * @author Sanjoy Roy
 */
public class ResourceCollection<T> extends Resources<T> {

    private Map<String, Query> queries = new LinkedHashMap<>();
    private long totalItems;
    private int totalPages;
    private int returnedItems;
    private int pageSize;

    public ResourceCollection() {
    }

    public ResourceCollection(Iterable<T> content, Link... links) {
        super(content, links);
    }

    public Map<String, Query> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, Query> queries) {
        this.queries = queries;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getReturnedItems() {
        return returnedItems;
    }

    public void setReturnedItems(int returnedItems) {
        this.returnedItems = returnedItems;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
