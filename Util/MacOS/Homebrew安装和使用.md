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



# Homebrew安装和卸载

### 安装

```bash
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/install)"
(替补地址)/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/Homebrew.sh)"
```

### 配置

#### 必备设置

- 替换 brew.git：

```bash
git -C "$(brew --repo)" remote set-url origin https://mirrors.ustc.edu.cn/brew.git
```

- 替换 homebrew-core.git：

```bash
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.ustc.edu.cn/homebrew-core.git
```

#### 按需设置

- 替换 homebrew-cask.git：

```bash
git -C "$(brew --repo homebrew/cask)" remote set-url origin https://mirrors.ustc.edu.cn/homebrew-cask.git
```

- 替换homebrew-bottles：

首先要先区分你的mac用哪种终端工具

```bash
## 区分你的mac用哪种终端工具
echo $SHELL
## 切换方法：
##切换bash：
chsh -s /bin/bash
## 切换zsh：
chsh -s /bin/zsh
```

如果是 bash，则执行：

```bash
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.bash_profile
source ~/.bash_profile
```

若是 zsh，则执行：

```bash
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.zshrc
source ~/.zshrc
```



注：Homebrew 主要由四个部分组成: brew、homebrew-core 、homebrew-cask、homebrew-bottles，它们对应的功能如下：

| 组成             | 功能                                |
| ---------------- | ----------------------------------- |
| Homebrew         | 源代码仓库                          |
| homebrew-core    | Homebrew 核心源                     |
| homebrew-cask    | 提供macos应用和大型二进制文件的安装 |
| homebrew-bottles | 预编译二进制软件包                  |



### 卸载

```ruby
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/uninstall)"
(替补地址)/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/HomebrewUninstall.sh)"
```



# Homebrew基本使用

- 安装任意包

  ```ruby
$ brew install <packageName>
    brew install --cask <packageName>
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

# MacBook常用软件和包

```bash
## 百度网盘
## 钉钉
## 腾讯会议
## 网易邮箱大师
## 网易云音乐
## 微信
## 迅雷
## 迅雷影音
## 有道云笔记
## AppCleaner
## Beyond Compare
## CheatSheet(快捷键提示软件)
## Downie
## git
## Google Chrome
## IINA
## IntelliJ IDEA
## Maven
## Mysql
## Parallels Desktop
## QQ
## Royal TSX
## redis
## Snipaste
## sublime-text
## The Unarchiver
## Typora
## V2rayU
## Visual Studio Code
## WPS Office
## XMind ZEN
```

```
brew install --cask baidunetdisk;
brew install --cask dingtalk;
brew install --cask tencent-meeting;
brew install --cask mailmaster;
brew install --cask neteasemusic;
brew install --cask wechat;
brew install --cask thunder;
brew install --cask xlplayer;
brew install --cask youdaonote;
brew install --cask appcleaner;
brew install --cask beyond-compare;
brew install --cask cheatsheet;
brew install --cask downie;
brew install git;
brew install --cask google-chrome;
brew install --cask iina;
brew install --cask intellij-idea;
brew install maven;
brew install mysql;
brew install --cask parallels;
brew install --cask qq;
brew install --cask royal-tsx;
brew install redis;
brew install --cask snipaste;
brew install --cask sublime-text；
brew install --cask the-unarchiver;
brew install --cask typora;
brew install --cask v2rayu;
brew install --cask visual-studio-code;
brew install --cask wpsoffice;
brew install --cask xmind-zen;
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
