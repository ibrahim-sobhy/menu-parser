package com.pricer.test.menuparser.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Food {
    private String name;
    //TODO should be money type to isolate the format from actual value
    private String price;
    private String description;
    private int calories;
}
