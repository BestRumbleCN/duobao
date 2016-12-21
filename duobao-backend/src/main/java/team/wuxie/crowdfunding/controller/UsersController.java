package team.wuxie.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(UserValidator.validator());
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
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TUser> list;
        //todo 查询方式待修改
        Map<String, String> map = Maps.newHashMap();
        map.put("userId", dtModel.getSearch().getValue());
        map.put("username", dtModel.getSearch().getValue());
        list = userService.selectAllLike(map);
        PageInfo<TUser> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    /**
     * 添加用户
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
            user.newUser();
            userService.insertSelective(user);
            return AjaxResult.getSuccess("添加成功");
        } catch (IllegalArgumentException e) {
            return AjaxResult.getFailure(Resources.getMessage(e.getMessage()));
        }
    }

    /**
     * 加载用户详情
     *
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String loadUserDetailView(@PathVariable Integer userId, Model model) {
        TUser user = userService.selectById(userId);
        if (user == null) return redirect404();
        model.addAttribute("user", user);
        return "user/user_list";
    }


    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateUser(@PathVariable Integer userId,
                                 @Valid @ModelAttribute("user") TUser user, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));

        try {
            user.updateUser(userId);
            userService.updateSelective(user);
            return AjaxResult.getSuccess("修改成功");
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
            return AjaxResult.getSuccess("状态修改成功");
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
        userService.deleteById(userId);
        return AjaxResult.getSuccess("删除成功");
    }
}

