# ***荔枝代码生成器***

## 简介

​		本教程旨在为开发人员提供详细的代码指南，了解代码生成器，助你从零开始构建一个Java代码生成器。

​		本教程分为三个主要部分，每个部分都会介绍相关的工具和技术且每个部分循环递进，可以说是三个教程，让你一次性学三个教程！！！

#### 第一部分：Velocity模板引擎教程

​			代码生成的第一步是理解和使用模板引擎，而Velocity是广泛应用的Java模板引擎之一。在本部分中，我会详细介绍Velocity的基础知识，包括模板的语法、占位符、变量传递等。通过学习Velocity，让大家了解velocity的模板，能够自己独立生成一个基本的模版。

#### 第二部分：Java生成基本的增删改查（CRUD）代码

​		在掌握Velocity的基础上，第二部分教程将深入到如何通过Java代码实现基础的增删改查代码生成器。有了此代码生成器，大家能够快速的生成自己的项目，了解代码生成器，自己也可以定制专属于自己的代码生成器。

#### 第三部分：生成若依应用代码生成器

​		第三部分教程则是本课程的重点，即如何为若依App框架生成前端的uniapp代码。若依作为一个流行的开源Java开发框架，已经集成了许多常见的开发功能，其中包括代码生成器功能，但是若依APP并没有生成uniapp的模版，我们将一步一步了解若依的代码生成模块，并在此模块基础上，生成若依APP的代码生成器。

​		同时，我们工作室已经坐好若依APP的代码生成器，且已经在若依APP的推荐项目中展示（https://gitee.com/SpringSunYY/LZ-RuoYi-App）

​		![image-20240915203625037](./assets/image-20240915203625037.png)

### 总结

​		通过本教程，你将逐步掌握Velocity模板引擎的使用技巧，学会如何通过Java代码生成基础的增删改查功能，并最终为若依APP构建代码生成器。教程中不仅涵盖了各步骤的理论知识，还包括了大量的实践操作和技巧，旨在帮助你提高开发效率，减少重复工作，成为更加高效的Java开发人员。



## 第一部分：Velocity模板引擎教程

### 一、常见的Java模板引擎

#### 1. **Velocity**

Velocity是一个轻量级的模板引擎，专为在Java项目中动态生成内容而设计。它被广泛用于生成HTML页面、XML文件、配置文件，尤其是在代码生成器领域。

**特点**：

- 语法简单，容易上手
- 高度可扩展，适合各种类型的模板生成需求
- 可与Java无缝集成，使用`VelocityContext`传递数据到模板中
- 性能较好，适合大型代码生成器的开发

**优点**：

- **广泛使用**：Velocity是成熟的技术，文档丰富，社区支持良好，在代码生成器领域应用广泛。
- **灵活性强**：其模板灵活性较强，可以适用于各种代码生成场景，比如生成Controller、Service、Mapper等层的代码。
- **与Java高度兼容**：Velocity与Java深度集成，通过简单的API即可将数据传递到模板中，从而生成Java代码。
- **学习曲线低**：相对简单的语法，使其更容易掌握，适合快速上手并应用在代码生成器中。

#### 2. **Freemarker**

Freemarker是另一个常用的Java模板引擎，专门设计用于生成文本（包括HTML、XML、Java代码等）。

**特点**：

- 强大的表达式语言，支持复杂的逻辑控制
- 比Velocity语法稍复杂，但功能更加丰富
- 有丰富的内置函数和控制指令

**优点**：

- **功能丰富**：Freemarker比Velocity提供更多的模板指令和内置函数，适合复杂模板的处理。
- **灵活的条件和循环控制**：Freemarker提供强大的控制结构（如条件语句和循环），可处理复杂的数据和逻辑。

**缺点**：

- **学习曲线较高**：相比Velocity，Freemarker的语法更加复杂，需要更多的时间去学习和理解。
- **性能稍逊**：在某些场景下，Freemarker的运行性能不如Velocity，特别是生成大量代码时。

#### 3. **Thymeleaf**

Thymeleaf主要用于生成动态HTML页面，适合Web应用中的视图层。

**特点**：

- 主要用于生成HTML、XML，不太适合Java代码生成
- 与Spring框架集成紧密，是Spring MVC的首选模板引擎

**优点**：

- **Web应用友好**：Thymeleaf在生成Web页面时非常高效，与前端代码结合紧密，适用于视图层的动态渲染。
- **易于调试**：由于Thymeleaf的模板在未渲染时也是合法的HTML文档，因此调试和设计更为简单。

**缺点**：

- **不适合代码生成器**：Thymeleaf的设计初衷是生成HTML，而不是Java代码，因此在代码生成器场景中，使用Thymeleaf显得不太合适。

### 二、为什么选择Velocity

选择Velocity作为Java代码生成器中的模板引擎，主要基于以下几个关键因素：

1. **简单且灵活的语法** Velocity的语法相对简单，容易学习和应用。它提供了一套基本的模板标记（如变量、条件、循环等），适合用于生成多种类型的代码模板。对于希望快速上手代码生成的开发者，Velocity的学习曲线低，非常适合初学者和团队使用。
2. **性能优异** 代码生成器通常需要处理大量模板文件，尤其是在生成多层结构的应用代码时（如Controller、Service、Mapper等）。Velocity的性能表现相对优秀，适合大规模代码生成任务。
3. **与Java的深度集成** Velocity与Java高度集成，开发者可以通过`VelocityContext`将Java对象传递给模板，从而在模板中动态生成代码。其无缝对接特性使得它非常适合用于基于Java的代码生成器。
4. **成熟且社区支持良好** Velocity作为一个成熟的模板引擎，有着丰富的文档和社区支持。无论是遇到问题，还是希望获取最佳实践，开发者都能轻松找到资源。同时，许多已有的代码生成器项目（如MyBatis Generator）也选择了Velocity作为其模板引擎，证明了其在该领域的实用性。
5. **轻量级和可扩展性** Velocity是一个轻量级引擎，易于集成到各种Java应用中。而且它高度可扩展，开发者可以自定义指令或函数，适应各种特殊场景的代码生成需求。
6. ***最最最重要因素**：因为若依的代码生成器的模版引擎是velocity，所以我们选择velocity。*



### 三、快速入门

#### 1. 需求

使用velocity定义一个html模板 , 将动态数据填充到模板中 , 形成一个完整的html页面。我们将在这个生成这个模版了解velocity的基本语法。

#### 2. 步骤分析

1. 创建项目(maven)
2. 引入依赖
3. 定义模板
4. 输出html



#### 3.代码实现

##### 3.1 创建Maven工程

![image-20240915204646397](./assets/image-20240915204646397.png)



##### 3.2 引入坐标

首先引入坐标:

```xml
    <dependencies>
        <!-- 版本选择2.3 与若依一致 -->
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.3</version>
        </dependency>
        <!--   hutool工具箱，操作文件     -->
        <!--  https://hutool.cn/docs/index.html#/ -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.8.8</version>
        </dependency>
    </dependencies>
```

##### 3.3 编写模板

在项目resources目录下创建vm\demo.html.vm文件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
 
hello , ${name} !
 
</body>
</html>
```

##### 3.4 输出结果

在Main.class下面新增方法，测试模版：

```java
public void test01() throws Exception {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建Velocity容器
        VelocityContext context = new VelocityContext();
        context.put("name", "荔枝");
        //加载模板
        Template tpl = Velocity.getTemplate("vm/demo.html.vm", "UTF-8");

        //文件输出位置
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "generated" + File.separator + "demo.html";
        // 创建文件对象
        File outputFile = new File(outputPath);

        // 创建目录
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // 创建文件写入器
        FileWriter fw = new FileWriter(outputFile);
        //合并数据到模板
        tpl.merge(context, fw);
        //释放资源
        fw.close();
    }
```

![image-20240915213509158](./assets/image-20240915213509158.png)

#### 4.运行原理

​		Velocity解决了如何在后台程序和网页之间传递数据的问题，后台代码和视图之间相互独立，一方的修改不影响另一方 .

​		他们之间是通过环境变量（Context）来实现的，网页制作一方和后台程序一方相互约定好对所传递变量的命名约定，比如上个程序例子中的site, name变量，它们在网页上就是$name ,$site 。

​		只要双方约定好了变量名字，那么双方就可以独立工作了。无论页面如何变化，只要变量名不变，那么后台程序就无需改动，前台网页也可以任意由网页制作人员修改。这就是Velocity的工作原理。

​		![](./assets/%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86.drawio.png)



### 四、基本语法

##### VTL 基本介绍

Velocity Template Language (VTL) 是Velocity模板引擎所使用的模板语言，用于定义模板的结构和逻辑控制，帮助开发人员动态生成内容。VTL 语法简单直观，可以通过占位符、变量、指令等实现动态内容生成。VTL 是Velocity的核心部分，支持从简单的文本替换到复杂的控制结构。

VTL的语句分为4大类：**注释** , **非解析内容** , **引用**和**指令**。

##### 1. **变量（Variables）**
VTL中的变量使用 `${}` 语法表示。通过将数据对象传递到`VelocityContext`，可以在模板中使用这些对象的属性或方法。

**示例**：

```java
#set($name = "荔枝1")
Hello, ${name}!
```
**输出**：

```
hello , 荔枝 !

Hello,荔枝1
```

这里的 `$name` 是一个变量，它的值被设置为 "荔枝1"，然后在模板中输出。

##### 2. **注释（Comments）**
VTL支持三种类型的注释：单行注释和多行注释。注释的内容不会出现在最终生成的代码中。

###### 单行注释：
```vtl
##因为刚才已经合并name
```

###### 多行注释：
```vtl
#*
 这里已经重新
 设置name
*#
```

###### 文档注释：

```
#**
文档注释内容1
文档注释内容2
*#
```

##### 3.**变量进阶**

###### 3.1.引用变量

引用语句就是对引擎上下文对象中的属性进行操作。语法方面分为常规语法($属性)和正规语法(${属性})。

**语法**：

```
$变量名, 若上下文中没有对应的变量，则输出字符串"$变量名"
${变量名},若上下文中没有对应的变量，则输出字符串"${变量名}" 
$!变量名, 若上下文中没有对应的变量，则输出空字符串"" 
$!{变量名}, 若上下文中没有对应的变量，则输出空字符串""
```

**示例**：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    ##因为刚才已经合并name
hello , ${name}!
    #*
     这里已经重新
     设置name
    *#
###set($name="荔枝1")
##Hello,${name}
    #**
     文档注释内容1
     文档注释内容2
     *#
<h1>----------------------------</h1>
<h1>引用变量</h1>
常规用法：$name<br>
正规用法：${name}<br>

##如果获取的变量不存在，表达式会原样展示，如果不想展示，可以使用$!变量名
没有变量：${name1}<br>
常规用法：$name1<br>
常规用法：$!name<br>
正规用法：$!{name}<br>
</body>
</html>
```



###### 3.2.属性引用

属性引用可以把他理解为一个类的变量为public，直接调用这个变量或者理解为一个map类型，key——value。

**语法**：

```
$变量名.属性, 	若上下文中没有对应的变量，则输出字符串"$变量名.属性"
${变量名.属性}	若上下文中没有对应的变量，则输出字符串"${变量名.属性}"
$!变量名.属性	若上下文中没有对应的变量，则输出字符串""
$!{变量名.属性}	若上下文中没有对应的变量，则输出字符串""
```

**实现**：

首先创建一个用户类作为我们的变量：

```java
public class UserInfo {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

在容器中添加用户信息：

```java
        //用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("YY");
        userInfo.setPassword("YYLZ");
        context.put("userInfo",userInfo);
```

**事例**：

```html
<h1>----------------------------</h1>
<h1>引用属性</h1>
常规语法 : $userInfo.username --- $userInfo.password
<br>
正规语法 : ${userInfo.username} --- ${userInfo.password}
```

###### 3.3.方法引用

方法引用实际就是指方法调用操作，关注点**返回值**和**参数** , 方法的返回值将输出到最终结果中，而他这个方法也就是你传入的变量所拥有的方法。

**语法**：

```
$变量名.方法([入参1[, 入参2]*]?), 常规写法
${变量名.方法([入参1[, 入参2]*]?)}, 正规写法
 
$!变量名.方法([入参1[, 入参2]*]?), 常规写法
$!{变量名.方法([入参1[, 入参2]*]?)}, 正规写法
```

**实现**：

```html
<h1>----------------------------</h1>
<h1>方法属性</h1>
<h2>判断常过来的name是否为空</h2>
${name.isEmpty()}<br>
<h2>根据,分割name</h2>
${name.split(",")}<br>
<h2>获取用户名</h2>
${userInfo.getUsername()}
</body>
```



##### 4. **指令**

指令主要用于定义重用模块、引入外部资源、流程控制。指令以 `#` 作为起始字符。

###### 4.1.set

`#set` 指令用于给变量赋值。你可以通过它来初始化或者更改变量的值。可以在页面中声明定义变量。

**语法**：

```
#set($变量 = 值)
```

**示例**：

```java
<h1>-------------------</h1>
<h1>指令</h1>
<h2>set定义</h2>

    #set($str = "hello world")
    #set($int = 1)
    #set($arr = [1,2])
    #set($boolean = true)
    #set($map = {"key1":"value1", "key2":"value2"})

    ## 在字符串中也可以引用之前定义过的变量
    #set($str2 = "$str , how are you !")
    #set($str3 = '$str , how are you !')

<h2>获取set指令定义的变量</h2>
str: ${str}<br>
int: ${int}<br>
arr: ${arr}<br>
boolean: ${boolean}<br>
mar: ${map.key1}--${map.key2}<br>
str2: ${str2}<br>
str3: ${str3}<br>
```
###### 4.2 **条件语句**

VTL支持条件语句，例如 `#if`、`#else` 和 `#elseif`，用于实现基本的逻辑判断。

**语法**：

```
#if(判断条件)
  .........
#elseif(判断条件)
  .........
#else
  .........
#end 
```

**示例**：

```java
<h2>------------------</h2>
<h2>条件语句</h2>
    #if(${name.isEmpty()})
    我没有名字<br>
    #elseif(!${name.isEmpty()})
    我的名字是：${name}
    #end
<br>
    #if(${userInfo.getPassword().equals("YYLZ")})
    密码正确
    #end
```
###### 4.3. **循环语句**
使用 `#foreach` 指令来遍历集合或数组，生成动态内容。可以用来遍历列表、数组等数据结构。

**格式：**

```
#foreach($item in $items)
    ..........
    [#break]
#end
```

$items : 需要遍历的对象或者集合

如果items的类型为map集合, 那么遍历的是map的value

$item : 变量名称, 代表遍历的每一项

#break : 退出循环

内置属性 :

$foreach.index : 获取遍历的索引 , 从0开始

$foreach.count : 获取遍历的次数 , 从1开始

hasNext()判断是否有下一个元素

**示例**（遍历数组）：

创建一个list数组来作为遍历数据：

```java
        //用户数组
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo info = new UserInfo();
            info.setUsername("荔枝"+i);
            info.setPassword("YY"+i);
            userInfoList.add(info);
        }
        context.put("userInfoList",userInfoList);
```
做一个表格来遍历userInfoList：

```html
<h2>------------------</h2>
<h2>循环</h2>
<h3>遍历数组</h3>
<table border="1px" align="left">
    <tr>
        <td>编号</td>
        <td>用户名</td>
        <td>密码</td>
        <td>操作</td>
    </tr>
    #foreach($user in $userInfoList)
        <tr>
            <td>${foreach.index}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>
                <a href="">编辑</a>
                <a href="">删除</a>
            </td>
        </tr>
    #end
</table>
```

**事例**（遍历map）：

```html
<h2>------------------</h2>
<h3>遍历map集合</h3>
<h4>遍历值</h4>
    #foreach($value in $map)
        $value<br>
    #end
<h2>------------------</h2>
<h3>遍历键值对</h3>
    #foreach($entry in $map.entrySet())
        $entry.key --- $entry.value<br>
    #end
```



###### 4.4.宏指令

**作用 :** 定义重用模块（可带参数）

**语法 :**

定义语法

```html
#macro(宏名 [$arg]?)
   .....
#end
```

**示例 :**

```html

    ##定义宏指令
    #macro(table $userList)
    <table border="1px" align="center">
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>操作</td>
        </tr>
        #foreach($user in $userList)
            <tr>
                <td>${foreach.index}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <a href="">编辑</a>
                    <a href="">删除</a>
                </td>
            </tr>
        #end
    #end
    <h1 style="margin: auto; text-align: center">使用宏指令的事例--------------</h1>
    #table($userInfoList)
    #table($userInfoList)
```

![image-20240922230443640](./assets/image-20240922230443640.png)

##### 5.引入资源

###### 5.1.#include

作用 : 引入外部资源 , 引入的资源不会被引擎所解析

**语法** : #include(resource)

- resource可以为**单引号或双引号的字符串**，也可以为**$变量**，内容为外部资源路径。
- 注意 : 路径如果为相对路径，则以引擎配置的文件加载器加载路径作为参考

**事例**：

​		首先创建一个文件在demo同级目录创建一个文件demo2.html.vm

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    ##因为刚才已经合并name
hello , ${name}!

</body>
</html>
```

```html
<h1>引入资源------------------</h1>
    ##    因为我们这里是使用文件加载器加载路径，所以需要和文件加载器路径保持一致
    #include("vm/demo2.html.vm")
```

![image-20240922222325354](./assets/image-20240922222325354.png)



###### 5.2.\#parse

**作用 :** 引入外部资源 , 引入的资源将被引擎所解析

**语法 :** `#parse(resource)`

> - resource可以为**单引号或双引号的字符串**，也可以为**$变量**，内容为外部资源路径。
> - 注意 : 路径如果为相对路径，则以引擎配置的文件加载器加载路径作为参考系

**事例**：

```html
<h1>引入资源parse------------------</h1>
    #parse("vm/demo2.html.vm")
```

![image-20240922222738171](./assets/image-20240922222738171.png)



###### 5.3.\#define

**作用 :** `定义重用模块（不带参数）`

**语法 :**

```html
#define($模块名称)
	模块内容
#end
```

**事例**：

```html
#define($table)
##<h1>定义模块的事例--------------</h1>
<table border="1px" align="center" >
    <tr>
        <td>编号</td>
        <td>用户名</td>
        <td>密码</td>
        <td>操作</td>
    </tr>
    #foreach($user in $userInfoList)
        <tr>
            <td>${foreach.index}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>
                <a href="">编辑</a>
                <a href="">删除</a>
            </td>
        </tr>
    #end
</table>
#end
<h1 style="margin: auto; text-align: center">使用模块的事例--------------</h1>
$table
$table
```

![image-20240922224354804](./assets/image-20240922224354804.png)



###### 5.4.\#evaluate

**作用 :** 动态计算 , 动态计算可以让我们在字符串中使用变量

**语法 :** `#evalute("计算语句")`

**示例 :**

```html
<h1 style="margin: auto; text-align: center">动态计算---------------</h1>
    #evaluate("#if($name=='YY') <h1>YY</h1>  #else
    <h1>荔枝</h1> #end")
```

