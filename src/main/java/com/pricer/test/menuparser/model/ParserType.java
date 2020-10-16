package com.pricer.test.menuparser.model;

import com.pricer.test.menuparser.parser.MenuJsonParser;
import com.pricer.test.menuparser.parser.MenuParser;
import com.pricer.test.menuparser.parser.MenuXmlParser;

import java.util.function.Supplier;

public  enum ParserType {
    JSON(MenuJsonParser::new),
    XML(MenuXmlParser::new);

    public Supplier<MenuParser> parser;

    ParserType(Supplier<MenuParser> parser) {
        this.parser = parser;
    }
}
