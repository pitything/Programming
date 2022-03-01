---
title: 翻墙教程
date: 2022-01-23 09:14:00
tags:
- break wall
categories:
- break wall
---

# 准备

1、腾讯云一台轻量应用服务器（1核1GB）



## 搭建

- 搭建脚本
```
bash <(curl -s -L [https://git.io/v2ray.sh)](https://www.youtube.com/redirect?event=comments&redir_token=QUFFLUhqa3BzQmdDcFF0M0s4N3lZdjdKNkltcDlfcFdUUXxBQ3Jtc0tudXdJSnpTb1g3dFM3eEhzV2ROT0ZSWkpfMTJJSndFWUlPQk14ZGI5RlVFZ0Z6cHRyd1RjbTdfYmVzZEJPczU5TElyR0hZTkRFS3F6bVdvemFNWXdnc1dSblhxdlQ0eHl2TmNmOU1CU3hrTzFVaFdURQ&q=https%3A%2F%2Fgit.io%2Fv2ray.sh)&stzid=UgwZ6LjawmgodU6eZkJ4AaABAg)
```
- BBR Plus脚本

```
wget -N --no-check-certificate "[https://raw.githubusercontent.com/hijkpw/Linux-NetSpeed/master/tcp.sh](https://www.youtube.com/redirect?event=comments&redir_token=QUFFLUhqbWVQU1N1Vi1fUnZFSjlCcEREckdkY0tHd1o2QXxBQ3Jtc0tuZEt4aTlhVGIwcTJWOXBvdlFhWWV3VG5Lc2RkNFNQNFNycnh2TlFXR0o4RUpzbVNaeWNWU2ZaQ0pqLTJ5RGlQYkJhcnpYaHFuVS1rekhXblZPSkRYMU5FUWVqSDdxeUhfM0pVa19hQUVVd3EzZHR3aw&q=https%3A%2F%2Fraw.githubusercontent.com%2Fhijkpw%2FLinux-NetSpeed%2Fmaster%2Ftcp.sh&stzid=UgwZ6LjawmgodU6eZkJ4AaABAg)" && chmod +x [tcp.sh](https://www.youtube.com/redirect?event=comments&redir_token=QUFFLUhqbTVRLUtjVFo2QzBVUjlfZzBJRGYwRDg0RFVDUXxBQ3Jtc0tuU3BEV0FleXNiUU5pbjN6NUxSNmpWLTNZY2k5SjhTeGdRRlJtRFhJTWd0eUZuRHp4QnJaMURHd1FJRUlOeXVvZ1ZPTWdjN1puOEpkVDI0eGpMZWcxdXhRWnE2YXhZMDlRZ0dubDdOaEZIRnUtSkVNTQ&q=http%3A%2F%2Ftcp.sh%2F&stzid=UgwZ6LjawmgodU6eZkJ4AaABAg) && ./[tcp.sh](https://www.youtube.com/redirect?event=comments&redir_token=QUFFLUhqazZRd2VsMnJ0akxfVXRLNU5GM3FjUHl2WGxxQXxBQ3Jtc0tuMUhBTDRjeWRzNlBrZlJQQ3E2N0pMNkNYMnNBVndMU1RSSTk4M1RMZU5OeVVISnZDYUpLaHE5c0JBNFJUMVZnY3R1WUhSVzZrbzlUSU5BZi1nX1Vld3dzYnV5d1I3YU1qdlZpTHdFZ191Rng2Q1lsTQ&q=http%3A%2F%2Ftcp.sh%2F&stzid=UgwZ6LjawmgodU6eZkJ4AaABAg)
```



## 配置信息

### V2Ray 配置信息

- 输入 v2ray url 可生成 vmess URL 链接 / 输入 v2ray qr 可生成二维码链接

 地址 (Address) = 43.134.168.10

 端口 (Port) = 21650

 用户ID (User ID / UUID) = ea99e8d5-d07e-4d1f-8823-1d0824d9b13e

 额外ID (Alter Id) = 0

 传输协议 (Network) = tcp

 伪装类型 (header type) = none



### Shadowsocks 配置信息

- 输入 v2ray ssqr 可生成 Shadowsocks 二维码链接

 服务器地址 = 43.134.168.10

 服务器端口 = 33202

 密码 = Jianghl1019

 加密协议 = aes-256-gcm

 SS 链接 = ss://YWVzLTI1Ni1nY206SmlhbmdobDEwMTlANDMuMTM0LjE2OC4xMDozMzIwMg==#233v2.com_ss_43.134.168.10


## 连接
> 关闭防火墙：设置所有端口都支持TCP、UDP协议