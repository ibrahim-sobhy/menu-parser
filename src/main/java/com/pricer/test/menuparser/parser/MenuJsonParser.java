package com.pricer.test.menuparser.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricer.test.menuparser.model.Food;
import com.pricer.test.menuparser.model.Menu;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MenuJsonParser implements MenuParser {

    @Override
    public List<Food> parse(Path menuPath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            var menu = mapper.readValue(menuPath.toFile(), Menu.class);
            return Arrays.asList(menu.getBreakfastMenu().getFoods());
        } catch (IOException e) {
            log.error("Failed to parse JSON menu file", e);
            throw new IllegalStateException();
        }
    }
}
