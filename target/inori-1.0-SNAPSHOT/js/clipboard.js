let imgContent = document.getElementById("imgContent")
let inputContent = document.getElementById("inputContent")
let contentBox = document.getElementById("contentBox")

let edit = document.getElementById("edit")
let getText = document.getElementById("getText")
let uploadText = document.getElementById("uploadText")
let screenshot = document.getElementById("screenshot")
let empty = document.getElementById("empty")

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

edit.onclick = () => {
    imgContent.style.display = "none";
    inputContent.style.display = "inline"
}

getText.onclick = () => {
    imgContent.style.display = "none";
    inputContent.style.display = "inline"
    let fn = (data) => {
        console.log(data.text)
        inputContent.value = data.text;
    }
    ajax("/clipboard.do", "method=getText", fn)
}

uploadText.onclick = () => {
    let textContent = inputContent.value
    let reqData = "method=uploadText&" +
        "textContent=" + textContent
    ajax("/clipboard.do", reqData)
}

screenshot.onclick = () => {
    let fn = (data) => {
        imgContent.style.display = "inline";
        inputContent.style.display = "none"

        imgContent.src = data.imgPath + "?" + new Date().getTime()
        imgContent.onload = function () {
            console.log(this.offsetHeight)
            contentBox.style.height = this.offsetHeight + "px"
        }
    }
    ajax("/clipboard.do", "method=screenshot", fn)
}
empty.onclick = () => {
    imgContent.style.display = "none";
    inputContent.style.display = "inline"
    inputContent.style.height = "490px"
    inputContent.value = ""
}




