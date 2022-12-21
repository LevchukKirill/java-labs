package lab2.model;

import java.util.ArrayList;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    private ArrayList<Long> taughtCourseInfoIds;

    public ArrayList<Long> getTaughtCourseInfoIds() {
        return this.taughtCourseInfoIds;
    }

    public void setTaughtCourseInfoIds(ArrayList<Long> taughtCourseInfoIds) {
        this.taughtCourseInfoIds = taughtCourseInfoIds;
    }
    
}
