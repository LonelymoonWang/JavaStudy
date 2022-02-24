package wang.lonelymoon.javastudy.OptionalTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import wang.lonelymoon.javastudy.OptionalTest.SexEnum;

import java.util.Optional;

/**
 * @author lonelymoon
 */
@Slf4j
public class OptionalAppTest {


    public static String getSex(Student student) {
        if (null == student) {
            return SexEnum.UNKNOWN.getDesc();
        }
        return student.getSexEnum().getDesc();
    }

    public static String getSexByOptional(Student student) {
        return Optional.ofNullable(student).map(u -> u.getSexEnum().getDesc()).orElse(SexEnum.UNKNOWN.getDesc());
    }

    public static void getUsernameByConsumer(Student student) {
        Optional.ofNullable(student).ifPresent(s -> {
            log.info("name::{}", s.getUsername());
        });
    }

    @Test
    public void testGetAgeByMap() {
        Student student = getStudent();
        Optional<Object> optional = Optional.of(student).map(Student::getAge
        );
        optional.ifPresent(o -> log.info("age:{}", o));
    }

    @Test
    public void testGetAgeByFlatMap() {
        Student student = getStudent();
        Optional<Integer> ageOptional = Optional.of(student).flatMap(s -> Optional.ofNullable(s.getAge()));
        ageOptional.ifPresent(o -> log.info("age:{}", o));
    }

    @Test
    public void testGetAgeByOrElse() {
        Student student = getStudent();
        Integer age = Optional.of(student).map(Student::getAge).orElse(10);
        log.info("age:{}", age);
    }

    @Test
    public void testGetAgeByOrElseGet() {
        Student student = null;
        Integer age = Optional.ofNullable(student).map(Student::getAge).orElseGet(() -> 15);
        log.info("age:{}", age);
    }

    @Test
    public void getObject() {
        Student student = getStudent();
        Student student1 = Optional.of(student).get();
        log.info("student1:{}", student1);
    }

    @Test
    public void testGetAgeByOrElseThrow()
    {
        Student student = null;
        Optional.ofNullable(student).map(Student::getAge).orElseThrow(() -> new RuntimeException("报异常"));
    }

    @Test
    public void testFilterAge() {
//        Student student = getStudent();
        Student student = null;
        Optional.ofNullable(student).filter(u -> u.getAge() > 18).ifPresent(u -> log.info("The student age is more than 18."));
    }

    @Test
    public void testGetIsPresent() {
//        Student student = getStudent();
        Student student = null;
        /**
         * isPresent 判断是否非空
         */
        boolean present = Optional.ofNullable(student).isPresent();
        log.info("isPresent:{}", present);
    }

    @Test
    public void testGetSexByOptional() {
        Student student = getStudent();
        String sex = getSex(student);
        log.info("sex:{}", sex);
        String sexByOptional = getSexByOptional(student);
        log.info("sexByOptional:{}", sexByOptional);
    }

    @Test
    public void testGetUsernameByConsumer() {
        Student student = getStudent();
        String sex = getSex(student);
        log.info("sex:{}", sex);
        getUsernameByConsumer(student);
    }


    private Student getStudent() {
        Student student = new Student();
        student.setId(0L);
        student.setUsername("六");
        student.setAge(20);
        student.setSexEnum(SexEnum.MAN);
        log.info("student:{}", student);
        return student;
    }
}
