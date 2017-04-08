# ALog

## Functions

* 可设置Log开启和关闭
* 可设置Log全局Tag
* 全局Tag为空时Tag为当前类名
* 可设置Log是否显示头部信息
* Log头部含有当前线程名
* Log头部含有当前类及行号和函数名，支持点击跳转
* 可设置Log是否写入文件
* 可设置Log是否显示边框
* 可设置Log过滤器
* 支持长字符串的输出
* 支持多参数输出
* 支持单独写入文件
* 支持JSON串的输出
* 支持XML串的输出
* jar包不足5Kb


## How to use

``` groovy
compile 'com.blankj:alog:0.0.1'
```

或者下载如下jar包

 [ ![Download](https://img.shields.io/badge/download-4k-brightgreen.svg) ](https://jcenter.bintray.com/com/blankj/alog/0.0.1/alog-0.0.1-sources.jar)


## Usage

在Application的`onCreate`函数中初始化，如下
```
@Override
public void onCreate() {
    super.onCreate();
    ALog.Builder builder = new ALog.Builder(this);
}
```

当然，ALog还支持多参数配置，具体如下
```
new ALog.Builder(this)
        .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，默认开
        .setGlobalTag("")// 设置log全局标签，默认为空
                         // 当全局标签不为空时，我们输出的log全部为该tag，
                         // 为空时，如果传入的tag为空那就显示类名，否则显示tag
        .setLogHeadSwitch(true)// 设置log头部是否显示，默认显示
        .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
        .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
        .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose
```







## License

```
Copyright [2017] [Blankj]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```