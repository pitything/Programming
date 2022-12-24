#  JavaWeb

## Servlet

### Servlet配置

- 编写一个类去继承 HttpServlet 接口 

- 实现 service 方法，处理请求，并响应数据 

  ```java
  public class HelloServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
      System.out.println("Hello Servlet 被访问了");
    }
  }
  ```

- 到 web.xml 中去配置 servlet 程序的访问地址，servlet3.0支持使用注解进行配置

  ```xml
  <servlet> 
    <!--servlet-name 标签 Servlet 程序起一个别名（一般是类名） -->
    <servlet-name>HelloServlet</servlet-name> 
    <!--servlet-class 是 Servlet 程序的全类名-->
    <servlet-class>com.atguigu.servlet.HelloServlet</servlet-class>
  </servlet> 
  <!--servlet-mapping 标签给 servlet 程序配置访问地址-->
  <servlet-mapping> 
    <!--servlet-name 标签的作用是告诉服务器，我当前配置的地址给哪个 Servlet 程序使用-->
    <servlet-name>HelloServlet</servlet-name> 
    <!--url-pattern 标签配置访问地址 <br/> / 斜杠在服务器解析的时候，表示地址为：http://ip:port/工程路径 <br/> /hello 表示地址为：http://ip:port/工程路径/hello <br/> -->
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
  ```

  ```java
  @WebServlet("/hello")
  class HelloServlet extends HttpServlet {} 
  ```

### 设置编码

- request设置编码
  - `tomcat8之前`，设置编码：

    - get请求方式：

      - 方式1：代码修改

        ```java
        byte[] fnameBytes = request.getParameter("fname").getBytes("ISO-8859-1");
        String fname2 = new String(fnameBytes, "UTF-8");
        ```

      - 方式2：修改tomcat下的server.xml配置文件，增加 `URIEncoding="UTF-8"`

        <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221114175334931.png" alt="image-20221114175334931" style="zoom:50%;" />

    - post请求方式：

    ```java
    request.setCharacterEncoding("UTF-8");
    ```

  - `tomcat8开始`，设置编码，`只需要针对post方式`， 设置编码(post)这一句代码必须在所有的获取参数动作之前

  ```java
  request.setCharacterEncoding("UTF-8");
  ```

- reponse设置编码

  ```java
  response.setContentType("text/html;charset=UTF-8");
  ```

### Servlet的继承关系

> 重点查看的是服务方法（service()）

#### 继承关系

- javax.servlet.Servlet接口
  - javax.servlet.GenericServlet抽象类
    - javax.servlet.http.HttpServlet抽象子类

#### 相关方法

##### javax.servlet.Servlet接口:

```java
void init(config) - 初始化方法
void service(request,response) - 服务方法
void destory() - 销毁方法
```

##### javax.servlet.GenericServlet抽象类：

```java
void service(request,response) - 仍然是抽象的
```

##### javax.servlet.http.HttpServlet 抽象子类：

```java
void service(request,response) - 不是抽象的
```

1. 获取请求的方式：

   ```java
   String method = req.getMethod(); 
   ```

2. 各种if判断，根据请求方式不同，决定去调用不同的do方法

  ```java
  if (method.equals("GET")) {
  	this.doGet(req,resp);
  } else if (method.equals("HEAD")) {
  	this.doHead(req, resp);
  } else if (method.equals("POST")) {
  	this.doPost(req, resp);
  } else if (method.equals("PUT")) {
    ...
  }
  ```

3. 在HttpServlet这个抽象类中，do方法都差不多：都是直接报个异常：`http.method_XXX_not_supported`

  ```java
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String protocol = req.getProtocol();
    String msg = lStrings.getString("http.method_get_not_supported");
    if (protocol.endsWith("1.1")) {
  	  resp.sendError(405, msg);
    } else {
  	  resp.sendError(400, msg);
    }
  }
  ```

##### 小结：

- 继承关系： HttpServlet -> GenericServlet -> Servlet
- Servlet中的核心方法： init() , service() , destroy()
- 服务方法： 当有请求过来时，`service方法`会自动响应（其实是tomcat容器调用的），在HttpServlet中我们会去分析请求的方式：到底是get、post、head还是delete等等，然后再决定调用的是哪个do开头的方法，那么在HttpServlet中这些do方法默认都是405的实现风格-要我们子类去实现对应的方法，否则默认会报405错误
- 因此，我们在新建Servlet时，我们才会去考虑请求方法，从而决定重写哪个do方法

### Servlet的生命周期
- 生命周期：从出生到死亡的过程就是生命周期。对应Servlet中的三个方法：init(), service(), destroy()

  第一次接收请求时，这个Servlet会进行`实例化`(调用构造方法)、`初始化`(调用init())、然后`服务`(调用service())从第二次请求开始，每一次都是服务当容器关闭时，其中的所有的servlet实例会被`销毁`，调用销毁方法

- 注意：
  - Servlet实例tomcat只会`创建一个`，所有的请求都是这个实例去响应。
  - 默认情况下，第一次请求时，tomcat才会去实例化，初始化，然后再服务，这样提高系统的启动速度，但是第一次请求时，耗时较长。如果需要提高系统的启动速度，当前默认情况就是这样。如果需要提高响应速度，我们应该设置Servlet的初始化时机。
  - Servlet的`初始化时机`：默认是第一次接收请求时，实例化，初始化。我们可以通过`<load-on-startup>`来设置servlet启动的先后顺序,数字越小，启动越靠前，最小值0
  - Servlet在容器中是：单例的、线程不安全的
    - 单例：所有的请求都是同一个实例去响应
    - 线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化
    - 我们已经知道了servlet是线程不安全的，给我们的启发是： `尽量不要在servlet中定义成员变量`。如果不得不定义成员变量，那么不要去：①不要去修改成员变量的值 ②不要去根据成员变量的值做一些逻辑判断

### Http协议

- Http 称之为 超文本传输协议
- Http是无状态的
- Http请求响应包含两个部分：请求和响应
   - 请求：包含三个部分： 1.请求行 ； 2.请求消息头 ； 3.请求主体
     - 请求行包含是三个信息： 1. 请求的方式 ； 2.请求的URL ； 3.请求的协议（一般都是HTTP1.1）
     - 请求消息头中包含了很多客户端需要告诉服务器的信息，比如：我的浏览器型号、版本、我能接收的内容的类型、我给你发的内容的类型、内容的长度等等
     - 请求体，三种情况
       get方式，没有请求体，但是有一个queryString
       post方式，有请求体，form data
       json格式，有请求体，request payload
   - 响应：包含三个部分： 1. 响应行 ； 2.响应头 ； 3.响应体
     - 响应行包含三个信息：1.协议 2.响应状态码(200) 3.响应状态(ok)
     - 响应头：包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码、内容长度等）
     - 响应体：响应的实际内容（比如请求add.html页面时，响应的内容就是<html><head><body><form....）

### 会话

#### Http是无状态

- HTTP 无状态 ：服务器无法判断这两次请求是同一个客户端发过来的，还是不同的客户端发过来的
- 无状态带来的现实问题：第一次请求是添加商品到购物车，第二次请求是结账；如果这两次请求服务器无法区分是同一个用户的，那么就会导致混乱
- 通过`会话跟踪技术`来解决无状态的问题。

####  会话跟踪技术

- 客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后响应给客户端
- 下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端
- 常用的API

```java
request.getSession() -> 获取当前的会话，没有则创建一个新的会话
request.getSession(true) -> 效果和不带参数相同
  request.getSession(false) -> 获取当前会话，没有则返回null，不会创建新的
  session.getId() -> 获取sessionID
  session.isNew() -> 判断当前session是否是新的
  session.getMaxInactiveInterval() -> session的非激活间隔时长，默认1800秒(30分钟)
  session.setMaxInactiveInterval()
  session.invalidate() -> 强制性让会话立即失效
   ....
```

#### session保存作用域

 - session保存作用域和具体的某一个session对应

 - 常用的API：
   
   ```java
   void session.setAttribute(k,v)
   Object session.getAttribute(k)
   void removeAttribute(k)
   ```

### 请求转发和重定向

- 服务器内部请求转发 :
  - request.getRequestDispatcher("...").forward(request,response);
   - `一次`请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的
   - 地址栏没有变化
- 客户端重定向：
  -  response.sendRedirect("....");
   - `两次`请求响应的过程。客户端肯定知道请求URL有变化
   - 地址栏有变化

### Thymeleaf - 视图模板技术

- 添加thymeleaf的jar包
- 新建一个Servlet类ViewBaseServlet
- 在web.xml文件中添加配置
  - 配置前缀 view-prefix
  - 配置后缀 view-suffix
- 使得我们的Servlet继承ViewBaseServlet
-  根据逻辑视图名称 得到 物理视图名称
  //逻辑视图名称 ：   index
  //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
  //所以真实的视图名称是：      /       index       .html
  super.processTemplate("index",request,response);
- 使用thymeleaf的标签
  th:if   ,  th:unless   , th:each   ,   th:text

### 保存作用域

原始情况下，保存作用域我们可以认为有四个： page（页面级别，现在几乎不用） , request（一次请求响应范围） , session（一次会话范围） , application（整个应用程序范围）

- request：一次请求响应范围

- session：一次会话范围有效 

  ```java
  HttpSession session = req.getSession();
  ```

- application： 一次应用程序范围有效

  ```java
  ServletContext servletContext = req.getServletContext();
  ```

### 路径问题

- 相对路径：../表示上一级，./表示当前目录
- 绝对路径：使用<base href="http://localhost:8080/pro">可以作为绝对路径的基础路径，其他文件直接按照绝对路径写可以省略基础路径。

### Servlet优化

> 见代码：_ 1fruit、_ 2fruit_mvc_reflect、_ 3fruit_dispatcher、_4fruit_mvc_controller

1. 最初的做法是： 一个请求对应一个Servlet，这样存在的问题是`servlet太多`了
2. 把一些列的请求都对应一个Servlet, IndexServlet/AddServlet/EditServlet/DelServlet/UpdateServlet -> 合并成FruitServlet
   通过一个operate的值来决定调用FruitServlet中的哪一个方法。使用的是`switch-case`
3. 在上一个版本中，Servlet中充斥着大量的switch-case，试想一下，随着我们的项目的业务规模扩大，那么会有很多的Servlet，也就意味着会有很多的switch-case，这是一种代码冗余
   因此，我们在servlet中使用了`反射技术`，我们`规定operate的值和方法名一致`，那么接收到operate的值是什么就表明我们需要调用对应的方法进行响应，如果找不到对应的方法，则抛异常
4. 在上一个版本中我们使用了反射技术，但是其实还是存在一定的问题：每一个servlet中都有类似的反射技术的代码。因此继续抽取，设计了中央控制器类：`DispatcherServlet`
   DispatcherServlet这个类的工作分为两大部分：
   - 根据url定位到能够处理这个请求的controller组件：
      1)从url中提取servletPath : /fruit.do -> fruit
      2)根据fruit找到对应的组件:FruitController ， 这个对应的依据我们存储在applicationContext.xml中
        <bean id="fruit" class="com.atguigu.fruit.controllers.FruitController/>
        通过DOM技术我们去解析XML文件，在中央控制器中形成一个beanMap容器，用来存放所有的Controller组件
      3)根据获取到的operate的值定位到我们FruitController中需要调用的方法
   - 调用Controller组件中的方法：
      1) 获取参数
         获取即将要调用的方法的参数签名信息: Parameter[] parameters = method.getParameters();
         通过parameter.getName()获取参数的名称；通过parameter.getType()获取参数的类型
         准备了Object[] parameterValues 这个数组用来存放对应参数的参数值
         另外，我们需要考虑参数的类型问题，需要做类型转化的工作。 
      2) 执行方法
         Object returnObj = method.invoke(controllerBean , parameterValues);
      3) 视图处理
         String returnStr = (String)returnObj;
         if(returnStr.startWith("redirect:")){
          ....
         }else if.....

### ServletConfig和ServletContext

- Servlet中的初始化方法有两个：init() , init(config)。

  ```java
  // 其中带参数的方法代码如下：
  public void init(ServletConfig config) throws ServletException {
    this.config = config ;
    init();
  }
  // 另外一个无参的init方法如下：
  public void init() throws ServletException{
  }
  ```

  如果我们想要在Servlet初始化时做一些准备工作，那么我们可以重写init方法。我们可以通过如下步骤去获取初始化设置的数据

- ServletConfig对象：ServletConfig 是每个 Servlet 程序创建时，就创建一个对应的 ServletConfig 对象，存放每个Servlet的配置参数。

   - 配置

   ```xml
   <!-- 在web.xml文件中配置Servlet -->
   <servlet>
     <servlet-name>Demo01Servlet</servlet-name>
     <servlet-class>com.atguigu.servlet.Demo01Servlet</servlet-class>
     <init-param>
       <param-name>hello</param-name>
       <param-value>world</param-value>
     </init-param>
     <init-param>
       <param-name>uname</param-name>
       <param-value>jim</param-value>
     </init-param>
   </servlet>
   <servlet-mapping>
     <servlet-name>Demo01Servlet</servlet-name>
     <url-pattern>/demo01</url-pattern>
   </servlet-mapping>
   ```
   ```java
   // 也可以通过注解的方式进行配置
   @WebServlet(urlPatterns = {"/demo01"} ,
   initParams = {
     @WebInitParam(name="hello",value="world"),
     @WebInitParam(name="uname",value="jim")
   })
   ```

   - 获取与使用		
   
     ```java
     ServletConfig config = getServletConfig();
     config.getInitParameter(key);
     ```

- ServletContext：一个web工程只有一个ServletContext对象，web工程启动时创建，停止时销毁。存放整个工程的配置参数，可以像map一样存放数据

  - 配置
  
    ```xml
    <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    ```
    
  - 获取与使用
  
    ```java
    // 在初始化方法中： 
    ServletContxt servletContext = getServletContext();
    // 在服务方法中也可以通过request对象获取，也可以通过session获取：
    request.getServletContext(); 
    session.getServletContext()
    // 获取初始化值：
    servletContext.getInitParameter();
    ```

### MVC

#### 业务层

- MVC : Model（模型）、View（视图）、Controller（控制器）
  视图层：用于做数据展示以及和用户交互的一个界面
  控制层：能够接受客户端的请求，具体的业务功能还是需要借助于模型组件来完成
  模型层：模型分为很多种：有比较简单的pojo/vo(value object)，有业务模型组件，有数据访问层组件
      1) pojo/vo : 值对象：一般用于与前端页面的交互
      2) DAO ： 数据访问对象
      3) BO ： 业务对象，是PO的组合，如投保人是一个PO，被保险人是一个PO，险种信息是一个PO等等，他们组合气来是第一张保单的BO

  ​    4）PO/Entity：数据库中表的字段形成一一对应关系

- 区分业务对象和数据访问对象：
    1） DAO中的方法都是单精度方法或者称之为细粒度方法。什么叫单精度？一个方法只考虑一个操作，比如添加，那就是insert操作、查询那就是select操作....
    2） BO中的方法属于业务方法，也实际的业务是比较复杂的，因此业务方法的粒度是比较粗的
        注册这个功能属于业务功能，也就是说注册这个方法属于业务方法。
        那么这个业务方法中包含了多个DAO方法。也就是说注册这个业务功能需要通过多个DAO方法的组合调用，从而完成注册功能的开发。
        注册：
              1. 检查用户名是否已经被注册 - DAO中的select操作
                          2. 向用户表新增一条新用户记录 - DAO中的insert操作
                          3. 向用户积分表新增一条记录（新用户默认初始化积分100分） - DAO中的insert操作
                                      4. 向系统消息表新增一条记录（某某某新用户注册了，需要根据通讯录信息向他的联系人推送消息） - DAO中的insert操作
                                      5. 向系统日志表新增一条记录（某用户在某IP在某年某月某日某时某分某秒某毫秒注册） - DAO中的insert操作
                                                  6. ....
  3) 在库存系统中添加业务层组件

#### IOC

- 耦合/依赖
    依赖指的是某某某离不开某某某
    在软件系统中，层与层之间是存在依赖的。我们也称之为耦合。
    我们系统架构或者是设计的一个原则是： `高内聚低耦合`。
    层内部的组成应该是高度聚合的，而层与层之间的关系应该是低耦合的，最理想的情况0耦合（就是没有耦合）

- IOC - 控制反转 / DI - 依赖注入

  - `控制反转`：
        1) 之前在Servlet中，我们创建service对象 ， FruitService fruitService = new FruitServiceImpl();
           这句话如果出现在servlet中的某个方法内部，那么这个fruitService的作用域（生命周期）应该就是这个方法级别；
           如果这句话出现在servlet的类中，也就是说fruitService是一个成员变量，那么这个fruitService的作用域（生命周期）应该就是这个servlet实例级别
        2) 之后我们在applicationContext.xml中定义了这个fruitService。然后通过解析XML，产生fruitService实例，存放在beanMap中，这个beanMap在一个BeanFactory中
           因此，我们转移（改变）了之前的service实例、dao实例等等他们的生命周期。控制权从程序员转移到BeanFactory。这个现象我们称之为控制反转

  - `依赖注入`：
    1) 之前我们在控制层出现代码：FruitService fruitService = new FruitServiceImpl()；
       那么，控制层和service层存在耦合。
    2) 之后，我们将代码修改成FruitService fruitService = null ;
       然后，在配置文件中配置:

      ```xml
    <bean id="fruit" class="FruitController">
      <property name="fruitService" ref="fruitService"/>
    </bean>
      ```

## filter过滤器

- Filter也属于Servlet规范

- Filter开发步骤：新建类实现Filter接口，然后实现其中的三个方法：init、doFilter、destroy

- 配置Filter，可以用注解@WebFilter，也可以使用xml文件

  ```xml
  @WebFilter("*.do")
  
  <filter>
    <filter-name>Filter03</filter-name>
    <filter-class>com.atguigu.filters.Filter03</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>Filter03</filter-name>
    <url-pattern>/*.do</url-pattern>
  </filter-mapping>
  ```

- 如果采取的是`注解`的方式进行配置，那么过滤器链的拦截顺序是按照全类名的先后顺序排序的
  如果采取的是`xml`的方式进行配置，那么按照配置的先后顺序进行排序

## 事务管理

- 涉及到的组件：
    - OpenSessionInViewFilter
    - TransactionManager
    - ThreadLocal
    - ConnUtil
    - BaseDAO

- ThreadLocal
    - get() , set(obj)

    - ThreadLocal称之为本地线程 。 我们可以通过set方法在当前线程上存储数据、通过get方法在当前线程上获取数据

    - set方法源码分析：

        ```java
        public void set(T value) {
          Thread t = Thread.currentThread(); //获取当前的线程
          ThreadLocalMap map = getMap(t);    //每一个线程都维护各自的一个容器（ThreadLocalMap）
          if (map != null)
            map.set(this, value);          //这里的key对应的是ThreadLocal，因为我们的组件中需要传输（共享）的对象可能会有多个（不止Connection）
          else
            createMap(t, value);           //默认情况下map是没有初始化的，那么第一次往其中添加数据时，会去初始化
        }
        ```

  - get方法源码分析：

    ```java
    public T get() {
      Thread t = Thread.currentThread(); //获取当前的线程
      ThreadLocalMap map = getMap(t);    //获取和这个线程（企业）相关的ThreadLocalMap（也就是工作纽带的集合）
      if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);   //this指的是ThreadLocal对象，通过它才能知道是哪一个工作纽带
        if (e != null) {
          @SuppressWarnings("unchecked")
          T result = (T)e.value;     //entry.value就可以获取到工具箱了
          return result;
        }
      }
      return setInitialValue();
    }
    ```

## 监听器

### 分类

1) ServletContextListener - 监听ServletContext对象的创建和销毁的过程
2) HttpSessionListener - 监听HttpSession对象的创建和销毁的过程
3) ServletRequestListener - 监听ServletRequest对象的创建和销毁的过程

4) ServletContextAttributeListener - 监听ServletContext的保存作用域的改动(add,remove,replace)
5) HttpSessionAttributeListener - 监听HttpSession的保存作用域的改动(add,remove,replace)
6) ServletRequestAttributeListener - 监听ServletRequest的保存作用域的改动(add,remove,replace)

7) HttpSessionBindingListener - 监听某个对象在Session域中的创建与移除
8) HttpSessionActivationListener - 监听某个对象在Session域中的序列化和反序列化

1. ServletContextListener的应用 - ContextLoaderListener：可以在监听到ServletContext加载时，加载bean配置文件，而不是在DispatcherServlet中加载

### 配置

配置Listener，可以用注解@WebListener，也可以使用xml文件

```java
@WebListener
```

```xml
<listener>
    <listener-class>com.atguigu.listeners.MyServletContextListener</listener-class>
</listener>
```

## Cookie

1. 创建Cookie对象
2. 在客户端保存Cookie
3. 设置Cookie的有效时长
   cookie.setMaxAge(60)  ， 设置cookie的有效时长是60秒
   cookie.setDomain(pattern);
   cookie.setPath(uri);
4. Cookie的应用：
    4-1: 记住用户名和密码十天 setMaxAge(60 * 60 * 24 * 10)
    4-2: 十天免登录

## Kaptcha

1. 为什么需要验证码
2. kaptcha如何使用:
   - 添加jar
   - 在web.xml文件中注册KaptchaServlet，并设置验证码图片的相关属性
   - 在html页面上编写一个img标签，然后设置src等于KaptchaServlet对应的url-pattern
3. kaptcha验证码图片的各个属性在常量接口：Constants中
4. KaptchaServlet在生成验证码图片时，会同时将验证码信息保存到session中
   因此，我们在注册请求时，首先将用户文本框中输入的验证码值和session中保存的值进行比较，相等，则进行注册

## JS 正则

- 正则表达式的使用三步骤：
      1. 定义正则表达式对象
         正则表达式定义有两个方式：
         1) 对象形式
            var reg = new RegExp("abc")
         2) 直接量形式
            var reg = /abc/;
         3) 匹配模式：
          - g 全局匹配
          - i 忽略大小写匹配
          - m 多行匹配
          - gim这三个可以组合使用，不区分先后顺序
            例如： var reg = /abc/gim , var reg = new RegExp("abc","gim");
          2. 定义待校验的字符串
           3. 校验
- 元字符
    . , \w , \W , \s , \S , \d , \D , \b , ^ , $

- []表示集合
    [abc] 表示 a或者b或者c
    [ ^abc] 表示取反，只要不是a不是b不是c就匹配
    [a-c] 表示a到c这个范围匹配

- 出现的次数

  * 表示多次 （0 ~ n ）
  + 至少一次 ( >=1 )
    ? 最多一次 (0 ~ 1)
    {n} 出现n次
    {n,} 出现n次或者多次
    {n,m} 出现n到m次

## Book项目

1. 需求分析

2. 数据库设计

    <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/01.%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.png" alt="01.数据库设计" style="zoom:55%;" />
    
    1） 实体分析
    
    - 图书                Book
- 用户                User
    - 订单                OrderBean
    - 订单详情             OrderItem
    - 购物车项             CartItem
    
    2） 实体属性分析
    
    - 图书 : 书名、作者、价格、销量、库存、封面、状态
    - 用户 : 用户名、密码、邮箱
    - 订单 : 订单编号、订单日期、订单金额、订单数量、订单状态、用户
    - 订单详情 : 图书、数量、所属订单
    - 购物车项 : 图书、数量、所属用户