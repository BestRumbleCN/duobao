package team.wuxie.crowdfunding.controller;

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
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.exception.AjaxException;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.DataTable;
import team.wuxie.crowdfunding.util.Page;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.i18n.Resources;
import team.wuxie.crowdfunding.util.validation.UserValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ClassName:UserController <br/>
 *
 * @author fly
 * @version 1.0
 * @see
 * @since 2016年7月13日 下午5:16:23
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @InitBinder("user")
    private void initBinder(DataBinder binder) {
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
     * @param dataTable
     * @return
     */
    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<TUser> findUserPage(DataTable dataTable) {
        //定义列名
        String[] cols = {"user_id", "username", "user_status", "role", "create_time", null};
        dataTable.setParams(cols, request);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength(), dataTable.getOrderBy());
        List<TUser> list;
        if (!Strings.isNullOrEmpty(dataTable.getSearchValue())) {
            Map<String, String> map = Maps.newHashMap();
            map.put("userId", dataTable.getSearchValue());
            map.put("username", dataTable.getSearchValue());
            list = userService.selectAllLike(map);
        } else {
            list = userService.selectAll();
        }
        PageInfo<TUser> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dataTable.getDraw());
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
    public AjaxResult saveUser(@Valid TUser user, BindingResult result) throws AjaxException {
        if (result.hasErrors()) return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));

        try {
            userService.insertOrUpdate(user, getCurrentUser().getUserId());
            return AjaxResult.getSuccess(Resources.getMessage("insert.success"));
        } catch (IllegalArgumentException e) {
            return AjaxResult.getSuccess(Resources.getMessage(e.getMessage()));
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

