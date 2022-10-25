# MySQL高级篇

## Linux下MySQL的安装与使用

### 安装前说明

#### Linux系统及工具的准备

> - 安装并启动好两台虚拟机： CentOS 7
>   - 掌握克隆虚拟机的操作
>   - mac地
>   - 主机名
>   - ip地址
>   - UUID
>
> - 安装有 Xshell 和 Xftp 等访问CentOS系统的工具
>
> - CentOS6和CentOS7在MySQL的使用中的区别
>
>   ```
>    防火墙：6是iptables，7是firewalld 
>    启动服务的命令：6是service，7是systemctl
>   ```

#### 查看是否安装过MySQL

> - 如果你是用rpm安装, 检查一下RPM PACKAGE：
>
>   ```shell
>   rpm -qa | grep -i mysql # -i 忽略大小写
>   ```
>
> - 检查mysql service：
>
>   ```bash
>   systemctl status mysqld.service
>   ```
>
> - 如果存在mysql-libs的旧版本包，显示如下：
>
>   <img src="/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20220826091807724.png" alt="image-20220826091807724" style="zoom:50%;" />
>   
> - 如果不存在mysql-lib的版本，显示如下：
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826091826876.png" alt="image-20220826091826876" style="zoom:50%;" />

#### MySQL的卸载

> - 关闭mysql服务
>
>   ```
>   systemctl stop mysqld.service
>   ```
>
> - **查看当前** **mysql** **安装状况**
>
>   ```shell
>   rpm -qa | grep -i mysql
>   yum list installed | grep mysql
>   ```
>
> - **卸载上述命令查询出的已安装程序**，务必卸载干净，反复执行 rpm -qa | grep -i mysql 确认是否有卸载残留
>
>   ```shell
>   yum remove mysql-xxx mysql-xxx mysql-xxx mysqk-xxxx
>   ```
>
> - **删除** **mysql** **相关文件**
>   - 查找相关文件
>
>     ```
>     find / -name mysql
>     ```
>
>   - 删除上述命令查找出的相关文件
>
>     ```
>     rm -rf xxx
>     ```
>
> - **删除** **my.cnf**
>
>   ```
>   rm -rf /etc/my.cnf
>   ```

### MySQL的Linux版安装

#### MySQL的 4 大版本

> **MySQL Community Server** **社区版本**，开源免费，自由下载，但不提供官方技术支持，适用于大多数普通用户。
>
> **MySQL Enterprise Edition** **企业版本**，需付费，不能在线下载，可以试用30天。提供了更多的功能和更完备的技术支持，更适合于对数据库的功能和可靠性要求较高的企业客户。
>
> **MySQL Cluster** **集群版**，开源免费。用于架设集群服务器，可将几个MySQL Server封装成一个Server。需要在社区版或企业版的基础上使用。
>
> **MySQL Cluster CGE** **高级集群版**，需付费。

#### 下载MySQL指定版本

> - 下载地址：官网： **https://www.mysql.com**
>
> - 打开官网，点击DOWNLOADS，然后，点击MySQL Community(GPL) Downloads
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826092831636.png" alt="image-20220826092831636" style="zoom:45%;" />
>
> - 点击 MySQL Community Server
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826092905952.png" alt="image-20220826092905952" style="zoom:33%;" />
>
> - 在General Availability(GA) Releases中选择适合的版本
>
>   - 如果安装Windows 系统下MySQL ，推荐下载 MSI安装程序 ；点击 Go to Download Page 进行下载即可
>   - <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826093041733.png" alt="image-20220826093041733" style="zoom:33%;" />
>
>   - Windows下的MySQL安装有两种安装程序
>     - mysql-installer-web-community-8.0.25.0.msi 下载程序大小：2.4M；安装时需要联网安装组件。
>     - mysql-installer-community-8.0.25.0.msi 下载程序大小：435.7M；安装时离线安装即可。**推荐。**
>
>   - Linux下的MySQL，这里不能直接选择CentOS 7系统的版本，所以选择与之对应的 Red Hat Enterprise Linux，下载RPM Bundle全量包。包括了所有下面的组件。
>
>     <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826093400053.png" alt="image-20220826093400053" style="zoom: 43%;" />
>
>     <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826093532590.png" alt="image-20220826093532590" style="zoom:50%;" />

#### CentOS7下MySQL安装过程

> - 将安装程序拷贝到/opt目录下
>
> - 在mysql的安装文件目录下执行：（必须按照顺序执行）
>
>   ```shell
>   rpm -ivh mysql-community-common-8.0.25-1.el7.x86_64.rpm 
>   rpm -ivh mysql-community-client-plugins-8.0.25-1.el7.x86_64.rpm 
>   rpm -ivh mysql-community-libs-8.0.25-1.el7.x86_64.rpm 
>   rpm -ivh mysql-community-client-8.0.25-1.el7.x86_64.rpm 
>   rpm -ivh mysql-community-server-8.0.25-1.el7.x86_64.rpm
>   ```
>
> - 注意: 如在检查工作时，没有检查mysql依赖环境在安装mysql-community-server会报错
>
>   rpm 是Redhat Package Manage缩写，通过RPM的管理，用户可以把源代码包装成以rpm为扩展名的文件形式，易于安装。
>
>   -i , --install 安装软件包
>
>   -v , --verbose 提供更多的详细信息输出
>
>   -h , --hash 软件包安装的时候列出哈希标记 (和 -v 一起使用效果更好)，展示进度条

##### 安装过程中可能的报错信息

> - ![image-20220826094105177](/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20220826094105177.png)
>
> 一个命令：**yum remove mysql-libs** 解决，清除之前安装过的依赖即可
>
> - ![image-20220826095400489](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826095400489.png)
>
>   ```
>   yum -y remove mariadb-libs-1:5.5.68-1.el7.x86_64
>   ```
>   
> - InnoDB: Operating system error number 13 in a file operation的解决方法
>
>   ```shell
>   [root@localhost ~]# getenforce    //查看selinux状态
>   Enforcing
>   [root@localhost ~]# setenforce 0    //临时关闭selinux，重启后失效
>    
>   [root@localhost ~]# getenforce
>   Permissive
>   [root@localhost ~]# systemctl start mysqld     //启动mysql服务成功
>   [root@localhost ~]# ss -antulp | grep :3306
>   tcp    LISTEN     0      80     [::]:3306               [::]:*                   users:(("mysqld",pid=74057,fd=17))
>   ```

#####  查看MySQL版本

> - 执行如下命令，如果成功表示安装mysql成功。类似java -version如果打出版本等信息
>
> ```shell
> mysql --version 
> mysqladmin --version
> ```
>
> - 执行如下命令，查看是否安装成功。需要增加 -i 不用去区分大小写，否则搜索不到。
>
> ```shell
> rpm -qa|grep -i mysql
> ```

##### 服务的初始化

> - 为了保证数据库目录与文件的所有者为 mysql 登录用户，如果你是以 root 身份运行 mysql 服务，需要执行下面的命令初始化：
>
>   ```
>   mysqld --initialize --user=mysql
>   ```
>
> - 说明： --initialize 选项默认以“安全”模式来初始化，则会为 root 用户生成一个密码并将该密码标记为过期，登录后你需要设置一个新的密码。生成的临时密码会往日志中记录一份。
>
> - 查看密码：
>
>   ```
>   cat /var/log/mysqld.log
>   ```
>
>   ![image-20220826100301750](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826100301750.png)
>
>   root@localhost: 后面就是初始化的密码

##### 启动MySQL，查看状态

```bash
#加不加.service后缀都可以 ,mysqld 这个可执行文件就代表着 MySQL 服务器程序，运行这个可执行文件就可以直接启动一个服务器进程。
启动：systemctl start mysqld.service 
关闭：systemctl stop mysqld.service 
重启：systemctl restart mysqld.service 
查看状态：systemctl status mysqld.service
查看进程：ps -ef | grep -i mysql
```

##### 查看MySQL服务是否自启动

> - systemctl list-unit-files|grep mysqld.service ，默认是enabled。
> - 设置自启动：systemctl enable mysqld.service 
> - 设置不自启动：systemctl disable mysqld.service 

### MySQL登录

#### 首次登录

> 通过 mysql -hlocalhost -P3306 -uroot -p进行登录，在Enter password：录入初始化密码

#### 修改密码

> - 因为初始化密码默认是过期的，所以查看数据库会报错
>
> - 修改密码：5.7版本之后（不含5.7），mysql加入了全新的密码安全机制。设置新密码太简单会报错。
>
>   ```sql
>   ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';
>   ```

#### 设置远程登录

##### 当前问题

> 在用SQLyog或Navicat中配置远程连接Mysql数据库时遇到如下报错信息，这是由于Mysql配置了不支持远程连接引起的。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826101229595.png" alt="image-20220826101229595" style="zoom:50%;" />

##### 确认网络

> - 在远程机器上使用ping ip地址保证网络畅通
> - 在远程机器上使用telnet命令保证端口号开放访问：telnet ip地址 端口号

##### 关闭防火墙或开放端口

> 方式一：关闭防火墙
>
> - CentOS6：service iptables stop 
>
> - CentOS7：
>
>   ```shell
>   systemctl start firewalld.service 
>   systemctl status firewalld.service 
>   systemctl stop firewalld.service 
>   #设置开机启用防火墙 
>   systemctl enable firewalld.service 
>   #设置开机禁用防火墙 
>   systemctl disable firewalld.service
>   ```
>
> 方式二：开放端口
>
> - 查看开放的端口号：firewall-cmd --list-all
>
> - 设置开放的端口号：
>
>   firewall-cmd --add-service=http --permanent
>
>   firewall-cmd --add-port=3306/tcp --permanent
>
> - 重启防火墙
>
>   firewall-cmd --reload

#####  Linux下修改配置

> - 在Linux系统MySQL下测试：
>
> ```sql
> use mysql; 
> select Host,User from user;
> ```
>
> - 可以看到root用户的当前主机配置信息为localhost。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826102506268.png" alt="image-20220826102506268" style="zoom:50%;" />
>
> - 修改Host为通配符%
>
>   Host列指定了允许用户登录所使用的IP，比如user=root Host=192.168.1.1。这里的意思就是说root用户只能通过192.168.1.1的客户端去访问。 user=root Host=localhost，表示只能通过本机客户端去访问。而 %是个 通配符 ，如果Host=192.168.1.%，那么就表示只要是IP地址前缀为“192.168.1.”的客户端都可以连接。如果 Host=% ，表示所有IP都有连接权限。
>
>   注意：在生产环境下不能为了省事将host设置为%，这样做会存在安全问题，具体的设置可以根据生产环境的IP进行设置。
>
>   ```sql
>   update user set host = '%' where user ='root';
>   或者
>   grant all privileges on *.* to 'root'@'%' identified by '123456';
>   
>   -- Host修改完成后记得执行flush privileges使配置立即生效：
>   flush privileges;
>   ```

##### 测试

> - 如果是 MySQL5.7 版本，接下来就可以使用SQLyog或者Navicat成功连接至MySQL了。
>
> - 如果是 MySQL8 版本，连接时还会出现如下问题：
>
>   <img src="/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20220826102822125.png" alt="image-20220826102822125" style="zoom:50%;" />
>   
>   配置新连接报错：错误号码 2058，分析是 mysql 密码加密方法变了。
>   
> - **解决方法：**Linux下 mysql -u root -p 登录你的 mysql 数据库，然后 执行这条SQL：
>
>   ```sql
>   ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'abc123';
>   ```
>
>   然后在重新配置SQLyog的连接，则可连接成功了，OK。

### 字符集的相关操作

#### 修改MySQL 5. 7 字符集

> 在MySQL 8. 0 版本之前，默认字符集为latin1，utf 8 字符集指向的是utf8mb3。网站开发人员在数据库设计的时候往往会将编码修改为utf 8 字符集。如果遗忘修改默认的编码，就会出现乱码的问题。
>
> 从MySQL8.0 开始，数据库的默认编码将改为utf8mb4，从而避免上述乱码的问题。

##### 查看默认使用的字符集

```sql
show variables like 'character%';
show variables like '%char%';
```

> - MySQL8.0
>
>   <img src="/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20220826145323727.png" alt="image-20220826145323727" style="zoom:50%;" />
>   
> - MySQL5.7
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826145354772.png" alt="image-20220826145354772" style="zoom:50%;" />
>
>   MySQL 5. 7 默认的客户端和服务器都用了latin1，不支持中文，保存中文会报错。MySQL 5. 7 截图如下：
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826145443003.png" alt="image-20220826145443003" style="zoom:50%;" />

##### 修改字符集

> 在MySQL 5. 7 或之前的版本中，在文件最后加上中文字符集配置。重启MySQL，但是原库、原表的设置不会改变。

```sql
vim /etc/my.cnf
character_set_server=utf8mb4
systemctl restart mysqld
```

##### 已有库&表字符集的变更

> MySQL 5. 7 版本中，以前创建的库，创建的表字符集还是latin 1 。注意：但是原有的数据如果是用非'utf8'编码的话，数据本身编码不会发生改变。已有数据需要导出或删除，然后重新插入。
>
> - 修改已创建数据库的字符集
>
>   ```sql
>   alter database dbtest1 character set 'utf8';
>   ```
>
> - 修改已创建数据表的字符集
>
>   ```sql
>   alter table t_emp convert to character set 'utf8';
>   ```
>

#### 各级别的字符集

> MySQL有 4 个级别的字符集和比较规则，分别是：
>
> - 服务器级别
>
> - 数据库级别
>
> - 表级别
>
> - 列级别
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826150043616.png" style="zoom:50%;" />

##### 服务器级别

> character_set_server ：服务器级别的字符集。
>
> 我们可以在启动服务器程序时通过启动选项或者在服务器程序运行过程中使用 SET 语句修改这两个变量的值。比如我们可以在配置文件中这样写：
>
> ```
> [server] 
> character_set_server=gbk # 默认字符集 
> collation_server=gbk_chinese_ci #对应的默认的比较规则
> ```
>
> 当服务器启动的时候读取这个配置文件后这两个系统变量的值便修改了。

##### 数据库级别

> character_set_database：当前数据库的字符集
>
> ```sql
> CREATE DATABASE 数据库名 [[DEFAULT] CHARACTER SET 字符集名称] [[DEFAULT] COLLATE 比较规则名称]; 
> ALTER DATABASE 数据库名 [[DEFAULT] CHARACTER SET 字符集名称] [[DEFAULT] COLLATE 比较规则名称];
> ```

##### 表级别

> 如果创建和修改表的语句中没有指明字符集和比较规则，将使用该表所在`数据库的字符集`和比较规则作为该表的字符集和比较规则。

```sqlite
CREATE TABLE 表名 (列的信息) [[DEFAULT] CHARACTER SET 字符集名称] [COLLATE 比较规则名称]] 
ALTER TABLE 表名 [[DEFAULT] CHARACTER SET 字符集名称] [COLLATE 比较规则名称]
```

##### 列级别

> - 对于某个列来说，如果在创建和修改的语句中没有指明字符集和比较规则，将使用该列`所在表的字符集`和比较规则作为该列的字符集和比较规则。
> - 在转换列的字符集时需要注意，如果转换前列中存储的数据不能用转换后的字符集进行表示会发生错误。比方说原先列使用的字符集是utf8，列中存储了一些汉字，现在把列的字符集转换为ascii的话就会出错，因为ascii字符集并不能表示汉字字符。

```sql
CREATE TABLE 表名( 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称], 其他列... );
ALTER TABLE 表名 MODIFY 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称];
```

#### 字符集与比较规则(了解)

##### utf 8 与 utf 8 mb 4

> utf8字符集表示一个字符需要使用 1 ～ 4 个字节，但是我们常用的一些字符使用 1 ～ 3 个字节就可以表示了。而字符集表示一个字符所用的最大字节长度，在某些方面会影响系统的存储和性能，所以设计MySQL的设计者偷偷的定义了两个概念：
>
> utf8mb3：阉割过的utf8字符集，只使用 1 ～ 3 个字节表示字符。如果是utf8，代表是utf8mb3
> utf8mb4：正宗的utf8字符集，使用 1 ～ 4 个字节表示字符。可以存放emoj表情

##### 比较规则

> 一般的字符集用于比较大小、排序等使用。MySQL版本一共支持 41 种字符集，其中的Default collation列表示这种字符集中一种默认的比较规则，里面包含着该比较规则主要作用于哪种语言，比如utf8_polish_ci表示以波兰语的规则比较，utf8_spanish_ci是以西班牙语的规则比较，utf8_general_ci是一种通用的比较规则。
>
> 后缀表示该比较规则是否区分语言中的重音、大小写。具体如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826151522303-20220826151855335-20220826151912645.png" alt="image-20220826151522303" style="zoom:50%;" />

```sql
-- 查看GBK字符集的比较规则 
SHOW COLLATION LIKE 'gbk%'; 
-- 查看UTF-8字符集的比较规则 
SHOW COLLATION LIKE 'utf8%';

-- 查看服务器的字符集和比较规则
SHOW VARIABLES LIKE '%_server';
-- 查看数据库的字符集和比较规则
SHOW VARIABLES LIKE '%_database';
-- 查看具体数据库的字符集
SHOW CREATE DATABASE dbtest1;
-- 修改具体数据库的字符集
ALTER DATABASE dbtest1 DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

-- 查看表的字符集 
show create table employees; 
-- 查看表的比较规则 
show table status from atguigudb like 'employees'; 
-- 修改表的字符集和比较规则 
ALTER TABLE emp1 DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
```

#### 请求到响应过程中字符集的变化

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826152651053.png" alt="image-20220826152651053" style="zoom:50%;" />

> - 客户端发送请求所使用的字符集
>
>   一般情况下客户端所使用的字符集和当前操作系统一致，不同操作系统使用的字符集可能不一样，如下：类 Unix 系统使用的是 utf8 ，Windows 使用的是 gbk
>
>   当客户端使用的是 utf8 字符集，字符 '我' 在发送给服务器的请求中的字节形式就是：0xE68891。如果你使用的是可视化工具，比如navicat之类的，这些工具可能会使用自定义的字符集来编码发送到服务器的字符串，而不采用操作系统默认的字符集（所以在学习的时候还是尽量用命令行窗口）。
>
> - 服务器接收到客户端发送来的请求其实是一串二进制的字节，它会认为这串字节采用的字符集是character_set_client ，然后把这串字节转换为 character_set_connection 字符集编码的字符。
>
>   由于我的计算机上 character_set_client 的值是 utf8 ，首先会按照 utf8 字符集对字节串0xE68891 进行解码，得到的字符串就是 '我' ，然后按照 character_set_connection 代表的字符集，也就是 gbk 进行编码，得到的结果就是字节串 0xCED2 。 
>
> - 因为表 t 的列 col 采用的是 gbk 字符集，与 character_set_connection 一致，所以直接到列中找字节值为 0xCED2 的记录，最后找到了一条记录。如果某个列使用的字符集和character_set_connection代表的字符集不一致的话，还需要进行一次字符集转换。
>
> - 上一步骤找到的记录中的 col 列其实是一个字节串 0xCED2 ， col 列是采用 gbk 进行编码的，所以首先会将这个字节串使用 gbk 进行解码，得到字符串 '我' ，然后再把这个字符串使用character_set_results 代表的字符集，也就是 utf8 进行编码，得到了新的字节串：0xE68891 ，然后发送给客户端。
>
> - 由于客户端是用的字符集是 utf8 ，所以可以顺利的将 0xE68891 解释成字符 我 ，从而显示到我们的显示器上，所以我们人类也读懂了返回的结果。
> - ![image-20220826153655710](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826153655710.png)

### SQL大小写规范


#### Windows和Linux平台区别

> - 在 SQL 中，关键字和函数名是不用区分字母大小写的，比如 SELECT、WHERE、ORDER、GROUP BY 等关键字，以及 ABS、MOD、ROUND、MAX 等函数名。
>
> - windows系统默认大小写不敏感 ，但是 linux系统是大小写敏感的 。
>
> - 通过如下命令查看：
>
> ```sql
> SHOW VARIABLES LIKE '%lower_case_table_names%'
> ```
>
> - lower_case_table_names参数值的设置：
>   - 默认为0，大小写敏感 。
>   - 设置1，大小写不敏感。创建的表，数据库都是以小写形式存放在磁盘上，对于sql语句都是转换为小写对表和数据库进行查找。
>   - 设置2，创建的表和数据库依据语句上格式存放，凡是查找都是转换为小写进行。
>
> - MySQL在Linux下数据库名、表名、列名、别名大小写规则是这样的：
>   - 数据库名、表名、表的别名、变量名是严格区分大小写的；
>   - 关键字、函数名称在 SQL 中不区分大小写；
>   - 列名（或字段名）与列的别名（或字段别名）在所有的情况下均是忽略大小写的；
>
> - MySQL在Windows的环境下全部不区分大小写

#### Linux下大小写规则设置

> - 当想设置为大小写不敏感时，要在 my.cnf 这个配置文件 [mysqld] 中加入lower_case_table_names=1 ，然后重启服务器。但是要在重启数据库实例之前就需要将原来的数据库和表转换为小写，否则将找不到数据库名。
>
> - 此参数适用于MySQL5.7。在MySQL 8下禁止在重新启动 MySQL 服务时将lower_case_table_names 设置成不同于初始化 MySQL 服务时设置的lower_case_table_names 值。如果非要将MySQL8设置为大小写不敏感，具体步骤为：
>   - 停止MySQL服务 
>   - 删除数据目录，即删除 /var/lib/mysql 目录 
>   - 在MySQL配置文件（ /etc/my.cnf ）中添加 lower_case_table_names=1 
>   - 启动MySQL服务

#### SQL编写建议

> - 关键字和函数名称全部大写；
>
> - 数据库名、表名、表别名、字段名、字段别名等全部小写；
>
> - SQL 语句必须以分号结尾。

### sql_mode的合理设置

#### 宽松模式 vs 严格模式

> - `宽松模式`：如果设置的是宽松模式，那么我们在插入数据的时候，即便是给了一个错误的数据，也可能会被接受，并且不报错。
>
> - 举例：我在创建一个表时，该表中有一个字段为name，给name设置的字段类型时char(10)，如果我在插入数据的时候，其中name这个字段对应的有一条数据的长度超过了 10 ，例如'1234567890abc'，超过了设定的字段长度 10 ，那么不会报错，并且取前 10 个字符存上，也就是说你这个数据被存为了'1234567890'，而'abc'就没有了。但是，我们给的这条数据是错误的，因为超过了字段长度，但是并没有报错，并且mysql自行处理并接受了，这就是宽松模式的效果。
> - 应用场景：通过设置sql mode为宽松模式，来保证大多数sql符合标准的sql语法，这样应用在不同数据库之间进行迁移时，则不需要对业务sql 进行较大的修改。
> - `严格模式`：出现上面宽松模式的错误，应该报错才对，所以MySQL5.7版本就将sql_mode默认值改为了严格模式。所以在生产等环境中，我们必须采用的是严格模式，进而开发、测试环境的数据库也必须要设置，这样在开发测试阶段就可以发现问题。并且我们即便是用的MySQL5.6，也应该自行将其改为严格模式。
> - 开发经验：MySQL等数据库总想把关于数据的所有操作都自己包揽下来，包括数据的校验，其实开发中，我们应该在自己开发的项目程序级别将这些校验给做了，虽然写项目的时候麻烦了一些步骤，但是这样做之后，我们在进行数据库迁移或者在项目的迁移时，就会方便很多。
> - 改为严格模式后可能会存在的问题：若设置模式中包含了NO_ZERO_DATE，那么MySQL数据库不允许插入零日期，插入零日期会抛出错误而不是警告。例如，表中含字段TIMESTAMP列（如果未声明为NULL或显示DEFAULT子句）将自动分配DEFAULT '0000-00-00 00:00:00'（零时间戳），这显然是不满足sql_mode中的NO_ZERO_DATE而报错。

#### 模式查看和设置

> 查看

```sql
select @@session.sql_mode 
select @@global.sql_mode 
show variables like 'sql_mode';
```

> 临时设置

```sql
-- 改为严格模式。此方法在当前服务中生效，重启MySQL服务后失效。 
set GLOBAL sql_mode='STRICT_TRANS_TABLES';
-- 改为严格模式。此方法只在当前会话中生效，关闭当前会话就不生效了。 
set SESSION sql_mode='STRICT_TRANS_TABLES';
```

> 永久设置方式：在/etc/my.cnf中配置sql_mode，重启MySQL。当然生产环境上是禁止重启MySQL服务的，所以采用临时设置方式 + 永久设置方式来解决线上的问题，那么即便是有一天真的重启了MySQL服务，也会永久生效了
>
> ```sql
> sql_mode=ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR _DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
> ```

## MySQL的数据目录

### MySQL 8 的主要目录结构

> 安装好MySQL 8 之后，我们查看如下的目录结构：

```shell
find / -name mysql
```

#### 数据库文件的存放路径

> MySQL数据库文件的存放路径：/var/lib/mysql/
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826161503159.png" alt="image-20220826161503159" style="zoom:50%;" />

#### 相关命令目录

> 相关命令目录：/usr/bin（mysqladmin、mysqlbinlog、mysqldump等命令）和/usr/sbin

#### 配置文件目录

> 配置文件目录：/usr/share/mysql-8.0（命令及配置文件），/etc/mysql（如my.cnf）

### 数据库和文件系统的关系

#### 默认数据库

> 查看默认数据库： SHOW DATABASES; 
>
> 可以看到有 4 个数据库是属于MySQL自带的系统数据库

##### mysql

> MySQL 系统自带的核心数据库，它存储了MySQL的用户账户和权限信息，一些存储过程、事件的定义信息，一些运行过程中产生的日志信息，一些帮助信息以及时区信息等。

##### information_schema

> MySQL 系统自带的数据库，这个数据库保存着`MySQL服务器维护的所有其他数据库的信息`，比如有哪些表、哪些视图、哪些触发器、哪些列、哪些索引。这些信息并不是真实的用户数据，而是一些描述性信息，有时候也称之为元数据。在系统数据库information_schema中提供了一些以innodb_sys开头的表，用于表示内部系统表。

##### performance_schema

> MySQL 系统自带的数据库，这个数据库里主要保存`MySQL服务器运行过程中的一些状态信息`，可以用来监控 MySQL 服务的各类性能指标。包括统计最近执行了哪些语句，在执行过程的每个阶段都花费了多长时间，内存的使用情况等信息。

##### sys

> MySQL 系统自带的数据库，这个数据库主要是`通过视图的形式把information_schema和performance_schema结合起来`，帮助系统管理员和开发人员监控 MySQL 的技术性能。

#### 数据库在文件系统中的表示

> - 这个数据目录下的文件和子目录比较多，除了 information_schema 这个系统数据库外，其他的数据库在 数据目录 下都有对应的子目录。
> - 表以表名 + ".ibd"表示，如：表employees，employees.ibd

![image-20220826190933080](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826190933080.png)

![image-20220826191021124](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220826191021124.png)

#### 表在文件系统中的表示

##### InnoDB存储引擎模式

> - 表结构
>
>   MySQL5.7，为了保存表结构， InnoDB 在 数据目录 下对应的数据库子目录下创建了一个专门用于 `描述表结构的文件` ，文件名是这样：`表名.frm `，MySQL8.0中没有这个文件
>
>   比方说我们在 atguigu 数据库下创建一个名为 test 的表：那在数据库 atguigu 对应的子目录下就会创建一个名为 test.frm 的用于描述表结构的文件。.frm文件的格式在不同的平台上都是相同的。这个后缀名为.frm是以 二进制格式 存储的，我们直接打开是乱码的。
>
> - 表中数据和索引
>
>   - 系统表空间(system tablespace)
>
>     默认情况下，InnoDB会在数据目录下创建一个名为 `ibdata1 `、大小为 `12M `的文件，这个文件就是对应的 系统表空间 在文件系统上的表示。当不够用的时候它会自己增加文件大小。
>
>     当然，如果你想让系统表空间对应文件系统上多个实际文件，或者仅仅觉得原来的 ibdata1 这个文件名难听，那可以在MySQL启动时配置对应的文件路径以及它们的大小，比如我们这样修改一下my.cnf 配置文件：`innodb_data_file_path=data1:512M;data2:512M:autoextend`
>
>   - 独立表空间(file-per-table tablespace)
>
>     在`MySQL5.6.6以及之后的版本`中，InnoDB并不会默认的把各个表的数据存储到系统表空间中，而是为 每 一个表建立一个独立表空间 ，也就是说我们创建了多少个表，就有多少个独立表空间。使用 独立表空间 来存储表数据的话，会在该表所属数据库对应的子目录下创建一个表示该独立表空间的文件，文件名和表名相同，只不过添加了一个 .ibd 的扩展名而已，所以完整的文件名称长这样：表名.ibd
>
>     `MySQL8.0`将.frm合并到.ibd文件中，通过命令：`ibd2sdi --dump-file=user.txt user.ibd`将ibd文件解析成txt，可以看到表结构，但是不可以看到数据（推测是为了保密性）
>
>     比如：我们使用了 独立表空间 去存储 atguigu 数据库下的 test 表的话，那么在该表所在数据库对应的 atguigu 目录下会为 test 表创建这两个文件：test.frm 、test.ibd，其中 test.ibd 文件就用来存储 test 表中的数据和索引。
>
>   -  系统表空间与独立表空间的设置
>
>     我们可以自己指定使用 系统表空间 还是 独立表空间 来存储数据，这个功能由启动参数`innodb_file_per_table` 控制，比如说我们想刻意将表数据都存储到 系统表空间 时，可以在启动MySQL服务器的时候这样配置：`innodb_file_per_table=0 # 0：代表使用系统表空间； 1：代表使用独立表空间` 
>
>   - 其他类型的表空间
>
>     随着MySQL的发展，除了上述两种老牌表空间之外，现在还新提出了一些不同类型的表空间，比如通用表空间（general tablespace）、临时表空间（temporary tablespace）

##### MyISAM存储引擎模式

> - 表结构
>
>   在存储表结构方面， MyISAM 和 InnoDB 一样，也是在 数据目录 下对应的数据库子目录下创建了一个专门用于描述表结构的文件：表名.frm
>
> - 表中数据和索引
>
>   在MyISAM中的索引全部都是 `二级索引` ，该存储引擎的 `数据和索引是分开存放` 的。所以在文件系统中也是使用不同的文件来存储数据文件和索引文件，同时表数据都存放在对应的数据库子目录下。
>
>   MySQL5.7中，假如 test表使用MyISAM存储引擎的话，那么在它所在数据库对应的 atguigu 目录下会为 test 表创建这三个文件：`test.frm 存储表结构 、test.MYD 存储数据 (MYData) 、test.MYI 存储索引 (MYIndex) `
>
>   MySQL8.0中，为`test.sdi 存储表结构` 、test.MYD 存储数据 (MYData) 、test.MYI 存储索引 (MYIndex)

## 用户与权限管理

### 用户管理

#### 登录MySQL服务器

> 启动MySQL服务后，可以通过mysql命令来登录MySQL服务器，命令如下：
>
> ```sql
> mysql –h hostname|hostIP –P port –u username –p DatabaseName –e "SQL语句"
> ```
>
> - h参数后面接主机名或者主机IP，hostname为主机，hostIP为主机IP。
>
> - P参数后面接MySQL服务的端口，通过该参数连接到指定的端口。MySQL服务的默认端口是 3306 ，
>   不使用该参数时自动连接到 3306 端口，port为连接的端口号。
>
> - u参数后面接用户名，username为用户名。
>
> - p参数会提示输入密码。
>   DatabaseName参数指明登录到哪一个数据库中。如果没有该参数，就会直接登录到MySQL数据库
>   中，然后可以使用USE命令来选择数据库。
>
> - e参数后面可以直接加SQL语句。登录MySQL服务器以后即可执行这个SQL语句，然后退出MySQL
>   服务器。
>
>   举例：
>
>   ```sqsl
>   mysql -uroot -p -hlocalhost -P3306 mysql -e "select host,user from user"
>   ```
>
>   

#### 创建用户

> CREATE USER语句的基本语法形式如下：
>
> ```sql
> CREATE USER 用户名 [IDENTIFIED BY '密码'][,用户名 [IDENTIFIED BY '密码']];
> ```
>
> - 用户名参数表示新建用户的账户，由 用户（User） 和 主机名（Host） 构成；
>
> - “[ ]”表示可选，也就是说，可以指定用户登录时需要密码验证，也可以不指定密码验证，这样用户可以直接登录。不过，不指定密码的方式不安全，不推荐使用。如果指定密码值，这里需要使用IDENTIFIED BY指定明文密码值。
> - CREATE USER语句可以同时创建多个用户。
>
> 举例：
>
> ```sql
> CREATE USER zhang3 IDENTIFIED BY '123123'; # 默认host是 % 
> CREATE USER 'kangshifu'@'localhost' IDENTIFIED BY '123456';
> ```

#### 修改用户

```sql
UPDATE mysql.user SET USER='li4' WHERE USER='wang5'; 
FLUSH PRIVILEGES;
```

#### 删除用户

##### 方式 1 ：使用DROP方式删除（推荐）

> 使用DROP USER语句来删除用户时，必须用于DROP USER权限。DROP USER语句的基本语法形式如下：
>
> ```
> DROP USER user[,user]…; 
> ```
>
> 举例：
>
> ```sql
> DROP USER li4 ; # 默认删除host为%的用户 
> DROP USER 'kangshifu'@'localhost';
> ```

##### 方式 2 ：使用DELETE方式删除

> 注意：不推荐通过 DELETE FROM USER u WHERE USER='li4' 进行删除，系统会有残留信息保留。而drop user命令会删除用户以及对应的权限，执行命令后你会发现mysql.user表和mysql.db表的相应记录都消失了。

```sql
DELETE FROM mysql.user WHERE Host=’hostname’ AND User=’username’; 
FLUSH PRIVILEGES;
```
#### 设置当前用户密码

> - 使用ALTER USER命令来修改当前用户密码如下语句代表修改当前登录用户的密码。基本语法如下：
>
>   ```sql
>   ALTER USER USER() IDENTIFIED BY 'new_password'; 
>   ```
>
> - 使用SET语句来修改当前用户密码，`使用root用户登录MySQL`后，可以使用SET语句来修改密码，该语句会自动将密码加密后再赋给当前用户。具体SQL语句如下：
>
>   ```sql
>   SET PASSWORD='new_password';
>   ```

#### 修改其它用户密码

> - 使用ALTER语句来修改普通用户的密码
>
>   ```sql
>   ALTER USER user [IDENTIFIED BY '新密码'] [,user[IDENTIFIED BY '新密码']]…;
>   ```
>
> - 使用SET命令来修改普通用户的密码，使用`root用户`登录到MySQL服务器后，可以使用SET语句来修改普通用户的密码。SET语句的代码如下：
>   
>  ```sql
> SET PASSWORD FOR 'username'@'hostname'='new_password';
>  ```
> 
> - 使用UPDATE语句修改普通用户的密码（不推荐）
>   ```sql
>   UPDATE MySQL.user SET authentication_string=PASSWORD("123456") 
>   WHERE User = "username" AND Host = "hostname";
>   ```

#### MySQL 8 密码管理(了解)

##### 密码过期策略

> - 在MySQL中，数据库管理员可以 手动设置 账号密码过期，也可以建立一个 自动 密码过期策略。
> - 过期策略可以是 全局的 ，也可以为 每个账号 设置单独的过期策略。
>
> ```sql
> ALTER USER user PASSWORD EXPIRE; 
> ALTER USER 'kangshifu'@'localhost' PASSWORD EXPIRE;
> ```
>
> - 使用SQL语句更改该变量的值并持久化
>
>   ```sql
>   SET PERSIST default_password_lifetime = 180; # 建立全局策略，设置密码每隔180天过期
>   ```
>
> - 配置文件my.cnf中进行维护
>
>   ```sql
>   default_password_lifetime=180 #建立全局策略，设置密码每隔180天过期
>   ```
>
> - 每个账号既可延用全局密码过期策略，也可单独设置策略。在 CREATE USER 和 ALTER USER 语句上加入 PASSWORD EXPIRE 选项可实现单独设置策略。
>
>  ```sql
>   -- 设置kangshifu账号密码每90天过期： 
>   CREATE USER 'kangshifu'@'localhost' PASSWORD EXPIRE INTERVAL 90 DAY; 
>   ALTER USER 'kangshifu'@'localhost' PASSWORD EXPIRE INTERVAL 90 DAY; 
>   -- 设置密码永不过期： 
>   CREATE USER 'kangshifu'@'localhost' PASSWORD EXPIRE NEVER; 
>   ALTER USER 'kangshifu'@'localhost' PASSWORD EXPIRE NEVER; 
>   -- 延用全局密码过期策略： 
>   CREATE USER 'kangshifu'@'localhost' PASSWORD EXPIRE DEFAULT; 
>   ALTER USER 'kangshifu'@'localhost' PASSWORD EXPIRE DEFAULT;
>  ```
>

##### 密码重用策略

> 手动设置密码重用方式 1 ：全局
>
> - 使用SQL
>
>   ```sql
>   SET PERSIST password_history = 6; #设置不能选择最近使用过的6个密码
>   SET PERSIST password_reuse_interval = 365; #设置不能选择最近一年内的密码
>   ```
>
> - my.cnf配置文件
>
>   ```sql
>   password_history=6 
>   password_reuse_interval=365
>   ```
>
> 手动设置密码重用方式2：单独设置
>
> ```sql
> -- 既不能使用最近5个密码，也不能使用365天内的密码 
> CREATE USER 'kangshifu'@'localhost' 
> PASSWORD HISTORY 5 
> PASSWORD REUSE INTERVAL 365 DAY; 
> 
> ALTER USER 'kangshifu'@'localhost'
> PASSWORD HISTORY 5 
> PASSWORD REUSE INTERVAL 365 DAY; 
> ```

### 权限管理

#### 权限列表

> show privileges ;
>
> - CREATE和DROP权限，可以创建新的数据库和表，或删除（移掉）已有的数据库和表。如果将MySQL数据库中的DROP权限授予某用户，用户就可以删除MySQL访问权限保存的数据库。 
> - SELECT、INSERT、UPDATE和DELETE权限允许在一个数据库现有的表上实施操作。 
> - SELECT权限只有在它们真正从一个表中检索行时才被用到。 
> - INDEX权限允许创建或删除索引，INDEX适用于已有的表。如果具有某个表的CREATE权限，就可以在CREATE TABLE语句中包括索引定义。 
> - ALTER权限可以使用ALTER TABLE来更改表的结构和重新命名表。 
> - CREATE ROUTINE权限用来创建保存的程序（函数和程序），ALTER ROUTINE权限用来更改和删除保存的程序，EXECUTE权限用来执行保存的
>   程序。 
> - GRANT权限允许授权给其他用户，可用于数据库、表和保存的程序。 
> - FILE权限使用户可以使用LOAD DATA INFILE和SELECT ... INTO OUTFILE语句读或写服务器上的文件，任何被授予FILE权
>   限的用户都能读或写MySQL服务器上的任何文件（说明用户可以读任何数据库目录下的文件，因为服务
>   器可以访问这些文件）。

#### 授予权限的原则

> 权限控制主要是出于安全因素，因此需要遵循以下几个经验原则：
>
> 1 、只授予能满足需要的最小权限，防止用户干坏事。比如用户只是需要查询，那就只给select权限就可以了，不要给用户赋予update、insert或者delete权限。
>
> 2 、创建用户的时候限制用户的登录主机，一般是限制成指定IP或者内网IP段。
>
> 3 、为每个用户设置满足密码复杂度的密码。
>
> 4 、定期清理不需要的用户，回收权限或者删除用户。

#### 授予权限

> 给用户授权的方式有 2 种，分别是通过把 角色赋予用户给用户授权 和 直接给用户授权 。用户是数据库的使用者，我们可以通过给用户授予访问数据库中资源的权限，来控制使用者对数据库的访问，消除安全隐患。
>
> - 该权限如果发现没有该用户，则会直接新建一个用户
>
> ```sql
> GRANT 权限1,权限2,…权限n ON 数据库名称.表名称 TO 用户名@用户地址 [IDENTIFIED BY ‘密码口令’];
> ```
>
> - 举例
>
> - 给li4用户用本地命令行方式，授予atguigudb这个库下的所有表的插删改查的权限。
>
>   ```sql
>   GRANT SELECT,INSERT,DELETE,UPDATE ON atguigudb.* TO li4@localhost ;
>   ```
>
> - 授予通过网络方式登录的joe用户 ，对所有库所有表的全部权限，密码设为123。注意这里唯独`不包括grant`的权限
>
>   ```sql
>   GRANT ALL PRIVILEGES ON *.* TO joe@'%' IDENTIFIED BY '123';
>   ```

#### 查看权限

> 查看当前用户权限
>
> ```sql
> SHOW GRANTS; 
> SHOW GRANTS FOR CURRENT_USER;
> SHOW GRANTS FOR CURRENT_USER();
> ```
>
> 查看某用户的全局权限
>
> ```sql
> SHOW GRANTS FOR 'user'@'主机地址' ;
> ```

#### 收回权限

> 收回权限就是取消已经赋予用户的某些权限。 收回用户不必要的权限可以在一定程度上保证系统的安全性。 MySQL中使用REVOKE语句取消用户的某些权限。使用REVOKE收回权限之后，用户账户的记录将从db、host、tables_priv和columns_priv表中删除，但是用户账户记录仍然在user表中保存（删除user表中的账户记录使用DROP USER语句）。**注意：在将用户账户从user表删除之前，应该收回相应用户的所有权限。**

```sql
-- 注意：须用户重新登录后才能生效
REVOKE 权限1,权限2,…权限n ON 数据库名称.表名称 FROM 用户名@用户地址; 

-- 收回全库全表的所有权限 
REVOKE ALL PRIVILEGES ON *.* FROM joe@'%'; 
-- 收回mysql库下的所有表的插删改查权限 
REVOKE SELECT,INSERT,UPDATE,DELETE ON mysql.* FROM joe@localhost;
```
### 权限表

#### user表

> user表是MySQL中最重要的一个权限表，记录用户账号和权限信息，这些字段可以分成 4 类，分别是范围列（或用户列）、权限列、安全列和资源控制列。


##### 范围列（或用户列）

> - host ： 表示连接类型
>   - % 表示所有远程通过 TCP方式的连接
>   - IP 地址 如 (192.168.1.2、127.0.0.1) 通过制定ip地址进行的TCP方式的连接
>   - 机器名 通过制定网络中的机器名进行的TCP方式的连接
>   - ::1 IPv6的本地ip地址，等同于IPv4的 127.0.0.1 
>   - localhost 本地方式通过命令行方式的连接 ，比如mysql -u xxx -p xxx 方式的连接。
>
> - user ： 表示用户名，同一用户通过不同方式链接的权限是不一样的。
>
> - password ： 密码
>   - 所有密码串通过 password(明文字符串) 生成的密文字符串。MySQL 8.0 在用户管理方面增加了角色管理，默认的密码加密方式也做了调整，由之前的 SHA1 改为了 SHA2 ，不可逆 。同时加上 MySQL 5.7 的禁用用户和用户过期的功能，MySQL 在用户管理方面的功能和安全性都较之前版本大大的增强了。
>   - mysql 5.7 及之后版本的密码保存到` authentication_string` 字段中不再使用password 字段。

##### 权限列

> - Grant_priv字段：表示是否拥有GRANT权限
>
> - Shutdown_priv字段：表示是否拥有停止MySQL服务的权限
>
> - Super_priv字段：表示是否拥有超级权限
>
> - Execute_priv字段：表示是否拥有EXECUTE权限。拥有EXECUTE权限，可以执行存储过程和函数。
>
> - Select_priv , Insert_priv等：为该用户所拥有的权限。

##### 安全列

> - 安全列只有 6 个字段
> - 两个是ssl相关的（ssl_type、ssl_cipher），用于加密；
> - 两个是x相关的（x509_issuer、x509_subject），用于标识用户；
> - 另外两个Plugin字段用于验证用户身份的插件，该字段不能为空。如果该字段为空，服务器就使用内建授权验证机制验证用户身份。

##### 资源控制列

> 资源控制列的字段用来限制用户使用的资源，包含 4 个字段，分别为：
>
> ①max_questions，用户每小时允许执行的查询操作次数；
>
> ②max_updates，用户每小时允许执行的更新操作次数； 
>
> ③max_connections，用户每小时允许执行的连接操作次数； ④max_user_connections，用户允许同时建立的连接次数。

#### db表

> - 用户列：db表用户列有 3 个字段，分别是Host、User、Db。这 3 个字段分别表示主机名、用户名和数据库名。表示从某个主机连接某个用户对某个数据库的操作权限，这 3 个字段的组合构成了db表的主键。
>
> - 权限列：Create_routine_priv和Alter_routine_priv这两个字段决定用户是否具有创建和修改存储过程的权限。

#### tables_priv表和columns_priv表

>  tables_priv表用来对表设置操作权限，columns_priv表用来对表的某一列设置权限。
>
> tables_priv表有8个字段，分别是Host、Db、User、Table_name、Grantor、Timestamp、Table_priv和 Column_priv，各个字段说明如下：
>
> - Host 、 Db 、 User 和 Table_name 四个字段分别表示主机名、数据库名、用户名和表名。
> - Grantor表示修改该记录的用户。
> - Timestamp表示修改该记录的时间。
> - Table_priv 表示对象的操作权限。包括Select、Insert、Update、Delete、Create、Drop、Grant、 References、Index和Alter。 
> - Column_priv字段表示对表中的列的操作权限，包括Select、Insert、Update和References。 

#### procs_priv表

> procs_priv表可以对存储过程和存储函数设置操作权限

### 访问控制(了解)

#### 连接核实阶段

> 当用户试图连接MySQL服务器时，服务器基于用户的身份以及用户是否能提供正确的密码验证身份来确定接受或者拒绝连接。即客户端用户会在连接请求中提供用户名、主机地址、用户密码，MySQL服务器接收到用户请求后，会 **使用user表中的host、user和authentication_string这 3 个字段匹配客户端提供信息** 。
>
> 服务器只有在user表记录的Host和User字段匹配客户端主机名和用户名，并且提供正确的密码时才接受连接。 **如果连接核实没有通过，服务器就完全拒绝访问** ； **否则，服务器接受连接，然后进入阶段 2 等待用户请求。**

#### 请求核实阶段

> 一旦建立了连接，服务器就进入了访问控制的阶段 2 ，也就是请求核实阶段。对此连接上进来的每个请求，服务器检查该请求要执行什么操作、是否有足够的权限来执行它，这正是需要授权表中的权限列发挥作用的地方。这些权限可以来自user、db、table_priv和column_priv表。
>
> 确认权限时，MySQL首先`检查user表`，如果指定的权限没有在user表中被授予，那么MySQL就会继续`检查db表`，db表是下一安全层级，其中的权限限定于数据库层级，在该层级的SELECT权限允许用户查看指定数据库的所有表中的数据；如果在该层级没有找到限定的权限，则MySQL继续`检查tables_priv表以及columns_priv表`，如果所有权限表都检查完毕，但还是没有找到允许的权限操作，MySQL将返回错误信息，用户请求的操作不能执行，操作失败。

### 角色管理


#### 创建角色

> 创建角色使用CREATE ROLE语句，角色名称的命名规则和用户名类似。
>
> 如果 host_name省略，默认为% ， role_name不可省略 ，不可为空。
>
> 创建新角色，默认只有`USAGE`权限，即` 连接登录数据库的权限`

```sql
CREATE ROLE 'role_name'[@'host_name'] [,'role_name'[@'host_name']]... 
CREATE ROLE 'manager'@'localhost';
```

#### 给角色赋予权限

> 创建角色之后，默认这个角色是没有任何权限的，我们需要给角色授权。给角色授权的语法结构是：语句中privileges代表权限的名称，多个权限以逗号隔开。
>
> ```sql
> GRANT privileges ON table_name TO 'role_name'[@'host_name'];
> GRANT SELECT ON demo.settlement TO 'manager';
> ```

#### 查看角色的权限

> SHOW GRANTS FOR 'manager'; 

#### 回收角色的权限

> 角色授权后，可以对角色的权限进行维护，对权限进行添加或撤销。添加权限使用GRANT语句，与角色授权相同。撤销角色或角色权限使用REVOKE语句。修改了角色的权限，会影响拥有该角色的账户的权限。
>
> ```sql
> REVOKE privileges ON tablename FROM 'rolename';
> REVOKE INSERT, UPDATE, DELETE ON school.* FROM 'school_write';
> ```

#### 删除角色

> 当我们需要对业务重新整合的时候，可能就需要对之前创建的角色进行清理，删除一些不会再使用的角色。删除角色的操作很简单，你只要掌握语法结构就行了。注意， 如果你删除了角色，那么用户也就失去了通过这个角色所获得的所有权限 。

```sql
DROP ROLE role [,role2]... 
DROP ROLE 'school_read';
```
#### 给用户赋予角色

> 角色创建并授权后，要赋给用户并处于 激活状态 才能发挥作用。给用户添加角色可使用GRANT语句，语法形式如下：
>
> role代表角色，user代表用户。可将多个角色同时赋予多个用户，用逗号隔开即可。

```sql
GRANT role [,role2,...] TO user [,user2,...];
GRANT 'school_read' TO 'kangshifu'@'localhost';
SELECT CURRENT_ROLE();
```

#### 激活角色

> - 使用set default role命令激活角色
>
>   ```sql
>   SET DEFAULT ROLE ALL TO 'kangshifu'@'localhost';
>   ```
>
> - 将activate_all_roles_on_login设置为ON，对 所有角色永久激活 。运行这条语句之后，用户才真正拥有了赋予角色的所有权限。
>
>   ```sql
>   SET GLOBAL activate_all_roles_on_login=ON;
>   ```

#### 撤销用户的角色

```sql
REVOKE role FROM user;
REVOKE 'school_read' FROM 'kangshifu'@'localhost';
```

#### 设置强制角色(mandatory role)

> - 方式1：服务启动前设置
>
>   ```
>   mandatory_roles='role1,role2@localhost,r3@%.atguigu.com' 
>   ```
>
> - 方式2：运行时设置
>
> ```sql
> SET PERSIST mandatory_roles = 'role1,role2@localhost,r3@%.example.com'; #系统重启后仍然 有效
> SET GLOBAL mandatory_roles = 'role1,role2@localhost,r3@%.example.com'; #系统重启后失效
> ```

## 逻辑架构

### 逻辑架构剖析

#### 服务器处理客户端请求

> 那服务器进程对客户端进程发送的请求做了什么处理，才能产生最后的处理结果呢？这里以查询请求为例展示：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830102121836.png" alt="image-20220830102121836" style="zoom:50%;" />
>
> 下面具体展开看一下：
>
> ![image-20220830102151499](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830102151499.png)
>
> ![image-20220830092250214](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830092250214.png)


#### Connectors

#### 第 1 层：连接层

> 系统（客户端）访问MySQL服务器前，做的第一件事就是`建立TCP连接`。经过三次握手建立连接成功后，MySQL服务器对TCP传输过来的账号密码做身份认证、权限获取。
>
> - 用户名或密码不对，会收到一个Access denied for user错误，客户端程序结束执行
>
> - 用户名密码认证通过，会从权限表查出账号拥有的权限与连接关联，之后的权限判断逻辑，都将依赖于此时读到的权限
>
> TCP连接收到请求后，必须要分配给一个线程专门与这个客户端的交互。所以还会有个线程池，去走后面的流程。每一个连接从线程池中获取线程，省去了创建和销毁线程的开销。

#### 第 2 层：服务层

> - **SQL Interface: SQL接口**
>   - 接收用户的SQL命令，并且返回用户需要查询的结果。比如SELECT ... FROM就是调用SQL Interface 
>   - MySQL支持DML（数据操作语言）、DDL（数据定义语言）、存储过程、视图、触发器、自定义函数等多种SQL语言接口
>
> - **Parser:** **解析器**
>   - 在解析器中对 SQL 语句进行语法分析、语义分析。将SQL语句分解成数据结构，并将这个结构传递到后续步骤，以后SQL语句的传递和处理就是基于这个结构的。如果在分解构成中遇到错误，那么就说明这个SQL语句是不合理的。
>   - 在SQL命令传递到解析器的时候会被解析器验证和解析，并为其创建 `语法树` ，并根据数据字典丰富查询语法树，会 `验证该客户端是否具有执行该查询的权限` 。创建好语法树后，MySQL还会对SQl查询进行语法上的优化，进行查询重写。 
>
> - **Optimizer:** **查询优化器**
>   - SQL语句在语法解析之后、查询之前会使用查询优化器确定 SQL 语句的执行路径，生成一个执行计划 。
>   - 这个执行计划表明应该 使用哪些索引 进行查询（全表检索还是使用索引检索），表之间的连接顺序如何，最后会按照执行计划中的步骤调用存储引擎提供的方法来真正的执行查询，并将查询结果返回给用户。
>   - 它使用`“ 选取-投影-连接 ”`策略进行查询。例如：`SELECT id,name FROM student WHERE gender = '女';`这个SELECT查询先根据WHERE语句进行 选取 ，而不是将表全部查询出来以后再进行gender过滤。 这个SELECT查询先根据id和name进行属性 投影 ，而不是将属性全部取出以后再进行过滤，将这两个查询条件 连接 起来生成最终查询结果。
>
> - **Caches & Buffers** **： 查询缓存组件**
>   - MySQL内部维持着一些Cache和Buffer，比如Query Cache用来缓存一条SELECT语句的执行结果，如果能够在其中找到对应的查询结果，那么就不必再进行查询解析、优化和执行的整个过程了，直接将结果反馈给客户端。
>   - 这个缓存机制是由一系列小缓存组成的。比如表缓存，记录缓存，key缓存，权限缓存等 。
>   - 这个查询缓存可以在 不同客户端之间共享 。 
>   - 从MySQL 5.7.20开始，不推荐使用查询缓存，并在` MySQL 8.0中删除 `

#### 第 3 层：引擎层

> 插件式存储引擎层（ Storage Engines）， **真正的负责了MySQL中数据的存储和提取，对物理服务器级别维护的底层数据执行操作** ，服务器通过API与存储引擎进行通信。不同的存储引擎具有的功能不同，这样我们可以根据自己的实际需要进行选取。

#### 存储层

> 所有的数据，数据库、表的定义，表的每一行的内容，索引，都是存在文件系统上，以文件的方式存在的，并完成与存储引擎的交互。当然有些存储引擎比如InnoDB，也支持不使用文件系统直接管理裸设备，但现代文件系统的实现使得这样做没有必要了。在文件系统之下，可以使用本地磁盘，可以使用DAS、NAS、SAN等各种存储系统。

#### 小结

> MySQL架构图本节开篇所示。下面为了熟悉SQL执行流程方便，我们可以简化如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830110559777.png" alt="image-20220830110559777" style="zoom:50%;" />
>
> 1. 连接层：客户端和服务器端建立连接，客户端发送 SQL 至服务器端；
>
> 2. SQL 层（服务层）：对 SQL 语句进行查询处理；与数据库文件的存储方式无关；
>
> 3. 存储引擎层：与数据库文件打交道，负责数据的存储和读取。

### SQL执行流程

#### MySQL 中的 SQL执行流程

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830110737318.png" alt="image-20220830110737318" style="zoom:50%;" />

**MySQL的查询流程：**

**1. 查询缓存** ：Server 如果在查询缓存中发现了这条 SQL 语句，就会直接将结果返回给客户端；如果没有，就进入到解析器阶段。`MySQL8.0 抛弃了缓存功能`原因：1⃣️只有相同的查询操作才会命中查询缓存。两个查询请求在任何字符上的不同（例如：空格、注释、大小写），都会导致缓存不会命中。因此 MySQL 的查询缓存命中率不高。2⃣️如果查询请求中包含某些系统函数、用户自定义变量和函数、一些系统表，如 mysql 、information_schema、 performance_schema 数据库中的表，那这个请求就不会被缓存，如：NOW函数。3⃣️一致性问题：MySQL的缓存系统会监测涉及到的每张表，只要该表的结构或者数据被修改，如对该表使用了INSERT、 UPDATE、DELETE、TRUNCATE TABLE、ALTERTABLE、DROP TABLE或 DROP DATABASE语句，那使用该表的所有高速缓存查询都将变为无效并从高速缓存中删除。

**2. 解析器** ：分析器先做`“词法分析”`。你输入的是由多个字符串和空格组成的一条 SQL 语句，MySQL 需要识别出里面的字符串分别是什么，代表什么。 MySQL 从你输入的"select"这个关键字识别出来，这是一个查询语句。它也要把字符串“T”识别成“表名 T”，把字符串“ID”识别成“列 ID”。接着，要做`“语法分析”`。根据词法分析的结果，语法分析器（比如：Bison）会根据语法规则，判断你输入的这个 SQL 语句是否满足 MySQL 语法。如果SQL语句正确，则会生成一个这样的语法树：

![image-20220830111809335](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830111809335.png)

**3. 优化器** ：在查询优化器中，可以分为` 逻辑查询 优化阶段和 物理查询 优化`阶段。在优化器中会确定 SQL 语句的执行路径，比如是根据全表检索，还是根据索引检索等。

**4. 执行器 **：在执行之前需要判断该用户是否 `具备权限` 。如果没有，就会返回权限错误。如果具备权限，就执行 SQL查询并返回结果。在 MySQL8.0 以下的版本，如果设置了查询缓存，这时会将查询结果进行缓存。

> SQL 语句在 MySQL 中的流程是：SQL语句→查询缓存→解析器→优化器→执行器。

![image-20220830112525567](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830112525567.png)

#### SQL执行过程

> - 确认profiling 是否开启，profiling=0 代表关闭，我们需要把 profiling 打开，即设置为 1 
>
> ```sql
> select @@profiling;
> set profiling=1;
> ```
>
> - 查看profile，显示执行计划，查看程序的执行步骤
>
>   ```sql
>   -- 查询所有
>   show profiles;
>   -- 查询最近一次
>   show profile;
>   -- 根据id查询 
>   show profile for query 7;
>   ```
>
> - MySQL5.7中SQL执行过程（使用缓存）
>
>   ```sql
>   -- 开启缓存：在 /etc/my.cnf 中新增一行：
>   query_cache_type=1
>   ```

#### SQL语法顺序

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830144949350.png" alt="image-20220830144949350" style="zoom:50%;" />

#### Oracle中的SQL执行流程(了解)

> Oracle 中采用了`共享池`来判断 SQL 语句是否存在缓存和执行计划，通过这一步骤我们可以知道应该采用硬解析还是软解析。
>
> 我们先来看下 SQL 在 Oracle 中的执行过程：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220830145519008.png" alt="image-20220830145519008" style="zoom:50%;" />
>
> **1 .语法检查：** 检查 SQL 拼写是否正确，如果不正确，Oracle 会报语法错误。
>
> **2 .语义检查：** 检查 SQL 中的访问对象是否存在。比如我们在写 SELECT 语句的时候，列名写错了，系统就会提示错误。语法检查和语义检查的作用是保证 SQL 语句没有错误。
>
> **3 .权限检查：** 看用户是否具备访问该数据的权限。
>
> **4.共享池检查：** 共享池（Shared Pool）是一块内存池， **最主要的作用是缓存 SQL 语句和该语句的执行计划。** Oracle 通过检查共享池是否存在 SQL 语句的执行计划，来判断进行软解析，还是硬解析。在共享池中，Oracle 首先对 SQL 语句进行 Hash 运算，然后根据 Hash 值在库缓存（Library Cache）中查找，如果存在 SQL 语句的执行计划，就直接拿来执行，直接进入“执行器”的环节，这就是软解析。如果没有找到 SQL 语句和执行计划，Oracle 就需要创建解析树进行解析，生成执行计划，进入“优化器”这个步骤，这就是硬解析。
>
> **5. 优化器**：优化器中就是要进行硬解析，也就是决定怎么做，比如创建解析树，生成执行计划。
>
> **6. 执行器：**当有了解析树和执行计划之后，就知道了 SQL 该怎么被执行，这样就可以在执行器中执行语句了。
>
> 共享池是 Oracle 中的术语，包括了`库缓存，数据字典缓冲区`等。我们上面已经讲到了`库缓存区`，它主要缓存 SQL 语句和执行计划。而`数据字典缓冲区`存储的是 Oracle 中的对象定义，比如表、视图、索引等对象。当对 SQL 语句进行解析的时候，如果需要相关的数据，会从数据字典缓冲区中提取。
>
> 库缓存这一个步骤，决定了 SQL 语句是否需要进行硬解析。为了提升 SQL 的执行效率，我们应该尽量避免硬解析，因为在 SQL 的执行过程中，创建解析树，生成执行计划是很消耗资源的。
>
> 你可能会问，如何避免硬解析，尽量使用软解析呢？在 Oracle 中，绑定变量是它的一大特色。绑定变量就是在 SQL 语句中使用变量，通过不同的变量取值来改变 SQL 的执行结果。这样做的好处是能提升软解析的可能性，不足之处在于可能会导致生成的执行计划不够优化，因此是否需要绑定变量还需要视情况而定。
>
> 举个例子，我们可以使用下面的查询语句：
>
> ```sql
> select * from player where player_id = 10001;
> ```
>
> ```sql
> select * from player where player_id = :player_id;
> ```
>
> 这两个查询语句的效率在 Oracle 中是完全不同的。如果你在查询 player_id = 10001 之后，还会查询10002 、 10003 之类的数据，那么每一次查询都会创建一个新的查询解析。而第二种方式使用了绑定变量，那么在第一次查询之后，在共享池中就会存在这类查询的执行计划，也就是软解析。
>
> 因此， **我们可以通过使用绑定变量来减少硬解析，减少 Oracle 的解析工作量。** 但是这种方式也有缺点，使用动态 SQL 的方式，因为参数不同，会导致 SQL 的执行效率不同，同时 SQL 优化也会比较困难。
>
> **Oracle的架构图：**
>
> <img src="/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20220831093816549.png" alt="image-20220831093816549" style="zoom:50%;" />
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831093836829.png" alt="image-20220831093836829" style="zoom:50%;" />

##### 小结：

Oracle 和 MySQL 在进行 SQL 的查询上面有软件实现层面的差异。Oracle 提出了共享池的概念，通过共享池来判断是进行软解析，还是硬解析。

### 数据库缓冲池(buffer pool)

> InnoDB存储引擎是以页为单位来管理存储空间的，我们进行的增删改查操作其实本质上都是在访问页面（包括读页面、写页面、创建新页面等操作）。而磁盘 I/O 需要消耗的时间很多，而在内存中进行操作，效率则会高很多，为了能让数据表或者索引中的数据随时被我们所用，DBMS 会申请占用内存来作为数据缓冲池，在真正访问页面之前，需要把在磁盘上的页缓存到内存中的Buffer Pool之后才可以访问。
>
> 这样做的好处是可以让磁盘活动最小化，从而减少与磁盘直接进行 I/O 的时间。要知道，这种策略对提升 SQL 语句的查询性能来说至关重要。如果索引的数据在缓冲池里，那么访问的成本就会降低很多。

#### 缓冲池 vs 查询缓存

##### 缓冲池（Buffer Pool）

首先我们需要了解在 InnoDB 存储引擎中，缓冲池都包括了哪些。在 InnoDB 存储引擎中有一部分数据会放到内存中，缓冲池则占了这部分内存的大部分，它用来存储各种数据的缓存，如下图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831101107786.png" alt="image-20220831101107786" style="zoom:50%;" />**

**缓存原则：**“位置 * 频次”这个原则，可以帮我们对 I/O 访问效率进行优化。

首先，位置决定效率，提供缓冲池就是为了在内存中可以直接访问数据。其次，频次决定优先级顺序。因为缓冲池的大小是有限的，比如磁盘有 200 G，但是内存只有 16 G，缓冲池大小只有 1 G，就无法将所有数据都加载到缓冲池里，这时就涉及到优先级顺序，会优先对使用频次高的热数据进行加载。


##### 查询缓存

> 查询缓存是提前把查询结果缓存起来，这样下次不需要执行就可以直接拿到结果。需要说明的是，在MySQL 中的查询缓存，不是缓存查询计划，而是查询对应的结果。因为命中条件苛刻，而且只要数据表发生变化，查询缓存就会失效，因此命中率低。

#### 缓冲池如何读取数据

缓冲池管理器会尽量将经常使用的数据保存起来，在数据库进行页面读操作的时候，首先会判断该页面是否在缓冲池中，如果存在就直接读取，如果不存在，就会通过内存或磁盘将页面存放到缓冲池中再进行读取。缓存在数据库中的结构和作用如下图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831101627185.png" alt="image-20220831101627185" style="zoom:50%;" />

#### 查看/设置缓冲池的大小

如果你使用的是 InnoDB 存储引擎，可以通过查看 innodb_buffer_pool_size 变量来查看缓冲池的大小。

```sql
show variables like 'innodb_buffer_pool_size';
```

你能看到此时 InnoDB 的缓冲池大小只有 134217728 / 1024 / 1024 = 128 MB。我们可以修改缓冲池大小，比如改为 256 MB，方法如下：

```sql
set global innodb_buffer_pool_size = 268435456 ;

-- 或者修改my.cnf配置文件
[server] 
innodb_buffer_pool_size = 268435456
```

#### 多个Buffer Pool实例

我们看下如何查看缓冲池的个数，使用命令：

```sql
show variables like 'innodb_buffer_pool_instances';
```

修改缓冲池的个数

```sql
[server] 
innodb_buffer_pool_instances = 2
```

那每个Buffer Pool实例实际占多少内存空间是总共的大小除以实例的个数，结果就是每个Buffer Pool实例占用的大小。

#### 引申问题

Buffer Pool是MySQL内存结构中十分核心的一个组成，你可以先把它想象成一个黑盒子。**黑盒下的更新数据流程**

![image-20220831102507608](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831102507608.png)

我更新到一半突然发生错误了，想要回滚到更新之前的版本，该怎么办？连数据持久化的保证、事务回滚都做不到还谈什么崩溃恢复？

答案： **Redo Log & Undo Log**

## 存储引擎

> 存储引擎是负责对表中的数据进行提取和写入工作的，我们可以为不同的表设置不同的存储引擎，也就是说不同的表可以有不同的物理存储结构，不同的提取和写入方式。

### 查看存储引擎

```sql
show engines;
show engines \G;
```
### 设置系统默认的存储引擎

#### 查看默认的存储引擎

```sql
show variables like '%storage_engine%';
SELECT @@default_storage_engine;
```

#### 修改默认的存储引擎

如果在创建表的语句中没有显式指定表的存储引擎的话，那就会默认使用InnoDB作为表的存储引擎。修改表的默认存储引擎

```sql
SET DEFAULT_STORAGE_ENGINE=MyISAM;

-- 修改配置文件
default-storage-engine=MyISAM
-- 重启服务 
systemctl restart mysqld.service
```

### 设置表的存储引擎

#### 创建表时指定存储引擎

我们之前创建表的语句都没有指定表的存储引擎，那就会使用默认的存储引擎InnoDB。如果我们想显式的指定一下表的存储引擎，那可以这么写：

```sql
CREATE TABLE 表名( 建表语句; ) 
ENGINE = 存储引擎名称;
```

#### 修改表的存储引擎

```sql
ALTER TABLE 表名 ENGINE = 存储引擎名称;
alter table test_engine engine = myisam;
```

### 引擎介绍

#### InnoDB 引擎：外键、支持事务、行级锁的存储引擎

> - MySQL从3.23.34a开始就包含InnoDB存储引擎。`大于等于5.5之后，默认采用InnoDB引擎`。
> - InnoDB是MySQL的默认事务型引擎，它被设计用来处理大量的短期(short-lived)事务。可以确保事务的完整提交(Commit)和回滚(Rollback)。
> - 除了增加和查询外，还需要更新、删除操作，那么，应优先选择InnoDB存储引擎。
> - 数据文件结构：（在《第 02 章_MySQL数据目录》章节已讲）
>   表名.frm 存储表结构（MySQL8.0时，合并在表名.ibd中）
>   表名.ibd 存储数据和索引
> - InnoDB是为处理巨大数据量的最大性能设计。
>   - 在以前的版本中，字典数据以元数据文件、非事务表等来存储。现在这些元数据文件被删除了。比如：.frm， .par，.trn， .isl，.db.opt等都在MySQL8.0中不存在了。
> - 对比MyISAM的存储引擎，InnoDB写的处理效率差一些，并且会占用更多的磁盘空间以保存数据和索引。
> - MyISAM只缓存索引，不缓存真实数据；InnoDB不仅缓存索引还要缓存真实数据，`对内存要求较高`，而且内存大小对性能有决定性的影响。因为InnoDB的索引和数据都放在一个文件中：ibd文件

#### MyISAM 引擎：主要的非事务处理存储引擎

> - MyISAM提供了大量的特性，包括全文索引、压缩、空间函数(GIS)等，但MyISAM`不支持事务、行级锁、外键`，有一个毫无疑问的缺陷就是`崩溃后无法安全恢复`。5.5之前默认的存储引擎
> - 优势是访问的`速度快`，对事务完整性没有要求或者以SELECT、INSERT为主的应用
> - 针对数据统计有额外的常数存储。故而 `count(*) 的查询效率很高`
> - 数据文件结构：（在《第 02 章_MySQL数据目录》章节已讲）
>   表名.frm 存储表结构
>   表名.MYD 存储数据 (MYData)
>   表名.MYI 存储索引 (MYIndex)
> - 应用场景：只读应用或者以读为主的业务
> - 对比InnoDB
> - <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831111258573.png" alt="image-20220831111258573" style="zoom:50%;" />

#### Archive 引擎：用于数据存档 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831143544491.png" alt="image-20220831143544491" style="zoom:50%;" />

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831111713331.png" alt="image-20220831111713331" style="zoom:33%;" />

#### Blackhole 引擎：丢弃写操作，读操作会返回空内容

#### CSV 引擎：存储数据时，以逗号分隔各个数据项

> 创建CSV表还会创建相应的 元文件 ，用于 `存储表的状态 和 表中存在的行数 `。此文件的名称与表的名称相同，后缀为 CSM 。
>
> 如果检查 test.CSV 通过执行上述语句创建的数据库目录中的文件，其内容使用Notepad++打开如下：
>
> ```sql
> "1","record one" 
> "2","record two"
> ```
>
> 这种格式可以被 Microsoft Excel 等电子表格应用程序读取，甚至写入

```sql
CREATE TABLE test_CSV (i INT NOT NULL, c CHAR(10) NOT NULL) ENGINE = CSV;
INSERT INTO test_CSV VALUES( 1 ,'record one'),( 2 ,'record two');
select * from test_CSV;
```

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831112219194.png" alt="image-20220831112219194" style="zoom:50%;" />

#### Memory 引擎：置于内存的表

- **概述：**

  Memory采用的逻辑介质是内存，响应速度很快，但是当mysqld守护进程崩溃的时候数据会丢失。另外，要求存储的数据是数据长度不变的格式，比如，Blob和Text类型的数据不可用(长度不固定的)。

- **主要特征：**

  Memory同时支持哈希（HASH）索引和B+树索引。
  Memory表至少比MyISAM表要快一个数量级。
  MEMORY表的大小是受到限制的。表的大小主要取决于两个参数，分别是max_rows和max_heap_table_size。其中，max_rows可以在创建表时指定；max_heap_table_size的大小默认为16MB，可以按需要进行扩大。
  数据文件与索引文件分开存储。
  缺点：其数据易丢失，生命周期短。基于这个缺陷，选择MEMORY存储引擎时需要特别小心。

- **使用Memory存储引擎的场景：**
  1. 目标数据比较小，而且非常频繁的进行访问，在内存中存放数据，如果太大的数据会造成内存溢出。可以通过参数max_heap_table_size控制Memory表的大小，限制Memory表的最大的大小。
  2. 如果数据是临时的，而且必须立即可用得到，那么就可以放在内存中。
  3. 存储在Memory表中的数据如果突然间丢失的话也没有太大的关系。

#### Federated 引擎：访问远程表

Federated引擎是访问其他MySQL服务器的一个代理，尽管该引擎看起来提供了一种很好的跨服务器的灵活性，但也经常带来问题，因此默认是禁用的。

#### Merge引擎：管理多个MyISAM表构成的表集合

#### NDB引擎：MySQL集群专用存储引擎

也叫做 NDB Cluster 存储引擎，主要用于 MySQL Cluster 分布式集群环境，类似于 Oracle 的 RAC 集群。

#### 引擎对比

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831114348696.png" alt="image-20220831114348696" style="zoom:45%;" />

### 阿里巴巴、淘宝用哪个

> Percona 为 MySQL 数据库服务器进行了改进，在功能和性能上较 MySQL 有很显著的提升。
>
> 该版本提升了在高负载情况下的 InnoDB 的性能、为 DBA 提供一些非常有用的性能诊断工具；另外有更多的参数和命令来控制服务器行为。
>
> 该公司新建了一款存储引擎叫Xtradb完全可以替代Innodb，并且在性能和并发上做得更好
>
> 阿里巴巴大部分mysql数据库其实使用的percona的原型加以修改。

### 课外补充：

#### InnoDB表的优势

> InnoDB存储引擎在实际应用中拥有诸多优势，比如操作便利、提高了数据库的性能、维护成本低等。如果由于硬件或软件的原因导致服务器崩溃，那么在重启服务器之后不需要进行额外的操作。InnoDB崩溃恢复功能自动将之前提交的内容定型，然后撤销没有提交的进程，重启之后继续从崩溃点开始执行。
>
> InnoDB存储引擎在主内存中维护缓冲池，高频率使用的数据将在内存中直接被处理。这种缓存方式应用于多种信息，加速了处理进程。在专用服务器上，物理内存中高达80%的部分被应用于缓冲池。如果需要将数据插入不同的表中，可以设置外键加强数据的完整性。更新或者删除数据，关联数据将会被自动更新或删除。如果试图将数据插入从表，但在主表中没有对应的数据，插入的数据将被自动移除。如果磁盘或内存中的数据出现崩溃，在使用脏数据之前，校验和机制会发出警告。当每个表的主键都设置合理时，与这些列有关的操作会被自动优化。插入、更新和删除操作通过做改变缓冲自动机制进行优化。 InnoDB不仅支持当前读写，也会 缓冲改变的数据到数据流磁盘 。 
>
> InnoDB的性能优势不只存在于长时运行查询的大型表。在同一列多次被查询时，自适应哈希索引会提高查询的速度。使用InnoDB可以压缩表和相关的索引，可以 在不影响性能和可用性的情况下创建或删除索 引 。对于大型文本和BLOB数据，使用动态行形式，这种存储布局更高效。通过查询INFORMATION_SCHEMA库中的表可以监控存储引擎的内部工作。在同一个语句中，InnoDB表可以与其他存储引擎表混用。即使有些操作系统限制文件大小为2GB，InnoDB仍然可以处理。 当处理大数据量时， InnoDB兼顾CPU，以达到最大性能 。 

#### InnoDB和ACID模型

ACID模型是一系列数据库设计规则，这些规则着重强调可靠性，而可靠性对于商业数据和任务关键型应用非常重要。MySQL包含类InnoDB存储引擎的组件，与ACID模型紧密相连，这样出现意外时，数据不会崩溃，结果不会失真。如果依赖ACID模型，可以不使用一致性检查和崩溃恢复机制。如果拥有额外的软件保护，极可靠的硬件或者应用可以容忍一小部分的数据丢失和不一致，可以将MySQL设置调整为只依赖部分ACID特性，以达到更高的性能。下面讲解InnoDB存储引擎与ACID模型相同作用的四个方面。

**1. 原子方面** ACID的原子方面主要涉及InnoDB事务，与MySQL相关的特性主要包括：

自动提交设置。
COMMIT语句。
ROLLBACK语句。
操作INFORMATION_SCHEMA库中的表数据。

**2. 一致性方面** ACID模型的一致性主要涉及保护数据不崩溃的内部InnoDB处理过程，与MySQL相关的特性主要包括：

InnoDB双写缓存。
InnoDB崩溃恢复。

**3. 隔离方面** 隔离是应用于事务的级别，与MySQL相关的特性主要包括：

自动提交设置。
SET ISOLATION LEVEL语句。
InnoDB锁的低级别信息。

**4. 耐久性方面** ACID模型的耐久性主要涉及与硬件配置相互影响的MySQL软件特性。由于硬件复杂多样化，耐久性方面没有具体的规则可循。与MySQL相关的特性有：

InnoDB双写缓存，通过innodb_doublewrite配置项配置。
配置项innodb_flush_log_at_trx_commit。
配置项sync_binlog。
配置项innodb_file_per_table。
存储设备的写入缓存。
存储设备的备用电池缓存。
运行MySQL的操作系统。
持续的电力供应。
备份策略。
对分布式或托管的应用，最主要的在于硬件设备的地点以及网络情况。

#### InnoDB架构

**1.** **缓冲池** 缓冲池是主内存中的一部分空间，用来缓存已使用的表和索引数据。缓冲池使得经常被使用的数据能够直接在内存中获得，从而提高速度。

**2.** **更改缓存** 更改缓存是一个特殊的数据结构，当受影响的索引页不在缓存中时，更改缓存会缓存辅助索引页的更改。索引页被其他读取操作时会加载到缓存池，缓存的更改内容就会被合并。不同于集群索引，辅助索引并非独一无二的。当系统大部分闲置时，清除操作会定期运行，将更新的索引页刷入磁盘。更新缓存合并期间，可能会大大降低查询的性能。在内存中，更新缓存占用一部分InnoDB缓冲池。在磁盘中，更新缓存是系统表空间的一部分。更新缓存的数据类型由innodb_change_buffering配置项管理。

**3.** **自适应哈希索引** 自适应哈希索引将负载和足够的内存结合起来，使得InnoDB像内存数据库一样运行，不需要降低事务上的性能或可靠性。这个特性通过innodb_adaptive_hash_index选项配置，或者通过-- skip-innodb_adaptive_hash_index命令行在服务启动时关闭。

**4.** **重做日志缓存** 重做日志缓存存放要放入重做日志的数据。重做日志缓存大小通过innodb_log_buffer_size配置项配置。重做日志缓存会定期地将日志文件刷入磁盘。大型的重做日志缓存使得大型事务能够正常运行而不需要写入磁盘。

**5.** **系统表空间** 系统表空间包括InnoDB数据字典、双写缓存、更新缓存和撤销日志，同时也包括表和索引数据。多表共享，系统表空间被视为共享表空间。

**6.** **双写缓存** 双写缓存位于系统表空间中，用于写入从缓存池刷新的数据页。只有在刷新并写入双写缓存后，InnoDB才会将数据页写入合适的位置。

**7.** **撤销日志** 撤销日志是一系列与事务相关的撤销记录的集合，包含如何撤销事务最近的更改。如果其他事务要查询原始数据，可以从撤销日志记录中追溯未更改的数据。撤销日志存在于撤销日志片段中，这些片段包含于回滚片段中。

**8.** **每个表一个文件的表空间** 每个表一个文件的表空间是指每个单独的表空间创建在自身的数据文件中，而不是系统表空间中。这个功能通过innodb_file_per_table配置项开启。每个表空间由一个单独的.ibd数据文件代表，该文件默认被创建在数据库目录中。

**9.** **通用表空间** 使用CREATE TABLESPACE语法创建共享的InnoDB表空间。通用表空间可以创建在MySQL数据目录之外能够管理多个表并支持所有行格式的表。

**10.** **撤销表空间** 撤销表空间由一个或多个包含撤销日志的文件组成。撤销表空间的数量由innodb_undo_tablespaces配置项配置。

**11.** **临时表空间** 用户创建的临时表空间和基于磁盘的内部临时表都创建于临时表空间。innodb_temp_data_file_path配置项定义了相关的路径、名称、大小和属性。如果该值为空，默认会在innodb_data_home_dir变量指定的目录下创建一个自动扩展的数据文件。

**12.** **重做日志** 重做日志是基于磁盘的数据结构，在崩溃恢复期间使用，用来纠正数据。正常操作期间，重做日志会将请求数据进行编码，这些请求会改变InnoDB表数据。遇到意外崩溃后，未完成的更改会自动在初始化期间重新进行



## 索引的数据结构


### 索引概述

MySQL官方对索引的定义为： **索引（Index）是帮助MySQL高效获取数据的数据结构** 。

**索引的本质：** 索引是数据结构。你可以简单理解为“`排好序的快速查找数据结构`”，满足特定查找算法。这些数据结构以某种方式指向数据， 这样就可以在这些数据结构的基础上实现高级查找算法。索引是在存储引擎中实现的，每个存储引擎支持的索引不一定完全相同，存储引擎可以定义每个表的最大索引数和最大索引长度，最大索引数至少为16，最大索引长度为256字节

### 索引及其优缺点

#### 优点

> （1）类似大学图书馆建书目索引，提高数据检索的效率，`降低 数据库的IO成本` ，这也是创建索引最主要的原因。 
>
> （2）通过创建`唯一索引，可以保证数据库表中每一行 数据的唯一性 `。 
>
> （3）在实现数据的参考完整性方面，可以 `加速表和表之间的连接` 。换句话说，对于有依赖关系的子表和父表联合查询时，可以提高查询速度。 
>
> （4）在使用分组和排序子句进行数据查询时，可以显著 `减少查询中分组和排序的时间` ，降低了CPU的消耗。

#### 缺点

> （1）创建索引和维护索引要` 耗费时间` ，并且随着数据量的增加，所耗费的时间也会增加。 
>
> （2）索引需要占 `磁盘空间` ，除了数据表占数据空间之外，每一个索引还要占一定的物理空间， 存储在磁盘上 ，如果有大量的索引，索引文件就可能比数据文件更快达到最大文件尺寸。 
>
> （3）虽然索引大大提高了查询速度，同时却会` 降低更新表的速度 `。当对表中的数据进行增加、删除和修改的时候，索引也要动态地维护，这样就降低了数据的维护速度。
>
> 提示：如果频繁更新表的时候，可以先将索引删除，在进行频繁更新，更新完后在添加索引

### InnoDB中索引的推演

#### 索引之前的查找

> 在没有索引的情况下，不论是根据主键列或者其他列的值进行查找，由于我们并不能快速的定位到记录所在的页，所以只能 `从第一个页 沿着 双向链表 一直往下找`，在每一个页中根据我们上面的查找方式去查找指定的记录。因为要遍历所有的数据页，所以这种方式显然是 超级耗时 的。如果一个表有一亿条记录呢？此时 索引 应运而生。

#### 设计索引

##### 建一个表

这个新建的index_demo表中有 2 个INT类型的列， 1 个CHAR(1)类型的列，而且我们规定了c1列为主键，这个表使用`Compact行格式`来实际存储记录的。这里我们简化了index_demo表的行格式示意图：

```sql
CREATE TABLE index_demo(
	c1 INT,
	c2 INT,
	c3 CHAR( 1 ),
	PRIMARY KEY(c1)
) ROW_FORMAT = Compact;
```
<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831165205332.png" alt="image-20220831165205332" style="zoom:50%;" />

> record_type：记录头信息的一项属性，表示记录的类型， 0 表示普通记录、 2 表示最小记录、 3 表示最大记录、 1 目录项纪录
> next_record：记录头信息的一项属性，表示下一条地址相对于本条记录的地址偏移量，我们用箭头来表明下一条记录是谁。
> 各个列的值：这里只记录在index_demo表中的三个列，分别是c1、c2和c3。
> 其他信息：除了上述 3 种信息以外的所有信息，包括其他隐藏列的值以及记录的额外信息。

把一些记录放到页里的示意图就是：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220831165437151.png" alt="image-20220831165437151" style="zoom:50%;" />


##### 一个简单的索引设计方案

为快速定位记录所在的数据页而 建 立一个目录 ，建这个目录必须完成下边这些事：

- **下一个数据页中用户记录的主键值必须大于上一个页中用户记录的主键值。**

- **给所有的页建立一个目录项。**

所以我们为上边几个页做好的目录就像这样子：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901153301855.png" alt="image-20220901153301855" style="zoom:50%;" />

以 页28 为例，它对应 目录项2 ，这个目录项中包含着该页的页号 28 以及该页中用户记录的最小主键值 5 。我们只需要把几个目录项在物理存储器上连续存储（比如：数组），就可以实现根据主键值快速查找某条记录的功能了。比如：查找主键值为 20 的记录，具体查找过程分两步：

1. 先从目录项中根据 二分法 快速确定出主键值为 20 的记录在 目录项3 中（因为 12 < 20 < 209 ），它对应的页是 页9 。 

2. 再根据前边说的在页中查找记录的方式去 页9 中定位具体的记录。

至此，针对数据页做的简易目录就搞定了。这个目录有一个别名，称为 索引 。

##### InnoDB中的索引方案

###### ① 迭代 1 次：目录项纪录的页

我们把前边使用到的目录项放到数据页中的样子就是这样：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901153649506.png" alt="image-20220901153649506" style="zoom:50%;" />

从图中可以看出来，我们新分配了一个编号为 30 的页来专门存储目录项记录。这里再次强调目录项记录和普通的用户记录的 `不同点` ：

目录项记录的record_type值是 1 ，而普通用户记录的record_type值是 0 。
目录项记录只有主键值和页的编号两个列，而普通的用户记录的列是用户自己定义的，可能包含很多列，另外还有InnoDB自己添加的隐藏列。
了解：记录头信息里还有一个叫min_rec_mask的属性，只有在存储目录项记录的页中的主键值最小的目录项记录的min_rec_mask值为 1 ，其他别的记录的min_rec_mask值都是 0 。

`相同点`： 两者用的是一样的数据页，都会为主键值生成Page Directory（页目录），从而在按照主键值进行查找时可以使用二分法来加快查询速度。

现在以查找主键为 20 的记录为例，根据某个主键值去查找记录的步骤就可以大致拆分成下边两步：

1. 先到存储目录项记录的页，也就是页 30 中通过二分法快速定位到对应目录项，因为12 < 20 <209 ，所以定位到对应的记录所在的页就是页 9 。
2. 再到存储用户记录的页 9 中根据二分法快速定位到主键值为 20 的用户记录。

###### **② 迭代 2 次：多个目录项纪录的页**

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901154027658.png" alt="image-20220901154027658" style="zoom:50%;" />

从图中可以看出，我们插入了一条主键值为320的用户记录之后需要两个新的数据页：为存储该用户记录而新生成了 页31 。

因为原先存储目录项记录的 页30的容量已满 （我们前边假设只能存储4条目录项记录），所以不得不需要一个新的 页32 来存放 页31 对应的目录项。

现在因为存储目录项记录的页不止一个，所以如果我们想根据主键值查找一条用户记录大致需要3个步骤，以查找主键值为 20 的记录为例：

1. 确定 目录项记录页我们现在的存储目录项记录的页有两个，即 页30 和 页32 ，又因为页30表示的目录项的主键值的范围是 [1, 320) ，页32表示的目录项的主键值不小于 320 ，所以主键值为 20 的记录对应的目录项记录在 页30 中。

2. 通过目录项记录页 确定用户记录真实所在的页 。在一个存储 目录项记录 的页中通过主键值定位一条目录项记录的方式说过了。

3. 在真实存储用户记录的页中定位到具体的记录。

###### ③ 迭代 3 次：目录项记录页的目录页

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901154257125.png" alt="image-20220901154257125" style="zoom:50%;" />

如图，我们生成了一个存储更高级目录项的 页33 ，这个页中的两条记录分别代表页30和页32，如果用户记录的主键值在 [1, 320) 之间，则到页30中查找更详细的目录项记录，如果主键值 不小于320 的话，就到页32中查找更详细的目录项记录。

###### **④ B+Tree**

一个B+树的节点其实可以分成好多层，`规定最下边的那层，也就是存放我们用户记录的那层为第 0 层`，之后依次往上加。之前我们做了一个非常极端的假设：存放用户记录的页最多存放 3 条记录，存放目录项记录的页最多存放 4 条记录。其实真实环境中一个页存放的记录数量是非常大的，假设所有存放用户记录的叶子节点代表的数据页可以存放 100 条用户记录，所有存放目录项记录的内节点代表的数据页可以存放 1000 条目录项记录，那么：

- 如果B+树只有1层，也就是只有1个用于存放用户记录的节点，最多能存放 100 条记录。
- 如果B+树有2层，最多能存放 1000×100=10,0000 条记录。

- 如果B+树有3层，最多能存放 1000×1000×100=1,0000,0000 条记录。

- 如果B+树有4层，最多能存放 1000×1000×1000×100=1000,0000,0000 条记录。相当多的记录！！！

你的表里能存放 100000000000 条记录吗？所以一般情况下，我们 用到的B+树都不会超过4层 ，那我们通过主键值去查找某条记录最多只需要做4个页面内的查找（查找3个目录项页和一个用户记录页），又因为在每个页面内有所谓的 Page Directory （页目录），所以在页面内也可以通过 二分法 实现快速定位记录。

#### 常见索引分类

> 索引按照物理实现方式，索引可以分为 2 种：聚簇（聚集）和非聚簇（非聚集）索引。我们也把非聚集索引称为二级索引或者辅助索引。

##### 聚簇索引

- 特点：

  - 一般建表时的主键就会被mysql作为聚簇索引，如果没有主键,则选择非空唯一的索引作为聚簇索引，都没有则隐式创建一个索引作为聚簇索引
  - 使用记录的`主键值的大小进行记录和页的排序`，这包括三个方面的含义：
    - `页内 的记录`是按照主键的大小顺序排成一个 单向链表 。
    - 各个存放 `用户记录的页` 也是根据页中用户记录的主键大小顺序排成一个 双向链表 。
    - 存放 `目录项记录的页` 分为不同的层次，在同一层次中的页也是根据页中目录项记录的主键大小顺序排成一个 双向链表 。 

  - B+树的 `叶子节点 存储的是完整的用户记录`。
    - 所谓完整的用户记录，就是指这个记录中存储了所有列的值（包括隐藏列）。

- 优点：
  - 数据访问更快 ，因为聚簇索引将索引和数据保存在同一个B+树中，因此`从聚簇索引中获取数据比非聚簇索引更快`
  - 聚簇索引对于主键的 `排序查找 和 范围查找 速度非常快`
  - 按照聚簇索引排列顺序，查询显示一定范围数据的时候，由于数据都是紧密相连，数据库不用从多个数据块中提取数据，所以 `节省了大量的io操作 `。

- **缺点：**
  - 插入速度严重依赖于插入顺序，按照主键的顺序插入是最快的方式，否则将会出现页分裂，严重影响性能。因此，对于InnoDB表，我们一般都会定义一个 自增的ID列为主键
  - 更新主键的代价很高，因为将会导致被更新的行移动。因此，对于InnoDB表，我们一般定义 `主键为不可更新`
  - 二级索引访问需要两次索引查找，第一次找到主键值，第二次根据主键值找到行数据

##### 非聚簇索引（辅助索引、二级索引）

非聚簇索引：将数据与索引分开存储，索引结构的叶子节点指向了数据对应的位置。`叶子节点存储的不再是行的物理位置，而是主键值`，辅助索引访问数据总是需要二次查找，也就是`回表操作`。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901172943726.png" alt="image-20220901172943726" style="zoom:50%;" />

**概念：回表** 我们根据这个以c 2 列大小排序的B+树只能确定我们要查找记录的主键值，所以如果我们想根据c 2 列的值查找到完整的用户记录的话，仍然需要到聚簇索引中再查一遍，这个过程称为回表。也就是根据c 2 列的值查询一条完整的用户记录需要使用到 2 棵B+树！



#### InnoDB的B+树索引的注意事项

##### 根页面位置万年不动

B+树索引的建立过程

- 建立索引先会创建一个根页面，表中没有数据的时候，根页面没有数据，也没有目录项数据
- 往表中插入一条数据，是在根页面插入
- 根页面空间用完后在进行插入数据，会将根页面的数据复制到新的页面中，如页面a，再将页面a进行`页分裂`，生成页面b，比较键值将新增数据插入到页面a或者页面b中，根页面升级为存储目录项纪录的页面。

注意：根节点页面生成就不会改变位置，之后使用到这个索引，直接从固定位置读取即可。

##### 内节点中目录项记录的唯一性

为了让新插入的数据能找到插入位置，我们要`保证B+树同一层内节点的目录项纪录除页码外时唯一的`。对于非聚簇索引的内节点的目录项纪录实际上包括三个部分：`索引键、主键、页码号`。需要将主键也添加到目录项纪录中。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902100806553.png" alt="image-20220902100806553" style="zoom:50%;" />

##### 一个页面最少存储 2 条记录

### MyISAM中的索引方案

#### B+树索引适用存储引擎如表所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902101611870.png" alt="image-20220902101611870" style="zoom:50%;" />

即使多个存储引擎支持同一种类型的索引，但是他们的实现原理也是不同的。

Innodb和MyISAM默认的索引是B+tree索引；

Memory默认的索引是Hash索引。

MyISAM引擎使用B+Tree作为索引结构，`叶子节点的data域存放的是数据记录的地址`。

#### MyISAM索引的原理

MyISAM引擎的数据和索引分开存储，数据存放在.myd文件中，索引存放在.myi文件中。插入数据，数据文件并不会根据索引键进行排序，而是直接将数据进行插入。索引文件如主键索引，存放的是`主键值+对应数据地址`

下图是MyISAM索引的原理图。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902102601619.png" alt="image-20220902102601619" style="zoom:50%;" />

如果我们在Col 2 上建立一个二级索引，则此索引的结构如下图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902102546641.png" alt="image-20220902102546641" style="zoom:50%;" />


### MyISAM 与 InnoDB对比

**MyISAM的索引方式都是“非聚簇”的，与InnoDB包含 1 个聚簇索引是不同的。小结两种引擎中索引的区别：**

① 在InnoDB存储引擎中，我们只需要根据主键值对聚簇索引进行一次查找就能找到对应的记录，而在MyISAM中却需要进行一次回表操作，意味着MyISAM中建立的索引相当于全部都是二级索引。

② InnoDB的数据文件本身就是索引文件，而MyISAM索引文件和数据文件是分离的，索引文件仅保存数据记录的地址。

③ InnoDB的非聚簇索引data域存储相应记录主键的值，而MyISAM索引记录的是地址。

④ MyISAM的回表操作是十分快速的，因为是拿着地址偏移量直接到文件中取数据的，反观InnoDB是通过获取主键之后再去聚簇索引里找记录，虽然说也不慢，但还是比不上直接用地址去访问。

⑤ InnoDB要求表必须有主键（MyISAM可以没有）。如果没有显式指定，则MySQL系统会自动选择一个可以非空且唯一标识数据记录的列作为主键。如果不存在这种列，则MySQL自动为InnoDB表生成一个隐含字段作为主键，这个字段长度为 6 个字节，类型为长整型。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902103600738.png" alt="image-20220902103600738" style="zoom:50%;" />

### 索引的代价

索引是个好东西，可不能乱建，它在空间和时间上都会有消耗：

- **空间上的代价**

  每建立一个索引都要为它建立一棵B+树，每一棵B+树的每一个节点都是一个数据页，一个页默认会占用` 16KB` 的存储空间，一棵很大的B+树由许多数据页组成，那就是很大的一片存储空间。

- **时间上的代价**

  每次对表中的数据进行 `增、删、改 操作时，都需要去修改各个B+树索引`。而且我们讲过，B+树每层节点都是按照索引列的值 从小到大的顺序排序 而组成了 双向链表 。不论是叶子节点中的记录，还是内节点中的记录（也就是不论是用户记录还是目录项记录）都是按照索引列的值从小到大的顺序而形成了一个单向链表。而增、删、改操作可能会对节点和记录的排序造成破坏，所以存储引擎需要额外的时间进行一些 记录移位 ， 页面分裂 、 页面回收 等操作来维护好节点和记录的排序。如果我们建了许多索引，每个索引对应的B+树都要进行相关的维护操作，会给性能拖后腿。

### 索引数据结构

> 从MySQL的角度，影响索引使用效率最大的因素就是`与磁盘的I/O次数`，I/O次数越多，效率越低

#### 全表遍历

按照索引顺序查找，效率低 

#### Hash结构

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902144744598.png" alt="image-20220902144744598" style="zoom:50%;" />

通过hash函数进行计算，相同的输入可以得到相同的输出。通过hash查找效率很快，基本上只要一次就可以找到，比树形结构更快。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902143600586.png" alt="image-20220902143600586" style="zoom:50%;" />

上图中哈希函数h有可能将两个不同的关键字映射到相同的位置，这叫做`碰撞`，在数据库中一般采用`链接法`来解决。在链接法中，将散列到同一槽位的元素放在一个链表中，如下图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902143635601.png" alt="image-20220902143635601" style="zoom:50%;" />

**Hash结构效率高，那为什么索引结构要设计成树型呢**

1⃣️Hash索引只能满足=、<>、in查询，如果要进行范围查询，Hash索引会退化为O(n)的时间复杂度

2⃣️Hash索引没有顺序，如果ORDER BY的话要重新排序

3⃣️对于组合索引，Hash索引是将多个键合并起来一起计算的，结果不准确

4⃣️对于等值查询Hash索引的效率很高，但是如果索引列的重复数据很多，Hash索引会退化为链表结构，效率值会降低。如性别、年龄。

**Hash索引的自适应索引：**

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902150037598.png" alt="image-20220902150037598" style="zoom:50%;" />

InnoDB不支持Hash索引，但是提供了自适应Hash索引。如果某个数据经常被访问，会将这个数据存放到一个Hash表中。采用自适应 Hash 索引目的是方便根据 SQL 的查询条件加速定位到叶子节点，特别是当 B+ 树比较深的时候，通过自适应 Hash 索引可以明显提高数据的检索效率。我们可以通过innodb_adaptive_hash_index变量来查看是否开启了自适应 Hash，比如：

```sql
show variables like '%adaptive_hash_index';
```

#### 二叉搜索树

如果我们利用二叉树作为索引结构，那么磁盘的IO次数和索引树的高度是相关的。

##### 二叉搜索树的特点

- 一个节点最多有两个字节点
- 左子节点 < 当前节点 <= 右子节点

##### 创造出来的二分搜索树如下图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902151313566.png" alt="image-20220902151313566" style="zoom:50%;" />

极端场景：二叉搜索树退化为链表。为了提高查询效率，就需要 减少磁盘IO数 。为了减少磁盘IO的次数，就需要尽量 降低树的高度 ，需要把原来“瘦高”的树结构变的“矮胖”，树的每层的分叉越多越好。

#### AVL树

左右子树的节点高度相差不超过1，并且左右子树也是平衡二叉树。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902151709781.png" alt="image-20220902151709781" style="zoom:50%;" />

#### B-Tree

##### B 树的结构如下图所示

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220902151902983.png" alt="image-20220902151902983" style="zoom:50%;" />

##### 一个 M 阶的 B 树（M>2）有以下的特性：

1. 如果增加或者删除数据导致不平衡，B树会自动进行调整，所有叶子节点位于同一层。
2. 数据存放在叶子节点和中间节点中
3. 根节点的儿子数的范围是 [2,M]。
4. 中间节点包含 k-1 个关键字和 k 个孩子，孩子的数量 = 关键字的数量 +1，k 的取值范围为[ceil(M/2), M]。
5. 叶子节点包括 k-1 个关键字（叶子节点没有孩子），k 的取值范围为 [ceil(M/2), M]。
6. 假设中间节点节点的关键字为：Key[1], Key[2], ..., Key[k-1]，且关键字按照升序排序，即 Key[i] < Key[i+1]。此时 k-1 个关键字相当于划分了 k 个范围，也就是对应着 k 个指针，即为：P[1], P[2], ...,P[k]，其中 P[1] 指向关键字小于 Key[1] 的子树，P[i] 指向关键字属于 (Key[i-1], Key[i]) 的子树，P[k]指向关键字大于 Key[k-1] 的子树。



你能看出来在 B 树的搜索过程中，我们比较的次数并不少，但如果把数据读取出来然后在内存中进行比较，这个时间就是可以忽略不计的。而读取磁盘块本身需要进行 I/O 操作，消耗的时间比在内存中进行比较所需要的时间要多，是数据查找用时的重要因素。B 树相比于平衡二叉树来说磁盘 I/O 操作要少，在数据查询中比平衡二叉树效率要高。所以只要树的高度足够低，IO次数足够少，就可以提高查询性能。

#### B+Tree

##### B+ 树和 B 树的差异

1. 有 k 个孩子的节点就有 k 个关键字。也就是孩子数量 = 关键字数，而 B 树中，孩子数量 = 关键字数+1。
2. 非叶子节点的关键字也会同时存在在子节点中，并且是在子节点中所有关键字的最大（或最小）。
3. B+树非叶子节点仅用于索引，不保存数据记录，跟记录有关的信息都放在叶子节点中。B树中，非叶子节点既保存索引，也保存数据记录。这使得B+树的查询效率更高，因为一次磁盘I/O读取16kb，包括的关键字更多。
4. 所有关键字都在叶子节点出现，叶子节点构成一个有序链表，而且叶子节点本身按照关键字的大小从小到大顺序链接。这使得B+树的范围查询效率更高，如果是B树，还需要通过中序遍历进行查找。 

B 树和 B+ 树都可以作为索引的数据结构，在 MySQL 中采用的是 B+ 树。但B树和B+树各有自己的应用场景，不能说B+树完全比B树好，反之亦然

**思考题：B+树的存储能力如何？为何说一般查找行记录，最多只需1~3次磁盘IO**

InnoDB的页大小为16KB，int占4Byte，一般的指针类型大小为4KB或者8KB，假设关键字大小为16B，那么一个页可以存储1000个关键字。如果是2层：1000 * 1000  = 100万条数据，如果是3层：1000 * 1000 * 1000 = 10亿条数据。实际情况下，不可能每个页存满，所以B+树的高度一般是2～4层，MySQL的InnoDB引擎的根节点是常驻内存的，因此一次查找最多1～3次磁盘IO

**思考题：Hash 索引与 B+ 树索引的区别**

Hash索引不支持范围查询；不支持组合索引查询；不支持Order By排序；InnoDB不支持Hash索引

#### R树

R-Tree在MySQL很少使用，仅支持`geometry数据类型`，支持该类型的存储引擎只有`myisam、bdb、innodb、ndb、archive`几种。举个R树在现实领域中能够解决的例子：查找 20 英里以内所有的餐厅。如果没有R树你会怎么解决？一般情况下我们会把餐厅的坐标(x,y)分为两个字段存放在数据库中，一个字段记录经度，另一个字段记录纬度。这样的话我们就需要遍历所有的餐厅获取其位置信息，然后计算是否满足要求。如果一个地区有 100 家餐厅的话，我们就要进行 100 次位置计算操作了，如果应用到谷歌、百度地图这种超大数据库中，这种方法便必定不可行了。R树就很好的`解决了这种高维空间搜索问题`。它把B树的思想很好的扩展到了多维空间，采用了B树分割空间的思想，并在添加、删除操作时采用合并、分解结点的方法，保证树的平衡性。因此，R树就是一棵用来`存储高维数据的平衡树`。相对于B-Tree，R-Tree的优势在于范围查找。

## InnoDB 数据存储结构

### 数据库的存储结构：页

#### 磁盘与内存交互的基本单位：页

Innodb将数据划分为若干页，每个页的默认大小为16KB。在数据库中，不论读1行还是多行，都是将行所在的页进行加载。数据库管理存储空间的最小单位是页，每次I/O操作的最小单位是页。

```sql
-- 查看页大小
show variables like '%innodb_page_size%'
```

SQL Server中页的默认大小为8KB，Oracle中使用“块”(Block)表示页，有2、4、8、16、32、64KB。

#### 页结构概述

![image-20221014123142029](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014123142029.png)

页不在物理上相连，通过`双向链表`连接即可。每个数据页中的记录按照主键值从小到大用`单向链表`进行连接，并且生成一个`页目录`，可以使用`二分法`对内部数据进行检索。

#### 页的上层结构

页的上层结构包含：区(Extent)、段(Segment)、表(Tablespace)空间的概念。关系如下图：

![image-20221014125904968](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014125904968.png)

区（Extent）是页上一层的存储结构，一个区分配64个连续的页，64*16KB = 1MB。

段（Segment）是区的上一层存储结构，由一个或者多个区组成，不要求区与区相邻。段(Segment)分为索引段，数据段，回滚段等。其中索引段就是非叶子结点部分，而数据段就是叶子结点部分，回滚段用于数据的回滚和多版本控制。段是数据库的分配单位，不同类型的数据库对象以不同的段形式存在。如：创建一个表时会创建一个表段，创建一个索引时会创建一个索引段。一个段包含256个区(256M大小)

表空间（Tablespace）是一个逻辑容器，存储一个或多个段。分为系统表空间、用户表空间、撤销表空间、临时表空间。 

### 数据⻚内部结构

按照类型分：数据页、系统页、Undo页、事务数据页。

数据页大小为16KB，分为7个部分，如下：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014132541820-20221014132622811.png" style="zoom:50%;" />

#### File Header（文件头部）

File Header（文件头部）（ 38 字节），描述各种⻚的通用信息。（比如⻚的编号、其上一⻚、下一⻚是谁等） 

构成：

> `FIL_PAGE_OFFSET（ 4 字节）`每一个⻚都有一个单独的⻚号，就跟你的身份证号码一样，InnoDB通过⻚号可以唯一定位一个⻚。
>
> `FIL_PAGE_TYPE（ 2 字节）`这个代表当前⻚的类型。<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014133746774.png" alt="image-20221014133746774" style="zoom:50%;" />
>
> `FIL_PAGE_PREV（ 4 字节）和FIL_PAGE_NEXT（ 4 字节）`分别代表本⻚的上一个和下一个⻚的⻚号
>
> `FIL_PAGE_SPACE_OR_CHKSUM（ 4 字节）`代表当前⻚面的校验和（checksum）。 什么是校验和？就是对于一个很⻓的字节串来说，我们会通过某种算法来计算一个比较短的值来代表这个很⻓的字节串，这个比较短的值就称为校验和。在比较两个很⻓的字节串之前，先比较这两个⻓字节串的校验和，如果校验和都不一样，则两个⻓字节串肯定是不同的，所以省去了直接比较两个比较⻓的字节串的时间损耗。 
>
> 文件头部和文件尾部都有属性：FIL_PAGE_SPACE_OR_CHKSUM 
>
> `作用`：InnoDB存储引擎以⻚为单位把数据加载到内存中处理，如果该⻚中的数据在内存中被修改了，那么在修改后的某个时间需要把数据同步到磁盘中。但是在同步了一半的时候断电了，造成了该⻚传输的不完整。为了检测一个⻚是否完整（也就是在同步的时候有没有发生只同步一半的尴尬情况），这时可以通过文件尾的校验和（checksum 值）与文件头的校验和做比对，如果两个值不相等则证明⻚的传输有问题，需要重新进行传输，否则认为⻚的传输已经完成。
>
> `具体的`：每当一个⻚面在内存中修改了，在同步之前就要把它的校验和算出来，因为File Header在⻚面的前边，所以校验和会被首先同步到磁盘，当完全写完时，校验和也会被写到⻚的尾部，如果完全同步成功，则⻚的首部和尾部的校验和应该是一致的。如果写了一半儿断电了，那么在File Header中的校验和就代表着已经修改过的⻚，而在File Trailer中的校验和代表着原先的⻚，二者不同则意味着同步中间出了错。这里，校验方式就是采用 Hash 算法进行校验。
>
> `FIL_PAGE_LSN（ 8 字节）` ⻚面被最后修改时对应的日志序列位置（英文名是：Log Sequence Number）

#### File Trailer（文件尾部）

File Trailer（文件尾部）（ 8 字节）

前 4 个字节代表⻚的校验和：这个部分是和File Header中的校验和相对应的。 

后 4个字节代表⻚面被最后修改时对应的日志序列位置（LSN）：这个部分也是为了校验⻚的完整性的，如果首部和尾部的LSN值校验不成功的话，就说明同步过程出现了问题。

#### Free Space (空闲空间)
我们自己存储的记录会按照指定的行格式存储到User Records部分。但是在一开始生成⻚的时候，其实并没有User Records这个部分，每当我们插入一条记录，都会从Fr ee Space部分，也就是尚未使用的存储空间中申请一个记录大小的空间划分到User Records部分，当Free Space部分的空间全部被User Records部分替代掉之后，也就意味着这个⻚使用完了，如果还有新的记录插入的话，就需要去申请新的⻚了。

#### User Records (用户记录)
User Records中的这些记录按照指定的行格式一条一条摆在User Records部分，相互之间形成单链表。 用户记录里的一条条数据如何记录？这里需要讲讲记录行格式的记录头信息。

#### Infimum + Supremum（最小最大记录）
记录可以比较大小吗？是的，记录可以比大小，对于一条完整的记录来说，比较记录的大小就是比较主键的大小。比方说我们插入的 4 行记录的主键值分别是： 1 、2 、 3 、 4 ，这也就意味着这 4 条记录是从小到大依次递增。

InnoDB规定的最小记录与最大记录这两条记录的构造十分简单，都是由 5 字节大小的记录头信息和 8 字节大小的一个固定的部分组成的，如图所示：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014150720224.png" alt="image-20221014150720224" style="zoom:50%;" />

 这两条记录不是我们自己定义的记录，所以它们并不存放在⻚的User Records部分，他们被单独放在一个称为Infimum+Supremum的部分，如图所示： 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014150750701.png" alt="image-20221014150750701" style="zoom:50%;" />

#### Page Directory（⻚目录）

为什么需要⻚目录？在⻚中，记录是以单向链表的形式进行存储的。单向链表的特点就是插入、删除非常方便，但是检索效率不高，最差的情况下需要遍历链表上的所有节点才能完成检索。因此在⻚结构中专⻔`设计了⻚目录这个模块，专⻔给记录做一个目录，通过二分查找法的方式进行检索，提升效率`。 需求：根据主键值查找⻚中的某条记录，如何实现快速查找呢？SELECT * FROM page_demo WHERE c 1= 3 ;

使用⻚目录，二分法查找

1. 将所有的记录分成几个组，这些记录包括最小记录和最大记录，但不包括标记为“已删除”的记录。 

2. 第 1 组，也就是最小记录所在的分组只有 1 个记录； 最后一组，就是最大记录所在的分组，会有 1 - 8 条记录； 其余的组记录数量在 4 - 8 条之间。这样做的好处是，除了第 1 组（最小记录所在组）以外，其余组的记录数会尽量平分。 

3. 在每个组中最后一条记录的头信息中会存储该组一共有多少条记录，`作为 n_owned 字段`。 

4. `⻚目录用来存储每组最后一条记录的地址偏移量`，这些地址偏移量会按照先后顺序存储起来，`每组的地址偏移量也被称之为槽（slot）`，每个槽相当于指针指向了不同组的最后一个记录。 

  1. 举例 1 ：

    <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014155134728.png" alt="image-20221014155134728" style="zoom:50%;" />

  2. 举例 2 ：现在的page_demo表中正常的记录共有 6 条，InnoDB会把它们分成两组，第一组中只有一个最小记录，第二组中是剩余的 5 条记录。如下图： 从这个图中我们需要注意这么几点：现在⻚目录部分中有两个槽，也就意味着我们的记录被分成了两个组，槽1 中的值是 112 ，代表最大记录的地址偏移量（就是从⻚面的 0 字节开始数，数 112 个字节）；槽 0 中的值是 99 ，代表最小记录的地址偏移量。注意最小和最大记录的头信息中的n_owned属性最小记录的n_owned值为 1 ，这就代表着以最小记录结尾的这个分组中只有 1 条记录，也就是最小记录本身。最大记录的n_owned值为 5 ，这就代表着以最大记录结尾的这个分组中只有 5 条记录，包括最大记录本身还有我们自己插入的 4 条记录。 用箭头指向的方式替代数字，这样更易于我们理解，修改后如下：再换个⻆度看一下：（单纯从逻辑上看一下这些记录和⻚目录的关系）

    <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014155537160.png" alt="image-20221014155537160" style="zoom:50%;" />

⻚目录分组的个数如何确定？

> 为什么最小记录的n_owned值为 1 ，而最大记录的n_owned值为 5 呢？ InnoDB规定：对于最小记录所在的分组只能有 1 条记录，最大记录所在的分组拥有的记录条数只能在 1 ~ 8 条之间，剩下的分组中记录的条数范围只能在是 4 ~ 8 条之间.
>
> 分组是按照下边的步骤进行的：
>
> 初始情况下一个数据⻚里只有最小记录和最大记录两条记录，它们分属于两个分组。之后每插入一条记录，都会从⻚目录中找到主键值比本记录的主键值大并且差值最小的槽，然后把该槽对应的记录的n_owned值加 1 ，表示本组内又添加了一条记录，直到该组中的记录数等于 8个。在一个组中的记录数等于 8 个后再插入一条记录时，会将组中的记录拆分成两个组，一个组中 4 条记录，另一个 5 条记录。这个过程会在⻚目录中新增一个槽来记录这个新增分组中最大的那条记录的偏移量。

⻚目录结构下如何快速查找记录？

> 现在向page_demo表中添加更多的数据。如下：
>
> INSERT INTO page_demo VALUES( 5 , 500 , 'zhou'), ( 6 , 600 , 'chen'), ( 7 , 700 , 'deng'), ( 8 , 800 , 'yang'), ( 9 ,900 , 'wang'), ( 10 , 1000 , 'zhao'), ( 11 , 1100 , 'qian'), ( 12 , 1200 , 'feng'), ( 13 , 1300 ,'tang'), ( 14 , 1400 , 'ding'), ( 15 , 1500 , 'jing'), ( 16 , 1600 , 'quan');
>
> 添加了 12 条记录，现在⻚里一共有 18 条记录了（包括最小和最大记录），这些记录被分成了 5个组，如图所示：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014160150089.png" alt="image-20221014160150089" style="zoom:50%;" />
>
> 这里只保留了 16 条记录的记录头信息中的n_owned和next_record属性，省略了各个记录之间的箭头。现在看怎么从这个⻚目录中查找记录。因为各个槽代表的记录的主键值都是从小到大排序的，所以我们可以使用二分法来进行快速查找。 5 个槽的编号分别是： 0 、 1 、 2 、 3 、 4 ，所以初始情况下最低的槽就是low= 0 ，最高的槽就是high= 4 。比方说我们想找主键值为 6 的记录，过程是这样的：
>
> 1. 计算中间槽的位置：( 0 + 4 )/ 2 = 2 ，所以查看槽 2 对应记录的主键值为 8 ，又因为 8 > 6 ，所以设置high= 2 ，low保持不变。
>
> 2. 重新计算中间槽的位置：( 0 + 2 )/ 2 = 1 ，所以查看槽 1 对应的主键值为 4 ，又因为 4 < 6 ，所以设置low= 1 ，high保持不变。 
>
> 3. 因为high - low的值为 1 ，所以确定主键值为 6 的记录在槽 2 对应的组中。此刻我们需要找到槽 2 中主键值最小的那条记录，然后沿着单向链表遍历槽 2 中的记录。但是我们前边又说过，每个槽对应的记录都是该组中主键值最大的记录，这里槽 2 对应的记录是主键值为 8 的记录，怎么定位一个组中最小的记录呢？别忘了各个槽都是挨着的，我们可以很轻易的拿到槽 1 对应的记录（主键值为 4 ），该条记录的下一条记录就是槽 2 中主键值最小的记录，该记录的主键值为 5 。所以我们可以从这条主键值为 5 的记录出发，遍历槽 2中的各条记录，直到找到主键值为 6 的那条记录即可。由于一个组中包含的记录条数只能是 1 ~ 8 条，所以遍历一个组中的记录的代价是很小的。 
>
>    小结：在一个数据⻚中查找指定主键值的记录的过程分为两步： 1. 通过二分法确定该记录所在的槽，并找到该槽所在分组中主键值最小的那条记录。 2. 通过记录的next_record属性遍历该槽所在的组中的各个记录。

#### Page Header（⻚面头部）

为了能得到一个数据⻚中存储的记录的状态信息，比如本⻚中已经存储了多少条记 录，第一条记录的地址是什么，⻚目录中存储了多少个槽等等，特意在⻚中定义了一个叫Page Header的部分，这个部分占用固定的` 56 个字节`，`专⻔存储各种状态信息`。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014161150537.png" alt="image-20221014161150537" style="zoom:50%;" />

`PAGE_DIRECTION`
假如新插入的一条记录的主键值比上一条记录的主键值大，我们说这条记录的插入方向是右边，反之则是左边。用来表示最后一条记录插入方向的状态就是PAGE_DIRECTION 。 

`PAGE_N_DIRECTION`
假设连续几次插入新记录的方向都是一致的，InnoDB会把沿着同一个方向插入记录的条数记下来，这个条数就用PAGE_N_DIRECTION这个状态表示。当然，如果最后一条记录的插入方向改变了的话，这个状态的值会被清零重新统计。

### InnoDB 行格式（或记录格式）

我们平时的数据以行为单位来向表中插入数据，这些记录在磁盘上的存放方式也被称为`行格式`或者`记录格式`。InnoDB存储引擎设计了 4 种不同类型的`行格式`，分别是`Compact`、`Redundant`、`Dynamic`和`Compressed`行格式。 

查看MySQL 8 的默认行格式：默认dynamic

```sql
select @@innodb_default_row_format;
```

指定行格式的语法

```sql
CREATE TABLE record_test_table(
    col1 VARCHAR(8),
    col2 VARCHAR(8) NOT NULL,
    col3 CHAR(8),
    col4 VARCHAR(8)
) ROW_FORMAT = COMPACT;
```

#### COMPACT行格式
在`MySQL 5.0之后，5.7之前版本，默认设置为Compact行格式`。一条完整的记录其实可以被分为记录的额外信息和记录的真实数据两大部分。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014144137051.png" alt="image-20221014144137051" style="zoom:50%;" />

##### 变⻓字段⻓度列表
MySQL支持一些变⻓的数据类型，比如VARCHAR(M)、VARBINARY(M)、TEXT类型，BLOB类型，这些数据类型修饰列称为变⻓字段，变⻓字段中存储多少字节的数据不是固定的，所以我们在存储真实数据的时候需要顺便把这些数据占用的字节数也存起来。在Compact行格式中，`把所有变⻓字段的真实数据占用的字节⻓度都存放在记录的开头部位，从而形成一个变⻓字段⻓度列表`。 

注意：这里面存储的变⻓⻓度和字段顺序是反过来的。比如两个varchar字段在表结构的顺序是a( 10 )，b( 15 )。那么在变⻓字段⻓度列表中存储的⻓度顺序就是 15 ， 10 ，是反过来的。 以record_test_table表中的第一条记录举例：因为record_test_table表的col 1 、col 2 、col 4 列都是VARCHAR( 8 )类型的，所以这三个列的值的⻓度都需要保存在记录开头处，注意record_test_table表中的各个列都使用的是ascii字符集（每个字符只需要 1 个字节来进行编码）。 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014163426587.png" alt="image-20221014163426587" style="zoom:50%;" />

又因为这些⻓度值需要按照列的逆序存放，所以最后变⻓字段⻓度列表的字节串用十六进制表示的效果就是（各个字节之间实际上没有空格，用空格隔开只是方便理解）： 06 04 08 把这个字节串组成的变⻓字段⻓度列表填入上边的示意图中的效果就是：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014163454588.png" alt="image-20221014163454588" style="zoom:50%;" />

##### NULL值列表

Compact行格式会把可以为NULL的列统一管理起来，存在一个标记为NULL值列表中。如果表中没有允许存储 NULL 的列，则 NULL值列表也不存在了。为什么定义NULL值列表？之所以要存储NULL是因为数据都是需要对⻬的，如果没有标注出来NULL值的位置，就有可能在查询数据的时候出现混乱。如果使用一个特定的符号放到相应的数据位表示空置的话，虽然能达到效果，但是这样很浪费空间，所以直接就在行数据得头部开辟出一块空间专⻔用来记录该行数据哪些是非空数据，哪些是空数据，`格式如下： 1. 二进制位的值为 1 时，代表该列的值为NULL。 2. 二进制位的值为 0 时，代表该列的值不为NULL。 `

例如：字段 a、b、c，其中a是主键，在某一行中存储的数依 次是 a= 1 、b=null、c= 2 。那么Compact行格式中的NULL值列表中存储： 01 。第一个 0 表示c不为null，第二个 1 表示b是null。这里之所以没有a是因为数据库会自动跳过主键，因为主键肯定是非NULL且唯一的，在NULL值列表的数据中就会自动跳过主键；并且会自动跳过not null的列。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014170458665.png" alt="image-20221014170458665" style="zoom:50%;" />

##### 记录头信息（ 5 字节）

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014144703924.png" alt="image-20221014144703924" style="zoom:50%;" />

插入数据：INSERT INTO page_demo VALUES( 1 , 100 , 'song'), ( 2 , 200 , 'tong'), ( 3 , 300 ,'zhan'), ( 4 , 400 , 'lisi')；图示如下：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014144843912.png" alt="image-20221014144843912" style="zoom:50%;" />

`delete_mask`
这个属性`标记着当前记录是否被删除`，占用 1 个二进制位。值为 0 ：代表记录并没有被删除；值为 1 ：代表记录被删除掉了 被删除的记录为什么还在⻚中存储呢？你以为它删除了，可它还在真实的磁盘上。这些被删除的记录之所以不立即从磁盘上移除，是因为移除它们之后其他的记录在磁盘上需要`重新排列，导致性能消耗`。所以只是打一个删除标记而已，所有被删除掉的记录都会组成一个所谓的`垃圾链表`，在这个链表中的记录占用的空间称之为`可重用空间` ，之后如果有新记录插入到表中的话，可能把这些被删除的记录占用的存储空间覆盖掉。
`min_rec_mask`
B+树的`每层非叶子节点中的最小记录都会添加该标记，min_rec_mask值为 1 `。我们自己插入的四条记录的min_rec_mask值都是 0 ，意味着它们都不是B+树的非叶子节点中的最小记录。

`record_type`
这个属性表示`当前记录的类型`，一共有 4 种类型的记录： 0 ：表示普通记录；1 ：表示B+树非叶节点记录； 2 ：表示最小记录； 3 ：表示最大记录。 

`heap_no`

这个属性表示`当前记录在本⻚中的位置`。 从图中可以看出来，我们插入的 4 条记录在本⻚中的位置分别是： 2 、 3 、 4 、 5 。 怎么不⻅heap_no值为 0 和 1 的记录呢？MySQL会自动给每个⻚里加了两个记录，由于这两个记录并不是我们自己插入的，所以有时候也称为伪记录或者虚拟记录。`这两个伪记录一个代表最小记录，一个代表最大记录`。最小记录和最大记录的heap_no值分别是 0 和 1 ，也就是说它们的位置最靠前。

`n_owned`

⻚目录中每个组中最后一条记录的头信息中会`存储该组一共有多少条记录`，作为n_owned 字段。详情⻅page directory。

`next_record`

记录头信息里该属性非常重要，它表示`从当前记录的真实数据到下一条记录的真实数据的地址偏移量，而不是下一条记录的具体位置`。比如：第一条记录的next_record值为 32 ，意味着从第一条记录的真实数据的地址处向后找 32 个字节便是下一条记录的真实数据。注意，下一条记录指得并不是按照我们插入顺序的下一条记录，而是按照主键值由小到大的顺序的下一条记录。而且规定Infimum记录（也就是最小记录）的下一条记录就是本⻚中主键值最小的用户记录，而本⻚中主键值最大的用户记录的下一条记录就是 Supremum记录（也就是最大记录）。下图用箭头代替偏移量表示next_record。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014152257085.png" alt="image-20221014152257085" style="zoom:50%;" />

`删除操作`：从表中删除掉一条记录，这个链表也是会跟着变化：

mysql>DELETE FROM page_demo WHERE c 1 = 2 ;Query OK, 1 row affected ( 0. 02sec)

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014152507119.png" alt="image-20221014152507119" style="zoom:50%;" />

- 第 2 条记录并没有从存储空间中移除，而是把该条记录的delete_mask值设置为 1 。
- 第 2 条记录的next_record值变为了 0 ，意味着该记录没有下一条记录了。
- 第 1 条记录的next_record指向了第 3 条记录。
- 最大记录的n_owned值从 5 变成了 4 。 
- 不论我们怎么对⻚中的记录做增删改操作，InnoDB始终会维护一条记录的单链表，链表中的各个节点是按照主键值由小到大的顺序连接起来的。

`添加操作`：主键值为 2 的记录被我们删掉了，但是存储空间却没有回收，如果我们再次把这条记录插入到表中，会发生什么事呢？

mysql> INSERT INTOpage_demo VALUES( 2 , 200 , 'tong');Query OK, 1 row affected ( 0. 00 sec)

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014152918879.png" alt="image-20221014152918879" style="zoom:50%;" />

我们看一下记录的存储情况： 直接复用了原来被删除记录的存储空间。  

说明：当数据⻚中存在多条被删除掉的记录时，这些记录的next_record属性将会把这些被删除掉的记录组成一个垃圾链表，以备之后重用这部分存储空间。

##### 记录的真实数据

记录的真实数据除了我们自己定义的列的数据以外，还会有三个隐藏列： 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014170739931.png" alt="image-20221014170739931" style="zoom:50%;" />

实际上这几个列的真正名称其实是：DB_ROW_ID、DB_TRX_ID、DB_ROLL_PTR。

- 一个表没有手动定义主键，则会选取一个Unique键作为主键，如果连Unique键都没有定义的话，则会为表默认添加一个名为row_id的隐藏列作为主键。所以`row_id是在没有自定义主键以及Unique键的情况下才会存在的`。
- 事务ID和回滚指针在后面的《第14 章_MySQL事务日志》章节中讲解。 

#### Dynamic和Compressed行格式

##### 行溢出
InnoDB存储引擎可以将一条记录中的某些数据存储在真正的数据⻚面之外。 很多DBA喜欢MySQL数据库提供的VARCHAR(M)类型，认为可以存放 65535 字节。这是真的吗？

如果我们使用 ascii字符集的话，一个字符就代表一个字节，我们看看VARCHAR( 65535 )是否可用。

CREATE  TABLE  varchar_size_demo( cVARCHAR( 65535 ) )  CHARSET=ascii  ROW_FORMAT=Compact;

结果如下：ERROR 1118 ( 42000 ): Row size too large. The maximum row size for the usedtable type, not counting BLOBs, is 65535. This includes storage overhead,check the manual. You have to change some columns to  TEXT or  BLOBs 报错信息表达的意思是：MySQL对一条记录占用的最大存储空间是有限制的，除BLOB或者TEXT类型的列之外， 其他所有的列（不包括隐藏列和记录头信息）占用
的字节⻓度加起来不能超过 65535 个字节。 

这个 65535 个字节除了列本身的数据之外，还包括一些其他的数据，以Compact行格式为例，比如说我们为了存储一个VARCHAR(M)类型的列，除了真实数据占有空间以外，还需要记录的额外信息。如果该VARCHAR类型的列没有NOT NULL属性，那最多只能存储 65532 个字节的数据，`因为变⻓字段的⻓度占用 2 个字节，NULL值标识需要占用 1 个字节。`
CREATE  TABLE  varchar_size_demo( c VARCHAR( 65532 ))  CHARSET=asciiROW_FORMAT=Compact; 

#如果有not null属性，那么就不需要NULL值标识，也就可以多存储一个字节，即 65533 个字节

CREATE  TABLE  varchar_size_demo(c VARCHAR( 65533 ) not null)  CHARSET=ascii  ROW_FORMAT=Compact; 

通过上面的案例，我们可以知道一个⻚的大小一般是 16 KB，也就是 16384 字节，而`一个VARCHAR(M)类型的列就最多可以存储 65533 个字节，这样就可能出现一个⻚存放不了一条记录，这种现象称为行溢出`。 在Compact和Reduntant行格式中，对于占用存储空间非常大的列，在记录的真实数据处只会存储该列的一部分数据，把剩余的数据分散存储在几个其他的⻚中进行分⻚存储，然后记录的真实数据处用 20 个字节存储指向这些⻚的地址（当然这 20 个字节中还包括这些分散在其他⻚面中的数据的占用的字节数），从而可以找到剩余数据所在的⻚。这称为⻚的扩展，举例如下：

 <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014172644122.png" style="zoom:50%;" />

##### Dynamic和Compressed行格式

在MySQL 8. 0 中，默认行格式就是Dynamic，`Dynamic、Compressed行格式和Compact行格式挺像，只不过在处理行溢出数据时有分歧`：

- Compressed和Dynamic两种记录格式对于存放在BLOB中的数据采用了`完全的行溢出的方式`。如图，在数据⻚中只存放 20 个字节的指针（溢出⻚的地址），实际的数据都存放在Off Page（溢出⻚）中。
- Compact和Redundant两种格式会在记录的真实数据处存储一部分数据（存放 768 个前缀字节）。 
- Compressed行记录格式的另一个功能就是，存储在其中的行数据会以zlib的算法进行压缩，因此对于BLOB、TEXT、VARCHAR这类大⻓度类型的数据能够进行非常有效的存储。
- <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014173002379.png" alt="image-20221014173002379" style="zoom:50%;" />

#### Redundant行格式

Redundant是`MySQL 5. 0 版本之前InnoDB的行记录存储方式`，MySQL 5. 0 支持Redundant是为了兼容之前版本的⻚格式。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014173402164-20221014174052941.png" style="zoom:50%;" />

从上图可以看到，不同于Compact行记录格式，Redundant行格式的首部是一个字段⻓度偏移列表，同样是按照列的顺序逆序放置的。

##### 字段⻓度偏移列表
注意Compact行格式的开头是变⻓字段⻓度列表，而Redundant行格式的开头是字
段⻓度偏移列表，与变⻓字段⻓度列表有两处不同： 

- 少了“变⻓”两个字：Redundant行格式会把该条记录中所有列（包括隐藏列）的⻓度信息都按照逆序存储到字段⻓度偏移列表。
- 多了“偏移”两个字：这意味着计算列值⻓度的方式不像Compact行格式那么直观，它是采用两个相邻数值的差值来计算各个列值的⻓度。

举例：比如第一条记录的字段⻓度偏移列表就是： 2B 25 1F 1B 13 0C 06 

按照两个相邻数值的差值来计算各个列值的⻓度的意思就是： (0x为16进制)

第一列(row_id)的⻓度就是 0 x 06 个字节，也就是 6 个字节。

第二列(transaction_id)的⻓度就是 ( 0 x 0 C - 0 x 06 )个字节，也就是 6 个字节。

第三列(roll_pointer)的⻓度就是 ( 0 x 13 - 0 x 0 C)个字节，也就是 7 个字节。

第四列(col 1 )的⻓度就是 ( 0 x 1 B - 0 x 13 )个字节，也就是 8 个字节。

第五列(col 2 )的⻓度就是 ( 0 x 1 F - 0 x 1 B)个字节，也就是 4 个字节。

第六列(col 3 )的⻓度就是 ( 0 x 25 - 0 x 1 F)个字节，也就是 6 个字节。

第七列(col 4 )的⻓度就是 ( 0 x 2 B - 0 x 25 )个字节，也就是 6 个字节。

##### 记录头信息（record header）

不同于Compact行格式，Redundant行格式中的记录头信息固定`占用 6 个字节（ 48位）`，每位的含义⻅下表。 与Compact行格式的记录头信息对比来看，有两处不同：

- Redundant行格式多了n_field和 1byte_offs_flag这两个属性。
- Redundant行格式没有record_type这个属性。 
- 其中，n_fields：代表一行中列的数量，占用 10位，这也很好地解释了为什么MySQL一个行支持最多的列为 1023 。另一个值为1byte_offs_flags，该值定义了偏移列表占用 1 个字节还是 2 个字节。当它的值为 1时，表明使用 1 个字节存储。当它的值为 0 时，表明使用 2 个字节存储。
- 1 byte_offs_flag的值是怎么选择的。我们前边说过每个列对应的偏移量可以占用 1 个
  字节或者 2 个字节来存储，那到底什么时候用 1 个字节，什么时候用 2 个字节呢？其
  实是根据该条Redundant行格式记录的真实数据占用的总大小来判断的：当记录的
  真实数据占用的字节数值不大于 127 （十六进制 0 x 7 F，二进制 01111111 ）时，每个
  列对应的偏移量占用 1 个字节。当记录的真实数据占用的字节数大于 127 ，但不大于
  32767 （十六进制 0 x 7 FFF，二进制 0111111111111111 ）时，每个列对应的偏移量占
  用 2 个字节。 有没有记录的真实数据大于 32767 的情况呢？有，不过此时的记录已
  经存放到了溢出⻚中，在本⻚中只保留前 768 个字节和 20 个字节的溢出⻚面地址。
  因为字段⻓度偏移列表处只需要记录每个列在本⻚面中的偏移就好了，所以每个列
  使用 2 个字节来存储偏移量就够了。大家可以看出来，Redundant行格式还是比较
  简单粗暴的，直接使用整个记录的真实数据⻓度来决定使用 1 个字节还是 2 个字节存
  储列对应的偏移量。只要整条记录的真实数据占用的存储空间大小大于 127 ，即使
  第一个列的值占用存储空间小于 127 ，那对不起，也需要使用 2 个字节来表示该列对
  应的偏移量。简单粗暴，就是这么简单粗暴（所以这种行格式有些过时了）。为了
  在解析记录时知道每个列的偏移量是使用 1 个字节还是 2 个字节表示的，Redundant
  行格式特意在`记录头信息`里放置了一个称之为 1 byte_offs_flag的属性：
  Redundant行格式中NULL值的处理因为Redundant行格式并没有NULL值列表，所
  以Redundant行格式在字段⻓度偏移列表中的各个列对应的偏移量处做了一些特殊
  处理 ⸺ 将列对应的偏移量值的第一个比特位作为是否为NULL的依据，该比特位
  也可以被称之为NULL比特位。也就是说在解析一条记录的某个列时，首先看一下
  该列对应的偏移量的NULL比特位是不是为 1 。如果为 1 ，那么该列的值就是NULL，
  否则不是NULL。这也就解释了上边介绍为什么只要记录的真实数据大于 127 （十六
  进制 0 x 7 F，二进制 01111111 ）时，就采用 2 个字节来表示一个列对应的偏移量，主
  要是第一个比特位是所谓的NULL比特位，用来标记该列的值是否为NULL。但是还
  有一点要注意，对于值为NULL的列来说，该列的类型是否为定⻓类型决定了NULL
  值的实际存储方式，我们接下来分析一下record_test_table表的第二条记录，它对
  应的字段⻓度偏移列表如下：   A 4 A 4 1 A 17 13 0 C 06 按照列的顺序排放就是： 06
  0 C 13 17 1 A A 4 A 4 我们分情况看一下：如果存储NULL值的字段是定⻓类型的，比
  方说`CHAR(M)`数据类型的，则NULL值也将占用记录的真实数据部分，并把该字
  段对应的数据使用 0 x 00 字节填充。如图第二条记录的c 3 列的值是NULL，而c 3 列的
  类型是`CHAR( 10 )`，占用记录的真实数据部分 10 字节，所以我们看到在
  Redundant行格式中使用 0 x 00000000000000000000 来表示NULL值。另外，c 3
  列对应的偏移量为 0 xA 4 ，它对应的二进制实际是： 10100100 ，可以看到最高位为
  1 ，意味着该列的值是NULL。将最高位去掉后的值变成了 0100100 ，对应的十进制
  值为 36 ，而c 2 列对应的偏移量为 0 x 1 A，也就是十进制的 26 。 36 - 26 = 10 ，也就是
  说最终c 3 列占用的存储空间为 10 个字节。如果该存储NULL值的字段是变⻓数据类
  型的，则不在记录的真实数据处占用任何存储空间。比如record_test_table表的c 4列是VARCHAR( 10 )类型的，VARCHAR( 10 )是一个变⻓数据类型，c 4 列对应的偏移
  量为 0 xA 4 ，与c 3 列对应的偏移量相同，这也就意味着它的值也为NULL，将 0 xA 4
  的最高位去掉后对应的十进制值也是 36 ， 36 - 36 = 0 ，也就意味着c 4 列本身不占
  用任何记录的实际数据处的空间。 除了以上的几点之外，Redundant行格式和
  Compact行格式还是大致相同的。

## 索引的创建与设计原则

### 索引的声明与使用

#### 索引的分类

MySQL的索引包括普通索引、唯一性索引、全文索引、单列索引、多列索引和空间索引等。

从`功能逻辑`上说，索引主要有 4 种，分别是`普通索引、唯一索引、主键索引、全文索引`。

按照`物理实现`方式，索引可以分为 2 种：`聚簇索引和非聚簇索引`。

按照`作用字段个数`进行划分，分成`单列索引和联合索引`。

##### 普通索引

这是最基本的索引，它没有任何限制

##### 唯一索引

与普通索引类似，不同的就是：`索引列的值必须唯一，但允许有空值（注意和主键不同）`。如果是组合索引，则列值的组合必须唯一。可以通过unique关键字创建唯一性约束，使用drop index xxx on table来删除唯一性约束。

##### 主键索引

主键索引建立的规则是 int优于varchar,一般在建表的时候创建,最好是与表的其他字段不相关的列或者是业务不相关的列.一般会设为 int 而且是 AUTO_INCREMENT自增类型的。`主键索引不允许为空`

##### 组合索引

我们也可以同时以多个列的大小作为排序规则，最多16个字段，也就是同时`为多个列建立索引`，比方说我们想让B+树按照 c2和c3列 的大小进行排序，这个包含两层含义：

- 先把各个记录和页按照c2列进行排序。

- 在记录的c2列相同的情况下，采用c3列进行排序

创建一个组合索引会出现多个索引，他们的`Seq_in_index`不同

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220901180021570.png" alt="image-20220901180021570" style="zoom:50%;" />

**最左前缀原则**：只从最左面的索引开始组合。

举例：建立(a,b,c)联合索引

| 查询语句                                    | 索引使用    |
| ------------------------------------------- | ----------- |
| select * from t where a=1 and b=1 and c =1; | a,b,c       |
| select * from t where a=1 and b=1;          | a,b         |
| select * from t where b=1 and a=1;          | a,b(优化器) |
| select * from t where a=1;                  | a           |
| select * from t where b=1 and c=1;          | 用不到      |
| select * from t where a=1 and c=1;          | a           |
| select * from t where a=1 and b>1 and c =1; | a,b         |

##### 全文索引

MySQL从3.23.23版开始支持全文索引和全文检索，FULLTEXT索引仅可用于 MyISAM 表；5.7.6之后innodb支持全文索引；他们可以从CHAR、VARCHAR或TEXT列中作为CREATE TABLE语句的一部分被创建，或是随后使用ALTER TABLE 或CREATE INDEX被添加。`对于较大的数据集，将你的资料输入一个没有FULLTEXT索引的表中，然后创建索引，其速度比把资料输入现有FULLTEXT索引的速度更为快`。不过切记对于大容量的数据表，生成全文索引是一个非常消耗时间非常消耗硬盘空间的做法。

##### 小结：

不同的存储引擎支持的索引类型也不一样 

InnoDB ： 支持 B-tree、Full-text 等索引，不支持 Hash索引； 

MyISAM ： 支持 B-tree、Full-text 等索引，不支持 Hash 索引； 

Memory ： 支持 B-tree、Hash 等索引，不支持 Full-text 索引； 

NDB ： 支持 Hash 索引，不支持 B-tree、Full-text 等索引； 

Archive ： 不支持 B-tree、Hash、Full-text 等索引；

#### 创建索引

##### 创建表的时候创建索引

> 隐式创建索引

```sql
CREATE TABLE emp(
emp_id INT PRIMARY KEY AUTO_INCREMENT,
emp_name VARCHAR( 20 ) UNIQUE,
dept_id INT,
CONSTRAINT emp_dept_id_fk FOREIGN KEY(dept_id) REFERENCES dept(dept_id)
);
```

> 显式创建表时创建索引的话，基本语法格式如下：

```sql
CREATE TABLE table_name [col_name data_type]
[UNIQUE | FULLTEXT | SPATIAL] [INDEX | KEY] [index_name] (col_name [length]) [ASC |
DESC]
```

UNIQUE、FULLTEXT和SPATIAL为可选参数，分别表示唯一索引、全文索引和空间索引；

INDEX与KEY为同义词，两者的作用相同，用来指定创建索引；

index_name指定索引的名称，为可选参数，如果不指定，那么MySQL默认col_name为索引名；

col_name为需要创建索引的字段列，该列必须从数据表中定义的多个列中选择；

length为可选参数，表示索引的长度，只有字符串类型的字段才能指定索引长度；

ASC或DESC指定升序或者降序的索引值存储。

###### 创建普通索引

> 在book表中的year_publication字段上建立普通索引，SQL语句如下：

```sql
CREATE TABLE book(
	book_id INT ,
  book_name VARCHAR( 100 ),
	authors VARCHAR( 100 ),
  info VARCHAR( 100 ) ,
  comment VARCHAR( 100 ),
  year_publication YEAR,
  INDEX(year_publication)
);
```

###### 创建唯一索引

```sql
CREATE TABLE test1(
  id INT NOT NULL,
  name varchar( 30 ) NOT NULL,
  UNIQUE INDEX uk_idx_id(id)
);
```

###### 创建主键索引

> 设定为主键后数据库会自动建立索引，innodb为聚簇索引，语法：随表一起建索引：

```sql
CREATE TABLE student (
  id INT(10) UNSIGNED AUTO_INCREMENT ,
  student_no VARCHAR(200),
  student_name VARCHAR(200),
  PRIMARY KEY(id)
);
```

> 删除主键索引 

```sql
ALTER TABLE student drop PRIMARY KEY ;
```

> 修改主键索引：必须先删除掉(drop)原索引，再新建(add)索引

###### 创建组合索引

> 创建表test3，在表中的id、name和age字段上建立组合索引，SQL语句如下：

```sql
CREATE TABLE test3(
  id INT( 11 ) NOT NULL,
  name CHAR( 30 ) NOT NULL,
  age INT( 11 ) NOT NULL,
  info VARCHAR( 255 ),
  INDEX multi_idx(id,name,age)
);
```

###### 创建全文索引

> 在MySQL5.7及之后版本中可以不指定最后的ENGINE了，因为在此版本中InnoDB支持全文索引。

```sql
CREATE TABLE `papers` (
  `id` int( 10 ) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar( 200 ) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`content`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
```

> 不同于like方式的的查询：

```sql
SELECT * FROM papers WHERE content LIKE ‘%查询字符串%’;
```

> 全文索引用match+against方式查询：

```sql
SELECT * FROM papers WHERE MATCH(title,content) AGAINST (‘查询字符串’);
```

###### 创建空间索引

> 空间索引创建中，要求空间类型的字段必须为非空。
>
> 举例：创建表test5，在空间类型为GEOMETRY的字段上创建空间索引，SQL语句如下：

```sql
CREATE TABLE test5(
  geo GEOMETRY NOT NULL,
  SPATIAL INDEX spa_idx_geo(geo)
) ENGINE=MyISAM;
```

##### 在已经存在的表上创建索引

> 使用ALTER TABLE语句创建索引 ALTER TABLE语句创建索引的基本语法如下：

```sql
ALTER TABLE table_name ADD [UNIQUE | FULLTEXT | SPATIAL] [INDEX | KEY]
[index_name] (col_name[length],...) [ASC | DESC]

alter table student add unique index student_name(student_name);
```

> 使用CREATE INDEX创建索引 CREATE INDEX语句可以在已经存在的表上添加索引，在MySQL中，CREATE INDEX被映射到一个ALTER TABLE语句上，基本语法结构为：

```sql
CREATE [UNIQUE | FULLTEXT | SPATIAL] INDEX index_name
ON table_name (col_name[length],...) [ASC | DESC]

create unique index student_no on student(student_no);
```

#### 查看索引

```sql
SHOW INDEX FROM test1;
```

#### 删除索引

> 使用ALTER TABLE删除索引 ALTER TABLE删除索引的基本语法格式如下：

```sql
ALTER TABLE table_name DROP INDEX index_name;
```
> 使用DROP INDEX语句删除索引 DROP INDEX删除索引的基本语法格式如下：

```sql
DROP INDEX index_name ON table_name;
```

> 删除表中的列时，如果要删除的列为索引的组成部分，则该列也会从索引中删除。如果组成索引的所有列都被删除，则整个索引将被删除。

### MySQL 8. 0 索引新特性

#### 支持降序索引

支持降序索引，可以避免Using filesort，Using filesort是MySQL中一种速度比较慢的外部排序，能避免是最好的。多数情况下，管理员可以通过优化索引来尽量避免出现Using filesort，从而提高数据库执行速度。

#### 隐藏索引

在MySQL 5.7版本及之前，只能通过显式的方式删除索引。此时，如果发现删除索引后出现错误，又只能通过显式创建索引的方式将删除的索引创建回来。如果数据表中的数据量非常大，或者数据表本身比较大，这种操作就会消耗系统过多的资源，操作成本非常高。

从MySQL 8.x开始支持 隐藏索引（invisible indexes） ，只需要将待删除的索引设置为隐藏索引，使查询优化器不再使用这个索引（即使使用force index（强制使用索引），优化器也不会使用该索引），确认将索引设置为隐藏索引后系统不受任何响应，就可以彻底删除索引。 这种通过先将索引设置为隐藏索引，再删除索引的方式就是软删除 。

注意：`主键不能设置为隐藏索引`  

##### 1. 创建表时直接创建 

> 在MySQL中创建隐藏索引通过SQL语句INVISIBLE来实现，其语法形式如下：

```sql
CREATE TABLE tablename(
  propname1 type1[CONSTRAINT1],
  propname2 type2[CONSTRAINT2],
  ......
  propnamen typen,
  INDEX [indexname](propname1 [(length)]) INVISIBLE
);
```

##### 在已经存在的表上创建

```sql
CREATE INDEX indexname
ON tablename(propname[(length)]) INVISIBLE;
```

##### 通过ALTER TABLE语句创建

```sql
ALTER TABLE tablename ADD INDEX indexname (propname [(length)]) INVISIBLE;
```

##### 切换索引可见状态 

> 通过设置隐藏索引的可见性可以查看索引对调优的帮助

```sql
ALTER TABLE tablename ALTER INDEX index_name INVISIBLE; #切换成隐藏索引 
ALTER TABLE tablename ALTER INDEX index_name VISIBLE; #切换成非隐藏索引
```

##### 使隐藏索引对查询优化器可见

在MySQL 8.x版本中，为索引提供了一种新的测试方式，可以通过查询优化器的一个开关（use_invisible_indexes）来打开某个设置，使隐藏索引对查询优化器可见。如果 use_invisible_indexes设置为off(默认)，优化器会忽略隐藏索引。如果设置为on，即使隐藏索引不可见，优化器在生成执行计划时仍会考虑使用隐藏索引。

```sql
select @@optimizer_switch;
set session optimizer_switch = "use_invisible_indexes=on";
```

### 索引的设计原则

#### 数据准备

> 创建课程表并插入100条数据，创建学生表并插入1000000条数据

##### 第 1 步：创建数据库、创建表

```sql
CREATE DATABASE atguigudb1;
USE atguigudb1; 
-- 1.创建学生表和课程表 
CREATE TABLE `student_info`
(
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `student_id`  INT     NOT NULL,
    `name`        VARCHAR(20) DEFAULT NULL,
    `course_id`   INT     NOT NULL,
    `class_id`    INT(11)     DEFAULT NULL,
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `course`
(
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `course_id`   INT     NOT NULL,
    `course_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
```

##### 第 2 步：创建模拟数据必需的存储函数

```sql

-- 函数1：创建随机产生字符串函数
DELIMITER // CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE i < n
        DO
            SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1 + RAND() * 52), 1)); SET i = i + 1;
        END WHILE;
    RETURN return_str;
END // DELIMITER ;

-- 函数2：创建随机数函数
DELIMITER // CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
    DECLARE i INT DEFAULT 0; SET i = FLOOR(from_num + RAND() * (to_num - from_num + 1)); RETURN i;
END // DELIMITER ;
```
创建函数，假如报错：This function has none of DETERMINISTIC.......

解决方法：

```sql
-- 临时解决
set global log_bin_trust_function_creators=1;
-- 永久解决：/etc/my.cnf下my.cnf[mysqld]加上：
log_bin_trust_function_creators=1
```

log_bin_trust_function_creators：主从复制，主机会将写操作记录在bin-log日志中。从机读取bin-log日志，执行语句来同步数据。如果使用函数来操作数据，存储函数有可能导致主从的数据不一致，如uuid()函数，执行语句一样但是数据不一样。所以，默认情况下，mysql不开启创建函数设置，即log_bin_trust_function_creators设置为0。

##### 第 3 步：创建插入模拟数据的存储过程

```sql
-- 存储过程1：创建插入课程表存储过程
DELIMITER // CREATE PROCEDURE insert_course(max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0; SET autocommit = 0; -- 设置手动提交事务
    REPEAT
        SET i = i + 1; -- 赋值
        INSERT INTO course (course_id, course_name) VALUES (rand_num(10000, 10100), rand_string(6));
    UNTIL i = max_num
        END REPEAT;
    COMMIT; -- 提交事务
END // DELIMITER ;

-- 存储过程2：创建插入学生信息表存储过程
DELIMITER // CREATE PROCEDURE insert_stu(max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0; -- 设置手动提交事务
    REPEAT
        SET i = i + 1; -- 赋值
        INSERT INTO student_info (course_id, class_id, student_id, NAME)
        VALUES (rand_num(10000, 10100), rand_num(10000, 10200), rand_num(1, 200000), rand_string(6));
    UNTIL i = max_num END REPEAT;
    COMMIT; -- 提交事务
END //
DELIMITER ;
```

##### 第 4 步：调用存储过程

```sql
CALL insert_course(100);
CALL insert_stu(1000000);
```

#### 哪些情况适合创建索引

##### 字段的数值有唯一性的限制

> 业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引。（来源：Alibaba）
>
> 说明：不要以为唯一索引影响了 insert 速度，这个速度损耗可以忽略，但提高查找速度是明显的。

##### 频繁作为 WHERE 查询条件的字段

> 某个字段在SELECT语句的 WHERE 条件中经常被使用到，那么就需要给这个字段创建索引了。尤其是在数据量大的情况下，创建普通索引就可以大幅提升数据查询的效率。
>
> 比如student_info数据表（含100万条数据），假设我们想要查询 student_id=123110 的用户信息。

##### 经常 GROUP BY 和 ORDER BY 的列

> 索引就是让数据按照某种顺序进行存储或检索，因此当我们使用 GROUP BY 对数据进行分组查询，或者使用 ORDER BY 对数据进行排序的时候，就需要 对分组或者排序的字段进行索引 。如果待排序的列有多个，那么可以在这些列上建立 组合索引 。
>
> `注意：将group by的字段可在前面，order by的字段写在后面。` 

##### UPDATE、DELETE 的 WHERE 条件列

> 对数据按照某个条件进行查询后再进行 UPDATE 或 DELETE 的操作，如果对 WHERE 字段创建了索引，就能大幅提升效率。原理是因为我们需要先根据 WHERE 条件列检索出来这条记录，然后再对它进行更新或删除。**如果进行更新的时候，更新的字段是非索引字段，提升的效率会更明显，这是因为非索引字段更新不需要对索引进行维护。**

##### DISTINCT 字段需要创建索引

>  `连接表的数量尽量不要超过 3 张` ，因为每增加一张表就相当于增加了一次嵌套的循环，数量级增长会非常快，严重影响查询的效率。
>
> `对 WHERE 条件创建索引 `，因为 WHERE 才是对数据条件的过滤。如果在数据量非常大的情况下，没有 WHERE 条件过滤是非常可怕的。
>
>  `对用于连接的字段创建索引` ，并且该字段在多张表中的 类型必须一致 。比如 course_id 在 student_info 表和 course 表中都为 int(11) 类型，而不能一个为 int 另一个为 varchar 类型。否则会使用到转换函数导致索引失效。

#####  使用列的类型小的创建索引

##### 使用字符串前缀创建索引 

> 创建一张商户表，因为地址字段比较长，在地址字段上建立前缀索引

```sql
create table shop(address varchar(120) not null); 
alter table shop add index(address(12));
```

问题是，截取多少呢？截取得多了，达不到节省索引存储空间的目的；截取得少了，重复内容太多，字段的散列度(选择性)会降低。 怎么计算不同的长度的选择性呢？先看一下字段在全部数据中的选择度，通过不同长度去计算，与全表的选择性对比：`越接近于1越好`

```sql
count(distinct left(列名, 索引长度))/count(*) 

select count(distinct left(address,10)) / count(*) as sub10, -- 截取前10个字符的选择度 
count(distinct left(address,15)) / count(*) as sub11, -- 截取前15个字符的选择度 
count(distinct left(address,20)) / count(*) as sub12, -- 截取前20个字符的选择度 
count(distinct left(address,25)) / count(*) as sub13 -- 截取前25个字符的选择度
from shop;
```
注意：`索引列前缀对排序的影响`，如：select * from shop order by address limit 10; 如果正好截取的前12个字符都是一样的，那么无法进行排序。

拓展：Alibaba《Java开发手册》

【强制】在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度。

说明：索引的长度与区分度是一对矛盾体，一般对字符串类型数据，长度为 20 的索引，区分度会高达90% 以上，可以使用 count(distinct left(列名, 索引长度))/count(*)的区分度来确定。

##### 区分度高(散列性高)的列适合作为索引

##### 使用最频繁的列放到联合索引的左侧

> 这样也可以较少的建立一些索引。同时，由于"最左前缀原则"，可以增加联合索引的使用率。

##### 在多个字段都要创建索引的情况下，联合索引优于单值索引

#### 限制索引的数目

`单表数量不超过6个`，原因：

1. 占用磁盘空间
2. 每次修改数据，需要更新索引，消耗时间
3. 优化器选择索引，会根据每一个可以用到的索引进行评估，生成一个最好的执行计划。如果存在多个索引，会增加MySQL优化器生成执行计划的时间，降低性能。

#### 哪些情况不适合创建索引

##### 在where中使用不到的字段，不要设置索引

##### 数据量小的表最好不要使用索引

> 在数据表中的数据行数比较少的情况下，比如不到 1000 行，是不需要创建索引的。

##### 有大量重复数据的列上不要建立索引

##### 避免对经常更新的表创建过多的索引

##### 不建议用无序的值作为索引

> 例如身份证、UUID(在索引比较时需要转为ASCII，并且插入时可能造成页分裂)、MD5、HASH、无序长字符串等。

##### 删除不再使用或者很少使用的索引

##### 不要定义冗余或重复的索引

###### 冗余索引

> 我们知道，通过 idx_name_birthday_phone_number 索引就可以对 name 列进行快速搜索，再创建一个专门针对 name 列的索引就算是一个 冗余索引 ，维护这个索引只会增加维护的成本，并不会对搜索有什么好处。

```sql
CREATE TABLE person_info
(
    id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    birthday     DATE         NOT NULL,
    phone_number CHAR(11)     NOT NULL,
    country      varchar(100) NOT NULL,
    PRIMARY KEY (id),
    KEY idx_name_birthday_phone_number (name(10), birthday, phone_number),
    KEY idx_name (name(10))
);
```
###### 重复索引

> 我们看到，col1 既是主键、又给它定义为一个唯一索引，还给它定义了一个普通索引，可是主键本身就会生成聚簇索引，所以定义的唯一索引和普通索引是重复的，这种情况要避免。

```sql
CREATE TABLE repeat_index_demo
(
    col1 INT PRIMARY KEY,
    col2 INT,
    UNIQUE uk_idx_c1 (col1),
    INDEX idx_c1 (col1)
);
```
## 性能分析工具的使用

### 数据库服务器的优化步骤

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221015203808519.png" alt="image-20221015203808519" style="zoom:70%;" />





### 查看系统性能参数

> 在MySQL中，可以使用SHOW STATUS语句查询一些MySQL数据库服务器的性能参数、执行频率。
>
> SHOW STATUS语句语法如下：

```sql
SHOW [GLOBAL|SESSION] STATUS LIKE '参数';
```

 • Connections：连接MySQL服务器的次数。 

• Uptime：MySQL服务器的上线时间。 

• Slow_queries：慢查询的次数。 

• Innodb_rows_read：Select查询返回的行数 

•Innodb_rows_inserted：执行INSERT操作插入的行数 

• Innodb_rows_updated：执行UPDATE操作更新的行数 

• Innodb_rows_deleted：执行DELETE操作删除的行数 

• Com_select：查询操作的次数。 

•Com_insert：插入操作的次数。对于批量插入的 INSERT 操作，只累加一次。 

• Com_update：更新操作的次数。

 • Com_delete：删除操作的次数。

### 统计SQL的查询成本：last_query_cost

> sql查询使用了多少个页

```sql
 SHOW STATUS LIKE 'last_query_cost';
```

页的数量是刚才的 20 倍，但是查询的效率并没有明显的变化，实际上这两个 SQL 查询的时间基本上一样，就是因为采用了顺序读取的方式将页面一次性加载到缓冲池中，然后再进行查找。虽然 页 数量（last_query_cost）增加了不少 ，但是通过缓冲池的机制，并 没有增加多少查询时间 。

### 定位执行慢的 SQL：慢查询日志

#### 开启慢查询日志参数

> 开启slow_query_log，同时文件保存在 /var/lib/mysql/atguigu02-slow.log文件

```sql
set global slow_query_log='ON';
show variables like 'slow_query_log';
```

> 修改long_query_time阈值，设置为 1 秒

```sql
set global long_query_time = 1;
show global variables like '%long_query_time%';
```

> 查看慢查询数目

```sql
show status like 'slow_queries';
```

#### 慢查询日志分析工具：mysqldumpslow

> mysqldumpslow 命令的具体参数如下：

- a: 不将数字抽象成N，字符串抽象成S

- -s: 是表示按照何种方式排序：

  c: 访问次数

  l: 锁定时间

  r: 返回记录

  t: 查询时间

  al:平均锁定时间

  ar:平均返回记录数

  at:平均查询时间 （默认方式）

  ac:平均查询次数

- -t: 即为返回前面多少条的数据；

- -g: 后边搭配一个正则匹配模式，大小写不敏感的；

> 举例：我们想要按照查询时间排序，查看前五条 SQL 语句，这样写即可：/var/lib/mysql/atguigu01-slow.log为日志文件

```sql
mysqldumpslow -s t -t 5 /var/lib/mysql/atguigu01-slow.log
```

#### 关闭慢查询日志

> MySQL服务器停止慢查询日志功能有两种方法：
>
> 方式 1 ：永久性方式：修改日志文件，或者注释掉该项配置
>
> ```sql
> [mysqld] slow_query_log=OFF 
> [mysqld] #slow_query_log =OFF
> ```
>
> 方式 2 ：临时性方式
>
> ```sql
> SET GLOBAL slow_query_log=off;
> ```

#### 删除慢查询日志

- 直接手动删除日志

- 使用命令重新生成慢查询日志文件

  ```
  mysqladmin -uroot -p flush-logs slow
  ```

### 查看 SQL 执行成本：SHOW PROFILE

> 开启 show profile：

```sql
show variables like 'profiling';
set profiling='ON';
```

> 查看所有执行成本

```sql
show profiles;
```

> 查看指定查询语句的执行成本

```sql
show profile for query 1;
```

> show profile的常用查询参数：
>
> ① ALL：显示所有的开销信息。 
>
> ② BLOCK IO：显示块IO开销。 
>
> ③ CONTEXT SWITCHES：上下文切换开销。
>
> ④ CPU：显示CPU开销信息。 
>
> ⑤ IPC：显示发送和接收开销信息。 
>
> ⑥ MEMORY：显示内存开销信息。 
>
> ⑦ PAGE FAULTS：显示页面错误开销信息。 
>
> ⑧ SOURCE：显示和Source_function，Source_file，Source_line相关的开销信息。 
>
> ⑨ SWAPS：显示交换次数开销信息。

### 分析查询语句：EXPLAIN

#### 版本情况

> MySQL 5.6.3以前只能EXPLAIN SELECT；MYSQL 5.6.3以后就可以EXPLAIN SELECT，UPDATE，DELETE
>
> 在5.7以前的版本中，想要显示partitions需要使用explain partitions命令；想要显示filtered需要使用explain extended命令。在5.7版本后，默认explain直接显示partitions和filtered中的信息。

#### EXPLAIN各列作用

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017114802987.png" alt="image-20221017114802987" style="zoom:50%;" />

##### table

> 不论我们的查询语句有多复杂，里边儿包含了多少个表，到最后也是需要对每个表进行单表访问的，所以MySQL规定 EXPLAIN语句输出的每条记录都对应着某个单表的访问方法 ，该条记录的table列代表着该表的表名（有时不是真实的表名字，可能是简称）。
>
> 多表关联查询，放在上面的是驱动表，下面的是被驱动表。

##### id

> - 我们写的查询语句一般都以 SELECT 关键字开头，比较简单的查询语句里只有一个 SELECT 关键字，那就只有一个序号，都是1，如：
>
>   ```sql
>   select * from student_info where student_id = '106517'
>   ```
>
> - 如果有多个select，那么序号从1开始有多个，如下有两个
>
>   ```sql
>   SELECT * FROM s1 WHERE key1 IN (SELECT key1 FROM s2) OR key3 = 'a';
>   ```
>
> - 如果查询器优化，可能多个select有1个序号，如下将子查询优化成了多表查询
>
>   ```sql
>   select * from student_info where id in(select id from student_info where student_id = '130867')
>   ```
>
> - UNION去重会新增一行，使用了临时表，id为null，如图，UNION ALL则没有。
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017134519811.png" alt="image-20221017134519811" style="zoom:50%;" />

小结:

- id如果相同，可以认为是一组，从上往下顺序执行

- 在所有组中，id值越大，优先级越高，越先执行

- 关注点，id号每个号码，表示一趟独立的查询，一个sql的查询趟数越少越好

##### select_type

> 确定小查询在大查询中扮演了什么样的角色

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017134835664.png" alt="image-20221017134835664" style="zoom:50%;" />

###### SIMPLE

> 不包含UNION和子查询的语句，连接查询也可以算是SIMPLE

###### PRIMARY

> 对于包含UNION或者子查询的语句，最左边或者是最上面的（也就是主表）的类型是PRIMARY

###### UNION

> 语句中 UNION 或者 UNION ALL 关键字后面的查询类型

###### UNION RESULT

> 语句中 UNION合并成的临时表

###### SUBQUERY

> 子查询：如果子查询不能优化为多表查询，且子查询没有使用主表的条件（不相关子查询），则子查询的类型为SUBQUERY
>
> ```sql
> EXPLAIN SELECT * FROM s1 WHERE key1 IN (SELECT key1 FROM s2) OR key3 = 'a';
> ```

###### DEPENDENT SUBQUERY

> 子查询：如果子查询不能优化为多表查询，且子查询使用了主表的条件（相关子查询），则子查询的类型为DEPENDENT SUBQUERY
>
> ```sql
> EXPLAIN SELECT * FROM s1 WHERE key1 IN (SELECT key1 FROM s2 WHERE s1.key2 = s2.key2) OR key3 = 'a';
> ```

###### DEPENDENT UNION

> 子查询中使用了UNION，UNION之后的查询为DEPENDENT UNION
>
> ```sql
> EXPLAIN SELECT * FROM s1 WHERE key1 IN 
> (SELECT key1 FROM s2 WHERE key1 = 'a' 
>  UNION 
>  SELECT key1 FROM s1 WHERE key1 = 'b');
> ```

###### DERIVED

> 派生：如下中的derived_s1表为派生表，所以类型为DERIVED
>
> ```sql
> EXPLAIN SELECT * FROM (SELECT key1, count(*) as c FROM s1 GROUP BY key1) AS derived_s1 where c > 1;
> ```

###### MATERIALIZED

> 物化：子查询不使用查询条件，相当于把子查询中的表某个字段的所有数据查出来
>
> ```sql
> EXPLAIN SELECT * FROM s1 WHERE key1 IN (SELECT key1 FROM s2);
> ```

###### UNCACHEABLE SUBQUERY

不常用，就不多说了。

###### UNCACHEABLE UNION

不常用，就不多说了。



##### partitions (可略)

> 表示查询中表的分区命中情况，非分区表为null。
>
> ```sql
> -- 创建分区表，
> -- 按照id分区，id<100 p0分区，其他p1分区 
> CREATE TABLE user_partitions(
>     id   INT auto_increment,
>     NAME VARCHAR(12),
>     PRIMARY KEY (id)
> ) PARTITION BY RANGE (id)( PARTITION p0 VALUES less than (100), PARTITION p1 VALUES less than MAXVALUE );
> 
> EXPLAIN SELECT * FROM user_partitions WHERE id>200;
> ```

##### type ☆

> 完整的访问方法如下：system，const，eq_ref，ref，fulltext，ref_or_null，index_merge，unique_subquery，index_subquery，range，index，ALL。

###### system

> 当表中只有一条数据，并且该表使用的存储引擎的统计数据是精确的，如MyISAM、Memory，type则为system

###### const

> 当对于主键索引或者唯一索引进行常数等值匹配时，为const

###### eq_ref

> 多表联合查询，如果被驱动表是通过主键或者唯一索引与主表等值关联（如果是组合索引那么多个列都要关联），使用 eq_ref

###### ref

> 普通索引等值查询，使用ref

###### fulltext

> 全文索引

###### ref_or_null

> 通过普通索引进行等值查询，该字段可以为null
>
> ```sql
> explain select * from student_info where class_id = '10039' or class_id is null;
> ```

###### index_merge

> 多个列都有普通索引，并且用Or查询，mysql合并索引进行查询
>
> ```sql
>  EXPLAIN SELECT * FROM s1 WHERE key1 = 'a' OR key3 = 'a';
> ```

###### unique_subquery

> 针对包含in的查询语句，如果优化器将 in 优化为exists，并且子查询中使用主键进行等值查询

###### index_subquery

###### range

> 使用区间范围查询
>
> ```sql
> EXPLAIN SELECT * FROM s1 WHERE key1 > 'a' AND key1 < 'b';
> ```

###### index

> 当可以使用索引覆盖，但是需要扫描所有索引记录时，使用index。
>
> 索引覆盖：指查询的字段和where条件的字段在一个索引中，可以直接通过索引进行查询，不用回表操作。如：
>
> ```sql
> EXPLAIN SELECT key_part2 FROM s1 WHERE key_part3 = 'a';
> ```
>
> s1表有idx1(key_part1, key_part2, key_part3)索引，可以直接通过索引获取数据，不用回表。

###### ALL

> 全表扫描

###### 小结

> 结果值从最好到最坏依次是： system > const > eq_ref > ref > fulltext > ref_or_null > index_merge >unique_subquery > index_subquery > range > index > ALL 其中比较重要的几个提取出来（见上图中的蓝色）。
>
> SQL 性能优化的目标：`至少要达到 range 级别`，要求是 ref 级别，最好是 consts级别。（阿里巴巴开发手册要求）

##### possible_keys和key

> possible_keys可能使用的索引；
>
> key真实使用的索引；
>
> 两者并不是父子集合的关系

##### key_len ☆

> 实际使用到的索引字节长度，值越大越好，主要针对联合索引。
>
> key_len的长度计算公式：
>
> varchar(10)变长字段且允许NULL = 10 * ( character set： 
>
> utf8=3,gbk=2,latin1=1)+1(NULL)+2(变长字段) 
>
> varchar(10)变长字段且不允许NULL = 10 * ( character set：utf8=3,gbk=2,latin1=1)+2(变长字段) 
>
> char(10)固定字段且允许NULL = 10 * ( character set：utf8=3,gbk=2,latin1=1)+1(NULL) 
>
> char(10)固定字段且不允许NULL = 10 * ( character set：utf8=3,gbk=2,latin1=1) 

##### ref

> 当使用索引等值查询时，与索引列等值匹配的对象的信息。
>
> ```sql
> -- 匹配信息为常量
> EXPLAIN SELECT * FROM s1 WHERE key1 = 'a';
> -- 匹配信息为函数
> EXPLAIN SELECT * FROM s1 INNER JOIN s2 ON s2.key1 = UPPER(s1.key1);
> -- 匹配信息为s2.id
> EXPLAIN SELECT * FROM s1 INNER JOIN s2 ON s1.id = s2.id;
> ```

##### rows ☆

> 使用索引，预计需要读取条目数量，值越小越好

##### filtered

> 满足要求的条数 / rows，越大越好。主要针对联合查询进行优化，如下：9688 * 10% = 969，意味着969条数据要进行  s1.key1 = s2.key1  等值匹配
>
> ```sql
> EXPLAIN SELECT * FROM s1 INNER JOIN s2 ON s1.key1 = s2.key1 WHERE s1.common_field = 'a';
> ```
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017182831714.png" alt="image-20221017182831714" style="zoom:50%;" />

##### Extra ☆

> 用于说明Mysql如何执行sql语句

###### Impossible WHERE

```sql
EXPLAIN SELECT * FROM s1 WHERE 1 != 1;
```

###### Using where

```sql
EXPLAIN SELECT * FROM s1 WHERE common_field = 'a';
```
###### No matching min/max row

```sql
EXPLAIN SELECT MIN(key1) FROM s1 WHERE key1 = 'abcdefg';
```

###### Using index

> 使用索引覆盖，即索引中包含需要查询的字段和where条件的字段，不需要回表

```sql
EXPLAIN SELECT key1 FROM s1 WHERE key1 = 'a';
```

###### Using index condition

> 搜索条件中出现了索引列，但是不能用到索引。如下sql会先使用 key1索引找出符合条件的数据，在根据 key1进一步筛选，最后在回表找所有字段。这个操作也叫索引条件下推，也就是Using index condition

```sql
SELECT * FROM s1 WHERE key1 > 'z' AND key1 LIKE '%a';
```

###### Using join buffer (Block Nested Loop)

```sql
 EXPLAIN SELECT * FROM s1 INNER JOIN s2 ON s1.common_field = s2.common_field;
```

###### Not exists

> id不可能为null，但是条件使用了is null

```sql
EXPLAIN SELECT * FROM s1 LEFT JOIN s2 ON s1.key1 = s2.key1 WHERE s2.id IS NULL;
```

###### Using intersect(...)、Using union(...)和Using sort_union(...)

> 类似合并

```sql
EXPLAIN SELECT * FROM s1 WHERE key1 = 'a' OR key3 = 'a';
```

###### Zero limit

```sql
EXPLAIN SELECT * FROM s1 LIMIT 0;
```

###### Using filesort

> 字段排序

```sql
EXPLAIN SELECT * FROM s1 ORDER BY key1 LIMIT 10;
```

###### Using temporary

> 使用临时表

```sql
EXPLAIN SELECT DISTINCT common_field FROM s1;
```

##### 小结

- EXPLAIN不考虑各种Cache 
- EXPLAIN不能显示MySQL在执行查询时所作的优化工作
- EXPLAIN不会告诉你关于触发器、存储过程的信息或用户自定义函数对查询的影响情况
- 部分统计信息是估算的，并非精确值

### EXPLAIN的进一步使用

#### EXPLAIN四种输出格式

> EXPLAIN可以输出四种格式：传统格式，JSON格式，TREE格式以及可视化输出。用户可以根据需要选择适用于自己的格式。

##### 传统格式

> 传统格式简单明了，输出是一个表格形式，概要说明查询计划。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017200714419.png" alt="image-20221017200714419" style="zoom:50%;" />

##### JSON格式

> 在EXPLAIN单词和真正的查询语句中间加上FORMAT=JSON。

##### TREE格式

> TREE格式是8.0.16版本之后引入的新格式，主要根据查询的各个部分之间的关系和各部分的执行顺序来描述如何查询。
>
> 在EXPLAIN单词和真正的查询语句中间加上FORMAT=TREE。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221017203249907.png" alt="image-20221017203249907" style="zoom:50%;" />

##### 可视化输出

> 可视化输出，可以通过MySQL Workbench可视化查看MySQL的执行计划。通过点击Workbench的放大镜图标，即可生成可视化的查询计划。
>
> 上图按从左到右的连接顺序显示表。红色框表示 全表扫描 ，而绿色框表示使用 索引查找 。对于每个表，显示使用的索引。还要注意的是，每个表格的框上方是每个表访问所发现的行数的估计值以及访问该表的成本。

#### SHOW WARNINGS的使用

> 可以显示优化器进行优化后的执行语句。如：子查询优化为联合查询、in优化为exists
>
> 使用方法：explain + sql语句 执行后，使用show warnings;


### MySQL监控分析视图-sys schema

#### Sys schema视图摘要

> **1.** **主机相关：**以host_summary开头，主要汇总了IO延迟的信息。
>
> **2. Innodb****相关：**以innodb开头，汇总了innodb buffer信息和事务等待innodb锁的信息。
>
> **3. I/o****相关：**以io开头，汇总了等待I/O、I/O使用量情况。
>
> **4.** **内存使用情况：**以memory开头，从主机、线程、事件等角度展示内存的使用情况
>
> **5.** **连接与会话信息：**processlist和session相关视图，总结了会话相关信息。
>
> **6.** **表相关：**以schema_table开头的视图，展示了表的统计信息。
>
> **7.** **索引信息：**统计了索引的使用情况，包含冗余索引和未使用的索引情况。
>
> **8.** **语句相关：**以statement开头，包含执行全表扫描、使用临时表、排序等的语句信息。
>
> **9.** **用户相关：**以user开头的视图，统计了用户使用的文件I/O、执行语句统计信息。
>
> **10.** **等待事件相关信息：**以wait开头，展示等待事件的延迟情况。

#### Sys schema视图使用场景

##### 索引情况

```sql
#1. 查询冗余索引
select * from sys.schema_redundant_indexes;
#2. 查询未使用过的索引
select * from sys.schema_unused_indexes;
#3. 查询索引的使用情况
select index_name,rows_selected,rows_inserted,rows_updated,rows_deleted
from sys.schema_index_statistics where table_schema='dbname' ;
```
##### 表相关

```sql
# 1. 查询表的访问量
select table_schema,table_name,sum(io_read_requests+io_write_requests) as io from
sys.schema_table_statistics group by table_schema,table_name order by io desc;
# 2. 查询占用bufferpool较多的表
select object_schema,object_name,allocated,data
from sys.innodb_buffer_stats_by_table order by allocated limit 10 ;
# 3. 查看表的全表扫描情况
select * from sys.statements_with_full_table_scans where db='dbname';
```
##### 语句相关

```sql
#1. 监控SQL执行的频率
select db,exec_count,query from sys.statement_analysis
order by exec_count desc;
```
```sql
#2. 监控使用了排序的SQL
select db,exec_count,first_seen,last_seen,query
from sys.statements_with_sorting limit 1 ;
```
```sql
#3. 监控使用了临时表或者磁盘临时表的SQL
select db,exec_count,tmp_tables,tmp_disk_tables,query
from sys.statement_analysis where tmp_tables> 0 or tmp_disk_tables > 0
order by (tmp_tables+tmp_disk_tables) desc;
```

##### IO相关

```sql
#1. 查看消耗磁盘IO的文件
select file,avg_read,avg_write,avg_read+avg_write as avg_io
from sys.io_global_by_file_by_bytes order by avg_read  limit 10 ;
```
##### Innodb 相关

```sql
#1. 行锁阻塞情况
select * from sys.innodb_lock_waits;
```

## 索引优化与查询优化

### 数据准备

> 学员表插 50 万条，班级表插 1 万条。

#### 步骤 1 ：建表

```sql
CREATE TABLE `class`
(
    `id`        INT(11) NOT NULL AUTO_INCREMENT,
    `className` VARCHAR(30) DEFAULT NULL,
    `address`   VARCHAR(40) DEFAULT NULL,
    `monitor`   INT     NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `student`
(
    `id`      INT(11) NOT NULL AUTO_INCREMENT,
    `stuno`   INT     NOT NULL,
    `name`    VARCHAR(20) DEFAULT NULL,
    `age`     INT(3)      DEFAULT NULL,
    `classId` INT(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
#         CONSTRAINT `fk_class_id` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
```

#### 步骤 2 ：设置参数

> 命令开启：允许创建函数设置

```sql
set global log_bin_trust_function_creators=1; # 不加global只是当前窗口有效。
```

#### 步骤 3 ：创建函数

> 保证每条数据都不同

```sql
#随机产生字符串 
DELIMITER // CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT ''; DECLARE i INT DEFAULT 0;
    WHILE i < n
        DO
            SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1 + RAND() * 52), 1)); SET i = i + 1;
        END WHILE;
    RETURN return_str;
END // DELIMITER ;

# 随机产生班级编号
DELIMITER // CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET i = FLOOR(from_num + RAND() * (to_num - from_num + 1));
    RETURN i;
END // DELIMITER ; 
```

#### 步骤 4 ：创建存储过程

> 创建往stu表中插入数据的存储过程 

```sql
DELIMITER // CREATE PROCEDURE insert_stu(START INT, max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0; #设置手动提交事务 
    REPEAT
        #循环 
        SET i = i + 1; #赋值 
        INSERT INTO student (stuno, name, age, classId)
        VALUES ((START + i), rand_string(6), rand_num(1, 50), rand_num(1, 1000));
    UNTIL i = max_num END REPEAT;
    COMMIT; #提交事务 
END //
DELIMITER ;
```
> 创建往class表中插入数据的存储过程

```sql
#执行存储过程，往class表添加随机数据 
DELIMITER // CREATE PROCEDURE `insert_class`(max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0; SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        INSERT INTO class (classname, address, monitor) VALUES (rand_string(8), rand_string(10), rand_num(1, 100000));
    UNTIL i = max_num END REPEAT;
    COMMIT;
END // DELIMITER ;
```

#### 步骤 5 ：调用存储过程

```sql
#执行存储过程，往class表添加1万条数据
CALL insert_class(10000);
#执行存储过程，往stu表添加50万条数据
CALL insert_stu1(100000,500000);
```

#### 步骤 6 ：删除某表上的索引

```sql
DELIMITER // CREATE PROCEDURE `proc_drop_index`(dbname VARCHAR(200), tablename VARCHAR(200))
BEGIN
    DECLARE done INT DEFAULT 0; DECLARE ct INT DEFAULT 0; DECLARE _index VARCHAR(200) DEFAULT '';
    DECLARE _cur CURSOR FOR SELECT index_name
                            FROM information_schema.STATISTICS
                            WHERE table_schema = dbname
                              AND table_name = tablename
                              AND seq_in_index = 1
                              AND index_name <> 'PRIMARY';
    #每个游标必须使用不同的declare continue handler for not found set done=1来控制游标的结束
    DECLARE CONTINUE HANDLER FOR NOT FOUND set done = 2;
    #若没有数据返回,程序继续,并将变量done设为2
    OPEN _cur; FETCH _cur INTO _index;
    WHILE _index <> ''
        DO
            SET @str = CONCAT("drop index ", _index, " on ", tablename); PREPARE sql_str FROM @str; EXECUTE sql_str;
            DEALLOCATE PREPARE sql_str; SET _index = ''; FETCH _cur INTO _index;
        END WHILE;
    CLOSE _cur;
END //
DELIMITER ;

CALL proc_drop_index("dbname", "tablename");
```

### 索引失效案例

#### 全值匹配我最爱

> 如果存在多个字段等值查询，创建联合索引的效率比单个字段索引的效率要高。优化器会优先考虑使用联合索引。

#### 最佳左前缀法则

> 过滤条件顺序要按照索引顺序进行过滤，如果跳过了某个字段，那么该字段后面的都不会使用。
>
> **拓展：Alibaba《Java开发手册》**
>
> 索引文件具有 B-Tree 的最左前缀匹配特性，如果左边的值未确定，那么无法使用此索引。

#### 运算、函数导致索引失效

##### 使用了运算

> WHERE stuno+1 = 900001; 导致索引失效

```sql
CREATE INDEX idx_sno ON student(stuno);
EXPLAIN SELECT SQL_NO_CACHE id, stuno, NAME FROM student WHERE stuno+1 = 900001;
```

##### 使用了函数

```sql
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE LEFT(student.name,3) = 'abc';
```

#### 类型转换(自动或手动)导致索引失效

> name为varchar类型

```sql
# 未使用到索引 
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE name=123; 
# 使用到索引 
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE name='123';
```

#### 范围条件右边的列索引失效

> classid范围搜索，那么索引中classid后面的列都不会用到索引。因此，创建联合索引时，要把范围字段放在最后面。

```sql
create index idx_age_classid_name on student(age,classid,name);

EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.age=30 AND student.classId>20 AND student.name = 'abc' ; 

-- 解决方法：将范围查询条件放置语句最后
create index idx_age_name_classid on student(age,name,classid);
```
#### 不等于(!= 或者<>)索引失效

> 失效的原因是如果使用索引，还要进行回表操作，不如直接全表扫描。例外：`如果是索引覆盖的情况，可以使用索引，因为省去了回表的时间。`类似的，not like、not in、is not null也会导致索引失效

#### is null可以使用索引，is not null无法使用索引

> 尽量使用 not null约束，如果存在要为空的字段，数字可以使用0，字符串使用‘’

#### like以通配符%开头索引失效

> **拓展：Alibaba《Java开发手册》**
>
> 【强制】页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决。
>
> `注意：如果是索引覆盖，则可以使用索引，因为不用回表`

#### OR 前后存在非索引的列，索引失效

> 如果age有索引，classid没有索引（联合索引不以classid为最左的不算），下列sql不走索引
>
> ```sql
> EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 10 OR classid = 100;
> ```

#### 数据库和表的字符集统一使用utf8mb4

> 统一使用utf8mb4( 5.5.3版本以上支持)兼容性更好，统一字符集可以避免由于字符集转换产生的乱码。`不同的 字符集 进行比较前需要进行 转换 会造成索引失效`。

### 关联查询优化

#### 采用左外连接

> LEFT JOIN条件用于确定如何从右表搜索行，左边一定都有，所以 `右边是我们的关键点,一定需要建立索引`

#### 采用内连接

> inner join自动选择驱动表
>
> 如果两个表连接查询，其中只有一个表有索引，那么这个有索引的表会作为被驱动表；
>
> 如果两个都有索引，那么会选择数量多的作为被驱动表，即小表驱动大表；
>
> 如果大表有索引，小表没有索引，大表作为驱动表

#### join语句原理

##### 驱动表和被驱动表

> 驱动表就是主表，被驱动表就是从表。
>
> - 内连接：select * from A join B ...一般小表驱动大表
> - 外连接：不一定前一个是主表，优化器会将有索引的或者大表作为被驱动表

##### Simple Nested-Loop Join（简单嵌套循环连接）

> 整体流程：从表A中取出一条数据，和表B中的数据一条条对比，匹配上就将B的数据放进result中。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221018172357124.png" alt="image-20221018172357124" style="zoom:50%;" />
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221018172539102.png" alt="image-20221018172539102" style="zoom:50%;" />

##### Index Nested-Loop Join（索引嵌套循环连接）

> 驱动表每条记录通过索引匹配，如果不是主键索引，要进行回表操作。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221018172639116.png" alt="image-20221018172639116" style="zoom:50%;" />
>
> Index(Height)表示索引高度
>
> <img src="/Users/leon_chiang/Library/Application%20Support/typora-user-images/image-20221018172840364.png" alt="image-20221018172840364" style="zoom:50%;" />

##### Block Nested-Loop Join（块嵌套循环连接）

> 不再是一条条读取驱动表数据，而是按块读取，引入了join buffer缓冲区。将驱动表数据缓存到缓冲区中，按照块和被驱动表数据进行匹配，降低了被驱动表的访问率。
>
> 注意：缓冲区中存放select后面的列，不用到的列不要select出来，避免select * 的写法，可以让缓冲区存放更多条数据。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221018174634004.png" alt="image-20221018174634004" style="zoom:50%;" />
>
> 参数设置：
>
> block_nested_loop：是否开启块嵌套循环，show variables like '%optimizer_switch%'中修改
>
> join_buffer_size：缓存池大小设置，默认256KB，show variables like '%join_buffer_size%';

#### join小结

- 效率对比：INLJ > BNLJ > SNLJ
- 小结果集驱动大结果集（不是表的大小，而是 筛选后的行数 * 单行select查询的字节数大小 ）

- 保证被驱动表的JOIN字段已经创建了索引

- 需要JOIN 的字段，数据类型保持绝对一致。

- 能够直接多表关联的尽量直接关联，不用子查询。(减少查询的趟数)

- 衍生表建不了索引

#### hash join介绍

> 在Mysql8.0.20弃用了BNLJ的方式，采用hash join的方式进行匹配。
>
> hash join将较小的结果集的join key存入到内存中，建立散列表进行匹配


### 子查询优化

> MySQL从4.1版本开始支持子查询，使用子查询可以进行SELECT语句的嵌套查询，即一个SELECT查询的结果作为另一个SELECT语句的条件。 子查询可以一次性完成很多逻辑上需要多个步骤才能完成的SQL操作 。
>
> **子查询是** **MySQL** **的一项重要的功能，可以帮助我们通过一个** **SQL** **语句实现比较复杂的查询。但是，子查询的执行效率不高。**
>
> 原因：
>
> ① 执行子查询时，MySQL需要为内层查询语句的查询结果 `建立一个临时表` ，然后外层查询语句从临时表中查询记录。查询完毕后，再 `撤销这些临时表` 。这样会消耗过多的CPU和IO资源，产生大量的慢查询。
>
> ② 子查询的结果集存储的临时表，不论是内存临时表还是磁盘临时表都 `不会存在索引` ，所以查询性能会受到一定的影响。
>
> ③ 对于返回结果集比较大的子查询，其对查询性能的影响也就越大。
>
> **在MySQL中，可以使用连接（JOIN）查询来替代子查询。**连接查询 不需要建立临时表 ，其 速度比子查询 要快 ，如果查询中使用索引的话，性能就会更好。
>
> 提示：`尽量不要使用NOT IN 或者 NOT EXISTS，用LEFT JOIN xxx ON xx WHERE xx 替代`，如：
>
> ```sql
> select count(1) from student s where s.classId not in ( select c.id from class c where c.className like '高三%');
> 
> select count(1) from student left join class on student.classId = class.id where class.className not like '高三%';
> ```

### 排序优化

#### 排序优化

> 在 ORDER BY 字段上加索引
>
> 1. SQL 中，可以在 WHERE 子句和 ORDER BY 子句中使用索引，目的是在 WHERE 子句中 避免全表扫 描 ，在 ORDER BY 子句 `避免使用 FileSort 排序`  。当然，某些情况下全表扫描，或者 FileSort 排序不一定比索引慢，比如通过索引查出来行数还是很多，每一行都要回表再次查询，还不如使用全表扫描，FileSort 内存排序可能更快。但总的来说，我们还是要避免，以提高查询效率。
>
> 2. 尽量使用 Index 完成 ORDER BY 排序。如果 WHERE 和 ORDER BY 后面是相同的列就使用单索引列；如果不同就使用联合索引。
>
> 3. 无法使用 Index 时，需要对 FileSort 方式进行调优。
>
> `注意索引失效：`
>
> 1. order by 和索引顺序不一样，用不到索引
> 2. 排序方向和索引相反 desc 也用不到索引，如果order by都使用desc则可以使用索引：如 index(age, name)，sql：order by age desc, name desc 则可以使用，单独一个desc则不可以使用索引
>
> 举例：
>
> ```sql
>  INDEX a_b_c(a,b,c) order by 
> -- 能使用索引最左前缀
> - ORDER BY a
> - ORDER BY a,b
> - ORDER BY a,b,c
> - ORDER BY a DESC,b DESC,c DESC 
> 
> -- 如果WHERE使用索引的最左前缀定义为常量，则order by 能使用索引
> - WHERE a = const ORDER BY b,c
> - WHERE a = const AND b = const ORDER BY c
> - WHERE a = const ORDER BY b,c
> - WHERE a = const AND b > const ORDER BY b,c 
> 
> -- 不能使用索引进行排序
> - ORDER BY a ASC,b DESC,c DESC /* 排序不一致 */
> - WHERE g = const ORDER BY b,c /*丢失a索引*/
> - WHERE a = const ORDER BY c /*丢失b索引*/
> - WHERE a = const ORDER BY a,d /*d不是索引的一部分*/
> - WHERE a in (...) ORDER BY b,c /*对于排序来说，多个相等条件也是范围查询*/
> ```

#### filesort算法：双路排序和单路排序

##### 双路排序 （慢）

> - MySQL 4.1之前是使用双路排序 ，字面意思就是两次扫描磁盘，最终得到数据， 读取行指针和order by列 ，对他们进行排序，然后扫描已经排序好的列表，按照列表中的值重新从列表中读取对应的数据输出
>
> - 从磁盘取排序字段，在buffer进行排序，再从 磁盘取其他字段 。
>
> 取一批数据，要对磁盘进行两次扫描，众所周知，IO是很耗时的，所以在mysql4.1之后，出现了第二种改进的算法，就是单路排序。

##### 单路排序 （快）

> 从磁盘读取查询需要的 所有列 ，按照order by列在buffer对它们进行排序，然后扫描排序后的列表进行输出， 它的效率更快一些，避免了第二次读取数据。并且把随机IO变成了顺序IO，但是它会使用更多的空间， 因为它把每一行都保存在内存中了。
>
> `注意`：
>
> - 避免使用select* from XXX 查询，如果单路查询内存超过了 sorte_buffer 的值，会增加磁盘io次数。
> - 设置sort_buffer 的值：show variable like 'sort_buffer_size' ，innodb默认是1M，MyISAM默认8M，调节范围1-8M
> - 尝试提高max_length_for_sort_data，默认1k，范围1-8k，该参数来调节filesort的算法，如果超过，则使用双路，否则使用单路。

### GROUP BY优化

> group by 使用索引的原则几乎跟order by一致 ，group by 即使没有过滤条件用到索引，也可以直接使用索引。
>
> group by 先排序再分组，遵照索引建的最佳左前缀法则
>
> 当无法使用索引列，增大 max_length_for_sort_data 和 sort_buffer_size 参数的设置
>
> where效率高于having，能写在where限定的条件就不要写在having中了
>
> 减少使用order by，和业务沟通能不排序就不排序，或将排序放到程序端去做。Order by、group by、distinct这些语句较为耗费CPU，数据库的CPU资源是极其宝贵的。
>
> 包含了order by、group by、distinct这些查询的语句，where条件过滤出来的结果集请保持在1000行以内，否则SQL会很慢。

### 优化分页查询

> ```sql
> SELECT * FROM student limit 2000000, 10
> ```
>
> - 在索引上完成排序分页操作，最后根据主键关联回原表查询所需要的其他列内容。
>
>   ```sql
>   SELECT * FROM student t,(SELECT id FROM student ORDER BY id LIMIT 2000000,10) aWHERE t.id = a.id;
>   ```
>
> - 该方案适用于主键自增的表，可以把Limit 查询转换成某个位置的查询 。
>
>   ```sql
>   SELECT * FROM student WHERE id > 2000000 LIMIT 10;
>   ```

### 优先考虑覆盖索引

#### 什么是覆盖索引？

> **理解方式一**：索引是高效找到行的一个方法，但是一般数据库也能使用索引找到一个列的数据，因此它不必读取整个行。毕竟索引叶子节点存储了它们索引的数据；当能通过读取索引就可以得到想要的数据，那就不需要读取行了。**一个索引包含了满足查询结果的数据就叫做覆盖索引。**
>
> **理解方式二**：非聚簇复合索引的一种形式，它包括在查询里的SELECT、JOIN和WHERE子句用到的所有列（即建索引的字段正好是覆盖查询条件中所涉及的字段）。
>
> 简单说就是， `索引列+主键 包含 SELECT 到 FROM之间查询的列 ` 。 

#### 覆盖索引的利弊

> **好处：**
>
> - 避免Innodb表进行索引的二次查询（回表）   
>
> - 可以把随机IO变成顺序IO加快查询效率
>
> **弊端：**
>
> 索引字段的维护 总是有代价的。因此，在建立冗余索引来支持覆盖索引时就需要权衡考虑了。 

### 索引下推

> Index Condition Pushdown(ICP)是MySQL 5.6中新特性，是一种在存储引擎层使用索引过滤数据的一种优化方式。ICP可以减少存储引擎访问基表的次数以及MySQL服务器访问存储引擎的次数。

#### 使用前后的扫描对比

> 使用前，存储层多返回了需要被index filter过滤掉的整行记录
>
> 使用ICP后，直接就去掉了不满足index filter条件的记录，省去了他们回表和传递到server层的成本。
>
> ICP的 加速效果 取决于在存储引擎内通过 ICP筛选 掉的数据的比例。

#### ICP开启和关闭

```sql
set optimizer_switch = 'index_condition_pushdown=off';
```

#### ICP的使用条件

> ① 只能用于二级索引(secondary index) 
>
> ②  explain显示的执行计划中type值为 range 、 ref 、 eq_ref 或者 ref_or_null 。 
>
> ③ where条件的字段在索引列中
>
> ④ ICP可以用于MyISAM和InnnoDB存储引擎
>
> ⑤ MySQL 5.6版本的不支持分区表的ICP功能，5.7版本的开始支持。
>
> ⑥ 当SQL使用覆盖索引时，不支持ICP优化方法，因为ICP主要是优化回表，而索引覆盖不用回表

#### ICP使用案例

```sql
-- 建立索引
ALTER TABLE student_info ADD INDEX IDX_classId_studentId_name(class_id, student_id,name);

EXPLAIN SELECT * FROM student_info WHERE NAME LIKE '张%' AND class_id = 10 AND student_id = 1;
```

### 普通索引 vs 唯一索引

#### 查询过程

> 假设，执行查询的语句是 select id from test where k=5。
>
> 对于普通索引来说，查找到满足条件的第一个记录(5,500)后，需要查找下一个记录，直到碰到第一个不满足k=5条件的记录。
>
> 对于唯一索引来说，由于索引定义了唯一性，查找到第一个满足条件的记录后，就会停止继续检索。
>
> 那么，这个不同带来的性能差距会有多少呢？答案是， 微乎其微 。 

#### 更新过程

> 为了说明普通索引和唯一索引对更新语句性能的影响这个问题，介绍一下change buffer。
>
> 当需要更新一个数据页时，如果数据页在内存中就直接更新，而如果这个数据页还没有在内存中的话，在不影响数据一致性的前提下， InooDB会将这些更新操作缓存在change buffer中 ，这样就不需要从磁盘中读入这个数据页了。在下次查询需要访问这个数据页的时候，将数据页读入内存，然后执行change buffer中与这个页有关的操作。通过这种方式就能保证这个数据逻辑的正确性。
>
> 将change buffer中的操作应用到原数据页，得到最新结果的过程称为 merge 。除了 访问这个数据页 会触发merge外，系统有 后台线程会定期 merge。在 数据库正常关闭（shutdown） 的过程中，也会执行merge操作。
>
> 如果能够将更新操作先记录在change buffer， 减少读磁盘 ，语句的执行速度会得到明显的提升。而且，数据读入内存是需要占用 buffer pool 的，所以这种方式还能够 避免占用内存 ，提高内存利用率。
>
> `唯一索引的更新就不能使用change buffer ，实际上也只有普通索引可以使用。`

#### change buffer的使用场景

> 1. 普通索引和唯一索引应该怎么选择？其实，这两类索引在查询能力上是没差别的，主要考虑的是对 更新性能 的影响。所以，建议你 尽量选择普通索引 。 
>
> 2. 在实际使用中会发现， 普通索引 和 change buffer 的配合使用，对于 数据量大 的表的更新优化还是很明显的。
>
> 3. 如果所有的更新后面，都马上 伴随着对这个记录的查询 ，那么你应该 关闭change buffer 。而在其他情况下，change buffer都能提升更新性能。
>
> 4. 由于唯一索引用不上change buffer的优化机制，因此如果 业务可以接受 ，从性能角度出发建议优先考虑非唯一索引。但是如果"业务可能无法确保"的情况下，怎么处理呢？
>
> - 首先， 业务正确性优先 。我们的前提是“业务代码已经保证不会写入重复数据”的情况下，讨论性能问题。如果业务不能保证，或者业务就是要求数据库来做约束，那么没得选，必须创建唯一索引。这种情况下，本节的意义在于，如果碰上了大量插入数据慢、内存命中率低的时候，给你多提供一个排查思路。
>
> - 然后，在一些“ 归档库 ”的场景，你是可以考虑使用唯一索引的。比如，线上数据只需要保留半年，然后历史数据保存在归档库。这时候，归档数据已经是确保没有唯一键冲突了。要提高归档效率，可以考虑把表里面的唯一索引改成普通索引

### 其它查询优化策略

#### EXISTS 和 IN 的区分

> 遵循小表驱动大表的原则，如果student_info小，class表大，则用exists，反之用in。

```sql
select * from student_info s where s.class_id in (select c.id from class c);
select * from student_info s where exists (select * from class c where c.id = s.class_id);
```

#### COUNT(\*)、COUNT(1)与COUNT(具体字段)效率

> **在Innodb下：**
>
> 1. count(1) 和count(*)查询所有包括为null的记录行数，count(字段)会忽略null的字段行数
> 2. Count(可空字段)要逐个判断是否为空，消耗时间
> 3. Count(*)、count(1)和count(主键)会自动优化为使用字段长度最短的二级索引，因为一次磁盘IO能够读取更多的数据页，减少磁盘IO的次数，提升性能
> 4. Count(非主键索引)使用该索引进行查询
> 5. Count(无索引字段)使用全表查询
>
> **在MyISAM**
>
> 每张表都有一个row_count字段来维护总行数，不需要扫描索引或者全表。
>
> **总结：**count(*) = count(1) > count(具体字段)

#### 关于SELECT(*)

> 在表查询中，建议明确字段，不要使用 * 作为查询的字段列表，推荐使用SELECT <字段列表> 查询。原因：
>
> ① MySQL 在解析的过程中，会通过 查询数据字典 将"*"按序转换成所有列名，这会大大的耗费资源和时间。
>
> ② 无法使用 覆盖索引 

#### LIMIT 1 对优化的影响

> 针对的是会扫描全表的 SQL 语句，如果你可以确定结果集只有一条，那么加上 LIMIT 1 的时候，当找到一条结果的时候就不会继续扫描了，这样会加快查询速度。
>
> 如果数据表已经对字段建立了唯一索引，那么可以通过索引进行查询，不会全表扫描的话，就不需要加上 LIMIT 1 了。

#### 多使用COMMIT

> 只要有可能，在程序中尽量多使用 COMMIT，这样程序的性能得到提高，需求也会因为 COMMIT 所释放的资源而减少。
>
> COMMIT 所释放的资源：
>
> - 回滚段上用于恢复数据的信息
> - 被程序语句获得的锁
>
> - redo / undo log buffer 中的空间
> -  管理上述 3 种资源中的内部花费

### 淘宝数据库，主键如何设计的？

> 聊一个实际问题：淘宝的数据库，主键是如何设计的？
>
> 某些错的离谱的答案还在网上年复一年的流传着，甚至还成为了所谓的MySQL军规。其中，一个最明显的错误就是关于MySQL的主键设计。
>
> 大部分人的回答如此自信：用8字节的 BIGINT 做主键，而不要用INT。 错 ！这样的回答，只站在了数据库这一层，而没有 从业务的角度 思考主键。主键就是一个自增ID吗？站在
>
> 2022年的新年档口，用自增做主键，架构设计上可能 连及格都拿不到 。 

#### 自增ID的问题

> 自增ID做主键，简单易懂，几乎所有数据库都支持自增类型，只是实现上各自有所不同而已。自增ID除了简单，其他都是缺点，总体来看存在以下几方面的问题：
>
> **1.** **可靠性不高**
>
> 存在自增ID回溯的问题，这个问题直到最新版本的MySQL 8.0才修复。
>
> **2.** **安全性不高**
>
> 对外暴露的接口可以非常容易猜测对应的信息。比如：/User/1/这样的接口，可以非常容易猜测用户ID的值为多少，总用户数量有多少，也可以非常容易地通过接口进行数据的爬取。
>
> **3.** **性能差**
>
> 自增ID的性能较差，需要在数据库服务器端生成。
>
> **4.** **交互多**
>
> 业务还需要额外执行一次类似 last_insert_id() 的函数才能知道刚才插入的自增值，这需要多一次的网络交互。在海量并发的系统中，多1条SQL，就多一次性能上的开销。
>
> **5.** **局部唯一性**
>
> 最重要的一点，自增ID是局部唯一，只在当前数据库实例中唯一，而不是全局唯一，在任意服务器间都是唯一的。对于目前分布式系统来说，这简直就是噩梦。

#### 业务字段做主键

> **建议尽量不要用跟业务有关的字段做主键。毕竟，作为项目设计的技术人员，我们谁也无法预测在项目的整个生命周期中，哪个业务字段会因为项目的业务需求而有重复，或者重用之类的情况出现。**

#### 推荐的主键设计

> 非核心业务 ：对应表的主键自增ID，如告警、日志、监控等信息。
>
> 核心业务 ：**主键设计至少应该是全局唯一且是单调递增**。全局唯一保证在各系统之间都是唯一的，单调递增是希望插入时不影响数据库性能。
>
> 推荐使用UUID

##### UUID的特点

> 全局唯一，占用36字节，数据无序，插入性能差。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023103810269.png" alt="image-20221023103810269" style="zoom:50%;" />

- 为什么UUID是全局唯一的？ 

在UUID中时间部分占用60位，存储的类似TIMESTAMP的时间戳，但表示的是从1582-10-15 00：00：00.00到现在的100ns的计数。可以看到UUID存储的时间精度比TIMESTAMPE更高，时间维度发生重复的概率降低到1/100ns。

时钟序列是为了避免时钟被回拨导致产生时间重复的可能性。MAC地址用于全局唯一。

- 为什么UUID占用36个字节？ 

UUID根据字符串进行存储，设计时还带有无用"-"字符串，因此总共需要36个字节。

- 为什么UUID是随机无序的呢？

因为UUID的设计中，将时间低位放在最前面，而这部分的数据是一直在变化的，并且是无序。

##### UUID改造

> 若将时间高低位互换，则时间就是单调递增的了，也就变得单调递增了。MySQL 8.0可以更换时间低位和时间高位的存储方式，这样UUID就是有序的UUID了。
>
> MySQL 8.0还解决了UUID存在的空间占用的问题，除去了UUID字符串中无意义的"-"字符串，并且将字符串用二进制类型保存，这样存储空间降低为了16字节。
>
> 可以通过MySQL8.0提供的uuid_to_bin函数实现上述功能，同样的，MySQL也提供了`bin_to_uuid函数`进行转化：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023104818397.png" alt="image-20221023104818397" style="zoom:50%;" />

##### 如果不是MySQL8.0 肿么办？

手动赋值字段做主键！

比如，设计各个分店的会员表的主键，因为如果每台机器各自产生的数据需要合并，就可能会出现主键重复的问题。

可以在总部 MySQL 数据库中，有一个管理信息表，在这个表中添加一个字段，专门用来记录当前会员编号的最大值。

门店在添加会员的时候，先到总部 MySQL 数据库中获取这个最大值，在这个基础上加 1，然后用这个值

作为新会员的“id”，同时，更新总部 MySQL 数据库管理信息表中的当 前会员编号的最大值。这样一来，各个门店添加会员的时候，都对同一个总部 MySQL 数据库中的数据表字段进 行操作，就解决了各门店添加会员时会员编号冲突的问题。

## 数据库的设计规范

### 范 式

> **在关系型数据库中，关于数据表设计的基本原则、规则就称为范式(normal form)。**可以理解为，一张数据表的设计结构需要满足的某种设计标准的 级别 。要想设计一个结构合理的关系型数据库，必须满足一定的范式。
>
> 目前关系型数据库有六种常见范式，按照范式级别，从低到高分别是：**第一范式（1NF）、第二范式（2NF）、第三范式（3NF）、巴斯-科德范式（BCNF）、第四范式(4NF）和第五范式（5NF，又称完美范式）**。范式越高，冗余度越低，满足高范式一定会满足低范式。 
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023124556987.png" alt="image-20221023124556987" style="zoom:50%;" />

#### 键和相关属性的概念

> 这里有两个表：
>
> 球员表(player) ：球员编号 | 姓名 | 身份证号 | 年龄 | 球队编号
>
> 球队表(team) ：球队编号 | 主教练 | 球队所在地
>
> `超键` ：能唯一标识元组的属性集，通俗的说，就是包含主键的多个字段集合。对于球员表来说，超键就是包括球员编号或者身份证号的任意组合，比如（球员编号）（球员编号，姓名）（身份证号，年龄）等。
>
> `候选键` ：最小的超键，不包含多余属性的超键，可以有多个，对于球员表来说，候选键就是（球员编号）或者（身份证号）。
>
> `主键` ：从候选键中选择，只有一个，比如（球员编号）。
>
> `外键` ：如果一个字段不是A表的主键，而是B表的主键，那么该字段就是外间。如球员表中的球队编号。
>
> `主属性`：包含在候选键的属性，如（球员编号）或者（身份证号），如果是联合主键，那么其中的一个也是主属性
>
> `非主属性`：与主属性相对，其他的属性（姓名）（年龄）（球队编号）都是非主属性。

#### 第一范式( 1 st NF)

> 确保表中每个字段都有原子性，表中每个字段都是不可拆分的最小数据单元。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023130506767.png" alt="image-20221023130506767" style="zoom:50%;" />
>
> 如上表不符合第一范式，修改如下
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023130514455.png" alt="image-20221023130514455" style="zoom:50%;" />

#### 第二范式( 2 nd NF)

> 满足第一范式的基础上，表里的每一条数据都要可以唯一标识，也就是要有主键，并且`非主属性字段(不是非主键字段)`要完全依赖于主键，不能依赖于主键的一部分。1NF 告诉我们字段属性需要是原子性的，而 2NF 告诉我们一张表就是一个独立的对象，一张表只表达一个意思。
>
> 举例：比赛表 player_game ，球员编号、姓名、年龄、比赛编号、比赛时间、比赛场地。这个不满足第二范式，因为主键是球员编号和比赛编号，姓名、年龄只依赖于球员编号，比赛时间、比赛场地只依赖于比赛编号，修改为3张表，如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023133741320.png" alt="image-20221023133741320" style="zoom:50%;" />
>
> 不满足第二范式的问题：
>
> 1. 数据冗余 ：如果一个球员可以参加 m 场比赛，那么球员的姓名和年龄就重复了 m-1 次。一个比赛也可能会有 n 个球员参加，比赛的时间和地点就重复了 n-1 次。
>
> 2. 插入异常 ：如果我们想要添加一场新的比赛，但是这时还没有确定参加的球员都有谁，那么就没法插入。
> 3. 删除异常 ：如果我要删除某个球员编号，如果没有单独保存比赛表的话，就会同时把比赛信息删除掉。
>
> 4. 更新异常 ：如果我们调整了某个比赛的时间，那么数据表中所有这个比赛的时间都需要进行调整，否则就会出现一场比赛时间不同的情况。

#### 第三范式( 3 rd NF)

> 满足第二范式的基础上，非主属性字段和主键字段直接相关，非主属性字段不能依赖于其他的非主属性字段，即非主键字段不能相互依赖，要保持独立。2NF和3NF通常以这句话概括：“每个非键属性依赖于键，依赖于整个键，并且除了键别无他物”。 
>
> 举例：
>
> 部门信息表 ：每个部门有部门编号（dept_id）、部门名称、部门简介等信息。
>
> 员工信息表 ：每个员工有员工编号、姓名、部门编号。列出部门编号后就不能再将部门名称、部门简介等与部门有关的信息再加入员工信息表中。
>
> 如果不存在部门信息表，则根据第三范式（3NF）也应该构建它，否则就会有大量的数据冗余。

#### 小结

优点：减少了数据的冗余，第三范式被认为在性能、扩展性、数据完整性达到了最好的平衡

缺点：为了满足范式，一般会建立多张表，使用多张表关联查询会降低查询的效率，可能导致索引失效。

### 反范式化

#### 概述

> **规范化** **vs** **性能**
>
> 1. 为满足某种商业目标 , 数据库性能比规范化数据库更重要
> 2. 在数据规范化的同时 , 要综合考虑数据库的性能
> 3. 通过在给定的表中添加额外的字段，以大量减少需要从中搜索信息所需的时间
> 4. 通过在给定的表中插入计算列，以方便查询

#### 反范式的新问题

> - 存储 空间变大 了
> - 一个表中字段做了修改，另一个表中冗余的字段也需要做同步修改，否则 数据不一致
> - 若采用存储过程来支持数据的更新、删除等额外操作，如果更新频繁，会非常 消耗系统资源 
> - 在 数据量小 的情况下，反范式不能体现性能的优势，可能还会让数据库的设计更加 复杂 

#### 反范式的适用场景

> 当冗余信息有价值或者能大幅度提高查询效率的时候，我们才会采取反范式的优化。

##### 增加冗余字段的建议

> 1. 这个字段经常被查询到
> 2. 这个字段不常修改

##### 2. 历史快照、历史数据的需要

> 在现实生活中，我们经常需要一些冗余信息，比如订单中的收货人信息，包括姓名、电话和地址等。每次发生的 订单收货信息 都属于 历史快照 ，需要进行保存，但用户可以随时修改自己的信息，这时保存这些冗余信息是非常有必要的。
>
> 反范式优化也常用在 数据仓库 的设计中，因为数据仓库通常 存储历史数据 ，对增删改的实时性要求不强，对历史数据的分析需求强。这时适当允许数据的冗余度，更方便进行数据分析。

### BCNF(巴斯范式)

> 如果满足了第三范式，并且`只有1个候选键，或者每个候选键都是单属性`，则自然达到了巴斯范式（巴斯-科德范式）。巴斯范式是第三范式的改进，使得冗余度更小。

#### 举例

我们分析如下表的范式情况：

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023150401766.png" alt="image-20221023150401766" style="zoom:50%;" />

在这个表中，一个仓库只有一个管理员，同时一个管理员也只管理一个仓库。我们先来梳理下这些属性之间的依赖关系。

仓库名决定了管理员，管理员也决定了仓库名，同时（仓库名，物品名）的属性集合可以决定数量这个属性。这样，我们就可以找到数据表的候选键。

`候选键` ：是（管理员，物品名）和（仓库名，物品名），然后我们从候选键中选择一个作为 主键 ，比如（仓库名，物品名）。

`主属性` ：包含在任一候选键中的属性，也就是仓库名，管理员和物品名。

`非主属性` ：数量这个属性。

**是否符合三范式**

如何判断一张表的范式呢？我们需要根据范式的等级，从低到高来进行判断。

首先，数据表每个属性都是原子性的，符合 1NF 的要求；

其次，数据表中非主属性”数量“都与候选键全部依赖，（仓库名，物品名）决定数量，（管理员，物品名）决定数量。因此，数据表符合 2NF 的要求；

最后，数据表中的非主属性，不传递依赖于候选键。因此符合 3NF 的要求。

**存在的问题**

既然数据表已经符合了 3NF 的要求，是不是就不存在问题了呢？我们来看下面的情况：

1. 增加一个仓库，但是还没有存放任何物品。根据数据表实体完整性的要求，主键不能有空值，因此会出现 插入异常 ； 

2. 如果仓库更换了管理员，我们就可能会 修改数据表中的多条记录 ； 

**问题解决**

首先我们需要确认造成异常的原因：主属性仓库名对于候选键（管理员，物品名）是部分依赖的关系，

这样就有可能导致上面的异常情况。因此引入BCNF，`它在 3NF 的基础上消除了主属性对候选键的部分依赖或者传递依赖关系。`

如果在关系R中，U为主键，A属性是主键的一个属性，若存在A->Y，Y为主属性，则该关系不属于BCNF。

根据 BCNF 的要求，我们需要把仓库管理关系 warehouse_keeper 表拆分成下面这样：

仓库表 ：（仓库名，管理员）

库存表 ：（仓库名，物品名，数量）

这样就不存在主属性对于候选键的部分依赖或传递依赖，上面数据表的设计就符合 BCNF。 

### 第四范式

> 概念：
>
> - 多值依赖：1对多的关系
> - 函数依赖：所有不是1对多的关系
> - 平凡的多值依赖：表中只有1组1对多的关系，同一个k只对应多个a
> - 非平凡的多值依赖：表中有多组1对多的关系，如同一个k对应多个a，且对应多个b
>
> 第四范式在满足BCFN下，消除非平凡且非函数的多值依赖，只保留了平凡的多值依赖。

#### 举例

> 职工表(职工编号，职工孩子姓名，职工选修课程)。
>
> 在这个表中，同一个职工可能会有多个职工孩子姓名。同样，同一个职工也可能会有多个职工选修课程，即这里存在着多值事实，不符合第四范式。
>
> 如果要符合第四范式，只需要将上表分为两个表，使它们只有一个多值事实，例如： 职工表一 (职工编号，职工孩子姓名)， 职工表二 (职工编号，职工选修课程)，两个表都只有一个多值事实，所以符合第四范式。

### 第五范式、域键范式

> 除了第四范式外，我们还有更高级的第五范式（又称完美范式）和域键范式（DKNF）。
>
> 在满足第四范式（4NF）的基础上，消除不是由候选键所蕴含的连接依赖。**如果关系模式R中的每一个连接依赖均由R的候选键所隐含，则称此关系模式符合第五范式。** 
>
> 函数依赖是多值依赖的一种特殊的情况，而多值依赖实际上是连接依赖的一种特殊情况。但连接依赖不像函数依赖和多值依赖可以由 语义直接导出 ，而是在 关系连接运算 时才反映出来。存在连接依赖的关系模式仍可能遇到数据冗余及插入、修改、删除异常等问题。
>
> 第五范式处理的是 无损连接问题 ，这个范式基本 没有实际意义 ，因为无损连接很少出现，而且难以察觉。而域键范式试图定义一个 终极范式 ，该范式考虑所有的依赖和约束类型，但是实用价值也是最小的，只存在理论研究中。

### ER模型

> **ER** **模型中有三个要素，分别是实体、属性和关系**。 
>
> `实体` ，可以看做是数据对象，往往对应于现实生活中的真实存在的个体。在 ER 模型中，用 矩形 来表示。实体分为两类，分别是 强实体 和 弱实体 。强实体是指不依赖于其他实体的实体；弱实体是指对另一个实体有很强的依赖关系的实体。
>
> `属性` ，则是指实体的特性。比如超市的地址、联系电话、员工数等。在 ER 模型中用 椭圆形 来表示。
>
> `关系` ，则是指实体之间的联系。比如超市把商品卖给顾客，就是一种超市与顾客之间的联系。在 ER 模型中用 菱形 来表示。
>
>  注意：实体和属性不容易区分。这里提供一个原则：我们要从系统整体的角度出发去看，**可以独立存在的是实体，不可再分的是属性**。也就是说，属性不能包含其他属性。


#### 关系的类型

> **在 ER 模型的 3 个要素中，关系又可以分为 3 种类型，分别是 一对一、一对多、多对多。**
>
> 一对一 ：指实体之间的关系是一一对应的，比如个人与身份证信息之间的关系就是一对一的关系。一个人只能有一个身份证信息，一个身份证信息也只属于一个人。
>
> 一对多 ：指一边的实体通过关系，可以对应多个另外一边的实体。相反，另外一边的实体通过这个关系，则只能对应唯一的一边的实体。比如说，我们新建一个班级表，而每个班级都有多个学生，每个学生则对应一个班级，班级对学生就是一对多的关系。
>
> 多对多 ：指关系两边的实体都可以通过关系对应多个对方的实体。比如在进货模块中，供货商与超市之间的关系就是多对多的关系，一个供货商可以给多个超市供货，一个超市也可以从多个供货商那里采购商品。再比如一个选课表，有许多科目，每个科目有很多学生选，而每个学生又可以选择多个科目，这就是多对多的关系。


### 数据表的设计原则

> 综合以上内容，总结出数据表设计的一般原则："三少一多"
>
> **1.** **数据表的个数越少越好** 
>
> **2.** **数据表中的字段个数越少越好**
>
> **3.** **数据表中联合主键的字段个数越少越好**
>
> **4.** **使用主键和外键越多越好**
>
> 注意：这个原则并不是绝对的，有时候我们需要牺牲数据的冗余度来换取数据处理的效率

### 数据库对象编写建议

#### 关于库

> 1. 【强制】库的名称必须控制在32个字符以内，只能使用英文字母、数字和下划线，建议以英文字母开头。
>
> 2. 【强制】库名中英文 一律小写 ，不同单词采用 下划线 分割。须见名知意。
>
> 3. 【强制】库的名称格式：业务系统名称_子系统名。
>
> 4. 【强制】库名禁止使用关键字（如type,order等）。
>
> 5. 【强制】创建数据库时必须 显式指定字符集 ，并且字符集只能是utf8或者utf8mb4。创建数据库SQL举例：CREATE DATABASE crm_fund DEFAULT CHARACTER SET 'utf8' ; 
>
> 6. 【建议】对于程序连接数据库账号，遵循 权限最小原则，使用数据库账号只能在一个DB下使用，不准跨库。程序使用的账号 原则上不准有drop权限 。 
>
> 7. 【建议】临时库以 tmp_ 为前缀，并以日期为后缀；备份库以 bak_ 为前缀，并以日期为后缀。

#### 关于表、列

> 1. 【强制】表和列的名称必须控制在32个字符以内，表名只能使用英文字母、数字和下划线，建议以 英文字母开头 。 
>
> 2. 【强制】 表名、列名一律小写 ，不同单词采用下划线分割。须见名知意。
>
> 3. 【强制】表名要求有模块名强相关，同一模块的表名尽量使用 统一前缀 。比如：crm_fund_item 
>
> 4. 【强制】创建表时必须 显式指定字符集 为utf8或utf8mb4。 
>
> 5. 【强制】表名、列名禁止使用关键字（如type,order等）。
>
> 6. 【强制】创建表时必须 显式指定表存储引擎 类型。如无特殊需求，一律为InnoDB。 
>
> 7. 【强制】建表必须有comment。 
>
> 8. 【强制】字段命名应尽可能使用表达实际含义的英文单词或 缩写 。如：公司 ID，不要使用 corporation_id, 而用corp_id 即可。
>
> 9. 【强制】布尔值类型的字段命名为 is_描述 。如member表上表示是否为enabled的会员的字段命名为 is_enabled。 
>
> 10. 【强制】禁止在数据库中存储图片、文件等大的二进制数据通常文件很大，短时间内造成数据量快速增长，数据库进行数据库读取时，通常会进行大量的随机IO操作，文件很大时，IO操作很耗时。通常存储于文件服务器，数据库只存储文件地址信息。
>
> 11. 【建议】建表时关于主键： 表必须有主键 (1)强制要求主键为id，类型为int或bigint，且为auto_increment 建议使用unsigned无符号型。 (2)标识表里每一行主体的字段不要设为主键，建议设为其他字段如user_id，order_id等，并建立unique key索引。因为如果设为主键且主键值为随机插入，则会导致innodb内部页分裂和大量随机I/O，性能下降。
>
> 12. 【建议】核心表（如用户表）必须有行数据的 创建时间字段 （create_time）和 最后更新时间字段 （update_time），便于查问题。
>
> 13. 【建议】表中所有字段尽量都是 NOT NULL 属性，业务可以根据需要定义 DEFAULT值 。 因为使用NULL值会存在每一行都会占用额外存储空间、数据迁移容易出错、聚合函数计算结果偏差等问题。
>
> 14. 【建议】所有存储相同数据的 列名和列类型必须一致 （一般作为关联列，如果查询时关联列类型不一致会自动进行数据类型隐式转换，会造成列上的索引失效，导致查询效率降低）。
>
> 15. 【建议】中间表（或临时表）用于保留中间结果集，名称以 tmp_ 开头。备份表用于备份或抓取源表快照，名称以 bak_ 开头。中间表和备份表定期清理。

#### 关于索引

> 1. 【强制】InnoDB表必须主键为id int/bigint auto_increment，且主键值禁止被更新。
> 2. 【强制】InnoDB和MyISAM存储引擎表，索引类型必须为BTREE。
> 3. 【建议】主键的名称以 pk_ 开头，唯一键以 uni_ 或 uk_ 开头，普通索引以 idx_ 开头，一律使用小写格式，以字段的名称或缩写作为后缀。
> 4. 【建议】多单词组成的columnname，取前几个单词首字母，加末单词组成column_name。如:sample 表 member_id 上的索引：idx_sample_mid。
> 5. 【建议】单个表上的索引个数不能超过 6 个。
> 6. 【建议】在建立索引时，多考虑建立联合索引，并把区分度最高的字段放在最前面。
> 7. 【建议】在多表 JOIN 的SQL里，保证被驱动表的连接列上有索引，这样JOIN 执行效率最高。
> 8. 【建议】建表或加索引时，保证表里互相不存在冗余索引。 比如：如果表里已经存在key(a,b)，则key(a)为冗余索引，需要删除。

#### SQL编写

>1. 【强制】程序端SELECT语句必须指定具体字段名称，禁止写成 *。
>
>2. 【建议】程序端insert语句指定具体字段名称，不要写成INSERT INTO t1 VALUES(...)。
>3. 【建议】除静态表或小表（ 100 行以内），DML语句必须有WHERE条件，且使用索引查找。
>4. 【建议】INSERT INTO...VALUES(XX),(XX),(XX).. 这里XX的值不要超过 5000 个。 值过多虽然上线很快，但会引起主从同步延迟。
>5. 【建议】SELECT语句不要使用UNION，推荐使用UNION ALL，并且UNION子句个数限制在 5 个以内。
>6. 【建议】线上环境，多表 JOIN 不要超过 5 个表。
>7. 【建议】减少使用ORDER BY，和业务沟通能不排序就不排序，或将排序放到程序端去做。ORDERBY、GROUP BY、DISTINCT 这些语句较为耗费CPU，数据库的CPU资源是极其宝贵的。
>8. 【建议】包含了ORDER BY、GROUP BY、DISTINCT 这些查询的语句，WHERE 条件过滤出来的结果集请保持在 1000 行以内，否则SQL会很慢。
>9. 【建议】对单表的多次alter操作必须合并为一次对于超过100W行的大表进行alter table，必须经过DBA审核，并在业务低峰期执行，多个alter需整合在一起。 因为alter table会产生表锁，期间阻塞对于该表的所有写入，对于业务可能会产生极大影响。
>10. 【建议】批量操作数据时，需要控制事务处理间隔时间，进行必要的sleep。
>11. 【建议】事务里包含SQL不超过 5 个。因为过长的事务会导致锁数据较久，MySQL内部缓存、连接消耗过多等问题。
>12. 【建议】事务里更新语句尽量基于主键或UNIQUE KEY，如UPDATE... WHERE id=XX;否则会产生间隙锁，内部扩大锁定范围，导致系统性能下降，产生死锁。

## 数据库其它调优策略

### 数据库调优的措施

#### 调优的目标

> 尽可能 节省系统资源 ，以便系统可以提供更大负荷的服务。（吞吐量更大）
>
> 合理的结构设计和参数调整，以提高用户操作 响应的速度 。（响应速度更快）

#### 如何定位调优问题

> 如何确定呢？一般情况下，有如下几种方式：
>
> **用户的反馈（主要）**
>
> **日志分析（主要）**
>
> **服务器资源使用监控**
>
> **数据库内部状况监控**
>
> **其它**
>
> 除了活动会话监控以外，我们也可以对 事务 、 锁等待 等进行监控，这些都可以帮助我们对数据库的运行状态有更全面的认识。

#### 调优的维度和步骤

> 我们需要调优的对象是整个数据库管理系统，它不仅包括 SQL 查询，还包括数据库的部署配置、架构等。从这个角度来说，我们思考的维度就不仅仅局限在 SQL 优化上了。通过如下的步骤我们进行梳理：

##### 第 1 步：选择适合的 DBMS

##### 第 2 步：优化表设计

##### 第 3 步：优化逻辑查询


##### 第 4 步：优化物理查询

> 物理查询优化是在确定了逻辑查询优化之后，采用物理优化技术（比如索引等），通过计算代价模型对各种可能的访问路径进行估算，从而找到执行方式中代价最小的作为执行计划。在这个部分中，我们需要掌握的重点是`对索引的创建和使用`。

##### 第 5 步：使用 Redis 或 Memcached 作为缓存

> 除了可以对 SQL 本身进行优化以外，我们还可以请外援提升查询的效率。
>
> 因为数据都是存放到数据库中，我们需要从数据库层中取出数据放到内存中进行业务逻辑的操作，当用户量增大的时候，如果频繁地进行数据查询，会消耗数据库的很多资源。如果我们将常用的数据直接放到内存中，就会大幅提升查询的效率。
>
> 键值存储数据库可以帮我们解决这个问题。
>
> 常用的键值存储数据库有 Redis 和 Memcached，它们都可以将数据存放到内存中。

##### 第 6 步：库级优化

###### 读写分离

> 从机器负责读，主机负责写

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023171557785.png" alt="image-20221023171557785" style="zoom:50%;" />

###### 数据分片

> - 将不同的表分散在不同的主机上
> - 垂直分表、水平分表部署在不同的主机上 
> - 分拆在提升数据库性能的同时，也会增加维护和使用成本。 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221023171823018.png" alt="image-20221023171823018" style="zoom:50%;" />

### 优化MySQL服务器

#### 优化服务器硬件

> **服务器的硬件性能直接决定着MySQL数据库的性能。** 硬件的性能瓶颈直接决定MySQL数据库的运行速度和效率。针对性能瓶颈提高硬件配置，可以提高MySQL数据库查询、更新的速度。 （ 1 ）配置较大的内存 （ 2 ）配置高速磁盘系统 （ 3 ）合理分布磁盘I/O （ 4 ）配置多处理器

#### 优化MySQL的参数

> `innodb_buffer_pool_size`：这个参数是Mysql数据库最重要的参数之一，表示InnoDB类型的表和索引的最大缓存。它不仅仅缓存索引数据，还会缓存表的数据。这个值越大，查询的速度就会越快。但是这个值太大会影响操作系统的性能。默认128M。
>
> `key_buffer_size`：表示索引缓冲区的大小。索引缓冲区是所有的线程共享。增加索引缓冲区可以得到更好处理的索引（对所有读和多重写）。当然，这个值不是越大越好，它的大小取决于内存的大小。如果这个值太大，就会导致操作系统频繁换页，也会降低系统性能。对于内存在4GB左右的服务器该参数可设置为256M或384M。
>
> `table_cache`：表示同时打开的表的个数。这个值越大，能够同时打开的表的个数越多。物理内存越大，设置就越大。默认为 2402 ，调到512-1024最佳。这个值不是越大越好，因为同时打开的表太多会影响操作系统的性能。
>
> `query_cache_size`：表示查询缓冲区的大小。可以通过在MySQL控制台观察，如果Qcache_lowmem_prunes的值非常大，则表明经常出现缓冲不够的情况，就要增加Query_cache_size的值；如果Qcache_hits的值非常大，则表明查询缓冲使用非常频繁，如果该值较小反而会影响效率，那么可以考虑不用查询缓存；Qcache_free_blocks，如果该值非常大，则表明缓冲区中碎片很多。MySQL8.0之后失效。该参数需要和query_cache_type配合使用。
>
> `query_cache_type`的值是 0 时，所有的查询都不使用查询缓存区。但是query_cache_type=0并不会导致MySQL释放query_cache_size所配置的缓存区内存。当query_cache_type=1时，所有的查询都将使用查询缓存区，除非在查询语句中指定SQL_NO_CACHE，如SELECT SQL_NO_CACHE * FROM tbl_name。当query_cache_type=2时，只有在查询语句中使用SQL_CACHE关键字，查询才会使用查询缓存区。使用查询缓存区可以提高查询的速度，这种方式只适用于修改操作少且经常执行相同的查询操作的情况。
>
> `sort_buffer_size`：表示每个需要进行排序的线程分配的缓冲区的大小。增加这个参数的值可以提高ORDER BY或GROUP BY操作的速度。默认数值是2 097 144字节（约2MB）。对于内存在4GB左右的服务器推荐设置为6-8M，如果有 100 个连接，那么实际分配的总共排序缓冲区大小为100 × 6＝ 600MB。
>
> `join_buffer_size` = 8M ：表示联合查询操作所能使用的缓冲区大小，和sort_buffer_size一样，该参数对应的分配内存也是每个连接独享。
>
> `read_buffer_size`：表示每个线程连续扫描时为扫描的每个表分配的缓冲区的大小（字节）。当线程从表中连续读取记录时需要用到这个缓冲区。SET SESSION read_buffer_size=n可以临时设置该参数的值。默认为64K，可以设置为4M。
>
> `innodb_flush_log_at_trx_commit`：表示何时将缓冲区的数据写入日志文件，并且将日志文件写入磁盘中。该参数对于innoDB引擎非常重要。该参数有 3 个值，分别为 0 、 1 和 2 。该参数的默认值为 1 。值为 0 时，表示每秒 1 次的频率将数据写入日志文件并将日志文件写入磁盘。每个事务的commit并不会触发前面的任何操作。该模式速度最快，但不太安全，mysqld进程的崩溃会导致上一秒钟所有事务数据的丢失。值为 1 时，表示每次提交事务时将数据写入日志文件并将日志文件写入磁盘进行同步。该模式是最安全的，但也是最慢的一种方式。因为每次事务提交或事务外的指令都需要把日志写入（flush）硬盘。值为 2 时，表示每次提交事务时将数据写入日志文件，每隔 1 秒将日志文件写入磁盘。该模式速度较快，也比 0 安全，只有在操作系统崩溃或者系统断电的情况下，上一秒钟所有事务数据才可能丢失。
>
> `innodb_log_buffer_size`：这是 InnoDB 存储引擎的事务日志所使用的缓冲区。为了提高性能，也是先将信息写入 Innodb Log Buffer 中，当满足 innodb_flush_log_trx_commit 参数所设置的相应条件（或者日志缓冲区写满）之后，才会将日志写到文件（或者同步到磁盘）中。
>
> `max_connections`：表示允许连接到MySQL数据库的最大数量，默认值是 151 。如果状态变量connection_errors_max_connections 不为零，并且一直增长，则说明不断有连接请求因数据库连接数已达到允许最大值而失败，这是可以考虑增大max_connections 的值。在Linux 平台下，性能好的服务器，支持 500-1000 个连接不是难事，需要根据服务器性能进行评估设定。这个连接数不是越大越好，因为这些连接会浪费内存的资源。过多的连接可能会导致MySQL服务器僵死。
>
> `back_log`：用于控制MySQL监听TCP端口时设置的积压请求栈大小。如果MySql的连接数达到
> max_connections时，新来的请求将会被存在堆栈中，以等待某一连接释放资源，该堆栈的数量即
> back_log，如果等待连接的数量超过back_log，将不被授予连接资源，将会报错。5.6.6 版本之前默
> 认值为 50 ， 之后的版本默认为 50 + （max_connections / 5）， 对于Linux系统推荐设置为小于 512
> 的整数，但最大不超过 900 。
> 如果需要数据库在较短的时间内处理大量连接请求， 可以考虑适当增大back_log 的值。
> thread_cache_size：线程池缓存线程数量的大小，当客户端断开连接后将当前线程缓存起来，
> 当在接到新的连接请求时快速响应无需创建新的线程 。这尤其对那些使用短连接的应用程序来说可
> 以极大的提高创建连接的效率。那么为了提高性能可以增大该参数的值。默认为 60 ，可以设置为
> 120 。可以通过如下几个MySQL状态值来适当调整线程池的大小：当 Threads_cached 越来越少，但 Threads_connected 始终不降，且 Threads_created 持续升高，可适当增加 thread_cache_size 的大小。
> `wait_timeout`：指定一个请求的最大连接时间，对于4GB左右内存的服务器可以设置为5-10。
> `interactive_timeout`：表示服务器在关闭连接前等待行动的秒数。

这里给出一份my.cnf的参考配置：

```
port = 3306 serverid = 1 socket = /tmp/mysql.sock skip-locking #避免MySQL的外部锁定，减少出错几率增强稳定性。 skip-name-resolve #禁止MySQL对外部连接进行DNS解析，使用这一选项可以消除MySQL进行DNS解析的时间。但需要注意，如果开启该选项，则所有远程主机连接授权都要使用IP地址方式，否则MySQL将无法正常处理连接请求！ back_log = 384key_buffer_size = 256M max_allowed_packet = 4M thread_stack = 256Ktable_cache = 128K sort_buffer_size = 6M read_buffer_size = 4Mread_rnd_buffer_size=16M join_buffer_size = 8M myisam_sort_buffer_size =64M table_cache = 512 thread_cache_size = 64 query_cache_size = 64Mtmp_table_size = 256M max_connections = 768 max_connect_errors = 10000000wait_timeout = 10 thread_concurrency = 8 #该参数取值为服务器逻辑CPU数量*2，在本例中，服务器有 2 颗物理CPU，而每颗物理CPU又支持H.T超线程，所以实际取值为 4 *2=8 skip-networking #开启该选项可以彻底关闭MySQL的TCP/IP连接方式，如果WEB服务器是以远程连接的方式访问MySQL数据库服务器则不要开启该选项！否则将无法正常连接！ table_cache=innodb_additional_mem_pool_size=4M #默认为2M innodb_flush_log_at_trx_commit=innodb_log_buffer_size=2M #默认为1M innodb_thread_concurrency=8 #你的服务器CPU有几个就设置为几。建议用默认一般为 8 tmp_table_size=64M #默认为16M，调到64-256最佳thread_cache_size=120 query_cache_size=32M
```
### 优化数据库结构

#### 拆分表：冷热数据分离

> 把一个表中使用频率很高的字段和使用频率很低的字段分离，减少磁盘IO，增加热数据的命中率，更好的利用缓存，避免冷数据占用缓存。

#### 增加中间表

> 把经常需要连接查询的字段用一张中间表进行存储，将原来的连接查询改成从中间表进行查询，要注意数据的一致性。

#### 增加冗余字段

> 使用反范式化，适当的增加冗余字段，减少连接查询，提升效率

#### 优化数据类型

> 优先选择符合业务存储需要的最小的数据类型。

##### 情况 1 ：对整数类型数据进行优化。

> 遇到整数类型的字段可以用 INT 型 。这样做的理由是，INT 型数据有足够大的取值范围，不用担心数据超出取值范围的问题。刚开始做项目的时候，首先要保证系统的稳定性，这样设计字段类型是可以的。但在数据量很大的时候，数据类型的定义，在很大程度上会影响到系统整体的执行效率。
>
> 对于 非负型 的数据（如自增ID、整型IP）来说，要优先使用无符号整型 UNSIGNED 来存储。因为无符号相对于有符号，同样的字节数，存储的数值范围更大。如tinyint有符号为-128-127，无符号为0-255，多出一倍的存储空间。

##### **情况 2 ：既可以使用文本类型也可以使用整数类型的字段，要选择使用整数类型** 。

> 跟文本类型数据相比，大整数往往占用更少的存储空间，因此，在存取和比对的时候，可以占用更少的内存空间。所以，在二者皆可用的情况下，尽量使用整数类型，这样可以提高查询的效率。如：将IP地址转换成整型数据。

##### 情况 3 ：避免使用TEXT、BLOB数据类型

> Mysql内存临时表不支持TEXT、BLOB这样的字段，如果查询到这些字段需要排序，要到磁盘临时表中进行排序。
>
> 建议新建一张扩展表，单独存储大字段。

##### 情况 4 ：避免使用ENUM类型

##### 情况 5 ：使用TIMESTAMP存储时间

> timestamp占用空间更小，占用4个字节，datetime占用8个字节，存储范围是1970-01-01 00:00:01 ～ 2038-01-19 03:14:07

##### 情况 6 ：用DECIMAL代替FLOAT和DOUBLE存储精确浮点数

#### 优化插入记录的速度

**1. MyISAM引擎的表：**

> **① 禁用索引**
>
> **② 禁用唯一性检查**
>
> **③ 使用批量插入**
>
> ④ 使用LOAD DATA INFILE 批量导入

**2. InnoDB引擎的表： **

> **① 禁用唯一性检查**
>
> **② 禁用外键检查**
>
> **③ 禁止自动提交**

#### 使用非空约束

> 在设计字段的时候，如果业务允许，建议尽量使用非空约束
>
> - null值字段会多出一个字节判断是否为空，占用空间
> - is not null不可使用索引
> - 使用聚合函数，空字段会影响查询结果

#### 分析表、检查表与优化表

##### 分析表

> 分析表主要是分析关键字的分布。MySQL中提供了ANALYZE TABLE语句分析表，ANALYZE TABLE语句的基本语法如下：

默认的，MySQL服务会将 ANALYZE TABLE语句写到binlog中，以便在主从架构中，从服务能够同步数据。
可以添加参数LOCAL 或者 NO_WRITE_TO_BINLOG取消将语句写到binlog中。

使用ANALYZE TABLE分析表的过程中，数据库系统会自动对表加一个只读锁。在分析期间，只能读取
表中的记录，不能更新和插入记录。ANALYZE TABLE语句能够分析InnoDB和MyISAM类型的表，但是不能
作用于视图。

```sql
ANALYZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE tbl_name[,tbl_name]...
```

> 默认的，MySQL服务会将 ANALYZE TABLE语句写到binlog中，以便在主从架构中，从服务能够同步数据。 可以添加参数LOCAL 或者 NO_WRITE_TO_BINLOG取消将语句写到binlog中。
>
> 使用ANALYZE TABLE分析表的过程中，数据库系统会自动对表`加一个只读锁`。在分析期间，只能读取 表中的记录，不能更新和插入记录。ANALYZE TABLE语句能够分析InnoDB和MyISAM类型的表，但是不能 作用 于视图。
>
> ANALYZE TABLE分析后的统计结果会反应到`index的cardinality`的值，该值统计了表中某一键所在的列不重复 的值的个数。`该值越接近表中的总行数，则在表连接查询或者索引查询时，就越优先被优化器选择使用。` 也就是索引列的cardinality的值与表中数据的总条数差距越大，即使查询的时候使用了该索引作为查 询条件，存储引擎实际查询的时候使用的概率就越小。cardinality可以通过 SHOW INDEX FROM 表名查看。

##### 检查表

> MySQL中可以使用CHECK TABLE语句来检查表。CHECK TABLE语句能够检查InnoDB和MyISAM类型的表是否存在错误。CHECK TABLE语句在执行过程中也会给表加上`只读锁`。
>
> 对于MyISAM类型的表，CHECK TABLE语句还会更新关键字统计数据。而且，CHECK TABLE也可以检查视图是否有错误，比如在视图定义中被引用的表已不存在。该语句的基本语法如下：

```sql
CHECK TABLE tbl_name [, tbl_name] ... [option] ...
option = {QUICK | FAST | MEDIUM | EXTENDED | CHANGED}
```

> 其中，tbl_name是表名；option只对MyISAM类型的表有效，对InnoDB类型的表无效。option参数有 5 个取值，分别是QUICK、FAST、MEDIUM、EXTENDED和CHANGED。各个选项的意义分别是：
>
> QUICK：不扫描行，不检查错误的连接。
> FAST：只检查没有被正确关闭的表。
> CHANGED：只检查上次检查后被更改的表和没有被正确关闭的表。
> MEDIUM：扫描行，以验证被删除的连接是有效的。也可以计算各行的关键字校验和，并使用计算出的校验和验证这一点。
> EXTENDED：对每行的所有关键字进行一个全面的关键字查找。这可以确保表是100%一致的，但是花的时间较长。
>
> 该语句对于检查的表可能会产生多行信息。最后一行有一个状态的 Msg_type 值，Msg_text 通常为 OK。如果得到的不是 OK，通常要对其进行修复；是 OK 说明表已经是最新的了。表已经是最新的，意味着存储引擎对这张表不必进行检查。

##### 优化表

> 消除因删除或者更新数据造成的空间浪费。

###### 方式 1 ：OPTIMIZE TABLE

> MySQL中使用`OPTIMIZE TABLE语句来优化表`。但是，OPTILMIZE TABLE语句只能优化表中的`VARCHAR、BLOB或TEXT类型`的字段。一个表使用了这些字段的数据类型，若已经删除了表的一大部分数据，或者已经对含有可变长度行的表（含有VARCHAR、BLOB或TEXT列的表）进行了很多更新，则应使用OPTIMIZE TABLE来重新利用未使用的空间，并整理数据文件的碎片。
>
> OPTIMIZE TABLE 语句对InnoDB和MyISAM类型的表都有效。该语句在执行过程中也会给表加上`只读锁`。
>
> OPTILMIZE TABLE语句的基本语法如下：

```sql
OPTIMIZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE tbl_name [, tbl_name] ...
```

> LOCAL | NO_WRITE_TO_BINLOG关键字的意义和分析表相同，都是指定不写入二进制日志。
>
> 在MyISAM中，是先分析这张表，然后会整理相关的MySQL datafile，之后回收未使用的空间；在InnoDB中，回收空间是简单通过Alter table进行整理空间。在优化期间，MySQL会创建一个临时表，优化完成之后会删除原始表，然后会将临时表rename成为原始表，并重新分析临时表。
>
> 说明： 在多数的设置中，根本不需要运行OPTIMIZE TABLE。即使对可变长度的行进行了大量的更新，也不需要经常运行，每周一次或每月一次即可，并且只需要对特定的表运行。

###### 使用mysqlcheck命令

```sql
mysqlcheck -o atguigudb1 test_timestamp -uroot -p
-o 表示优化
atguigudb1 数据库名称
test_timestamp 表名称
```

#### 小结

> 上述这些方法都是有利有弊的。比如：
>
> 修改数据类型，节省存储空间的同时，你要考虑到数据不能超过取值范围；
>
> 增加冗余字段的时候，不要忘了确保数据一致性；
>
> 把大表拆分，也意味着你的查询会增加新的连接，从而增加额外的开销和运维的成本。
>
> 因此，你一定要结合实际的业务需求进行权衡。

### 大表优化

#### 限定查询的范围

> 禁止不带任何限制数据范围条件的查询语句。 比如：我们当用户在查询订单历史的时候，我们可以控制在一个月的范围内；

#### 读/写分离

> 经典的数据库拆分方案，主库负责写，从库负责读。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024110154398.png" alt="image-20221024110154398" style="zoom:50%;" />

#### 垂直拆分

> 当数据量级达到 千万级 以上时，有时候我们需要把一个数据库切成多份，放到不同的数据库服务器上，减少对单一数据库服务器的访问压力。
>
> - 如果数据库中表过多，可以采用垂直分库，将同一个功能模块的表部署在同一台服务器上
> - 如果数据库中表字段过多，可以采用垂直分表，将数据冷热分离
>
> **垂直拆分的优点：** 可以使得列数据变小，在查询时减少读取的Block数，减少I/O次数。此外，垂直分区可以简化表的结构，易于维护。
>
> **垂直拆分的缺点：** 主键会出现冗余，需要管理冗余列，并会引起 JOIN 操作。此外，垂直拆分会让事务变得更加复杂。

#### 水平拆分

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024110937589.png" alt="image-20221024110937589" style="zoom:50%;" />

> 下面补充一下数据库分片的两种常见方案：
>
> - 客户端代理： 分片逻辑在应用端，封装在jar包中，通过修改或者封装JDBC层来实现。 当当网的Sharding-JDBC 、阿里的TDDL是两种比较常用的实现。
> - 中间件代理： 在应用和数据中间加了一个代理层。分片逻辑统一维护在中间件服务中。 我们现在谈的 Mycat 、 360 的Atlas、网易的DDB等等都是这种架构的实现。

### 其它调优策略

#### 服务器语句超时处理

>  在MySQL 8. 0 中可以设置服务器语句超时的限制，单位可以达到毫秒级别。当中断的执行语句超过设置的毫秒数后，服务器将终止查询影响不大的事务或连接，然后将错误报给客户端。
>
> 设置服务器语句超时的限制，可以通过设置系统变量MAX_EXECUTION_TIME来实现。默认情况下，MAX_EXECUTION_TIME的值为 0 ，代表没有时间限制。 例如：

```sql
SET GLOBAL MAX_EXECUTION_TIME= 2000 ;
SET SESSION MAX_EXECUTION_TIME= 2000 ; #指定该会话中SELECT语句的超时时间
```
#### 创建全局通用表空间

#### MySQL 8. 0 新特性：隐藏索引对调优的帮助



## 事务基础知识

### 数据库事务概述

#### 存储引擎支持情况

>能看出在 MySQL 中，只有InnoDB 是支持事务的。

#### 基本概念

> **事务：**一组逻辑操作单元，使数据从一种状态变换到另一种状态。
>
> **事务处理的原则：**保证所有事务都作为 一个工作单元 来执行，即使出现了故障，都不能改变这种执行方式。当在一个事务中执行多个操作时，要么所有的事务都被提交( commit )，那么这些修改就 永久 地保存下来；要么数据库管理系统将 放弃 所作的所有 修改 ，整个事务回滚( rollback )到最初状态。

#### 事务的ACID特性

- 原子性（atomicity）：

> 原子性是指事务是一个不可分割的工作单位，要么全部提交，要么全部失败回滚。

- 一致性（consistency）

> （国内很多网站上对一致性的阐述有误，具体你可以参考 Wikipedia 对 **Consistency** 的阐述）根据定义，一致性是指事务执行前后，数据`从一个合法性状态变换到另外一个合法性状态`。这种状态是语义上的而不是语法上的，跟具体的业务有关。
>
> 那什么是合法的数据状态呢？满足预定的约束的状态就叫做合法的状态。通俗一点，这状态是由你自己来定义的（比如满足现实世界中的约束）。满足这个状态，数据就是一致的，不满足这个状态，数据就是不一致的！如果事务中的某个操作失败了，系统就会自动撤销当前正在执行的事务，返回到事务操作之前的状态。
>
> 举例：
>
> A有100块钱，要转出去200块，这个就是不合法，不满足一致性。
>
> A有300，B有100，A转给B 100块钱，此时A的账户余额扣除了100，而由于某种原因B的账户余额没有增加100，这也是不合法

- 隔离型（isolation）：

> 事务的隔离性是指`一个事务的执行 不能被其他事务干扰 `，即一个事务内部的操作及使用的数据对 并发 的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
>
> 如果无法保证隔离性会怎么样？假设A账户有200元，B账户0元。A账户往B账户转账两次，每次金额为50元，分别在两个事务中执行。如果无法保证隔离性，会出现下面的情形：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024131438749.png" alt="image-20221024131438749" style="zoom:50%;" />

- 持久性（durability）

> 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是`永久性的`，接下来的其他操作和数据库故障不应该对其有任何影响。
>
> 持久性是通过`事务日志`来保证的。日志包括了`重做日志和回滚日志`。当我们通过事务对数据进行修改的时候，首先会将数据库的变化信息记录到重做日志中，然后再对数据库中对应的行进行修改。这样做的好处是，即使数据库系统崩溃，数据库重启后也能找到没有更新到数据库系统中的重做日志，重新执行，从而使事务具有持久性。

#### 事务的状态

> 我们现在知道事务是一个抽象的概念，它其实对应着一个或多个数据库操作，MySQL根据这些操作所执行的不同阶段把事务大致划分成几个状态：
>
> - **活动的（active）**
>
>   事务对应的数据库操作正在执行过程中时，我们就说该事务处在 活动的 状态。
>
> - **部分提交的（partially committed）**
>
>   当事务中的最后一个操作执行完成，但由于操作都在内存中执行，所造成的影响并 没有刷新到磁盘时，我们就说该事务处在 部分提交的 状态。
>
> - **失败的（failed）**
>
>   当事务处在 活动的 或者 部分提交的 状态时，可能遇到了某些错误（数据库自身的错误、操作系统错误或者直接断电等）而无法继续执行，或者人为的停止当前事务的执行，我们就说该事务处在 失 败的 状态。
>
> - **中止的（aborted）**
>
>   如果事务执行一部分而变为 失败的 状态，那么就需要把已经修改的事务中的操作还原到事务执行前的状态。换句话说，就是要撤销失败事务对当前数据库造成的影响。我们把这个撤销的过程称之为 回滚 。当 回滚 操作执行完毕时，也就是数据库恢复到了执行事务之前的状态，我们就说该事务处在了 中止的 状态。
>
> - **提交的（committed）**
>
>   当一个处在 部分提交的 状态的事务将修改过的数据都 同步到磁盘 上之后，我们就可以说该事务处在了 提交的 状态。
>
> 一个基本的状态转换图如下所示： 
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024132514162.png" alt="image-20221024132514162" style="zoom:50%;" />

### 如何使用事务

#### 显式事务

> 使用`START TRANSACTION 或者 BEGIN `显式开启事务，START TRANSACTION 语句相较于 BEGIN 特别之处在于，后边能跟随几个 修饰符 ：
>
> ① READ ONLY ：标识当前事务是一个 只读事务 ，也就是属于该事务的数据库操作只能读取数据，而不能修改数据。
>
> ② READ WRITE ：标识当前事务是一个 读写事务 ，也就是属于该事务的数据库操作既可以读取数据，也可以修改数据。
>
> ③ WITH CONSISTENT SNAPSHOT ：启动一致性读。
>
> 使用`COMMIT`提交事务，使用`ROLLBACK`回滚事务。
>
> 使用`SAVEPOINT XX`，建立一个保存点，使用`ROLLBACK TO [SAVEPOINT] `回滚到一个保存点。

#### 隐式事务

> MySQL中有一个系统变量autocommit：show variables like 'autocommit'; 默认是ON
>
> 关闭隐式事务：
>
> - 设置autocommit参数为：OFF，set autocommit = false;
> - 使用START TRANSACTION 或者 BEGIN 显式开启事务

#### 隐式提交数据的情况

> - 数据定义语言（Data definition language，缩写为：DDL）如：ALTER、CREATE、DROP语句
>
> - 隐式使用或修改mysql数据库中的表，如ALTER USER，GRANT，SET PASSWORD等
>
> - 事务控制或关于锁定的语句
>
>   ① 当我们在一个事务还没提交或者回滚时就又使用 START TRANSACTION 或者 BEGIN 语句开启了另一个事务时，会 隐式的提交 上一个事务
>
>   ② 当前的 autocommit 系统变量的值为 OFF ，我们手动把它调为 ON 时，也会 隐式的提交 前边语句所属的事务。
>
>   ③ 使用 LOCK TABLES 、 UNLOCK TABLES 等关于锁定的语句也会 隐式的提交 前边语句所属的事务。
>
> - 加载数据的语句，LOAD DATA批量导入
>
> - 关于MySQL主从复制的一些语句，如：START SLAVE、STOP SLAVE、RESET SLAVE、CHANGE MASTER TO等
>
> - 其它的一些语句， 如：ANALYSE TABLE、CHECK TABLE、OPTIMIZE TABLE等

#### 使用举例 1 ：提交与回滚

> completion_type参数介绍，
>
> 0（默认）：当我们commit提交了一个事务后，下一次开启事务也要用START TRANSACTION或者BEGIN
>
> 1：相当于是commit and chain，开启一个链式事务，commit提交后，自动开启一个相同隔离级别的事务
>
> 2：提交后自动与服务器断开连接。

### 事务隔离级别

#### 数据并发问题

##### 脏写（Dirty Write）

> 对于两个事务 Session A、Session B，如果事务Session A修改了另一个未提交事务Session B修改过的数据，那就意味着发生了脏写
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024155942932.png" alt="image-20221024155942932" style="zoom:33%;" />

##### 脏读（Dirty Read）

> 对于两个事务 Session A、Session B，Session A 读取了已经被 Session B 更新但还`没有被提交的字段`。之后若 Session B 回滚，Session A读取的内容就是临时且无效的。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024160005206.png" alt="image-20221024160005206" style="zoom:33%;" />

##### 不可重复读（Non **-** Repeatable Read）

> 对于两个事务Session A、Session B，Session A 读取了一个字段，然后 Session B`更新`了该字段，并且提交了。 之后Session A再次读取同一个字段，值就不同了。那就意味着发生了不可重复读。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024160050131.png" alt="image-20221024160050131" style="zoom:33%;" />

##### 幻读（Phantom）

> 对于两个事务Session A、Session B, Session A 从一个表中读取了一个字段, 然后 Session B 在该表中`插入(删除不算，幻读强调多了数据)`了一些新的行，并且提交。 之后, 如果 Session A 再次读取同一个表, 就会多出几行。那就意味着发生了幻读。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024160315091.png" alt="image-20221024160315091" style="zoom:33%;" />

#### SQL中的四种隔离级别

> 我们愿意舍弃一部分隔离性来换取一部分性能在这里就体现在：设立一些隔离级别，隔离级别越低，并发问题发生的就越多。 SQL标准 中设立了4个 隔离级别 ： 不论是哪种隔离级别，都不允许脏写的情况发生。
>
> - `READ UNCOMMITTED `：读未提交，在该隔离级别，所有事务都可以看到其他未提交事务的执行结果。不能避免脏读、不可重复读、幻读。
>
> - `READ COMMITTED` ：读已提交，它满足了隔离的简单定义：一个事务只能看见已经提交事务所做的改变。这是`大多数数据库系统的默认隔离级别（但不是MySQL默认的）`。可以避免脏读，但不可重复读、幻读问题仍然存在。
>
> - `REPEATABLE READ` ：可重复读，事务A在读到一条数据之后，此时事务B对该数据进行了修改并提交，那么事务A再读该数据，读到的还是原来的内容。可以避免脏读、不可重复读，但幻读问题仍然存在。这是`MySQL的默认隔离级别`。
> - `SERIALIZABLE` ：可串行化，确保事务可以从一个表中读取相同的行。在这个事务持续期间，会给用到的行`加上一个行锁`，禁止其他事务对该表这些行执行插入、更新和删除操作。所有的并发问题都可以避免，但性能十分低下。能避免脏读、不可重复读和幻读。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024161012151.png" alt="image-20221024161012151" style="zoom:50%;" />
>
> 不同的隔离级别有不同的现象，并有不同的锁和并发机制，隔离级别越高，数据库的并发性能就越差，4种事务隔离级别与并发性能的关系如下：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221024161327087.png" alt="image-20221024161327087" style="zoom:30%;" />

#### MySQL查看事务的隔离级别

> 查看隔离级别，MySQL 5.7.20的版本之前： 
>
> ```sql
> SHOW VARIABLES LIKE 'tx_isolation';
> ```
>
> 查看隔离级别，MySQL 5.7.20的版本及之后： 
>
> ```sql
> SHOW VARIABLES LIKE 'transaction_isolation'; 
> ```
>
> 不同MySQL版本中都可以使用的： 
>
> ```sql
> SELECT @@transaction_isolation;
> ```

#### MySQL设置事务的隔离级别

```sql
SET [GLOBAL|SESSION] TRANSACTION ISOLATION LEVEL 隔离级别; 
#其中，隔离级别格式： > READ UNCOMMITTED > READ COMMITTED > REPEATABLE READ > SERIALIZABLE 

SET [GLOBAL|SESSION] TRANSACTION_ISOLATION = '隔离级别' 
#其中，隔离级别格式： > READ-UNCOMMITTED > READ-COMMITTED > REPEATABLE-READ > SERIALIZABLE
```

> 使用 GLOBAL 关键字（在全局范围影响）：则：
>
> - 当前已经存在的会话无效
>
> - 只对执行完该语句之后产生的会话起作用
>
> 使用 SESSION 关键字（在会话范围影响）：则：
>
> - 对当前会话的所有后续的事务有效
> - 如果在当前会话的事务之间执行，则对后续的事务有效
> - 可以在已经开启的事务中间执行，但不会影响当前正在执行的事务

### 事务的常见分类

> 从事务理论的角度来看，可以把事务分为以下几种类型：
>
> 扁平事务（Flat Transactions）
> 带有保存点的扁平事务（Flat Transactions with Savepoints）
> 链事务（Chained Transactions）
> 嵌套事务（Nested Transactions）
> 分布式事务（Distributed Transactions）

## MySQL事务日志

> 事务的隔离性由 锁机制 实现。而事务的原子性、一致性和持久性由事务的 redo 日志和undo 日志来保证。redo日志和undo日志都可视为一种恢复操作。
>
> `REDO LOG` 称为 重做日志 ，提供再写入操作，恢复提交事务修改的页操作，用来保证事务的持久性。是存储引擎层生成的日志，记录的是`物理级别`上页的操作，如：XX页XX偏移量修改了XX值。保证了数据的可靠性。
>
> `UNDO LOG` 称为 回滚日志 ，回滚行记录到某个特定版本，用来保证事务的原子性、一致性。是存储引擎层生成的日志，主要记录的是`逻辑操作`的日志，如：insert一条数据，undo日志会记录一条相反的delete语句，用于事务的回滚和一致性非锁定读。

### redo日志

#### 为什么需要REDO日志

> 一方面，缓冲池可以帮助我们消除CPU和磁盘之间的鸿沟，checkpoint机制可以保证数据的最终落盘，然而由于checkpoint并不是每次变更的时候就触发的，而是master线程隔一段时间去处理的。所以最坏的情况就是事务提交后，刚写完缓冲池，数据库宕机了，那么这段数据就是丢失的，无法恢复。
>
> 另一方面，事务包含持久性的特性，就是说对于一个已经提交的事务，在事务提交后即使系统发生了崩溃，这个事务对数据库中所做的更改也不能丢失。
>
> 那么如何保证这个持久性呢？一个简单的做法：在事务提交完成之前把该事务所修改的所有页面都刷新到磁盘，但是这个简单粗暴的做法有些问题
>
> 另一个解决的思路：我们只是想让已经提交了的事务对数据库中数据所做的修改永久生效，即使后来系统崩溃，在重启后也能把这种修改恢复出来。所以我们其实没有必要在每次事务提交时就把该事务在内存中修改过的全部页面刷新到磁盘，只需要把修改了哪些东西记录一下就好。比如，某个事务将系统表空间中第 10 号页面中偏移量为 100 处的那个字节的值 1 改成 2 。我们只需要记录一下：将第 0 号表空间的 10 号页面的偏移量为 100 处的值更新为 2 。
>
> `Redo log的作用`：Innodb存储引擎采用WAL策略（WRITE-AHEAD logging），先写日志，在写磁盘文件。如果服务器发生了宕机，内容中的数据未及时刷新到磁盘中，可以通过redo日志恢复，保证了数据的持久性。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025112046437.png" alt="image-20221025112046437" style="zoom:33%;" />


#### REDO日志的好处、特点

##### 好处

> redo日志降低了刷盘频率
> redo日志占用的空间非常小

##### 特点

> redo日志是顺序写入磁盘的
>
> 事务执行过程中，redo log不断记录：和binlog有区别，redo log是存储引擎层的日志，而binlog是数据库层的日志，当插入10万条数据，redo log会将每一条数据顺序记录，而bin log不会记录，只有当事务提交之后才会记录到bin log中。

#### redo的组成

> Redo log可以简单分为以下两个部分：
>
> - 重做日志的缓冲 (redo log buffer)，保存在内存中，是易失的。服务器在启动时，会向操作系统申请一段连续的缓存空间，这段空间被分为若干个连续的redo log block，一个block占用512字节大小
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025133236119.png" alt="image-20221025133236119" style="zoom:30%;" />
>
>   **参数设置：innodb_log_buffer_size：**redo log buffer 大小，默认16M，最大值是4096M，最小值为1M。
>
> - 重做日志文件 (redo log file)，保存在硬盘中，是持久的。Linux文件中，如图所示：
>
>   <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025133445604.png" alt="image-20221025133445604" style="zoom:50%;" />

#### redo的整体流程

> 以一个更新事务为例，redo log 流转过程，如下图所示：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025133550447.png" alt="image-20221025133550447" style="zoom:50%;" />
>
> 第1步：先将原始数据从磁盘中读入内存中来，修改数据的内存拷贝 
>
> 第2步：生成一条重做日志并写入redo log buffer，记录的是数据被修改后的值 
>
> 第3步：当事务commit时，将redo log buffer中的内容刷新到 redo log file，对 redo log file采用追加 写的方式 
>
> 第4步：定期将内存中修改的数据刷新到磁盘中
>
> Write-Ahead Log(预先日志持久化)：在持久化一个数据页之前，先将内存中相应的日志页持久化。

#### redo log的刷盘策略

> redo log的写入并不是直接写入磁盘的，InnoDB引擎会在写redo log的时候先写redo log buffer，之后以一定的频率刷入到真正的redo log file 中。这里的一定频率怎么看待呢？这就是我们要说的刷盘策略。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025134506757.png" alt="image-20221025134506757" style="zoom:50%;" />

> 注意，redo log buffer刷盘到redo log file的过程并不是真正的刷到磁盘中去，只是刷入到文件系统缓存（`page cache`）中去（这是现代操作系统为了提高文件写入效率做的一个优化），真正的写入会交给系统自己来决定（比如page cache足够大了）。那么对于InnoDB来说就存在一个问题，如果交给系统来同步，同样如果系统宕机，那么数据也丢失了（虽然整个系统宕机的概率还是比较小的）。
>
> 针对这种情况，InnoDB给出`innodb_flush_log_at_trx_commit`参数，该参数控制 commit提交事务时，如何将 redo log buffer 中的日志刷新到 redo log file 中。它支持三种策略：
>
> 设置为 0 ：表示每次事务提交时不进行刷盘操作。（系统默认master thread每隔`1s`进行一次重做日志的同步）
>
> 设置为 1 ：表示每次事务提交时都将进行同步，刷盘操作（`默认值`）。commit后马上 将buffer中数据存入到page cache，再马上将page cache的数据放入redo log file中
>
> 设置为 2 ：表示每次事务提交时都只把 redo log buffer 内容写入 page cache，不进行同步。由os自己决定什么时候同步到磁盘文件。

#### 不同刷盘策略演示

> innodb_flush_log_at_trx_commit = 1，只要事务提交成功，redo log就会修改，保证了数据绝对不会丢失，但是`效率最低`

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025140438925.png" alt="image-20221025140438925" style="zoom:30%;" />

> Innodb_flush_log_at_trx_commit = 2，每次commit会将内存中的数据更新到page cache中，之后每隔1s将page cache数据刷到file中。`效率居中`，如果MySQL服务器宕机不会影响数据，因为page cache是操作系统层的，但是操作系统宕机会导致数据丢失。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025142030346.png" alt="image-20221025142030346" style="zoom:33%;" />

> Innodb_flush_log_at_trx_commit = 0，每次commit后不进行操作，让后台线程自己每隔1s刷盘，`效率最高但是最不安全`。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025141425985.png" alt="image-20221025141425985" style="zoom:33%;" />

#### 写入redo log buffer 过程

##### 补充概念：Mini-Transaction

> MySQL对于底层页面中一个原子访问的过程叫做一个`Mini-Transaction`，简称mtr。如：一个update语句更新了3行记录中，那么就有3个mtr，每个mtr对应一组redo log，这一组log是不可分割的原子。
>
> 一个事务可以包含若干条语句，每一条语句其实是由若干个mtr组成，每一个mtr又可以包含若干条redo日志，画个图表示它们的关系就是这样：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025145208663.png" alt="image-20221025145208663" style="zoom:33%;" />

##### redo 日志写入log buffer

> 向redo log buffer写入输入是顺序的，通过`buf_free`值指明后续日志的插入位置。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025150806823.png" alt="image-20221025150806823" style="zoom:50%;" />
>
> 每个mtr都会产生一组redo日志，用示意图来描述一下这些mtr产生的日志情况：
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025150849586.png" alt="image-20221025150849586" style="zoom:50%;" />
>
> 不同的事务可能是并发执行的，所以T1、T2之间的mtr可能是交替执行的。
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025150907301.png" alt="image-20221025150907301" style="zoom:50%;" />
>
> 

##### redo log block的结构图

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025151304800.png" alt="image-20221025151304800" style="zoom:50%;" />


#### redo log file

##### 相关参数设置

> - `innodb_log_group_home_dir`：指定 redo log 文件组所在的路径，`默认值为./`，表示在数据库的数据目录下。MySQL的默认数据目录（var/lib/mysql）下默认有两个名为ib_logfile0和ib_logfile1的文件，log buffer中的日志默认情况下就是刷新到这两个磁盘文件中。此redo日志文件位置还可以修改。
> - `innodb_log_files_in_group` ：指明redo log file的个数，命名方式如：ib_logfile0，iblogfile1...iblogfilen。`默认 2 个`，最大 100 个。
> - `innodb_flush_log_at_trx_commit `：控制 redo log 刷新到磁盘的策略，`默认为 1 `。
> - `innodb_log_file_size `：单个 redo log 文件设置大小，`默认值为 48M，最大值为512G`，注意最大值指的是整个 redo log 系列文件之和，即（innodb_log_files_in_group * innodb_log_file_size ）不能大于最大值512G。根据业务修改其大小，以便容纳较大的事务。

##### 日志文件组

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025153012954.png" alt="image-20221025153012954" style="zoom:50%;" />

> redo log从内存写入file中，先从ib_logfile0开始写，如果写满了再继续写，一直到ib_logfile n，如果ib_logfile n也写满了，又从ib_logfile 0开始从头写。
>
> 总共的redo日志文件大小其实就是：innodb_log_file_size × innodb_log_files_in_group。
>
> 采用循环使用的方式向redo日志文件组里写数据的话，会导致后写入的redo日志覆盖掉前边写的redo日志？当然！所以InnoDB的设计者提出了checkpoint的概念。

##### Checkpoint

> write pos：当前记录的位置。
>
> checkpoint：已经刷盘的位置，说明checkpoint左边的数据已经刷新到磁盘中，可以覆盖了
>
> <img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025153449593.png" alt="image-20221025153449593" style="zoom:50%;" />
>
> 如果 write pos 追上 checkpoint ，表示 **日志文件组** 满了，这时候不能再写入新的 redo log记录，MySQL 得停下来，清空一些记录，把 checkpoint 推进一下。


### Undo日志

> redo log是事务持久性的保证，undo log是事务原子性的保证。在事务中`更新数据的前置操作`其实是要先`写入一个undo log。`

#### 如何理解Undo日志

> 事务需要保证 原子性 ，也就是事务中的操作要么全部完成，要么什么也不做。但有时候事务执行到一半会出现一些情况，比如：
>
> - 情况一：事务执行过程中可能遇到各种错误，比如 服务器本身的错误 ， 操作系统错误 ，甚至是突然 断电 导致的错误。
>
> - 情况二：程序员可以在事务执行过程中手动输入 ROLLBACK 语句结束当前事务的执行。
>
> 以上情况出现，我们需要把数据改回原先的样子，这个过程称之为 回滚 ，这样就可以造成一个假象：这个事务看起来什么都没做，所以符合 原子性 要求。由于SELECT不会修改数据，所以不会写入到undo日志中。undo日志的产生会伴随着产生redo日志，因为undo日志也需要持久化。

#### Undo日志的作用

##### 作用 1 ：回滚数据

> 只是逻辑性的恢复数据，不能在物理层面恢复数据。也就是说，如果新增了一条数据造成了页分裂，此时使用undo日志进行回滚，只会将新增的记录删除，页分裂并不能恢复。

##### 作用 2 ：MVCC

> 在Innodb存储引擎中的MVCC是通过UNDO日志完成的。当用户读取一条记录时，如果该记录已经被其他事务占用，可以通过undo日志读取之前的行版本信息，实现非锁定读取。

#### undo的存储结构

##### 回滚段与undo页

> InnoDB对undo log的管理采用段的方式，也就是`回滚段（rollback segment）`。每个回滚段记录了`1024 `个undo log segment，而在每个undo log segment段中进行undo页的申请，在undo页里面写回滚记录。
>
> - 在InnoDB1.1版本之前（不包括1.1版本），只有一个rollback segment，因此支持同时在线的事务限制为 1024 。虽然对绝大多数的应用来说都已经够用。
> - 从1.1版本开始InnoDB支持最大 `128` 个rollback segment，故其支持同时在线的事务限制提高到了128*1024。

##### 回滚段与事务

> 1. 每个事务只会使用一个回滚段，一个回滚段在同一时刻可能会服务于多个事务。
>
> 2. 当一个事务开始的时候，会制定一个回滚段，在事务进行的过程中，当数据被修改时，原始的数据会被复制到回滚段。
>
> 3. 在回滚段中，事务会不断填充盘区，直到事务结束或所有的空间被用完。如果当前的盘区不够用，事务会在段中请求扩展下一个盘区，如果所有已分配的盘区都被用完，事务会覆盖最初的盘区或者在回滚段允许的情况下扩展新的盘区来使用。
>
> 4. 回滚段存在于undo表空间中，在数据库中可以存在多个undo表空间，但同一时刻只能使用一个undo表空间。
>
> 5. 当事务提交时，InnoDB存储引擎会做以下两件事情：
>    - 将undo log放入列表中，以供之后的purge清除操作
>    - 判断undo log所在的页是否可以重用，使用率低于3/4可以重用，若可以分配给下个事务使用

##### 回滚段中的数据分类

> 1. 未提交的回滚数据(uncommitted undo information)： 该数据关联的事务没有提交，所以这部分数据不能被其他事务的数据覆盖
> 2. 已经提交但未过期的回滚数据(committed undo information)：事务已经提交，但是收到undo retention参数的保持时间影响，没有过期
> 3. 事务已经提交并过期的数据(expired undo information)：事务已经提交，且超过了undo retention参数的保持时间，当回滚段满了，会优先覆盖这部分数据。
>
> 事务提交后，不能马上删除undo log和undo log所在的页，因为可能其他事务需要通过undo log得到之前的行记录版本。所以，事务提交后，将undo log放入一个链表中，有purge线程判断是否删除undo log和所在页。

#### undo的类型

> - `insert undo log`：是指在insert操作前产生的日志，因为insert操作只对当前事务可见，其他事务不可见，所以其他事务不会用到这条数据，可以在事务提交后直接删除。
> - `update undo log`：是指在`update和delete`操作中产生的日志。该日志可能需要提供MVCC机制，因此不能在事务提交后直接删除，提交时放入undo log链表中，等待purge线程删除。

#### undo log的生命周期

##### 简要生成过程

> 假设有如下事务：A=1，B=2，使用update语句使得A=3，B=4
>
> ```
> 1.BEGIN
> 2.记录 A=1 到undo日志
> 3.set A=3
> 4.记录 A=3 到redo日志
> 5.记录 B=2 到undo日志
> 6.set B=4 
> 7.记录 B=4 到redo日志
> 8.刷新redo log到redo log file
> 9.commit
> ```
>
> - 如果1-7步发生服务器宕机，事务没有提交，不会对数据造成任何影响
> - 如果8-9步之间发生宕机，事务没有提交，可以等服务器恢复后，执行commit/rollback利用log进行提交或者回滚。
> - 如果9步之后发生宕机，事务已经提交，但是没有刷新到磁盘中，可以通过redo日志刷盘。

**只有Buffer Pool的流程：**

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025200528528.png" alt="image-20221025200528528" style="zoom:70%;" />

**有了Redo Log和Undo Log之后**

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025200544783.png" alt="image-20221025200544783" style="zoom:70%;" />

##### 详细生成过程

记录的真实数据除了我们自己定义的列的数据以外，还会有三个隐藏列： 

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221014170739931.png" alt="image-20221014170739931" style="zoom:50%;" />

实际上这几个列的真正名称其实是：DB_ROW_ID、DB_TRX_ID、DB_ROLL_PTR。

- DB_ROW_ID：一个表没有手动定义主键，则会选取一个Unique键作为主键，如果连Unique键都没有定义的话，则会为表默认添加一个名为row_id的隐藏列作为主键。所以`row_id是在没有自定义主键以及Unique键的情况下才会存在的`。
- DB_TRX_ID：修改该条数据的事务id
- DB_ROLL_PTR：回滚指针，执行undo log的指针。

###### 当我们执行INSERT

```sql
begin;
INSERT INTO user (name) VALUES ("tom");
```

> 执行insert，会生成一条insert undo log，数据的回滚指针指向这个log。log中记录了undo log的序号、插入的主键和值，在rollback时直接通过主键删除即可。

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025201428535.png" alt="image-20221025201428535" style="zoom:50%;" />

###### 当我们执行UPDATE

```sql
UPDATE user SET id=2 WHERE id=1;
```

> 执行update，会生成update undo log，如果`不更新主键`，会将更新写入到undo log中，并且将回滚指针指向新的undo log，新的undo log在执行老的undo log

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025201722022.png" alt="image-20221025201722022" style="zoom:50%;" />

```sql
UPDATE user SET id=2 WHERE id=1;
```

> 如果`更新了主键`，会将原来数据deletemark标记设置为1，再新建一条主键为2的一样的数据，生成undo log，并指向最新的undo log

![image-20221025202147750](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025202147750.png)

##### undo log是如何回滚的

> 以上面的例子来说，假设执行rollback，那么对应的流程应该是这样：
>
> 1. 通过undo no=3的日志把id=2的数据删除
> 2. 通过undo no=2的日志把id=1的数据的deletemark还原成 0
> 3. 通过undo no=1的日志把id=1的数据的name还原成Tom
> 4. 通过undo no=0的日志把id=1的数据删除 

#### 小结

<img src="https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20221025203105325.png" alt="image-20221025203105325" style="zoom:50%;" />

> undo log是逻辑日志，对事务回滚时，只是将数据库逻辑地恢复到原来的样子。
>
> redo log是物理日志，记录的是数据页的物理变化，undo log不是redo log的逆过程。