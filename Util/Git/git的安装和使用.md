---
title: git的安装和使用
date: 2021-07-15 09:14:00
tags:
- git
categories:
- git
---

# git的介绍

Git是目前世界上最先进的分布式版本控制系统（没有之一）。BitMover公司威胁Linux社区，Linus花了两周时间自己用C写了一个分布式版本控制系统，这就是Git！

# git和svn对比

CVS及SVN都是集中式的版本控制系统，而Git是分布式版本控制系统

## 集中式和分布式

### 集中式

集中式版本控制系统，版本库是集中存放在中央服务器的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，然后回到家自己改，改完了，再放回图书馆。**集中式版本控制系统最大的毛病就是必须联网才能工作**

![git的安装01](https://img-blog.csdnimg.cn/20210715104626371.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3BpdHl0aGluZw==,size_16,color_FFFFFF,t_70)

### 分布式

分布式版本控制系统根本没有“中央服务器”，每个人的电脑上都是一个完整的版本库，这样，你工作的时候，就不需要联网了，因为版本库就在你自己的电脑。和集中式版本控制系统相比，分布式版本控制系统的安全性要高很多，因为每个人电脑里都有完整的版本库，某一个人的电脑坏掉了不要紧，随便从其他人那里复制一个就可以了。而集中式版本控制系统的中央服务器要是出了问题，所有人都没法干活了。Git的优势不单是不必联网这么简单，后面我们还会看到Git极其强大的分支管理，把SVN等远远抛在了后面。

![git的安装02](https://img-blog.csdnimg.cn/20210715104840776.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3BpdHl0aGluZw==,size_16,color_FFFFFF,t_70)

# git的安装

## 在Linux上安装git

首先，你可以试着输入`git`，看看系统有没有安装Git：

```
$ git
The program 'git' is currently not installed. You can install it by typing:
sudo apt-get install git
```

像上面的命令，有很多Linux会友好地告诉你Git没有安装，还会告诉你如何安装Git。

如果你碰巧用Debian或Ubuntu Linux，通过一条`sudo apt-get install git`就可以直接完成Git的安装，非常简单。

老一点的Debian或Ubuntu Linux，要把命令改为`sudo apt-get install git-core`，因为以前有个软件也叫GIT（GNU Interactive Tools），结果Git就只能叫`git-core`了。由于Git名气实在太大，后来就把GNU Interactive Tools改成`gnuit`，`git-core`正式改为`git`。

如果是其他Linux版本，可以直接通过源码安装。先从Git官网下载源码，然后解压，依次输入：`./config`，`make`，`sudo make install`这几个命令安装就好了

## 在MacOS上安装Git

安装homebrew，然后通过homebrew安装Git，具体方法请参考homebrew的文档：http://brew.sh/。



## 在Windows上安装Git

在Windows上使用Git，可以从Git官网直接[下载安装程序](https://git-scm.com/downloads)，然后按默认选项安装即可。

安装完成后，在开始菜单里找到“Git”->“Git Bash”，蹦出一个类似命令行窗口的东西，就说明Git安装成功！



# git使用

## 配置用户名和邮箱

```
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```