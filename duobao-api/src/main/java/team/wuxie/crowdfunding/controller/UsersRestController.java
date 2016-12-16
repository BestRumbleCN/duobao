package team.wuxie.crowdfunding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.wuxie.crowdfunding.annotation.LoginSkip;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.service.LuckyShareService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;
import team.wuxie.crowdfunding.vo.UserGoodsBidDetailVO;
import team.wuxie.crowdfunding.vo.UserVO;

/**
 * <p>
 * 多用户相关的RestController
 * </p>
 *
 * @author wushige
 * @date 2016-08-10 12:01
 */
@RestController
@RequestMapping("/users")
@Api(value = "Users - Controller", description = "多用户相关")
public class UsersRestController extends BaseRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    UserService userService;
    
    @Autowired
    GoodsBidService goodsBidService;
    
    @Autowired
    LuckyShareService luckyShareService;

    /**
     * 查看其他用户
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("查看其他用户（DONE）")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ApiResult<UserVO> getProfile(@PathVariable Integer userId) {
        UserVO userVO = userService.selectByUserId(userId);
        return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, userVO);
    }
    
    /**
     * 查看其他用户夺宝记录
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("查看其他用户夺宝记录（DONE）")
    @ApiImplicitParams({ 
    		@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path"),
    		@ApiImplicitParam(name = "status", value = "状态（1 进行中 4 已开奖）", required = true, dataType = "int", paramType = "query")})
    @RequestMapping(value = "/{userId}/shoppingLog", method = RequestMethod.GET)
    public ApiResult getLog(@PathVariable("userId") Integer userId,Integer status) {
        //UserVO userVO = userService.selectByUserId(userId);
        List<UserGoodsBidDetailVO> result = goodsBidService.selectByUserIdAndStatus(userId, status);
        return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, result);
    }
    
    /**
     * 查看其他用户中奖记录
     *
     * @return
     */
    @LoginSkip
    @ApiOperation("查看其他用户中奖记录（DONE）")
    @ApiImplicitParams({ 
    	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path"),
    	})
    @RequestMapping(value = "/{userId}/luckyLog", method = RequestMethod.GET)
    public ApiResult getLuckyLog(@PathVariable("userId") Integer userId) {
    	//UserVO userVO = userService.selectByUserId(userId);
    	List<UserGoodsBidDetailVO> result = goodsBidService.selectLuckyByUserId(userId);
    	return ApiResult.getSuccess(MessageId.GET_OTHER_PROFILE, result);
    }
}
