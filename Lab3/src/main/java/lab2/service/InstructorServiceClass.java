package lab2.service;

import lab2.model.Instructor;
import lab2.model.Student;

import java.util.ArrayList;

/**
 * Реализация интерфейса сервиса для преподавателя
 */
public class InstructorServiceClass implements InstructorService {

    private SubscriptionManager subscriptionManager;

    public InstructorServiceClass(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    /**
     * @param courseInstanceId идентификатор запланированного курса
     * @return список студентов, зарегистрированных на данный курс
     */
    @Override
    public ArrayList<Student> findStudentsByCourseInstanceId(long courseInstanceId) {
        return this.subscriptionManager.findStudentsByCourseInstanceId(courseInstanceId);
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    @Override
    public ArrayList<Student> findStudentsByInstructorId(long instructorId) {
        return this.subscriptionManager.findStudentsByInstructorId(instructorId);
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseInstanceId идентификатор заплаинрованного курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    @Override
    public ArrayList<Instructor> findReplacement(long instructorId, long courseInstanceId) {
        return this.subscriptionManager.findReplacement(instructorId, courseInstanceId);
    }

}
