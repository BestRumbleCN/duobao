package team.wuxie.crowdfunding.domain;

/**
 * ClassName:UserTest <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年7月15日 下午1:17:36
 * @see
 */
public class UserTest {
	private Integer id;
	private String userName;
	private String password;
	private Long points;
	private String remark1;
	private String remark2;
	private String remark3;
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserTest [id=" + id + ", userName=" + userName + ", password=" + password + ", points=" + points
				+ ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3=" + remark3 + ", status=" + status + "]";
	}

}
