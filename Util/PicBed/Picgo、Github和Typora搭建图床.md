---
title: Picgo、Github和Typora搭建图床
date: 2021-07-15 09:14:00
tags:
- 图床
categories:
- 图床
---

# PicGo、GitHub、Typora 搭建个人图床工具

## 写在前面

我以前用的 七牛云 + Mpic 的组合，后来由于七牛云测试域名收回，我的图床就废了。以前的好多图片都埋藏在七牛云的服务器上，又气又难过。思考好一段时间，想自己搭服务，但成本有点高，备案的域名 + 服务器一年几百块。直到今天用 GitHub 搭起了图床，可以说非常开心了。所以跟大家分享一下。

>- 方便程度：★★★★☆
>- 配置难度：★★☆☆☆
>- 适用环境：win + mac + linux
>- 需要工具：GitHub 账号 + PicGo 客户端
>- 稳定性：背靠 GitHub 和微软，比自建服务器都稳
>- 隐私性：这算是唯一缺点，你的图片别人可以访问



## GitHub 仓库设置

> 流程：新建 public 仓库 -> 创建 token -> 复制 token 备用

### 新建仓库
点击 git 主页右上角的 +号创建 New repository；



填写仓库信息，例如我就创建了一个 cloudimg 的仓库。这里注意，仓库得设置为 Public 因为后面通过客户端访问算是外部访问，因此无法访问 Private ，这样的话图片传上来之后只能存储不能显示。所以要设置为 Public。

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3llZmNpb24vUGljRGF0YS9tYXN0ZXIvaW1nLzIwMTkwMzExMjIyNTE0LnBuZw.png)



### 创建 token 并复制保存
此时仓库已经建立，点击右上角头像，然后进入设置；

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3llZmNpb24vUGljRGF0YS9tYXN0ZXIvaW1nLzIwMTkwMzExMjIyOTI1LnBuZw.png)

在页面最下找到 Developer settings，点击进入；

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3llZmNpb24vUGljRGF0YS9tYXN0ZXIvaW1nLzE1NTIzMTQ2OTEyMzIucG5n.png)

创建 token；

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/format,png-20220129213707191.png)

填 description（也是随心填），勾选复选框 repo ，接着到页面底部 Generate token 就完成了；

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/format,png-20220129213727595.png)

然后复制生成一串字符 token，这个 token 只出现一次，所以要保存一下。

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3llZmNpb24vUGljRGF0YS9tYXN0ZXIvaW1nLzIwMTkwMzExMjIzOTAxLnBuZw.png)

## PicGo 客户端配置
### 下载&安装
MacBook使用Homebrew下载

```
brew install --cask picgo;
```

直接去github下载

https://github.com/Molunerfinn/PicGo/releases/tag/v2.3.0



### 配置
先上图

![img](https://raw.githubusercontent.com/pitything/images/main/https://raw.githubusercontent.com/pitything/images/master/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3llZmNpb24vUGljRGF0YS9tYXN0ZXIvaW1nLzIwMTkwMzExMjI0NDQzLnBuZw.png)

>- 仓库名 即你的仓库名
>- 分支名 默认 master
>- Token 就是刚刚复制的那一串字符
>- 存储路径 这个可以填也可以不填，填了的话图片就上传到 git 中 data 这个文件夹
>- 域名 https://raw.githubusercontent.com/yefcion/cloudimg/master 这个要改一下 格式 https://raw.githubusercontent.com/[username]/[仓库名]/master
>然后点确定就可以了。
>
>注：这里提供一个加速访问图片的方法：CDN加速，具体原理自行百度（我还不是很懂）
>将上面的域名改为：
>原 https://raw.githubusercontent.com/yefcion/cloudimg/master
>现 https://cdn.jsdelivr.net/gh/yefcion/cloudimg@master
>
>然后关于上传的快捷键设置。默认的是 command + shift + p



## Typora配置

![image-20220129214546515](https://raw.githubusercontent.com/pitything/images/main/https://cdn.jsdelivr.net/gh/pitything/images@master/image-20220129214546515.png)



## 总结

综上，操作完成。

本方案唯一缺点，不能私人。但是考虑到 GitHub 上传的图在列表里没法预览，应该没人会闲着没事翻记录。


