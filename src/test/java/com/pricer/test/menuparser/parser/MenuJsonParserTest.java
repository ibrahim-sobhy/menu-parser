package com.pricer.test.menuparser.parser;

import com.pricer.test.menuparser.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuJsonParserTest {

    MenuJsonParser menuJsonParser;

    @BeforeEach
    public void setup() {
        menuJsonParser = new MenuJsonParser();
    }

    @Test
    public void shouldFailParsingIfFileIsMalformed() {
        var pathToFile = Path.of("src", "test", "resources","empty-menu.json");

        assertThatThrownBy( () -> menuJsonParser.parse(pathToFile) )
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    public void shouldParseJsonFile() {
        var expectedFood = Arrays.asList(
                Food.builder().name("Belgian Waffles").price("$5.95").calories(650)
                        .description("Two of our famous Belgian Waffles with plenty of real maple syrup").build(),
                Food.builder().name("Strawberry Belgian Waffles").price("$7.95").calories(900)
                        .description("Light Belgian waffles covered with strawberries and whipped cream").build(),
                Food.builder().name("Berry-Berry Belgian Waffles").price("$8.95").calories(900)
                        .description("Light Belgian waffles covered with an assortment of fresh berries and whipped cream").build(),
                Food.builder().name("French Toast").price("$4.50").calories(600)
                        .description("Thick slices made from our homemade sourdough bread").build(),
                Food.builder().name("Homestyle Breakfast").price("$6.95").calories(950)
                        .description("Two eggs, bacon or sausage, toast, and our ever-popular hash browns").build()
        );

        var pathToFile = Path.of("src", "test", "resources","menu.json");

        var foods = menuJsonParser.parse(pathToFile);

        assertThat(foods).contains(expectedFood.toArray(new Food[foods.size()]));
    }

}