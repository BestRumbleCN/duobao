package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TGoods;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TGoods.PROP_GOODS_NAME;
import static team.wuxie.crowdfunding.domain.TGoods.PROP_STATEMENT;
import static team.wuxie.crowdfunding.domain.TGoods.PROP_TYPE_ID;

/**
 * <p>
 * 商品验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class GoodsValidator implements Validator {

    private final List<String> requiredFields;

    public GoodsValidator() {
        this(ImmutableList.of(PROP_TYPE_ID, PROP_GOODS_NAME, PROP_STATEMENT));
    }

    public GoodsValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static GoodsValidator validator() {
        return new GoodsValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TGoods.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TGoods goods = (TGoods) target;
        if (goods.getGoodsId() == null) { //添加
            if (checkRequired(PROP_TYPE_ID)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_TYPE_ID, "goods.v.typeId_required", "请填写商品分类");
            }
            if (checkRequired(PROP_GOODS_NAME)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_GOODS_NAME, "goods.v.goodsName_required", "请填写商品名称");
            }
            if (checkRequired(PROP_STATEMENT)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_STATEMENT, "goods.v.statement_required", "请填写商品夺宝申明");
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
