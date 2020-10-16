package com.pricer.test.menuparser;

import com.pricer.test.menuparser.model.ParserType;
import com.pricer.test.menuparser.model.SortOrder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ApplicationParam {
    private Path menuPath;
    private String fileFormat;
    private String sortAs;

    private ApplicationParam(String menuFilePath, Optional<String> sorting) {
        this.menuPath = Path.of(menuFilePath);
        if (Files.isReadable(menuPath)) {
            var fileType = Optional.of(menuFilePath)
                    .filter(f -> f.contains(".json") || f.contains(".xml"))
                    .map( f-> f.substring(menuFilePath.lastIndexOf('.') + 1))
                    .orElseThrow( () -> new IllegalArgumentException("File extension not supported"));
            var sortOption = sorting
                    .map(String::toUpperCase)
                    .filter(sortingOption -> Arrays.asList("DESC", "ASC").contains(sortingOption))
                    .orElse("ASC");
            this.fileFormat = fileType;
            this.sortAs = sortOption;
        } else {
            throw new IllegalArgumentException("File is not exist");
        }

    }


    public static ApplicationParam tokenizeArguments(String[] args) {

        if (Objects.isNull(args) || args.length < 1) {
            throw new IllegalArgumentException("Menu file should be passed as parameter");
        }

        String menuFilePath = args[0];
        String sorting = args.length > 1 ? args[1] : null;

        return new ApplicationParam(menuFilePath, Optional.ofNullable(sorting));
    }

    public Path menuPath() {
        return this.menuPath;
    }

    public SortOrder sortAs() {
        return SortOrder.valueOf(this.sortAs);
    }
    public ParserType format() {
        return ParserType.valueOf(this.fileFormat.toUpperCase());
    }


    @Override
    public String toString() {
        return "ApplicationParam{" +
                "menuPath=" + menuPath +
                ", sortAs='" + sortAs + '\'' +
                '}';
    }
}
