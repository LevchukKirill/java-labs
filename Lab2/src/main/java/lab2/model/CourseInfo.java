package lab2.model;

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
    private long[] prerequisites;

    /**
     * Список категорий студентов, которые могут посещать курс
     */
    private StudentCategory[] studentCategories;

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

    public long[] getPrerequisites() {
        return this.prerequisites;
    }

    public void setPrerequisites(long[] prerequisites) {
        this.prerequisites = prerequisites;
    }

    public StudentCategory[] getStudentCategories() {
        return this.studentCategories;
    }

    public void setStudentCategories(StudentCategory[] studentCategories) {
        this.studentCategories = studentCategories;
    }

}