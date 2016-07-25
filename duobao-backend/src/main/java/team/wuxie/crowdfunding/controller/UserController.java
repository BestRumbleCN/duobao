package team.wuxie.crowdfunding.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.User;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.DataTable;
import team.wuxie.crowdfunding.util.Page;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserController <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月13日 下午5:16:23
 * @see 	 
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String loadUsersView() {
        return "user/user_list";
    }

    @RequestMapping(value = "/channelOrderPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<User> findChannelOrderPage(DataTable dataTable) {
        //定义列名
        String[] cols = {"user_id", "username", "status", "role", "price", "status", "create_time"};
        dataTable.setRequest(request);
        dataTable.setCols(cols);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength(), dataTable.getOrderBy());
        List<User> list;
        if (!Strings.isNullOrEmpty(dataTable.getSearchValue())) {
            Map<String, String> map = Maps.newHashMap();
            map.put("userId", dataTable.getSearchValue());
            map.put("username", dataTable.getSearchValue());
            list = userService.selectAllLike(map);
        } else {
            list = userService.selectAll();
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dataTable.getDraw());
    }
}

