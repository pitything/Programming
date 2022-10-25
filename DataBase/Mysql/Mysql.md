# Mysql

## 数据库概述

### 为什么要使用数据库

> - 持久化(persistence)： 把数据保存到可掉电式存储设备中以供之后使用 。大多数情况下，特别是企业级应用， 数据持久化意味着将内存中的数据保存到硬盘上加以”固化” ，而持久化的实现过程大多通过各种关系数据库来完成。
>
> - 持久化的主要作用是 将内存中的数据存储在关系型数据库中 ，当然也可以存储在磁盘文件、XML数据文件中。

### 数据库与数据库管理系统

#### 数据库的相关概念

> DB：数据库（Database）即存储数据的“仓库”，其本质是一个文件系统。它保存了一系列有组织的数据。
>
> DBMS：数据库管理系统（Database Management System）是一种操纵和管理数据库的大型软件，用于建立、使用和维护数据库，对数据库进行统一管理和控制。用户通过数据库管理系统访问数据库中表内的数据。
>
> SQL：结构化查询语言（Structured Query Language）专门用来与数据库通信的语言。

#### 数据库与数据库管理系统的关系

> 数据库管理系统(DBMS)可以管理多个数据库，一般开发人员会针对每一个应用创建一个数据库。为保存应用中实体的数据，一般会在数据库创建多个表，以保存程序中实体用户的数据。数据库管理系统、数据库和表的关系如图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220811105251068.png" alt="image-20220811105251068" style="zoom:67%;" />

#### 常见的数据库介绍

> - **Oracle**
>
> 1979 年，Oracle 2 诞生，它是第一个商用的 RDBMS（关系型数据库管理系统）。随着 Oracle 软件的名气越来越大，公司也改名叫 Oracle 公司。
>
> 2007 年，总计 85 亿美金收购BEA Systems。
>
> 2009 年，总计 74 亿美金收购SUN。此前的 2008 年，SUN以 10 亿美金收购MySQL。意味着Oracle 同时拥有了MySQL 的管理权，至此 Oracle 在数据库领域中成为绝对的领导者。
>
> 2013 年，甲骨文超越IBM，成为继Microsoft后全球第二大软件公司。
>
> 如今 Oracle 的年收入达到了 400 亿美金，足以证明商用（收费）数据库软件的价值。
>
> - **SQL Server**
>
> SQL Server 是微软开发的大型商业数据库，诞生于 1989 年。C#、.net等语言常使用，与WinNT完全集成，也可以很好地与Microsoft BackOffice产品集成。
>
> - **DB**
>
> IBM公司的数据库产品,收费的。常应用在银行系统中。
>
> - **PostgreSQL**
>
> PostgreSQL 的稳定性极强，最符合SQL标准，开放源码，具备商业级DBMS质量。PG对数据量大的文本以及SQL处理较快。
>
> - **SyBase** 
>
>
> 已经淡出历史舞台。提供了一个非常专业数据建模的工具PowerDesigner。
>
> - **SQLite**
>
> 嵌入式的小型数据库，应用在手机端。 零配置，SQlite3不用安装，不用配置，不用启动，关闭或者配置数据库实例。当系统崩溃后不用做任何恢复操作，再下次使用数据库的时候自动恢复。
>
> - **informix**
>
> IBM公司出品，取自Information 和Unix的结合，它是第一个被移植到Linux上的商业数据库产品。仅运行于unix/linux平台，命令行操作。 性能较高，支持集群，适应于安全性要求极高的系统，尤其是银行，证券系统的应用。

### 3. MySQL介绍

#### 概述

> - MySQL是一个开放源代码的关系型数据库管理系统，由瑞典MySQL AB（创始人Michael Widenius）公司 1995 年开发，迅速成为开源数据库的 No.1。
> - 2008 被Sun收购（ 10 亿美金）， 2009 年Sun被Oracle收购。MariaDB应运而生。（MySQL 的创造者担心 MySQL 有闭源的风险，因此创建了 MySQL 的分支项目 MariaDB）
> - MySQL6.x 版本之后分为社区版和商业版。
> - MySQL是一种关联数据库管理系统，将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。
> - MySQL是开源的，所以你不需要支付额外的费用。
> - MySQL是可以定制的，采用了GPL（GNU General Public License）协议，你可以修改源码来开发自己的MySQL系统。
> - MySQL支持大型数据库，支持 5000 万条记录的数据仓库， 32 位系统表文件最大可支持4GB， 64 位系统支持最大的表文件为8TB。
> - MySQL使用标准的SQL数据语言形式。
> - MySQL可以允许运行于多个系统上，并且支持多种语言。这些编程语言包括C、C++、Python、Java、Perl、PHP和Ruby等。

#### MySQL发展史重大事件

> MySQL的历史就是整个互联网的发展史。互联网业务从社交领域、电商领域到金融领域的发展，推动着应用对数据库的需求提升，对传统的数据库服务能力提出了挑战。高并发、高性能、高可用、轻资源、易维护、易扩展的需求，促进了MySQL的长足发展。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220811112635444.png" alt="image-20220811112635444" style="zoom:50%;" />


#### 关于MySQL 8. 0

> MySQL从5.7版本直接跳跃发布了8.0版本，可见这是一个令人兴奋的里程碑版本。MySQL 8版本在功能上做了显著的改进与增强，开发者对MySQL的源代码进行了重构，最突出的一点是多MySQL Optimizer优化器进行了改进。不仅在速度上得到了改善，还为用户带来了更好的性能和更棒的体验。


#### MySQL优点

> 1. 开放源代码，使用成本低。
> 2. 性能卓越，服务稳定。
> 3. 软件体积小，使用简单，并且易于维护。
> 4. 历史悠久，社区用户非常活跃，遇到问题可以寻求帮助。
> 5. 许多互联网公司在用，经过了时间的验证。

#### Oracle vs MySQL

> - Oracle 更适合大型跨国企业的使用，因为他们对费用不敏感，但是对性能要求以及安全性有更高的要求。
> - MySQL 由于其 **体积小、速度快、总体拥有成本低，可处理上千万条记录的大型数据库，尤其是开放源码这一特点，使得很多互联网公司、中小型网站选择了MySQL作为网站数据库** （Facebook，Twitter，YouTube，阿里巴巴/蚂蚁金服，去哪儿，美团外卖，腾讯）。

### RDBMS 与 非RDBMS

> 从排名中我们能看出来，关系型数据库绝对是 DBMS 的主流，其中使用最多的 DBMS 分别是 Oracle、MySQL 和 SQL Server。这些都是关系型数据库（RDBMS）。

#### 关系型数据库(RDBMS)

##### 实质

> 这种类型的数据库是最古老的数据库类型，关系型数据库模型是把复杂的数据结构归结为简单的二元关系（即二维表格形式）。
>
> 关系型数据库以行(row)和列(column)的形式存储数据，以便于用户理解。这一系列的行和列被称为表(table)，一组表组成了一个库(database)表与表之间的数据记录有关系(relationship)。现实世界中的各种实体以及实体之间的各种联系均用关系模型来表示。关系型数据库，就是建立在关系模型基础上的数据库。
>
> SQL 就是关系型数据库的查询语言。

#####  优势

> 复杂查询 可以用SQL语句方便的在一个表以及多个表之间做非常复杂的数据查询。事务支持 使得对于安全性能很高的数据访问要求得以实现。

#### 非关系型数据库(非RDBMS)

##### 介绍

> 非关系型数据库 ，可看成传统关系型数据库的功能阉割版本，基于`键值对`存储数据，不需要经过SQL层的解析，性能非常高。同时，通过减少不常用的功能，进一步提高性能。目前基本上大部分主流的非关系型数据库都是免费的。

##### 分类

> 相比于 SQL，NoSQL 泛指非关系型数据库，包括了榜单上的键值型数据库、文档型数据库、搜索引擎和列存储等，除此以外还包括图形数据库。也只有用 NoSQL 一词才能将这些技术囊括进来。

**键值型数据库**

> 键值型数据库通过 Key-Value 键值的方式来存储数据，其中 Key 和 Value 可以是简单的对象，也可以是复杂的对象。Key 作为唯一的标识符，优点是查找速度快，在这方面明显优于关系型数据库，缺点是无法像关系型数据库一样使用条件过滤（比如 WHERE），如果你不知道去哪里找数据，就要遍历所有的键，这就会消耗大量的计算。键值型数据库典型的使用场景是作为内存缓存。Redis是最流行的键值型数据库。

**文档型数据库**

> 此类数据库可存放并获取文档，可以是XML、JSON等格式。在数据库中文档作为处理信息的基本单位，一个文档就相当于一条记录。文档数据库所存放的文档，就相当于键值数据库所存放的“值”。MongoDB是最流行的文档型数据库。此外，还有CouchDB等。

**搜索引擎数据库**

> 虽然关系型数据库采用了索引提升检索效率，但是针对全文索引效率却较低。搜索引擎数据库是应用在搜索引擎领域的数据存储形式，由于搜索引擎会爬取大量的数据，并以特定的格式进行存储，这样在检索的时候才能保证性能最优。核心原理是“倒排索引”。典型产品：Solr、Elasticsearch、Splunk 等。

**列式数据库**

> 列式数据库是相对于行式存储的数据库，Oracle、MySQL、SQL Server 等数据库都是采用的行式存储（Row-based），而列式数据库是将数据按照列存储到数据库中，这样做的好处是可以大量降低系统的I/O，适合于分布式文件系统，不足在于功能相对有限。典型产品：HBase等。

**图形数据库**

> 图形数据库，利用了图这种数据结构存储了实体（对象）之间的关系。图形数据库最典型的例子就是社交网络中人与人的关系，数据模型主要是以节点和边（关系）来实现，特点在于能高效地解决复杂的关系问题。 图形数据库顾名思义，就是一种存储图形关系的数据库。它利用了图这种数据结构存储了实体（对象）之间的关系。关系型数据用于存储明确关系的数据，但对于复杂关系的数据存储却有些力不从心。如社交网络中人物之间的关系，如果用关系型数据库则非常复杂，用图形数据库将非常简单。典型产品：Neo4J、InfoGrid等。

##### NoSQL的演变

> 由于 SQL 一直称霸 DBMS，因此许多人在思考是否有一种数据库技术能远离 SQL，于是 NoSQL 诞生了，但是随着发展却发现越来越离不开 SQL。到目前为止 NoSQL 阵营中的 DBMS 都会有实现类似 SQL 的功能。下面是“NoSQL”这个名词在不同时期的诠释，从这些释义的变化中可以看出 NoSQL 功能的演变：
>
> 1970 ：NoSQL = We have no SQL
>
> 1980 ：NoSQL = Know SQL
>
> 2000 ：NoSQL = No SQL!
>
> 2005 ：NoSQL = Not only SQL
>
> 2013 ：NoSQL = No, SQL!
>
> NoSQL 对 SQL 做出了很好的补充，比如实际开发中，有很多业务需求，其实并不需要完整的关系型数据库功能，非关系型数据库的功能就足够使用了。这种情况下，使用性能更高、成本更低的非关系型数据库当然是更明智的选择。比如：日志收集、排行榜、定时器等。

#### 小结

> NoSQL 的分类很多，即便如此，在 DBMS 排名中，还是 SQL 阵营的比重更大，影响力前 5 的 DBMS 中有4 个是关系型数据库，而排名前 20 的 DBMS 中也有 12 个是关系型数据库。所以说，掌握 SQL 是非常有必要的。整套课程将围绕 SQL 展开。

### 关系型数据库设计规则

> 关系型数据库的典型数据结构就是数据表，这些数据表的组成都是结构化的（Structured）。
>
> 将数据放到表中，表再放到库中。
>
> 一个数据库中可以有多个表，每个表都有一个名字，用来标识自己。表名具有唯一性。
>
> 表具有一些特性，这些特性定义了数据在表中如何存储，类似Java和Python中 “类”的设计。

#### 表、记录、字段

> E-R（entity-relationship，实体-联系）模型中有三个主要概念是：实体集、属性、联系集。
>
> 一个实体集（class）对应于数据库中的一个表（table），一个实体（instance）则对应于数据库表中的一行（row），也称为一条记录（record）。一个属性（attribute）对应于数据库表中的一列（column），也称为一个字段（field）。

#### 表的关联关系

> 表与表之间的数据记录有关系(relationship)。现实世界中的各种实体以及实体之间的各种联系均用关系模型来表示。
> 四种：一对一关联、一对多关联、多对多关联、自我引用

##### 一对一关联（one-to-one）

> 在实际的开发中应用不多，因为一对一可以创建成一张表。
>
> 举例：设计学生表：学号、姓名、手机号码、班级、系别、身份证号码、家庭住址、籍贯、紧急联系人、...
>
> 拆为两个表：两个表的记录是一一对应关系。
>
> - 基础信息表（常用信息）：学号、姓名、手机号码、班级、系别
>
> - 档案信息表（不常用信息）：学号、身份证号码、家庭住址、籍贯、紧急联系人、...
>
> 两种建表原则：
>
> - 外键唯一：主表的主键和从表的外键（唯一），形成主外键关系，外键唯一。
>
> - 外键是主键：主表的主键和从表的主键，形成主外键关系。


##### 一对多关系（one-to-many）

>  常见实例场景：客户表和订单表，分类表和商品表，部门表和员工表。
>
>  举例： 员工表：编号、姓名、...、所属部门；部门表：编号、名称、简介
>
>  一对多建表原则：在从表(多方)创建一个字段，字段作为外键指向主表(一方)的主键


##### 多对多（many-to-many）

>   要表示多对多关系，必须创建第三个表，该表通常称为联接表，它将多对多关系划分为两个一对多关
>
>  系。将这两个表的主键都插入到第三个表中。
>
>  举例 1 ：学生-课程
>
>  学生信息表：一行代表一个学生的信息（学号、姓名、手机号码、班级、系别...）
>
>
>  课程信息表：一行代表一个课程的信息（课程编号、授课老师、简介...）
>
>  选课信息表：一个学生可以选多门课，一门课可以被多个学生选择
>
>  多对多关系建表原则：需要创建第三张表，中间表中至少两个字段，这两个字段分别作为外键指向各自一方的主键。

##### 自我引用(Self reference)

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220811144616353.png" alt="image-20220811144616353" style="zoom:50%;" />

## MySQL环境搭建

### MySQL的 4 大版本

> - MySQL Community Server 社区版本 ，开源免费，自由下载，但不提供官方技术支持，适用于大多数普通用户。
> - MySQL Enterprise Edition 企业版本 ，需付费，不能在线下载，可以试用 30 天。提供了更多的功能和更完备的技术支持，更适合于对数据库的功能和可靠性要求较高的企业客户。
> - MySQL Cluster 集群版 ，开源免费。用于架设集群服务器，可将几个MySQL Server封装成一个Server。需要在社区版或企业版的基础上使用。
> - MySQL Cluster CGE 高级集群版 ，需付费。

### Mac系统环境搭建

#### 卸载

```bash
brew remove mysql
brew cleanup
launchctl unload -w ~/Library/LaunchAgents/com.mysql.mysqld.plist
rm ~/Library/LaunchAgents/com.mysql.mysqld.plist
sudo rm -rf /usr/local/var/mysql
```

#### 安装

```
homebrew install mysql
```

#### 配置环境变量

```yml
# Mysql命令添加到环境变量里
vim ~/.zshrc 
# 最后加入 
export PATH=${PATH}:/usr/local/Cellar/mysql/8.0.30/bin
# 使配置生效 
source ~/.zshrc
```

#### 常用命令

```yml
# 查看mysql版本
mysql —version
# 用户登陆
mysql -u root -p
# 停止mysql服务
mysql.server stop
# 启动mysql
mysql.server start
```

### MySQL演示使用

#### 查看所有数据库

> - `information_schema`是 MySQL 系统自带的数据库，主要保存 MySQL 数据库服务器的系统信息，比如数据库的名称、数据表的名称、字段名称、存取权限、数据文件 所在的文件夹和系统使用的文件夹，等等
> - `performance_schema`是 MySQL 系统自带的数据库，可以用来监控 MySQL 的各类性能指标。
> - `sys`数据库是 MySQL 系统自带的数据库，主要作用是以一种更容易被理解的方式展示 MySQL 数据库服务器的各类性能指标，帮助系统管理员和开发人员监控 MySQL 的技术性能。
> - `mysql`数据库保存了 MySQL 数据库服务器运行时需要的系统信息，比如数据文件夹、当前使用的字符集、约束检查信息，等等

```
show databases;
```

#### 创建数据库

```yml
#创建atguigudb数据库，该名称不能与已经存在的数据库重名。 
create database atguigudb;
```

#### 使用数据库

```yml
#使用atguigudb数据库 
use atguigudb;
```

#### 查看某个库的所有表格

```yml
show tables;  #要求前面有use语句
show tables from 数据库名;
```
#### 创建表格

```yml
#创建学生表
create table student(
id int,
name varchar( 20 )  #说名字最长不超过 20 个字符
);
```

#### 查看一个表的数据

```java
select * from 数据库表名称;
```

#### 添加一条记录

```yml
#添加两条记录到student表中 
insert into student values(1,'张三'); 
insert into student values(2,'李四');
```

#### 查看表的创建信息

```
show create table 表名称;
```

#### 查看数据库的创建信息

```yml
#查看atguigudb数据库的详细创建信息
show create database atguigudb;
```

#### 删除表格

```yml
#删除学生表
drop table student;
```

#### 删除数据库

```yml
#删除atguigudb数据库
drop database atguigudb;
```

#### MySQL的编码设置

##### MySQL 5. 7 

> 问题再现：命令行操作sql乱码问题
>
> - 步骤 1 ：查看编码命令
>
>   ```yml
>   show variables like 'character_%';
>   show variables like 'collation_%';
>   ```
>
> - 步骤 2 ：修改mysql的数据目录下的my.ini配置文件
>
>   ```yml
>   [mysql] #大概在63行左右，在其下添加 ... 
>   default-character-set=utf8 #默认字符集 
>   [mysqld] # 大概在76行左右，在其下添加 ... 
>   character-set-server=utf8
>   collation-server=utf8_general_ci
>   ```
>
> - 步骤 3 ：重启服务

##### MySQL 8. 0 中

> 在MySQL 8.0版本之前，默认字符集为latin1，utf8字符集指向的是utf8mb3。网站开发人员在数据库设计的时候往往会将编码修改为utf8字符集。如果遗忘修改默认的编码，就会出现乱码的问题。
>
> 从MySQL 8.0开始，数据库的默认编码改为`utf8mb4`，从而避免了上述的乱码问题。

### MySQL目录结构

 ![image-20220811154436549](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220811154436549.png)


### 常见问题的解决

#### 重置密码

> - 已经登陆后，修改密码
>
>   ```yml
>   ALTER USER 'root'@'localhost' IDENTIFIED BY '123456'; 
>   ```
>
> - 未登录，修改密码
>
>   ```yml
>   mysql_secure_installation
>   ```

#### 命令行客户端的字符集问题

> 查看所有字符集： **SHOW VARIABLES LIKE 'character_set_%';**
>
> 解决方案，设置当前连接的客户端字符集 “SET NAMES GBK;”

#### 修改数据库和表的字符编码

```yml
alter table student charset utf8; #修改表字符编码为UTF8 

alter table student modify name varchar(20) charset utf8; #修改字段字符编码为UTF8

alter database 0728db charset utf8; #修改数据库的字符编码为utf8
```

## 基本的SELECT语句

### SQL概述

#### SQL背景知识

> - 1946 年，世界上第一台电脑诞生，如今，借由这台电脑发展起来的互联网已经自成江湖。在这几十年里，无数的技术、产业在这片江湖里沉浮，有的方兴未艾，有的已经几幕兴衰。但在这片浩荡的波动里，有一门技术从未消失，甚至“老当益壮”，那就是 SQL。
>
>   - 45 年前，也就是 1974 年，IBM 研究员发布了一篇揭开数据库技术的论文《SEQUEL：一门结构化的英语查询语言》，直到今天这门结构化的查询语言并没有太大的变化，相比于其他语言，SQL 的半衰期可以说是非常长了。
>
> - 不论是前端工程师，还是后端算法工程师，都一定会和数据打交道，都需要了解如何又快又准确地提取自己想要的数据。更别提数据分析师了，他们的工作就是和数据打交道，整理不同的报告，以便指导业务决策。
>
> - SQL（Structured Query Language，结构化查询语言）是使用关系模型的数据库应用语言，与数据直接打交道，由IBM上世纪 70 年代开发出来。后由美国国家标准局（ANSI）开始着手制定SQL标准，先后有SQL- 86 ，SQL- 89 ，SQL- 92 ，SQL- 99 等标准。
>
> - SQL 有两个重要的标准，分别是 SQL92 和 SQL99，它们分别代表了 92 年和 99 年颁布的 SQL 标准，我们今天使用的 SQL 语言依然遵循这些标准。
>
> - 不同的数据库生产厂商都支持SQL语句，但都有特有内容。
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812103741742.png" alt="image-20220812103741742" style="zoom:50%;" />

#### SQL 分类

> - DDL（Data Definition Languages、数据定义语言） ，这些语句定义了不同的数据库、表、视图、索引等数据库对象，还可以用来创建、删除、修改数据库和数据表的结构。主要的语句关键字包括`CREATE、DROP、ALTER`等。
> - DML（Data Manipulation Language、数据操作语言） ，用于添加、删除、更新和查询数据库记录，并检查数据完整性。主要的语句关键字包括`INSERT、DELETE、UPDATE、SELECT`等。SELECT是SQL语言的基础，最为重要。
> - DCL（Data Control Language、数据控制语言） ，用于定义数据库、表、字段、用户的访问权限和安全级别。主要的语句关键字包括`GRANT、REVOKE、COMMIT、ROLLBACK、SAVEPOINT`等。
> - 因为查询语句使用的非常的频繁，所以很多人把查询语句单拎出来一类：DQL（数据查询语言）。
> - 还有单独将COMMIT、ROLLBACK 取出来称为TCL （Transaction Control Language，事务控制语言）。

### SQL语言的规则与规范


#### 基本规则

> - SQL 可以写在一行或者多行。为了提高可读性，各子句分行写，必要时使用缩进
>
> - 每条命令以 ; 或 \g 或 \G 结束
>
> - 关键字不能被缩写也不能分行
>
> - 关于标点符号
>   - 必须保证所有的()、单引号、双引号是成对结束的
>   - 必须使用英文状态下的半角输入方式
>   - 字符串型和日期时间类型的数据可以使用单引号（' '）表示
>   - 列的别名，尽量使用双引号（" "），而且不建议省略 as

#### SQL大小写规范 （建议遵守）

> - **MySQL** **在** **Windows** **环境下是大小写不敏感的**
>
> - **MySQL** **在** **Linux** **环境下是大小写敏感的**
>   - 数据库名、表名、表的别名、变量名是严格区分大小写的
>   - 关键字、函数名、列名(或字段名)、列的别名(字段的别名) 是忽略大小写的。
>
> - **推荐采用统一的书写规范：**
>   - 数据库名、表名、表别名、字段名、字段别名等都小写
>   - SQL 关键字、函数名、绑定变量等都大写

#### 注释

> 单行注释：#注释文字(MySQL特有的方式) 
>
> 单行注释：-- 注释文字(--后面必须包含一个空格。) 
>
> 多行注释：/* 注释文字 */ 

#### 命名规则（暂时了解）

> - 数据库、表名不得超过30个字符，变量名限制为29个
> - 必须只能包含 A–Z, a–z, 0–9, _共63个字符
> - 数据库名、表名、字段名等对象名中间不要包含空格
> - 同一个MySQL软件中，数据库不能同名；同一个库中，表不能重名；同一个表中，字段不能重名
> - 必须保证你的字段没有和保留字、数据库系统或常用方法冲突。如果坚持使用，请在SQL语句中使用`（着重号）引起来
> - 保持字段名和类型的一致性，在命名字段并为其指定数据类型的时候一定要保证一致性。假如数据类型在一个表里是整数，那在另一个表里可就别变成字符型了

#### 数据导入指令

> 在命令行客户端登录mysql，使用source指令导入

```yml
mysql> source d:\mysqldb.sql
```

### 基本的SELECT语句

#### SELECT...

```yml
SELECT 1; #没有任何子句 
SELECT 9/2; #没有任何子句
```

#### SELECT ... FROM

> - 获取不需要的列数据通常会降低查询和所使用的应用程序的效率，不推荐你直接使用 SELECT * 进行查询。
> - 许多开发人员习惯将关键字大写、数据列和表名小写，读者也应该养成一个良好的编程习惯，这样写出来的代码更容易阅读和维护

```yml
SELECT * FROM departments;
```

#### 列的别名

> 紧跟列名，也可以**在列名和别名之间加入关键字 AS，别名使用双引号**，以便在别名中包含空格或特殊的字符并区分大小写。AS 可以省略。建议别名简短，见名知意

#### 去除重复行

> - DISTINCT 需要放到所有列名的前面，如果写成 SELECT salary, DISTINCT department_id FROM employees 会报错。
>
> - DISTINCT 其实是对后面所有列名的组合进行去重，你能看到最后的结果是 74 条，因为这 74 个部门id不同，都有 salary 这个属性值。如果你想要看都有哪些不同的部门（department_id），只需要写 DISTINCT department_id 即可，后面不需要再加其他的列名了。

#### 空值参与运算

> - 所有运算符或列值遇到null值，运算的结果都为null
> - 这里你一定要注意，在 MySQL 里面， 空值不等于空字符串。一个空字符串的长度是 0，而一个空值的长度是空。而且，在 MySQL 里面，空值是占用空间的。

#### 着重号

> 我们需要保证表中的字段、表名等没有和保留字、数据库系统或常用方法冲突。如果真的相同，请在SQL语句中使用一对``（着重号）引起来。
>
> 如：SELECT * FROM `order`

### 显示表结构

> 使用DESCRIBE 或 DESC 命令，表示表结构。

```
DESCRIBE employees;
或
DESC employees;
```
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812111747727.png" alt="image-20220812111747727" style="zoom:50%;" />
>
> Field：表示字段名称。
>
> Type：表示字段类型，这里 barcode、goodsname 是文本型的，price 是整数类型的。
>
> Null：表示该列是否可以存储NULL值。
>
> Key：表示该列是否已编制索引。PRI表示该列是表主键的一部分；UNI表示该列是UNIQUE索引的一
>
> 部分；MUL表示在列中某个给定值允许出现多次。
>
> Default：表示该列是否有默认值，如果有，那么值是多少。
>
> Extra：表示可以获取的与给定列有关的附加信息，例如AUTO_INCREMENT等

### 过滤数据

> 使用WHERE 子句，将不满足条件的行过滤掉，WHERE子句紧随 FROM子句

```
SELECT employee_id, last_name, job_id, department_id
FROM employees
WHERE department_id = 90 ;
```

## 运算符

### 算术运算符

> 算术运算符主要用于数学运算，其可以连接运算符前后的两个数值或表达式，对数值或表达式进行加（+）、减（-）、乘（*）、除（/）和取模（%）运算。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812112430271.png" alt="image-20220812112430271" style="zoom:50%;" />

#### 加法与减法运算符

> - 一个整数类型的值对整数进行加法和减法操作，结果还是一个整数；
>
> - 一个整数类型的值对浮点数进行加法和减法操作，结果是一个浮点数；
>
> - 加法和减法的优先级相同，进行先加后减操作与进行先减后加操作的结果是一样的；
>
> - 在Java中，+的左右两边如果有字符串，那么表示字符串的拼接。但是在MySQL中+只表示数值相加。如果遇到非数值类型，先尝试转成数值，如果转失败，就按0计算。（补充：MySQL中字符串拼接要使用字符串函数CONCAT()实现）

#### 乘法与除法运算符

> - 一个数乘以整数1和除以整数1后仍得原数；
> - 一个数乘以浮点数1和除以浮点数1后变成浮点数，数值与原数相等；
> - 一个数除以整数后，不管是否能除尽，结果都为一个浮点数；
> - 一个数除以另一个数，除不尽时，结果为一个浮点数，并保留到小数点后4位；
> - 乘法和除法的优先级相同，进行先乘后除操作与先除后乘操作，得出的结果相同。
> - 在数学运算中，0不能用作除数，在MySQL中，一个数除以0为NULL。

### 比较运算符

> 比较运算符用来对表达式左边的操作数和右边的操作数进行比较，比较的结果为真则返回 1 ，比较的结果为假则返回 0 ，其他情况则返回NULL。
>
> 比较运算符经常被用来作为SELECT查询语句的条件来使用，返回符合条件的结果记录。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812113143041.png" alt="image-20220812113143041" style="zoom:50%;" />

#### 等号运算符

> - 如果等号两边的值、字符串或表达式都为字符串，则MySQL会按照字符串进行比较，其比较的是每个字符串中字符的ANSI编码是否相等。
>
> - 如果等号两边的值都是整数，则MySQL会按照整数来比较两个值的大小。
>
> - 如果等号两边的值一个是整数，另一个是字符串，则MySQL会将字符串转化为数字进行比较。
>
> - 如果等号两边的值、字符串或表达式中有一个为NULL，则比较结果为NULL。
>
> 对比：SQL中赋值符号使用 :

#### 安全等于运算符 

> 安全等于运算符（<=>）与等于运算符（=）的作用是相似的， 唯一的区别 是‘<=>’可以用来对NULL进行判断。在两个操作数均为NULL时，其返回值为1，而不为NULL；当一个操作数为NULL时，其返回值为0，而不为NULL。

#### 不等于运算符 

> 不等于运算符（<>和!=）用于判断两边的数字、字符串或者表达式的值是否不相等，如果不相等则返回1，相等则返回0。不等于运算符不能判断NULL值。如果两边的值有任意一个为NULL，或两边都为NULL，则结果为NULL。

#### 非符号类型的运算符

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812135656556.png" alt="image-20220812135656556" style="zoom:50%;" />

#### 空运算符 

> 空运算符（IS NULL或者ISNULL）判断一个值是否为NULL，如果为NULL则返回 1 ，否则返回0

#### 非空运算符 

> 非空运算符（IS NOT NULL）判断一个值是否不为NULL，如果不为NULL则返回 1 ，否则返回 0 。

#### 最小值运算符 

> 语法格式为：LEAST(值 1 ，值 2 ，...，值n)。其中，“值n”表示参数列表中有n个值。在有两个或多个参数的情况下，返回最小值。由结果可以看到，当参数是整数或者浮点数时，LEAST将返回其中最小的值；当参数为字符串时，返回字母表中顺序最靠前的字符；`当比较值列表中有NULL时，不能判断大小，返回值为NULL`。

#### 最大值运算符 

> 语法格式为：GREATEST(值 1 ，值 2 ，...，值n)。其中，n表示参数列表中有n个值。当有两个或多个参数时，返回值为最大值。假如任意一个自变量为NULL，则GREATEST()的返回值为NULL。由结果可以看到，当参数中是整数或者浮点数时，GREATEST将返回其中最大的值；当参数为字符串时，返回字母表中顺序最靠后的字符；`当比较值列表中有NULL时，不能判断大小，返回值为NULL`。

#### BETWEEN AND

> BETWEEN运算符使用的格式通常为SELECT D FROM TABLE WHERE C BETWEEN A AND B，此时，当C大于或等于A，并且C小于或等于B时，结果为1，否则结果为0。

#### IN和NOT IN

> - IN运算符用于判断给定的值是否是IN列表中的一个值，如果是则返回1，否则返回0。如果给定的值为NULL，或者IN列表中存在NULL，则结果为NULL。 
> - NOT IN运算符用于判断给定的值是否不是IN列表中的一个值，如果不是IN列表中的一个值，则返回 1 ，否则返回 0 。

#### LIKE运算符 

> LIKE运算符主要用来匹配字符串，通常用于模糊匹配，如果满足条件则返回 1 ，否则返回0 。如果给定的值或者匹配条件为NULL，则返回结果为NULL。
>
> - %”：匹配0个或多个字符。 
> - “_”：只能匹配一个字符。 

#### ESCAPE

> - 回避特殊符号的：**使用转义符**。
>
> - 如果使用\表示转义，省略ESCAPE。如果不是\，则要加上ESCAPE。ESCAPE作用就是指定一个字符替代“\”的作用。
>
>   ```yml
>   SELECT job_id FROM jobs WHERE job_id LIKE ‘IT$_%‘ escape ‘$‘;
>   ```

#### REGEXP运算符

> REGEXP运算符用来匹配字符串，语法格式为： expr REGEXP 匹配条件 。如果expr满足匹配条件，返回1；如果不满足，则返回0。若expr或匹配条件任意一个为NULL，则结果为NULL。
>
> REGEXP运算符在进行匹配时，常用的有下面几种通配符：
>
> （1）‘^’匹配以该字符后面的字符开头的字符串。 
>
> （2）‘$’匹配以该字符前面的字符结尾的字符串。 
>
> （3）‘.’匹配任何一个单字符。 
>
> （4）“[...]”匹配在方括号内的任何字符。例如，“[abc]”匹配“a”或“b”或“c”。为了命名字符的范围，使用一 个‘-’。“[a-z]”匹配任何字母，而“[0-9]”匹配任何数字。 
>
> （5）‘ * ’匹配零个或多个在它前面的字符。例如，“x * ”匹配任何数量的‘x’字符，“[0-9]*”匹配任何数量的数字， 而“*”匹配任何数量的任何字符。 

### 逻辑运算符

> 逻辑运算符主要用来判断表达式的真假，在MySQL中，逻辑运算符的返回结果为 1 、 0 或者NULL
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812152535849.png" alt="image-20220812152535849" style="zoom:50%;" />

#### 逻辑非运算符 

> 逻辑非（NOT或!）运算符表示当给定的值为0时返回1；当给定的值为非0值时返回0；当给定的值为NULL时，返回NULL。 

#### 逻辑与运算符 

> 逻辑与（AND或&&）运算符是当给定的所有值均为非 0 值，并且都不为NULL时，返回1 ；当给定的一个值或者多个值为 0 时则返回 0 ；否则返回NULL。

#### 逻辑或运算符 

> 逻辑或（OR或||）运算符是当给定的值都不为NULL，并且任何一个值为非 0 值时，则返回 1 ，否则返回 0 ；当一个值为NULL，并且另一个值为非 0 值时，返回 1 ，否则返回NULL；当两个值都为NULL时，返回NULL。
>
> OR可以和AND一起使用，但是在使用时要注意两者的优先级，由于AND的优先级高于OR，因此先对AND两边的操作数进行操作，再与OR中的操作数结合。

#### 逻辑异或运算符 

> 逻辑异或（XOR）运算符是当给定的值中任意一个值为NULL时，则返回NULL；如果两个非NULL的值都是 0 或者都不等于 0 时，则返回 0 ；如果一个值为 0 ，另一个值不为 0 时，则返回 1 。
>

### 位运算符

> 位运算符是在二进制数上进行计算的运算符。位运算符会先将操作数变成二进制数，然后进行位运算，最后将计算结果从二进制变回十进制数。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812152919596.png" alt="image-20220812152919596" style="zoom:50%;" />

#### 按位与运算符 

> 按位与（&）运算符将给定值对应的二进制数逐位进行逻辑与运算。当给定值对应的二进制位的数值都为 1 时，则该位返回 1 ，否则返回 0 。
>
> 1 的二进制数为 0001 ， 10 的二进制数为 1010 ，所以 1 & 10 的结果为 0000 ，对应的十进制数为 0 。 20 的二进制数为 10100 ， 30 的二进制数为 11110 ，所以 20 & 30 的结果为 10100 ，对应的十进制数为 20 。

#### 按位或运算符 

>  按位或（|）运算符将给定的值对应的二进制数逐位进行逻辑或运算。当给定值对应的二进制位的数值有一个或两个为1时，则该位返回1，否则返回0。
>
> 1的二进制数为0001，10的二进制数为1010，所以1 | 10的结果为1011，对应的十进制数为11。20的二进制数为10100，30的二进制数为11110，所以20 | 30的结果为11110，对应的十进制数为30。

#### 按位异或运算符 

> 按位异或（^）运算符将给定的值对应的二进制数逐位进行逻辑异或运算。当给定值对应的二进制位的数值不同时，则该位返回1，否则返回0。 
>
> 1的二进制数为0001，10的二进制数为1010，所以1 ^ 10的结果为1011，对应的十进制数为11。20的二进制数为10100，30的二进制数为11110，所以20 ^ 30的结果为01010，对应的十进制数为10

#### 按位取反运算符 

> 按位取反（~）运算符将给定的值的二进制数逐位进行取反操作，即将 1 变为 0 ，将 0 变为 1 。
>
> 由于按位取反（~）运算符的优先级高于按位与（&）运算符的优先级，所以10 & ~1，首先，对数字1进行按位取反操作，结果除了最低位为0，其他位都为1，然后与10进行按位与操作，结果为10。

#### 按位右移运算符 

> 按位右移（>>）运算符将给定的值的二进制数的所有位右移指定的位数。右移指定的位数后，右边低位的数值被移出并丢弃，左边高位空出的位置用0补齐。
>
> 1的二进制数为0000 0001，右移2位为0000 0000，对应的十进制数为0。4的二进制数为0000 0100，右移2位为0000 0001，对应的十进制数为1。 

#### 按位左移运算符 

> 按位左移（<<）运算符将给定的值的二进制数的所有位左移指定的位数。左移指定的位数后，左边高位的数值被移出并丢弃，右边低位空出的位置用0补齐。
>
> 1的二进制数为0000 0001，左移两位为0000 0100，对应的十进制数为4。4的二进制数为0000 0100，左移两位为0001 0000，对应的十进制数为16。 

### 运算符的优先级

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812153539637.png" alt="image-20220812153539637" style="zoom:50%;" />

> 数字编号越大，优先级越高，优先级高的运算符先进行计算。可以看到，赋值运算符的优先级最低，使用“()”括起来的表达式的优先级最高。

### 拓展：使用正则表达式查询

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220812153818975.png" alt="image-20220812153818975" style="zoom:50%;" />

## 排序与分页

### 排序

#### 排序规则

> 使用 ORDER BY 子句排序：ASC（ascend）: 升序、DESC（descend）:降序，ORDER BY 子句在SELECT语句的结尾。

#### 单列排序

```
SELECT last_name, job_id, department_id, hire_date
FROM employees
ORDER BY hire_date ;
```
#### 多列排序

> 可以使用不在SELECT列表中的列排序。
>
> 在对多列进行排序的时候，首先排序的第一列必须有相同的列值，才会对第二列进行排序。如果第一列数据中所有值都是唯一的，将不再对第二列进行排序。

```yml
SELECT last_name, department_id, salary FROM employees ORDER BY department_id, salary DESC;
```

### 分页

> MySQL中使用 LIMIT 实现分页，格式：LIMIT [位置偏移量,] 行数
>
> 第一个“位置偏移量”参数指示MySQL从哪一行开始显示，是一个可选参数，如果不指定“位置偏移量”，将会从表中的第一条记录开始（第一条记录的位置偏移量是 0 ，第二条记录的位置偏移量是1 ，以此类推）；第二个参数“行数”指示返回的记录条数。
>
> MySQL 8.0中可以使用“LIMIT 3 OFFSET 4”，意思是获取从第 5 条记录开始后面的 3 条记录，和“LIMIT 4,3;”返回的结果相同。

```sql
--前 10 条记录：
SELECT * FROM 表名 LIMIT 0 , 10 ;
或者
SELECT * FROM 表名 LIMIT 10 ;
```

> - 分页显式公式 ：（当前页数- 1 ）*每页条数，每页条数

```sql
SELECT * FROM table LIMIT (PageNo - 1 )*PageSize,PageSize;
```

> LIMIT子句必须放在整个SELECT语句的最后！
>
> 使用 LIMIT 的好处：约束返回结果的数量可以 减少数据表的网络传输量 ，也可以 提升查询效率 。如果我们知道返回结果只有1 条，就可以使用 LIMIT 1 ，告诉 SELECT 语句只需要返回一条记录即可。这样的好处就是 SELECT 不需要扫描完整的表，只需要检索到一条符合条件的记录即可返回。

#### 拓展

>  在不同的 DBMS 中使用的关键字可能不同。在 MySQL、PostgreSQL、MariaDB 和 SQLite 中使用 LIMIT 关键字，而且需要放到 SELECT 语句的最后面
>
>  - 如果是 SQL Server 和 Access，需要使用 TOP 关键字，比如：
>
>    ```sql
>  SELECT TOP 5 name, hp_max FROM heros ORDER BY hp_max DESC
>    ```
>  - 如果是 DB2，使用 FETCH FIRST 5 ROWS ONLY 这样的关键字：
>
>    ```sql
>  SELECT name, hp_max FROM heros ORDER BY hp_max DESC FETCH FIRST 5 ROWS ONLY 
>    ```
>
>  - 如果是 Oracle，你需要基于 ROWNUM 来统计行数：
>
>  ```sql
>  SELECT rownum,name, hp_max FROM heros WHERE rownum < 5 ORDER BY hp_max DESC;
>  ```
>
>  需要说明的是，这条语句是先取出来前 5 条数据行，然后再按照 hp_max 从高到低的顺序进行排序。但这样产生的结果和上述方法的并不一样。我会在后面讲到子查询，你可以使用
>
>    ```sql
>  SELECT rownum, name, hp_max
>  FROM (
>  	SELECT name, hp_max
>  	FROM heros 
>  	ORDER BY hp_max DESC
>  ) 
>  WHERE rownum < 5;
>    ```

## 多表查询

> 多表查询，也称为关联查询，指两个或更多个表一起完成查询操作。
>
> 前提条件：这些一起查询的表之间是有关系的（一对一、一对多），它们之间一定是有关联字段，这个关联字段可能建立了外键，也可能没有建立外键。比如：员工表和部门表，这两个表依靠“部门编号”进行关联。
>
> 超过三个表禁止 join。需要 join 的字段，数据类型保持绝对一致；多表关联查询时， 保证被关联的字段需要有索引。说明：即使双表 join 也要注意表索引、SQL 性能。

###  笛卡尔积（或交叉连接）的理解

> 笛卡尔乘积是一个数学运算。假设我有两个集合 X 和 Y，那么 X 和 Y 的笛卡尔积就是 X 和 Y 的所有可能组合，也就是第一个对象来自于 X，第二个对象来自于 Y 的所有可能。组合的个数即为两个集合中元素个数的乘积数。
>
> SQL92中，笛卡尔积也称为 交叉连接 ，英文是 CROSS JOIN 。在 SQL99 中也是使用 CROSS JOIN表示交叉连接。它的作用就是可以把任意表进行连接，即使这两张表不相关。

#### 案例分析与问题解决

> **笛卡尔积的错误会在下面条件下产生**：
>
> - 省略多个表的连接条件（或关联条件）
>
> - 连接条件（或关联条件）无效
>
> - 所有表中的所有行互相连接
>
> 为了避免笛卡尔积， 可以**在** **WHERE** **加入有效的连接条件。**
>
> **在表中有相同列时，在列名之前加上表名前缀。**

### 多表查询分类讲解

#### 等值连接 vs 非等值连接

##### 等值连接

> - 多个连接条件与 AND 操作符
>- 多个表中有相同列时，必须在列名之前加上表名前缀。
> - 用别名可以简化查询。如果我们使用了表的别名，在查询字段中、过滤条件中就只能使用别名进行代替，不能使用原有的表名，否则就会报错
>- 连接 n个表,至少需要n- 1 个连接条件。

```sql
SELECT table1.column, table2.column
FROM table1, table
WHERE table1.column 1 = table2.column 2 ;  -- 连接条件
```

##### 非等值连接

```sql
SELECT e.last_name, e.salary, j.grade_level 
FROM employees e, job_grades j 
WHERE e.salary BETWEEN j.lowest_sal AND j.highest_sal;
```


#### 自连接 vs 非自连接

>  当table1和table2本质上是同一张表，只是用取别名的方式虚拟成两张表以代表不同的意义。然后两个表再进行内连接，外连接等查询。

#### 内连接 vs 外连接

> 除了查询满足条件的记录以外，外连接还可以查询某一方不满足条件的记录。
>
> 内连接：合并具有同一列的两个以上的表的行, **结果集中不包含一个表与另一个表不匹配的行**
>
> 外连接：两个表在连接过程中除了返回满足连接条件的行以外**还返回左（或右）表中不满足条件的行 ，这种连接称为左（或右） 外连接**。没有匹配的行时, 结果表中相应的列为空(NULL)。
>
> 如果是左外连接，则连接条件中左边的表也称为 主表 ，右边的表称为 从表 。
>
> 如果是右外连接，则连接条件中右边的表也称为 主表 ，左边的表称为 从表 。

##### SQL 92 ：使用(+)创建连接

> 在 SQL92 中采用（+）代表从表所在的位置。即左或右外连接中，(+) 表示哪个是从表。
>
> Oracle 对 SQL92 支持较好，而 MySQL 则不支持 SQL92 的外连接。
>
> 在 SQL92 中，只有左外连接和右外连接，没有满（或全）外连接。

```sql
-- 左外连接 
SELECT last_name,department_name 
FROM employees ,departments 
WHERE employees.department_id = departments.department_id(+); 

-- 右外连接 
SELECT last_name,department_name 
FROM employees ,departments 
WHERE employees.department_id(+) = departments.department_id;
```

### SQL 99 语法实现多表查询

#### 基本语法

> 使用JOIN...ON子句创建连接的语法结构：
>
> **可以使用** **ON** **子句指定额外的连接条件**。
>
> **ON** **子句使语句具有更高的易读性**。
>
> ```sql
> SELECT table1.column, table2.column,table3.column
> FROM table
> JOIN table2 ON table1 和 table2 的连接条件
> JOIN table3 ON table2 和 table3 的连接条件
> ```

#### 内连接(INNER JOIN)

> JOIN和INNER JOIN等价

```sql
SELECT 字段列表 
FROM A表 
INNER JOIN B表 ON 关联条件 
WHERE 等其他子句;
```

#### 外连接(OUTER JOIN)

> 需要注意的是，LEFT JOIN 和 RIGHT JOIN 只存在于 SQL99 及以后的标准中，在 SQL92 中不存在，只能用 (+) 表示。

##### 左外连接(LEFT OUTER JOIN)

> LEFT OUTER JOIN 等价于 LEFT JOIN

```sql
SELECT 字段列表
FROM A表 
LEFT JOIN B表 ON 关联条件 
WHERE 等其他子句
```

##### 右外连接(RIGHT OUTER JOIN)

> RIGHT OUTER JOIN 等价于 RIGHT JOIN

```sql
SELECT 字段列表
FROM A表 
RIGHT JOIN B表 ON 关联条件 
WHERE 等其他子句;
```

##### 满外连接(FULL OUTER JOIN)

> 满外连接的结果 = 左右表匹配的数据 + 左表没有匹配到的数据 + 右表没有匹配到的数据。
>
> SQL99是支持满外连接的。使用FULL JOIN 或 FULL OUTER JOIN来实现。
>
> 需要注意的是，`MySQL不支持FULL JOIN，但是可以用 LEFT JOIN UNION RIGHT JOIN代替。`

#### 交叉连接(CROSS JOIN)

> CROSS JOIN为笛卡尔积

```sql
-- (注：cross join后加条件只能用where,不能用on)
select * from table1 a 
cross join table2 b 
where a.id=b.id 
```

### UNION的使用

> **合并查询结果** 利用UNION关键字，可以给出多条SELECT语句，并将它们的结果组合成单个结果集。合并时，两个表对应的列数和数据类型必须相同，并且相互对应。各个SELECT语句之间使用UNION或UNION ALL关键字分隔。

```sql
SELECT column,... FROM table1 
UNION [ALL] 
SELECT column,... FROM table2
```

> - UNION 操作符返回两个查询的结果集的并集，去除重复记录。
> - UNION ALL操作符返回两个查询的结果集的并集。对于两个结果集的重复部分，不去重。
> - 注意：执行UNION ALL语句时所需要的资源比UNION语句少。如果明确知道合并数据后的结果数据不存在重复数据，或者不需要去除重复的数据，则尽量使用UNION ALL语句，以提高数据查询的效率。

### 7 种SQL JOINS的实现

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813094821336.png" alt="image-20220813094821336" style="zoom:37%;" />

### SQL 99 语法新特性

#### 自然连接

> SQL 99 在 SQL 92 的基础上提供了一些特殊语法，比如 NATURAL JOIN 用来表示自然连接。我们可以把自然连接理解为 SQL 92 中的等值连接。它会帮你自动查询两张连接表中所有相同的字段，然后进行等值连接。
>
> - 在SQL 92 标准中：
>
>   ```sql
>   SELECT employee_id,last_name,department_name 
>   FROM employees e
>   JOIN departments d 
>   ON e.`department_id` = d.`department_id` AND e.`manager_id` = d.`manager_id`;
>   ```
>
> - 在 SQL 99 中你可以写成：
>
>   ```sql
>   SELECT employee_id,last_name,department_name 
>   FROM employees e NATURAL JOIN departments d;
>   ```

#### USING连接

> 当我们进行连接的时候，SQL 99 还支持使用 USING 指定数据表里的同名字段进行等值连接。但是只能配合JOIN一起使用。USING 指定了具体的相同的字段名称，你需要在 USING的括号 () 中填入要指定的同名字段。
>
> ```sql
> SELECT employee_id,last_name,department_name 
> FROM employees e JOIN departments d USING (department_id);
> ```
>
> 使用 JOIN...USING 可以简化 JOIN ON 的等值连接。它与下面的 SQL 查询结果是相同的
>
> ```sql
> SELECT employee_id,last_name,department_name 
> FROM employees e ,departments d 
> WHERE e.department_id = d.department_id;
> ```

### 附录：常用的 SQL 标准

> SQL 有两个主要的标准，分别是` SQL92 和 SQL99 `。92 和 99 代表了标准提出的时间，SQL92 就是 92 年提出的标准规范。当然除了 SQL92 和 SQL99 以外，还存在 SQL-86、SQL-89、SQL:2003、SQL:2008、 SQL:2011 和 SQL:2016 等其他的标准。
>
> **实际上最重要的 SQL 标准就是 SQL92 和 SQL99**。一般来说 SQL92 的形式更简单，但是写的 SQL 语句会比较长，可读性较差。而 SQL99 相比于 SQL92 来说，语法更加复杂，但可读性更强。我们从这两个标准发布的页数也能看出，SQL92 的标准有 500 页，而 SQL99 标准超过了1000 页。
>
> **SQL92** **和** **SQL99** **是经典的** **SQL** **标准，也分别叫做** **SQL-2** **和** **SQL-3** **标准。**也正是在这两个标准发布之后，SQL 影响力越来越大，甚至超越了数据库领域。现如今 SQL 已经不仅仅是数据库领域的主流语言，还是信息领域中信息处理的主流语言。在图形检索、图像检索以及语音检索中都能看到 SQL 语言的使用。

## 单行函数

### 函数的理解

#### 什么是函数

> 函数是把我们经常使用的代码封装起来，需要的时候直接调用即可。这样既提高了代码效率，又提高了可维护性。
>
> 从函数定义的角度出发，我们可以将函数分成 内置函数 和 自定义函数。
>
> 内置函数是系统内置的通用函数，而自定义函数是我们根据自己的需要编写的

#### 不同DBMS函数的差异

> **DBMS** **之间的差异性很大，远大于同一个语言不同版本之间的差异。**实际上，只有很少的函数是被 DBMS 同时支持的。比如，大多数 DBMS 使用（||）或者（+）来做拼接符，而在 MySQL 中的字符串拼接函数为concat()。大部分 DBMS 会有自己特定的函数，这就意味着**采用** **SQL** **函数的代码可移植性是很差的**


#### MySQL的内置函数及分类

> MySQL提供的内置函数从 `实现的功能角度` 可以分为数值函数、字符串函数、日期和时间函数、流程控制函数、加密与解密函数、获取MySQL信息函数、聚合函数等。
>
> 我将这些丰富的内置函数再分为两类： 单行函数 、 聚合函数（或分组函数）。
>
> 单行函数：**只对一行进行变换，每行返回一个结果**

### 数值函数

#### 基本函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813102803518.png" alt="image-20220813102803518" style="zoom:50%;" />


#### 角度与弧度互换函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813103040797.png" alt="image-20220813103040797" style="zoom:50%;" />

#### 三角函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813103116132.png" alt="image-20220813103116132" style="zoom:50%;" />

#### 指数与对数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813103157324.png" alt="image-20220813103157324" style="zoom:50%;" />

#### 进制间的转换

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813103211968.png" alt="image-20220813103211968" style="zoom:50%;" />

### 字符串函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813103531131.png" alt="image-20220813103531131" style="zoom:67%;" />

### 日期和时间函数

#### 获取日期、时间

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813105209278.png" alt="image-20220813105209278" style="zoom:50%;" />

#### 日期与时间戳的转换

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813105312120.png" alt="image-20220813105312120" style="zoom:70%;" />

#### 获取月份、星期、星期数、天数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813105446805.png" alt="image-20220813105446805" style="zoom:70%;" />

#### 日期的操作函数

```sql
SELECT EXTRACT(MINUTE FROM NOW()),
				EXTRACT( WEEK FROM NOW()), 
				EXTRACT( QUARTER FROM NOW()),
				EXTRACT( MINUTE_SECOND FROM NOW()) 
FROM DUAL;
```

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813105805239.png" alt="image-20220813105805239" style="zoom:67%;" />

#### 时间和秒钟转换的函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813105940439.png" alt="image-20220813105940439" style="zoom:67%;" />

#### 计算日期和时间的函数

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813135538727.png" alt="image-20220813135538727" style="zoom:50%;" />

```sql
SELECT DATE_ADD(NOW(), INTERVAL 1 DAY) AS col1,
				DATE_ADD('2021-10-21 23:32:12',INTERVAL 1 SECOND) AS col2, 
				ADDDATE('2021-10-21 23:32:12',INTERVAL 1 SECOND) AS col3, 
				DATE_ADD('2021-10-21 23:32:12',INTERVAL '1_1' MINUTE_SECOND) AS col4, 
				-- 可以是负数 
				DATE_ADD(NOW(), INTERVAL -1 YEAR) AS col5, 
				-- 需要单引号  
				DATE_ADD(NOW(), INTERVAL '1_1' YEAR_MONTH) AS col6
FROM DUAL; 

SELECT DATE_SUB('2021-01-21',INTERVAL 31 DAY) AS col1, 
			SUBDATE('2021-01-21',INTERVAL 31 DAY) AS col2, 
			DATE_SUB('2021-01-21 02:01:01',INTERVAL '1 1' DAY_HOUR) AS col3 
FROM DUAL;
```

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813135612097.png" alt="image-20220813135612097" style="zoom:50%;" />

#### 日期的格式化与解析

![image-20220813140356857](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813140356857.png)

> 上述 非GET_FORMAT 函数中fmt参数常用的格式符：
>
> ```sql
> SELECT DATE_FORMAT(NOW(), '%H:%i:%s');
> SELECT STR_TO_DATE('2014-04-22 15:47:06','%Y-%m-%d %H:%i:%s') FROM DUAL;
> ```
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813141707045.png" alt="image-20220813141707045" style="zoom:50%;" />
>
> GET_FORMAT函数中date_type和format_type参数取值如下：
>
> ```sql
> SELECT GET_FORMAT(DATE, 'USA');
> ```
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813141749320.png" alt="image-20220813141749320" style="zoom:50%;" />

### 流程控制函数

> 流程处理函数可以根据不同的条件，执行不同的处理流程，可以在SQL语句中实现不同的条件选择。MySQL中的流程处理函数主要包括IF()、IFNULL()和CASE()函数。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813142450110.png" alt="image-20220813142450110" style="zoom:50%;" />

### 加密与解密函数

> 加密与解密函数主要用于对数据库中的数据进行加密和解密处理，以防止数据被他人窃取。这些函数在保证数据库安全时非常有用。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813142604708.png" alt="image-20220813142604708" style="zoom:50%;" />

### MySQL信息函数

> MySQL中内置了一些可以查询MySQL信息的函数，这些函数主要用于帮助数据库开发或运维人员更好地对数据库进行维护工作。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813143011847.png" alt="image-20220813143011847" style="zoom:50%;" />

### 其他函数

> MySQL中有些函数无法对其进行具体的分类，但是这些函数在MySQL的开发和运维过程中也是不容忽视的。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813143122805.png" alt="image-20220813143122805" style="zoom:50%;" />

## 聚合函数

### 聚合函数介绍

> 聚合函数作用于一组数据，并对一组数据返回一个值。聚合函数不能嵌套调用。比如不能出现类似“AVG(SUM(字段名称))”形式的调用。

#### AVG和SUM函数

> 可以对 数值型数据 使用AVG 和 SUM 函数。

#### MIN和MAX函数

> 可以对 任意数据类型 的数据使用 MIN 和 MAX 函数。

#### COUNT函数

> - COUNT(*)返回表中记录总数，适用于 任意数据类型 。
>
> - COUNT(expr) 返回 expr不为空 的记录总数。
>
> - 用count( * )，count(1)，count(列名)：
>   MyISAM引擎的表是没有区别的，这种引擎内部有一计数器在维护着行数。
>   
>   Innodb引擎的表：
>   
>   1、count( * )包括了所有的列，相当于行数，在统计结果的时候，不会忽略列值为NULL。
>   
>   2、count(1)包括了忽略所有列，用1代表代码行，在统计结果的时候，不会忽略列值为NULL。
>   
>   3、count(列名)只包括列名那一列，在统计结果的时候，会忽略列值为空。
>   
>   效率：count(*) ≈ count(1) > count(id) > count(字段)

### GROUP BY

#### 基本使用

> - 可以使用GROUP BY子句将表中的数据分成若干组
>
> - 在SELECT列表中所有未包含在组函数中的列都应该包含在GROUP BY子句中
>
>   ```sql
>   SELECT department_id, AVG(salary) FROM employees GROUP BY department_id ;
>   ```
>
> - 包含在 GROUP BY 子句中的列不必包含在SELECT 列表中
>
>   ```sql
>   SELECT AVG(salary) FROM employees GROUP BY department_id ;
>   ```

#### GROUP BY中使用WITH ROLLUP

> - 使用WITH ROLLUP关键字之后，在所有查询出的分组记录之后增加一条记录，该记录计算查询出的所有记录的总和，即统计记录数量
>
> - 当使用ROLLUP时，不能同时使用ORDER BY子句进行结果排序，即ROLLUP和ORDER BY是互相排斥
>
>   ```sql
>   SELECT department_id,AVG(salary) 
>   FROM employees 
>   WHERE department_id > 80 
>   GROUP BY department_id WITH ROLLUP;
>   ```

### HAVING

#### 基本使用

> 由于不能在 WHERE 子句中使用聚合函数，所以使用 HAVING 对聚合函数的结果进行筛选

```sql
SELECT department_id, MAX(salary) 
FROM employees 
GROUP BY department_id 
HAVING MAX(salary)>10000 ;
```

#### WHERE和HAVING的对比

> - WHERE 查询条件中不可以使用聚合函数，而 HAVING 查询条件中可以使用聚合函数。
>
> - WHERE 在数据分组前进行过滤，而 HAVING 在数据分组后进行过滤 。
>
> - WHERE 根据数据表中的字段直接进行过滤，而 HAVING 是根据前面已经查询出的字段进行过滤。WHERE 比 HAVING 更高效
>
>   ```sql
>   select id from tb_article where update_time is not null;-- 不会报错
>   select id from tb_article having update_time is not null;-- 会报错，查询字段没有 update_time
>   ```
>
> - WHERE 查询条件中不可以使用字段别名，而 HAVING 查询条件中可以使用字段别名。
>
>   ```sql
>   select id as a, update_time as b 
>   from tb_article  
>   having b is not null;
>   ```

### SELECT的执行过程

#### 查询的结构

```sql
SELECT ...,....,...
FROM ... JOIN ...
ON 多表的连接条件
JOIN ...
ON ...
WHERE 不包含组函数的过滤条件
AND/OR 不包含组函数的过滤条件
GROUP BY ...,...
HAVING 包含组函数的过滤条件
ORDER BY ... ASC/DESC
LIMIT ...,...
```
#### SELECT顺序

> - 关键字的顺序：SELECT ... FROM ... WHERE ... GROUP BY ... HAVING ... ORDER BY ... LIMIT... 
>
> - 语句的执行顺序（在 MySQL 和 Oracle 中，SELECT 执行顺序基本相同）：
>
>   FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> DISTINCT -> ORDER BY -> LIMIT 
>
>   在 SELECT 语句执行这些步骤的时候，每个步骤都会产生一个 虚拟表 ，然后将这个虚拟表传入下一个步骤中作为输入。需要注意的是，这些步骤隐含在 SQL 的执行过程中，对于我们来说是不可见的。
>
>     <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813161539282.png" alt="image-20220813161539282" style="zoom:30%;" />
>

#### SQL 的执行原理

> - 先执行 FROM 这一步的。在这个阶段，如果是多张表联查，还会经历下面的几个步骤：
>   - 先通过 CROSS JOIN 求笛卡尔积，相当于得到虚拟表 vt（virtual table）1-1； 
>   - 通过 ON 进行筛选，在虚拟表 vt1-1 的基础上进行筛选，得到虚拟表 vt1-2； 
>   - 添加外部行。如果我们使用的是左连接、右链接或者全连接，就会涉及到外部行，也就是在虚拟表 vt1-2 的基础上增加外部行，得到虚拟表 vt1-3。
>
> - 当我们拿到了查询数据表的原始数据，也就是最终的虚拟表 vt1 ，就可以在此基础上再进行 WHERE 阶 段 。在这个阶段中，会根据 vt1 表的结果进行筛选过滤，得到虚拟表 vt2 。
>
> - 然后进入第三步和第四步，也就是 GROUP 和 HAVING 阶段 。在这个阶段中，实际上是在虚拟表 vt2 的基础上进行分组和分组过滤，得到中间的虚拟表 vt3 和 vt4 。
>
> - 当我们完成了条件筛选部分之后，就可以筛选表中提取的字段，也就是进入到 SELECT 和 DISTINCT 阶段 。首先在 SELECT 阶段会提取想要的字段，然后在 DISTINCT 阶段过滤掉重复的行，分别得到中间的虚拟表vt5-1 和 vt5-2 。
>
> - 当我们提取了想要的字段数据之后，就可以按照指定的字段进行排序，也就是 ORDER BY 阶段 ，得到虚拟表 vt6 。
>
> - 最后在 vt6 的基础上，取出指定行的记录，也就是 LIMIT 阶段 ，得到最终的结果，对应的是虚拟表vt7 。

## 子查询

> 子查询指一个查询语句嵌套在另一个查询语句内部的查询，这个特性从MySQL 4.1开始引入。

### 子查询的基本使用

#### 子查询的基本语法结构

```sql
SELECT last_name,salary 
FROM employees 
WHERE salary > ( SELECT salary FROM employees WHERE last_name = 'Abel' );
```

#### 子查询的分类

> - 我们按内查询的结果返回一条还是多条记录，将子查询分为 单行子查询 、 多行子查询 。
> - 我们按内查询是否被执行多次，将子查询划分为 相关(或关联)子查询 和 不相关(或非关联)子查询 。
>   - 子查询从数据表中查询了数据结果，如果这个数据结果只执行一次，然后这个数据结果作为主查询的条件进行执行，那么这样的子查询叫做不相关子查询。
>   - 如果子查询需要执行多次，即采用循环的方式，先从外部查询开始，每次都传入子查询进行查询，然后再将结果反馈给外部，这种嵌套的执行方式就称为相关子查询。

### 单行子查询

#### 单行比较操作符

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813170427941.png" alt="image-20220813170427941" style="zoom:50%;" />

### 多行子查询

#### 多行比较操作符

> ANY和ALL区别：配合单行比较操作符一起用，如： < ANY ( 子查询 )；ANY小于一个，ALL小于所有

![image-20220813170620229](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813170620229.png)

### 相关子查询

> 如果子查询的执行依赖于外部查询，通常情况下都是因为子查询中的表用到了外部的表，并进行了条件关联，因此每执行一次外部查询，子查询都要重新计算一次，这样的子查询就称之为 关联子查询 。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220813172533913.png" alt="image-20220813172533913" style="zoom:50%;" />

#### EXISTS 与 NOT EXISTS

> - 关联子查询通常也会和 EXISTS操作符一起来使用，用来检查在子查询中是否存在满足条件的行。
>
> - **如果在子查询中不存在满足条件的行：**
>   - 条件返回 FALSE
>   - 继续在子查询中查找
>
> - **如果在子查询中存在满足条件的行：**
>   - 不在子查询中继续查找
>   - 条件返回 TRUE 
>
> - NOT EXISTS关键字表示如果不存在某种条件，则返回TRUE，否则返回FALSE。

#### 相关更新

> 使用相关子查询依据一个表中的数据更新另一个表的数据。

```sql
UPDATE table1 alias1 
SET column = (SELECT expression 
              FROM table2 alias2 
              WHERE alias1.column = alias2.column);
```

#### 相关删除

> 使用相关子查询依据一个表中的数据删除另一个表的数据。

```sql
DELETE FROM table1 alias1 
WHERE column operator (
  SELECT expression 
  FROM table2 alias2 
  WHERE alias1.column = alias2.column);
```

## 创建和管理表

### 基础知识

#### 标识符命名规则

> - 数据库名、表名不得超过30个字符，变量名限制为29个
> - 必须只能包含 A–Z, a–z, 0–9, _共63个字符
> - 数据库名、表名、字段名等对象名中间不要包含空格
> - 同一个MySQL软件中，数据库不能同名；同一个库中，表不能重名；同一个表中，字段不能重名
> - 必须保证你的字段没有和保留字、数据库系统或常用方法冲突。如果坚持使用，请在SQL语句中使用`（着重号）引起来
> - 保持字段名和类型的一致性：在命名字段并为其指定数据类型的时候一定要保证一致性，假如数据类型在一个表里是整数，那在另一个表里可就别变成字符型了

#### MySQL中的数据类型

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815093515443.png" alt="image-20220815093515443" style="zoom:50%;" />

> 常用数据类型

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815093544314.png" alt="image-20220815093544314" style="zoom:50%;" />

### 创建和管理数据库

#### 创建数据库

> 方式1：创建数据库
>
> ```sql
> CREATE DATABASE 数据库名;
> ```
>
> 方式2：创建数据库并指定字符集
>
> ```sql
> CREATE DATABASE 数据库名 CHARACTER SET 字符集;
> ```
>
> 方式3：判断数据库是否已经存在，不存在则创建数据库（ 推荐 ）
>
> ```sql
> CREATE DATABASE IF NOT EXISTS 数据库名;
> ```
>
> 注意：DATABASE 不能改名。一些可视化工具可以改名，它是建新库，把所有表复制到新库，再删旧库完成的。

#### 使用数据库

> 查看当前所有的数据库
>
> ```sql
> SHOW DATABASES; -- 有一个S，代表多个数据库
> ```
>
> 查看当前正在使用的数据库
>
> ```sql
> SELECT DATABASE(); -- 使用的一个 mysql 中的全局函数
> ```
>
> 查看指定库下所有的表
>
> ```sql
> SHOW TABLES FROM 数据库名;
> ```
>
> 查看数据库的创建信息
>
> ```sql
> SHOW CREATE DATABASE 数据库名;
> ```
>
> 使用/切换数据库
>
> ```sql
> USE 数据库名;
> ```
>
> 注意：要操作表格和数据之前必须先说明是对哪个数据库进行操作，否则就要对所有对象加上“数据库名.”。

#### 修改数据库

> 更改数据库字符集
>
> ```sql
> ALTER DATABASE 数据库名 CHARACTER SET 字符集; -- 比如：gbk、utf8等
> ```
>
> 删除数据库
>
> - 方式 1 ：删除指定的数据库
>
>   ```sql
>   DROP DATABASE 数据库名;
>   ```
>
> - 方式 2 ：删除指定的数据库（推荐）
>
>   ```sql
>   DROP DATABASE IF EXISTS 数据库名;
>   ```

### 创建表

#### 创建方式 1

> **必须具备：**
>
> - CREATE TABLE权限
> - 存储空间
>
> **语法格式：**
>
> ```sql
> CREATE TABLE [IF NOT EXISTS] 表名
> ( 字段1, 数据类型 [约束条件] [默认值], 
> 	字段2, 数据类型 [约束条件] [默认值], 
> 	字段3, 数据类型 [约束条件] [默认值], 
>  	……[表约束条件] );
> ```
>
> 加上了IF NOT EXISTS关键字，则表示：如果当前数据库中不存在要创建的数据表，则创建数据表；如果当前数据库中已经存在要创建的数据表，则忽略建表语句，不再创建数据表。
>
> **必须指定：**表名，列名(或字段名)，数据类型，**长度**
>
> **可选指定：**约束条件，默认值
>
> **举例**
>
> ```sql
> CREATE TABLE emp (
>   -- int类型 
>   emp_id INT,
>   -- 最多保存20个中英文字符 
>   emp_name VARCHAR(20),
>   -- 总位数不超过15位 
>   salary DOUBLE,
>   -- 日期类型 
>   birthday DATE 
> );
> 
> DESC emp;
> ```

#### 创建方式 2

> 使用 AS subquery 选项，**将创建表和插入数据结合起来**
>
> 指定的列和子查询中的列要一一对应
>
> 通过列名和默认值定义列
>
> ```sql
> CREATE TABLE emp1 AS SELECT * FROM employees; 
> 
> CREATE TABLE emp2 AS SELECT * FROM employees WHERE 1=2; -- 创建的emp2是空表
> 
> CREATE TABLE dept80 AS 
> 	SELECT employee_id, last_name, salary*12 ANNSAL, hire_date 
> 	FROM employees 
> 	WHERE department_id = 80; 
> 
> DESC dept80;
> ```

#### 查看数据表结构

> - 使用DESCRIBE/DESC语句查看数据表结构
> - 使用SHOW CREATE TABLE语句查看表创建时的详细语句，还可以查看存储引擎和字符编码。

```sql
DESC 表名;
SHOW CREATE TABLE 表名;
```

### 修改表

> 修改表指的是修改数据库中已经存在的数据表的结构。
>
> **使用** **ALTER TABLE** **语句可以实现：**添加列、修改列、删除列、重命名列

#### 追加一个列

```sql
ALTER TABLE 表名 ADD 【COLUMN】 字段名 字段类型 【FIRST|AFTER 字段名】;
ALTER TABLE dept ADD job_id varchar( 15 );
```

#### 修改一个列

> 修改列的数据类型，长度、默认值和位置
>
 ```sql
ALTER TABLE 表名 MODIFY 【COLUMN】 字段名1 字段类型 【DEFAULT 默认值】【FIRST|AFTER 字段名 2】;
ALTER TABLE dept80 MODIFY salary double(9,2) default 1000;
 ```

#### 删除一个列

```sql
ALTER TABLE 表名 DROP 【COLUMN】字段名;
ALTER TABLE dept80 DROP COLUMN job_id;
```

#### 重命名一个列

```sql
ALTER TABLE 表名 CHANGE 【column】 列名 新列名 新数据类型; 
ALTER TABLE dept80 CHANGE department_name dept_name varchar(15);
```

### 重命名表

```sql
-- 方式1
RENAME TABLE emp TO myemp; 
-- 方式2
ALTER table dept RENAME [TO] detail_dept; -- [TO]可以省略 
```

### 删除表

> - 在MySQL中，当一张数据表 没有与其他任何数据表形成关联关系 时，可以将当前数据表直接删除。
> - 数据、表结构、所有相关索引都被删除
> - 所有正在运行的相关事务被提交
>
> - 删除表操作不可回滚
>
> 语法格式：
>
> ```sql
> DROP TABLE [IF EXISTS] 数据表1 [, 数据表2, …, 数据表n];
> ```

### 清空表

> - 删除表中所有的数据
>
> - 释放表的存储空间
> - TRUNCATE语句**不能回滚**，而使用 DELETE 语句删除数据，可以回滚
>
> 举例：
>
> ```sql
> TRUNCATE TABLE detail_dept;
> ```

> 阿里开发规范：
>
> 【参考】TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少，但 TRUNCATE 无事务且不触发 TRIGGER，有可能造成事故，故不建议在开发代码中使用此语句。

## 数据处理之增删改

### 插入数据

#### VALUES的方式添加

**情况1**：为表的所有字段按默认顺序插入数据

```sql
INSERT INTO departments VALUES (100, 'Finance', NULL, NULL);
```

**情况2**：为表的指定字段插入数据，INSERT语句中只向部分字段中插入值，而其他字段的值为表定义时的默认值。

```sql
INSERT INTO departments(department_id, department_name) VALUES (80, 'IT');
```

**情况3**：同时插入多条记录，插入时指定多个值列表，每个值列表之间用逗号分隔开

```sql
INSERT INTO emp(emp_id,emp_name) VALUES (1001,'shkstart'), (1002,'atguigu'), (1003,'Tom');
```

一个同时插入多行记录的INSERT语句等同于多个单行插入的INSERT语句，但是多行的INERT语句在处理过程中 效率更高。使用INSERT同时插入多条记录时，MySQL会返回一些在执行单行插入时没有的额外信息，这些信息的含义如下： 

- Records：表明插入的记录条数。 
- Duplicates：表明插入时被忽略的记录，原因可能是这些记录包含了重复的主键值。 
- Warnings：表明有问题的数据值，例如发生数据类型转换。

**小结：**VALUES 也可以写成 VALUE ，但是VALUES是标准写法。字符和日期型数据应包含在单引号中。

#### 将查询结果插入到表中

> - 在 INSERT 语句中加入子查询。
> - 不必书写 VALUES 子句。
> - 子查询中的值列表应与 INSERT 子句中的列名对应

```sql
INSERT INTO sales_reps(id, name, salary, commission_pct) 
  SELECT employee_id, last_name, salary, commission_pct 
  FROM employees 
  WHERE job_id LIKE '%REP%';
```

### 更新数据

> - 使用 **WHERE** 子句指定需要更新的数据。省略 WHERE 子句，则表中的所有数据都将被更新。
>
> ```sql
> UPDATE employees SET department_id = 70 WHERE employee_id = 113;
> ```
>
> **更新中的数据完整性错误**：不存在 113 号部门

### 删除数据

> - 使用 WHERE 子句删除指定的记录。如果省略 WHERE 子句，则表中的全部数据将被删除
>
>   ```sql
>   DELETE FROM departments WHERE department_name = 'Finance';
>   ```

### MySQL 8 新特性：计算列

> CREATE TABLE 和 ALTER TABLE 中都支持增加计算列。某一列的值是通过别的列计算得来的。例如，a列值为1、b列值为2，c列
>
> 不需要手动插入，定义a+b的结果为c的值，那么c就是计算列，是通过别的列计算得来的。

```sql
CREATE TABLE tb1( id INT, a INT, b INT, c INT GENERATED ALWAYS AS (a + b) VIRTUAL );
```

##### 类型 类型举例

##### 整数类型 TINYINT、SMALLINT、MEDIUMINT、INT(或INTEGER)、BIGINT

##### 浮点类型 FLOAT、DOUBLE

##### 定点数类型 DECIMAL

##### 位类型 BIT

##### 日期时间类型 YEAR、TIME、DATE、DATETIME、TIMESTAMP

##### 文本字符串类型 CHAR、VARCHAR、TINYTEXT、TEXT、MEDIUMTEXT、LONGTEXT

##### 枚举类型 ENUM

##### 集合类型 SET

##### 二进制字符串类

##### 型

##### BINARY、VARBINARY、TINYBLOB、BLOB、MEDIUMBLOB、LONGBLOB

##### JSON类型 JSON对象、JSON数组

##### 空间数据类型

##### 单值类型：GEOMETRY、POINT、LINESTRING、POLYGON；

##### 集合类型：MULTIPOINT、MULTILINESTRING、MULTIPOLYGON、

##### GEOMETRYCOLLECTION

```
MySQL关键字 含义
```
```
NULL 数据列可包含NULL值
```
```
NOT NULL 数据列不允许包含NULL值
```
```
DEFAULT 默认值
```
```
PRIMARY KEY 主键
```
```
AUTO_INCREMENT 自动递增，适用于整数类型
```
```
UNSIGNED 无符号
```
```
CHARACTER SET name 指定一个字符集
```
## MySQL数据类型精讲

### MySQL中的数据类型

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815112032562.png" alt="image-20220815112032562" style="zoom:50%;" />

> 常见数据类型的属性

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815112053246.png" alt="image-20220815112053246" style="zoom:50%;" />

### 整数类型

> 整数类型一共有 5 种，包括 TINYINT、SMALLINT、MEDIUMINT、INT（INTEGER）和 BIGINT。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815135803218.png" alt="image-20220815135803218" style="zoom:70%;" />

#### 可选属性

##### M

> - M: 表示显示宽度，M的取值范围是( 0 , 255 )。例如，int( 5 )：当数据宽度小于 5 位的时候在数字前面需要用字符填满宽度。该项功能需要配合“ZEROFILL”使用，表示用“ 0 ”填满宽度，否则指定显示宽度无效。
>
> - 设置了显示宽度，那么插入的数据宽度超过显示宽度限制，不会截断或插入失败，不会对插入的数据有任何影响，还是按照类型的实际宽度进行保存，即显示宽度与类型可以存储的值范围无关。 **从MySQL 8. 0. 17 开始，整数数据类型不推荐使用显示宽度属性。**
>
> - 整型数据类型可以在定义表结构时指定所需要的显示宽度，如果不指定，则系统为每一种类型指定默认的宽度值。（MySQL 5. 7 中显式如下，MySQL 8 中不再显式范围）
>
>   ![image-20220815140858808](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815140858808.png)
>
> - TINYINT有符号数和无符号数的取值范围分别为-128~127和0~255，由于负号占了一个数字位，因此TINYINT默认的显示宽度为4。同理，其他整数类型的默认显示宽度与其有符号数的最小值的宽度相同。

##### UNSIGNED

> - UNSIGNED : 无符号类型（非负），所有的整数类型都有一个可选的属性UNSIGNED（无符号属性），无符号整数类型的最小取值为0。所以，如果需要在MySQL数据库中保存非负整数值时，可以将整数类型设置为无符号类型。
>
> - int类型默认显示宽度为int(11)，无符号int类型默认显示宽度为int(10)。 

##### ZEROFILL

> - ZEROFILL : 0填充,（如果某列是ZEROFILL，那么MySQL会自动为当前列添加UNSIGNED属性），如果指定了ZEROFILL只是表示不够M位时，用0在左边填充，如果超过M位，只要不超过数据存储范围即可。
>
> - 原来，在 int(M) 中，M 的值跟 int(M) 所占多少存储空间并无任何关系。 int(3)、int(4)、int(8) 在磁盘上都是占用 4 bytes 的存储空间。也就是说，**int(M)，必须和UNSIGNED ZEROFILL**一起使用才有意义。如果整数值超过M位，就按照实际位数存储。只是无须再用字符 0 进行填充。

#### 适用场景

> - TINYINT ：一般用于枚举数据，比如系统设定取值范围很小且固定的场景。
>
> - SMALLINT ：可以用于较小范围的统计数据，比如统计工厂的固定资产库存数量等。
>
> - MEDIUMINT ：用于较大整数的计算，比如车站每日的客流量等。
>
> - INT、INTEGER ：取值范围足够大，一般情况下不用考虑超限问题，用得最多。比如商品编号。
>
> - BIGINT ：只有当你处理特别巨大的整数时才会用到。比如双十一的交易量、大型门户网站点击量、证券公司衍生产品持仓等。

#### 如何选择？

> 在评估用哪种整数类型的时候，你需要考虑` 存储空间 和 可靠性 `的平衡问题：一方 面，用占用字节数少的整数类型可以节省存储空间；另一方面，要是为了节省存储空间， 使用的整数类型取值范围太小，一旦遇到超出取值范围的情况，就可能引起 系统错误 ，影响可靠性。

### 浮点类型

#### 类型介绍

> 浮点数和定点数类型的特点是可以 处理小数 ，你可以把整数看成小数的一个特例。因此，浮点数和定点数的使用场景，比整数大多了。 MySQL支持的浮点数类型，分别是 FLOAT、DOUBLE、REAL。 
>
> - FLOAT 表示单精度浮点数；
>
> - DOUBLE 表示双精度浮点数；
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815145201884.png" alt="image-20220815145201884" style="zoom:50%;" />
>
> - REAL默认就是 DOUBLE。如果你把 SQL 模式设定为启用“REAL_AS_FLOAT”，那么，MySQL 就认为REAL 是 FLOAT。如果要启用“REAL_AS_FLOAT”，可以通过以下 SQL 语句实现：
>
>   ```sql
>   SET sql_mode = "REAL_AS_FLOAT";
>   ```
>
> - 为什么浮点数类型的无符号数取值范围，只相当于有符号数取值范围的一半？
>
>   MySQL 存储浮点数的格式为：`符号(S)、尾数(M)和 阶码(E)`。因此，无论有没有符号，MySQL 的浮点数都会存储表示符号的部分。因此， 所谓的无符号数取值范围，其实就是有符号数取值范围大于等于零的部分。

#### 数据精度说明

> - MySQL允许使用 非标准语法 （其他数据库未必支持，因此如果涉及到数据迁移，则最好不要这么用）： FLOAT(M,D) 或 DOUBLE(M,D) 。M称为 精度 ，D称为 标度 。(M,D)中 M=整数位+小数位，D=小数位。 D<=M<=255，0<=D<=30。例如，定义为FLOAT(5,2)的一个列可以显示为-999.99-999.99。如果超过这个范围会报错。
>
> - FLOAT和DOUBLE类型在不指定(M,D)时，默认会按照实际的精度（由实际的硬件和操作系统决定）来显示。
>
> - 说明：浮点类型，也可以加 UNSIGNED ，但是不会改变数据范围，例如：FLOAT(3,2) UNSIGNED仍然只能表示0-9.99的范围。
>
> - 不管是否显式设置了精度(M,D)，这里MySQL的处理方案如下：
>   - 如果存储时，整数部分超出了范围，MySQL就会报错，不允许存这样的值
>   - 如果存储时，小数点部分若超出范围，就分以下情况：
>     - 若四舍五入后，整数部分没有超出范围，则只警告，但能成功操作并四舍五入删除多余的小数位后保存。例如在FLOAT(5,2)列内插入999.009，近似结果是999.01。
>     - 若四舍五入后，整数部分超出范围，则MySQL报错，并拒绝处理。如FLOAT(5,2)列内插入999.995和-999.995都会报错。
>
> - 从MySQL 8.0.17开始，FLOAT(M,D) 和 DOUBLE(M,D) 用法在官方文档中已经明确不推荐使用，将来可能被移除。另外，关于浮点型FLOAT和DOUBLE的UNSIGNED也不推荐使用了，将来也可能被移除。

#### 精度误差说明

> 我们设计一个表，有f 1 这个字段，插入值分别为 0. 47 , 0. 44 , 0. 19 ，我们期待的运行结果是： 0. 47 + 0. 44 + 0. 19 = 1.1。而使用sum查询：查询结果是 1.0999999999999999。虽然误差很小，但确实有误差。把数据类型改成 FLOAT，得到 1.0999999940395355。显然，误差更大了。
>
> 那么，为什么会存在这样的误差呢？问题还是出在 MySQL 对浮点类型数据的存储方式上。
>
> MySQL 用 4 个字节存储 FLOAT 类型数据，用 8 个字节来存储 DOUBLE 类型数据。无论哪个，都是采用二进制的方式来进行存储的。比如 9.625，用二进制来表达，就是 1001.101，或者表达成 1.001101×2^3。如果尾数不是 0 或 5（比如 9.624），你就无法用一个二进制数来精确表达。进而，就只好在取值允许的范围内进行四舍五入。
>
> 在编程中，如果用到浮点数，要特别注意误差问题，**因为浮点数是不准确的，所以我们要避免使用 = 来判断两个数是否相等。**同时，在一些对精确度要求较高的项目中，千万不要使用浮点数，不然会导致结果错误，甚至是造成不可挽回的损失。那么，MySQL 有没有精准的数据类型呢？当然有，这就是定点数类型： DECIMAL 。 

### 定点数类型

#### 类型介绍

> - MySQL中的定点数类型只有 DECIMAL 一种类型。
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815160135886.png" alt="image-20220815160135886" style="zoom:50%;" />
>
> - 使用 DECIMAL(M,D) 的方式表示高精度小数。其中，M被称为精度，D被称为标度。0<=M<=65， 0<=D<=30，D<M。例如，定义DECIMAL（5,2）的类型，表示该列取值范围是-999.99~999.99。
>
> - **DECIMAL(M,D)**的最大取值范围与**DOUBLE**类型一样，但是有效的数据范围是由M和D决定的。DECIMAL 的存储空间并不是固定的，由精度值M决定，总共占用的存储空间为M+2个字节。也就是说，在一些对精度要求不高的场景下，比起占用同样字节长度的定点数，浮点数表达的数值范围可以更大一些。
>
> - 定点数在MySQL内部是以` 字符串` 的形式进行存储，这就决定了它一定是精准的。
>
> - 当DECIMAL类型不指定精度和标度时，其默认为`DECIMAL(10,0)`。当数据的精度超出了定点数类型的精度范围时，则MySQL同样会进行四舍五入处理。
>
> - **浮点数** **vs** **定点数**
>
>   - 浮点数相对于定点数的优点是在长度一定的情况下，浮点类型取值范围大，但是不精准，`适用于需要取值范围大，又可以容忍微小误差的科学计算场景`（比如计算化学、分子建模、流体动力学等）
>
>   - 定点数类型取值范围相对小，但是精准，没有误差，`适合于对精度要求极高的场景` （比如涉及金额计算的场景）
>
>   - 【 强制 】小数类型为 DECIMAL，禁止使用 FLOAT 和 DOUBLE。
>
>     说明：在存储的时候，FLOAT 和 DOUBLE 都存在精度损失的问题，很可能在比较值的时候，得到不正确的结果。如果存储的数据范围超过 DECIMAL 的范围，建议将数据拆成整数和小数并分开存储。

### 位类型：BIT

> - BIT类型中存储的是二进制值，类似 010110 。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815164539833.png" alt="image-20220815164539833" style="zoom:50%;" />
>
> - BIT类型，如果没有指定(M)，`默认是1位`。这个1位，表示只能存1位的二进制值。这里(M)是表示二进制的位数，位数最小值为1，最大值为64。
>
> - 在向BIT类型的字段中插入数据时，一定要确保插入的数据在BIT类型支持的范围内。
> - 使用SELECT命令查询位字段时，可以用 BIN() 或 HEX() 函数进行读取。使用b+0查询数据时，可以直接查询出存储的十进制数据的值

### 日期与时间类型

> MySQL8.0版本支持的日期和时间类型主要有：YEAR类型、TIME类型、DATE类型、DATETIME类型和TIMESTAMP类型。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815165720790.png" alt="image-20220815165720790" style="zoom:50%;" />
>
> - YEAR 类型通常用来表示年
>
> - DATE 类型通常用来表示年、月、日
>
> - TIME 类型通常用来表示时、分、秒
>
> - DATETIME 类型通常用来表示年、月、日、时、分、秒
>
> - TIMESTAMP 类型通常用来表示带时区的年、月、日、时、分、秒
>
>   
>
>   为什么时间类型 TIME 的取值范围不是 -23:59:59～23:59:59 呢？
>
>   MySQL 设计的 TIME 类型，不光表示一天之内的时间，而且可以用来表示一个时间间隔，这个时间间隔可以超过 24 小时。

#### YEAR类型

> - YEAR类型用来表示年份，在所有的日期时间类型中所占用的存储空间最小，只需要 `1个字节` 的存储空间。
>
> - 在MySQL中，YEAR有以下几种存储格式：
>   - 以4位字符串或数字格式表示YEAR类型，其格式为YYYY，最小值为1901，最大值为2155。 
>   - 以2位字符串格式表示YEAR类型，最小值为00，最大值为99。
>     - 当取值为01到69时，表示2001到2069；
>     - 当取值为70到99时，表示1970到1999；
>     - 当取值整数的0或00添加的话，那么是0000年；
>     - 当取值是日期/字符串的'0'添加的话，是2000年。
>
> - **从MySQL5.5.27开始**，2位格式的YEAR已经不推荐使用。YEAR默认格式就是“YYYY”，没必要写成YEAR(4)， 
> - **从MySQL 8.0.19开始**，不推荐使用指定显示宽度的YEAR(4)数据类型。

#### DATE类型

> - DATE类型表示日期，没有时间部分，格式为 YYYY-MM-DD ，其中，YYYY表示年份，MM表示月份，DD表示日期。需要` 3个字节 `的存储空间。在向DATE类型的字段插入数据时，同样需要满足一定的格式条件。
>   - 以 YYYY-MM-DD 格式或者 YYYYMMDD 格式表示的字符串日期，其最小取值为1000-01-01，最大取值为9999-12-03。YYYYMMDD格式会被转化为YYYY-MM-DD格式。
>   - 以 YY-MM-DD 格式或者 YYMMDD 格式表示的字符串日期，此格式中，年份为两位数值或字符串满足YEAR类型的格式条件为：当年份取值为00到69时，会被转化为2000到2069；当年份取值为70到99时，会被转化为1970到1999。
>   - 使用 CURRENT_DATE() 或者 NOW() 函数，会插入当前系统的日期。

#### TIME类型

> TIME类型用来表示时间，不包含日期部分。在MySQL中，需要 `3个字节 `的存储空间来存储TIME类型的数据，可以使用“HH:MM:SS”格式来表示TIME类型，其中，HH表示小时，MM表示分钟，SS表示秒。
>
> 在MySQL中，向TIME类型的字段插入数据时，也可以使用几种不同的格式。 
>
> （1）可以使用带有冒号的字符串，比如' D HH:MM:SS' 、' HH:MM:SS '、' HH:MM '、' D HH:MM '、' D HH '或' SS '格式，都能被正确地插入TIME类型的字段中。其中D表示天，其最小值为0，最大值为34。如果使用带有D格式的字符串插入TIME类型的字段时，D会被转化为小时，计算格式为D*24+HH。当使用带有冒号并且不带D的字符串表示时间时，表示当天的时间，比如12:10表示12:10:00，而不是00:12:10。 
>
> （2）可以使用不带有冒号的字符串或者数字，格式为' HHMMSS '或者 HHMMSS 。如果插入一个不合法的字符串或者数字，MySQL在存储数据时，会将其自动转化为00:00:00进行存储。比如1210，MySQL会将最右边的两位解析成秒，表示00:12:10，而不是12:10:00。 （3）使用 CURRENT_TIME() 或者 NOW() ，会插入当前系统的时间

#### DATETIME类型

> - DATETIME类型在所有的日期时间类型中占用的存储空间最大，总共需要 `8 个字节`的存储空间。在格式上为DATE类型和TIME类型的组合，可以表示为 YYYY-MM-DD HH:MM:SS ，其中YYYY表示年份，MM表示月份，DD表示日期，HH表示小时，MM表示分钟，SS表示秒。
>
> - 在向DATETIME类型的字段插入数据时，同样需要满足一定的格式条件。
>   - 以 YYYY-MM-DD HH:MM:SS 格式或者 YYYYMMDDHHMMSS 格式的字符串插入DATETIME类型的字段时，最小值为1000-01-01 00:00:00，最大值为9999-12-03 23:59:59。 
>   - 以YYYYMMDDHHMMSS格式的数字插入DATETIME类型的字段时，会被转化为YYYY-MM-DD HH:MM:SS格式。
>
> - 以 YY-MM-DD HH:MM:SS 格式或者 YYMMDDHHMMSS 格式的字符串插入DATETIME类型的字段时，两位数的年份规则符合YEAR类型的规则，00到69表示2000到2069；70到99表示1970到1999。
>
> - 使用函数 CURRENT_TIMESTAMP() 和 NOW() ，可以向DATETIME类型的字段插入系统的当前日期和时间。

#### TIMESTAMP类型

> - TIMESTAMP类型也可以表示日期时间，其显示格式与DATETIME类型相同，都是 YYYY-MM-DD HH:MM:SS ，需要`4个字节`的存储空间。但是`TIMESTAMP存储的时间范围比DATETIME要小很多`，只能存储“1970-01-01 00:00:01 UTC”到“2038-01-19 03:14:07 UTC”之间的时间。其中，UTC表示世界统一时间，也叫作世界标准时间。
>
> - 存储数据的时候需要对当前时间所在的时区进行转换，查询数据的时候再将时间转换回当前的时区。因此，使用TIMESTAMP 存储的同一个时间值，在不同的时区查询时会显示不同的时间。
>
> - 向TIMESTAMP类型的字段插入数据时，当插入的数据格式满足YY-MM-DD HH:MM:SS和YYMMDDHHMMSS时，两位数值的年份同样符合YEAR类型的规则条件，只不过表示的时间范围要小很多。
> - 如果向TIMESTAMP类型的字段插入的时间超出了TIMESTAMP类型的范围，则MySQL会抛出错误信息。

> **TIMESTAMP 和 DATETIME 的区别：**
>
> - TIMESTAMP存储空间比较小，表示的日期时间范围也比较小
> - 底层存储方式不同，TIMESTAMP底层存储的是毫秒值，距离1970-1-1 0:0:0 0毫秒的毫秒值。
> - 两个日期比较大小或日期计算时，TIMESTAMP更方便、更快。
> - TIMESTAMP和时区有关。TIMESTAMP会根据用户的时区不同，显示不同的结果。而DATETIME则只能反映出插入时当地的时区，其他时区的人查看数据必然会有误差的。

#### 开发中经验

> - 用得最多的日期时间类型，就是 DATETIME 。虽然 MySQL 也支持 YEAR（年）、 TIME（时间）、DATE（日期），以及 TIMESTAMP 类型，但是在实际项目中，尽量用 DATETIME 类型。因为这个数据类型包括了完整的日期和时间信息，取值范围也最大，使用起来比较方便。毕竟，如果日期时间信息分散在好几个字段，很不容易记，而且查询的时候，SQL 语句也会更加复杂。
>
> - 此外，一般存注册时间、商品发布时间等，不建议使用DATETIME存储，而是使用 时间戳 ，因为DATETIME虽然直观，但不便于计算。

### 文本字符串类型

> MySQL中，文本字符串总体上分为 CHAR 、 VARCHAR 、 TINYTEXT 、 TEXT 、 MEDIUMTEXT 、 LONGTEXT 、 ENUM 、 SET 等类型。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815175302317.png" alt="image-20220815175302317" style="zoom:50%;" />

#### CHAR与VARCHAR类型

> CHAR和VARCHAR类型都可以存储比较短的字符串。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815190359877.png" alt="image-20220815190359877" style="zoom:50%;" />

##### CHAR类型

> - CHAR(M) 类型一般需要预先定义字符串长度。如果不指定(M)，则表示长度`默认是1个字符`。
>
> - 如果保存时，数据的实际长度比CHAR类型声明的长度小，则会在 右侧填充 空格以达到指定的长度。当MySQL检索CHAR类型的数据时，CHAR类型的字段会去除尾部的空格。
>
> - 定义CHAR类型字段时，声明的字段长度即为CHAR类型字段所占的存储空间的字节数

##### VARCHAR类型

> - VARCHAR(M) 定义时， 必须指定 长度M，否则报错。
>
> - MySQL4.0版本以下，varchar(20)：指的是20字节，如果存放UTF8汉字时，只能存6个（每个汉字3字节） ；
> - MySQL5.0版本以上，varchar(20)：指的是20字符。
> - 检索VARCHAR类型的字段数据时，会保留数据尾部的空格。
> - VARCHAR类型的字段所占用的存储空间为字符串实际长度加1个字节，用于存储字段的长度
> - 【 强制 】VARCHAR 是可变长字符串，不预先分配存储空间，长度不要超过 5000。如果存储长度大于此值，定义字段类型为 TEXT，独立出来一张表，用主键来对应，避免影响其它字段索引效率

##### CHAR 或 VARCHAR 对比

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815192033082.png" alt="image-20220815192033082" style="zoom:50%;" />

> - 对比：CHAR的效率快（因为每次读取数据不用先读取占了多少字节，固定了个数），耗费空间。
> - 使用情况
>   - 使用CHAR：存储很短的信息、固定长度的。
>   - 使用VARCHAR：十分频繁改变长度的
> - 具体存储引擎中的情况
>   - MyISAM 数据存储引擎和数据列：MyISAM数据表，最好使用固定长度(CHAR)的数据列代替可变长度(VARCHAR)的数据列。这样使得整个表静态化，从而使 数据检索更快 ，用空间换时间。
>   - MEMORY 存储引擎和数据列：MEMORY数据表目前都使用固定长度的数据行存储，因此无论使用CHAR或VARCHAR列都没有关系，两者都是作为CHAR类型处理的。
>   - InnoDB 存储引擎，建议使用VARCHAR类型。因为对于InnoDB数据表，内部的行存储格式并没有区分固定长度和可变长度列（所有数据行都使用指向数据列值的头指针），而且**主要影响性能的因素是数据行使用的存储总量**，由于char平均占用的空间多于varchar，所以除了简短并且固定长度的，其他考虑varchar。这样节省空间，对磁盘I/O和数据存储总量比较好。

#### TEXT类型

> - 在MySQL中，TEXT用来保存文本类型的字符串，总共包含 4 种类型，分别为TINYTEXT、TEXT、MEDIUMTEXT 和 LONGTEXT 类型。
>
> - 在向TEXT类型的字段保存和查询数据时，系统自动按照实际长度存储，不需要预先定义长度。这一点和VARCHAR类型相同。
>
> - 每种TEXT类型保存的数据长度和所占用的存储空间不同，如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815192859052.png" alt="image-20220815192859052" style="zoom:50%;" />
>
> - **由于实际存储的长度不确定，MySQL 不允许 TEXT 类型的字段做主键**。遇到这种情况，你只能采用CHAR(M)，或者 VARCHAR(M)。

##### 开发中经验

> TEXT文本类型，可以存比较大的文本段，搜索速度稍慢，因此如果不是特别大的内容，建议使用CHAR，VARCHAR来代替。还有TEXT类型不用加默认值，加了也没用。而且text和blob类型的数据删除后容易导致“空洞”，使得文件碎片比较多，所以频繁使用的表不建议包含TEXT类型字段，建议单独分出去，单独用一个表。

### ENUM类型

> - ENUM类型也叫作枚举类型，ENUM类型的取值范围需要在定义字段时进行指定。设置字段值时，ENUM类型只允许从成员中选取单个值，不能一次选取多个值。
>
> - 其所需要的存储空间由定义ENUM类型时指定的成员个数决定。
>
> - <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815193359638.png" alt="image-20220815193359638" style="zoom:50%;" />
>   - 当ENUM类型包含1～255个成员时，需要1个字节的存储空间；
>
>   - 当ENUM类型包含256～65535个成员时，需要2个字节的存储空间。
>
>   - ENUM类型的成员个数的上限为65535个。
>
>     ```sql
>     CREATE TABLE test_enum( season ENUM('春','夏','秋','冬','unknow') );
>     INSERT INTO test_enum VALUES('春'),('秋'); 
>     -- 忽略大小写 
>     INSERT INTO test_enum VALUES('UNKNOW'); 
>     -- 允许按照角标的方式获取指定索引位置的枚举值 
>     INSERT INTO test_enum VALUES('1'),(3); 
>     -- Data truncated for column 'season' at row 1 
>     INSERT INTO test_enum VALUES('ab'); 
>     -- 当ENUM类型的字段没有声明为NOT NULL时，插入NULL也是有效的 
>     INSERT INTO test_enum VALUES(NULL);
>     ```
>
>     

### SET类型

> - SET表示一个字符串对象，可以包含0个或多个成员，但成员个数的`上限为 64` 。设置字段值时，可以取取值范围内的 0 个或多个值。
>
> - 当SET类型包含的成员个数不同时，其所占用的存储空间也是不同的，具体如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815193601360.png" alt="image-20220815193601360" style="zoom:50%;" />
>
> - ```sql
>   CREATE TABLE test_set( s SET ('A', 'B', 'C') );
>   INSERT INTO test_set (s) VALUES ('A'), ('A,B'); 
>   -- 插入重复的SET类型成员时，MySQL会自动删除重复的成员 
>   INSERT INTO test_set (s) VALUES ('A,B,C,A'); 
>   -- 向SET类型的字段插入SET成员中不存在的值时，MySQL会抛出错误。 
>   INSERT INTO test_set (s) VALUES ('A,B,C,D'); SELECT * FROM test_set;
>   ```

### 二进制字符串类型

> MySQL中的二进制字符串类型主要存储一些二进制数据，比如可以存储图片、音频和视频等二进制数据。
>
> MySQL中支持的二进制字符串类型主要包括BINARY、VARBINARY、TINYBLOB、BLOB、MEDIUMBLOB 和LONGBLOB类型。

#### BINARY与VARBINARY类型

> - BINARY和VARBINARY类似于CHAR和VARCHAR，只是它们存储的是二进制字符串。BINARY (M)为固定长度的二进制字符串，M表示最多能存储的字节数，取值范围是0~255个字符。如果未指定(M)，表示只能存储 1个字节 。例如BINARY (8)，表示最多能存储8个字节，如果字段值不足(M)个字节，将在右边填充'\0'以补齐指定长度。
>
> - VARBINARY (M)为可变长度的二进制字符串，M表示最多能存储的字节数，总字节数不能超过行的字节长度限制65535，另外还要考虑额外字节开销，VARBINARY类型的数据除了存储数据本身外，还需要1或2个字节来存储数据的字节数。VARBINARY类型 必须指定(M) ，否则报错
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815200057811.png" alt="image-20220815200057811" style="zoom:50%;" />

#### BLOB类型

> - BLOB是一个 二进制大对象 ，可以容纳可变数量的数据。
>
> - MySQL中的BLOB类型包括TINYBLOB、BLOB、MEDIUMBLOB和LONGBLOB 4种类型，它们可容纳值的最大长度不同。可以存储一个二进制的大对象，比如 图片 、 音频 和 视频 等。
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220815200301925.png" alt="image-20220815200301925" style="zoom:50%;" />
>
> - 需要注意的是，在实际工作中，往往不会在MySQL数据库中使用BLOB类型存储大对象数据，通常会将图片、音频和视频文件存储到 服务器的磁盘上 ，并将图片、音频和视频的访问路径存储到MySQL中。

##### TEXT和BLOB的使用注意事项：

> 在使用text和blob字段类型时要注意以下几点，以便更好的发挥数据库的性能。
>
> ① BLOB和TEXT值也会引起自己的一些问题，特别是执行了大量的删除或更新操作的时候。删除这种值会在数据表中留下很大的"空洞"，以后填入这些"空洞"的记录可能长度不同。为了提高性能，建议定期使用 OPTIMIZE TABLE 功能对这类表进行碎片整理。
>
> ② 如果需要对大文本字段进行模糊查询，MySQL 提供了前缀索引。但是仍然要在不必要的时候避免检索大型的BLOB或TEXT值。例如，SELECT * 查询就不是很好的想法，除非你能够确定作为约束条件的WHERE子句只会找到所需要的数据行。否则，你可能毫无目的地在网络上传输大量的值。
>
> ③ 把BLOB或TEXT列分离到单独的表中。在某些环境中，如果把这些数据列移动到第二张数据表中，可以让你把原数据表中的数据列转换为固定长度的数据行格式，那么它就是有意义的。这会减少主表中的碎片，使你得到固定长度数据行的性能优势。它还使你在主数据表上运行 SELECT * 查询的时候不会通过网络传输大量的BLOB或TEXT值。

### JSON 类型

> - JSON（JavaScript Object Notation）是一种轻量级的数据交换格式。简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。它易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。 **JSON 可以将 JavaScript 对象中表示的一组数据转换为字符串，然后就可以在网络或者程序之间轻松地传递这个字符串，并在需要的时候将它还原为各编程语言所支持的数据格式。**
>
> - 在MySQL 5.7中，就已经支持JSON数据类型。在MySQL 8.x版本中，JSON类型提供了可以进行自动验证的JSON文档和优化的存储结构，使得在MySQL中存储和读取JSON类型的数据更加方便和高效。 创建数据表，表中包含一个JSON类型的字段 js 。

```sql
CREATE TABLE test_json( js json );
INSERT INTO test_json (js) VALUES ('{"name":"songhk", "age":18, "address":{"province":"beijing", "city":"beijing"}}');
-- 当需要检索JSON类型的字段中数据的某个具体值时，可以使用“->”和“->>”符号。
-- "->"返回结果带引号；"->>"返回不带引号
SELECT js -> '$.name' AS NAME,js -> '$.age' AS age ,js -> '$.address.province' AS province, js -> '$.address.city' AS city FROM test_json;

SELECT js ->> '$.name' AS NAME,js ->> '$.age' AS age ,js ->> '$.address.province' AS province, js ->> '$.address.city' AS city FROM test_json;

```

### 小结及选择建议

> 在定义数据类型时，如果确定是 整数 ，就用 INT ； 如果是 小数 ，一定用定点数类型DECIMAL(M,D) ； 如果是日期与时间，就用 DATETIME 。
>
> 这样做的好处是，首先确保你的系统不会因为数据类型定义出错。不过，凡事都是有两面的，可靠性好，并不意味着高效。比如，TEXT 虽然使用方便，但是效率不如 CHAR(M) 和 VARCHAR(M)。

## 约束

### 约束(constraint)概述

#### 为什么需要约束

> 数据完整性（Data Integrity）是指数据的精确性（Accuracy）和可靠性（Reliability）。它是防止数据库中存在不符合语义规定的数据和防止因错误信息的输入输出造成无效操作或错误信息而提出的。
>
> 为了保证数据的完整性，SQL规范以约束的方式对**表数据进行额外的条件限制**。从以下四个方面考虑：
>
> - 实体完整性（Entity Integrity） ：例如，同一个表中，不能存在两条完全相同无法区分的记录
>
> - 域完整性（Domain Integrity） ：例如：年龄范围0-120，性别范围“男/女” 
>
> - 引用完整性（Referential Integrity） ：例如：员工所在部门，在部门表中要能找到这个部门
>
> - 用户自定义完整性（User-defined Integrity） ：例如：用户名唯一、密码不能为空等，本部门经理的工资不得高于本部门职工的平均工资的5倍。

#### 什么是约束

> 约束是表级的强制规定。可以在**创建表时规定约束（通过** **CREATE TABLE** **语句）**，或者在**表创建之后通过 ALTER TABLE 语句规定约束**。

#### 约束的分类

> - **根据约束数据列的限制，**约束可分为：
>   - **单列约束**：每个约束只约束一列
>   - **多列约束**：每个约束可约束多列数据
>
> - **根据约束的作用范围**，约束可分为：
>   - **列级约束**：只能作用在一个列上，跟在列的定义后面
>   - **表级约束**：可以作用在多个列上，不与列一起，而是单独定义
>
> - **根据约束起的作用**，约束可分为：
>
>   - **NOT NULL** **非空约束，规定某个字段不能为空**
>   - **UNIQUE** **唯一约束**，**规定某个字段在整个表中是唯一的**
>   - **PRIMARY KEY** **主键(非空且唯一)约束**
>   - **FOREIGN KEY** **外键约束**
>   - **CHECK** **检查约束**
>   - **DEFAULT** **默认值约束**
>
> - 查看约束
>
>   ```sql
>   -- information_schema数据库名（系统库） 
>   -- table_constraints表名称（专门存储各个表的约束） 
>   SELECT * FROM information_schema.table_constraints WHERE table_name = '表名称';
>   ```

### 非空约束

#### 作用

> 限定某个字段/某列的值不允许为空

#### 关键字

> NOT NULL

#### 特点

> - 默认，所有的类型的值都可以是NULL，包括INT、FLOAT等数据类型
>
> - 非空约束只能出现在表对象的列上，只能某个列单独限定非空，不能组合非空
>
> - 一个表可以有很多列都分别限定了非空
>
> - 空字符串''不等于NULL，0也不等于NULL 

#### 添加非空约束 

```sql
CREATE TABLE student( 
  sid int, 
  sname varchar(20) not null,
  tel char(11) ,
  cardid char(18) not null 
);
-- 修改表
alter table 表名称 modify 字段名 数据类型 not null;
```

#### 删除非空约束 

```sql
alter table 表名称 modify 字段名 数据类型 NULL;
alter table 表名称 modify 字段名 数据类型; -- 去掉not null，相当于修改某个非注解字段，该字段允许为空
```

### 唯一性约束

#### 作用

> 用来限制某个字段/某列的值不能重复。

#### 关键字

> UNIQUE

#### 特点

> - 同一个表可以有多个唯一约束。
> - 唯一约束可以是某一个列的值唯一，也可以多个列组合的值唯一。
> - 唯一性约束允许列值为空。
> - 在创建唯一约束的时候，如果不给唯一约束命名，就默认和列名相同。
>
> - MySQL会给唯一约束的列上默认创建一个唯一索引。

#### 添加唯一约束

```sql
-- 创建表
CREATE TABLE USER( 
  id INT NOT NULL UNIQUE, 
  NAME VARCHAR(25), 
  PASSWORD VARCHAR(16),
	-- 使用表级约束语法(NAME和PASSWORD不能重复)
  CONSTRAINT uk_name_pwd UNIQUE(NAME,PASSWORD) 
);
-- 修改列
ALTER TABLE USER MODIFY NAME VARCHAR(20) UNIQUE;
ALTER TABLE USER ADD CONSTRAINT uk_name_pwd UNIQUE(NAME,PASSWORD);
```

#### 删除唯一约束

```sql
ALTER TABLE USER DROP INDEX uk_name_pwd;
```

> - 添加唯一性约束的列上也会自动创建唯一索引。
> - 删除唯一约束只能通过删除唯一索引的方式删除。
> - 删除时需要指定唯一索引名，唯一索引名就和唯一约束名一样。
> - 如果创建唯一约束时未指定名称，如果是单列，就默认和列名相同；如果是组合列，那么默认排在第一个的列名相同。也可以自定义唯一性约束名。
> - 注意：可以通过 show index from 表名称;查看表的索引

### PRIMARY KEY 约束

#### 作用

> 用来唯一标识表中的一行记录。

#### 关键字

> primary key

#### 特点

> - 主键约束相当于**唯一约束 + 非空约束的组合**，主键约束列不允许重复，也不允许出现空值。
>
> - 一个表最多只能有一个主键约束，建立主键约束可以在列级别创建，也可以在表级别上创建。
>
> - 主键约束对应着表中的一列或者多列（复合主键）
>
> - 如果是多列组合的复合主键约束，那么这些列都不允许为空值，并且组合的值不允许重复。
>
> - **MySQL 的主键名总是PRIMARY**，就算自己命名了主键约束名也没用。
>
> - 当创建主键约束时，系统默认会在所在的列或列组合上建立对应的**主键索引**（能够根据主键查询的，就根据主键查询，效率更高）。如果删除主键约束了，主键约束对应的索引就自动删除了。
>
> - 需要注意的一点是，不要修改主键字段的值。因为主键是数据记录的唯一标识，如果修改了主键的值，就有可能会破坏数据的完整性。

#### 添加主键约束

```sql
-- 列级约束
create table temp( 
  id int primary key, 
  name varchar(20) 
);
-- 表级约束
CREATE TABLE emp5( 
  id INT NOT NULL AUTO_INCREMENT, 
  NAME VARCHAR(20), 
  pwd VARCHAR(15), 
  CONSTRAINT emp5_id_pk PRIMARY KEY(id) 
);
-- 修改表
ALTER TABLE emp5 ADD PRIMARY KEY(NAME,pwd);
```
#### 删除主键约束

> 说明：删除主键约束，不需要指定主键名，因为一个表只有一个主键，删除主键约束后，非空还存在。

```sql
ALTER TABLE emp5 DROP PRIMARY KEY;
```

### 自增列：AUTO_INCREMENT

#### 作用

> 某个字段的值自增

#### 关键字

> auto_increment

#### 特点和要求

> - 一个表最多只能有一个自增长列
> - 当需要产生唯一标识符或顺序值时，可设置自增长
> - 自增长列约束的列必须是键列（主键列，唯一键列）
> - 自增约束的列的数据类型必须是整数类型
> - 如果自增列指定了 0 和 null，会在当前最大值的基础上自增；如果自增列手动指定了具体值，直接赋值为具体值。

##### 错误演示

```sql
-- ERROR 1075 (42000): Incorrect table definition; there can be only one auto column and it must be defined as a key 
create table employee( 
  eid int auto_increment, 
  ename varchar(20) 
);
-- ERROR 1063 (42000): Incorrect column specifier for column 'ename' 因为ename不是整数类 型
create table employee( 
  eid int primary key, 
  ename varchar(20) unique key auto_increment
);
```

#### 如何指定自增约束

```sql
create table employee(
  eid int primary key auto_increment, 
  ename varchar(20)
);

alter table employee modify 字段名 数据类型 auto_increment;
```
#### 如何删除自增约束

```sql
-- 去掉auto_increment相当于删除 
alter table employee modify eid int;
```

####  MySQL 8. 0 新特性—自增变量的持久化

> **MySQL 5.7**，对于自增主键的分配规则，是由InnoDB数据字典内部一个 `计数器` 来决定的，而该计数器只在 `内存中维护 `，并不会持久化到磁盘中。当数据库重启时，该计数器会被初始化。
>
> **MySQL 8.0**，将自增主键的计数器持久化到 重做日志 中。每次计数器发生改变，都会将其写入`重做日志`中。如果数据库重启，InnoDB会根据重做日志中的信息来初始化计数器的内存值。
>
> **在MySQL 8.0之前**，自增主键AUTO_INCREMENT的值如果大于max(primary key)+1，在MySQL重启后，会重置AUTO_INCREMENT=max(primary key)+1，这种现象在某些情况下会导致业务主键冲突或者其他难以发现的问题。

### FOREIGN KEY 约束

#### 作用

> 限定某个表的某个字段的引用完整性。

#### 关键字

> FOREIGN KEY 

#### 主表和从表/父表和子表

> 主表（父表）：被引用的表，被参考的表
>
> 从表（子表）：引用别人的表，参考别人的表
>
> 例如：员工表的员工所在部门这个字段的值要参考部门表：部门表是主表，员工表是从表。

#### 特点

> - 从表的外键列，依赖/被参考的值必须是唯一的，必须引用/参考主表的主键或唯一约束的列
> - 在创建外键约束时，如果不给外键约束命名，**默认名不是列名，而是自动产生一个外键名**（表名 + "_ibfk_1"例如student_ibfk_1;），也可以指定外键约束名。
> - 创建(CREATE)表时就指定外键约束的话，先创建主表，再创建从表
> - 删表时，先删从表（或先删除外键约束），再删除主表
> - 当主表的记录被从表参照时，主表的记录将不允许删除，如果要删除数据，需要先删除从表中依赖该记录的数据，然后才可以删除主表的数据
> - 在“从表”中指定外键约束，并且一个表可以建立多个外键约束从表的外键列与主表被参照的列名字可以不相同，但是数据类型必须一样，逻辑意义一致。如果类型不一样，创建子表时，就会出现错误“ERROR 1005 (HY000): Can't create table'database.tablename'(errno: 150)”。例如：都是表示部门编号，都是int类型。
> - **当创建外键约束时，系统默认会在所在的列上建立对应的普通索引**。但是索引名是外键的约束名。（根据外键查询效率很高）删除外键约束后，必须 手动 删除对应的索引
> - 添加了外键约束后，主表的修改和删除数据受约束；添加了外键约束后，从表的添加和修改数据受约束

#### 添加外键约束

```sql
-- 主表 
create table dept(
  did int primary key, -- 部门编号 
  dname varchar(50) -- 部门名称 
);
-- 从表 
create table emp( 
  eid int primary key, -- 员工编号 
  ename varchar(5), -- 员工姓名 
  deptid int, -- 员工所在的部门
  foreign key (deptid) references dept(did) -- 在从表中指定外键约束 
  -- emp表的deptid和和dept表的did的数据类型一致，意义都是表示部门的编号 
);

-- 修改表
ALTER TABLE emp1 ADD [CONSTRAINT emp_dept_id_fk] FOREIGN KEY(dept_id) REFERENCES dept(ddid);
```

#### 常见问题

> - 不是键列
>
> - 数据类型不一致

#### 约束等级

> - Cascade方式 ：在父表上update/delete记录时，同步update/delete掉子表的匹配记录
>
> - Set null方式 ：在父表上update/delete记录时，将子表上匹配记录的列设为null，但是要注意子表的外键列不能为not null 
>
> - No action方式 ：如果子表中有匹配的记录，则不允许对父表对应候选键进行update/delete操作
>
> - Restrict方式(默认) ：同no action， 都是立即检查外键约束
>
> - Set default方式 （在可视化工具SQLyog中可能显示空白）：父表有变更时，子表将外键列设置成一个默认的值，但Innodb不能识别
>
> 对于外键约束，最好是采用: `ON UPDATE CASCADE ON DELETE RESTRICT `的方式


#### 删除外键约束

> - 第一步先查看约束名和删除外键约束 
>
> ```sql
> SELECT * FROM information_schema.table_constraints WHERE table_name = '表名称';
> ALTER TABLE 从表名 DROP FOREIGN KEY 外键约束名; 
> ```
>
> - 第二步查看索引名和删除索引。（注意，只能手动删除） 
>
> ```sql
> SHOW INDEX FROM 表名称; -- 查看某个表的索引名 
> ALTER TABLE 从表名 DROP INDEX 索引名;
> ```
>
> - 举例
>
>   ```sql
>   SELECT * FROM information_schema.table_constraints WHERE table_name = 'emp'; 
>   alter table emp drop foreign key emp_ibfk_1; 
>   show index from emp;
>   alter table emp drop index deptid; 
>   show index from emp;
>   ```

#### 开发场景

> 在 MySQL 里，外键约束是有成本的，需要消耗系统资源。对于大并发的 SQL 操作，有可能会不适合。比如大型网站的中央数据库，可能会 因为外键约束的系统开销而变得非常慢 。所以， MySQL 允许你不使用系统自带的外键约束，在 应用层面 完成检查数据一致性的逻辑。也就是说，即使你不用外键约束，也要想办法通过应用层面的附加逻辑，来实现外键约束的功能，确保数据的一致性。
>
> 
>
> 【 强制 】不得使用外键与级联，一切外键概念必须在应用层解决。
>
> 说明：（概念解释）学生表中的 student_id 是主键，那么成绩表中的 student_id 则为外键。如果更新学生表中的 student_id，同时触发成绩表中的 student_id 更新，即为级联更新。外键与级联更新适用于 单 机低并发 ，不适合 分布式 、 高并发集群 ；级联更新是强阻塞，存在数据库 更新风暴 的风险；外键影响数据库的 插入速度 。 

### CHECK 约束

#### 作用

> - 检查某个字段的值是否符号xx要求，一般指的是值的范围
>
> - **MySQL 5.7** **不支持** ，MySQL5.7 可以使用check约束，但check约束对数据验证没有任何作用。添加数据时，没有任何错误或警
>
>   告
>
> - **MySQL 8.0**中可以使用check约束了

#### 关键字

> CHECK

#### 使用

```sql
-- 默认名为表名 + _chk_1
create table employee( 
  eid int primary key, 
  ename varchar(5), 
  age INT CHECK(age > 20),
  gender char check ( gender in ('男' or '女'))
);
```

### DEFAULT约束

#### 作用

> - 给某个字段/某列指定默认值，一旦设置默认值，在插入数据时，如果此字段没有显式赋值，则赋值为默认值。
> - 默认值约束一般不在唯一键和主键列上

#### 关键字

> DEFAULT

#### 如何给字段加默认值

```sql
create table employee( 
  eid int primary key, 
  ename varchar(20) not null, 
  gender char default '男', 
  tel char(11) not null default '' -- 默认是空字符串 
);

alter table 表名称 modify 字段名 数据类型 default 默认值;
```
#### 如何删除默认值约束

```sql
alter table employee modify gender char; #删除gender字段默认值约束，如果有非空约束，也一并删除
```

## 视图

### 常见的数据库对象

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220817112445551.png" alt="image-20220817112445551" style="zoom:40%;" />

### 视图概述

#### 为什么使用视图？

> 视图一方面可以帮我们使用表的一部分而不是所有的表，另一方面也可以针对不同的用户制定不同的查询视图。
>
> 比如，针对一个公司的销售人员，我们只想给他看部分数据，而某些特殊的数据，比如采购的价格，则不会提供给他。再比如，人员薪酬是个敏感的字段，那么只给某个级别以上的人员开放，其他人的查询视图中则不提供这个字段。

#### 视图的理解

> - 视图是一种 虚拟表 ，本身是 不具有数据 的，占用很少的内存空间，它是 SQL 中的一个重要概念。
>
> - **视图建立在已有表的基础上**, 视图赖以建立的这些表称为**基表**。
>
> - 视图的创建和删除只影响视图本身，不影响对应的基表。在数据库中，视图不会保存数据，数据真正保存在数据表中，但是当对视图中的数据进行增加、删除和修改操作时，数据表中的数据会相应地发生变化，反之亦然。
> - 向视图提供数据内容的语句为 SELECT 语句, 可以将视图理解为**存储起来的** **SELECT** **语句**
> - 视图，是向用户提供基表数据的另一种表现形式。通常情况下，小型项目的数据库可以不使用视图，但是在大型项目中，以及数据表比较复杂的情况下，视图的价值就凸显出来了，它可以帮助我们把经常查询的结果集放到虚拟表中，提升使用效率。理解和使用起来都非常方便。

### 创建视图

> - 在创建视图时，没有在视图名后面指定字段列表，则视图中字段列表默认和SELECT语句中的字段列表一致。如果SELECT语句中给字段取了别名，那么视图中的字段名和别名相同。

```sql
-- 标准语句
CREATE [OR REPLACE] [ALGORITHM = {UNDEFINED | MERGE | TEMPTABLE}] 
	VIEW 视图名称 [(字段列表)] AS 查询语句
  [WITH [CASCADED|LOCAL] CHECK OPTION]
-- 基于表创建视图
CREATE VIEW empvu80 AS 
	SELECT employee_id, last_name, salary 
	FROM employees 
	WHERE department_id = 80;

CREATE VIEW emp_year_salary (ename,year_salary) AS
	SELECT ename,salary*12*(1+IFNULL(commission_pct,0))
  FROM t_employee;
  
-- 基于视图创建视图
CREATE VIEW emp_dept_ysalary AS
	SELECT emp_dept.ename,dname,year_salary 
	FROM emp_dept 
	INNER JOIN emp_year_salary ON emp_dept.ename = emp_year_salary.ename;
```

### 查看视图

```sql
SHOW TABLES; 
DESC / DESCRIBE 视图名称; 
-- 查看视图信息（显示数据表的存储引擎、版本、数据行数和数据大小等） 
SHOW TABLE STATUS LIKE '视图名称'; 
SHOW CREATE VIEW 视图名称;
```

### 更新视图的数据

> 虽然可以更新视图数据，但总的来说，视图作为 虚拟表 ，主要用于 方便查询 ，不建议更新视图的数据。**对视图数据的更改，都是通过对实际数据表里数据的操作来完成的。**

#### 一般情况

> MySQL支持使用INSERT、UPDATE和DELETE语句对视图中的数据进行插入、更新和删除操作。当视图中的数据发生变化时，数据表中的数据也会发生变化，反之亦然。

#### 不可更新的视图

> 要使视图可更新，视图中的行和底层基本表中的行之间必须存在 一对一 的关系。另外当视图定义出现如下情况时，视图不支持更新操作：
>
> - 在定义视图的时候指定了“ALGORITHM = TEMPTABLE”，视图将不支持INSERT和DELETE操作；
>
> - 视图中不包含基表中所有被定义为非空又未指定默认值的列，视图将不支持INSERT操作；
>
> - 在定义视图的SELECT语句中使用了 JOIN联合查询 ，视图将不支持INSERT和DELETE操作；
>
> - 在定义视图的SELECT语句后的字段列表中使用了 数学表达式 或 子查询 ，视图将不支持INSERT，也不支持UPDATE使用了数学表达式、子查询的字段值；
>
> - 在定义视图的SELECT语句后的字段列表中使用 DISTINCT 、 聚合函数 、 GROUP BY 、 HAVING 、 UNION 等，视图将不支持INSERT、UPDATE、DELETE；
>
> - 在定义视图的SELECT语句中包含了子查询，而子查询中引用了FROM后面的表，视图将不支持 INSERT、UPDATE、DELETE；
>
> - 视图定义基于一个 不可更新视图 ；
> - 常量视图。

### 修改、删除视图

#### 修改视图

````sql
-- 使用CREATE OR REPLACE VIEW 子句修改视图,CREATE VIEW 子句中各列的别名应和子查询中各列相对应
CREATE OR REPLACE VIEW empvu80 (id_number, name, sal, department_id) AS
	SELECT employee_id, first_name || ' ' || last_name, salary, department_id 
	FROM employees WHERE department_id = 80; 
	
-- ALTER VIEW
ALTER VIEW 视图名称 AS 查询语句
````

#### 删除视图

> - 删除视图只是删除视图的定义，并不会删除基表的数据。
> - 基于视图a、b创建了新的视图c，如果将视图a或者视图b删除，会导致视图c的查询失败。这样的视图c需要手动删除或修改，否则影响使用。 

```sql
DROP VIEW IF EXISTS 视图名称1,视图名称2,视图名称3,...; 
```

### 总结

#### 视图优点

> **1.操作简单**
>
> 将经常使用的查询操作定义为视图，可以使开发人员不需要关心视图对应的数据表的结构、表与表之间的关联关系，也不需要关心数据表之间的业务逻辑和查询条件，而只需要简单地操作视图即可，极大简化了开发人员对数据库的操作。
>
> **2.** **减少数据冗余**
>
> 视图跟实际数据表不一样，它存储的是查询语句。所以，在使用的时候，我们要通过定义视图的查询语句来获取结果集。而视图本身不存储数据，不占用数据存储的资源，减少了数据冗余。
>
> **3.** **数据安全**
>
> MySQL将用户对数据的 访问限制 在某些数据的结果集上，而这些数据的结果集可以使用视图来实现。用户不必直接查询或操作数据表。这也可以理解为视图具有 隔离性 。视图相当于在用户和实际的数据表之间加了一层虚拟表。同时，MySQL可以根据权限将用户对数据的访问限制在某些视图上，**用户不需要查询数据表，可以直接通过视图获取数据表中的信息**。这在一定程度上保障了数据表中数据的安全性。
>
> **4.** **适应灵活多变的需求**
>
>  当业务系统的需求发生变化后，如果需要改动数据表的结构，则工作量相对较大，可以使用视图来减少改动的工作量。这种方式在实际工作中使用得比较多。
>
> **5.** **能够分解复杂的查询逻辑** 
>
> 数据库中如果存在复杂的查询逻辑，则可以将问题进行分解，创建多个视图获取数据，再将创建的多个视图结合起来，完成复杂的查询逻辑。

#### 视图不足

> **如果实际数据表的结构变更了，我们就需要及时对相关的视图进行相应的维护**。特别是嵌套的视图（就是在视图的基础上创建视图），维护会变得比较复杂。

## 存储过程与函数

> MySQL从 5. 0 版本开始支持存储过程和函数。存储过程和函数能够将复杂的SQL逻辑封装在一起，应用程序无须关注存储过程和函数内部复杂的SQL逻辑，而只需要简单地调用存储过程和函数即可。

### 存储过程概述

#### 理解

> **含义** ：存储过程的英文是 Stored Procedure。它的思想很简单，就是一组经过预先编译的 SQL 语句的封装。
>
> 执行过程：存储过程预先存储在 MySQL 服务器上，需要执行的时候，客户端只需要向服务器端发出调用存储过程的命令，服务器端就可以把预先存储好的这一系列 SQL 语句全部执行。
> 
>**好处** ：
> 
>1 、简化操作，提高了sql语句的重用性，减少了开发程序员的压力 
> 2 、减少操作过程中的失误，提高效率
> 3 、减少网络传输量（客户端不需要把所有的 SQL 语句通过网络发给服务器） 
> 4 、减少了 SQL 语句暴露在网上的风险，也提高 了数据查询的安全性
> 
>**和视图、函数的对比** ：
> 
>它和视图有着同样的优点，清晰、安全，还可以减少网络传输量。不过它和视图不同，视图是虚拟表，通常不对底层数据表直接操作，而存储过程是程序化的 SQL，可以直接操作底层数据表，相比于面向集合的操作方式，能够实现一些更复杂的数据处理。
> 
> 一旦存储过程被创建出来，使用它就像使用函数一样简单，我们直接通过调用存储过程名即可。相较于函数，存储过程没有返回值

#### 分类

> 存储过程的参数类型可以是IN、OUT和INOUT。根据这点分类如下：
>
> 1、没有参数（无参数无返回）
>
> 2、仅仅带 IN 类型（有参数无返回）
>
> 3、仅仅带 OUT 类型（无参数有返回） 
>
> 4、既带 IN 又带 OUT（有参数有返回） 
>
> 5、带 INOUT（有参数有返回）
>
> 注意：IN、OUT、INOUT 都可以在一个存储过程中带多个。

### 创建存储过程


#### 语法分析

```sql
CREATE PROCEDURE 存储过程名(IN|OUT|INOUT 参数名 参数类型,...) 
[characteristics ...]
BEGIN
	存储过程体 
END
```

> 说明：
>
> - IN ：当前参数为输入参数，也就是表示入参；存储过程只是读取这个参数的值。如果没有定义参数种类， 默认就是 IN ，表示输入参数。
>
> - OUT ：当前参数为输出参数，也就是表示出参；执行完成之后，调用这个存储过程的客户端或者应用程序就可以读取这个参数返回的值了。
>
> - INOUT ：当前参数既可以为输入参数，也可以为输出参数。
>
> - 参数名不要用表的列名，形参类型可以是 MySQL数据库中的任意类型。
>
> - characteristics 表示创建存储过程时指定的对存储过程的约束条件，其取值信息如下：
>
>   ```sql
>   LANGUAGE SQL 
>   | [NOT] DETERMINISTIC 
>   | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA } 
>   | SQL SECURITY { DEFINER | INVOKER } 
>   | COMMENT 'string'
>   ```
>
>   - LANGUAGE SQL ：说明存储过程执行体是由SQL语句组成的，当前系统支持的语言为SQL。 
>   - [NOT] DETERMINISTIC ：指明存储过程执行的结果是否确定。DETERMINISTIC表示结果是确定的。每次执行存储过程时，相同的输入会得到相同的输出。NOT DETERMINISTIC表示结果是不确定的，相同的输入可能得到不同的输出。如果没有指定任意一个值，默认为NOT DETERMINISTIC。 
>   - { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA } ：指明子程序使用SQL语句的限制。CONTAINS SQL表示当前存储过程的子程序包含SQL语句，但是并不包含读写数据的SQL语句；NO SQL表示当前存储过程的子程序中不包含任何SQL语句；READS SQL DATA表示当前存储过程的子程序中包含读数据的SQL语句；MODIFIES SQL DATA表示当前存储过程的子程序中包含写数据的SQL语句。默认情况下，系统会指定为CONTAINS SQL。 
>   - SQL SECURITY { DEFINER | INVOKER } ：执行当前存储过程的权限，即指明哪些用户能够执行当前存储过程。DEFINER 表示只有当前存储过程的创建者或者定义者才能执行当前存储过程；INVOKER 表示拥有当前存储过程的访问权限的用户能够执行当前存储过程。如果没有设置相关的值，则MySQL默认指定值为DEFINER。 
>   - COMMENT 'string'：注释信息，可以用来描述存储过程。
>
> - 存储过程体中可以有多条 SQL 语句，如果仅仅一条SQL 语句，则可以省略 BEGIN 和 END。编写存储过程并不是一件简单的事情，可能存储过程中需要复杂的 SQL 语句。
>   - BEGIN…END：BEGIN…END 中间包含了多个语句，每个语句都以（;）号为结束符。 
>   - DECLARE：DECLARE 用来声明变量，使用的位置在于 BEGIN…END 语句中间，而且需要在其他语句使用之前进 行变量的声明。 
>   - SET：赋值语句，用于对变量进行赋值。 
>   - SELECT… INTO：把从数据表中查询的结果存放到变量中，也就是为变量赋值。
> - 需要设置新的结束标记，因为MySQL默认的语句结束符号为分号‘;’。为了避免与存储过程中SQL语句结束符相冲突，需要使用DELIMITER改变存储过程的结束符。
>   
>   - DELIMITER 新的结束标记

#### 代码举例

```sql
DELIMITER //
CREATE PROCEDURE show_someone_salary(IN empname VARCHAR(20),OUT empsalary DOUBLE) 
	LANGUAGE SQL 
	NOT DETERMINISTIC 
	CONTAINS SQL 
	SQL SECURITY DEFINER 
	COMMENT '查看薪资' 
	BEGIN
		SELECT salary INTO empsalary FROM emps WHERE ename = empname; 
  END //	
DELIMITER ;
```

```sql
-- 求和 1 ～ n
DELIMITER $
CREATE PROCEDURE `add_num`(IN n INT)
BEGIN
    DECLARE i INT;
    DECLARE sum INT;
    SET i = 1;
    SET sum = 0;
    WHILE i <= n
        DO
            SET sum = sum + i;
            SET i = i + 1;
        END WHILE;
    SELECT sum;
END $
DELIMITER ;
```

### 调用存储过程

#### 调用格式

> 存储过程有多种调用方法。存储过程必须使用CALL语句调用，并且存储过程和数据库相关，如果要执行其他数据库中的存储过程，需要指定数据库名称，例如CALL dbname.procname。
>
> - 调用in模式的参数：
>
>   ```sql
>   CALL sp1('值'); 
>   ```
>
> - 调用out模式的参数：
>
>   ```sql
>   SET @name; 
>   CALL sp1(@name); 
>   SELECT @name;
>   ```
>
> - 调用inout模式的参数：
>
>   ```sql
>   SET @name=值; 
>   CALL sp1(@name); 
>   SELECT @name;
>   ```

### 存储函数的使用

#### 语法分析

##### 语法格式：

```sql
CREATE FUNCTION 函数名(参数名 参数类型,...) 
RETURNS 返回值类型
[characteristics ...] 
BEGIN 
	函数体 -- 函数体中肯定有 RETURN 语句 
END

-- 从1加到n
DELIMITER $
CREATE function `add_num1`(n INT)
returns int
deterministic
BEGIN
    DECLARE i INT;
    DECLARE sum INT;
    SET i = 1;
    SET sum = 0;
    WHILE i <= n
        DO
            SET sum = sum + i;
            SET i = i + 1;
        END WHILE;
    return sum;
END $
DELIMITER ;
```

> 1、参数列表：指定参数为IN、OUT或INOUT只对PROCEDURE是合法的，FUNCTION中总是默认为IN参数。
>
> 2、RETURNS type 语句表示函数返回数据的类型；RETURNS子句只能对FUNCTION做指定，对函数而言这是 强制 的。它用来指定函数的返回类型，而且函数体必须包含一个 RETURN value 语句。
>
> 3、characteristic 创建函数时指定的对函数的约束。取值与创建存储过程时相同，这里不再赘述。
> 	(如果报错： you might want to use the less safe log_bin_trust_function_creators variable ”，
> 	意味着开启了bin-log, 加上必要的函数特性“[NOT] DETERMINISTIC”和“{CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA}”；
> 	或者 SET GLOBAL log_bin_trust_function_creators = 1; )
>
> 4、函数体也可以用BEGIN…END来表示SQL代码的开始和结束。如果函数体只有一条语句，也可以省略BEGIN…END。

#### 调用存储函数

> SELECT 函数名(实参列表)

#### 对比存储函数和存储过程

> **存储函数可以放在查询语句中使用，存储过程不行**。反之，存储过程的功能更加强大，包括能够执行对表的操作（比如创建表，删除表等）和事务操作，这些功能是存储函数不具备的

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220818094320321.png" alt="image-20220818094320321" style="zoom:50%;" />

### 存储过程和函数的查看、修改、删除

#### 查看

> - 使用SHOW CREATE语句查看存储过程和函数的创建信息
>
>   ```sql
>   SHOW CREATE {PROCEDURE | FUNCTION} 存储过程名或函数名 
>   SHOW CREATE FUNCTION test_db.CountProc;
>   ```
>
> -  使用SHOW STATUS语句查看存储过程和函数的状态信息，返回子程序的特征，如数据库、名字、类型、创建者及创建和修改日期。[LIKE 'pattern']：匹配存储过程或函数的名称，可以省略。当省略不写时，会列出MySQL数据库中存在的所有存储过程或函数的信息。
>
>   ```sql
>   SHOW {PROCEDURE | FUNCTION} STATUS [LIKE 'pattern'];
>   ```
>
> -  从information_schema.Routines表中查看存储过程和函数的信息
>
>   ```sql
>   SELECT * FROM information_schema.Routines 
>   WHERE ROUTINE_NAME='存储过程或函数的名' [AND ROUTINE_TYPE = {'PROCEDURE|FUNCTION'}]; 
>   
>   SELECT * FROM information_schema.Routines 
>   WHERE ROUTINE_NAME='count_by_id' AND ROUTINE_TYPE = 'FUNCTION';
>   ```

#### 修改

> 修改存储过程或函数，不影响存储过程或函数功能，只是修改相关特性。使用ALTER语句实现。
>
> ```sql
> ALTER {PROCEDURE | FUNCTION} 存储过程或函数的名 [characteristic ...]
> 
> ALTER PROCEDURE CountProc 
> 	MODIFIES SQL DATA 
> 	SQL SECURITY INVOKER ;
> ```

#### 删除

> IF EXISTS：如果程序或函数不存储，它可以防止发生错误，产生一个用SHOW WARNINGS查看的警告。

```sql
DROP {PROCEDURE | FUNCTION} [IF EXISTS] 存储过程或函数的名
DROP PROCEDURE CountProc; 
DROP FUNCTION CountProc;
```

### 关于存储过程使用的争议

#### 优点

> **1**、存储过程可以一次编译多次使用。存储过程只在创建时进行编译，之后的使用都不需要重新编译，这就提升了 SQL 的执行效率。
>
> **2**、可以减少开发工作量。将代码 封装 成模块，实际上是编程的核心思想之一，这样可以把复杂的问题拆解成不同的模块，然后模块之间可以 重复使用 ，在减少开发工作量的同时，还能保证代码的结构清晰。
>
> **3**、存储过程的安全性强。我们在设定存储过程的时候可以 设置对用户的使用权限 ，这样就和视图一样具有较强的安全性。
>
> **4**、可以减少网络传输量。因为代码封装到存储过程中，每次使用只需要调用存储过程即可，这样就减少了网络传输量。
>
> **5**、良好的封装性。在进行相对复杂的数据库操作时，原本需要使用一条一条的 SQL 语句，可能要连接多次数据库才能完成的操作，现在变成了一次存储过程，只需要 连接一次即可

#### 缺点

>  **1 、可移植性差。** 存储过程不能跨数据库移植，比如在 MySQL、Oracle 和 SQL Server 里编写的存储过程，在换成其他数据库时都需要重新编写。
>
> **2 、调试困难。** 只有少数 DBMS 支持存储过程的调试。对于复杂的存储过程来说，开发和维护都不容易。虽然也有一些第三方工具可以对存储过程进行调试，但要收费。
>
> **3 、存储过程的版本管理很困难。** 比如数据表索引发生变化了，可能会导致存储过程失效。我们在开发软件的时候往往需要进行版本管理，但是存储过程本身没有版本控制，版本迭代更新的时候很麻烦。
>
> **4 、它不适合高并发的场景。** 高并发的场景需要减少数据库的压力，有时数据库会采用分库分表的方式，而且对可扩展性要求很高，在这种情况下，存储过程会变得难以维护，增加数据库的压力，显然就不适用了。

## 变量、流程控制与游标

### 变量

#### 系统变量

##### 系统变量分类

> - 变量由系统定义，不是用户定义，属于 `服务器 `层面。启动MySQL服务，生成MySQL服务实例期间，MySQL将为MySQL服务器内存中的系统变量赋值，这些系统变量定义了当前MySQL服务实例的属性、特征。这些系统变量的值要么是 编译MySQL时参数 的默认值，要么是 配置文件 （例如my.ini等）中的参数值。大家可以通过网址 https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html 查看MySQL文档的系统变量。
>
> - 系统变量分为`全局系统变量（需要添加 global 关键字）以及会话系统变量（需要添加 session 关键字）`，**默认会话级别。**静态变量（在 MySQL 服务实例运行期间它们的值不能使用 set 动态修改）属于特殊的全局系统变量。
>
> - 全局系统变量针对于所有会话（连接）有效，会话系统变量仅针对于当前会话（连接）有效。会话期间，当前会话对某个会话系统变量值的修改，不会影响其他会话同一个会话系统变量的值。会话1对某个全局系统变量值的修改会导致会话2中同一个全局系统变量值的修改。

##### 查看系统变量

> - **查看所有或部分系统变量**
>
>   ```sql
>   -- 查看所有全局变量 
>   SHOW GLOBAL VARIABLES; 
>   -- 查看所有会话变量 
>   SHOW SESSION VARIABLES; 
>   SHOW VARIABLES;
>   ```
>
> - **查看指定系统变量**
>
>   ```sql
>   -- 查看指定的系统变量的值 
>   SELECT @@global.变量名; 
>   -- 查看指定的会话变量的值 
>   SELECT @@session.变量名;
>   SELECT @@变量名;
>   ```
>
> - **修改系统变量的值**
>
>   - 修改MySQL 配置文件 ，继而修改MySQL系统变量的值（该方法需要重启MySQL服务）
>   - 在MySQL服务运行期间，使用“set”命令重新设置系统变量的值
>
>   ```sql
>   #为某个系统变量赋值 
>   #方式1： SET @@global.变量名=变量值; 
>   #方式2： SET GLOBAL 变量名=变量值; 
>   
>   #为某个会话变量赋值 
>   #方式1： SET @@session.变量名=变量值; 
>   #方式2： SET SESSION 变量名=变量值;
>   ```

#### 用户变量

##### 用户变量分类

> - 用户变量是用户自己定义的，作为 MySQL 编码规范，MySQL 中的用户变量以 一个“@” 开头。根据作用范围不同，又分为 会话用户变量 和 局部变量 。
>
> - 会话用户变量：作用域和会话变量一样，只对 当前连接 会话有效。
>
> - 局部变量：只在 BEGIN 和 END 语句块中有效。局部变量只能在 存储过程和函数 中使用。

##### 会话用户变量

> 变量的定义

```sql
-- 方式1：“=”或“:=” 
SET @用户变量 = 值; 
SET @用户变量 := 值; 

-- 方式2：“:=” 或 INTO关键字 
SELECT @用户变量 := 表达式 [FROM 等子句]; 
SELECT 表达式 INTO @用户变量 [FROM 等子句];
```

> 查看用户变量的值 （查看、比较、运算等）

```sql
SELECT @用户变量
```

##### 局部变量

> - 定义：可以使用 DECLARE 语句定义一个局部变量
>
> - 作用域：仅仅在定义它的 BEGIN ... END 中有效
>
> - 位置：只能放在 BEGIN ... END 中，而且只能放在第一句
>
>   ```sqlite
>   BEGIN 
>   	-- 声明局部变量,没有DEFAULT子句，初始值为NULL
>   	DECLARE 变量名1 变量数据类型 [DEFAULT 变量默认值]; 
>   	DECLARE 变量名2,变量名3,... 变量数据类型 [DEFAULT 变量默认值]; 
>   	-- 为局部变量赋值 
>   	SET 变量名1 = 值; 
>   	SELECT 值 INTO 变量名2 [FROM 子句]; 
>   	-- 查看局部变量的值 
>   	SELECT 变量1,变量2,变量3;
>   END
>   ```

##### 会话用户变量 VS 局部变量

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220822095720911.png" alt="image-20220822095720911" style="zoom:50%;" />

### 定义条件与处理程序

> `定义条件` 是事先定义程序执行过程中可能遇到的问题， `处理程序` 定义了在遇到问题时应当采取的处理方式，并且保证存储过程或函数在遇到警告或错误时能继续执行。这样可以增强存储程序处理问题的能力，避免程序异常停止运行。

#### 定义条件

> - 定义条件就是给MySQL中的错误码命名，这有助于存储的程序代码更清晰。它将一个 错误名字 和 指定的 错误条件 关联起来。这个名字可以随后被用在定义处理程序的 DECLARE HANDLER 语句中。
>
> - 定义条件使用DECLARE语句，语法格式如下：
>
>   ```sql
>   DECLARE 错误名称 CONDITION FOR 错误码（或错误条件）
>   ```
>
> - 错误码的说明：MySQL_error_code 和 sqlstate_value 都可以表示MySQL的错误。
>
>   - MySQL_error_code是数值类型错误代码。
>   - sqlstate_value是长度为5的字符串类型错误代码。
>
>   例如，在ERROR 1048 (23000)中，1048是MySQL_error_code，'23000'是sqlstate_value。
>
>   ```sql
>   -- 使用MySQL_error_code 
>   DECLARE Field_Not_Be_NULL CONDITION FOR 1048; 
>   -- 使用sqlstate_value 
>   DECLARE Field_Not_Be_NULL CONDITION FOR SQLSTATE '23000';
>   ```

#### 定义处理程序

> - 可以为SQL执行过程中发生的某种类型的错误定义特殊的处理程序。定义处理程序时，使用DECLARE语句的语法如下：
>
>   ```sql
>   DECLARE 处理方式 HANDLER FOR 错误类型 处理语句
>   ```
>
> - **处理方式**：处理方式有3个取值：CONTINUE、EXIT、UNDO。 
>
>   - CONTINUE ：表示遇到错误不处理，继续执行。
>   - EXIT ：表示遇到错误马上退出。
>   - UNDO ：表示遇到错误后撤回之前的操作。MySQL中暂时不支持这样的操作。
>
> - **错误类型**（即条件）可以有如下取值：
>   - SQLSTATE '字符串错误码' ：表示长度为5的sqlstate_value类型的错误代码； 
>   - MySQL_error_code ：匹配数值类型错误代码；
>   - 错误名称 ：表示DECLARE ... CONDITION定义的错误条件名称。
>   - SQLWARNING ：匹配所有以01开头的SQLSTATE错误代码；
>   - NOT FOUND ：匹配所有以02开头的SQLSTATE错误代码；
>   - SQLEXCEPTION ：匹配所有没有被SQLWARNING或NOT FOUND捕获的SQLSTATE错误代码；
>
> - **处理语句**：如果出现上述条件之一，则采用对应的处理方式，并执行指定的处理语句。语句可以是像“ SET 变量 = 值 ”这样的简单语句，也可以是使用 BEGIN ... END 编写的复合语句。
>
> - 定义处理程序的几种方式，代码如下：
>
>   ```sql
>   -- 方法1：捕获sqlstate_value 
>   DECLARE CONTINUE HANDLER FOR SQLSTATE '42S02' SET @info = 'NO_SUCH_TABLE'; 
>   -- 方法2：捕获mysql_error_value 
>   DECLARE CONTINUE HANDLER FOR 1146 SET @info = 'NO_SUCH_TABLE'; 
>   -- 方法3：先定义条件，再调用 
>   DECLARE no_such_table CONDITION FOR 1146; 
>   DECLARE CONTINUE HANDLER FOR NO_SUCH_TABLE SET @info = 'NO_SUCH_TABLE'; 
>   -- 方法4：使用SQLWARNING 
>   DECLARE EXIT HANDLER FOR SQLWARNING SET @info = 'ERROR'; 
>   -- 方法5：使用NOT FOUND 
>   DECLARE EXIT HANDLER FOR NOT FOUND SET @info = 'NO_SUCH_TABLE'; 
>   -- 方法6：使用SQLEXCEPTION 
>   DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @info = 'ERROR';
>   ```


### 流程控制

> 针对于MySQL 的流程控制语句主要有 3 类。注意：只能用于存储程序。
>
> - 条件判断语句 ：IF 语句和 CASE 语句
> - 循环语句 ：LOOP、WHILE 和 REPEAT 语句
> - 跳转语句 ：ITERATE 和 LEAVE 语句

#### 分支结构之 IF

> - IF 语句的语法结构是：
>
> ```sql
> IF 表达式1 THEN 操作1 
> [ELSEIF 表达式2 THEN 操作2]…… 
> [ELSE 操作N] 
> END IF
> ```
>
> - 特点：① 不同的表达式对应不同的操作 ② 使用在begin end中
>
> - 举例
>
>   ```sql
>   IF val IS NULL THEN SELECT 'val is null'; 
>   ELSE SELECT 'val is not null'; 
>   END IF;
>   ```

#### 分支结构之 CASE

> 情况一：类似于switch 

```sql
CASE 表达式 
	WHEN 值1 THEN 结果1或语句1(如果是语句，需要加分号) 
	WHEN 值2 THEN 结果2或语句2(如果是语句，需要加分号) 
	... 
	ELSE 结果n或语句n(如果是语句，需要加分号) 
END [case]（如果是放在begin end中需要加上case，如果放在select后面不需要）

CASE val 
	WHEN 1 THEN SELECT 'val is 1'; 
	WHEN 2 THEN SELECT 'val is 2'; 
	ELSE SELECT 'val is not 1 or 2'; 
END CASE;
```

> 情况二：类似于多重if

```sql
CASE 
	WHEN 条件1 THEN 结果1或语句1(如果是语句，需要加分号)
  WHEN 条件2 THEN 结果2或语句2(如果是语句，需要加分号) 
  ... 
  ELSE 结果n或语句n(如果是语句，需要加分号) 
END [case]（如果是放在begin end中需要加上case，如果放在select后面不需要）

CASE 
	WHEN val IS NULL THEN SELECT 'val is null'; 
	WHEN val < 0 THEN SELECT 'val is less than 0'; 
	WHEN val > 0 THEN SELECT 'val is greater than 0'; 
	ELSE SELECT 'val is 0'; 
END CASE;
```

#### 循环结构之LOOP

> LOOP循环语句用来重复执行某些语句。LOOP内的语句一直重复执行直到循环被退出（使用LEAVE子句），跳出循环过程。
>
> LOOP语句的基本格式如下：
>
> ```sql
> [loop_label:] LOOP 
> 	循环执行的语句 
> END LOOP [loop_label]
> ```
>
> 示例：使用LOOP语句进行循环操作，id值小于10时将重复执行循环过程。
>
> ```sql
> DECLARE id INT DEFAULT 0; 
> add_loop:LOOP 
> 	SET id = id +1; 
> 	IF id >= 10 THEN LEAVE add_loop; 
> 	END IF; 
> END LOOP add_loop;
> ```

#### 循环结构之WHILE

> WHILE语句创建一个带条件判断的循环过程。WHILE在执行语句执行时，先对指定的表达式进行判断，如果为真，就执行循环内的语句，否则退出循环。
>
> WHILE语句的基本格式如下：
>
> ```sql
> [while_label:] WHILE 循环条件 DO 
> 	循环体 
> END WHILE [while_label];
> ```
>
> 示例：
>
> ```sql
> add_id : while @id10 < 10 do
>     set @id10 = @id10 + 1;
> end while add_id;
> 
> while @id10 < 10 do
>     set @id10 = @id10 + 1;
> end while;
> ```

#### 循环结构之REPEAT

> REPEAT语句创建一个带条件判断的循环过程。与WHILE循环不同的是，REPEAT 循环首先会执行一次循环，然后在 UNTIL 中进行表达式的判断，如果满足条件就退出，即 END REPEAT；如果条件不满足，则会就继续执行循环，直到满足退出条件为止。
>
> REPEAT语句的基本格式如下
>
> ```sql
> [repeat_label:] REPEAT 
> 	循环体的语句 
> UNTIL 结束循环的条件表达式 
> END REPEAT [repeat_label]
> ```
>
> repeat_label为REPEAT语句的标注名称，该参数可以省略；REPEAT语句内的语句或语句群被重复，直至expr_condition为真。
>
> 举例：
>
> ```sql
> REPEAT 
> 	SET i = i + 1; 
> UNTIL i >= 10 
> END REPEAT;
> ```

#### 对比三种循环结构

> - 这三种循环都可以省略名称，但如果循环中添加了循环控制语句（LEAVE或ITERATE）则必须添加名称。 
> - LOOP：一般用于实现简单的"死"循环
> - WHILE：先判断后执行 
> - REPEAT：先执行后判断，无条件至少执行一次

#### 跳转语句之LEAVE语句

> LEAVE语句：可以用在循环语句内，或者以 BEGIN 和 END 包裹起来的程序体内，表示跳出循环或者跳出程序体的操作。如果你有面向过程的编程语言的使用经验，你可以把 LEAVE 理解为` break`。
>
> ```sql
> DECLARE id INT DEFAULT 0; 
> add_loop:LOOP 
> 	SET id = id +1; 
> 	IF id >= 10 THEN LEAVE add_loop; 
> 	END IF; 
> END LOOP add_loop;
> ```
>
> 其中，label参数表示循环的标志。LEAVE和BEGIN ... END或循环一起被使用。

#### 跳转语句之ITERATE语句

> ITERATE语句：只能用在循环语句（LOOP、REPEAT和WHILE语句）内，表示重新开始循环，将执行顺序转到语句段开头处。如果你有面向过程的编程语言的使用经验，你可以把 ITERATE 理解为 `continue`，意思为“再次循环”。
>
> 格式：ITERATE label 
>
> label参数表示循环的标志。ITERATE语句必须跟在循环标志前面。

### 游标

#### 什么是游标（或光标）

> 在 SQL 中，游标是一种临时的数据库对象，可以指向存储在数据库表中的数据行指针。这里游标 充当了指针的作用 ，我们可以通过操作游标来对数据行进行操作。MySQL中游标可以在存储过程和函数中使用。

#### 使用游标步骤

> - 声明游标
>
>   在MySQL中，使用DECLARE关键字来声明游标，其语法的基本形式如下：
>
>   ```sql
>   DECLARE cursor_name CURSOR FOR select_statement;
>   ```
>
>   这个语法适用于 MySQL，SQL Server，DB2 和 MariaDB。如果是用 Oracle 或者 PostgreSQL，需要写成：
>
>   ```sql
>   DECLARE cursor_name CURSOR IS select_statement;
>   ```
>
>   举例：
>
>   ```sql
>   DECLARE cur_emp CURSOR FOR SELECT employee_id,salary FROM employees; 
>   DECLARE cursor_fruit CURSOR FOR SELECT f_name, f_price FROM fruits ;
>   ```
>
> - 打开游标
>
>   当我们定义好游标之后，如果想要使用游标，必须先打开游标。打开游标的时候 SELECT 语句的查询结果集就会送到游标工作区，为后面游标的 逐条读取 结果集中的记录做准备。 
>
>   ```sql
>   OPEN cursor_name;
>   OPEN cur_emp ;
>   ```
>
> - 使用游标（从游标中取得数据）
>
>   这句的作用是使用 cursor_name 这个游标来读取当前行，并且将数据保存到 var_name 这个变量中，游标指针指到下一行。如果游标读取的数据行有多个列名，则在 INTO 关键字后面赋值给多个变量名即可。注意：var_name必须在声明游标之前就定义好。
>
>   ```sql
>   FETCH cursor_name INTO var_name [, var_name] ... 
>   FETCH cur_emp INTO emp_id, emp_sal ;
>   ```
>
> - **关闭游标**
>
>   有 OPEN 就会有 CLOSE，也就是打开和关闭游标。当我们使用完游标后需要关闭掉该游标。因为游标会占用系统资源 ，如果不及时关闭，**游标会一直保持到存储过程结束**，影响系统运行的效率。而关闭游标的操作，会释放游标占用的系统资源。关闭游标之后，我们就不能再检索查询结果中的数据行，如果需要检索只能再次打开游标。
>
>   ```sql
>   CLOSE cursor_name
>   ```

#### 举例

> 创建存储过程“get_count_by_limit_total_salary()”，声明IN参数 limit_total_salary，DOUBLE类型；声明OUT参数total_count，INT类型。函数的功能可以实现累加薪资最高的几个员工的薪资值，直到薪资总和达到limit_total_salary参数的值，返回累加的人数给total_count。

```sql
DELIMITER // 
CREATE PROCEDURE get_count_by_limit_total_salary(IN limit_total_salary DOUBLE,OUT total_count INT) 
BEGIN
	DECLARE sum_salary DOUBLE DEFAULT 0; -- 记录累加的总工资 
	DECLARE cursor_salary DOUBLE DEFAULT 0; -- 记录某一个工资值 
	DECLARE emp_count INT DEFAULT 0; -- 记录循环个数 
	-- 定义游标 
	DECLARE emp_cursor CURSOR FOR SELECT salary FROM employees ORDER BY salary DESC; 
	-- 打开游标 
	OPEN emp_cursor; 
	REPEAT
		-- 使用游标（从游标中获取数据） 
		FETCH emp_cursor INTO cursor_salary; 
		SET sum_salary = sum_salary + cursor_salary; 
		SET emp_count = emp_count + 1; 
	UNTIL sum_salary >= limit_total_salary 
	END REPEAT; 
	SET total_count = emp_count; 
	-- 关闭游标
  CLOSE emp_cursor;
END // 
DELIMITER ;
```

#### 小结

> 游标是 MySQL 的一个重要的功能，为 逐条读取 结果集中的数据，提供了完美的解决方案。跟在应用层面实现相同的功能相比，游标可以在存储程序中使用，效率高，程序也更加简洁。但同时也会带来一些性能问题，比如在使用游标的过程中，会对数据行进行 加锁 ，这样在业务并发量大的时候，不仅会影响业务之间的效率，还会 消耗系统资源 ，造成内存不足，这是因为游标是在内存中进行的处理。
>
> 建议：养成用完之后就关闭的习惯，这样才能提高系统的整体效率。

### 补充：MySQL 8. 0 的新特性—全局变量的持久化

> 使用SET GLOBAL语句设置的变量值只会 临时生效 。 数据库重启 后，服务器又会从MySQL配置文件中读取变量的默认值。
>
> MySQL 8.0版本新增了 `SET PERSIST` 命令。例如，设置服务器的最大连接数为1000： MySQL会将该命令的配置保存到数据目录下的 mysqld-auto.cnf 文件中，下次启动时会读取该文件，用其中的配置来覆盖默认的配置文件。
>
> ```sql
> SET PERSIST global max_connections = 1000;
> ```

## 触发器

> 在实际开发中，我们经常会遇到这样的情况：有 2 个或者多个相互关联的表，如 商品信息 和 库存信息 分别存放在 2 个不同的数据表中，我们在添加一条新商品记录的时候，为了保证数据的完整性，必须同时在库存表中添加一条库存记录。
>
> 这样一来，我们就必须把这两个关联的操作步骤写到程序里面，而且要用 事务 包裹起来，确保这两个操作成为一个 原子操作 ，要么全部执行，要么全部不执行。要是遇到特殊情况，可能还需要对数据进行手动维护，这样就很 容易忘记其中的一步 ，导致数据缺失。
>
> 这个时候，咱们可以使用触发器。**你可以创建一个触发器，让商品信息数据的插入操作自动触发库存数据的插入操作。**这样一来，就不用担心因为忘记添加库存数据而导致的数据缺失了。

### 触发器概述

> MySQL从 5.0.2 版本开始支持触发器。MySQL的触发器和存储过程一样，都是嵌入到MySQL服务器的一段程序。
>
> 触发器是由 事件来触发 某个操作，这些事件包括 INSERT 、 UPDATE 、 DELETE 事件。所谓事件就是指用户的动作或者触发某项行为。如果定义了触发程序，当数据库执行这些语句时候，就相当于事件发生了，就会 自动 激发触发器执行相应的操作。
>
> 当对数据表中的数据执行插入、更新和删除操作，需要自动执行一些数据库逻辑时，可以使用触发器来实现。

### 触发器的创建

> - 创建触发器的语法结构是：
>
> ```sql
> CREATE TRIGGER 触发器名称 
> {BEFORE|AFTER} {INSERT|UPDATE|DELETE} ON 表名 
> FOR EACH ROW 
> 触发器执行的语句块;
> ```
>
> - 说明：
>   - 表名 ：表示触发器监控的对象。
>   - BEFORE|AFTER ：表示触发的时间。BEFORE 表示在事件之前触发；AFTER 表示在事件之后触发。
>   - INSERT|UPDATE|DELETE ：表示触发的事件。INSERT 表示插入记录时触发；UPDATE 表示更新记录时触发；DELETE 表示删除记录时触发。
>   - 触发器执行的语句块 ：可以是单条SQL语句，也可以是由BEGIN…END结构组成的复合语句块。
>
> - 举例：NEW关键字代表INSERT添加语句的新记录
>
>   ```sql
>   DELIMITER // 
>   CREATE TRIGGER salary_check_trigger 
>   BEFORE INSERT ON employees 
>   FOR EACH ROW 
>   BEGIN
>   	DECLARE mgrsalary DOUBLE;
>     SELECT salary INTO mgrsalary FROM employees WHERE employee_id = NEW.manager_id; 
>     IF NEW.salary > mgrsalary 
>     	THEN SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = '薪资高于领导薪资错误'; 
>     END IF; 
>   END // 
>   DELIMITER ;
>   ```

### 查看触发器

> - 方式1：查看当前数据库的所有触发器的定义
>
>   ```sql
>   SHOW TRIGGERS;
>   ```
>
> - 方式2：查看当前数据库中某个触发器的定义
>
>   ```sql
>   SHOW CREATE TRIGGER 触发器名
>   ```
>
> - 方式3：从系统库information_schema的TRIGGERS表中查询“salary_check_trigger”触发器的信息。
>
>   ```sql
>   SELECT * FROM information_schema.TRIGGERS;
>   ```

### 删除触发器

> 触发器也是数据库对象，删除触发器也用DROP语句，语法格式如下：
>
> ```sql
> DROP TRIGGER IF EXISTS 触发器名称;
> ```

### 触发器的优缺点

#### 优点

> - 触发器可以确保数据的完整性 。
> - 触发器可以帮助我们记录操作日志。
> - 触发器还可以用在操作数据前，对数据进行合法性检查

#### 缺点

> - 触发器最大的一个问题就是可读性差。
> - 相关数据的变更，可能会导致触发器出错

#### 注意点

> 注意，如果在子表中定义了外键约束，并且外键指定了ON UPDATE/DELETE CASCADE/SET NULL子句，此时修改父表被引用的键值或删除父表被引用的记录行时，也会引起子表的修改和删除操作，此时基于子表的UPDATE和DELETE语句定义的触发器并不会被激活。

## MySQL 8 其它新特性

### MySQL 8 新特性概述

> MySQL从5.7版本直接跳跃发布了8.0版本 ，可见这是一个令人兴奋的里程碑版本。MySQL 8版本在功能上做了显著的改进与增强，开发者对MySQL的源代码进行了重构，最突出的一点是多MySQL Optimizer优化器进行了改进。不仅在速度上得到了改善，还为用户带来了更好的性能和更棒的体验。

### MySQL 8. 0 新增特性

> **1.** **更简便的NoSQL支持** NoSQL泛指非关系型数据库和数据存储。随着互联网平台的规模飞速发展，传统的关系型数据库已经越来越不能满足需求。从5.6版本开始，MySQL就开始支持简单的NoSQL存储功能。MySQL 8对这一功能做了优化，以更灵活的方式实现NoSQL功能，不再依赖模式（schema）。
>
> **2.** **更好的索引** 在查询中，正确地使用索引可以提高查询的效率。MySQL 8中新增了 隐藏索引 和 降序索 引 。隐藏索引可以用来测试去掉索引对查询性能的影响。在查询中混合存在多列索引时，使用降序索引可以提高查询的性能。
>
> **3.更完善的JSON支持** MySQL从5.7开始支持原生JSON数据的存储，MySQL 8对这一功能做了优化，增加了聚合函数 JSON_ARRAYAGG() 和 JSON_OBJECTAGG() ，将参数聚合为JSON数组或对象，新增了行内操作符 ->>，是列路径运算符 ->的增强，对JSON排序做了提升，并优化了JSON的更新操作。
>
> **4.安全和账户管理** MySQL 8中新增了 caching_sha2_password 授权插件、角色、密码历史记录和FIPS模式支持，这些特性提高了数据库的安全性和性能，使数据库管理员能够更灵活地进行账户管理工作。
>
> **5.InnoDB的变化** InnoDB是MySQL默认的存储引擎 ，是事务型数据库的首选引擎，支持事务安全表（ACID），支持行锁定和外键。在MySQL 8 版本中，InnoDB在自增、索引、加密、死锁、共享锁等方面做了大量的 改进和优化 ，并且支持原子数据定义语言（DDL），提高了数据安全性，对事务提供更好的支持。
>
> **6.数据字典** 在之前的MySQL版本中，字典数据都存储在元数据文件和非事务表中。从MySQL 8开始新增了事务数据字典，在这个字典里存储着数据库对象信息，这些数据字典存储在内部事务表中。
>
> **7.** **原子数据定义语句** MySQL 8开始支持原子数据定义语句（Automic DDL），即 原子DDL 。目前，只有InnoDB存储引擎支持原子DDL。原子数据定义语句（DDL）将与DDL操作相关的数据字典更新、存储引擎操作、二进制日志写入结合到一个单独的原子事务中，这使得即使服务器崩溃，事务也会提交或回滚。使用支持原子操作的存储引擎所创建的表，在执行DROP TABLE、CREATE TABLE、ALTER TABLE、 RENAME TABLE、TRUNCATE TABLE、CREATE TABLESPACE、DROP TABLESPACE等操作时，都支持原子操作，即事务要么完全操作成功，要么失败后回滚，不再进行部分提交。 对于从MySQL 5.7复制到MySQL 8版本中的语句，可以添加 IF EXISTS 或 IF NOT EXISTS 语句来避免发生错误。
>
> **8.资源管理** MySQL 8开始支持创建和管理资源组，允许将服务器内运行的线程分配给特定的分组，以便线程根据组内可用资源执行。组属性能够控制组内资源，启用或限制组内资源消耗。数据库管理员能够根据不同的工作负载适当地更改这些属性。 目前，CPU时间是可控资源，由“虚拟CPU”这个概念来表示，此术语包含CPU的核心数，超线程，硬件线程等等。服务器在启动时确定可用的虚拟CPU数量。拥有对应权限的数据库管理员可以将这些CPU与资源组关联，并为资源组分配线程。 资源组组件为MySQL中的资源组管理提供了SQL接口资源组的属性用于定义资源组。MySQL中存在两个默认组，系统组和用户组，默认的组不能被删除，其属性也不能被更改。对于用户自定义的组，资源组创建时可初始化所有的属性，除去名字和类型，其他属性都可在创建之后进行更改。 在一些平台下，或进行了某些MySQL的配置时，资源管理的功能将受到限制，甚至不可用。例如，如果安装了线程池插件，或者使用的是macOS系统，资源管理将处于不可用状态。在FreeBSD和Solaris系统中，资源线程优先级将失效。在Linux系统中，只有配置了CAP_SYS_NICE属性，资源管理优先级才能发挥作用。
>
> **9.字符集支持** MySQL 8中默认的字符集由 latin1 更改为 utf8mb4 ，并首次增加了日语所特定使用的集合，utf8mb4_ja_0900_as_cs。 
>
> **10.优化器增强** MySQL优化器开始支持隐藏索引和降序索引。隐藏索引不会被优化器使用，验证索引的必要性时不需要删除索引，先将索引隐藏，如果优化器性能无影响就可以真正地删除索引。降序索引允许优化器对多个列进行排序，并且允许排序顺序不一致。
>
> **11.公用表表达式** 公用表表达式（Common Table Expressions）简称为CTE，MySQL现在支持递归和非递归两种形式的CTE。CTE通过在SELECT语句或其他特定语句前 使用WITH语句对临时结果集 进行命名。基础语法如下：Subquery代表子查询，子查询前`使用WITH语句`将结果集命名为cte_name，在后续的查询中即可使用cte_name进行查询。
>
> **12.窗口函数** MySQL 8开始支持窗口函数。在之前的版本中已存在的大部分 聚合函数 在MySQL 8中也可以作为窗口函数来使用。
>
> **13.正则表达式支持** MySQL在8.0.4以后的版本中采用支持Unicode的国际化组件库实现正则表达式操作，这种方式不仅能提供完全的Unicode支持，而且是多字节安全编码。MySQL增加了REGEXP_LIKE()、 EGEXP_INSTR()、REGEXP_REPLACE()和 REGEXP_SUBSTR()等函数来提升性能。另外，regexp_stack_limit和 regexp_time_limit 系统变量能够通过匹配引擎来控制资源消耗。
>
> **14.内部临时表** TempTable存储引擎取代MEMORY存储引擎成为内部临时表的默认存储引擎 。TempTable存储引擎为VARCHAR和VARBINARY列提供高效存储。internal_tmp_mem_storage_engine会话变量定义了内部临时表的存储引擎，可选的值有两个，TempTable和MEMORY，其中TempTable为默认的存储引擎。temptable_max_ram系统配置项定义了TempTable存储引擎可使用的最大内存数量。
>
> **15.日志记录** 在MySQL 8中错误日志子系统由一系列MySQL组件构成。这些组件的构成由系统变量log_error_services来配置，能够实现日志事件的过滤和写入。WITH cte_name (col_name1,col_name2 ...) AS (Subquery) SELECT * FROM cte_name;**16.****备份锁** 新的备份锁允许在线备份期间执行数据操作语句，同时阻止可能造成快照不一致的操作。备份锁由 LOCK INSTANCE FOR BACKUP 和 UNLOCK INSTANCE 语法提供支持，执行这些操作需要备份管理员特权。
>
> **17.增强的MySQL复制** MySQL 8复制支持对 JSON文档 进行部分更新的 二进制日志记录 ，该记录 使用紧凑 的二进制格式 ，从而节省记录完整JSON文档的空间。当使用基于语句的日志记录时，这种紧凑的日志记录会自动完成，并且可以通过将新的binlog_row_value_options系统变量值设置为PARTIAL_JSON来启用。

### MySQL 8. 0 移除的旧特性

> 在MySQL 5.7版本上开发的应用程序如果使用了MySQL8.0 移除的特性，语句可能会失败，或者产生不同的执行结果。为了避免这些问题，对于使用了移除特性的应用，应当尽力修正避免使用这些特性，并尽可能使用替代方法。
>
> **1.** **查询缓存** 查询缓存已被移除 ，删除的项有： **（1）**语句：FLUSH QUERY CACHE和RESET QUERY CACHE。 **（2）**系统变量：query_cache_limit、query_cache_min_res_unit、query_cache_size、 query_cache_type、query_cache_wlock_invalidate。 **（3）**状态变量：Qcache_free_blocks、 Qcache_free_memory、Qcache_hits、Qcache_inserts、Qcache_lowmem_prunes、Qcache_not_cached、 Qcache_queries_in_cache、Qcache_total_blocks。 **（4**）线程状态：checking privileges on cached query、checking query cache for query、invalidating query cache entries、sending cached result to client、storing result in query cache、waiting for query cache lock。 
>
> **2.加密相关** 删除的加密相关的内容有：ENCODE()、DECODE()、ENCRYPT()、DES_ENCRYPT()和 DES_DECRYPT()函数，配置项des-key-file，系统变量have_crypt，FLUSH语句的DES_KEY_FILE选项，HAVE_CRYPT CMake选项。 对于移除的ENCRYPT()函数，考虑使用SHA2()替代，对于其他移除的函数，使用AES_ENCRYPT()和AES_DECRYPT()替代。
>
> **3.空间函数相关** 在MySQL 5.7版本中，多个空间函数已被标记为过时。这些过时函数在MySQL 8中都已被移除，只保留了对应的ST_和MBR函数。
>
> **4.\N和NULL** 在SQL语句中，解析器不再将\N视为NULL，所以在SQL语句中应使用NULL代替\N。这项变化不会影响使用LOAD DATA INFILE或者SELECT...INTO OUTFILE操作文件的导入和导出。在这类操作中，NULL仍等同于\N。 
>
> **5. mysql_install_db** 在MySQL分布中，已移除了mysql_install_db程序，数据字典初始化需要调用带着-- initialize或者--initialize-insecure选项的mysqld来代替实现。另外，--bootstrap和INSTALL_SCRIPTDIR CMake也已被删除。
>
> **6.通用分区处理程序** 通用分区处理程序已从MySQL服务中被移除。为了实现给定表分区，表所使用的存储引擎需要自有的分区处理程序。 提供本地分区支持的MySQL存储引擎有两个，即InnoDB和NDB，而在MySQL 8中只支持InnoDB。 
>
> **7.系统和状态变量信息** 在INFORMATION_SCHEMA数据库中，对系统和状态变量信息不再进行维护。GLOBAL_VARIABLES、SESSION_VARIABLES、GLOBAL_STATUS、SESSION_STATUS表都已被删除。另外，系统变量show_compatibility_56也已被删除。被删除的状态变量有Slave_heartbeat_period、 Slave_last_heartbeat,Slave_received_heartbeats、Slave_retried_transactions、Slave_running。以上被删除的内容都可使用性能模式中对应的内容进行替代。
>
> **8.mysql_plugin工具** mysql_plugin工具用来配置MySQL服务器插件，现已被删除，可使用--plugin-load或- -plugin-load-add选项在服务器启动时加载插件或者在运行时使用INSTALL PLUGIN语句加载插件来替代该工具。

### 新特性 1 ：窗口函数

> 计算城市的不同区的销售额指标

```sql
SELECT city                                                    AS 城市,
       county                                                  AS 区,
       sales_value                                             AS 区销售额,
       SUM(sales_value) OVER (PARTITION BY city)               AS 市销售额, -- 计算市销售额
       sales_value / SUM(sales_value) OVER (PARTITION BY city) AS 市比率,
       SUM(sales_value) OVER ()                                AS 总销售额, -- 计算总销售额
       sales_value / SUM(sales_value) OVER ()                  AS 总比率
FROM sales
ORDER BY city, county;
```

#### 窗口函数分类

> MySQL从8.0版本开始支持窗口函数。窗口函数的作用类似于在查询中对数据进行分组，不同的是，分组操作会把分组的结果聚合成一条记录，而窗口函数是将结果置于每一条数据记录中。
>
> 窗口函数可以分为 静态窗口函数 和 动态窗口函数 。静态窗口函数的窗口大小是固定的，不会因为记录的不同而不同；动态窗口函数的窗口大小会随着记录的不同而变化。
>
> MySQL官方网站窗口函数的网址为https://dev.mysql.com/doc/refman/8.0/en/window-function-descriptions.html#function_row-number。
>
> 窗口函数总体上可以分为序号函数、分布函数、前后函数、首尾函数和其他函数，如下表：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220822205105234.png" alt="image-20220822205105234" style="zoom:50%;" />

#### 语法结构

> 窗口函数的语法结构是：
>
> ```sql
> 函数 OVER（[PARTITION BY 字段名 ORDER BY 字段名 ASC|DESC]） 
> 函数 OVER 窗口名 … WINDOW 窗口名 AS （[PARTITION BY 字段名 ORDER BY 字段名 ASC|DESC]）
> ```
>
> - OVER 关键字指定函数窗口的范围。
>   - 如果省略后面括号中的内容，则窗口会包含满足WHERE条件的所有记录，窗口函数会基于所有满足WHERE条件的记录进行计算。
>   - 如果OVER关键字后面的括号不为空，则可以使用如下语法设置窗口。
>
> - 窗口名：为窗口设置一个别名，用来标识窗口。
>
> - PARTITION BY子句：指定窗口函数按照哪些字段进行分组。分组后，窗口函数可以在每个分组中分别执行。
>
> - ORDER BY子句：指定窗口函数按照哪些字段进行排序。执行排序操作使窗口函数按照排序后的数据记录的顺序进行编号。
>
> - FRAME子句：为分区中的某个子集定义规则，可以用来作为滑动窗口使用。


#### 使用案例

##### 数据准备

```sql
CREATE TABLE goods( 
	id INT PRIMARY KEY AUTO_INCREMENT,
  category_id INT, 
  category VARCHAR(15), 
  NAME VARCHAR(30), 
  price DECIMAL(10,2),
  stock INT, upper_time DATETIME 
);

INSERT INTO goods(category_id,category,NAME,price,stock,upper_time) VALUES 
(1, '女装/女士精品', 'T恤', 39.90, 1000, '2020-11-10 00:00:00'), 
(1, '女装/女士精品', '连衣裙', 79.90, 2500, '2020-11-10 00:00:00'), 
(1, '女装/女士精品', '卫衣', 89.90, 1500, '2020-11-10 00:00:00'), 
(1, '女装/女士精品', '牛仔裤', 89.90, 3500, '2020-11-10 00:00:00'), 
(1, '女装/女士精品', '百褶裙', 29.90, 500, '2020-11-10 00:00:00'),
(1, '女装/女士精品', '呢绒外套', 399.90, 1200, '2020-11-10 00:00:00'), 
(2, '户外运动', '自行车', 399.90, 1000, '2020-11-10 00:00:00'), 
(2, '户外运动', '山地自行车', 1399.90, 2500, '2020-11-10 00:00:00'), 
(2, '户外运动', '登山杖', 59.90, 1500, '2020-11-10 00:00:00'),
(2, '户外运动', '骑行装备', 399.90, 3500, '2020-11-10 00:00:00'), 
(2, '户外运动', '运动外套', 799.90, 500, '2020-11-10 00:00:00'),
(2, '户外运动', '滑板', 499.90, 1200, '2020-11-10 00:00:00');
```

##### 序号函数

> ROW_NUMBER()函数能够对数据中的序号进行顺序显示

```sql
SELECT ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY price DESC) AS row_num,
	id,category_id,category,NAME,price,stock
FROM goods;
```

> RANK()函数能够对序号进行并列排序，并且会跳过重复的序号，比如序号为1、1、3

```sql
SELECT RANK() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num,
    id, category_id, category, NAME, price, stock
FROM goods;
```

> DENSE_RANK()函数对序号进行并列排序，并且不会跳过重复的序号，比如序号为 1 、 1 、 2 。

```sql
SELECT dense_rank() OVER(PARTITION BY category_id ORDER BY price DESC) AS row_num,
    id, category_id, category, NAME, price, stock
FROM goods;
```

##### 分布函数

> PERCENT_RANK()函数是等级值百分比函数，计算方式：(rank - 1) / (rows - 1) ，rank的值为使用RANK()函数产生的序号，rows的值为当前窗口的总记录数。

```sql
-- 计算 goods 数据表中名称为“女装/女士精品”的类别下的商品的PERCENT_RANK值

-- 写法一： 
SELECT RANK() OVER (PARTITION BY category_id ORDER BY price DESC)         AS r,
       PERCENT_RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS pr, 
       id, category_id, category, NAME, price, stock
FROM goods
WHERE category_id = 1;

-- 写法二 
SELECT RANK() OVER w         AS r,
       PERCENT_RANK() OVER w AS pr, 
       id, category_id, category, NAME, price, stock
FROM goods
WHERE category_id = 1 WINDOW w AS (PARTITION BY category_id ORDER BY price DESC);
```

> CUME_DIST()函数主要用于查询小于或等于某个值的比例。

```sql
-- 查询goods数据表中小于或等于当前价格的比例。
SELECT CUME_DIST() OVER(PARTITION BY category_id ORDER BY price ASC) AS cd,
id, category, NAME, price
FROM goods;
```

##### 前后函数

> LAG(expr,n)函数返回当前行的前n行的expr的值。

```sql
-- 查询goods数据表中前一个商品价格与当前商品价格的差值
SELECT id, category, NAME, price, pre_price, price - pre_price AS diff_price
FROM (
    SELECT id, category, NAME, price, 
  			LAG(price, 1) OVER (PARTITION BY category_id ORDER BY price) AS pre_price
    FROM goods
) t;
```

> LEAD(expr,n)函数返回当前行的后n行的expr的值。

```sql
-- 查询goods数据表中后一个商品价格与当前商品价格的差值
SELECT id, category, NAME, price, next_price, price - next_price AS diff_price
FROM (
	SELECT id, category, NAME, price,
  	lead(price,1) OVER (PARTITION BY category_id ORDER BY price desc) AS next_price
	FROM goods
) t;
```

##### 首尾函数

> FIRST_VALUE(expr)函数返回第一个expr的值。

```sql
-- 按照价格排序，查询第1个商品的价格信息。
SELECT  id, category, NAME, price, stock,
       FIRST_VALUE(price) OVER (PARTITION BY category_id ORDER BY price) AS first_price
FROM goods;
```

> LAST_VALUE(expr)函数

```sql
-- 按照价格排序，查询最后一个商品的价格信息。
SELECT  id, category, NAME, price, stock,
       LAST_VALUE(price) OVER (PARTITION BY category_id ORDER BY price) AS last_price
FROM goods;
```

##### 其他函数

> NTH_VALUE(expr,n)函数返回第n个expr的值。

```sql
-- 查询goods数据表中排名第2和第3的价格信息。
SELECT id, category, NAME, price,NTH_VALUE(price,2) OVER w AS second_price,
    NTH_VALUE(price,3) OVER w AS third_price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);
```

> NTILE(n)函数将分区中的有序数据分为n个桶，记录桶编号。

```sql
-- 将goods表中的商品按照价格分为3组。
SELECT NTILE(3) OVER w AS nt,id, category, NAME, price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);
```

### 新特性 2 ：公用表表达式

> 公用表表达式（或通用表表达式）简称为CTE（Common Table Expressions）。CTE是一个命名的临时结果集，作用范围是当前语句。CTE可以理解成一个可以复用的子查询，当然跟子查询还是有点区别的，CTE可以引用其他CTE，但子查询不能引用其他子查询。所以，可以考虑代替子查询。
>
> 依据语法结构和执行方式的不同，公用表表达式分为 `普通公用表表达式 和 递归公用表表达式 `2 种。

#### 普通公用表表达式

> - 语法结构
>
>   ```sql
>   WITH CTE名称 AS （子查询） SELECT|DELETE|UPDATE 语句;
>   
>   WITH emp_dept_id AS (SELECT DISTINCT department_id FROM employees)
>   ```

#### 递归公用表表达式

> - 递归公用表表达式也是一种公用表表达式，只不过，除了普通公用表表达式的特点以外，它还有自己的特点，就是**可以调用自己**
>
> - 语法结构：
>
>   ```sql
>   WITH RECURSIVE CTE名称 AS （子查询） SELECT|DELETE|UPDATE 语句;
>   ```
>
> - 递归公用表表达式由 2 部分组成，分别是`种子查询和递归查询`，中间通过关键字 `UNION [ALL]`进行连接。这里的**种子查询，意思就是获得递归的初始值**。这个查询只会运行一次，以创建初始数据集，之后递归查询会一直执行，直到没有任何新的查询数据产生，递归返回。
>
> - 举例：的employees表，包含employee_id，last_name和manager_id三个字段。如果a是b的管理者，那么，我们可以把b叫做a的下属，如果同时b又是c的管理者，那么c就是b的下属，是a的下下属
>
>   ```sql
>   -- 找自己和所有下属
>   with recursive temp as(
>       select employee_id, last_name, manager_id, 1 as n from employees where employee_id = 2
>       union
>       select a.employee_id, a.last_name, a.manager_id, n + 1 from employees a join temp on a.manager_id = temp.employee_id
>       )
>   select * from temp;
>   
>   -- 找自己和所有上级
>   with recursive temp as(
>       select employee_id, last_name, manager_id, 1 as n from employees where employee_id = 2
>       union
>       select a.employee_id, a.last_name, a.manager_id, n + 1 from employees a join temp on a.employee_id = temp.manager_id
>       )
>   select * from temp;
>   ```

