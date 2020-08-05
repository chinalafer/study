## NoSQL概述

### 为什么使用NoSQL

> 单机MySQL年代

![1596267189346](redis%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.images/1596267189346.png)

网站的访问量不大，单个数据完全够用。

随着时代的发展，单数据库的网站遇到一下瓶颈

1、数据量太大一个机器放不下

2、数据的索引（B + Tree），一个机器的内存放不下

3、访问量（读写混合），一个服务气承受不了

> Memcached（缓存） + MySQL + 垂直拆分

网站80%的情况都是在读，使用缓存来减少重复读，保证效率降低mysql的压力

![1596267508787](redis%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.images/1596267508787.png)

> 分库分表 + 水平拆分 + MySQL集群

==数据库的本质：读、写==

早些年MyISAM：表锁，效率极低

Innodb：行锁

使用分库分表解决写的压力。

![1596268392475](redis%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.images/1596268392475.png)

> 最近的年代

定位、地图、人际关系都是一种数据，MySQL等关系型数据库不满足需求。而且MySQL等关系型数据库用来存储图片、文件等大文件信息，会导致数据库的效率降低，再大数据IO的压力下，关系型数据的表不好改变。

**目前一个基本的互联网项目**

![1596269510606](redis%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.images/1596269510606.png)

**为什么使用NoSQL**

用户的个人信息、社交网络、地理信息。用户自己产生的数据，用户日志等等爆发式增长！

这时候我们需要使用NoSQL数据库，NoSQL可以很好的处理以上情况。

### 什么是NoSQL

> NoSQL

NoSQL = Not Only SQL （不仅仅是SQL）

泛指非关系型数据库，随着web2.0互联网的诞生！传统的关系型数据库很难应付web2.0时代的问题。

> NoSQL特点

解耦！

1、方便扩展（数据之间没有关系，很好拓展！）

2、大数据量高性能（Redis 一秒写8w次、读11w次，NoSQL的缓存记录级，是一种细粒度的缓存，性能较高）

3、数据类型多样（不需要事先设计数据库）

4、传统 RDBMS 和 NoSQL

```
传统的RDBMS
- 结构化组织
- SQL
- 数据和关系都存在单独的表中
- 数据操作语言、数据操作语言
- 严格的一致性
- 基础的事务
- ......
```

```
NoSQL
- 不仅仅是数据
- 没有固定的查询语言
- 键值对存储、列存储、文档存储、图形数据库（社交关系）
- 最终一致性
- CAP定理和BASE
- 高性能、高可用、高可扩
- ......
```

> 3V + 3高

大数据时代的3V：主要描述问题的

1、海量

2、多样

3、实时

大数据时代的3高：主要是对程序的要求

1、高并发

2、高可扩

3、高性能

公司中的实践：MySQL + NoSQL

### NoSQL的四大类型

**KV键值对**

- 新浪：Redis
- 阿里、百度：Redis + Memecache

**文档型数据库（bson格式和json一样）**

- MongoDB
  - MongoDB是一个基于分布式文件存储的数据库，C++编写，主要用来处理大量的文档
  - MongoDB是一个介于关系星数据库和非关系型数据库中间的产品，MongoDB是非关系型数据库中功能最丰富，最像关系星数据库的。

**列存储数据库**

- HBase

**图关系型数据库**

- 它存放的不是图形，而是关系，比如：朋友圈社交网络、广告推荐！
- Neo4j，InfoGrid

![1596272131424](redis%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.images/1596272131424.png)

## Redis入门

### 概述

> Redis（==Re==mote ==Di==ctionary ==S==erver )，即远程字典服务，

是一个开源的使用ANSI [C语言](https://baike.baidu.com/item/C语言)编写、支持网络、可基于内存亦可持久化的日志型、Key-Value[数据库](https://baike.baidu.com/item/数据库/103728)，并提供多种语言的API。 redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。 免费开源，也被人称为结构化数据库

> Redis能干嘛？

1、内存存储、持久化，内存中是断电即失，所以持久化很重要（rdb、oaf）

2、效率高，可以用于高速缓存

3、发布订阅系统

4、地图信息分析

5、计时器、计数器（浏览量）

6、......

> 特性

1、多样的数据类型

2、持久化

3、集群

4、事务

5、......

### Linux安装

1、官网下载最新的版本`redis-5.0.7.tar.gz`

2、上传至/opt，解压

```bash
tar -zxvf redis-5.0.7.tar.gz
```

3、进入解压后的文件，有redis配置文件redis.conf

4、环境安装

```bash
gcc -v #查看环境是否有gcc环境
yum install gcc-c++ #安装gcc环境
make #在解压路径下执行 make命令
make install #在解压路径下执行 make命令
```

5、默认安装路径`/usr/local/bin`

6、copy配置文件到安装路径，修改配置文件（设置redis服务后台启动）启动redis服务

```bash
mkdir redisconfig
cp /opt/redis-5.0.7/redis.conf redisconfig/
vi rediscoonfig/redis.conf 					#修改daemonize yes 因为默认不是后台启动，设置为后天启动redis服务
redis-server redisconfig/redis.conf 		#启动redis服务
```

7、使用redis-cli进行连接测试

```bash
redis-cli -p 6379 							#启动redis客户端 默认端口 6379

127.0.0.1:6379> ping						#查看是否连接成功
PONG
127.0.0.1:6379> set name lafer				#设置key value
OK
127.0.0.1:6379> set age 21
OK
127.0.0.1:6379> keys *						#查看所有key
1) "name"
2) "age"
127.0.0.1:6379> shutdown					#关闭redis服务
not connected> exit							#退出

ps -ef|grep redis							#查看redis服务端进程、客户端进程是否启动
```

### 性能测试

Redis性能测试基本命令

```bash
redis-benchmark [option] [option value]
```

redis 性能测试工具可选参数如下所示：

| 序号 | 选项      | 描述                                       | 默认值    |
| :--- | :-------- | :----------------------------------------- | :-------- |
| 1    | **-h**    | 指定服务器主机名                           | 127.0.0.1 |
| 2    | **-p**    | 指定服务器端口                             | 6379      |
| 3    | **-s**    | 指定服务器 socket                          |           |
| 4    | **-c**    | 指定并发连接数                             | 50        |
| 5    | **-n**    | 指定请求数                                 | 10000     |
| 6    | **-d**    | 以字节的形式指定 SET/GET 值的数据大小      | 2         |
| 7    | **-k**    | 1=keep alive 0=reconnect                   | 1         |
| 8    | **-r**    | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
| 9    | **-P**    | 通过管道传输 <numreq> 请求                 | 1         |
| 10   | **-q**    | 强制退出 redis。仅显示 query/sec 值        |           |
| 11   | **--csv** | 以 CSV 格式输出                            |           |
| 12   | **-l**    | 生成循环，永久执行测试                     |           |
| 13   | **-t**    | 仅运行以逗号分隔的测试命令列表。           |           |
| 14   | **-I**    | Idle 模式。仅打开 N 个 idle 连接并等待。   |           |

> 测试

```bash
[root@VM-0-14-centos bin]# redis-benchmark -h localhost -p 6379 -c 100 -n 100000
====== SET ======
  100000 requests completed in 1.59 seconds 	#10w个请求在1.59秒内完成
  100 parallel clients							#100个并行连接数
  3 bytes payload								#每次请求三个字节
  keep alive: 1									#保持一个服务端

37.87% <= 1 milliseconds
99.31% <= 2 milliseconds
99.67% <= 3 milliseconds
99.90% <= 4 milliseconds
99.93% <= 5 milliseconds
100.00% <= 5 milliseconds
63091.48 requests per second
```

### 基础知识

> 默认有16个数据库

在redis.conf 中 通过 database 设置  第0个数据库

```bash
127.0.0.1:6379> select 8					#选择指定数据库
OK
127.0.0.1:6379[8]> dbsize					#查看当前库大小
(integer) 0
127.0.0.1:6379[8]> set name value
OK
127.0.0.1:6379[8]> dbsize
(integer) 1
127.0.0.1:6379[8]> select 1
OK
127.0.0.1:6379[1]> dbsize
(integer) 0
127.0.0.1:6379[1]> get name
(nil)
127.0.0.1:6379[1]> select 8
OK
127.0.0.1:6379[8]> get name
"value"
127.0.0.1:6379[8]> flushdb					#清空当前库
OK
127.0.0.1:6379[8]> keys *
(empty list or set)
127.0.0.1:6379[8]> flushdb
OK
127.0.0.1:6379[8]> keys *
(empty list or set)
127.0.0.1:6379[8]> flushall					#清空所有库
OK
```

> Redis是单线程的！！！

Redis是很快的，Redis是基于内存操作的，CPU不是Redis性能瓶颈，Redis的瓶颈是根据机器内存和网络带宽，即然可以使用单线程来实现，那就用单线程了。

Redis是C语言编写的，官方提供的数据是100000+ 的QPS，完全不必使用key-value的Memecache差

Redis为什么单线程还这么快？

Redis将所有的数据都放在内存中，所以使用单线程去操作是最快的，多线程CPU存在上下文切换（多线程耗时的操作），对于内存系统来说，没有上下文切换效率就是最高的。

## 五大数据类型

Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。 它支持多种类型的数据结构，如 字符串（strings）， 散列（hashes）， 列表（lists）， 集合（sets）， 有序集合（sorted sets） 与范围查询， bitmaps， hyperloglogs 和 地理空间（geospatial） 索引半径查询。 Redis 内置了 复制（replication），LUA脚本（Lua scripting）， LRU驱动事件（LRU eviction），事务（transactions） 和不同级别的 磁盘持久化（persistence）， 并通过 Redis哨兵（Sentinel）和自动 分区（Cluster）提供高可用性（high availability）。

### Redis-Key

```bash
127.0.0.1:6379> set name lafer	#set key value
OK
127.0.0.1:6379> set age 18		
OK
127.0.0.1:6379> keys *			#查看所有key
1) "age"
2) "name"
127.0.0.1:6379> move name 1		#移除当前key   后面1 是指的哪个数据库
(integer) 1
127.0.0.1:6379> keys *
1) "age"
127.0.0.1:6379> EXPIRE age 10	#设置当前key过期时间
(integer) 1
127.0.0.1:6379> ttl age			#查看剩余过期时间
(integer) 7
127.0.0.1:6379> ttl age			
(integer) -2					#返回-2说明已经过期
127.0.0.1:6379> get age
(nil)
127.0.0.1:6379> set key1 value1
OK
127.0.0.1:6379> set age 1323
OK
127.0.0.1:6379> type key1		#查看当前key的类型
string
127.0.0.1:6379> type age
string
```

### String (字符串)

```bash
127.0.0.1:6379> APPEND key1 java		#当前key追加一个字符串
(integer) 10
127.0.0.1:6379> get key1
"value1java"
127.0.0.1:6379> get key2
(nil)
127.0.0.1:6379> append key2 value2		#如果当前key不存在相当于set key value操作
(integer) 6
127.0.0.1:6379> get key2
"value2"
127.0.0.1:6379> set views 0
OK
127.0.0.1:6379> INCR views				#自增操作 （可以用来记录网页浏览量）
(integer) 1
127.0.0.1:6379> get views
"1"
127.0.0.1:6379> INCRBY views 10			#可以设置步长
(integer) 11
127.0.0.1:6379> set key "java"
OK
127.0.0.1:6379> getrange key 0 2		#相当于java中String.subString() 字符串范围
"jav"
127.0.0.1:6379> get key
"java"
127.0.0.1:6379> SETRANGE key 2 av		#替换指定位置开始的字符串
(integer) 4
127.0.0.1:6379> get key
"jaav"
# setex (set with expire) 设置过期时间
# setnx	(set if not exist) 如果不存在设置 （分布式锁经常使用）
127.0.0.1:6379> FLUSHALL				#清空所有数据库 flushdb 清空当前数据库
OK
127.0.0.1:6379> SETEX db 10 redis		#设置10秒的过期时间
OK
127.0.0.1:6379> ttl db					#查看过期时间
(integer) 4
127.0.0.1:6379> ttl db
(integer) -2
127.0.0.1:6379> SETNX men mongodb		#如果不存在设置成功
(integer) 1
127.0.0.1:6379> SETNX men hbase			#如果存在设置失败
(integer) 0
127.0.0.1:6379> get men
"mongodb"
# mset 批量设置 同样有对应的msetnx
# mget 批量获取
127.0.0.1:6379> mset k1 v1  k2 v2 k3 v3
OK
127.0.0.1:6379> keys *
1) "k3"
2) "k1"
3) "k2"
127.0.0.1:6379> MSETNX k1 v5 k4 v4		#msetnx 是原子性操作指令，要么成功，要么失败。
(integer) 0
127.0.0.1:6379> mget k1 k2 k3 k4
1) "v1"
2) "v2"
3) "v3"
4) (nil)
127.0.0.1:6379> keys *
1) "k3"
2) "k1"
3) "k2"
127.0.0.1:6379> GETSET db redis			#先get后set 组合命令
(nil)
127.0.0.1:6379> get db
"redis"
127.0.0.1:6379> GETSET db mongodb
"redis"
127.0.0.1:6379> get db
"mongodb"
# 设计一个用户的粉丝数量  user:{id}:{field}
127.0.0.1:6379> set user:123:flows 0
OK
127.0.0.1:6379> INCR user:123:flows
(integer) 1
127.0.0.1:6379> get user:123:flows
"1"
```

### List

基本数据类型列表

所有List命令都是以L开头的

```bash
127.0.0.1:6379> LPUSH list1 v1					#将一个或多个值，插入到列表的头部（左）
(integer) 1
127.0.0.1:6379> LPUSH list1 v2
(integer) 2
127.0.0.1:6379> LPUSH list1 v3
(integer) 3
127.0.0.1:6379> LRANGE list1 0 -1
1) "v3"
2) "v2"
3) "v1"
127.0.0.1:6379> RPUSH list1 v4 v5				#将一个或多个值，插入列表的尾部（右）
(integer) 5
127.0.0.1:6379> lrange list1 0 -1
1) "v3"
2) "v2"
3) "v1"
4) "v4"
5) "v5"
127.0.0.1:6379> lpop list1						#移除list头部的一个值（左）
"v3"
127.0.0.1:6379> lpop list1
"v2"
127.0.0.1:6379> rpop list1						#移除list头部的一个值（右）
"v5"
127.0.0.1:6379> LRANGE list1 0 -1				
1) "v1"
2) "v4"
127.0.0.1:6379> lindex list1 1					#相当于java的list.get()根据下标获取元素
"v4"
127.0.0.1:6379> lindex list1 0
"v1"
127.0.0.1:6379> llen list1
(integer) 2
127.0.0.1:6379> LRANGE list1 0 -1
1) "v1"
2) "v4"
127.0.0.1:6379> lpush list1 v1 v2 v3
(integer) 5
127.0.0.1:6379> LRANGE list1 0 -1
1) "v3"
2) "v2"
3) "v1"
4) "v1"
5) "v4"
127.0.0.1:6379> LREM list1 1 v1					#移除指定个数的value
(integer) 1
127.0.0.1:6379> LRANGE list1 0 -1
1) "v3"
2) "v2"
3) "v1"
4) "v4"
127.0.0.1:6379> LREM list1 3 v1
(integer) 1
127.0.0.1:6379> lpush list1 v1 v5
(integer) 5
127.0.0.1:6379> lrange list1 0 -1
1) "v5"
2) "v1"
3) "v3"
4) "v2"
5) "v4"
127.0.0.1:6379> LTRIM list1 1 3					#截取指定范围的元素
OK
127.0.0.1:6379> lrange list1 0 -1
1) "v1"
2) "v3"
3) "v2"
127.0.0.1:6379> RPOPLPUSH list1 list2			#移除list1链表尾部元素放到list2的链表头部
"v2"
127.0.0.1:6379> 
127.0.0.1:6379> lrange list1 0 -1		
1) "v1"
2) "v3"
127.0.0.1:6379> LRANGE list2 0 -1
1) "v2"
127.0.0.1:6379> lset list1 0 v2					#跟新下标的元素
OK
127.0.0.1:6379> lrange list1 0 -1
1) "v2"
2) "v3"
127.0.0.1:6379> lset list3 0 23423				#不存在会报错
(error) ERR no such key
127.0.0.1:6379> lset list1 8 0asdf
(error) ERR index out of range
127.0.0.1:6379> lrange list1 0 -1
1) "v2"
2) "v3"
127.0.0.1:6379> LINSERT list1 before v3 v1		#将某个具体的value 插入到 指定元素的前面或者后面
(integer) 3
127.0.0.1:6379> lrange list1 0 -1
1) "v2"
2) "v1"
3) "v3"
```

> 总结

作为基本的数据结构之一，一切数据结构可分为两种，一种是链表，一种是数组。Redis中的List其实使用的一个双向链表，所以在两端插入和删除的效率高，根据下标进行的操作较慢

### Set

```bash
127.0.0.1:6379> SPOP set1
"redis"
127.0.0.1:6379> SPOP set1
"c#"
127.0.0.1:6379> SADD set1 java c++ c c# 			#向set中添加元素
(integer) 4
127.0.0.1:6379> SADD set1 java redis
(integer) 1
127.0.0.1:6379> SMEMBERS set1						#查看set中所有元素
1) "java"
2) "c"
3) "c++"
4) "c#"
5) "redis"
127.0.0.1:6379> SISMEMBER set1 java					#判断元素是否在当前集合中
(integer) 1
127.0.0.1:6379> SISMEMBER set1 ja
(integer) 0
127.0.0.1:6379> scard set1							#查看当前集合的个数
(integer) 5
127.0.0.1:6379> SREM set1 java c					#删除当前集合中的元素
(integer) 2
127.0.0.1:6379> SMEMBERS set1
1) "c++"
2) "c#"
3) "redis"
127.0.0.1:6379> SRANDMEMBER set1 1					#随机获取当前集合中的元素
1) "redis"
127.0.0.1:6379> SRANDMEMBER set1 1
1) "c++"					
127.0.0.1:6379> SPOP set1							#随机删除当前集合中的元素
"redis"
127.0.0.1:6379> SPOP set1 2
"c#"
"c++"
127.0.0.1:6379> sadd set1 java c++ c redis php
(integer) 5
127.0.0.1:6379> smove set1 set2 c++					#移动指定的元素到另一个集合中
(integer) 1
127.0.0.1:6379> SMEMBERS set1
1) "java"
2) "redis"
3) "c"
4) "php"
127.0.0.1:6379> SMEMBERS set2
1) "c++"
127.0.0.1:6379> SMEMBERS set2
1) "c++"
2) "java"
3) "c"
127.0.0.1:6379> SMEMBERS set1
1) "java"
2) "redis"
3) "c"
4) "php"
127.0.0.1:6379> sdiff set1 set2						#多个集合的差集
1) "php"
2) "redis"
127.0.0.1:6379> SINTER set1 set2					#多个集合的交集
1) "java"
2) "c"
127.0.0.1:6379> SUNION set1 set2					#多个集合的并集
1) "php"
2) "c"
3) "c++"
4) "java"
5) "redis"
```

