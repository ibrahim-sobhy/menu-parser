package com.pricer.test.menuparser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@Slf4j
@SpringBootApplication
public class MenuParserApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MenuParserApplication.class, args);
    }

    PrintStream out = System.out;

    @Override
    public void run(String... args) {
        var applicationParam = ApplicationParam.tokenizeArguments(args);
        log.info("{}", applicationParam);

        var sortedFoods = new MenuSorter().
                sort(applicationParam.format().parser.get(), applicationParam.menuPath(), applicationParam.sortAs());
        sortedFoods.forEach(out::println);
    }

    public void setOut(PrintStream outStream) {
        this.out = outStream;
    }
}
