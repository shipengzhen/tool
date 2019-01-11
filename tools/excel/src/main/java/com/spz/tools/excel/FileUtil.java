package com.spz.tools.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    /**
     * 注入日志
     */
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * @方法名 getFileName
     * @param path
     * @param fileNamePrefix
     * @param fileNameSuffix
     * @return String
     * @功能描述 获取格式为：(时间戳+3位随机数)+文件后缀的文件名，并且当在同目录下有相同文件名的文件存在，重新生成。
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午1:50:07
     */
    private static String getFileName(String path, String fileNamePrefix, String fileNameSuffix) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Random random = new Random();
        if (StringUtils.isEmpty(fileNamePrefix)) {
            fileNamePrefix = fileNameSuffix.replace(".", "");
        }
        fileNamePrefix += "_";
        String fileName = fileNamePrefix + format.format(new Date()) + String.format("%03d", random.nextInt(999))
                + fileNameSuffix;
        File file = new File(path + fileName);
        // 如果文件已存在，需要重新生成文件名
        if (file.exists() && file.isFile()) {
            return getFileName(path, fileNamePrefix, fileNameSuffix);
        }
        return fileName;
    }

    /**
     * @方法名 getFileFullPath
     * @param multipartFile
     * @param fileNamePrefix
     * @param uploadFileBasePath
     * @return String
     * @功能描述 获取文件全路径
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午2:23:23
     */
    @SuppressWarnings("unused")
    private static String getFileFullPath(MultipartFile multipartFile, String fileNamePrefix,
            String uploadFileBasePath) {
        return getFile(multipartFile, fileNamePrefix, uploadFileBasePath).toString();
    }

    /**
     * @功能描述：通过multipartFile生成File
     * @参数说明：@param multipartFile
     * @参数说明：@param fileNamePrefix
     * @参数说明：@param uploadFileBasePath
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年12月27日 下午4:19:15
     */
    private static File getFile(MultipartFile multipartFile, String fileNamePrefix, String uploadFileBasePath) {
        // 上传时的原文件名
        String oleFileName = multipartFile.getOriginalFilename();
        // 上传时的原文件名后缀
        String fileNameSuffix = "";
        if (StringUtils.isNotEmpty(oleFileName) && oleFileName.indexOf(".") >= 0) {
            fileNameSuffix = oleFileName.substring(oleFileName.lastIndexOf("."), oleFileName.length());
        }
        // 生成的新的文件名
        String newFileName = getFileName(uploadFileBasePath, fileNamePrefix, fileNameSuffix);

        File file = new File(uploadFileBasePath);
        //如果不是绝对路径,处理成绝对路径
        if (!file.isAbsolute()) {
            file = file.getAbsoluteFile();
        }

        //判断路径是否存在，不存在就新建
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        File path = new File(file, newFileName);
        return path;
    }

    /**
     * @方法名 upload
     * @param multipartFile 文件
     * @param fileNamePrefix 文件名前缀
     * @param uploadFileBasePath 要上传到的地址
     * @return String
     * @功能描述 上传文件
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午2:11:12
     */
    public static String upload(MultipartFile multipartFile, String fileNamePrefix, String uploadFileBasePath) {
        File file = getFile(multipartFile, fileNamePrefix, uploadFileBasePath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();
    }

    /**
     * @方法名 upload
     * @param multipartFile 文件
     * @param uploadFileBasePath
     * @return String
     * @功能描述 上传文件
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午2:11:12
     */
    public static String upload(MultipartFile multipartFile, String uploadFileBasePath) {
        return upload(multipartFile, null, uploadFileBasePath);
    }

    /**
     * @方法名 upload
     * @param multipartFile文件
     * @param uploadFileBasePath要上传到的地址
     * @return String
     * @功能描述 文件上传
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午1:36:08
     */
    public static String[] upload(MultipartFile[] multipartFiles, String fileNamePrefix, String uploadFileBasePath) {
        String[] filePaths = new String[multipartFiles.length];

        for (int i = 0; i < multipartFiles.length; i++) {
            filePaths[i] = upload(multipartFiles[i], fileNamePrefix, uploadFileBasePath);
        }
        return filePaths;
    }

    /**
     * @方法名 upload
     * @param multipartFile文件
     * @param uploadFileBasePath要上传到的地址
     * @return String
     * @功能描述 文件上传
     * @创建人 施鹏振 
     * @创建时间 2018年12月27日下午1:36:08
     */
    public static String[] upload(MultipartFile[] multipartFiles, String uploadFileBasePath) {
        return upload(multipartFiles, null, uploadFileBasePath);
    }

    /**
     * 方法描述:按指定的文件全路径(包含文件名)创建一个新的文件，并把内容写入到文件中
     *        如果这个绝对路径中文件夹与文件不存在将会自动创建，如果文件已经存在将会被覆盖
     * @param fileFullPath 文件全路径(包含文件名)
     * @param content 要写入文件的内容
     * date:2014-4-17
     * add by: liu_tao@xwtec.cn
     */
    public static File createNewFile(String fileFullPath, String content) {
        return createNewFile(fileFullPath, content, null);
    }

    /**
     * 
     * 方法描述:删除文件
     * @param path 文件相对路径  fileName 文件名
     * date:2014-4-25
     * add by: fanzhaode@xwtec.cn
     */
    public static void deleteFile(String relativePath, String fileName) throws Exception {
        // 文件存放的根目录的绝对路径
        StringBuilder path = new StringBuilder("");
        path.append(relativePath + File.separator + fileName);
        File file = new File(path.toString());
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            } catch (Exception e) {
                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            }
        }
    }

    /**
     * 方法描述:按指定的文件全路径(包含文件名)创建一个新的文件，并把内容写入到文件中
     *        如果这个绝对路径中文件夹与文件不存在将会自动创建，如果文件已经存在将会被覆盖
     * @param fileFullPath 文件全路径(包含文件名)
     * @param content 要写入文件的内容
     * @param enc 文件编码格式
     * date:2014-4-17
     * add by: liu_tao@xwtec.cn
     */
    public static File createNewFile(String fileFullPath, String content, String enc) {
        File file = new File(fileFullPath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        OutputStreamWriter os = null;
        try {
            if (enc == null || enc.length() == 0) {
                os = new OutputStreamWriter(new FileOutputStream(file));
            } else {
                os = new OutputStreamWriter(new FileOutputStream(file), enc);
            }

            os.write(content);

            os.close();
        } catch (IOException e) {
            logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
                }
            }
        }
        return file;
    }

    /**
     * txt文件格式导出内容信息
     * 方法描述:根据编码格式导出信息
     * @param list 要导出内容  （内部list是每一行数据的集合   外部list是所有行的集合）
     * @param enc 文件编码格式
     * date:2014-6-20
     * add by: fanzhaode@xwtec.cn
     */
    public static void exportFile(HttpServletResponse response, List<List<String>> list, String enc) {
        PrintWriter out = null;
        try {

            //实现下载功能
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String("exportFile.txt".getBytes("GBK"), "ISO8859_1"));
            response.setContentType("text/html");
            //编码格式
            response.setCharacterEncoding(enc);
            out = response.getWriter();
            /**
             * 循环所有行的集合
             */
            for (int i = 0; i < list.size(); i++) {

                List<String> l1 = list.get(i);

                /**
                 * 每行信息
                 */
                for (int j = 0; j < l1.size(); j++) {
                    out.write(l1.get(j));
                    out.write("\t");
                }
                //换行
                out.write("\r\n");
            }
            out.close();
        } catch (IOException e) {
            logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
                }
            }
        }
    }

    /**
     * @功能描述：创建文件目录
     * @参数说明：@param path
     * @作者： shipengzhen
     * @创建时间：2017年9月12日 上午11:16:57
     */
    public static void mkdir(String path) {
        if (StringUtils.isNotEmpty(path)) {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
        }
    }

    /**
     * @功能描述：拼接写入串
     * @参数说明：@param exportData
     * @参数说明：@param rowMap
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年8月2日 下午12:22:38
     */
    public static String getContent(List<Map<Object, Object>> exportData, Map<Object, Object> rowMap) {

        String content = "";
        for (Iterator<Entry<Object, Object>> iterator = rowMap.entrySet().iterator(); iterator.hasNext();) {
            Entry<Object, Object> entry = iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (null != value && null != key && !"class".equals(key.toString())) {
                content += value;
                if (iterator.hasNext()) {
                    content += "|";
                }
            }

        }

        if (null != exportData && exportData.size() > 0) {
            content += "\r\n";

            // 写入文件内容
            for (Iterator<Map<Object, Object>> iterator = exportData.iterator(); iterator.hasNext();) {
                Map<Object, Object> row = iterator.next();
                for (Iterator<Entry<Object, Object>> propertyIterator = row.entrySet().iterator(); propertyIterator
                        .hasNext();) {
                    Entry<Object, Object> entry = propertyIterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (null != value && null != key && !"class".equals(key.toString())) {
                        content += value + "|";
                    }
                }
                content = content.substring(0, content.length() - 1);
                if (iterator.hasNext()) {
                    content += "\r\n";
                }
            }
        }
        System.out.println(content);
        return content;

    }

    /**
     * @功能描述：创建文件
     * @参数说明：@param exportData
     * @参数说明：@param rowMap
     * @参数说明：@param fileFullPath
     * @作者： shipengzhen
     * @创建时间：2018年8月2日 下午12:22:36
     */
    public static File createNewFile(List<Map<Object, Object>> exportData, Map<Object, Object> rowMap,
            String fileFullPath) {
        return createNewFile(fileFullPath, getContent(exportData, rowMap));
    }

    /**
     * @功能描述：拼接写入串
     * @参数说明：@param exportData
     * @参数说明：@param rows
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年8月3日 上午9:58:23
     */
    public static String getContent(List<List<String>> exportData, List<String> rows) {
        String content = "";
        for (String row : rows) {
            if (StringUtils.isNotEmpty(row)) {
                content += row + "|";
            }
        }
        content = content.substring(0, content.length() - 1);

        for (List<String> strings : exportData) {
            content += "\r\n";
            if (null != strings) {
                for (String string : strings) {
                    if (StringUtils.isNotEmpty(string)) {
                        content += string + "|";
                    }
                }
            }
            content = content.substring(0, content.length() - 1);
        }

        System.out.println(content);

        return content;
    }

    /**
     * @功能描述：创建文件
     * @参数说明：@param exportData
     * @参数说明：@param rowMap
     * @参数说明：@param fileFullPath
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年8月3日 上午9:41:23
     */
    public static File createNewFile(List<List<String>> exportData, List<String> rows, String fileFullPath) {
        return createNewFile(fileFullPath, getContent(exportData, rows));
    }

    /**
     * @功能描述：创建文件
     * @参数说明：@param beans
     * @参数说明：@param rowMap
     * @参数说明：@param fileFullPath
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2018年8月3日 上午9:38:58
     */
    @SuppressWarnings("rawtypes")
    public static File createFile(List beans, Map<Object, Object> rowMap, String fileFullPath) {
        List<Map<Object, Object>> exportData = new ArrayList<Map<Object, Object>>();
        for (Object bean : beans) {
            BeanMap beanMap = new BeanMap(bean);
            Map<Object, Object> map = beanMap;
            exportData.add(map);
        }
        return createNewFile(exportData, rowMap, fileFullPath);
    }

    /**
     * @功能描述：获取文件后缀名
     * @参数说明：@param file
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2019年1月8日 下午7:02:26
     */
    public static String getFileSuffixName(File file) {
        String filePath = file.getPath();
        String extString = filePath.substring(filePath.lastIndexOf("."));
        return extString;
    }
    
   

}