package team.wuxie.crowdfunding.util.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TGoods;

/**
 * <p>
 * 商品分类验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class GoodsValidator implements Validator {

    public GoodsValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TGoods.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TGoods goodsType = (TGoods) target;
        if (goodsType.getGoodsId() == null) {
            ValidationUtils.rejectIfEmpty(errors, "typeId", null, "goods.typeId_cannot_be_null");
            ValidationUtils.rejectIfEmpty(errors, "goodsName", null, "goods.goodsName_cannot_be_null");
            ValidationUtils.rejectIfEmpty(errors, "statement", null, "goods.statement_cannot_be_null");
        }
    }
}
