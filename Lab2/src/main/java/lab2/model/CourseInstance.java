package lab2.model;

import java.time.LocalDate;

/**
 * Класс с данными о проведении курса. Один курс (например, дискретная математика) может быть проведен несколько раз.
 */
public class CourseInstance {
    
    /**
     * Идентификатор проведения курса
     */
    private long id;

    /**
     * Идентификатор курса, соответствующий CourseInfo.id
     */
    private long courseInfoId;

    /**
     * Идентификатор преподавателя
     */
    private long instructorId;

    /**
     * Дата начала курса
     */
    private LocalDate startDate;

    /**
     * Ограничение на число студентов курса
     */
    private int capacity;

    // геттеры и сеттеры

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseInfoId() {
        return this.courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public long getInstructorId() {
        return this.instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}