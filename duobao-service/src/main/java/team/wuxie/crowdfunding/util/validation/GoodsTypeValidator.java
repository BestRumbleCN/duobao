package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TGoodsType;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TGoodsType.PROP_TYPE_NAME;

/**
 * <p>
 * 商品分类验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class GoodsTypeValidator implements Validator {

    private final List<String> requiredFields;

    public GoodsTypeValidator() {
        this(ImmutableList.of(PROP_TYPE_NAME));
    }

    public GoodsTypeValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static GoodsTypeValidator validator() {
        return new GoodsTypeValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TGoodsType.class.equals(clazz) || TGoodsType.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TGoodsType goodsType = (TGoodsType) target;
        if (goodsType.getTypeId() == null) {  //添加
            if (checkRequired(PROP_TYPE_NAME)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_TYPE_NAME, "goodsType.typeName_cannot_be_null", "商品分类名称不能为空");
            }
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
