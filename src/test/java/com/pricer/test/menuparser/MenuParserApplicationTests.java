package com.pricer.test.menuparser;

import com.pricer.test.menuparser.model.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Arrays;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class MenuParserApplicationTests {

	MenuParserApplication menuParserApplication = new MenuParserApplication();

	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@BeforeEach
	public void setup() {
		menuParserApplication.setOut(new PrintStream(outputStream));
	}

	@Test
	public void shouldPrintOnStdOutMenuSortedAsc() {
		var pathToFile = Path.of("src", "test", "resources","menu.json");
		var menuItems = Arrays.asList(
				MenuItem.builder().name("Belgian Waffles").price("$5.95").calories(650)
						.description("Two of our famous Belgian Waffles with plenty of real maple syrup").build(),
				MenuItem.builder().name("Strawberry Belgian Waffles").price("$7.95").calories(900)
						.description("Light Belgian waffles covered with strawberries and whipped cream").build(),
				MenuItem.builder().name("Berry-Berry Belgian Waffles").price("$8.95").calories(900)
						.description("Light Belgian waffles covered with an assortment of fresh berries and whipped cream").build(),
				MenuItem.builder().name("French Toast").price("$4.50").calories(600)
						.description("Thick slices made from our homemade sourdough bread").build(),
				MenuItem.builder().name("Homestyle Breakfast").price("$6.95").calories(950)
						.description("Two eggs, bacon or sausage, toast, and our ever-popular hash browns").build()
		);
		menuParserApplication.run(pathToFile.toFile().getAbsolutePath());
		assertThat(outputStream.toString()).isEqualTo(
				menuItems.stream()
						.sorted(comparing(MenuItem::getName))
						.map(MenuItem::toString)
						.reduce( (f1,f2) -> f1 + "\r\n" + f2 ).get().concat("\r\n")
				);

	}

	@Test
	public void shouldPrintOnStdOutMenuSortedDesc() {
		var pathToFile = Path.of("src", "test", "resources","menu.json");
		var menuItems = Arrays.asList(
				MenuItem.builder().name("Belgian Waffles").price("$5.95").calories(650)
						.description("Two of our famous Belgian Waffles with plenty of real maple syrup").build(),
				MenuItem.builder().name("Strawberry Belgian Waffles").price("$7.95").calories(900)
						.description("Light Belgian waffles covered with strawberries and whipped cream").build(),
				MenuItem.builder().name("Berry-Berry Belgian Waffles").price("$8.95").calories(900)
						.description("Light Belgian waffles covered with an assortment of fresh berries and whipped cream").build(),
				MenuItem.builder().name("French Toast").price("$4.50").calories(600)
						.description("Thick slices made from our homemade sourdough bread").build(),
				MenuItem.builder().name("Homestyle Breakfast").price("$6.95").calories(950)
						.description("Two eggs, bacon or sausage, toast, and our ever-popular hash browns").build()
		);
		menuParserApplication.run(pathToFile.toFile().getAbsolutePath(), "DESC");
		assertThat(outputStream.toString()).isEqualTo(
				menuItems.stream()
						.sorted( (o1, o2) -> o2.getName().compareTo(o1.getName()) )
						.map(MenuItem::toString)
						.reduce( (f1,f2) -> f1 + "\r\n" + f2 ).get().concat("\r\n")
		);

	}


	@Test
	public void shouldFailIfMenuFileNotPassed() {
		assertThatThrownBy( () -> menuParserApplication.run())
				.isInstanceOf(IllegalArgumentException.class);
	}




}
