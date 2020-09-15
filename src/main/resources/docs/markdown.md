# WEB后台管理平台API文档


<a name="overview"></a>
## 概览
RESTFUL风格接口


### 版本信息
*版本* : 1.0


### URI scheme
*域名* : localhost:8888  
*基础路径* : /


### 标签

* Swagger接口文档API转Word : Word Controller
* 档案馆相关接口 : Update Controller




<a name="paths"></a>
## 资源

<a name="d5886d21695eb0ac8d3a6fb7af698572"></a>
### Swagger接口文档API转Word
Word Controller


<a name="wordusingget"></a>
#### 将 swagger 文档一键下载为 doc 文档
```
GET /downloadWord
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**url**  <br>*可选*|资源地址|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/downloadWord
```


<a name="getwordusingpost"></a>
#### 将 swagger json文件转换成 word文档并下载
```
POST /fileToWord
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**FormData**|**jsonFile**  <br>*可选*|swagger json file|file|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `multipart/form-data`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/fileToWord
```


###### 请求 formData
```json
"file"
```


<a name="getmarkdownusingget"></a>
#### 将 swagger json字符串转换成Markdown文档下载
```
GET /getMarkdown
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**url**  <br>*可选*|url|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/getMarkdown
```


##### HTTP响应示例

###### 响应 200
```json
"string"
```


<a name="getwordusingpost_1"></a>
#### 将 swagger json字符串转换成 word文档并下载
```
POST /strToWord
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**jsonStr**  <br>*可选*|swagger json string|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/strToWord
```


<a name="getwordusingget"></a>
#### 将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档
```
GET /toWord
```

Caution : 
operation.deprecated


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**download**  <br>*可选*|是否下载|integer (int32)|`1`|
|**Query**|**url**  <br>*可选*|资源地址|string||


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/toWord
```


##### HTTP响应示例

###### 响应 200
```json
"string"
```


<a name="721fc23f765b2e377ae126d2fdad0445"></a>
### 档案馆相关接口
Update Controller


<a name="searchusingget"></a>
#### 查询档案馆ES中的稿件信息
```
GET /search
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/search
```


<a name="updateusingget"></a>
#### 更新档案馆系统中的稿件内容
```
GET /update
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**oriDocId**  <br>*必填*|oriDocId|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/update?oriDocId=0
```


##### HTTP响应示例

###### 响应 200
```json
"object"
```




<a name="definitions"></a>
## 定义

<a name="file"></a>
### File

|名称|说明|类型|
|---|---|---|
|**absolute**  <br>*可选*|**样例** : `true`|boolean|
|**absoluteFile**  <br>*可选*|**样例** : `"[file](#file)"`|[File](#file)|
|**absolutePath**  <br>*可选*|**样例** : `"string"`|string|
|**canonicalFile**  <br>*可选*|**样例** : `"[file](#file)"`|[File](#file)|
|**canonicalPath**  <br>*可选*|**样例** : `"string"`|string|
|**directory**  <br>*可选*|**样例** : `true`|boolean|
|**executable**  <br>*可选*|**样例** : `true`|boolean|
|**file**  <br>*可选*|**样例** : `true`|boolean|
|**freeSpace**  <br>*可选*|**样例** : `0`|integer (int64)|
|**hidden**  <br>*可选*|**样例** : `true`|boolean|
|**lastModified**  <br>*可选*|**样例** : `0`|integer (int64)|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**parent**  <br>*可选*|**样例** : `"string"`|string|
|**parentFile**  <br>*可选*|**样例** : `"[file](#file)"`|[File](#file)|
|**path**  <br>*可选*|**样例** : `"string"`|string|
|**readable**  <br>*可选*|**样例** : `true`|boolean|
|**totalSpace**  <br>*可选*|**样例** : `0`|integer (int64)|
|**usableSpace**  <br>*可选*|**样例** : `0`|integer (int64)|
|**writable**  <br>*可选*|**样例** : `true`|boolean|


<a name="inputstream"></a>
### InputStream
*类型* : object


<a name="resource"></a>
### Resource

|名称|说明|类型|
|---|---|---|
|**description**  <br>*可选*|**样例** : `"string"`|string|
|**file**  <br>*可选*|**样例** : `"[file](#file)"`|[File](#file)|
|**filename**  <br>*可选*|**样例** : `"string"`|string|
|**inputStream**  <br>*可选*|**样例** : `"[inputstream](#inputstream)"`|[InputStream](#inputstream)|
|**open**  <br>*可选*|**样例** : `true`|boolean|
|**readable**  <br>*可选*|**样例** : `true`|boolean|
|**uri**  <br>*可选*|**样例** : `"[uri](#uri)"`|[URI](#uri)|
|**url**  <br>*可选*|**样例** : `"[url](#url)"`|[URL](#url)|


<a name="uri"></a>
### URI

|名称|说明|类型|
|---|---|---|
|**absolute**  <br>*可选*|**样例** : `true`|boolean|
|**authority**  <br>*可选*|**样例** : `"string"`|string|
|**fragment**  <br>*可选*|**样例** : `"string"`|string|
|**host**  <br>*可选*|**样例** : `"string"`|string|
|**opaque**  <br>*可选*|**样例** : `true`|boolean|
|**path**  <br>*可选*|**样例** : `"string"`|string|
|**port**  <br>*可选*|**样例** : `0`|integer (int32)|
|**query**  <br>*可选*|**样例** : `"string"`|string|
|**rawAuthority**  <br>*可选*|**样例** : `"string"`|string|
|**rawFragment**  <br>*可选*|**样例** : `"string"`|string|
|**rawPath**  <br>*可选*|**样例** : `"string"`|string|
|**rawQuery**  <br>*可选*|**样例** : `"string"`|string|
|**rawSchemeSpecificPart**  <br>*可选*|**样例** : `"string"`|string|
|**rawUserInfo**  <br>*可选*|**样例** : `"string"`|string|
|**scheme**  <br>*可选*|**样例** : `"string"`|string|
|**schemeSpecificPart**  <br>*可选*|**样例** : `"string"`|string|
|**userInfo**  <br>*可选*|**样例** : `"string"`|string|


<a name="url"></a>
### URL

|名称|说明|类型|
|---|---|---|
|**authority**  <br>*可选*|**样例** : `"string"`|string|
|**content**  <br>*可选*|**样例** : `"object"`|object|
|**defaultPort**  <br>*可选*|**样例** : `0`|integer (int32)|
|**deserializedFields**  <br>*可选*|**样例** : `"[urlstreamhandler](#urlstreamhandler)"`|[URLStreamHandler](#urlstreamhandler)|
|**file**  <br>*可选*|**样例** : `"string"`|string|
|**host**  <br>*可选*|**样例** : `"string"`|string|
|**path**  <br>*可选*|**样例** : `"string"`|string|
|**port**  <br>*可选*|**样例** : `0`|integer (int32)|
|**protocol**  <br>*可选*|**样例** : `"string"`|string|
|**query**  <br>*可选*|**样例** : `"string"`|string|
|**ref**  <br>*可选*|**样例** : `"string"`|string|
|**serializedHashCode**  <br>*可选*|**样例** : `0`|integer (int32)|
|**userInfo**  <br>*可选*|**样例** : `"string"`|string|


<a name="urlstreamhandler"></a>
### URLStreamHandler
*类型* : object





