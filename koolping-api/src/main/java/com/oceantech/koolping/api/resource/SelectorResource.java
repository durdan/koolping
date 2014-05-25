package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collections;
import java.util.List;

/**
 * @author Sanjoy Roy
 */
public class SelectorResource extends ResourceSupport {
    List<ChoiceResource> choiceResources = Collections.EMPTY_LIST;

    public List<ChoiceResource> getChoiceResources() {
        return choiceResources;
    }

    public void setChoiceResources(List<ChoiceResource> choiceResources) {
        this.choiceResources = choiceResources;
    }
}
