package cn.yourbatman.validation.constraintvalidatorcontext;

import cn.yourbatman.validation.ValidatorUtil;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) throws NoSuchMethodException {
        User rootBean = new User();
        Method method = rootBean.getClass().getMethod("mergeAddresses", Map.class, Map.class);
        Object[] paramters = {new HashMap<>(), new HashMap<>()};

        ValidatorUtil.obtainExecutableValidator().validateParameters(rootBean, method, paramters)
                .stream().forEach(v -> System.out.println("导航路径：" + v.getPropertyPath()));
    }

}


@Data
class User {
    private Map<String, Address> addresses;

    @MyParameterAnno
    public void createUser(String password, String passwordRepeat) {
    }

    @MyParameterAnno
    public void mergeAddresses(Map<String, Address> addresses, Map<String, Address> otherAddresses) {
    }
}

@Data
class Address {
    private String street;
    private Country country;
}

@Data
class Country {
    private String name;
}