package team.wuxie.crowdfunding.vo;

/**
 * <p>
 * 注册用户统计
 * </p>
 *
 * @author wushige
 * @date 2016-08-29 17:17
 */
public class UsersStatisticsVO extends VO {

    /**
     * 月份
     */
    private String month;
    /**
     * 数量
     */
    private int count;

    public UsersStatisticsVO() {
    }

    public UsersStatisticsVO(String month, int count) {
        this.month = month;
        this.count = count;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
