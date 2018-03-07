# Client_And_Server
## java的socket编程学习，实现C-S-C，C-C通信
>本程序模仿了简单的客户端与客户端之间的通信过程，两客户端之间建立的是tcp连接，不是udp

### 使用过程：
1. 先运行CenterServer,启动中间服务器
2. 运行多个ClientMain，在中间服务器登记登录信息
3. 往中间服务器发送消息: speak to user_name
4. 两个客户端之间建立tcp连接，然后就可以通信了
