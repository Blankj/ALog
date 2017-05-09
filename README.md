# ALog

[![ALog][alogsvg]][alog] [![api][apisvg]][api] [![build][buildsvg]][build] [![License][licensesvg]][license]

## Functions

* 可设置Log开启和关闭
* 可设置Log全局Tag
* 全局Tag为空时Tag为当前类名
* 可设置Log是否显示头部信息
* Log头部含有当前线程名
* Log头部含有当前类及行号和函数名，支持点击跳转
* 可设置Log是否写入文件
* 可设置Log写入文件目录
* 可设置Log是否显示边框
* 可设置Log过滤器
* 支持长字符串的输出
* 支持多参数输出
* 支持单独写入文件
* 支持JSON串的输出
* 支持XML串的输出
* jar包不足5Kb


## API→[ALog.java][alog.java]→[Demo][alog.demo]

```
Builder.setLogSwitch     : 设置log总开关
Builder.setGlobalTag     : 设置log全局tag
Builder.setLogHeadSwitch : 设置log头开关
Builder.setLog2FileSwitch: 设置log文件开关
Builder.setDir           : 设置log文件存储目录
Builder.setBorderSwitch  : 设置log边框开关
Builder.setLogFilter     : 设置log过滤器
v                        : Verbose日志
d                        : Debug日志
i                        : Info日志
w                        : Warn日志
e                        : Error日志
a                        : Assert日志
file                     : log到文件
json                     : log字符串之json
xml                      : log字符串之xml
```


## How to use

`compile 'com.blankj:alog:1.0.0'` or [![Download][jarsvg]][jar]


## Usage

### 初始化

在Application的`onCreate`函数中初始化，如下

``` java
@Override
public void onCreate() {
    super.onCreate();
    ALog.Builder builder = new ALog.Builder(this);
}
```

当然，ALog还支持多参数配置，具体如下

``` java
new ALog.Builder(sInstance)
        .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，默认开
        .setGlobalTag(null)// 设置log全局标签，默认为空
        // 当全局标签不为空时，我们输出的log全部为该tag，
        // 为空时，如果传入的tag为空那就显示类名，否则显示tag
        .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
        .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
        .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
        .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
        .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose
```


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


更多使用请运行demo来查看。



## Contact

[![jianshu][jianshusvg]][jianshu] [![weibo][weibosvg]][weibo]  [![Blog][blogsvg]][blog] [![QQ0Group][qq0groupsvg]][qq0group] [![QQ1Group][qq1groupsvg]][qq1group]




[alogsvg]: https://img.shields.io/badge/ALog-v1.0.0-blue.svg
[alog]: https://github.com/Blankj/ALog

[apisvg]: https://img.shields.io/badge/API-11+-blue.svg
[api]: https://android-arsenal.com/api?level=11

[buildsvg]: https://travis-ci.org/Blankj/ALog.svg?branch=master
[build]: https://travis-ci.org/Blankj/ALog

[licensesvg]: https://img.shields.io/badge/License-Apache--2.0-blue.svg
[license]: https://opensource.org/licenses/apache2.0.php

[alog.java]: https://github.com/Blankj/ALog/blob/master/alog/src/main/java/com/blankj/ALog.java
[alog.demo]: https://github.com/Blankj/ALog/blob/master/app/src/main/java/com/blankj/alog/ALogActivity.java
[jarsvg]: https://img.shields.io/badge/download-jar--4Kb-brightgreen.svg
[jar]: https://jcenter.bintray.com/com/blankj/alog/1.0.0/alog-1.0.0-sources.jar
[detail]: https://raw.githubusercontent.com/Blankj/ALog/master/img/detail.png
[args]: https://raw.githubusercontent.com/Blankj/ALog/master/img/args.png
[long]: https://raw.githubusercontent.com/Blankj/ALog/master/img/long.png
[file]: https://raw.githubusercontent.com/Blankj/ALog/master/img/file.png
[filecontent]: https://raw.githubusercontent.com/Blankj/ALog/master/img/filecontent.png
[json]: https://raw.githubusercontent.com/Blankj/ALog/master/img/json.png
[xml]: https://raw.githubusercontent.com/Blankj/ALog/master/img/xml.png

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