package com.spz.tools.cmd;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CmdUtil {

	private String charset = "GBK";

	private long timeout = 6000;

	public CmdUtil(String charset, long timeout) {
		super();
		this.systemCharset();
		this.charset = charset;
		this.timeout = timeout;
	}

	public CmdUtil() {
		super();
		this.systemCharset();
	}

	/**
	 * @方法名 systemCharset
	 * @return String
	 * @功能描述 获取系统字符编码
	 * @创建人 施鹏振
	 * @创建时间 2019年1月11日上午9:49:07
	 */
	private String systemCharset() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			charset = "GBK";
		} else {
			charset = StandardCharsets.UTF_8.toString();
		}
		return charset;
	}

///////////////////////////////// 一条命令合在一起//////////////////////////////////////
	public ExecuteResult execCmd(String command, long timeout) {
		return this.execCmd(command, timeout, charset);
	}

	public ExecuteResult execCmd(String command, String charset) {
		return this.execCmd(command, timeout, charset);
	}

	public ExecuteResult execCmd(String command) {
		return this.execCmd(command, timeout, charset);
	}

	public ExecuteResult execCmd(String command, long timeout, String charset) {
		CommandLine commandline = CommandLine.parse(command);
		return execCmd(commandline, timeout, charset);
	}
/////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////// 一条命令分开//////////////////////////////////////
	public ExecuteResult execCmd(String... commands) {
		return this.execCmd(timeout, charset, commands);
	}

	public ExecuteResult execCmd(long timeout, String... commands) {
		return this.execCmd(timeout, charset, commands);
	}

	public ExecuteResult execCmd(String charset, String... commands) {
		return this.execCmd(timeout, charset, commands);
	}

	public ExecuteResult execCmd(long timeout, String charset, String... commands) {
		ExecuteResult result = new ExecuteResult();
		if (commands.length > 0) {
			CommandLine commandline = null;
			for (String command : commands) {
				if (null == commandline) {
					commandline = new CommandLine(command);
				} else {
					commandline.addArgument(command);
				}
			}
			result = execCmd(commandline, timeout, charset);
		} else {
			result.setExitCode(0);
			result.setExecuteOut("请传递要执行的命令");
			log.info("ExecuteOut---->" + result.getExecuteOut());
			log.info("ExitCode---->" + result.getExitCode());
		}
		return result;
	}

///////////////////////////////////////////////////////////////////////

	public ExecuteResult execCmd(CommandLine commandline, long timeout, String charset) {
		ExecuteResult result = new ExecuteResult();
		try {
			// 接收正常结果流
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			// 接收异常结果流
			ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

			// 创建执行命令对象,并配置
			Executor exec = new DefaultExecutor();
			exec.setExitValues(null);

			// 设置一分钟超时
			ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
			exec.setWatchdog(watchdog);

			// 创建返回信息接收对象
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
			exec.setStreamHandler(streamHandler);

			// 创建返回结果接收对象
			DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();

			// 执行命令
			exec.execute(commandline, handler);

			// 命令执行返回前一直阻塞
			handler.waitFor();

			// 返回结果信息
			int exitValue = handler.getExitValue();
			result.setExitCode(exitValue);

			String output = outputStream.toString(charset);
			String error = errorStream.toString(charset);
			result.setExecuteOut(output + error);

			ExecuteException exception = handler.getException();
			if (null != exception) {
				result.setExecuteOut(result.getExecuteOut() + exception.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setExecuteOut(result.getExecuteOut() + e.getMessage());
		} finally {
			log.info("ExecuteOut---->" + result.getExecuteOut());
			log.info("ExitCode---->" + result.getExitCode());
		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		CmdUtil cmdUtil = new CmdUtil();
		String command = "ping localhost";
		cmdUtil.execCmd(command);
		cmdUtil.execCmd(new String[] { "ping", "localhost" });
	}

}
