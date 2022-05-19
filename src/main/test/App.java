import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class App {
    @Test
    public void testSystemUsage() {
        final long GB = 1024 * 1024 * 1024;
        while (true) {
            OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
            String osJson = JSON.toJSONString(operatingSystemMXBean);
//            System.out.println("osJson is " + osJson);
            JSONObject jsonObject = JSON.parseObject(osJson);
            double processCpuLoad = jsonObject.getDouble("processCpuLoad") * 100;
            double systemCpuLoad = jsonObject.getDouble("systemCpuLoad") * 100;
            Long totalPhysicalMemorySize = jsonObject.getLong("totalPhysicalMemorySize");
            Long freePhysicalMemorySize = jsonObject.getLong("freePhysicalMemorySize");
            double totalMemory = 1.0 * totalPhysicalMemorySize / GB;
            double freeMemory = 1.0 * freePhysicalMemorySize / GB;
            double memoryUseRatio = 1.0 * (totalPhysicalMemorySize - freePhysicalMemorySize) / totalPhysicalMemorySize * 100;

            StringBuilder result = new StringBuilder();
            result.append("系统CPU占用率: ")
                    .append(twoDecimal(systemCpuLoad))
                    .append("%，内存占用率：")
                    .append(twoDecimal(memoryUseRatio))
                    .append("%，系统总内存：")
                    .append(twoDecimal(totalMemory))
                    .append("GB，系统剩余内存：")
                    .append(twoDecimal(freeMemory))
                    .append("GB，该进程占用CPU：")
                    .append(twoDecimal(processCpuLoad))
                    .append("%");
            System.out.println(result.toString());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double twoDecimal(double doubleValue) {
        BigDecimal bigDecimal = new BigDecimal(doubleValue).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

}
