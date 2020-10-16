package com.pricer.test.menuparser;

import com.pricer.test.menuparser.model.ParserType;
import com.pricer.test.menuparser.model.SortOrder;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationParamTest {

    @Test
    public void shouldThrowExceptionIfMenuPathIsNotPassedAsArgument() {

        assertThatThrownBy( () -> ApplicationParam.tokenizeArguments(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Menu file should be passed as parameter");

    }

    @Test
    public void shouldThrowExceptionIfMenuPathIsNotThere() {

        assertThatThrownBy( () -> ApplicationParam.tokenizeArguments(new String[] {"not/existed/path"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File is not exist");

    }

    @Test
    public void shouldThrowExceptionIfFileTypeNotSupported() {
        var pathToFile = Path.of("src", "test", "resources").toFile().getAbsolutePath();
        assertThatThrownBy( () -> ApplicationParam.tokenizeArguments(new String[] {pathToFile + "/menu.wrong-file-type"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File extension not supported");

    }

    @Test
    public void shouldReadArgumentsCorrectly() {
        var pathToFile = Path.of("src", "test", "resources","empty-menu.json");

        var applicationParam  = ApplicationParam.tokenizeArguments(new String[] {pathToFile.toFile().getAbsolutePath(), "DESC"});

        assertThat(applicationParam).isNotNull();
        assertThat(applicationParam.menuPath().toFile().getAbsolutePath()).isEqualTo(pathToFile.toFile().getAbsolutePath());
        assertThat(applicationParam.format()).isEqualTo(ParserType.JSON);
        assertThat(applicationParam.sortAs()).isEqualTo(SortOrder.DESC);
    }


    @Test
    public void shouldDefaultSortingToAscIfNotCorrectlyPassed() {
        var pathToFile = Path.of("src", "test", "resources","empty-menu.json");

        var applicationParam  = ApplicationParam.tokenizeArguments(new String[] {pathToFile.toFile().getAbsolutePath()});

        assertThat(applicationParam.sortAs()).isEqualTo(SortOrder.ASC);
    }

}