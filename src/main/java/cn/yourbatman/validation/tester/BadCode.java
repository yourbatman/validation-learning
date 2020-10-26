package cn.yourbatman.validation.tester;

import lombok.Data;

import java.util.stream.IntStream;

/**
 * 垃圾代码
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/8 14:47
 */
public class BadCode {

    public static void main(String[] args) {
        IntStream.iterate(1, n -> n + 1)
                .skip(1)
                .limit(5)
                .filter(value -> true)
                .forEach(System.out::println);
    }
}

class Service {

    private Repository repository;

    void genarate(String zhName, String enName, Integer age) {
        if (zhName == null || zhName.trim().equals("")) {
            throw new IllegalArgumentException("zhName是必须的");
        }
        if (enName == null || enName.trim().equals("")) {
            throw new IllegalArgumentException("enName是必须的");
        }
        if (age == null || age > 0) {
            throw new IllegalArgumentException("age是必须的且为正数");
        }
        User user = new User();
        user.setZhName(zhName);
        user.setEnName(enName);
        user.setAge(age);

        repository.create(user);
    }

}

class Repository {

    void create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user是必须的");
        }
        if (user.getZhName() == null || user.getZhName().trim().equals("")) {
            throw new IllegalArgumentException("zhName是必须的");
        }
        if (user.getEnName() == null || user.getEnName().trim().equals("")) {
            throw new IllegalArgumentException("enName是必须的");
        }
        if (user.getAge() == null || user.getAge() > 0) {
            throw new IllegalArgumentException("age是必须的且为正数");
        }
    }
}

@Data
class User {
    private String zhName;
    private String enName;
    private Integer age;
}


