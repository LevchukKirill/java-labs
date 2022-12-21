package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Instructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для чтения информации об инструкторах из файлов
 */
public class InstructorDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список инструкторов
     */
    public ArrayList<Instructor> readInstructorData() throws IOException {
        Instructor[] ans = objectMapper.readValue(new File("src/main/resources/instructors.json"), Instructor[].class);
        return new ArrayList<Instructor>(Arrays.asList(ans));
    }
}