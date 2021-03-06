window.app = {
    path: function () {
        return document.getElementById("path").value;
    },
    isString: function (o) { //是否字符串
        return Object.prototype.toString.call(o).slice(8, -1) === 'String'
    },
    isNumber: function (o) { //是否数字
        return Object.prototype.toString.call(o).slice(8, -1) === 'Number'
    },

    isObj: function (o) { //是否对象
        return Object.prototype.toString.call(o).slice(8, -1) === 'Object'
    },

    isArray: function (o) { //是否数组
        return Object.prototype.toString.call(o).slice(8, -1) === 'Array'
    },

    isDate: function (o) { //是否时间
        return Object.prototype.toString.call(o).slice(8, -1) === 'Date'
    },

    isBoolean: function (o) { //是否boolean
        return Object.prototype.toString.call(o).slice(8, -1) === 'Boolean'
    },

    isFunction: function (o) { //是否函数
        return Object.prototype.toString.call(o).slice(8, -1) === 'Function'
    },

    isNull: function (str) { //是否为null
        if (str == null || str == "" || str.length == 0 || str == undefined || str == "null") {
            return true;
        }
        return false;
    },

    isUndefined: function (o) { //是否undefined
        return Object.prototype.toString.call(o).slice(8, -1) === 'Undefined'
    },

    isFalse: function (o) {
        if (!o || o === 'null' || o === 'undefined' || o === 'false' || o === 'NaN') return true
        return false
    },

    isNotNull: function (str) {
        if (str != null && str != "" && str.length > 0 && str != undefined) {
            return true;
        }
        return false;
    },
    isIos: function () {
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            // return "Android";
            return false
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            // return "iPhone";
            return true
        } else if (u.indexOf('iPad') > -1) {//iPad
            // return "iPad";
            return false
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            // return "Windows Phone";
            return false
        } else {
            return false
        }
    },

    isPC: function () { //是否为PC端
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad", "iPod"];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    },

    browserType: function () {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
        var isEdge = userAgent.indexOf("Edge") > -1; //判断是否IE的Edge浏览器
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
        var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器
        var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器
        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion == 7) return "IE7"
            else if (fIEVersion == 8) return "IE8";
            else if (fIEVersion == 9) return "IE9";
            else if (fIEVersion == 10) return "IE10";
            else if (fIEVersion == 11) return "IE11";
            else return "IE7以下"//IE版本过低
        }

        if (isFF) return "FF";
        if (isOpera) return "Opera";
        if (isEdge) return "Edge";
        if (isSafari) return "Safari";
        if (isChrome) return "Chrome";
    },
    checkStr: function (str, type) {
        switch (type) {
            case 'phone':   //手机号码
                return /^1[3|4|5|7|8][0-9]{9}$/.test(str);
            case 'tel':     //座机
                return /^(0\d{2,3}-\d{7,8})(-\d{1,4})?$/.test(str);
            case 'card':    //身份证
                return /^\d{15}|\d{18}$/.test(str);
            case 'pwd':     //密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                return /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/.test(str)
            case 'postal':  //邮政编码
                return /[1-9]\d{5}(?!\d)/.test(str);
            case 'QQ':      //QQ号
                return /^[1-9][0-9]{4,9}$/.test(str);
            case 'email':   //邮箱
                return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
            case 'money':   //金额(小数点2位)
                return /^\d*(?:\.\d{0,2})?$/.test(str);
            case 'URL':     //网址
                return /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/.test(str)
            case 'IP':      //IP
                return /((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))/.test(str);
            case 'date':    //日期时间
                return /^(\d{4})\-(\d{2})\-(\d{2}) (\d{2})(?:\:\d{2}|:(\d{2}):(\d{2}))$/.test(str) || /^(\d{4})\-(\d{2})\-(\d{2})$/.test(str)
            case 'username':
                return /^[a-zA-Z0-9_-]{4,16}$/.test(str);
        }
    },
    loads: function () {
        return '<div class="ibox-content" id="ibox" style="position:fixed;top:30%;z-index:1200;background:url(../wap/images/loading.gif) top center no-repeat;width:100%;height:140px;margin:auto auto;">\n' +
            '                        <div class="spiner-example">\n' +
            '                            <div class="sk-spinner sk-spinner-three-bounce">\n' +
            '                                <div class="sk-bounce1"></div>\n' +
            '                                <div class="sk-bounce2"></div>\n' +
            '                                <div class="sk-bounce3"></div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>';
    },
    dateTime: function (time, format) {
        var date = new Date(time);

        var year = date.getFullYear(),
            month = date.getMonth() + 1,//月份是从0开始的
            day = date.getDate(),
            hour = date.getHours(),
            min = date.getMinutes(),
            sec = date.getSeconds();
        var preArr = Array.apply(null, Array(10)).map(function (elem, index) {
            return '0' + index;
        });////开个长度为10的数组 格式为 00 01 02 03

        var newTime = format.replace(/YY/g, year)
            .replace(/MM/g, preArr[month] || month)
            .replace(/DD/g, preArr[day] || day)
            .replace(/hh/g, preArr[hour] || hour)
            .replace(/mm/g, preArr[min] || min)
            .replace(/ss/g, preArr[sec] || sec);

        return newTime;
    },
    contrastTime: function (start) {//判断当前选择时间是否小于是当前实际时间
        /*var evalue = document.getElementById(start).value;*/
        var dB = new Date(start.replace(/-/g, "/"));//获取当前选择日期
        var d = new Date();
        var str = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();//获取当前实际日期
        if (Date.parse(str) > Date.parse(dB)) {//时间戳对比
            return true;
        }
        return false;
    }
};