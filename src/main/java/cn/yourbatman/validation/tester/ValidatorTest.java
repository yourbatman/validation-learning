package cn.yourbatman.validation.tester;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 思考题搜集：
 * 1、如何声明式约束自定义容器？如Result<T>。提示：{@link javax.validation.valueextraction.ValueExtractor}
 * 2、如何让@ScriptAssert支持使用SpEL呢？
 * 3、
 */

/**
 * 关于约束继承、覆盖：
 * 1、字段、属性：子类是可以覆盖的，也就是说如果出现了复写，那么就以子类的为准，否则沿用父类的
 * 2、方法约束中的接口、继承问题： 方法的调用者必须满足的前提条件不能在子类型中得到加强，保证方法调用者的后置条件不能在子类型中减弱
 *  也就说方法入参的校验必须<=接口（父类） 实际是不能重新定义，方法返回值必须 大于等于 接口（父类）
 */

/**
 * 1、字段约束：验证引擎将直接访问实例变量，不会调用相关方法。支持任何访问级别，但不支持static
 * 2、属性约束：遵循Java Bean规范。就可以把注解标注在属性上（get/set方法）上，支持is。注意：必须注释该属性的getter方法，
 * 而不是其setter。这样，还可以限制没有设置方法的只读属性。使用 属性访问策略 来访问
 * 注意：不建议字段和get方法上都写上注解，因为会验证两遍
 * 3、容器元素约束：容器包括各种容器包括Iterable，Map，Optional、Observable等等
 * 4、类级别约束：(比如@ScriptAssert只能作用于public的类上，否则_this取不到值)
 * 5、级联验证（验证对象图）：使用@Valid完成（null在级联验证期间，值将被忽略）  嵌套容器元素也支持级联验证哦@NotNull @Valid
 * 6、方法跨参数约束：@ParameterScriptAssert，使用起来和@ScriptAssert差不多，只不过一个是方法，一个是类
 */

/**
 * 分组约束：
 * 1、
 */


/**
 * 声明式Bean校验
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/7 17:13
 */
public class ValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test1() {
        Room rootBean = new Room();
        rootBean.setName("YourBatman");
        rootBean.setLevel(0);

        Set<ConstraintViolation<Room>> violations = validator.validate(rootBean);
        printViolations(violations);


        // 契约式编程
        // @NotNull @Min(1) Integer level = rootBean.getLevel();


        // 防御式编程
        Integer level = rootBean.getLevel();
        if (level == null) {
            throw new IllegalArgumentException("level属性不能为null");
        } else if (level < 1) {
            throw new IllegalArgumentException("level属性的值不能小于1");
        }
    }


    /**
     * 校验容器内元素
     * 自定义容器类型？思考题：比如Result<T>
     */
    @Test
    public void test2() {
        Room rootBean = new Room();
        rootBean.setName("YourBatman");
        rootBean.setLevel(2);
        rootBean.setCourseMap(new HashMap<String, Integer>() {{
            put(null, 2);
            put("OW", 0);
        }});

        Set<ConstraintViolation<Room>> violations = validator.validate(rootBean);
        printViolations(violations);
    }

    /**
     * 类级别约束
     * {@link org.hibernate.validator.constraints.ScriptAssert}
     */
    @Test
    public void test3() {
        Room rootBean = new Room();
        rootBean.setName("YourBatman");
        rootBean.setLevel(2);
        rootBean.setCourseMap(new HashMap<String, Integer>() {{
            put("OW", 1);
        }});
        rootBean.setMaxStuNum(6);
        rootBean.setStudents(new ArrayList<Student>() {{
            add(new Student());
            add(new Student());
            add(new Student());
            add(new Student());
            add(new Student());
            add(new Student());
            add(new Student());
        }});

        Set<ConstraintViolation<Room>> violations = validator.validate(rootBean);
        printViolations(violations);
    }

    private <T> void printViolations(Set<ConstraintViolation<T>> violations) {
        if (violations.isEmpty()) {
            System.out.println("无校验失败项");
        }
        violations.stream().map(v -> v.getPropertyPath() + v.getMessage() + ",非法值为" + v.getInvalidValue()).forEach(System.out::println);
    }

    @Test
    public void test4() {
        OnlineRoom rootBean = new OnlineRoom();

        Set<ConstraintViolation<OnlineRoom>> violations = validator.validate(rootBean);
        printViolations(violations);
    }

    @Test
    public void test5() throws NoSuchMethodException {
        OnlineRoom rootBean = new OnlineRoom();
        Method method = rootBean.getClass().getMethod("takeClass", Number.class);
        Object[] args = new Object[]{20};

        Set<ConstraintViolation<OnlineRoom>> violations = validator.forExecutables().validateParameters(rootBean, method, args);
        printViolations(violations);
    }

    @Test
    public void test6() throws NoSuchMethodException {
        OnlineRoom rootBean = new OnlineRoom();
        Method method = rootBean.getClass().getMethod("takeClass", Number.class);
        CharSequence returnValue = rootBean.takeClass(20);

        Set<ConstraintViolation<OnlineRoom>> violations = validator.forExecutables().validateReturnValue(rootBean, method, null);
        printViolations(violations);
    }

    @Test
    public void test7() throws NoSuchMethodException {
        Room rootBean = new Room();
        Method method = rootBean.getClass().getMethod("load", Integer.class, List.class);
        Object[] args = new Object[]{3, new ArrayList<Student>() {{
            add(new Student());
            add(new Student());
            add(new Student());
            add(new Student());
        }}};

        Set<ConstraintViolation<Room>> violations = validator.forExecutables().validateParameters(rootBean, method, args);
        printViolations(violations);
    }
}


