package com.zhong.servlet;

import com.alibaba.fastjson.JSONArray;
import com.zhong.service.clipboard.ClipboardService;
import com.zhong.service.clipboard.SnapShot;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ClipboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.println(context.getRealPath("/"));

        Map<String, String> content = new HashMap<>();
        if (req != null) {
            String method = req.getParameter("method");
            if (method.equals("getText")) {
                content.put("text", ClipboardService.getSysClipboardText());
            } else if (method.equals("uploadText")) {
                ClipboardService.setSysClipboardText(req.getParameter("textContent"));
            } else if (method.equals("screenshot")) {
                System.out.println("method.equals.screenshot");
                File tempFile = new File(context.getRealPath("/") + "temp");
                tempFile.mkdirs();
                String filePath = tempFile.getAbsolutePath() + "\\screenshot";
                SnapShot cam = new SnapShot(filePath, "png");
                cam.snapShot();
                content.put("imgPath","temp\\screenshot.png");
            }
        }
        /**
         * 打包信息成JSON 推送到前端
         */
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONArray.toJSONString(content));
        writer.flush();
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Test
    public void test() {
        String filePath = ".src/main/webapp/temp/SnapShot";
        System.out.println(filePath);
    }
}
