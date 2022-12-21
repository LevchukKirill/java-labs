package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Student;
import lab2.model.StudentCategory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public ArrayList<Student> readBachelorStudentData() throws IOException {
        Student[] ans = objectMapper.readValue(new File("src/main/resources/bachelorStudents.json"), Student[].class);
        Stream.of(ans).forEach(student -> student.setCategory(StudentCategory.BACHELOR));
        return new ArrayList<Student>(Arrays.asList(ans));
    }

    /**
     * @return список студентов-магистров
     */
    public ArrayList<Student> readMasterStudentData() throws IOException {
        Student[] ans = objectMapper.readValue(new File("src/main/resources/masterStudents.json"), Student[].class);
        Stream.of(ans).forEach(student -> student.setCategory(StudentCategory.MASTER));
        return new ArrayList<Student>(Arrays.asList(ans));
    }

}
