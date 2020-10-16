package com.pricer.test.menuparser;

import com.pricer.test.menuparser.model.MenuItem;
import com.pricer.test.menuparser.model.SortOrder;
import com.pricer.test.menuparser.parser.MenuParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuSorterTest {

    private MenuSorter menuSorter = new MenuSorter();
    @Mock
    private MenuParser parser;

    @Test
    public void shouldSortAscByFoodName() {
        var menuItems =  List.of(
                MenuItem.builder().name("E").price("$1").calories(1).description("Describe E").build(),
                MenuItem.builder().name("B").price("$1").calories(1).description("Describe B").build(),
                MenuItem.builder().name("C").price("$1").calories(1).description("Describe C").build()
        );
        when(parser.parse(any(),any())).thenReturn(menuItems);
        var sortedItems = menuSorter.sort(parser, null, SortOrder.ASC);
        assertThat(sortedItems).isSortedAccordingTo(Comparator.comparing(MenuItem::getName));
    }

    @Test
    public void shouldSortDescByFoodName() {
        var menuItems =  List.of(
                MenuItem.builder().name("E").price("$1").calories(1).description("Describe E").build(),
                MenuItem.builder().name("B").price("$1").calories(1).description("Describe B").build(),
                MenuItem.builder().name("C").price("$1").calories(1).description("Describe C").build()
        );
        when(parser.parse(any(),any())).thenReturn(menuItems);
        var sortedItems = menuSorter.sort(parser, null, SortOrder.DESC);
        assertThat(sortedItems).isSortedAccordingTo( (o1, o2) -> o2.getName().compareTo(o1.getName()));
    }

}