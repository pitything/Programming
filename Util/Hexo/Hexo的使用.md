---
title: Hexo的使用
date: 2021-07-14 15:14:00
tags:
- Hexo
categories:
- Hexo
---

# Hexo介绍

[Hexo](https://link.zhihu.com/?target=https%3A//hexo.io/zh-cn/)是一个快速、简洁且高效的博客框架。Hexo 使用 [Markdown](https://link.zhihu.com/?target=http%3A//daringfireball.net/projects/markdown/)（或其他渲染引擎）解析文章，在几秒内，即可利用靓丽的主题生成静态网页。即把用户的markdown文件，按照指定的主题解析成静态网页



# Hexo搭建

## 安装Hexo

安装使用hexo之前需要先安装Node.js和Git，当已经安装了Node.js和npm(npm是node.js的包管理工具)，可以通过以下命令安装hexo

``` 
npm install -g hexo-cli 
```

## 新建Blog文件夹

``` 
hexo init Blog
cd Blog
npm install
```

## Blog目录结构


```text
.
├── _config.yml
├── package.json
├── scaffolds
├── source
|   ├── _drafts
|   └── _posts
└── themes
```

###  _config.yml

网站的配置信息，可以在此配置大部分的参数。 [配置参数讲解](https://link.zhihu.com/?target=https%3A//hexo.io/zh-cn/docs/configuration)

### package.json

应用程序的信息，以及需要安装的模块信息。

### scaffolds

模版文件夹。新建文章时，Hexo 会根据 scaffold 中的模板文件来建立新的文件。**Hexo的模板是指在新建的markdown文件中默认填充的内容**。例如，如果修改scaffold/post.md中的Front-matter内容，那么每次新建一篇文章时都会包含这个修改。也就是说，通过hexo命令每新建一个文章，都会包含指定模板文件中的内容。

[官网模板详述](https://link.zhihu.com/?target=https%3A//hexo.io/zh-cn/docs/templates)

### source

资源文件夹是存放用户资源的地方，如markdown文章。**Markdown 和 HTML 文件会被解析并放到 `public` 文件夹**，而其他文件会被拷贝过去。

> 注意：除 `_posts` 文件夹之外，开头命名为 `_` (下划线)的文件 / 文件夹和隐藏的文件将会被忽略。

### themes

主题文件夹。Hexo 会根据主题来解析source目录中的markdown文件生成静态页面。[官网主题详述](https://link.zhihu.com/?target=https%3A//hexo.io/zh-cn/docs/themes)



## 配置Github

修改`_config.yml`文件，将repository修改为自己的仓库地址

```yml
deploy:
  type: git
  repository: git@github.com:***/***.github.io.git
  branch: main
```



## 安装deployer-git

```
npm install hexo-deployer-git --save
```



# Hexo的使用

## 创建新文件

```
hexo new [layout] <title>
hexo new post "测试新建文件"
```

可以在命令中指定文章的布局（layout），不指定默认为 `post`，也可以通过修改 `_config.yml` 中的 `default_layout` 参数来指定默认布局。**创建的新文章会自动加上指定布局对应的模板文件中的内容**



## 布局（Layout）

Hexo 有三种默认布局：`post`、`page` 和 `draft`，它们分别对应不同的路径，而自定义的其他布局和 `post` 相同，都将储存到 `source/_posts` 文件夹。

布局路径`postsource/_postspagesourcedraftsource/_drafts`

> 如果你不想你的文章被处理，你可以将 Front-Matter 中的`layout:` 设为 `false` 。



## 模版（Scaffold）

在新建文章时，Hexo 会根据 `scaffolds` 文件夹内相对应的文件来建立文件，例如：

```bash
$ hexo new photo "My Gallery"
```

**在执行这行指令时，Hexo 会尝试在 `scaffolds` 文件夹中寻找 `photo.md`，并根据其内容建立文章**，以下是您可以在模版中使用的变量：

变量描述：`layout`布局 `title`标题 `date`文件建立日期



## Front-matter

Front-matter是文件最上方以 `---` 分隔的区域，用于指定个别文件的变量，举例来说：

```text
---
title: Hello World
date: 2013/7/13 20:46:25
---
```

> 注意：一般Front-matter使用的yaml语法，yaml语法需要注意空格，如`title: Hello World`冒号需要有一个空格，当然除YAML 外，你也可以使用 JSON 来编写 Front-matter。

以下是预先定义的参数，您可在模板中使用这些参数值并加以利用。

参数描述默认值：layout布局	title标题	date建立日期 	updated更新日期	comments开启文章的评论功能true	tags标签（不适用于分页）	categories分类（不适用于分页）	permalink覆盖文章网址



## 分类和标签

只有文章支持分类和标签，您可以在 Front-matter 中设置。在其他系统中，分类和标签听起来很接近，但是在 Hexo 中两者有着明显的差别：**分类具有顺序性和层次性而标签没有顺序和层次**。

```text
categories:
- Diary
tags:
- PS3
- Games
```

> WordPress支持对一篇文章设置多个分类，而且这些分类可以是同级的，也可以是父子分类。但是Hexo不支持指定多个同级分类。下面的指定方法： categories:

- Diary
- Life

会使分类`Life`成为`Diary`的子分类，而不是并列分类。因此，有必要为您的文章选择尽可能准确的分类.

## 文章摘要

设置文章摘要，我们只需在想显示为摘要的内容之后添 `<!-- more -->` 即可。像下面这样：

```text
---
title: hello hexo markdown
date: 2016-11-16 18:11:25
tags:
- hello
- hexo
- markdown
---

我是短小精悍的文章摘要(๑•̀ㅂ•́) ✧

<!-- more -->

紧接着文章摘要的正文内容
```

这样，`<!-- more -->` 之前、文档配置参数之后中的内容便会被渲染为站点中的文章摘要。

> 注意！文章摘要在文章详情页是正文中最前面的内容。



# Hexo的部署

## 生成

首先执行下列命令生成相应的静态网页，生成的静态网页以及相关资源都会在`public`目录下

``` 
hexo generate 或 hexo g
```



## 本地发布

在 `http://localhost:4000` 下启动。在服务器启动期间，Hexo 会监视文件变动并自动更新，无须重启服务器

```
hexo server 或 hexo s
## 修改自定义端口
hexo server -p 5000
```



## 发布到Github

```
hexo deploy 或 hexo d
```



## 清除缓存文件

为了避免不必要的错误，在生成静态文件前，强烈建议先运行以下命令：

```bash
hexo clean
```