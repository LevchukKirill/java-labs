package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.CourseInfo;
import lab2.model.CourseInstance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

/**
 * Класс для чтения информации о курсах из файлов
 */
public class CourseInfoDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Считывание зависимостей для данного экземпляра класса
     */
    public CourseInfoDataReader() {
        objectMapper.findAndRegisterModules();
    }

    /**
     * @return список описаний курсов
     */
    public ArrayList<CourseInfo> readCourseInfoData() throws IOException {
        CourseInfo[] ans = objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class);
        Stream.of(ans)
                .filter(courseInfo -> courseInfo.getPrerequisites() == null)
                .forEach(courseInfo -> courseInfo.setPrerequisites(new ArrayList<Long>()));
        return new ArrayList<CourseInfo>(Arrays.asList(ans));
    }

    /**
     * @return список проведений курсов
     */
    public ArrayList<CourseInstance> readCourseInstanceData() throws IOException {
        CourseInstance[] ans = objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class);
        return new ArrayList<CourseInstance>(Arrays.asList(ans));
    }
}