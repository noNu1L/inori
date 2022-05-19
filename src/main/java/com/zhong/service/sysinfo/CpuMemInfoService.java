package com.zhong.service.sysinfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CpuMemInfoService {

    /**
     * Windows下cpu温度，频率没有比较好得获取手段
     *
     */

    final long GB = 1024 * 1024 * 1024;
    private OperatingSystemMXBean operatingSystemMXBean;
    private String osJson;
    private JSONObject jsonObject;

    public CpuMemInfoService() {
        operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        osJson = JSON.toJSONString(operatingSystemMXBean);
        jsonObject = JSON.parseObject(osJson);
    }

    private double twoDecimal(double doubleValue) {
        BigDecimal bigDecimal = new BigDecimal(doubleValue).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public double getCpuLoad() {
        double systemCpuLoad = jsonObject.getDouble("systemCpuLoad") * 100;
        return twoDecimal(systemCpuLoad);
    }

    public double getMemoryUseRatio() {
        Long totalPhysicalMemorySize = jsonObject.getLong("totalPhysicalMemorySize");
        Long freePhysicalMemorySize = jsonObject.getLong("freePhysicalMemorySize");
        double memoryUseRatio = 1.0 * (totalPhysicalMemorySize - freePhysicalMemorySize) / totalPhysicalMemorySize * 100;
        return twoDecimal(memoryUseRatio);
    }

    public double getFreeMemory() {
        Long freePhysicalMemorySize = jsonObject.getLong("freePhysicalMemorySize");
        double freeMemory = 1.0 * freePhysicalMemorySize / GB;
        return twoDecimal(freeMemory);
    }

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("1");
            Thread.sleep(500);
        }
        System.out.println(getCpuLoad());
    }
}
