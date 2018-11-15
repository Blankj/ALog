# ALog

[![ALog][alogsvg]][alog] [![api][apisvg]][api] [![build][buildsvg]][build] [![License][licensesvg]][license]

## Functions

* 兼容 Android Studio 3.1.0 的 Logcat 完美显示日志
* 兼容 Kotlin
* 可设置 Log 开启和关闭
* 可设置是否输出到控制台（Logcat）
* 可设置 Log 全局 Tag
* 全局 Tag 为空时 Tag 为当前类名
* 可设置 Log 是否显示头部信息
* Log 文件顶部显示设备信息
* Log 头部含有当前线程名
* Log 头部含有当前类及行号和函数名，支持点击跳转
* 可设置 Log 是否写入文件
* 可设置 Log 写入文件目录
* 可设置 Log 写入文件前缀
* 可设置 Log 是否显示边框
* 可设置 Log 控制台过滤器
* 可设置 Log 文件过滤器
* 可设置 Log 栈深度
* 可设置 Log 栈偏移
* 可设置 Log 保存天数
* 可增加 Log 格式化器，默认已支持 Array, Throwable, Bundle, Intent 格式化输出
* 支持控制台长字符串的输出
* 支持多参数输出
* 支持单独写入文件
* 支持 JSON 串的输出
* 支持 XML 串的输出
* 支持 Live Templates
* jar 包不足 5Kb


## API -> [ALog.java][alog.java] -> [Demo][alog.demo]

```
init                     : 初始化
getConfig                : 获取 log 配置
Config.setLogSwitch      : 设置 log 总开关
Config.setConsoleSwitch  : 设置 log 控制台开关
Config.setGlobalTag      : 设置 log 全局 tag
Config.setLogHeadSwitch  : 设置 log 头部信息开关
Config.setLog2FileSwitch : 设置 log 文件开关
Config.setDir            : 设置 log 文件存储目录
Config.setFilePrefix     : 设置 log 文件前缀
Config.setBorderSwitch   : 设置 log 边框开关
Config.setSingleTagSwitch: 设置 log 单一 tag 开关（为美化 AS 3.1 的 Logcat）
Config.setConsoleFilter  : 设置 log 控制台过滤器
Config.setFileFilter     : 设置 log 文件过滤器
Config.setStackDeep      : 设置 log 栈深度
Config.setStackOffset    : 设置 log 栈偏移
Config.setSaveDays       : 设置 log 可保留天数
Config.addFormatter      : 新增 log 格式化器
log                      : 自定义 tag 的 type 日志
v                        : tag 为类名的 Verbose日志
vTag                     : 自定义 tag 的 Verbose日志
d                        : tag 为类名的 Debug 日志
dTag                     : 自定义 tag 的 Debug 日志
i                        : tag 为类名的 Info 日志
iTag                     : 自定义 tag 的 Info 日志
w                        : tag 为类名的 Warn 日志
wTag                     : 自定义 tag 的 Warn 日志
e                        : tag 为类名的 Error日志
eTag                     : 自定义 tag 的 Error日志
a                        : tag 为类名的 Assert 日志
aTag                     : 自定义 tag 的 Assert 日志
file                     : log 到文件
json                     : log 字符串之 json
xml                      : log 字符串之 xml
```


## How to use

`compile 'com.blankj:alog:1.9.1'` or [![Download][jarsvg]][jar]


## Usage

### 初始化

在 Application 的 `onCreate` 函数中初始化，如下

``` java
// init it in ur application
public void initALog() {
    ALog.Config config = ALog.init(this)
            .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
            .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
            .setGlobalTag(null)// 设置 log 全局标签，默认为空
            // 当全局标签不为空时，我们输出的 log 全部为该 tag，
            // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
            .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
            .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
            .setDir("")// 当自定义路径为空时，写入应用的 /cache/log/ 目录中
            .setFilePrefix("")// 当文件前缀为空时，默认为 "alog"，即写入文件为 "alog-MM-dd.txt"
            .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
            .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
            .setConsoleFilter(ALog.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
            .setFileFilter(ALog.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
            .setStackDeep(1)// log 栈深度，默认为 1
            .setStackOffset(0)// 设置栈偏移，比如二次封装的话就需要设置，默认为 0
            .setSaveDays(3)// 设置日志可保留天数，默认为 -1 表示无限时长
            // 新增 ArrayList 格式化器，默认已支持 Array, Throwable, Bundle, Intent 的格式化输出
            .addFormatter(new ALog.IFormatter<ArrayList>() {
                @Override
                public String format(ArrayList list) {
                    return "ALog Formatter ArrayList { " + list.toString() + " }";
                }
            });
    ALog.d(config.toString());
}
```

借助我帮大家写好的 `Live Templates` 大家可以更方便地使用 `ALog`，演示动画如下所示。

![templates][templates]

大家可以下载这个 [Live Templates][templates_jar] 包，然后在 AS 中 `File -> Import Settings` 即可。

关于如何写 `Live Templates`，其实大家可以借鉴安卓自带的 `Live Templates`，然后效仿一下即可。


### 默认初始化下的图例

* `ALog.d("debug");`

![detail][detail]

* `ALog.d("customTag", "debug0", "debug1");`

![args][args]

* `ALog.d(longStr);`

![long][long]

* `ALog.file(longStr);`

![file][file]

![filecontent][filecontent]

* `ALog.json(json);`

![json][json]

* `ALog.xml(xml);`

![xml][xml]


更多使用请运行 demo 来查看。



## Contact

[![jianshu][jianshusvg]][jianshu] [![weibo][weibosvg]][weibo]  [![Blog][blogsvg]][blog] [![QQ0Group][qq0groupsvg]][qq0group] [![QQ1Group][qq1groupsvg]][qq1group]


[alogsvg]: https://img.shields.io/badge/ALog-v1.9.1-brightgreen.svg
[alog]: https://github.com/Blankj/ALog

[apisvg]: https://img.shields.io/badge/API-14+-brightgreen.svg
[api]: https://android-arsenal.com/api?level=14

[buildsvg]: https://travis-ci.org/Blankj/ALog.svg?branch=master
[build]: https://travis-ci.org/Blankj/ALog

[licensesvg]: https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg
[license]: https://github.com/Blankj/ALog/blob/master/LICENSE

[alog.java]: https://github.com/Blankj/ALog/blob/master/alog/src/main/java/com/blankj/ALog.java
[alog.demo]: https://github.com/Blankj/ALog/blob/master/app/src/main/java/com/blankj/alog/ALogActivity.java
[jarsvg]: https://img.shields.io/badge/download-jar--4Kb-brightgreen.svg
[jar]: https://jcenter.bintray.com/com/blankj/alog/1.9.1/alog-1.9.1-sources.jar
[detail]: https://raw.githubusercontent.com/Blankj/ALog/master/art/detail.png
[args]: https://raw.githubusercontent.com/Blankj/ALog/master/art/args.png
[long]: https://raw.githubusercontent.com/Blankj/ALog/master/art/long.png
[file]: https://raw.githubusercontent.com/Blankj/ALog/master/art/file.png
[filecontent]: https://raw.githubusercontent.com/Blankj/ALog/master/art/filecontent.png
[json]: https://raw.githubusercontent.com/Blankj/ALog/master/art/json.png
[xml]: https://raw.githubusercontent.com/Blankj/ALog/master/art/xml.png
[templates]: https://raw.githubusercontent.com/Blankj/ALog/master/art/alog.gif
[templates_jar]: https://raw.githubusercontent.com/Blankj/ALog/master/art/alog_templates.jar

[jianshusvg]: https://img.shields.io/badge/简书-Blankj-brightgreen.svg
[jianshu]: http://www.jianshu.com/u/46702d5c6978

[weibosvg]: https://img.shields.io/badge/weibo-__Blankj-brightgreen.svg
[weibo]: http://weibo.com/3076228982

[blogsvg]: https://img.shields.io/badge/Blog-Blankj-brightgreen.svg
[blog]: http://blankj.com

[qq0groupsvg]: https://img.shields.io/badge/QQ0群(满)-74721490-fba7f9.svg
[qq0group]: https://shang.qq.com/wpa/qunwpa?idkey=62baf2c3ec6b0863155b0c7a10c71bba2608cb0b6532fc18515835e54c69bdd3

[qq1groupsvg]: https://img.shields.io/badge/QQ1群-25206533-fba7f9.svg
[qq1group]: https://shang.qq.com/wpa/qunwpa?idkey=d906789f84484465e2736f7b524366b4c23afeda38733d5c7b10fc3f6e406e9b