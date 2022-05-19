package com.zhong.service.media;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

public class MusicControlService {
    private static final Logger logger = Logger.getGlobal();

    /**
     * 使用vbs控制windows媒体
     * 约定在应用根目录下的 temp 目录中放置5以下vbs文件
     * temp 目录于tomcat bin目录下创建
     * <p>
     * volumeMute.vbs 静音
     * volumeUp.vbs 音量加
     * volumeDown.vbs 音量减
     * mediaPrev.vbs 上一首
     * mediaNext.vbs下一首
     * mediaPlay.vbs 播放 / 暂停
     * 文件以及文件的内容采用 Java 代码动态生成，不存在时则新建，存在时则直接调用
     *
     * @param type 0：静音/取消静音    1：音量加   2：音量减   3：上一首   4：下一首   5：播放/暂停
     */

    public static void mediaControl(String type) {
        try {
            if (type == null || "".equals(type.trim())) {
                logger.info("type 参数为空,不进行操作...");
            }
            /**tempFile：vbs 文件
             * vbsMessage：vbs 文件的内容*/
            String vbsMessage = "";
            File tempFile = null;
            Runtime runtime = Runtime.getRuntime();
            switch (type) {
                case "0":
                    tempFile = new File("temp", "volumeMute.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棴\"" : "";
                    break;
                case "1":
                    tempFile = new File("temp", "volumeUp.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棷\"" : "";
                    break;
                case "2":
                    tempFile = new File("temp", "volumeDown.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棶\"" : "";
                    break;
                case "3":
                    tempFile = new File("temp", "mediaPrev.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棻\"" : "";
                    break;
                case "4":
                    tempFile = new File("temp", "mediaNext.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棸\"" : "";
                    break;
                case "5":
                    tempFile = new File("temp", "mediaPlay.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棾\"" : "";
                    break;

                default:
                    return;
            }
            /**
             * 当6个vbs文件不存在时，则创建它们，应用默认编码为 utf-8 时，创建的 vbs 脚本运行时报错
             * 于是使用 OutputStreamWriter 将 vbs 文件编码改成gbd就正常了
             */
            if (!tempFile.exists() && !vbsMessage.equals("")) {
                if (!tempFile.getParentFile().exists()) {
                    tempFile.getParentFile().mkdirs();
                }
                tempFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
                outputStreamWriter.write(vbsMessage);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                logger.info("vbs 文件不存在，新建成功：" + tempFile.getAbsolutePath());
            }
            runtime.exec("wscript " + tempFile.getAbsolutePath()).waitFor();
            // logger.info("音量控制完成.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
