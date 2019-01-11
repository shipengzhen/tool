package com.spz.tools.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class CmdUtilxx {

	@Default
	private String charset = Charset.defaultCharset().toString();

	/**
	 * @方法名 execCMD
	 * @param exec
	 * @return InputStream
	 * @功能描述 执行命令
	 * @创建人 施鹏振
	 * @创建时间 2019年1月10日下午6:01:08
	 */
	public InputStream execCMD(String exec) {
		InputStream inputStream = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(exec);
			inputStream = process.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return inputStream;
	}

	/**
	 * @方法名 execCommand
	 * @param exec
	 * @return String
	 * @功能描述 执行命令
	 * @创建人 施鹏振
	 * @创建时间 2019年1月10日下午6:01:02
	 */
	public String execCommand(String exec) {
		String result = "";
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime.exec(exec);
			result = processStdout(process);
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			if (null != process) {
				process.destroy();
			}
		}
		return result;
	}

	/**
	 * @方法名 execCommand
	 * @param exec
	 * @return String
	 * @功能描述 执行命令
	 * @创建人 施鹏振
	 * @创建时间 2019年1月10日下午6:00:48
	 */
	public String execCommand(String[] exec) {

		String result = "";
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime.exec(exec);
			result = processStdout(process);
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			if (null != process) {
				process.destroy();
			}
		}
		return result;

	}

	/**
	 * @方法名 processStdout
	 * @param process
	 * @return String
	 * @功能描述 解析返回信息
	 * @创建人 施鹏振
	 * @创建时间 2019年1月10日下午6:00:27
	 */
	public String processStdout(Process process) {
		String result = "";
		InputStream errorStream = process.getErrorStream();
		InputStream inputStream = process.getInputStream();
		result = processStdout(errorStream, charset);
		String errorResult = processStdout(inputStream, charset);
		if (StringUtils.isNotEmpty(errorResult)) {
			return result + errorResult;
		}

		return result;
	}

	/**
	 * @方法名 processStdout
	 * @param inputStream
	 * @param charset
	 * @return String
	 * @功能描述 解析返回信息
	 * @创建人 施鹏振
	 * @创建时间 2019年1月10日下午6:00:13
	 */
	public String processStdout(InputStream inputStream, String charset) {
		StringBuffer result = new StringBuffer();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		try {
			// String reader = "";
			String reader = bufferedReader.readLine();
			System.out.println(reader);
//			while (() != null) {
//				result.append(reader + "\r\n");
//			}
		} catch (IOException e) {
			e.printStackTrace();
			result.append(e.getMessage());
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				result.append(e.getMessage());
			}

		}
		return result.toString();
	}
}
