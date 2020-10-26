package cn.yourbatman.validation.tester;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;

/**
 * 元数据
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/8 10:38
 */
public class MetadataDemo {


    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * 元数据入口
     */
    @Test
    public void test1() {
        BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Room.class);
    }


}
