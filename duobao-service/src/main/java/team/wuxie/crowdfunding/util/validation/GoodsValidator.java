package team.wuxie.crowdfunding.util.validation;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.support.Goods;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TGoods.*;

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
        this(ImmutableList.of(PROP_TYPE_ID, PROP_GOODS_NAME, PROP_SINGLE_PRICE, PROP_TOTAL_AMOUNT, PROP_STATEMENT));
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
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, PROP_TYPE_ID, "goods.v.typeId_required", "请填写商品分类");
            }
            if (checkRequired(PROP_GOODS_NAME)) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, PROP_GOODS_NAME, "goods.v.goodsName_required", "请填写商品名称");

//                if (!Strings.isNullOrEmpty(goods.getGoodsName())) {
//                    validateGoodsNameExisted(goods, errors);
//                }
            }
            if (checkRequired(PROP_SINGLE_PRICE)) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, PROP_SINGLE_PRICE, "goods.v.singlePrice_required", "请填写夺宝价格");

                Integer singlePrice = goods.getSinglePrice();
                if (singlePrice != null) {
                    if (singlePrice < 1 || singlePrice > 10000) {
                        errors.rejectValue(PROP_SINGLE_PRICE, "goods.v.singlePrice_invalid", null,
                                "夺宝价格范围需在1~10000(元)以内");
                    }
                }
            }
            if (checkRequired(PROP_TOTAL_AMOUNT)) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, PROP_TOTAL_AMOUNT, "goods.v.totalAmount_required", "请填写总需人数");

                Integer totalAmount = goods.getTotalAmount();
                if (totalAmount != null) {
                    if (totalAmount < 1 || totalAmount > 100000) {
                        errors.rejectValue(PROP_TOTAL_AMOUNT, "goods.v.totalAmount_invalid", null,
                                "总人数范围需在1~100000以内");
                    }
                }
            }
            if (checkRequired(PROP_STATEMENT)) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, PROP_STATEMENT, "goods.v.statement_required", "请填写商品夺宝申明");
            }

        }
    }

    private void validateGoodsNameExisted(TGoods goods, Errors errors) {
        if (Goods.existsByGoodsName(goods.getGoodsName(), goods.getGoodsId())) {
            errors.rejectValue(PROP_GOODS_NAME, "goods.v.goodsName_has_existed", null, "商品名称已被使用，请重新填写");
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
