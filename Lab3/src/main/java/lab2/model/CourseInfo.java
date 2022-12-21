package lab2.model;

import java.util.ArrayList;

/**
 * Класс для базовой информации о курсе
 */
public class CourseInfo {

    /**
     * Идентификатор курса
     */
    private long id;

    /**
     * Название курса
     */
    private String name;

    /**
     * Краткое описание курса
     */
    private String description;

    /**
     * Список идентификаторов курсов, которые нужно обязательно пройти до начала данного курса
     */
    private ArrayList<Long> prerequisites;

    /**
     * Список категорий студентов, которые могут посещать курс
     */
    private ArrayList<StudentCategory> studentCategories;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Long> getPrerequisites() {
        return this.prerequisites;
    }

    public void setPrerequisites(ArrayList<Long> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<StudentCategory> getStudentCategories() {
        return this.studentCategories;
    }

    public void setStudentCategories(ArrayList<StudentCategory> studentCategories) {
        this.studentCategories = studentCategories;
    }

}