package wang.lonelymoon.javastudy.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * java 8 时间默认序列化
 *
 * @author L.cm
 */
public class JavaTimeModule extends SimpleModule {

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_MINI = "yyyyMMddHHmmss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";

    public static final JavaTimeModule INSTANCE = new JavaTimeModule();
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(PATTERN_DATETIME);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern(PATTERN_TIME);

    public JavaTimeModule() {
        super(PackageVersion.VERSION);
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMAT));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMAT));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMAT));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMAT));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMAT));
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMAT));
    }

}
