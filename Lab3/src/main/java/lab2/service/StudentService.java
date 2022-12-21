package lab2.service;

import lab2.model.ActionStatus;
import lab2.model.CourseInstance;

import java.util.ArrayList;

/**
 * Интерфейс сервиса для студента
 */
public interface StudentService {

    /**
     * Регистрация студента на курс. Регистрация возможна при следующих условиях:
     * - курс еще не начался;
     * - курс предназначен для категории данного студента (магистра/бакалавра);
     * - студент прошел все обязательные курсы, необходимые для посещения данного курса;
     * - в курсе есть свободные места;
     * - этот студент ещё не зарегистрирован на этот курс.
     *
     * @param studentId идентификатор студента
     * @param courseInstanceId идентификатор запланированного курса
     * @return результат выполнения регистрации
     */
    ActionStatus subscribe(long studentId, long courseInstanceId);

    /**
     * Отмена регистрации студента на курс, которая возможна только в том случае, когда
     * курс еще не начался.
     *
     * @param studentId идентификатор студента
     * @param courseInstanceId идентификатор запланированного курса
     * @return результат выполнения отмены регистрации
     */
    ActionStatus unsubscribe(long studentId, long courseInstanceId);

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */
    ArrayList<CourseInstance> findAllSubscriptionsByStudentId(long studentId);
    
}
