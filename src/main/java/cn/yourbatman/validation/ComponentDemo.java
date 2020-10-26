package cn.yourbatman.validation;

import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;
import org.hibernate.validator.internal.engine.ValidatorContextImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.junit.jupiter.api.Test;

import javax.validation.MessageInterpolator;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.ValidatorFactory;

public class ComponentDemo {

    @Test
    public void test1() {
        ValidatorFactory validatorFactory = ValidatorUtil.obtainValidatorFactory();

        MessageInterpolator messageInterpolator = validatorFactory.getMessageInterpolator();
        System.out.println(messageInterpolator.getClass());
    }

    /**
     * 校验器上下文
     */
    @Test
    public void test2() {
        ValidatorFactoryImpl validatorFactory = (ValidatorFactoryImpl) ValidatorUtil.obtainValidatorFactory();
        // 使用默认的Context上下文，并且初始化一个Validator实例
        ValidatorContext validatorContext = new ValidatorContextImpl(validatorFactory)
                .parameterNameProvider(new DefaultParameterNameProvider())
                .clockProvider(DefaultClockProvider.INSTANCE);

        // 通过该上下文，生成校验器实例（调用多次，生成多个实例）
        System.out.println(validatorContext.getValidator());
        System.out.println(validatorContext.getValidator());
    }

    @Test
    public void test3() {
        Validator validator = ValidatorUtil.obtainValidatorFactory().usingContext()
                .parameterNameProvider(new DefaultParameterNameProvider())
                .clockProvider(DefaultClockProvider.INSTANCE)
                .getValidator();
    }

}
