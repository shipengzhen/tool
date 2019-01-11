package com.spz.tools.nexus;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.spz.tools.cmd.CmdUtil;
import com.spz.tools.cmd.ExecuteResult;

public class UploadDependencyNexus {

	// 上传
	public ExecuteResult upload(String groupId, String filePath, String url, String repositoryId) {
		return this.upload(groupId, null, null, filePath, url, repositoryId);
	}

	// 上传
	public ExecuteResult upload(String groupId, String version, String filePath, String url, String repositoryId) {
		return this.upload(groupId, null, version, filePath, url, repositoryId);
	}

	// 上传
	public ExecuteResult upload(String groupId, String artifactId, String version, String filePath, String url,
			String repositoryId) {
		CmdUtil cmdUtil = new CmdUtil();
		String commands = this.command(groupId, artifactId, version, filePath, url, repositoryId);
		return cmdUtil.execCmd(commands);
	}

	// 上传
	public ExecuteResult upload(String groupId, File file, String url, String repositoryId) {
		return this.upload(groupId, null, null, file, url, repositoryId);
	}

	// 上传
	public ExecuteResult upload(String groupId, String version, File file, String url, String repositoryId) {
		return this.upload(groupId, null, version, file, url, repositoryId);
	}

	// 上传
	public ExecuteResult upload(String groupId, String artifactId, String version, File file, String url,
			String repositoryId) {
		CmdUtil cmdUtil = new CmdUtil();
		String commands = this.command(groupId, artifactId, version, file, url, repositoryId);
		return cmdUtil.execCmd(commands);
	}

	// 获取参数
	public String command(String groupId, File file, String url, String repositoryId) {
		return this.command(groupId, null, null, file, url, repositoryId);
	}

	// 获取参数
	public String command(String groupId, String version, String filePath, String url, String repositoryId) {
		return this.command(groupId, null, version, filePath, url, repositoryId);
	}

	// 获取参数
	public String command(String groupId, String artifactId, String version, String filePath, String url,
			String repositoryId) {
		File file = new File(filePath);
		return this.command(groupId, artifactId, version, file, url, repositoryId);
	}

	// 获取参数
	public String command(String groupId, String filePath, String url, String repositoryId) {
		File file = new File(filePath);
		return this.command(groupId, null, null, file, url, repositoryId);
	}

	// 获取参数
	public String command(String groupId, String artifactId, String version, File file, String url,
			String repositoryId) {
		StringBuffer buffer = new StringBuffer();
		String[] commands = this.commands(groupId, artifactId, version, file, url, repositoryId);
		for (int i = 0; i < commands.length; i++) {
			if (i == commands.length - 1) {
				buffer.append(commands[i]);
			} else {
				buffer.append(commands[i] + " ");
			}
		}
		return buffer.toString();
	}

	// 获取参数
	public String[] commands(String groupId, String filePath, String url, String repositoryId) {
		return this.commands(groupId, null, null, filePath, url, repositoryId);
	}

	// 获取参数
	public String[] commands(String groupId, String version, String filePath, String url, String repositoryId) {
		return this.commands(groupId, null, version, filePath, url, repositoryId);
	}

	// 获取参数
	public String[] commands(String groupId, String artifactId, String version, String filePath, String url,
			String repositoryId) {
		File file = new File(filePath);
		return this.commands(groupId, artifactId, version, file, url, repositoryId);
	}

	// 获取参数
	public String[] commands(String groupId, String artifactId, String version, File file, String url,
			String repositoryId) {

		if (StringUtils.isBlank(artifactId)) {
			artifactId = file.getParentFile().getParentFile().getName();
		}

		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (StringUtils.isBlank(version)) {
			version = fileName.substring(0, dotIndex).replace(artifactId + "-", "");
		}

		String packaging = fileName.substring(dotIndex + 1, fileName.length());

		String dGroupId = "-DgroupId=" + groupId;
		String dArtifactId = "-DartifactId=" + artifactId;
		String dVersion = "-Dversion=" + version;
		String dPackaging = "-Dpackaging=" + packaging;
		String filePath = file.getPath();
		String dFile = "-Dfile=" + filePath;
		String dUrl = "-Durl=" + url;
		if (StringUtils.isBlank(repositoryId)) {
			repositoryId = "nexus-releases";
		}
		String dRepositoryId = "-DrepositoryId=" + repositoryId;

		String[] commands = new String[] { "cmd", "/c", "mvn", "deploy:deploy-file", dGroupId, dArtifactId, dVersion,
				dPackaging, dFile, dUrl, dRepositoryId };
		return commands;
	}

	public static void main(String[] args) {
		UploadDependencyNexus nexus = new UploadDependencyNexus();

		String groupId = "org.loushang.theme";

		String fileName = "loushang-theme-web-7.2.0.war";
		String file = "theme\\loushang-theme-web\\7.2.0\\" + fileName;

		String url = "https://www.shipengzhen.top/nexus/content/repositories/releases/";
		String repositoryId = "nexus-releases";

//		String command = nexus.command(groupId, "F:\\Learning\\Java\\jar\\loushang\\loushang\\" + file, url,
//				repositoryId);
//		System.out.println(command);
		nexus.upload(groupId, "F:\\Learning\\Java\\jar\\loushang\\loushang\\" + file, url, repositoryId);
	}

}
