---
title: Homebrew的安装和使用
date: 2021-07-15 09:14:00
tags:
- Homebrew
categories:
- Homebrew
---

# Homebrew的介绍

Homebrew是一款Mac OS平台下的软件包管理工具，拥有安装、卸载、更新、查看、搜索等很多实用的功能。简单的一条指令，就可以实现包管理，而不用你关心各种依赖和文件路径的情况，十分方便快捷。可以说Homebrew就是mac下的apt-get、yum神器(Red hat有yum，Ubuntu有apt-get)。

# Homebrew安装

### 安装

```bash
/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/Homebrew.sh)"
```

### 卸载

```ruby
/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/HomebrewUninstall.sh)"
```

# Homebrew基本使用

- 安装任意包分短发短发短发的短发短发短发似懂非懂

  ```ruby
$ brew install <packageName>
  ```
  
  示例：安装node

  

  ```ruby
$ brew install node
  ```
  
- 卸载任意包

  ```ruby
$ brew uninstall <packageName>
  ```
  
  示例：卸载git

  ```ruby
$ brew uninstall git
  ```

- 查询可用包

  

  ```ruby
  $ brew search <packageName>
  ```

- 查看已安装包列表

  

  ```cpp
  $ brew list
  ```

- 查看任意包信息

  

  ```ruby
  $ brew info <packageName>
  ```

- 更新Homebrew

  

  ```ruby
  $ brew update
  ```

- 查看Homebrew版本

  

  ```ruby
  $ brew -v
  ```

- Homebrew帮助信息

  

  ```ruby
  $ brew -h
  ```

# 注意

在Mac OS X 10.11系统以后，/usr/local/等系统目录下的文件读写是需要系统root权限的，以往的Homebrew安装如果没有指定安装路径，会默认安装在这些需要系统root用户读写权限的目录下，导致有些指令需要添加sudo前缀来执行，比如升级Homebrew需要：



```ruby
$ sudo brew update
```

如果你不想每次都使用sudo指令，你有两种方法可以选择:

1. 对/usr/local 目录下的文件读写进行root用户授权

   

   ```bash
   $ sudo chown -R $USER /usr/local
   ```

   示例：

   

   ```bash
   $ sudo chown -R gaojun /usr/local
   ```

2. （推荐）安装Homebrew时对安装路径进行指定，直接安装在不需要系统root用户授权就可以自由读写的目录下

   

   ```bash
   <install path> -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
   ```
