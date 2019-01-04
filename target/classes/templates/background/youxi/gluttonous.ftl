<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bootstrap贪吃蛇游戏代码 - 源码之家</title>
</head>
<body>
<#assign basePath=springMacroRequestContext.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.css">
<style>
    .mb-10 {
        margin-bottom: 10px;
    }

    .font-18 {
        font-size: 18px;
    }

    .game-wrap {
        width: 70%;
        padding-top: 50px;
        margin: 0 auto;
    }

    .snake-box {
        float: left;
        width: 80%;
        height: 500px;
        background-color: #666;
    }

    .right-operate {
        float: left;
        width: 20%;
        padding: 20px;
        background-color: #ccc;
    }

    .right-operate .btn {
        display: block;
        width: 100%;
        text-align: center;
    }
</style>
<div class="game-wrap clearfix">
    <div id="snake_box" class="snake-box"></div>
    <div class="right-operate">
        <div class="mb-10">
            得分：<span class="label label-success font-18" id="grade">0</span>
        </div>
        <div class="form-group">
            <select class="form-control" id="speed_select">
                <option value="slow">慢速</option>
                <option value="medium">中等</option>
                <option value="fast">快速</option>
            </select>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary" id="start_btn">开始游戏</button>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-warning" id="pause_btn">暂停游戏</button>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-info" id="continue_btn">继续游戏</button>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-success" onclick="window.location.reload()">重新开始游戏</button>
        </div>
    </div>
</div>

<script>
    ;(function (window, undefined) {
        /**
         Tool: 工具构造函数
         */
        function Tool() {
        }

        /*
            合并对象
        */
        Tool.prototype.extend = function () {
            var args = arguments,
                    argsLen = args.length,
                    target = args[0],
                    // 是否深度合并
                    deep = args[1],
                    callee = args.callee;
            if (argsLen < 2) {
                return;
            }
            if (({}).toString.call(deep) === "[object Boolean]" && deep) {
                for (var i = 1; i < argsLen; i++) {
                    var obj = args[i];
                    for (var attr in obj) {
                        var tempObj = obj[attr];
                        if (obj.hasOwnProperty(attr)) {
                            if (({}).toString.call(tempObj) === "[object Object]" || (typeof tempObj != "string" && tempObj.length && tempObj[tempObj.length - 1])) {
                                callee(target[attr], deep, tempObj);
                            } else {
                                target[attr] = tempObj;
                            }
                        }
                    }
                }
                return target;
            } else {
                // 如果不需要深度合并则直接遍历需要合并的对象
                for (var i = 1; i < argsLen; i++) {
                    var obj = args[i];
                    for (var attr in obj) {
                        if (obj.hasOwnProperty(attr)) {
                            target[attr] = obj[attr];
                        }
                    }
                }
                return target;
            }
        }
        /**
         获取指定元素距浏览器最顶端、最左端的距离
         */
        Tool.prototype.position = function (ele) {
            if (!ele || !ele.nodeName) {
                return {};
            }
            var offsetTop = ele.offsetTop,
                    offsetLeft = ele.offsetLeft,
                    offsetParent = ele.offsetParent,
                    obj = {};
            while (offsetParent != null) {
                offsetTop += offsetParent.offsetTop;
                offsetLeft += offsetParent.offsetLeft;
                offsetParent = offsetParent.offsetParent;
            }
            return {
                top: offsetTop,
                left: offsetLeft
            };
        }
        /**
         获取元素宽度
         */
        Tool.prototype.getWidth = function (ele) {
            if (!ele || !ele.nodeName) {
                return 0;
            }
            return ele.offsetWidth;
        }
        /**
         获取元素高度
         */
        Tool.prototype.getHeight = function (ele) {
            if (!ele || !ele.nodeName) {
                return 0;
            }
            return ele.offsetHeight;
        }
        /**
         获取或设置样式
         */
        Tool.prototype.css = function (ele, attr, val) {
            if (!ele || !ele.nodeName) {
                return;
            }
            attr = attr.replace(/\-(\w{1})/, function (m, $1) {
                return $1.toUpperCase();
            });
            // 获取css属性值
            if (typeof val === "undefined") {
                var cssVal;
                if (window.getComputedStyle) {
                    cssVal = window.getComputedStyle(ele, null)[attr];
                } else if (ele.currentStyle) {
                    cssVal = ele.currentStyle[attr];
                }
                if (cssVal && !/\%/g.test(cssVal)) {
                    var tempVal = Number(cssVal);
                    if (!isNaN(tempVal)) {
                        cssVal = tempVal;
                    }
                }
                return cssVal;
            }
            // 设置css属性
            var ignoreAttrs = {
                "zIndex": 1,
                "opacity": 1
            }
            if (typeof val === "number" && !(attr in ignoreAttrs)) {
                val = val + "px";
            }
            ele.style[attr] = val;
            return ele;
        }

        /**
         生成随机数
         */
        Tool.prototype.random = function (min, max) {
            switch (arguments.length) {
                case 1:
                    return parseInt(Math.random() * min + 1, 10);
                    break;
                case 2:
                    return parseInt(Math.random() * (max - min + 1) + min, 10);
                    break;
                default:
                    return 0;
                    break;
            }
        }
        window.tool = new Tool();
    })(window, undefined);
</script>
<script>
    ;(function (window, undefined) {
        /**
         Food: 食物构造函数
         */
        function Food(parent, options) {
            if (!parent || !parent.nodeName) {
                throw "element is required";
            }
            ;
            this.options = {
                width: 20,
                height: 20,
                x: 0,
                y: 0,
                className: "",
                name: "",
                text: "",
                backgroundColor: ""
            };

            this.colors = ["#FF8C00", "#FF83FA", "#9A32CD", "#999999", "#9932CC", "#98FB98", "#FF3030", "#FF1493", "#FF00FF", "#FF0000", "#98F5FF", "#96CDCD", "#949494", "#9400D3", "#9370DB", "#FF4500", "#FF4040", "#FF3E96", "#FF34B3", "#FF82AB", "#FF8247", "#FF7F50", "#FF7F24", "#FF7F00", "#FF7256"];
            tool.extend(this.options, true, options || {});
            this.options.className += " food";
            this.parent = parent;
            this.name = "";

            this._init();
        }

        /**
         初始化食物对象
         */
        Food.prototype._init = function () {
            var _ele = document.createElement("div");
            if (!this.options.backgroundColor) {
                this.options.backgroundColor = this.colors[Math.floor(Math.random() * this.colors.length)];
            }
            if (!this.options.name) {
                this.name = this.options.name = "food_" + Math.random().toString(36).substring(2, 8);
            } else {
                this.name = this.options.name;
            }
            this.x = this.options.x;
            this.y = this.options.y;
            tool.css(_ele, "width", this.options.width);
            tool.css(_ele, "height", this.options.height);
            tool.css(_ele, "position", "absolute");
            tool.css(_ele, "left", this.x);
            tool.css(_ele, "top", this.y);
            tool.css(_ele, "backgroundColor", this.options.backgroundColor);
            _ele.innerHTML = this.options.text;

            _ele.className = this.options.className;
            this.ele = _ele;
            this.parent.appendChild(this.ele);
        }
        /**
         设置食物的宽度
         */
        Food.prototype.setWidth = function (w) {
            if (typeof w === "undefined") {
                return;
            }
            tool.css(this.ele, "width", w);
            this.options.width = w;
            return this;
        }
        /**
         设置食物的高度
         */
        Food.prototype.setHeight = function (h) {
            if (typeof h === "undefined") {
                return;
            }
            tool.css(this.ele, "height", h);
            this.options.height = h;
            return this;
        }
        /**
         设置食物的位置
         */
        Food.prototype.setPosition = function (x, y) {
            if (typeof x !== "undefined") {
                x = Number(x);
                this.x = x;
                tool.css(this.ele, "left", x);
            }
            if (typeof y !== "undefined") {
                y = Number(y);
                this.y = y;
                tool.css(this.ele, "top", y);
            }
            return this;
        }

        window.Food = Food;
    })(window, undefined);
</script>
<script>

    ;(function (window, undefined) {
        /**
         贪吃蛇构造函数
         */
        function Snake(ele, options) {
            if (!ele || !ele.nodeName) {
                throw "element is required";
            }
            ;

            this._init(ele, options || {});
        }

        /**
         贪吃蛇初始化函数
         */
        Snake.prototype._init = function (ele, options) {
            this.ele = ele;
            this.options = {
                // 贪吃蛇初始化后的回调函数
                init: function () {
                },
                // 贪吃蛇吃到食物后的回调函数
                eated: function () {
                },
                // 蛇死亡时的回调函数
                onDie: function () {
                },
                // 贪吃蛇宽度
                width: 20,
                height: 20,
                // 贪吃蛇蛇头颜色
                snakeHeadColor: "",
                // 蛇默认移动的方向
                direction: "right"
            }

            tool.extend(this.options, true, options);

            // 吃到的食物的数量
            this.count = 0;
            // 贪吃蛇蛇头+蛇身
            this.snake = [];
            // 蛇将要吃的食物
            this._food;
            // 当前蛇移动的方向
            this._direction = this.options.direction;
            // 蛇是否死亡
            this._isDie = false;

            this.eleWidth = tool.getWidth(this.ele);
            this.eleHeight = tool.getHeight(this.ele);

            if (tool.css(this.ele, "position") === "static") {
                tool.css(this.ele, "position", "relative");
            }
            var snakeHead = new Food(this.ele, {
                width: this.options.width,
                height: this.options.height,
                text: "S",
                name: "snakeHead",
                x: 0,
                y: 0,
                backgroundColor: this.options.snakeHeadColor || "#f60"
            });

            tool.css(snakeHead.ele, "fontSize", 12);
            tool.css(snakeHead.ele, "color", "#fff");
            tool.css(snakeHead.ele, "textAlign", "center");
            tool.css(snakeHead.ele, "zIndex", "100");
            tool.css(snakeHead.ele, "lineHeight", this.options.height);

            // 添加蛇头
            this.snake.push(snakeHead);

            this.options.init && (typeof this.options.init == "function") && this.options.init.call(this);
        }

        /**
         蛇移动
         */
        Snake.prototype.move = function (direction) {
            if (typeof direction === "undefined") {
                direction = "left";
            }
            var snakes = this.snake,
                    i = 0,
                    that = this,
                    width = this.options.width,
                    height = this.options.height,
                    // 存储位置
                    positions = [],
                    len = snakes.length;
            switch (direction) {
                case "left":
                case 0:
                    // 如果在往左移动前是向右移动,则需把蛇头和蛇尾位置调换下
                    if (that._direction == "right") {
                        for (var j = len - 1; j >= 0; j--) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }
                        for (; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i].x - width, positions[i].y);
                        }
                    } else {
                        for (var j = 0; j < len; j++) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }
                        snakes[0].setPosition(snakes[0].x - width, snakes[0].y);
                        for (i = 1; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i - 1].x, positions[i - 1].y);
                        }
                    }
                    this._direction = "left";
                    break;
                case "right":
                case 1:
                    // 如果在往右移动前是向左移动,则需把蛇头和蛇尾位置调换下
                    if (that._direction == "left") {
                        for (var j = len - 1; j >= 0; j--) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }
                        for (; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i].x + width, positions[i].y);
                        }
                    } else {
                        for (var j = 0; j < len; j++) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }
                        snakes[0].setPosition(snakes[0].x + width, snakes[0].y);
                        for (i = 1; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i - 1].x, positions[i - 1].y);
                        }
                    }
                    this._direction = "right";
                    break;
                case "top":
                case 2:
                    if (that._direction == "bottom") {
                        for (var j = len - 1; j >= 0; j--) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }

                        for (; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i].x, positions[i].y - height);
                        }
                    } else {
                        for (var j = 0; j < len; j++) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }

                        snakes[0].setPosition(snakes[0].x, snakes[0].y - height);
                        for (i = 1; i < len; i++) {
                            var snake = snakes[i];

                            snake.setPosition(positions[i - 1].x, positions[i - 1].y);
                        }
                    }
                    this._direction = "top";
                    break;
                case "bottom":
                case 3:
                    if (that._direction == "top") {
                        for (var j = len - 1; j >= 0; j--) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }
                        for (; i < len; i++) {
                            var snake = snakes[i];
                            snake.setPosition(positions[i].x, positions[i].y - height);
                        }
                    } else {
                        for (var j = 0; j < len; j++) {
                            positions.push({
                                x: snakes[j].x,
                                y: snakes[j].y
                            });
                        }

                        snakes[0].setPosition(snakes[0].x, snakes[0].y + height);
                        for (i = 1; i < len; i++) {
                            var snake = snakes[i];

                            snake.setPosition(positions[i - 1].x, positions[i - 1].y);
                        }
                    }
                    this._direction = "bottom";
                    break;
            }
            positions = null;
            this.eat();
            this.isDie();
            return this;
        }

        /**
         添加食物
         */
        Snake.prototype.addFood = function (food) {
            if (!food || food.constructor !== Food) {
                return;
            }
            this._food = food;
            return this;
        }

        /**
         蛇吃食物
         */
        Snake.prototype.eat = function () {
            var that = this,
                    snakeHead = this.snake[0];

            if (snakeHead.x == this._food.x && snakeHead.y == this._food.y) {
                // 将吃到的食物添加到蛇身中去
                this.snake.push(this._food);
                // 获取刚添加进去的那一节身体
                var snakeLen = this.snake.length,
                        lastBody = this.snake[snakeLen - 1],
                        width = this.options.width,
                        height = this.options.height;

                switch (this._direction) {
                    case "left":
                        lastBody.setPosition(this.snake[snakeLen - 2].x + width, this.snake[snakeLen - 2].y);
                        break;
                    case "right":
                        lastBody.setPosition(this.snake[snakeLen - 2].x - width, this.snake[snakeLen - 2].y);
                        break;
                    case "top":
                        lastBody.setPosition(this.snake[snakeLen - 2].x, this.snake[snakeLen - 2].y + height);
                        break;
                    case "bottom":
                        lastBody.setPosition(this.snake[snakeLen - 2].x, this.snake[snakeLen - 2].y - height);
                        break;
                }

                this._food = {};
                this.count++;
                this.options.eated && (({}).toString.call(this.options.eated) == "[object Function]") && this.options.eated.call(this);
            }
            return this;
        }

        /**
         判断蛇是否死亡
         */
        Snake.prototype.isDie = function () {
            var that = this,
                    snakeHead = this.snake[0];
            if (snakeHead.x < 0 || snakeHead.x > this.eleWidth || snakeHead.y < 0 || snakeHead.y > this.eleHeight) {
                this._isDie = true;
                this.options.onDie && (({}).toString.call(this.options.onDie) == "[object Function]") && this.options.onDie.call(this);
            }
            return this;

        }

        window.Snake = Snake;
    })(window, undefined);
</script>
<script>
    ;(function (window, undefined) {
        /**
         贪吃蛇游戏构造函数
         */
        function SnakeGame(ele, options) {
            if (!ele || !ele.nodeName) {
                throw "element is required";
            }
            ;

            this._init(ele, options || {});
        }

        // 存储计时器
        var timer;
        /**
         贪吃蛇游戏初始化函数
         */
        SnakeGame.prototype._init = function (ele, options) {
            var that = this;
            this.ele = ele;
            this.options = {
                // 游戏初始化后的回调函数
                init: function () {
                },
                // 贪吃蛇吃到食物后的回调函数
                eated: function () {
                },
                // 游戏结束时的回调函数
                onDie: function () {
                },
                // 游戏开始时的回调函数
                onStart: function () {
                },
                // 游戏暂停后的回调函数
                onPaused: function () {
                },
                // 游戏继续时的回调回调
                onContinue: function () {
                },
                // 蛇移动的速度的级别
                speed: "slow",
                // 游戏速度时间
                speedTime: {
                    "slow": 600,
                    "medium": 350,
                    "fast": 200
                },
                // 贪吃蛇宽度
                width: 20,
                height: 20,
                // 贪吃蛇蛇头颜色
                snakeHeadColor: "",
                // 蛇默认移动的方向
                direction: "right"
            };

            tool.extend(this.options, true, options);

            // 蛇是否死亡
            this._isDie = false;
            // 判断键盘事件是否绑定
            this._eventBinded = false;

            this.eleWidth = tool.getWidth(this.ele);
            this.eleHeight = tool.getHeight(this.ele);
            // 当前蛇移动的方向
            this.direction = this.options.direction;
            this.gameRuning = false;
            // 游戏速度时间
            this.speedTime = this.options.speedTime;

            if (tool.css(this.ele, "position") === "static") {
                tool.css(this.ele, "position", "relative");
            }

            // 创建蛇
            this.snake = new Snake(this.ele, {
                width: this.options.width,
                height: this.options.height,
                // 贪吃蛇蛇头颜色
                snakeHeadColor: this.options.snakeHeadColor,
                // 蛇默认移动的方向
                direction: this.options.direction,
                eated: function () {
                    var position = that.randomPosition();
                    this.addFood(new Food(that.ele, {
                        x: position.left,
                        y: position.top
                    }));

                    that.options.eated && (typeof that.options.eated == "function") && that.options.eated.call(that);
                },
                onDie: function () {
                    clearInterval(timer);
                    that.options.onDie && (typeof that.options.onDie == "function") && that.options.onDie.call(that);
                }
            });

            // 添加食物
            var position = this.randomPosition();
            this.snake.addFood(new Food(this.ele, {
                x: position.left,
                y: position.top
            }));


            this.options.init && (typeof this.options.init == "function") && this.options.init.call(this);
        }

        /**
         开始游戏
         */
        SnakeGame.prototype.start = function () {
            var that = this;
            if (this.gameRuning) {
                return this;
            }
            clearInterval(timer);
            timer = setInterval(function () {
                that.snake.move(that.direction);
            }, this.speedTime[this.options.speed]);

            this.gameRuning = true;
            this._bindEvent();
            this.options.onStart && (typeof this.options.onStart == "function") && this.options.onStart.call(this);
            return this;
        }

        /**
         暂停游戏
         */
        SnakeGame.prototype.pause = function () {
            var that = this;
            clearInterval(timer);

            this.gameRuning = false;
            this.options.onPaused && (typeof this.options.onPaused == "function") && this.options.onPaused.call(this);
            return this;
        }

        /**
         继续游戏
         */
        SnakeGame.prototype.continueGame = function () {
            var that = this;
            if (this.gameRuning) {
                return this;
            }
            clearInterval(timer);
            timer = setInterval(function () {
                that.snake.move(that.direction);
            }, this.speedTime[this.options.speed]);

            this.gameRuning = true;
            this.options.onContinue && (typeof this.options.onContinue == "function") && this.options.onContinue.call(this);
            return this;
        }

        /**
         获取蛇吃到的食物的个数
         */
        SnakeGame.prototype.getCount = function () {
            return this.snake.count;
        }

        /**
         改变速度
         */
        SnakeGame.prototype.changeSpeed = function (speed) {
            if (!(speed in this.speedTime)) {
                return this;
            }
            var that = this;

            this.options.speed = speed;
            if (this.gameRuning) {
                clearInterval(timer);
                timer = setInterval(function () {
                    that.snake.move(that.direction);
                }, this.speedTime[this.options.speed]);
            }

            return this;
        }

        /**
         绑定键盘事件，可以使用方向键来控制
         */
        SnakeGame.prototype._bindEvent = function () {
            var that = this;
            if (that._eventBinded) {
                return this;
            }
            if (document.addEventListener) {
                document.addEventListener("keydown", function (e) {
                    e = e || window.event;
                    if (!that.gameRuning) {
                        return;
                    }
                    switch (e.keyCode) {
                        case 38:
                            // 上
                            // 如果当前方向为向下，用户直接按上方向键则不向上移动
                            if (that.direction == "bottom") {
                                return;
                            }
                            that.direction = "top";
                            break;
                        case 40:
                            // 下
                            if (that.direction == "top") {
                                return;
                            }
                            that.direction = "bottom";
                            break;
                        case 37:
                            // 左
                            if (that.direction == "right") {
                                return;
                            }
                            that.direction = "left";
                            break;
                        case 39:
                            // 右
                            if (that.direction == "left") {
                                return;
                            }
                            that.direction = "right";
                            break;
                    }

                    if (e.keyCode >= 38 && e.keyCode <= 40) {
                        if (e.preventDefault) {
                            e.preventDefault();
                        } else {
                            e.returnValue = false;
                        }
                        return false;
                    }
                }, false);
            } else {
                document.attachEvent("onkeydown", function (e) {
                    e = e || window.event;
                    switch (e.keyCode) {
                        case 38:
                            // 上
                            // 如果当前方向为向下，用户直接按上方向键则不向上移动
                            if (that.direction == "bottom") {
                                return;
                            }
                            that.direction = "top";
                            break;
                        case 40:
                            // 下
                            if (that.direction == "top") {
                                return;
                            }
                            that.direction = "bottom";
                            break;
                        case 37:
                            // 左
                            if (that.direction == "right") {
                                return;
                            }
                            that.direction = "left";
                            break;
                        case 39:
                            // 右
                            if (that.direction == "left") {
                                return;
                            }
                            that.direction = "right";
                            break;
                    }

                    if (e.keyCode >= 38 && e.keyCode <= 40) {
                        if (e.preventDefault) {
                            e.preventDefault();
                        } else {
                            e.returnValue = false;
                        }
                        return false;
                    }
                }, false);
                that._eventBinded = true;
            }
            return this;
        }

        /**
         生成随机位置
         */
        SnakeGame.prototype.randomPosition = function () {
            var obj = {},
                    /* 根据蛇每一节的宽度计算出整个盒子可以放多少节，以避免蛇在移动时蛇头与食物位置不能重合的情况
                        这样一来蛇始终都可以吃到食物
                    */
                    xgrid = Math.floor((this.eleWidth - this.options.width * 2) / this.options.width),
                    ygrid = Math.floor((this.eleHeight - this.options.height * 2) / this.options.height),
                    randomx = tool.random(1, xgrid),
                    randomy = tool.random(1, ygrid);

            obj.left = randomx * this.options.width;
            obj.top = randomy * this.options.height;
            return obj;
        }

        window.SnakeGame = SnakeGame;
    })(window, undefined);
</script>
<script>
    ;(function (window, undefined) {
        var snakeBox = document.getElementById("snake_box"),
                gradeEle = document.getElementById("grade"),
                speedSelect = document.getElementById("speed_select"),
                startBtn = document.getElementById("start_btn"),
                pauseBtn = document.getElementById("pause_btn"),
                continueBtn = document.getElementById("continue_btn");

        var snakeGame = new SnakeGame(snakeBox, {
            speed: speedSelect.value,
            eated: function () {
                gradeEle.innerHTML = this.getCount();
            },
            onDie: function () {
                alert("Game Over!");
            }
        });

        startBtn.onclick = function () {
            if (!this.started) {
                snakeGame.start();
                this.started = true;
            }
        }
        pauseBtn.onclick = function () {
            snakeGame.pause();
        }
        continueBtn.onclick = function () {
            snakeGame.continueGame();
        }
        speedSelect.onchange = function () {
            console.log(this.value);
            snakeGame.changeSpeed(this.value);
        }
    })(window, undefined);
</script>

<div style="text-align:center;">
</div>

</body>
</html>
