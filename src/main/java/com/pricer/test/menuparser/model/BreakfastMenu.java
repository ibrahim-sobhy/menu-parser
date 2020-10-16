package com.pricer.test.menuparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "breakfast_menu")
public class BreakfastMenu {
    @JsonProperty("food")
    @XmlElement(name = "food")
    private Food[] foods;
}
