package lab2.model;

/**
 * Класс для информации о студенте
 */
public class Student extends Person {

    /**
     * Идентификаторы датированных курсов, пройденных студентом
     */
    private long[] completedCourseInstanceIds;

    /**
     * Категория студента (бакалавр/магистр)
     */
    private StudentCategory category;

    // геттеры и сеттеры

    public long[] getCompletedCourseInstanceIds() {
        return this.completedCourseInstanceIds;
    }

    public void setCompletedCourseInstanceIds(long[] completedCourseInstanceIds) {
        this.completedCourseInstanceIds = completedCourseInstanceIds;
    }

    public StudentCategory getCategory() {
        return this.category;
    }

    public void setCategory(StudentCategory category) {
        this.category = category;
    }
    
}
