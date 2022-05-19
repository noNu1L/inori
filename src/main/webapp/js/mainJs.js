let mediaButtonBox = document.getElementsByClassName("mediaButtonBox");
let cpu = {
    tag: document.querySelector("#cpu"),
    flag: false
}
let mem = {
    tag: document.querySelector("#mem"),
    flag: false
}
let gpu = {
    tag: document.querySelector("#gpu"),
    flag: false
}
let timer;
let refreshTime = 0;

// ajax封装
let ajax = (url, respData, callback) => {
    let xhr = new XMLHttpRequest;
    xhr.open("GET", url + "?" + respData, true)
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200 || xhr.status == 304) {
                console.log("xhr.response:" + xhr.response)
                if (callback != null) {
                    let data = eval("(" + xhr.response + ")");
                    callback(data);
                }
            }
        }
    }
    xhr.send()
}

// 系统信息显示开关
let switchTo = (state, item) => {
    if (state == true) {
        item.flag = true
        item.tag.classList.replace("switchOff", "switchOn")
    } else if (state == false) {
        item.flag = false
        item.tag.classList.replace("switchOn", "switchOff")
    }
}

// 刷新时间切换
let switchRT = (rtTime) => {
    for (let item of document.querySelectorAll(".refreshTime")) {
        item.classList.remove("refreshTime-choice")
    }
    if (rtTime == 0 || rtTime == "off") {
        document.getElementById("off").classList.add("refreshTime-choice")
        refreshTime = 0
    } else {
        document.getElementById("rt" + rtTime + "s").classList.add("refreshTime-choice")
        refreshTime = rtTime;
    }
}

// 点击时 刷新信息并请求
let changeMode = () => {
    clearInterval(timer)
    //更新 SystemInfoConfig
    {
        let data = "cpuFlag=" + cpu.flag
            + "&gpuFlag=" + gpu.flag
            + "&memFlag=" + mem.flag
            + "&refreshTime=" + refreshTime
        ajax("/updateConfig.do", data)
    }

    if (refreshTime > 0 && (cpu.flag || mem.flag || gpu.flag)) {
        timer = setInterval(() => {
            if (cpu.flag) {
                let fn = (data) => document.getElementById("cpuLoad").innerHTML = data.cpuLoad + "%"
                ajax("/systemInfo.do", "systemInfo=cpuInfo", fn)
            }
            if (mem.flag) {
                let fn = (data) => {
                    document.getElementById("memoryUseRatio").innerHTML = data.memoryUseRatio + "%"
                    document.getElementById("freeMemory").innerHTML = data.freeMemory + "GB"
                }
                ajax("/systemInfo.do", "systemInfo=memInfo", fn)
            }
            if (gpu.flag) {
                let fn = (data) => {
                    document.getElementById("gpuLoad").innerHTML = data.gpuLoad + "%"
                    document.getElementById("gpuTemp").innerHTML = data.gpuTemp + "℃"
                    document.getElementById("gpuFan").innerHTML = data.gpuFan + "%"
                }
                ajax("/systemInfo.do", "systemInfo=gpuInfo", fn)
            }
        }, refreshTime * 1000)
    }
}

// 从服务器初始化信息
window.onload = (() => {
    let fn = (data) => {
        switchTo(JSON.parse(data.cpu), cpu)
        switchTo(JSON.parse(data.mem), mem)
        switchTo(JSON.parse(data.gpu), gpu)
        switchRT(data.refreshTime)
    }
    ajax("/systemInfo.do", "systemInfo=initInfo", fn)
    setTimeout(() => {
        console.log("cpu.flag:" + cpu.flag + ",mem.flag:" + mem.flag + ",gpu.flag:" + gpu.flag + ",refreshTime:" + refreshTime)
        changeMode();
    }, 3000)
})

cpu.tag.onclick = () => {
    switchTo(!cpu.flag, cpu)
    changeMode()
}
mem.tag.onclick = () => {
    switchTo(!mem.flag, mem)
    changeMode()
}
gpu.tag.onclick = () => {
    switchTo(!gpu.flag, gpu)
    changeMode()
}

// 多媒体控制绑定事件
for (let item of mediaButtonBox) {
    item.onclick = () => ajax("mediaControl.do", "mediaControl=" + item.id, null)
}

// 刷新时间绑定事件
for (let item of document.querySelectorAll(".refreshTime")) {
    item.onclick = () => {
        let time = item.id;
        if (item.id != "off" || item.id != "0") time = time.slice(2, item.id.indexOf("s"))
        switchRT(time)
        changeMode()
    }
}






