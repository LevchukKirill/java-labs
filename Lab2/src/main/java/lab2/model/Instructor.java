package lab2.model;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    private long[] taughtCourseInfoIds;

    public long[] getTaughtCourseInfoIds() {
        return this.taughtCourseInfoIds;
    }

    public void setTaughtCourseInfoIds(long[] taughtCourseInfoIds) {
        this.taughtCourseInfoIds = taughtCourseInfoIds;
    }
    
}
