package lab2.service;

import lab2.model.ActionStatus;
import lab2.model.CourseInstance;

/**
 * Реализация интерфейса сервиса для студента
 */
public class StudentServiceClass implements StudentService {

    private SubscriptionManager subscriptionManager;

    public StudentServiceClass(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

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
    @Override
    public ActionStatus subscribe(long studentId, long courseInstanceId) {
        return this.subscriptionManager.tryToSubscribe(studentId, courseInstanceId);
    }

    /**
     * Отмена регистрации студента на курс, которая возможна только в том случае, когда
     * курс еще не начался.
     *
     * @param studentId идентификатор студента
     * @param courseInstanceId идентификатор запланированного курса
     * @return результат выполнения отмены регистрации
     */
    @Override
    public ActionStatus unsubscribe(long studentId, long courseInstanceId) {
        return this.subscriptionManager.tryToUnsubscribe(studentId, courseInstanceId);
    }

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */
    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        return this.subscriptionManager.findAllSubscriptionsByStudentId(studentId);
    }

}
