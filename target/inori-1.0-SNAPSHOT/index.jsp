<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/pure-release-1.0.0/pure-min.css">
<link rel="stylesheet" href="css/pure-release-1.0.0/grids-responsive-min.css">

<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta charset="UTF-8">
    <title>MediaC</title>
    <style>
        body {
            margin: 0 30px;
        }

        .pricing-tables {
            max-width: 980px;
            margin: 20px auto;
        }

        .mediaBox {
            border-radius: 3px;
            box-shadow: 2px 2px 20px #eee;
            text-align: center;
        }

        .mediaButton {
            margin-top: 25px;
            width: 30px;
            height: 30px;
            border-radius: 8px;
            display: inline-block;
            vertical-align: middle;
        }

        .mediaButton:active {
            box-shadow: 2px 2px 20px #c2c2c2;
        }

        .mediaButtonBox {
            height: 80px;
        }


        .volumeUpImg {
            background-image: url("img/volume-high-outline.svg");
        }

        .volumeDownImg {
            background-image: url("img/volume-low-outline.svg");
        }

        .volumeMuteImg {
            background-image: url("img/volume-mute-outline.svg");
        }

        .mediaNextImg {
            background-image: url("img/play-skip-forward-outline.svg");
        }

        .mediaPlayImg {
            position: relative;
            left: 10px;
            background-image: url("img/play-outline.svg");
        }

        .mediaPauseImg {
            position: relative;
            right: 10px;
            background-image: url("img/pause-outline.svg");
        }

        .mediaPrevImg {
            background-image: url("img/play-skip-back-outline.svg");
        }

        .systemInfoBox {
            margin-top: 10px;
            border: 1px solid #eee;
            border-radius: 3px;
            height: 60px;
        }

        .systemInfoBox-itemTitle {
            height: 60px;
            border-radius: 3px;
            text-align: center;
            line-height: 60px;
            font-weight: bolder;
        }

        .item-info {
            font-size: 14px;
            height: 30px;
            text-align: center;
            line-height: 30px;
        }

        .systemInfoSwitch {
            margin-top: 20px;
            border-radius: 3px;
            height: 30px;
        }

        .refreshTime-title {
            font-weight: bold;
        }

        .refreshTime {
            border-radius: 3px;

        }

        .refreshTime-choice {
            background: #dddddd;
        }

        .systemInfoSwitch > div {
            height: 30px;
            text-align: center;
            line-height: 30px;
        }

        .switchOn {
            border-left: #0078e7 solid 4px;
        }

        .switchOff {
            border-left: #e9322d solid 4px;
        }

        .systemInfo {

        }

        .head {
            margin-top: 20px;
            height: 30px;
        }

        .head-title {
            height: 30px;
            line-height: 30px;

        }

    </style>
</head>
<body>

<div class="l-content">
    <div class="pricing-tables pure-menu pure-menu-horizontal title">
        <ul class="pure-menu-list">
            <li class="pure-menu-item pure-menu-selected">MediaControl</li>
            <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                <a href="#" id="menuLink1" class="pure-menu-link">组件</a>
                <ul class="pure-menu-children">
                    <li class="pure-menu-item"><a href="/" class="pure-menu-link">多媒体控制</a></li>
                    <li class="pure-menu-item"><a href="/clipboard.jsp" class="pure-menu-link">系统剪切板</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="pricing-tables pure-g systemInfo">
        <div class="pure-u-1 systemInfoBox">
            <div class="pure-u-4-24">
                <div class="systemInfoBox-itemTitle switchOff" id="cpu">
                    CPU
                </div>
                <div></div>
            </div>
            <div class="pure-u-6-24 ">
                <div class="item-info">频率</div>
                <div class="item-info ">/</div>

            </div>
            <div class="pure-u-6-24 ">
                <div class="item-info">占用</div>
                <div class="item-info" id="cpuLoad">/</div>
            </div>
            <div class="pure-u-6-24 ">
                <div class="item-info">温度</div>
                <div class="item-info">/</div>
            </div>
        </div>

        <div class="pure-u-1 systemInfoBox">
            <div class="pure-u-4-24">
                <div class="systemInfoBox-itemTitle switchOff" id="mem">
                    内存
                </div>
                <div></div>
            </div>

            <div class="pure-u-6-24">
                <div class="item-info">频率</div>
                <div class="item-info">/</div>

            </div>
            <div class="pure-u-6-24">
                <div class="item-info">占用</div>
                <div class="item-info" id="memoryUseRatio">/</div>
            </div>
            <div class="pure-u-6-24">
                <div class="item-info">剩余</div>
                <div class="item-info" id="freeMemory">/</div>
            </div>
        </div>
        <div class="pure-u-1 systemInfoBox">
            <div class="pure-u-4-24">
                <div class="systemInfoBox-itemTitle switchOn" id="gpu">
                    GPU
                </div>
                <div></div>
            </div>

            <div class="pure-u-6-24">
                <div class="item-info">温度</div>
                <div class="item-info" id="gpuTemp">/</div>

            </div>
            <div class="pure-u-6-24">
                <div class="item-info">占用</div>
                <div class="item-info" id="gpuLoad">/</div>
            </div>
            <div class="pure-u-6-24">
                <div class="item-info">风扇</div>
                <div class="item-info" id="gpuFan">/</div>
            </div>
        </div>

        <div class="pure-u-1 systemInfoSwitch">
            <div class="pure-u-7-24 pure-u-md-2-24 refreshTime-title">刷新间隔：</div>
            <div class="pure-u-3-24 pure-u-md-1-24 refreshTime refreshTime-choice" id="off">OFF</div>
            <div class="pure-u-3-24 pure-u-md-1-24 refreshTime " id="rt2s">2S</div>
            <div class="pure-u-3-24 pure-u-md-1-24 refreshTime refreshTime-choice" id="rt5s">5S</div>
            <div class="pure-u-3-24 pure-u-md-1-24 refreshTime" id="rt10s">10S</div>
        </div>
    </div>
    <div class="pricing-tables pure-g mediaBox">
        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="volumeUp">
            <div class="mediaButton volumeUpImg"></div>
        </div>

        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="volumeDown">
            <div class="mediaButton volumeDownImg"></div>
        </div>

        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="volumeMute">
            <div class="mediaButton volumeMuteImg"></div>
        </div>

        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="mediaPrev">
            <div class="mediaButton mediaPrevImg"></div>
        </div>
        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="mediaPlay">
            <div class="mediaButton mediaPlayImg"></div>
            <div class="mediaButton mediaPauseImg"></div>
        </div>
        <div class="pure-u-1-3 pure-u-md-1-6 mediaButtonBox" id="mediaNext">
            <div class="mediaButton mediaNextImg"></div>
        </div>
    </div>
</div>

<%--<script src="js/jquery-1.8.3.min.js"></script>--%>
<script src="js/mainJs.js"></script>
</body>
</html>