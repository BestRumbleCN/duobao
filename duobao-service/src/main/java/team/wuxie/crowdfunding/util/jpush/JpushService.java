package team.wuxie.crowdfunding.util.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TUserToken;

/**
 * ClassName:JpushService <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2017年1月6日 下午1:13:35
 * @see
 */
public class JpushService {

	public static JPushClient jpushClient = new JPushClient(
			PushConfig.MASTER_SECRET, PushConfig.APP_KEY, 3);

	public static void sendPUSH(TUserToken userToken, TMessage message) {
		if(userToken == null){
			return;
		}
		PushPayload payload = userToken.getPlatform() == 2 ? buildIOSPush(
				userToken.getUserId(), message.getContent(),
				message.getMessageId(), message.getMessageType().getValue())
				: buildAndroidPush(userToken.getUserId(), message.getContent(),
						message.getMessageId(), message.getMessageType()
								.getValue());
		try {
			@SuppressWarnings("unused")
			PushResult result = jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
			System.out.println(e);
		}
	}

	private static PushPayload buildIOSPush(Integer userId, String message,
			int messageId, int messageType) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.alias(userId + ""))
								.build())
				// .setMessage(Message.newBuilder().setMsgContent(message).addExtra("messageId",
				// messageId).build())
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification
												.newBuilder()
												.setAlert(message)
												.addExtra("messageId",
														messageId)
												.addExtra("messageType",
														messageType).build())
								.build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	private static PushPayload buildAndroidPush(Integer userId, String message,
			int messageId, int messageType) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.alias(userId + ""))
								.build())
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										AndroidNotification
												.newBuilder()
												.setAlert(message)
												.addExtra("messageId",
														messageId)
												.addExtra("messageType",
														messageType).build())
								.build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}
}
