package wang.lonelymoon.javastudy.OptionalTest;

import lombok.extern.slf4j.Slf4j;
import wang.lonelymoon.javastudy.OptionalTest.SexEnum;

import java.util.Optional;

/**
 * @author lonelymoon
 */
@Slf4j
public class OptionalTestApp {


    public static void main(String[] args) {
        Student student = null;
//        Student student = new Student();
//        student.setId(0L);
//        student.setUsername("六");
//        student.setAge(20);
//        student.setSexEnum(SexEnum.MAN);
        log.info("student:{}", student);
        String sex = getSex(student);
        log.info("sex:{}", sex);
        String sexByOptional = getSexByOptional(student);
        log.info("sexByOptional:{}", sexByOptional);

    }


    public static String getSex(Student student) {
        if (null == student) {
            return SexEnum.UNKNOWN.getDesc();
        }
        return student.getSexEnum().getDesc();
    }

    public static String getSexByOptional(Student student) {
        return Optional.ofNullable(student).map(u -> u.getSexEnum().getDesc()).orElse(SexEnum.UNKNOWN.getDesc());
    }
}
