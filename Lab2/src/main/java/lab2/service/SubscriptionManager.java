package lab2.service;

import lab2.model.*;
import lab2.reader.*;
import lab2.model.Subscription;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
            this.studentArray.addAll(Arrays.asList(this.studentDataReader.readBachelorStudentData()));
            this.studentArray.addAll(Arrays.asList(this.studentDataReader.readMasterStudentData()));
            // fill in instructors
            this.instructorArray.addAll(Arrays.asList(this.instructorDataReader.readInstructorData()));
            // fill in courses
            this.courseInfoArray.addAll(Arrays.asList(this.courseInfoDataReader.readCourseInfoData()));
            this.courseInstanceArray.addAll(Arrays.asList(courseInfoDataReader.readCourseInstanceData()));
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
        boolean courseInstanceIsAvailableForStudentCategory = false;
        for (StudentCategory allowableStudentCategory : courseInfo.getStudentCategories()) {
            if (studentCategory == allowableStudentCategory) {
                courseInstanceIsAvailableForStudentCategory = true;
                break;
            }
        }
        if (!courseInstanceIsAvailableForStudentCategory) {
            return ActionStatus.NOK;
        }

        // - студент прошел все обязательные курсы, необходимые для посещения данного курса -
        boolean allRequiredCoursesAreCompleted = true;
        for (long requiredCourseId : courseInfo.getPrerequisites()) {
            allRequiredCoursesAreCompleted = false;
            for (long completedCourseInstanceId : student.getCompletedCourseInstanceIds()) {
                CourseInstance completedCourseInstance = getCourseInstanceById(completedCourseInstanceId);
                if (completedCourseInstance.getCourseInfoId() == requiredCourseId) {
                    allRequiredCoursesAreCompleted = true;
                }
            }
            if (!allRequiredCoursesAreCompleted) {
                break;
            }
        }
        if (!allRequiredCoursesAreCompleted) {
            return ActionStatus.NOK;
        }

        // - на курсе есть свободные места -
        // - нет записи, уже осуществлённой этим же студентом на этот же запланированный курс -
        long subscriptionsAccountForCourseInstance = 0;
        boolean subscriptionDoesNotYetExist = true;
        for (Subscription subscription : this.subscriptions) {
            if (subscription.getCourseInstanceId() == courseInstanceId) {
                ++subscriptionsAccountForCourseInstance;
                if (subscription.getStudentId() == studentId) {
                    subscriptionDoesNotYetExist = false;
                    break;
                }
            }
        }
        if (subscriptionsAccountForCourseInstance > courseInstance.getCapacity()) {
            return ActionStatus.NOK;
        }
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
        int subscriptionIndex;
        for (subscriptionIndex = 0; subscriptionIndex < this.subscriptions.size(); ++subscriptionIndex) {
            Subscription subscription = this.subscriptions.get(subscriptionIndex);
            if (subscription.getStudentId() == studentId &&
                subscription.getCourseInstanceId() == courseInstanceId) {
                break;
            }
        }
        if (subscriptionIndex == this.subscriptions.size()) {
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
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        ArrayList<CourseInstance> ansArrayList = new ArrayList<>();
        for (Subscription subscription : this.subscriptions) {
            if (subscription.getStudentId() == studentId) {
                ansArrayList.add(getCourseInstanceById(subscription.getCourseInstanceId()));
            }
        }
        CourseInstance[] ans = new CourseInstance[ansArrayList.size()];
        ans = ansArrayList.toArray(ans);
        return ans;
    }

    /**
     * @param courseInstanceId идентификатор запланированного курса
     * @return список студентов, зарегистрированных на данный курс
     */
    public Student[] findStudentsByCourseInstanceId(long courseInstanceId) {
        ArrayList<Student> ansArrayList = new ArrayList<>();
        for (Subscription subscription : this.subscriptions) {
            if (subscription.getCourseInstanceId() == courseInstanceId) {
                ansArrayList.add(getStudentById(subscription.getStudentId()));
            }
        }
        Student[] ans = new Student[ansArrayList.size()];
        ans = ansArrayList.toArray(ans);
        return ans;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    public Student[] findStudentsByInstructorId(long instructorId) {
        ArrayList<Student> ansArrayList = new ArrayList<>();
        for (Subscription subscription : this.subscriptions) {
            CourseInstance courseInstance = getCourseInstanceById(subscription.getCourseInstanceId());
            if (courseInstance.getInstructorId() == instructorId) {
                ansArrayList.add(getStudentById(subscription.getStudentId()));
            }
        }
        Student[] ans = new Student[ansArrayList.size()];
        ans = ansArrayList.toArray(ans);
        return ans;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseInstanceId идентификатор заплаинрованного курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    public Instructor[] findReplacement(long instructorId, long courseInstanceId) {
        ArrayList<Instructor> ansArrayList = new ArrayList<>();
        CourseInstance courseInstance = getCourseInstanceById(courseInstanceId);
        CourseInfo courseInfo = getCourseInfoById(courseInstance.getCourseInfoId());
        long courseInfoId = courseInfo.getId();
        for (Instructor instructor : this.instructorArray) {
            for (long taughtCourseInfoId : instructor.getTaughtCourseInfoIds()) {
                if (taughtCourseInfoId == courseInfoId) {
                    ansArrayList.add(instructor);
                    break;
                }
            }
        }
        Instructor[] ans = new Instructor[ansArrayList.size()];
        ans = ansArrayList.toArray(ans);
        return ans;
    }

    /**
     * Получение студента по id
     */
    private Student getStudentById(long studentId) {
        for (Student student : this.studentArray) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    /**
     * Получение преподавателя по id
     */
    private Instructor getInstructorById(long instructorId) {
        for (Instructor instructor : this.instructorArray) {
            if (instructor.getId() == instructorId) {
                return instructor;
            }
        }
        return null;
    }

    /**
     * Получение курса по id
     */
    private CourseInfo getCourseInfoById(long courseInfoId) {
        for (CourseInfo courseInfo : this.courseInfoArray) {
            if (courseInfo.getId() == courseInfoId) {
                return courseInfo;
            }
        }
        return null;
    }

    /**
     * Получение запланированного курса по id
     */
    private CourseInstance getCourseInstanceById(long courseInstanceId) {
        for (CourseInstance courseInstance : this.courseInstanceArray) {
            if (courseInstance.getId() == courseInstanceId) {
                return courseInstance;
            }
        }
        return null;
    }

}
