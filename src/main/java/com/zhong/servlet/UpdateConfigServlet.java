package com.zhong.servlet;

import com.alibaba.fastjson.JSONArray;
import com.zhong.service.sysinfo.SystemInfoConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UpdateConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SystemInfoConfig.cpuInfo = Boolean.parseBoolean(req.getParameter("cpuFlag"));
        SystemInfoConfig.gpuInfo = Boolean.parseBoolean(req.getParameter("gpuFlag"));
        SystemInfoConfig.memInfo = Boolean.parseBoolean(req.getParameter("memFlag"));
        SystemInfoConfig.refreshTime = Integer.parseInt(req.getParameter("refreshTime"));


        System.out.println("SystemInfoConfig.cpuInfo:" + SystemInfoConfig.cpuInfo);
        System.out.println("SystemInfoConfig.gpuInfo:" + SystemInfoConfig.gpuInfo);
        System.out.println("SystemInfoConfig.memInfo:" + SystemInfoConfig.memInfo);
        System.out.println("SystemInfoConfig.refreshTime:" + SystemInfoConfig.refreshTime);

        execSuccess(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void execSuccess(HttpServletResponse resp) {
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONArray.toJSONString("success"));
        writer.flush();
    }
}
