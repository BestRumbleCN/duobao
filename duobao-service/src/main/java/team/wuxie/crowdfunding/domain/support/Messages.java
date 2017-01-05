package team.wuxie.crowdfunding.domain.support;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.mapper.TMessageMapper;
import team.wuxie.crowdfunding.model.MessageQuery;
import team.wuxie.crowdfunding.util.page.DtModel;
import team.wuxie.crowdfunding.util.page.Page;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Messages: 消息相关领域模型 -- 涉及消息表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Messages implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TMessageMapper messageMapper;

    private Messages() {
    }

    private static final Messages INSTANCE = new Messages();

    public static Messages getInstance() {
        return INSTANCE;
    }

    public static TMessageMapper messageMapper() {
        return INSTANCE.messageMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TMessage selectById(Integer messageId) {
        return messageMapper().selectByPrimaryKey(messageId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TMessage selectByIdOrFail(Integer messageId) {
        checkArgument(messageId != null && messageId > 0, "messageId illegal");
        TMessage message = selectById(messageId);
        messageNotFound(message);
        return message;
    }

    public static Page<TMessage> findMessagePage(String table, MessageQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TMessage> list = messageMapper().selectAllByQuery(query);
        PageInfo<TMessage> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    @Contract("null -> fail")
    private static void messageNotFound(TMessage message) throws EntityNotFoundException {
        if (message == null) throw new EntityNotFoundException("message.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        messageMapper = applicationContext.getBean(TMessageMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setMessageMapper(TMessageMapper messageMapper) {
        checkNotNull(messageMapper, "messageMapper");
        this.messageMapper = messageMapper;
    }
}
