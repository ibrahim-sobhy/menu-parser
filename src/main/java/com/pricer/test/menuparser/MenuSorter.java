package com.pricer.test.menuparser;

import com.pricer.test.menuparser.model.MenuItem;
import com.pricer.test.menuparser.model.SortOrder;
import com.pricer.test.menuparser.parser.MenuParser;

import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuSorter {
    public Stream<MenuItem> sort(MenuParser parser, Path menuPath, SortOrder sortOrder) {
        return parser
                .parse(menuPath,
                        foods -> foods.stream()
                                .map(food -> MenuItem.builder()
                                        .name(food.getName())
                                        .price(food.getPrice())
                                        .calories(food.getCalories())
                                        .description(food.getDescription())
                                        .build()
                                )
                                .collect(Collectors.toList()))
                .stream()
                .sorted((first, second) -> {
                    if (sortOrder == SortOrder.ASC) {
                        return first.getName().compareTo(second.getName());
                    } else {
                        return second.getName().compareTo(first.getName());
                    }
                });
    }
}
