package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.CourseInfo;
import lab2.model.CourseInstance;

import java.io.File;
import java.io.IOException;

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
    public CourseInfo[] readCourseInfoData() throws IOException {
        CourseInfo[] ans = objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class);
        for (int i = 0; i < ans.length; ++i) {
            if (ans[i].getPrerequisites() == null) {
                long[] empty = {};
                ans[i].setPrerequisites(empty);
            }
        }
        return ans;
    }

    /**
     * @return список проведений курсов
     */
    public CourseInstance[] readCourseInstanceData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class);
    }
}
