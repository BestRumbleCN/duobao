package team.wuxie.crowdfunding.util.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TGoodsType;

/**
 * <p>
 * 商品分类验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class GoodsTypeValidator implements Validator {

    public GoodsTypeValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TGoodsType.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TGoodsType goodsType = (TGoodsType) target;
        if (goodsType.getTypeId() == null) {
            ValidationUtils.rejectIfEmpty(errors, "typeName", null, "goodsType.typeName_cannot_be_null");
        }
    }
}
