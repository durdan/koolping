package com.oceantech.koolping.api.resource;

import org.junit.Test;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class SelectorResourceTest {
    @Test
    public void shouldConstructSelectorResource() {
        ChoiceResource choiceResource = new ChoiceResource();
        choiceResource.setName("choice");

        SelectorResource selectorResource = new SelectorResource();
        selectorResource.setChoiceResources(Arrays.asList(choiceResource));

        assertThat(selectorResource).isNotNull();
        assertThat(selectorResource.getChoiceResources().size()).isEqualTo(1);
        assertThat(selectorResource.getChoiceResources().get(0).getName()).isEqualTo("choice");
    }
}
