/**
 * 定义js版的arraylist
 * @author 博博大人
 * @time 2019/1/31 16:45
 */
function ArrayList() {
    // 初始化一个空数组
    this.arr = [],
        // 获取长度
        this.size = function () {
            return this.arr.length;
        },
        // 添加一个元素
        this.add = function () {
            if (arguments.length == 1) {
                this.arr.push(arguments[0]);
            } else if (arguments.length >= 2) {
                var deleteItem = this.arr[arguments[0]];
                this.arr.splice(arguments[0], 1, arguments[1], deleteItem)
            }
            return this;
        },
        // 根据下标获取元素
        this.get = function (index) {
            return this.arr[index];
        },
        // 根据下标删除
        this.removeIndex = function (index) {
            this.arr.splice(index, 1);
        },
        // 根据内容删除
        this.removeObj = function (obj) {
            this.removeIndex(this.indexOf(obj));
        },
        // 获取下标
        this.indexOf = function (obj) {
            for (var i = 0; i < this.arr.length; i++) {
                if (this.arr[i] === obj) {
                    return i;
                }
                ;
            }
            return -1;
        },
        // 判断长度是否是大于0
        this.isEmpty = function () {
            return this.arr.length == 0;
        },
        // 清空全部元素
        this.clear = function () {
            this.arr = [];
        },
        this.contains = function (obj) {
            return this.indexOf(obj) != -1;
        },
        // 获取所有元素
        this.getAll = function () {
            return this.arr;
        }

};


/**
 用法和java的ArrayList的用法基本一致，就不过多解释怎么用
 */