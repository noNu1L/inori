package com.zhong.servlet;

import com.alibaba.fastjson.JSONArray;
import com.zhong.service.media.MusicControlService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MediaControlServlet extends HttpServlet {
    private MusicControlService controlService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String mediaControl = req.getParameter("mediaControl");
        if (mediaControl.equals("") || mediaControl == null) {

        } else if (mediaControl.equals("volumeUp")) {
            System.out.println("volumeUp");
            controlService.mediaControl("1");
            execSuccess(resp);

        } else if (mediaControl.equals("volumeDown")) {
            System.out.println("volumeDown");
            controlService.mediaControl("2");
            execSuccess(resp);

        } else if (mediaControl.equals("mediaNext")) {
            System.out.println("mediaNext");
            controlService.mediaControl("4");

        } else if (mediaControl.equals("mediaPrev")) {
            System.out.println("mediaPrev");
            controlService.mediaControl("3");

        } else if (mediaControl.equals("mediaPlay")) {
            System.out.println("mediaPlay");
            controlService.mediaControl("5");
        }
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
