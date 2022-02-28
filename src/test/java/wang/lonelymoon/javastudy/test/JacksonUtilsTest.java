package wang.lonelymoon.javastudy.test;

import org.junit.Test;
import wang.lonelymoon.javastudy.core.jackson.JsonUtil;
import wang.lonelymoon.javastudy.entity.User;

public class JacksonUtilsTest {


    @Test
    public void testJson() {
        String json = JsonUtil.toJson(new User(1L, "lisi"));
        System.out.println(json);
    }

    @Test
    public void testJson01() {
        User user = JsonUtil.parse("{\"id\":1,\"username\":\"lisi\"}", User.class);
        System.out.println(user);
    }


}
