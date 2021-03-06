package me.pugabyte.jayce.commands.subcommands;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.pugabyte.jayce.Jayce;
import me.pugabyte.jayce.utils.InvalidArgumentException;
import org.eclipse.egit.github.core.Issue;

import java.util.Arrays;

import static me.pugabyte.jayce.Jayce.CONFIG;
import static me.pugabyte.jayce.Jayce.SERVICES;

public class EditSubCommand {
	private static final String USAGE = "edit <id> <title|body> <content>";

	public EditSubCommand(int id, CommandEvent event) throws InvalidArgumentException {
		String what;
		String content;
		try {
			String[] args = event.getArgs().split(" ");
			what = args[2].toLowerCase();
			content = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new InvalidArgumentException(Jayce.USAGE + USAGE);
		}

		if (!(what.equals("title") || what.equals("body"))) {
			throw new InvalidArgumentException("You must edit the title or the body");
		}
		if (content.isEmpty()) {
			throw new InvalidArgumentException("You must supply content for the " + what);
		}


		if (edit(id, what, content)) {
			event.reply(":thumbsup:");
		} else {
			event.reply("Could not edit issue");
		}
	}

	private boolean edit(int id, String what, String content) {
		try {
			Issue issue = SERVICES.issues.getIssue(CONFIG.githubUser, CONFIG.githubRepo, id);
			if (what.equalsIgnoreCase("title")) {
				issue.setTitle(content);
			} else if (what.equalsIgnoreCase("body")) {
				issue.setBody(content);
			} else {
				return false;
			}

			SERVICES.issues.editIssue(CONFIG.githubUser, CONFIG.githubRepo, issue);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
