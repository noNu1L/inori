<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/pure-release-1.0.0/pure-min.css">
<link rel="stylesheet" href="css/pure-release-1.0.0/grids-responsive-min.css">

<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">

    <meta http-equiv="Cache" content="no-cache">
    <meta charset="UTF-8">
    <title>MediaC</title>
    <style>
        body {
            margin: 0 30px;
        }

        .pricing-tables {
            max-width: 980px;
            margin: 0 auto;
        }

        #inputContent {
            /*padding: 5px;*/
            height: 490px;
            resize: none;
            border-radius: 3px;
            border: 0;
            /*border: 1px solid #BBB;*/
            outline: none;
        }

        #contentBox {
            margin-top: 30px;
            height: 500px;
            border-radius: 3px;
            border: 1px solid #BBB;
        }


        #imgContent {
            max-width: 980px;
            display: none;
        }

        .cBox {
            text-align: center;
        }

        .cButtonBox {
            height: 60px;
            /*border: 1px solid #777;*/
            border-radius: 0 0 8px 8px;
        }

        .cButton {
            margin-top: 15px;
            width: 30px;
            height: 30px;
            border-radius: 8px;
            display: inline-block;
            vertical-align: middle;
        }

        #edit {
            background: url("img/create-outline.svg");
        }

        #getText {
            background: url("img/cloud-download-outline.svg")
        }

        #uploadText {
            background: url("img/cloud-upload-outline.svg")
        }

        #screenshot {
            background: url("img/image-outline.svg")
        }

        #empty {
            background: url("img/trash-outline.svg")
        }

        .title {
            margin-top: 20px;
            height: 30px;
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


    <div class="pricing-tables pure-g" id="contentBox">
        <img class="pure-u-1" id="imgContent" src="img/cloud-download-outline.svg">
        <textarea class="pure-u-1" id="inputContent" placeholder="text..."></textarea>
    </div>

    <div class="pricing-tables pure-g cBox">
        <div class="pure-u-1-5 cButtonBox">
            <div class="cButton" id="edit"></div>
        </div>

        <div class="pure-u-1-5 cButtonBox">
            <div class="cButton" id="uploadText"></div>
        </div>

        <div class="pure-u-1-5 cButtonBox">
            <div class="cButton" id="getText"></div>
        </div>

        <div class="pure-u-1-5 cButtonBox">
            <div class="cButton" id="screenshot"></div>
        </div>

        <div class="pure-u-1-5 cButtonBox">
            <div class="cButton" id="empty"></div>
        </div>
    </div>
</div>


<script src="js/clipboard.js"></script>
</body>
</html>