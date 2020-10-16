package com.pricer.test.menuparser.parser;

import com.pricer.test.menuparser.model.Food;
import com.pricer.test.menuparser.model.MenuItem;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public interface MenuParser {
    default List<MenuItem> parse(Path menuPath, Function<List<Food>, List<MenuItem>> toMenuItems) {
        return toMenuItems.apply(parse(menuPath));
    }

    List<Food> parse(Path menuPath);
}
