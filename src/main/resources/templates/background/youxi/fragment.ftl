﻿<!DOCTYPE html>  
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>打砖块小游戏</title>

    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            cursor: none;
        }

        a {
            text-decoration: none;
        }

        ul, li {
            list-style: none;
        }

        body {
            font-size: 14px;
            font-family: "微软雅黑";
            background: url("images/bg.jpg") top/cover;
        }

        #ball {
            width: 15px;
            height: 15px;
            background: #b4ff0d;
            border-radius: 50%;
            position: absolute;
            top: 0;
            left: 0;
            box-shadow: 0 0 9px 9px #f3ff67;
        }

        #ward {
            width: 120px;
            height: 30px;
            z-index: 999;
            background-color: #336688;
            border-radius: 10px;
            box-shadow: 0 0 4px #333333;
            position: absolute;
            left: 0;
        }

        #score {
            width: 100px;
            height: 100px;
            font-size: 40px;
            position: absolute;
            right: 40px;
            top: 40px;
            color: #ff2541;
        }

        #wrap {
            width: 90%;
            height: 500px;
            position: relative;
            top: 100px;
            left: 0;
            right: 0;
            margin: auto;
        }

        #wrap div {
            width: 45px;
            height: 15px;
            border: 1px solid #ff645b;
            position: absolute;
            background: rgb(255, 99, 89);
            box-shadow: 0 0 9px 1px rgb(255, 187, 136) inset;
            top: 0;
            left: 0;
            transform-origin: top center
        }

        #gameover {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            width: 300px;
            height: 200px;
            box-shadow: 0 0 4px #222222;
            background-color: #e1e1e1;
            display: none
        }

        #gameover p {
            width: 100%;
            height: 40px;
            text-align: center;
            font-size: 36px;
            color: #336688;
            margin-top: 50px;
        }

        #gameover span {
            width: 60%;
            height: 40px;
            display: block;
            margin: 38px auto 0;
            text-align: center;
            font-size: 20px;
            background: #336688;
            color: #ffffff;
            border-radius: 10px;
            line-height: 40px;
        }
    </style>

</head>
<body>

<div id="wrap"></div>
<div id="ball"></div>
<div id="ward"></div>
<div id="score">0分</div>
<div id="gameover">
    <p>总分:74</p>
    <span>确定</span>
</div>
<script type="text/javascript">
    /*  javascript中严格区分大小写 a!==A;  
             1.需要哪些元素  
                 小球 挡板 砖块区域 初始x位置 初始y位置  
             2.要有那些行为  
                初始化init  
                     init用来存放初始化方法  
                     如果实例化对象调用所有方法很麻烦,所以一次性解决  
             创建砖块creatBrick  
             for循环生成砖块  
             计算每一个砖块初始化top和left  
             计算金字塔中线位置  
             初始化小球  
             初始化小球x方向移动向量  
             初始化小球y方向移动向量  
             初始化小球宽度和高度  
             初始化小球开始运动事件  
             初始化小球位置 x,y  
             初始化挡板  
             初始化挡板位置 x,y  
             初始化鼠标监听事件  
             挡板的移动  
             挡板中央跟随鼠标x方向移动  
             挡板运动边界 左 右  
             小球的移动  
             小球移动方法 requestAnimationFrame  
             小球x y 向量自增  
             碰撞检测{  
             1:小球和浏览器边框的碰撞  
             反弹 x||y  
             2:小球和挡板的碰撞  
             反弹 y  
             3.小球和砖块的碰撞  
             反弹 y && 移除砖块  
             }  
     */
    var oBall = document.querySelector("#ball");  //球  
    var oWard = document.querySelector("#ward");  //挡板  
    var oScore = document.querySelector('#score');//计分板  
    var oWrap = document.querySelector('#wrap');  //砖块区域  
    var over = document.querySelector('#gameover'); //结束  

    function Breakout(ball, ward, score, wrap, over) {   //打砖块小游戏对象 构造函数  
        this.ball = ball;
        this.ward = ward;
        this.scores = score;
        this.wrap = wrap;
        this.over = over;
        this.x = 0;
        this.y = 0;
        this.score = 0;
    }

    Breakout.prototype = {    //原型方法  
        init: function () {  //初始化系统  
            this.ballstar();    //初始化小球  
            this.creatBrick();  //创建砖块  
            this.wardMove();    //挡板移动  

        },
        creatBrick: function () {    //砖块初始化  
            var x = document.documentElement.offsetWidth / 2 - document.documentElement.offsetWidth * .05, //设置居中位置  
                    w = 45 * 2, //设置横向间距基准值
                    h = 15 * 2; //设置纵向间距基准值
            for (var i = 1; i <= 8; i++) {  //循环生成div 8层  
                for (var j = 0; j < i * 2 - 1; j++) {    //每一层的砖块个数为 层数*2-1  
                    var brick = document.createElement("div");
                    brick.style.top = (i - 1) * h + 'px';
                    brick.style.left = x - (i * w) + (j * w) + 'px';
                    this.wrap.appendChild(brick);
                }
            }
        },
        wardMove: function () {     //挡板初始化  
            this.ward.style.top = window.innerHeight - 180 + 'px';  //初始化挡板的top位置  
            this.ward.style.left = this.x - 60 + 'px';              //初始化挡板的left位置居中  
            this.addEvent(document, 'mousemove', this.mouseMove.bind(this)); //监听鼠标移动  
        },
        ballstar: function () {    //小球初始化  
            var This = this;
            this.y = window.innerHeight - 200;    //初始化坐标X的位置 窗口底部上移200px  
            this.x = window.innerWidth / 2;       //初始化坐标Y的位置 窗口中间部位  
            this.ball.style.top = this.y + 'px';  //初始化小球的top值为y  
            this.ball.style.left = this.x + 'px'; //初始化小球的left值为x  
            this.ball.speed = 10;                 //初始化小球的速度  
            this.ball.width = 15;                   //初始化小球的宽度  
            this.ball.height = 15;                  //初始化小球的高度  
            document.onclick = function () {      //点击开始游戏,小球运动  
                This.ballMove();                  //小球移动  
            }

        },
        //挡板移动  
        mouseMove: function (e) {                               //鼠标移动,挡板跟随鼠标运动  
            e = e || window.event;                              //事件对象兼容性处理  
            var _left = e.pageX - this.ward.offsetWidth / 2;    //保证鼠标移动,挡板中间位置同步鼠标位置  
            _left = Math.min(window.innerWidth - this.ward.offsetWidth, _left); //挡板向右移动不能超过屏幕右边界  
            _left = Math.max(0, _left);                                         //挡板向左移动不能超过屏幕左边界  
            this.ward.style.left = _left + 'px';                                //通过设置挡板left值实现挡板移动  
        },
        ballMove: function () {                 //小球开始运动  
            document.onclick = null;            //先清除document的点击事件防止一直重置运动  
            this.ball.xspeed = this.ball.speed; //初始化小球x运动速度和方向 +为往左 -为往右  
            this.ball.yspeed = -this.ball.speed;//初始化小球y运动速度和方向 +为往上 -为往下  
            function auto() {                   //运动函数 auto 通过requestAnimationFrame递归调用实现循环  
                this.x += this.ball.xspeed;     //x代表当前横向位置 += 横向移动速度 10 每一次都在自己原先的位置基础上+10  
                this.y += this.ball.yspeed;     //y代表当前横向位置 += 横向移动速度 10 每一次都在自己原先的位置基础上+10  
                this.crash();                   //碰撞检测  
                this.ball.style.left = this.x + 'px';   //小球运动赋值 x轴运动  
                this.ball.style.top = this.y + 'px';    //小球运动赋值 y轴运动  
                requestAnimationFrame(auto.bind(this)); //原生js动画 根据cpu运算速度来实现更新  
            }

            auto.call(this);
        },
        crash: function () {
            var maxWidth = window.innerWidth - this.ball.offsetWidth;        //浏览器左边界=浏览器宽度-球的宽度  
            var maxHeight = window.innerHeight - this.ball.offsetHeight;     //浏览器右边界=浏览器高度-球的高度  
            if (this.y >= maxHeight) {                                       //小球掉下去之后,游戏结束  
                this.gameOver();
            }
            if (this.x >= maxWidth) {
                this.ball.xspeed *= -1;                                     //小球碰到右边墙壁后 横向移动速度取反 往返方向移动  
                this.x = maxWidth;                                          //重置小球位置  
            }
            if (this.x < 0) {                                               //碰到左边墙 重置横向移动速度 并且重置横向位置 为0  
                this.ball.xspeed = this.ball.speed;
                this.x = 0;
            }
            if (this.y < 0) {                                               //碰到上边墙壁之后 重置纵向移动速度 以及纵向位置 为0  
                this.ball.yspeed = this.ball.speed;
                this.y = 0;
            }
            //挡板碰撞检测 xweizhi  
            if (Math.abs(this.x - (this.ward.offsetLeft + (this.ward.clientWidth / 2))) < 60 && Math.abs(this.y - this.ward.offsetTop - 30) < 45) {
                var color = this.ranColor();
                this.ward.style.background = color;
                this.ball.yspeed *= -1;
                this.y = this.ward.offsetTop - 40;
            }

            for (var i = this.wrap.children.length - 1; i >= 0; i--) {
                var ballMx = this.ball.offsetLeft + (this.ball.width / 2);
                var ballMy = this.ball.offsetTop + (this.ball.height / 2);
                var brickMx = (this.wrap.children[i].offsetLeft + this.wrap.offsetLeft) + (45 / 2);
                var brickMy = (this.wrap.children[i].offsetTop + this.wrap.offsetTop) + (15 / 2);
                if (Math.abs(ballMx - brickMx) <= 45 && Math.abs(ballMy - brickMy) <= 15) {
                    this.ball.yspeed *= -1;
                    this.y = brickMy;
                    this.wrap.removeChild(this.wrap.children[i]);
                    if (this.wrap.children.length == 0) {
                        this.gameOver();
                    }
                    this.scoreChange();
                }
            }
        },
        scoreChange: function () {
            this.score++;
            this.scores.innerHTML = this.score + '分';
        },
        gameOver: function () {
            this.over.style.display = 'block';
            this.over.children[0].innerHTML = '总分:' + this.score;
            var all = document.querySelectorAll('*');
            for (var i = 0; i < all.length; i++) {
                all[i].style.cursor = 'auto'
            }
            this.ward.style.display = 'none';
            this.ball.style.display = 'none';
            this.over.children[1].onclick = function () {
                window.location.reload();
            }
        },
        /*    getStyle: function (ele, curr) {  //获取属性值  
         return ele.currentStyle ? parseInt(ele.currentStyle[curr]) : parseInt(getComputedStyle(ele, null)[curr]);  
         },*/
        addEvent: function (element, e, fn) {//事件监听  
            return element.attachEvent ? element.attachEvent('on' + e, fn) : element.addEventListener(e, fn, false);
        },
        ranColor: function () { //随机颜色  
            var color = '#';
            for (var i = 0; i < 6; i++) {
                color += '0123456789abcdef'[Math.floor(Math.random() * 16)]
            }
            return color;
        },
    }
    var breakout = new Breakout(oBall, oWard, oScore, oWrap, over);
    breakout.init();
    //凑个200行玩玩....  
    //凑个200行玩玩...  
</script>
<div style="text-align:center;">
</div>

</body>
</html>