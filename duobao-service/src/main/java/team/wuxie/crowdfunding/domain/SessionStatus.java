package team.wuxie.crowdfunding.domain;


import team.wuxie.crowdfunding.domain.base.IntEnum;
import team.wuxie.crowdfunding.domain.base.ValueObject;

/**
 * <p>
 * UserToken 的会话状态
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:06
 */
public enum SessionStatus implements IntEnum, ValueObject<SessionStatus> {
    /**
     * 正常
     */
    NORMAL(1),

    /**
     * 正常登出
     */
    LOGOUT_NORMAL(2),

    /**
     * 被强制登出
     */
    LOGOUT_FORCE(3);

    private final short value;

    SessionStatus(int value) {
        this.value = (short) value;
    }

    @Override
    public boolean sameValueAs(SessionStatus other) {
        return this.equals(other);
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public static <T extends Number> SessionStatus of(T value) {
        if (value == null) {
            return null;
        }
        for (SessionStatus status : values()) {
            if (status.value == value.shortValue()) {
                return status;
            }
        }
        return null;
    }
}
