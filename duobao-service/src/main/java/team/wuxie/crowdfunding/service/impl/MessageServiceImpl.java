package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.mapper.TMessageMapper;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 消息ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:29
 */
@Service
public class MessageServiceImpl extends AbstractService<TMessage> implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TMessageMapper messageMapper;
}
