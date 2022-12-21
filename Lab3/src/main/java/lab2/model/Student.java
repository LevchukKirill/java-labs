package lab2.model;

import java.util.ArrayList;

/**
 * Класс для информации о студенте
 */
public class Student extends Person {

    /**
     * Идентификаторы датированных курсов, пройденных студентом
     */
    private ArrayList<Long> completedCourseInstanceIds;

    /**
     * Категория студента (бакалавр/магистр)
     */
    private StudentCategory category;

    // геттеры и сеттеры

    public ArrayList<Long> getCompletedCourseInstanceIds() {
        return this.completedCourseInstanceIds;
    }

    public void setCompletedCourseInstanceIds(ArrayList<Long> completedCourseInstanceIds) {
        this.completedCourseInstanceIds = completedCourseInstanceIds;
    }

    public StudentCategory getCategory() {
        return this.category;
    }

    public void setCategory(StudentCategory category) {
        this.category = category;
    }
    
}
