package cn.yourbatman.validation.use.container;

import cn.yourbatman.validation.ValidatorUtil;

import javax.validation.Validator;
import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/10/25 10:01
 * @see Result
 */
public class ResultValueExtractor implements ValueExtractor<Result<@ExtractedValue ?>> {

    public static void main(String[] args) {
        Room room = new Room();
        room.name = "YourBatman";
        Result<Room> result = new Result<>();
        result.setData(room);

        // 把Result作为属性放进去
        ResultDemo resultDemo = new ResultDemo();
        resultDemo.roomResult = result;

        // 注册自定义的值提取器
        Validator validator = ValidatorUtil.obtainValidatorFactory()
                .usingContext()
                .addValueExtractor(new ResultValueExtractor())
                .getValidator();
        ValidatorUtil.printViolations(validator.validate(resultDemo));
    }

    @Override
    public void extractValues(Result<?> originalValue, ValueReceiver receiver) {
        receiver.value(null, originalValue.getData());
    }

}