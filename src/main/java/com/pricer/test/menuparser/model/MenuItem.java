package com.pricer.test.menuparser.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MenuItem {
    private String name;
    private String price;
    private String description;
    private int calories;
}
