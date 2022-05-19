package com.zhong.servlet;

import com.alibaba.fastjson.JSONArray;
import com.zhong.service.sysinfo.CpuMemInfoService;
import com.zhong.service.sysinfo.GpuInfoService;
import com.zhong.service.sysinfo.SystemInfoConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SystemInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String systemInfo = req.getParameter("systemInfo");
        Map<String, String> info = new HashMap<>();
        if (systemInfo.equals("") || systemInfo == null) {

        } else if (systemInfo.equals("initInfo")) {
            info.put("cpu", String.valueOf(SystemInfoConfig.cpuInfo));
            info.put("mem", String.valueOf(SystemInfoConfig.memInfo));
            info.put("gpu", String.valueOf(SystemInfoConfig.gpuInfo));
            info.put("refreshTime", String.valueOf(SystemInfoConfig.refreshTime));
        } else if (systemInfo.equals("cpuInfo")) {
            CpuMemInfoService cpuMemInfoService = new CpuMemInfoService();
            info.put("cpuLoad", String.valueOf(cpuMemInfoService.getCpuLoad()));
        } else if (systemInfo.equals("memInfo")) {
            CpuMemInfoService cpuMemInfoService = new CpuMemInfoService();
            info.put("freeMemory", String.valueOf(cpuMemInfoService.getFreeMemory()));
            info.put("memoryUseRatio", String.valueOf(cpuMemInfoService.getMemoryUseRatio()));
        } else if (systemInfo.equals("gpuInfo")) {
            GpuInfoService gpuInfoService = new GpuInfoService();
            info.put("gpuLoad", String.valueOf(gpuInfoService.getGpuLoad()));
            info.put("gpuFan", String.valueOf(gpuInfoService.getGpuFan()));
            info.put("gpuTemp", String.valueOf(gpuInfoService.getGpuTemp()));
        }

        /**
         * 打包信息成JSON 推送到前端
         *
         */
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONArray.toJSONString(info));
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
