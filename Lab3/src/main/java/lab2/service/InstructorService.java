package lab2.service;

import lab2.model.Instructor;
import lab2.model.Student;

import java.util.ArrayList;

/**
 * Интерфейс сервиса для преподавателя
 */
public interface InstructorService {
    
    /**
     * @param courseInstanceId идентификатор запланированного курса
     * @return список студентов, зарегистрированных на данный курс
     */
    ArrayList<Student> findStudentsByCourseInstanceId(long courseInstanceId);

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    ArrayList<Student> findStudentsByInstructorId(long instructorId);

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseInstanceId идентификатор заплаинрованного курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    ArrayList<Instructor> findReplacement(long instructorId, long scheduledCourseId);

}