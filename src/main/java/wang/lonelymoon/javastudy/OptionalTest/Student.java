package wang.lonelymoon.javastudy.OptionalTest;

import lombok.Data;
import lombok.ToString;

/**
 * 学生类
 * @author lonelymoon
 */
@Data
@ToString
public class Student {
    private Long id;
    private String username;
    private Integer age;
    private SexEnum sexEnum;
}
