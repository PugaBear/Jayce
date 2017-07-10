package me.pugabear.gitkoda.utils;

import static me.pugabear.gitkoda.GitKoda.CONFIG;

import com.jagrosh.jdautilities.commandclient.CommandEvent;

public class Utils 
{
	// Method to hide messages in a channel with a webhook enabled
	public static void reply(CommandEvent event, String message)
	{
		if (!event.getChannel().getId().equals(CONFIG.webhookChannelId))
		{
			event.reply(message);
		}
	}
}
