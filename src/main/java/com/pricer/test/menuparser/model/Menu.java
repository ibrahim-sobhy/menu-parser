package com.pricer.test.menuparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Menu {
    @JsonProperty("breakfast_menu")
    @XmlElement(name = "breakfast_menu")
    private BreakfastMenu breakfastMenu;
}
