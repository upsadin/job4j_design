package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
        @Test
    void unavailableTest(@TempDir Path tempDir) {
            File source = new File("data/server.log");
            File target = tempDir.resolve("targetTemp.csv").toFile();
            Analysis analysis = new Analysis();
            String expected = "10:58:01;10:59:01;"
            + System.lineSeparator()
            + "11:01:02;11:02:02;";
            analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

            StringJoiner rsl = new StringJoiner(System.lineSeparator());
            try (BufferedReader in = new BufferedReader(new FileReader(target))) {
                in.lines()
                        .forEach(rsl::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertThat(expected).isEqualTo(rsl.toString());
        }
}