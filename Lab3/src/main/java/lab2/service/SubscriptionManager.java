package lab2.service;

import lab2.model.*;
import lab2.reader.*;
import lab2.model.Subscription;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.*;

public class SubscriptionManager {

    private ArrayList<Subscription> subscriptions;

    private CourseInfoDataReader courseInfoDataReader = new CourseInfoDataReader();
    private InstructorDataReader instructorDataReader = new InstructorDataReader();
    private StudentDataReader studentDataReader = new StudentDataReader();

    private ArrayList<Student> studentArray = new ArrayList<>();
    private ArrayList<Instructor> instructorArray = new ArrayList<>();
    private ArrayList<CourseInfo> courseInfoArray = new ArrayList<>();
    private ArrayList<CourseInstance> courseInstanceArray = new ArrayList<>();

    public SubscriptionManager() {
        try {
            // fill in students
            this.studentArray = this.studentDataReader.readBachelorStudentData();
            this.studentArray = this.studentDataReader.readMasterStudentData();
            // fill in instructors
            this.instructorArray = this.instructorDataReader.readInstructorData();
            // fill in courses
            this.courseInfoArray = this.courseInfoDataReader.readCourseInfoData();
            this.courseInstanceArray = this.courseInfoDataReader.readCourseInstanceData();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.subscriptions = new ArrayList<>();
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
    public ActionStatus tryToSubscribe(long studentId, long courseInstanceId) {
        Student student = getStudentById(studentId);
        CourseInstance courseInstance = getCourseInstanceById(courseInstanceId);
        CourseInfo courseInfo = getCourseInfoById(courseInstance.getCourseInfoId());
        LocalDate subscriptionRequestDate = LocalDate.now();

        // - курс еще не начался -
        boolean courseInstanceNotStartedYet = subscriptionRequestDate.isBefore(courseInstance.getStartDate());
        if (!courseInstanceNotStartedYet) {
            return ActionStatus.NOK;
        }

        // - курс предназначен для категории данного студента -
        StudentCategory studentCategory = student.getCategory();
        boolean courseInstanceIsAvailableForStudentCategory = courseInfo.getStudentCategories().stream()
                .anyMatch(allowableStudentCategory -> allowableStudentCategory == studentCategory);
        if (!courseInstanceIsAvailableForStudentCategory) {
            return ActionStatus.NOK;
        }

        // - студент прошел все обязательные курсы, необходимые для посещения данного курса -
        boolean allRequiredCoursesAreCompleted = true;
        for (long requiredCourseId : courseInfo.getPrerequisites()) {
            allRequiredCoursesAreCompleted = student.getCompletedCourseInstanceIds().stream()
                    .anyMatch(completedCourseInstanceId ->
                            getCourseInstanceById(completedCourseInstanceId).getCourseInfoId() == requiredCourseId);
            if (!allRequiredCoursesAreCompleted) {
                break;
            }
        }
        if (!allRequiredCoursesAreCompleted) {
            return ActionStatus.NOK;
        }

        // - на курсе есть свободные места -
        long subscriptionsAccountForCourseInstance = this.subscriptions.stream()
                .filter(subscription -> subscription.getCourseInstanceId() == courseInstanceId)
                .count();
        if (subscriptionsAccountForCourseInstance > courseInstance.getCapacity()) {
            return ActionStatus.NOK;
        }
        // - нет записи, уже осуществлённой этим же студентом на этот же запланированный курс -
        boolean subscriptionDoesNotYetExist = !this.subscriptions.stream()
                .anyMatch(subscription -> subscription.getCourseInstanceId() == courseInstanceId
                        && subscription.getStudentId() == studentId);
        if (!subscriptionDoesNotYetExist) {
            return ActionStatus.NOK;
        }


        // подписка
        subscriptions.add(new Subscription(studentId, courseInstanceId));
        return ActionStatus.OK;
    }

    /**
     * Отмена регистрации студента на курс, которая возможна только в том случае, когда
     * курс еще не начался, а сама запись о регистрации существует.
     *
     * @param studentId идентификатор студента
     * @param courseInstanceId идентификатор запланированного курса
     * @return результат выполнения отмены регистрации
     */
    public ActionStatus tryToUnsubscribe(long studentId, long courseInstanceId) {
        CourseInstance courseInstance = getCourseInstanceById(courseInstanceId);
        LocalDate unsubscriptionRequestDate = LocalDate.now();

        // - запись о регистрации существует -
        int subscriptionIndex = IntStream.range(0, this.subscriptions.size())
                .filter(i -> this.subscriptions.get(i).getStudentId() == studentId &&
                        this.subscriptions.get(i).getCourseInstanceId() == courseInstanceId)
                .findFirst()
                .orElse(-1);
        if (subscriptionIndex == -1) {
            return ActionStatus.NOK;
        }

        // - курс ещё не начался -
        boolean courseInstanceNotStartedYet = unsubscriptionRequestDate.isBefore(courseInstance.getStartDate());
        if (!courseInstanceNotStartedYet) {
            return ActionStatus.NOK;
        }

        // отписка
        this.subscriptions.remove(subscriptionIndex);
        return ActionStatus.OK;
    }

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */
    public ArrayList<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {
        ArrayList<CourseInstance> ansArrayList = new ArrayList<>();
        this.subscriptions.stream()
                .filter(subscription -> subscription.getStudentId() == studentId)
                .forEach(subscription -> ansArrayList.add(getCourseInstanceById(subscription.getCourseInstanceId())));
        return ansArrayList;
    }


    /**
     * @param courseInstanceId идентификатор запланированного курса
     * @return список студентов, зарегистрированных на данный курс
     */
    public ArrayList<Student> findStudentsByCourseInstanceId(long courseInstanceId) {
        ArrayList<Student> ansArrayList = new ArrayList<>();
        this.subscriptions.stream()
                .filter(subscription -> subscription.getCourseInstanceId() == courseInstanceId)
                .forEach(subscription -> ansArrayList.add(getStudentById(subscription.getStudentId())));
        return ansArrayList;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    public ArrayList<Student> findStudentsByInstructorId(long instructorId) {
        ArrayList<Student> ansArrayList = new ArrayList<>();
        this.subscriptions.stream()
                .filter(subscription -> getCourseInstanceById(subscription.getCourseInstanceId()).getInstructorId() == instructorId)
                .forEach(subscription -> ansArrayList.add(getStudentById(subscription.getStudentId())));
        return ansArrayList;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseInstanceId идентификатор заплаинрованного курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    public ArrayList<Instructor> findReplacement(long instructorId, long courseInstanceId) {
        ArrayList<Instructor> ansArrayList = new ArrayList<>();
        CourseInstance courseInstance = getCourseInstanceById(courseInstanceId);
        CourseInfo courseInfo = getCourseInfoById(courseInstance.getCourseInfoId());
        long courseInfoId = courseInfo.getId();
        for (Instructor instructor : this.instructorArray) {
            instructor.getTaughtCourseInfoIds().stream()
                    .filter(taughtCourseInfoId -> taughtCourseInfoId == courseInfoId)
                    .forEach(taughtCourseInfoId -> ansArrayList.add(instructor));
        }
        return ansArrayList;
    }

    /**
     * Получение студента по id
     */
    private Student getStudentById(long studentId) {
        return this.studentArray.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение преподавателя по id
     */
    private Instructor getInstructorById(long instructorId) {
        return this.instructorArray.stream()
                .filter(instructor -> instructor.getId() == instructorId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение курса по id
     */
    private CourseInfo getCourseInfoById(long courseInfoId) {
        return this.courseInfoArray.stream()
                .filter(courseInfo -> courseInfo.getId() == courseInfoId)
                .findFirst()
                .orElse(null);
    }


    /**
     * Получение запланированного курса по id
     */
    private CourseInstance getCourseInstanceById(long courseInstanceId) {
        return this.courseInstanceArray.stream()
                .filter(courseInstance -> courseInstance.getId() == courseInstanceId)
                .findFirst()
                .orElse(null);
    }

}