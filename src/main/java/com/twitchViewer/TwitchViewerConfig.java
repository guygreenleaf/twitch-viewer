package com.twitchViewer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("twitchViewer")
public interface TwitchViewerConfig extends Config
{
	@ConfigItem(
			position= 1,
		keyName = "twitchViewer",
		name = "Twitch Player",
		description = "Shows a twitch channel"
	)
	default boolean showViewer(){
		return true;
	}
}
