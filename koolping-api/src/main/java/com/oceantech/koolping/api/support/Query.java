package com.oceantech.koolping.api.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a query template
 *
 * @author Sanjoy Roy
 */
public class Query {
    private String href;
    private String display;
    private List<Field> fields = new ArrayList<>();

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }


    public Query addField(Field field){
        this.fields.add(field);
        return this;
    }

    public Query removeField(String name){
        this.fields.remove(getFirstField(name));
        return this;
    }

    public Field getFirstField(String name){
        for(Field field : fields){
            if(name.equals(field.getName())){
                return field;
            }
        }
        throw new IllegalArgumentException("No such field is found: "+name);
    }

    public void clearFields(){
        this.fields.clear();
    }

    public static class Field {
        private String name;
        private Object value;
        private String display;
        private boolean optional;

        public Field(String name) {
            this.name = name;
        }

        public Field(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public Field(String name, Object value, String display) {
            this.name = name;
            this.value = value;
            this.display = display;
        }

        public Field(String name, Object value, String display, boolean optional) {
            this.name = name;
            this.value = value;
            this.display = display;
            this.optional = optional;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public boolean isOptional() {
            return optional;
        }

        public void setOptional(boolean optional) {
            this.optional = optional;
        }
    }
}
