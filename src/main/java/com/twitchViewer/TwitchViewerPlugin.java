/*
 * Copyright (c) 2021, Guy Greenleaf <https://github.com/guygreenleaf>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.twitchViewer;


import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.google.inject.Provides;
import javax.inject.Inject;

import com.twitchViewer.overlay.TwitchOverlay;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.twitchViewer.Authorization;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;


@Slf4j
@PluginDescriptor(
	name = "Twitch Viewer"
)



public class TwitchViewerPlugin extends Plugin
{



	OAuth2Credential credential = new OAuth2Credential("twitch", "YOUR ACCESS TOKEN");

	TwitchClient twitchClient = TwitchClientBuilder.builder()
			.withEnableChat(true)
			.withChatAccount(credential)
			.build();



	private ChatMessages chatMessages;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TwitchOverlay twitchViewerOverlay;

	@Inject
	private TwitchViewerConfig config;



	@Provides
	TwitchViewerConfig provideConfig(ConfigManager configManager){
		twitchClient.getChat().joinChannel("NateBra");
		return configManager.getConfig(TwitchViewerConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{

		log.info("Viewer started!");

		String aString = "string!";

		twitchClient.getEventManager().onEvent(ChannelMessageEvent .class, event -> {

//			chatMessages.clearMessages();
//			chatMessages.addMessage("NUT");
			String newMessage = "[" + event.getChannel().getName() + "] " + event.getUser().getName() + ": " + event.getMessage();
			System.out.println(newMessage);
//			System.out.println(chatMessages.getMessage());
		});



		overlayManager.add(twitchViewerOverlay);

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");


		twitchClient.close();
//		twitchClient.getChat().leaveChannel("NateBra");

		overlayManager.remove(twitchViewerOverlay);

	}

}
