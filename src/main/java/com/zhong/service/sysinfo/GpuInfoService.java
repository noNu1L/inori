package com.zhong.service.sysinfo;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GpuInfoService {
    StringBuffer stringBuffer;

    /**
     * 使用英伟达官方程序nvidia-smi 获取显卡相关信息
     *  cmd中 执行nvidia-smi ，输出字符串，再进行截取
     */

    public GpuInfoService() {
        try {
            String line;
            stringBuffer = new StringBuffer(10);
            Process p = Runtime.getRuntime().exec("nvidia-smi.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                stringBuffer.append(line);
                // System.out.println(line); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public String getGpuLoad() {
        String gpuLoad = stringBuffer.substring(stringBuffer.lastIndexOf("%") - 3, stringBuffer.lastIndexOf("%"));
        return gpuLoad;
    }

    public String getGpuFan() {
        String gpuFan = stringBuffer.substring(stringBuffer.indexOf("%") - 3, stringBuffer.indexOf("%"));
        return gpuFan;
    }


    public String getGpuTemp() {
        String gpuTemp = stringBuffer.substring(stringBuffer.indexOf("%") + 3, stringBuffer.indexOf("%") + 6);
        return gpuTemp;
    }

    @Test
    public void test() {
        System.out.println(getGpuTemp());
        System.out.println(getGpuFan());
        System.out.println(getGpuLoad());
    }
}

