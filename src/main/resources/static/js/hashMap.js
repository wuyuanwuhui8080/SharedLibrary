/**
 * 定义js版的hashmap
 * @author 博博大人
 * @time 2019/1/31 16:31
 */
function HashMap() {
    //Map 大小
    var size = 0;
    //对象
    var entry = new Object();

    // 存
    this.put = function (key, value) {
        if (!this.containsKey(key)) {
            size++;
        }
        entry[key] = value;
    }

    // 取
    this.get = function (key) {
        return this.containsKey(key) ? entry[key] : null;
    }

    //删除
    this.remove = function (key) {
        if (this.containsKey(key) && (delete entry[key])) {
            size--;
        }
    }

    //是否包含 Key
    this.containsKey = function (key) {
        return (key in entry);
    }

    //是否包含 Value
    this.containsValue = function (value) {
        for (var prop in entry) {
            if (entry[prop] == value) {
                return true;
            }
        }
        return false;
    }

    //所有 Value
    this.values = function () {
        var values = new Array();
        for (var prop in entry) {
            values.push(entry[prop]);
        }
        return values;
    }

    //所有 Key
    this.keys = function () {
        var keys = new Array();
        for (var prop in entry) {
            keys.push(prop);
        }
        return keys;
    }

    // Map Size
    this.size = function () {
        return size;
    }

    // 清空
    this.clear = function () {
        size = 0;
        entry = new Object();
    }
}

/*var map = new HashMap(); */

/*
map.put("A","1");
map.put("B","2");
map.put("A","5");
map.put("C","3");
map.put("A","4");
*/

/*
alert(map.containsKey("XX"));
alert(map.size());
alert(map.get("A"));
alert(map.get("XX"));
map.remove("A");
alert(map.size());
alert(map.get("A"));
*/

/** 同时也可以把对象作为 Key **/
/*
var arrayKey = new Array("1","2","3","4");
var arrayValue = new Array("A","B","C","D");
map.put(arrayKey,arrayValue);
var value = map.get(arrayKey);
for(var i = 0 ; i < value.length ; i++)
{
    //alert(value[i]);
}
*/
