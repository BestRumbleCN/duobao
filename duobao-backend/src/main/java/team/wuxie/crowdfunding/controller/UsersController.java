package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.DtModel;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.validation.UserValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 多用户Controller
 *
 * @author fly
 * @version 1.0
 * @see
 * @since 2016年7月13日 下午5:16:23
 */
@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @Autowired
    UserService userService;

    /**
     * 加载用户列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadUsersView() {
        return "user/user_list";
    }

    /**
     * Ajax获取用户列表
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TUser> findUserPage(String table) {
        //定义列名
//        String[] cols = {"user_id", "username", "user_status", "create_time", null};
//        dataTable.setParams(cols, request);
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TUser> list;
        Map<String, String> map = Maps.newHashMap();
        map.put("userId", dtModel.getSearch().getValue());
        map.put("username", dtModel.getSearch().getValue());
        list = userService.selectAllLike(map);
        PageInfo<TUser> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 添加或者更新用户
     *
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveUser(@Valid @ModelAttribute("user") TUser user, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));

        try {
            userService.insertOrUpdate(user);
            return AjaxResult.getSuccess(Resources.getMessage("insert.success"));
        } catch (IllegalArgumentException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 更新用户状态：启用/禁用
     *
     * @param userId
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}/status", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateUserStatus(@PathVariable Integer userId) throws AjaxException {
        try {
            userService.updateUserStatus(userId);
            return AjaxResult.getSuccess(Resources.getMessage("update.success"));
        } catch (IllegalArgumentException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 根据userId删除用户
     *
     * @param userId
     * @return
     * @throws AjaxException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteUser(@PathVariable Integer userId) throws AjaxException {
        LOGGER.info(String.format("删除用户：userId=%s", String.valueOf(userId)));
        userService.deleteById(userId);
        return AjaxResult.getSuccess(Resources.getMessage("delete.success"));
    }
}

