package me.pugabear.gitkoda.utils;

import static me.pugabear.gitkoda.GitKoda.CONFIG;

import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.LabelService;
import org.eclipse.egit.github.core.service.UserService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class Services
{
	public IssueService issues = new IssueService();
	public LabelService labels = new LabelService();
	public UserService users = new UserService();
	public RepositoryService repos = new RepositoryService();

	public Services()
	{
		issues.getClient().setOAuth2Token(CONFIG.githubToken);
		labels.getClient().setOAuth2Token(CONFIG.githubToken);
		users.getClient().setOAuth2Token(CONFIG.githubToken);
		repos.getClient().setOAuth2Token(CONFIG.githubToken);
	}
}