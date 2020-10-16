package com.pricer.test.menuparser.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pricer.test.menuparser.model.BreakfastMenu;
import com.pricer.test.menuparser.model.Food;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MenuXmlParser implements MenuParser {
    @Override
    public List<Food> parse(Path menuPath) {
        XmlMapper mapper = XmlMapper.builder().defaultUseWrapper(false).build();
        try {
            var menu = mapper.readValue(menuPath.toFile(), BreakfastMenu.class);
            return Arrays.asList(menu.getFoods());
        } catch (IOException e) {
            log.error("Failed to parse XML menu file", e);
            throw new IllegalStateException();
        }
    }
}
