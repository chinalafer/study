127.0.0.1:6379> ZREM china:city shanghai
(integer) 1
127.0.0.1:6379> ZRANGE china:city 0 -1
1) "lanzhou"
2) "changsha"
3) "hangzhou"
4) "beijin"NoSQL概述

### 为什么使用NoSQL

> 单机MySQL年代

![1596267189346](https://gitee.com/lafer/laferImage/raw/master/img/1596267189346.png)

网站的访问量不大，单个数据完全够用。

随着时代的发展，单数据库的网站遇到一下瓶颈

1、数据量太大一个机器放不下

2、数据的索引（B + Tree），一个机器的内存放不下

3、访问量（读写混合），一个服务气承受不了

> Memcached（缓存） + MySQL + 垂直拆分

网站80%的情况都是在读，使用缓存来减少重复读，保证效率降低mysql的压力

![1596267508787](https://gitee.com/lafer/laferImage/raw/master/img/1596267508787.png)

> 分库分表 + 水平拆分 + MySQL集群

==数据库的本质：读、写==

早些年MyISAM：表锁，效率极低

Innodb：行锁

使用分库分表解决写的压力。

![1596268392475](https://gitee.com/lafer/laferImage/raw/master/img/1596268392475.png)

> 最近的年代

定位、地图、人际关系都是一种数据，MySQL等关系型数据库不满足需求。而且MySQL等关系型数据库用来存储图片、文件等大文件信息，会导致数据库的效率降低，再大数据IO的压力下，关系型数据的表不好改变。

**目前一个基本的互联网项目**

![1596269510606](https://gitee.com/lafer/laferImage/raw/master/img/1596269510606.png)

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
  - MongoDB是一个介于关系形数据库和非关系型数据库中间的产品，MongoDB是非关系型数据库中功能最丰富，最像关系星数据库的。

**列存储数据库**

- HBase

**图关系型数据库**

- 它存放的不是图形，而是关系，比如：朋友圈社交网络、广告推荐！
- Neo4j，InfoGrid

![1596272131424](https://gitee.com/lafer/laferImage/raw/master/img/1596272131424.png)

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

### List（列表）

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

### Set（集合）

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

### Hash（哈希）

```bash
127.0.0.1:6379> HSET myhash field1 lafer					#set一个具体的key-value
(integer) 1
127.0.0.1:6379> hget myhash field1							#get一个具体的key-value
"lafer"
127.0.0.1:6379> HMSET myhash field2 java field3 reids		#设置多个key-value
OK
127.0.0.1:6379> hget myhash field1
"lafer"
127.0.0.1:6379> hmget myhash field1 field1 field2 field3	#获取多个key-value
1) "lafer"
2) "lafer"
3) "java"
4) "reids"
127.0.0.1:6379> HGETALL myhash								#获取全部的key-value
1) "field1"
2) "lafer"
3) "field2"
4) "java"
5) "field3"
6) "reids"
127.0.0.1:6379> HDEL myhash field1							#删除指定的key
(integer) 1
127.0.0.1:6379> HGETALL myhash
1) "field2"
2) "java"
3) "field3"
4) "reids"
127.0.0.1:6379> HLEN myhash									#获取hash的长度
(integer) 2
127.0.0.1:6379> HEXISTS myhash field2						#判断指定key是否存在
(integer) 1
127.0.0.1:6379> HEXISTS myhash filed1
(integer) 0
127.0.0.1:6379> HKEYS myhash								#获取所有的key
1) "field2"
2) "field3"
127.0.0.1:6379> HVALS myhash								#获取所有的value
1) "java"
2) "reids"
127.0.0.1:6379> hset myhash field1 10
(integer) 1
127.0.0.1:6379> HGETALL myhash		
1) "field2"
2) "java"
3) "field3"
4) "reids"
5) "field1"
6) "10"
127.0.0.1:6379> HINCRBY myhash field1 1						#指定增量对指定value进行变更
(integer) 11
127.0.0.1:6379> HGETALL myhash
1) "field2"
2) "java"
3) "field3"
4) "reids"
5) "field1"
6) "11"
127.0.0.1:6379> HSETNX myhash field4 value4					#如果不存在则设置成功
(integer) 1
127.0.0.1:6379> HSETNX myhash field4 value4					#如果存在设置不成功
(integer) 0
```

hash 适合用于对象的存储，String更适合字符串存储

### Zset（有序集合）

```bash
127.0.0.1:6379> ZADD myzset 1 one							#设置一个值
(integer) 1
127.0.0.1:6379> ZADD myzset 2 two 3 three					#设置多个值
(integer) 2
127.0.0.1:6379> ZRANGE myzset 0 -1							#获取所有值
1) "one"
2) "two"
3) "three"
127.0.0.1:6379> ZRANGEBYSCORE myzset -inf +inf				#从小到大排序
1) "one"
2) "two"
3) "three"
127.0.0.1:6379> ZRANGEBYSCORE myzset -inf +inf withscores	#从小到大排序，显示分数
1) "one"
2) "1"
3) "two"
4) "2"
5) "three"
6) "3"
127.0.0.1:6379> ZRANGEBYSCORE myzset -inf 2 withscores		#从小到大排序，score小于等于2
1) "one"
2) "1"
3) "two"
4) "2"
127.0.0.1:6379> ZRANGE myzset 0 -1
1) "one"
2) "two"
3) "three"
127.0.0.1:6379> ZREM myzset one								#移除zset中的指定元素，可多个
(integer) 1
127.0.0.1:6379> ZRANGE myzset 0 -1
1) "two"
2) "three"
127.0.0.1:6379> zcard myzset								#获取zset中的元素个数
(integer) 2
127.0.0.1:6379> ZREVRANGE myzset 0 -1						#从大到小排序
1) "three"
2) "two"
127.0.0.1:6379> zadd myzset 4 java 5 redis 6 c++ 7 c		
(integer) 4
127.0.0.1:6379> ZCOUNT myzset 3 5							#获取score区间的元素个数
(integer) 3
```

案例思路：排行榜，班级分数排序等

## 三种特殊数据类型

### Geospatial

```bash
# geoadd 添加地理位置
# 规则：两级无法直接添加，一般来说通过java程序一次性通过文件导入
# 有效的经度从-180度到180度。
# 有效的纬度从-85.05112878度到85.05112878度。
# 当坐标位置超出上述指定范围时，该命令将会返回一个错误。
127.0.0.1:6379> GEOADD china:city 116.23 40.22 beijin 120.21 30.20 hangzhou 121.48 31.40 shanghai 
(integer) 3
127.0.0.1:6379> GEOADD china:city 112.98 28.25 changsha 103.71 36.10 lanzhou
(integer) 2
# GEOPOS 获取对应位置的经纬度
127.0.0.1:6379> GEOPOS china:city hangzhou lanzhou			
1) 1) "120.21000176668167114"
   2) "30.19999988833350102"
2) 1) "103.71000140905380249"
   2) "36.09999935873431554"
# GEODIST 查看长沙到杭州的直线距离
127.0.0.1:6379> GEODIST china:city changsha hangzhou km		
"734.3851"
# GEORADIUS 查看以指定经纬度为中心附近指定距离的元素
127.0.0.1:6379> GEORADIUS china:city 110 33 1000 km			
1) "lanzhou"
2) "beijin"
3) "changsha"
127.0.0.1:6379> GEORADIUS china:city 110 33 1000 km withdist
1) 1) "lanzhou"
   2) "671.2809"
2) 1) "beijin"
   2) "976.2648"
3) 1) "changsha"
   2) "600.3157"
127.0.0.1:6379> GEORADIUS china:city 110 33 1000 km withdist withcoord
1) 1) "lanzhou"
   2) "671.2809"
   3) 1) "103.71000140905380249"
      2) "36.09999935873431554"
2) 1) "beijin"
   2) "976.2648"
   3) 1) "116.23000055551528931"
      2) "40.2200010338739844"
3) 1) "changsha"
   2) "600.3157"
   3) 1) "112.9800000786781311"
      2) "28.25000087963665152"
127.0.0.1:6379> GEORADIUS china:city 110 33 1000 km withdist withcoord count 1
1) 1) "changsha"
   2) "600.3157"
   3) 1) "112.9800000786781311"
      2) "28.25000087963665152"
127.0.0.1:6379> GEORADIUS china:city 110 33 1000 km withdist withcoord count 2
1) 1) "changsha"
   2) "600.3157"
   3) 1) "112.9800000786781311"
      2) "28.25000087963665152"
2) 1) "lanzhou"
   2) "671.2809"
   3) 1) "103.71000140905380249"
      2) "36.09999935873431554"
# GEORADIUSBYMEMBER 查看以指定元素为中心附近指定距离的元素
127.0.0.1:6379> GEORADIUSBYMEMBER china:city hangzhou 1000 km			
1) "changsha"
2) "hangzhou"
3) "shanghai"
# geohash 该命令将返回11个字符的Geohash字符串，越相似距离越近
127.0.0.1:6379> GEOHASH china:city shanghai hangzhou changsha beijin
1) "wtw6sk5n300"
2) "wtm7z3wrb00"
3) "wt02dyv80v0"
4) "wx4sucu47r0"
```

> Geospatial底层还是使用的zset,那么zset的命令一样适用

```bash
127.0.0.1:6379> ZREM china:city shanghai
(integer) 1
127.0.0.1:6379> ZRANGE china:city 0 -1
1) "lanzhou"
2) "changsha"
3) "hangzhou"
4) "beijin"
```

### HyperLogLogs

基数统计的数据结构

应用场景：网页的UV（一个人访问一个网站多次，但是还算作一个人）这种情况如果使用传统的set进行保存，但是用户id是没用的，内存消耗大，目的只是为了统计，而不是保存用户id。但是使用HyperLogLogs是有误差的，但是这种统计允许存在这种误差(0.81%的错误率)。

优点：占用的内存是固定的，2^64不同的元素计数，只需要12KB内存！从内存角度来比较的话HyperLogLogs是首选。

```bash
127.0.0.1:6379> PFADD mykey2 a b c j k l m o p g d
(integer) 1
127.0.0.1:6379> pfadd mykey a b c d e f g h i
(integer) 1
127.0.0.1:6379> PFMERGE mykey3 mykey mykey2
OK
127.0.0.1:6379> PFCOUNT mykey3
(integer) 15
```

### Bitmaps

> 位存储

统计用户信息：活跃，不活跃；登录，未登录；打卡，未打卡！两个状态的都可以使用Bitmaps!

Bitmaps位图，操作二进制位进行记录，只有0和1两个状态！

```bash
# 设置指定位的 0 1 状态
127.0.0.1:6379> SETBIT sign 0 0
(integer) 0
127.0.0.1:6379> SETBIT sign 1 1 
(integer) 0
127.0.0.1:6379> SETBIT sign 2 1
(integer) 0
127.0.0.1:6379> SETBIT sign 3 0
(integer) 0
127.0.0.1:6379> SETBIT sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 1
(integer) 0
127.0.0.1:6379> setbit sign 6 0
127.0.0.1:6379> SETBIT sign 7 1
(integer) 0
127.0.0.1:6379> SETBIT sign  8 1
(integer) 0
127.0.0.1:6379> SETBIT sign  10 1
(integer) 0
127.0.0.1:6379> SETBIT sign  19 1
(integer) 0
# 获取指定位置上的bit
127.0.0.1:6379> GETBIT sign 3
(integer) 0
127.0.0.1:6379> GETBIT sign 4
(integer) 1
# bitcount 统计的是字符串指定起始位置的字节数（统计字符串被设置为1的bit数.）
127.0.0.1:6379> BITCOUNT sign 0 0				
(integer) 5
127.0.0.1:6379> BITCOUNT sign 0 1
(integer) 7
127.0.0.1:6379> BITCOUNT sign 1 2
(integer) 3
```

## 事务

Redis事务的本质：一组命令的集合！一个事务中的所有命令都会被序列化，在事务执行过程中，会按照顺序执行，一次性、顺序性、排他性！执行一系列的命令！

```bash
---- 队列 set set set 执行 ---
```

==Redis事务没有隔离级别的概念==

所有的命令在事务中，并没有直接被执行，只有在发起执行命令的时候才会执行！exec

==Redis单条命令是保证原子性的，但是事务不保证原子性==

Redis的事务：

- 开启事务（multi）
- 命令入队（.......）
- 执行事务（exec）

> 正常执行

```bash
127.0.0.1:6379> multi					#开启事务
OK
#命令入队
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> exec					#执行事务
1) OK
2) OK
3) "v2"
4) OK
```

> 放弃事务

```bash
127.0.0.1:6379> MULTI					#开启事务
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> DISCARD					#取消事务
OK			
127.0.0.1:6379> get k4					#事务队列中的命令都不会被执行
(nil)
```

> 编译型异常（代码中有问题，命令有错！），事务中所有的命令都不会被执行！

```bash
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> gethash k2				#错误的命令
(error) ERR unknown command `gethash`, with args beginning with: `k2`, 
127.0.0.1:6379> set k5 v5
QUEUED
127.0.0.1:6379> EXEC					#执行事务报错
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k4					#所有的命令都不会执行
(nil)
127.0.0.1:6379> get k5
(nil)
```

> 运行时异常（1 / 0）, 如果事务队列中存在语法性错误，那么执行命令的时候，其他命令是可以正常执行的，错误命令抛出异常···

```bash
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> set k1 "v1"
QUEUED
# 执行的时候报错，不影响其他执行的执行，所以说Redis单个命令是保证原子性的，事务是不保证原子性的。
127.0.0.1:6379> INCR k1				
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> set k5 v5
QUEUED
127.0.0.1:6379> EXEC
1) OK
2) (error) ERR value is not an integer or out of range
3) OK
4) OK
127.0.0.1:6379> get k4
"v4"
127.0.0.1:6379> get k5
"v5"
127.0.0.1:6379> get k1
"v1"
```

> ==监控！watch (面试重点)==

**悲观锁**

- 认为什么时候都会出问题，无论什么时候都会加锁！

**乐观锁**

- 认为什么时候都不会出现问题，所以不会上锁！更新数据的时候去判断一下，在此期间数据是否发生过修改。
- 获取version
- 更新的时候比较version

> 监视测试

正常执行成功！

```bash
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> WATCH money out				#监视 money 和 out 对象
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> DECRBY money 20
QUEUED
127.0.0.1:6379> INCRBY out 20
QUEUED
127.0.0.1:6379> exec						#数据在此期间没有发生变动，事务正常执行结束。
1) (integer) 80
2) (integer) 20
```

手动关闭事务

```bash
127.0.0.1:6379> get money
"90"
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> INCRBY money 10
QUEUED
127.0.0.1:6379> DISCARD
OK
127.0.0.1:6379> exec
(error) ERR EXEC without MULTI
```

多线程修改值，使用what当Redis的乐观锁操作！

```bash
127.0.0.1:6379> watch money out
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> decrby money 20
QUEUED
127.0.0.1:6379> incrby out 20
QUEUED
127.0.0.1:6379> exec						#数据在此期间被其他线程修改过，事务执行失败
(nil)
```

如果获取失败，获取最新值重新操作！

```bash
127.0.0.1:6379> UNWATCH 
OK
127.0.0.1:6379> WATCH money out
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> DECRBY money 10
QUEUED
127.0.0.1:6379> INCRBY out 10
QUEUED
127.0.0.1:6379> exec
1) (integer) 90
2) (integer) 30
```

## Jedis

Jedis是Redis官方推荐的java连接开发工具！使用Java操作Redis中间件！

Jedis中的方法和Redis中的命令都是一样的！

> Jedis操作事务

```java
public class TestMulti {
    public static void main(String[] args) {
        //创建客户端连接服务端，redis服务端需要被开启
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "java");
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
        try{
            //向redis存入一条数据
            multi.set("json", result);
            //再存入一条数据
            multi.set("json2", result);
            //这里引发了异常，用0作为被除数
            int i = 100/0;
            //如果没有引发异常，执行进入队列的命令
            multi.exec();
        }catch(Exception e){
            e.printStackTrace();
            //如果出现异常，回滚
            multi.discard();
        }finally{
            System.out.println(jedis.get("json"));
            System.out.println(jedis.get("json2"));
            //最终关闭客户端
            jedis.close();
        }
    }
}
```

## StringBoot整合

SpringBoot操作数据：Spring-data

说明：在SpringBoot2.x之后，jedis替换为了lettuce

jedis：采用的直连，多个线程操作的话，是不安全的，如果要避免不安全，使用jedis pool连接池！更像BIO模式

lettuce：采用netty，实例可以在多个线程中共享，不存在线程不安全的情况，可以减少线程数量，更像NIO模式

```java
//默认的RedisAutoConfiguration
@Bean
@ConditionalOnMissingBean(name = "redisTemplate") //自己定义一个redisTemplate来替换这个默认的！
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    throws UnknownHostException {
    // 默认的RedisTemplate没有过多的设置，redis 对象都需要序列化
    // 两个泛型都是 Object 的类型， 后面使用需要强制转换 <String, Object>
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}

@Bean
@ConditionalOnMissingBean	// 由于String 是redis中最常用的类型，所以单独提出来了一个Bean
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    throws UnknownHostException {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```

> 整合测试

1、导入依赖

```xml
<!--redis依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```



2、配置连接

```properties
# SpringBoot 所有的配置类，都有一个自动配置类，  RedisAutoConfiguration.java
# 自动配置类都会绑定一个 properties 配置文件    RedisProperties
# 配置redis
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

3、测试

```java
@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // redisTemplate 操作不同的数据类型，和基本的命令相同
        // opsForGeo
        // opsForHash
        // opsForHyperLogLog
        // opsForList
        // opsForSet
        // opsForValue
        // opsForZSet

        // 除了基本的操作，常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD

        // 获取redis的连接对象
        // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // connection.flushDb();
        // connection.flushDb();
        
        redisTemplate.opsForValue().set("name", "凉风");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
```

使用默认的RedisTemplate会使用JDK默认的序列化方式，在redis客户端会发生乱码，一般来说会使用Json序列化。

![image-20200807233523012](https://gitee.com/lafer/laferImage/raw/master/img/image-20200807233523012.png)

![image-20200807233600604](https://gitee.com/lafer/laferImage/raw/master/img/image-20200807233600604.png)

定义自己的RedisTemplate

```java
@Configuration
public class RedisConfig {

  @Bean
  @SuppressWarnings("all")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
      // 方便开发， 直接使用<String, Object>
      RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
      template.setConnectionFactory(factory);
      // Json序列化配置
      Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
      ObjectMapper om = new ObjectMapper();
      om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      jackson2JsonRedisSerializer.setObjectMapper(om);
      // String序列化配置
      StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

      // key采用String的序列化方式
      template.setKeySerializer(stringRedisSerializer);
      // hash的key也采用String的序列化方式
      template.setHashKeySerializer(stringRedisSerializer);
      // value序列化方式采用jackson
      template.setValueSerializer(jackson2JsonRedisSerializer);
      // hash的value序列化方式采用jackson
      template.setHashValueSerializer(jackson2JsonRedisSerializer);
      template.afterPropertiesSet();
      return template;
  }
}
```

```java
@Test
void test() throws JsonProcessingException {
    User lafer = new User("lafer", 18);
    String s = new ObjectMapper().writeValueAsString(lafer);
    redisTemplate.opsForValue().set("user", s);
    System.out.println(redisTemplate.opsForValue().get("user"));
}
```

实际开发中，一般来说直接封装一个RedisUtil来进行操作。

## Redis.conf详解

> 单位

```bash
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
#
# units are case insensitive so 1GB 1Gb 1gB are all the same.	配置文件units对大小写不敏感
```

> 包含

```bash
# include /path/to/local.conf
# include /path/to/other.conf  	相当于合并多个配置文件
```

> 网络

```bash
bind 127.0.0.1			# 绑定的ip
protected-mode yes		# 保护模式
port 6379				# 端口设置
```

> 通用GENERAL

```bash
daemonize yes						# 以守护进程的方式运行，默认为no
pidfile /var/run/redis_6379.pid		# 如果以后台的方式运行，我们就需要指定一个pid文件

# 日志
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
loglevel notice
logfile "" 							# 日志生成的文件名
databases 16						# 数据库数量， 默认16个
always-show-logo yes				# 是否总是显示logo
```

> 快照SNAPSHOTTING

```bash
# 持久化， 在规定时间内，执行了多少次操作，则会持久化到文件 .rdb .aof
# redis是内存数据库，如果没有持久化，那么数据断电及失。
save 900 1			# 如果900s内，如果至少有一个key进行了修改，及时进行持久化操作
save 300 10
save 60 10000

stop-writes-on-bgsave-error yes 	# 持久化如果出错，是否继续进行工作
rdbcompression yes					# 是否压缩rdb文件，需要消耗cpu资源
rdbchecksum yes						# 保存rdb文件的时候进行错误校验
dbfilename dump.rdb					# rdb保存的文件名称
dir ./								# rdb文件保存的目录文件
```

> REPLICATION 复制

```bash
# replicaof <masterip> <masterport>			#配置主机的ip和地址
# masterauth <master-password>				#配置主机的密码
```

> 安全SECURITY

```bash
127.0.0.1:6379> CONFIG GET requirepass				# 获取redis的密码
1) "requirepass"
2) ""
127.0.0.1:6379> config set requirepass "123"		# 设置redis的密码
OK
127.0.0.1:6379> keys *								# 没有权限
(error) NOAUTH Authentication required.
127.0.0.1:6379> AUTH 123							# 验证密码
OK
127.0.0.1:6379> keys *
1) "out"
2) "money"
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) "123"
```

> 限制CLIENTS

```bash
# maxclients 10000					# 设置能连接上redis的最大客户端的数量
# maxmemory <bytes>					# redis 配置最大的内存容量
# maxmemory-policy noeviction		# 内存到达上限之后的处理策略
	1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 
    2、allkeys-lru ： 删除lru算法的key   
    3、volatile-random：随机删除即将过期key   
    4、allkeys-random：随机删除   
    5、volatile-ttl ： 删除即将过期的   
    6、noeviction ： 永不过期，返回错误
```

> APPEND ONLY MODE aof配置

```bash
appendonly no						# 默认不开启，因为默认是rdb模式
appendfilename "appendonly.aof"		# aof持久化的文件名
# appendfsync always				# 每次修改都会 sync， 消耗性能
appendfsync everysec				# 每秒执行一次 sync，可能会丢失1s的数据
# appendfsync no					# 不执行 sync，这个时候操作系统自己同步数据
```



## Redis持久化

Redis是内存数据库，如果不将内存中的数据状态保存到磁盘，那么一旦服务器进程退出，服务器中的数据库状态也会消失，所以Redis提供了持久化功能。

**RDB（Redis DataBase）**

![image-20200808163612094](https://gitee.com/lafer/laferImage/raw/master/img/image-20200808163612094.png)

在指定的时间间隔内将内存中的数据集快照写入磁盘（SnapShot快照），它恢复时是将快照文件直接读到内存里。

Redis会单独创建（fork）一个子进程来进行持久化，会先将数据写到一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。整个过程中，主进程不进行任何的IO操作，这就确保了极高的性能。如果需要进行大规模数据恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。RDB的缺点就是最后一次持久化后的数据可能丢失。默认的就是RDB。

==rdb保存的文件默认是dump.rdb==

> 触发机制

1、sava的规则满足的情况下，会触发rdb规则

2、执行flushall命令，也会触发rdb规则

3、退出redis，也会产生rdb文件

> 如何恢复rdb文件

1、只需要将rdb文件放在redis启动目录就可以，redis启动的时候会自动检查dump.rdb恢复其中的数据

2、查看需要存放的位置

```bash
127.0.0.1:6379> config get dir
1) "dir"
2) "/usr/local/bin"
```

优点：

1、适合大规模的数据恢复！

2、对数据的完整性要求不高

缺点：

1、需要一定的时间间隔进程操作！如果redis意外宕机了，最后一次修改的数据就没有了。

2、fork进程的时候，会占用一定的内存空间。

**AOF（Append Only File）**

![image-20200808164215716](https://gitee.com/lafer/laferImage/raw/master/img/image-20200808164215716.png)

以日志的形式来记录每一个写操作，将Redis执行过的所有指令记录一下来（读操作不记录），只需追加文件但不可以改写文件，redis启动之初会读取该文件重新构建数据（redis重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作）

==aof保存的是 appendonly.aof 文件==

> 重写规则

aof默认就是文件的无限追加，文件会越来越大

```bash
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```

如果aof文件大于64m，fork一个新的进程来将我们的文件进行重写

> 配置

默认不开启，修改配置文件

```bash
appendonly yes
```

重启redis服务即可生效

如果aof文件有错位，这时候redis是启动不起来的，这时候使用redis-check-aof工具修复aof文件，发生错位的命令会会被删除

```bash
redis-check-aof appendonly.aof 
```

> 优点和缺点

优点：

1、每一次修改都同步，文件完整更好

2、每秒同步一次，可能会丢失一秒的数据

3、从不同步，效率最高

缺点：

1、相对数据文件来说，aof远远大于rdb，修复速度也比rdb慢

2、aof运行效率比rdb慢，所以默认配置就是rdb持久化

## Redis发布订阅

Redis发布订阅（pub/sub）是一种==消息通信模式==：发送者（pub）发送消息，订阅者（sub）接收消息。微博、微信关注系统！

Redis客户端可以订阅任意数量的频道

下图展示了频道 channel1 ， 以及订阅这个频道的三个客户端 —— client2 、 client5 和 client1 之间的关系：

![img](https://gitee.com/lafer/laferImage/raw/master/img/pubsub1.png)

当有新消息通过 PUBLISH 命令发送给频道 channel1 时， 这个消息就会被发送给订阅它的三个客户端：

![img](https://gitee.com/lafer/laferImage/raw/master/img/pubsub2.png)

**Redis发布订阅命令**

| 序号 | 命令及描述                                                   |
| :--- | ------------------------------------------------------------ |
| 1    | [PSUBSCRIBE pattern [pattern ...\]](https://www.runoob.com/redis/pub-sub-psubscribe.html) 订阅一个或多个符合给定模式的频道。 |
| 2    | [PUBSUB subcommand [argument [argument ...\]]](https://www.runoob.com/redis/pub-sub-pubsub.html) 查看订阅与发布系统状态。 |
| 3    | [PUBLISH channel message](https://www.runoob.com/redis/pub-sub-publish.html) 将信息发送到指定的频道。 |
| 4    | [PUNSUBSCRIBE [pattern [pattern ...\]]](https://www.runoob.com/redis/pub-sub-punsubscribe.html) 退订所有给定模式的频道。 |
| 5    | [SUBSCRIBE channel [channel ...\]](https://www.runoob.com/redis/pub-sub-subscribe.html) 订阅给定的一个或多个频道的信息。 |
| 6    | [UNSUBSCRIBE [channel [channel ...\]]](https://www.runoob.com/redis/pub-sub-unsubscribe.html) 指退订给定的频道。 |

> 测试

```bash
# 订阅端
127.0.0.1:6379> SUBSCRIBE lafer				#订阅lafer频道
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "lafer"
3) (integer) 1
1) "message"								#消息
2) "lafer"									#频道
3) "message"								#内容
1) "message"
2) "lafer"
3) "lf"
```

```bash
# 发送端
127.0.0.1:6379> PUBLISH lafer "message"		#发布者发布消息到频道
(integer) 1									#接受消息的订阅端数量
127.0.0.1:6379> PUBLISH lafer "lf"
(integer) 1
```

> 发布订阅频道结构原理

**1、订阅频道结构原理解析**

```bash
说明：每个 Redis 服务器进程都维持着一个表示服务器状态的 redis.h/redisServer 结构， 结构的 pubsub_channels 属性是一个字典， 这个字典就用于保存订阅频道的信息，其中，字典的键为正在被订阅的频道， 而字典的值则是一个链表， 链表中保存了所有订阅这个频道的客户端。
例子示意图：在下图展示的这个 pubsub_channels 示例中， client2 、 client5 和 client1 就订阅了 channel1 ， 而其他频道也分别被别的客户端所订阅。
```

![img](https://gitee.com/lafer/laferImage/raw/master/img/999593-20190722115529080-190360594.png)

```bash
操作：当客户端调用 SUBSCRIBE 命令时， 程序就将客户端和要订阅的频道在 pubsub_channels 字典中关联起来。
示意图：如果客户端 client10086 执行命令 SUBSCRIBE channel1 channel2 channel3 ，那么前面展示的 pubsub_channels 将变成下面这个样子，通过遍历所有输入频道。
```

![img](https://gitee.com/lafer/laferImage/raw/master/img/999593-20190722115737561-1282013618.png)

```bash
结论：通过 pubsub_channels 字典， 程序只要检查某个频道是否为字典的键， 就可以知道该频道是否正在被客户端订阅； 只要取出某个键的值， 就可以得到所有订阅该频道的客户端的信息。
```

**2、发布信息到频道结构解析**

```
原理说明：当调用 PUBLISH channel message 命令， 程序首先根据 channel 定位到字典的键， 然后将信息发送给字典值链表中的所有客户端。
例子示意图：对于以下这个 pubsub_channels 实例， 如果某个客户端执行命令 PUBLISH channel1 "hello moto" ，那么 client2 、 client5 和 client1 三个客户端都将接收到 "hello moto" 信息，通过遍历订阅频道的所有客户端。
```

![img](https://gitee.com/lafer/laferImage/raw/master/img/999593-20190722120719019-733971705.png)

**3、退订频道**

```
原理：使用 UNSUBSCRIBE 命令可以退订指定的频道， 这个命令执行的是订阅的反操作： 它从 pubsub_channels 字典的给定频道（键）中， 删除关于当前客户端的信息， 这样被退订频道的信息就不会再发送给这个客户端。
```

> 使用场景

1、实时消息系统

2、实时聊天室

3、订阅、关注系统

复杂场景使用MQ来做。

## Redis主从复制

#### 概念

主从复制，是将一台Redis服务器的数据，复制到其他Redis服务器，前者称为主节点（master/leader），后者称为从节点（slave/follower）；==数据的复制是单向的，只能从主节点到从节点。==Master以写为主，Slave以读为主。

==默认情况下，每天Redis服务器都是主节点；==

且一个主节点可以有多个从节点（或没有从节点），每一个从节点都只能有一个主节点。

**主从复制的作用主要有：**

1、数据冗余：主从复制实现了数据的热备份，是持久化之外的一种数据冗余方式

2、故障恢复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复；实际上是一种服务的冗余。

3、负载均衡：在主从复制的基础上，配合读写分离，可以由主节点提供写服务，由从节点提供读服务（即写Redis数据时应连接主节点，读Redis数据时应连接从节点），分担服务器负载；尤其是写少读多的场景下，通过多个从节点分担读负载，可以大大提高Redis服务器的并发量。

4、高可用（集群）基石：主从复制还是哨兵和集群能够实施的基础，因此说主从复制是Redis高可用的基石。

在真实的项目中，主从复制是必须要使用的，不可能单机使用Redis

==单台Redis最大使用内存不用该超过20G。==

#### 环境配置

```bash
127.0.0.1:6379> info replication					# 查看当前库的信息
# Replication
role:master											# 角色
connected_slaves:0									# slave个数
master_replid:9cc31b617587b8b4dbfa1791cc6fea940af91888
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

复制3个配置文件，修改对应信息

1、端口

2、pid名

3、log文件名

4、dump.rdb名

启动三台服务器，一主二从。

配置两台从机

```bash
127.0.0.1:6380> info replication
# Replication
role:master
connected_slaves:0
master_replid:669870c76012ac65968d9b07bbd73bf63f369312
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
127.0.0.1:6380> SLAVEOF 127.0.0.1 6379
OK
127.0.0.1:6380> info replication
# Replication
role:slave													# 从机
master_host:127.0.0.1										# 主机信息
master_port:6379
master_link_status:up
master_last_io_seconds_ago:0
master_sync_in_progress:0
slave_repl_offset:28
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:f2420ca32961e00f8ce020ce9c6ccbcc52bffc23
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:28
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:28‘
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:2
slave0:ip=127.0.0.1,port=6380,state=online,offset=70,lag=0	# 从机的信息
slave1:ip=127.0.0.1,port=6381,state=online,offset=70,lag=0
master_replid:f2420ca32961e00f8ce020ce9c6ccbcc52bffc23
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:70
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:70
```

真实的主从配置应该在配置文件中配置，这样的话是永久的，使用命令配置的从节点只是暂时的，重启服务器之后就会失效。

> 细节

主机可写可读，从机中不可写只可读。

```bash
127.0.0.1:6380> set key2 val1
(error) READONLY You can't write against a read only replica.
```

如果从机断开连接，重新启动，没有影响（命令行设置的从机会变成主机）

如果主机断开连接，从机依旧连接到主机，但是没有写操作，这个时候，主机重新连接，从机依旧可以直接获取到主机写的信息。

> 复制原理

Slave 启动成功连接到master后会送一个sync同步命令

Master收到命令，启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，在后台进程执行完毕之后，==master将会传送整个数据文件到slave，并完成一次完全同步。==

==全量复制==：slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。

==增量复制==：Master继续将新的所有收集到的修改命令依次传给slave，完成同步。

但是只要是重新连接master，一次完成同步（全量复制）将被自动执行。

> 手动选取新的主节点

如果主节挂了，可以使用`slaveof no one`让一个从机变成主机，再让其他节点收到配置到新的主机上！如果这个时候之前的主节点修复了，就只能重新连接。

#### 哨兵模式

（自动选取主节点）

> 概述

主从切换技术的方法是：当主服务器宕机后，需要手动把一台从服务器切换为主服务器，这就需要人工干预，费时费力，还会造成一段时间内服务不可用。这不是一种推荐的方式，更多的时候优先考虑哨兵模式。Redis从2.8开始正是提供Sentinal(哨兵)架构来解决这个问题。

后台监控主机是否故障，如果主机发生故障根据投票数==自动将从机转换为主机==。

哨兵模式是一种特殊的模式，首先Redis提供了哨兵的命令，哨兵是一个独立的进程，作为进程，他会独立运行。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例。

![img](https://gitee.com/lafer/laferImage/raw/master/img/webp)

这里的哨兵有两个作用

- 通过发送命令，让Redis服务器返回监控其运行状态，包括主服务器和从服务器
- 当哨兵检测到master宕机，会自动将slave切换到master，然后通过发布订阅模式通知其他的从服务器，修改配置文件，让他们切换主机。

然而一个烧饼进程对Redis服务器进行监控，可能会出现问题，因此，可以使用多个哨兵进行监控。各个哨兵之间还会进行监控，这样就形成了多哨兵模式。

![11320039-3f40b17c0412116c](https://gitee.com/lafer/laferImage/raw/master/img/11320039-3f40b17c0412116c.webp)

假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象称为**主管下线**，当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover[故障转移]操作。切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为**客观下线**。

> 测试

目前是一主二从

1、配置哨兵配置文件sentinel.conf

```bash
# sentinel monitor 被监控的名称 host port 1
sentinel monitor myredis 127.0.0.1 6379 1			#这里的1指的是需要至少1个Sentinel认为主Redis挂了才最终会采取下一步行为
```

2、启动哨兵

```bash
[root@VM-0-14-centos bin]# redis-sentinel sentinel.conf 
24940:X 08 Aug 2020 23:19:45.155 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
24940:X 08 Aug 2020 23:19:45.155 # Redis version=5.0.7, bits=64, commit=00000000, modified=0, pid=24940, just started
24940:X 08 Aug 2020 23:19:45.155 # Configuration loaded
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 5.0.7 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                   
 (    '      ,       .-`  | `,    )     Running in sentinel mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 26379
 |    `-._   `._    /     _.-'    |     PID: 24940
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           http://redis.io        
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

24940:X 08 Aug 2020 23:19:45.156 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
24940:X 08 Aug 2020 23:19:45.160 # Sentinel ID is d35606e0191eaea700c92ef57e55e965a712d119
24940:X 08 Aug 2020 23:19:45.160 # +monitor master myredis 127.0.0.1 6379 quorum 1
24940:X 08 Aug 2020 23:19:45.161 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:19:45.164 * +slave slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
# 主机宕机 这个时候会从从机中随机选择一个作为主机（投票算法）
24940:X 08 Aug 2020 23:21:07.361 # +sdown master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.361 # +odown master myredis 127.0.0.1 6379 #quorum 1/1
24940:X 08 Aug 2020 23:21:07.361 # +new-epoch 1
24940:X 08 Aug 2020 23:21:07.361 # +try-failover master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.366 # +vote-for-leader d35606e0191eaea700c92ef57e55e965a712d119 1
24940:X 08 Aug 2020 23:21:07.366 # +elected-leader master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.366 # +failover-state-select-slave master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.429 # +selected-slave slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.429 * +failover-state-send-slaveof-noone slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:07.505 * +failover-state-wait-promotion slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:08.411 # +promoted-slave slave 127.0.0.1:6381 127.0.0.1 6381 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:08.411 # +failover-state-reconf-slaves master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:08.464 * +slave-reconf-sent slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:09.451 * +slave-reconf-inprog slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:09.451 * +slave-reconf-done slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:09.505 # +failover-end master myredis 127.0.0.1 6379
24940:X 08 Aug 2020 23:21:09.505 # +switch-master myredis 127.0.0.1 6379 127.0.0.1 6381
24940:X 08 Aug 2020 23:21:09.505 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ myredis 127.0.0.1 6381
24940:X 08 Aug 2020 23:21:09.505 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ myredis 127.0.0.1 6381
24940:X 08 Aug 2020 23:21:39.531 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ myredis 127.0.0.1 6381
# 此时之前的主机修复后，只能归并到新的主机下，当作从机
24940:X 08 Aug 2020 23:26:03.947 * +convert-to-slave slave 127.0.0.1:6379 127.0.0.1 6379 @ myredis 127.0.0.1 6381
```

> 优点缺点

优点：

1、哨兵集群，基于主从复制模式，所有的主从复制优点，它全有

2、主从可以切换，故障可以转移，系统的可用性更好

3、哨兵模式就是主从复制的升级，手动到自动，更加强壮

缺点：

1、Redis不好在线扩容，集群容量一旦达到上限，在线扩容就十分麻烦

2、实现哨兵模式的配置很麻烦

> 哨兵模式的主要参数配置

```bash
# 端口
port 26379

# 是否后台启动
daemonize yes

# pid文件路径
pidfile /var/run/redis-sentinel.pid

# 日志文件路径
logfile "/var/log/sentinel.log"

# 定义工作目录
dir /tmp

# 定义Redis主的别名, IP, 端口，这里的2指的是需要至少2个Sentinel认为主Redis挂了才最终会采取下一步行为
sentinel monitor mymaster 127.0.0.1 6379 2

# 如果mymaster 30秒内没有响应，则认为其主观失效
sentinel down-after-milliseconds mymaster 30000

# 如果master重新选出来后，其它slave节点能同时并行从新master同步数据的台数有多少个，显然该值越大，所有slave节点完成同步切换的整体速度越快，但如果此时正好有人在访问这些slave，可能造成读取失败，影响面会更广。最保守的设置为1，同一时间，只能有一台干这件事，这样其它slave还能继续服务，但是所有slave全部完成缓存更新同步的进程将变慢。
sentinel parallel-syncs mymaster 1

# 该参数指定一个时间段，在该时间段内没有实现故障转移成功，则会再一次发起故障转移的操作，单位毫秒
sentinel failover-timeout mymaster 180000

# 不允许使用SENTINEL SET设置notification-script和client-reconfig-script。
sentinel deny-scripts-reconfig yes
```

## Redis缓存穿透和雪崩

Redis缓存的作用，极大的提升了应用程序的性能和效率，特别是数据查询方面。但同时，它也带来了一些问题。其中最要害的问题，就是数据一致性问题，从严格意义上讲，这个问题无解，如果对数据的一致性要求很高，那么就不能使用缓存。

另外的一些经典问题就是，缓存穿透、缓存雪崩和缓存机制击穿。

### 缓存穿透

> 概念

缓存穿透的概念很简单，用户想要查询一个数据，发现redis内存数据库没有，也就是缓存没有命中，于是向持久层数据库查询。发现也没有，于是本次查询失败。当用户很多的时候，缓存都没有命中，于是都去请求了持久层数据库。这会给持久层数据库造成很大的压力，这时候就相当于出现了缓存穿透。

> 解决方案

**布隆过滤器**

布隆过滤器是一种数据结构，对所有可能查询的参数以hash形式存储，在控制层先进行校验，不符合则丢弃，从而避免了对底层存储系统的查询压力。

![d788d43f8794a4c2773846a5251e13d3ac6e3910](https://gitee.com/lafer/laferImage/raw/master/img/d788d43f8794a4c2773846a5251e13d3ac6e3910.jpeg)

==运用：==

将数据库中所有的查询条件，放入布隆过滤器中，当一个查询请求过来时，先经过布隆过滤器进行查询，如果判断请求值存在，则继续查；如果判断请求值不存在，则直接丢弃。

==原理：==

对一个key进行k个hash算法取值得到k个值，在比特数组中将这k个值散列后的对应位置设定成1；

查的时候，对key 同样进行k个hash算法取值进行散列后的k个位置如果都为1，那么布隆过滤器判断该key存在。

==注意事项：==

布隆过滤器可能会误判，如果布隆过滤器判断key存在，该key可能实际不存在；如果布隆过滤器判断key不存在，那该key一定不存在。

Redis的bitmap只支持2^32大小，对应到内存也就是512MB，误判率万分之一，可以放下2亿左右的数据，性能高，空间占用率极小，省去了大量无效的数据库连接。

因此可以通过布隆过滤器，将Redis缓存穿透控制在一个可容范围内。

> 布隆过滤器另一个用途——推荐去重

将用户游览过的新闻放入布隆过滤器当中，推荐的新闻，经过布隆过滤器之后，推荐给客户的新闻一定是客户没有看过的。

**缓存空对象**

当存储层不命中后，即使返回的空对象也将其缓存起来，同时会设置一个过期时间，之后再访问这个数据将会从缓存中获取，保护了后端数据源

![203fb80e7bec54e7a4ba970397d293564ec26a4d](https://gitee.com/lafer/laferImage/raw/master/img/203fb80e7bec54e7a4ba970397d293564ec26a4d.jpeg)

这种方式存在两个问题：

1、如果空值能够被缓存起来，这就意味着缓存需要更多的空间存储更多的键，因为这当中可能会有很多的空值的键；

2、即使对空值设置了过期时间，还是会存在缓存层和存储层的数据会有一段时间窗口的不一致，这对于需要保持一致性的业务会有影响。

### 缓存击穿

> 概念

缓存击穿，是指一个key非常热点，在不停的扛着大并发，大并发集中对这一个点进行访问，当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，就像在一个屏障上凿开了一个洞。

当某个key在过期的瞬间，有大量的请求并发访问，这类数据一般是热点数据，由于缓存过期，会同时访问数据库来查询最新数据，并写回缓存，会导致数据库瞬间压力过大。

> 解决方案

**设置热点数据永不过期**

从缓存层面来看，没有设置过期时间，所以不会出现热点key过期后产生的问题。

**加互斥锁**

分布式锁：使用分布式锁，保证对于每一个key同时只有一个线程去查询后端服务，其他线程没有获得分布式锁的权限，因此只需要等待即可。这种方式将高并发的压力转移到分布式锁，因此对分布式锁的考验很大。

### 缓存雪崩

> 概念

缓存雪崩是指，缓存层出现了错误，不能正常工作了。于是所有的请求都会达到存储层，存储层的调用量会暴增，造成存储层也会挂掉的情况。

![9c16fdfaaf51f3dec6606ae3a504f81938297953](https://gitee.com/lafer/laferImage/raw/master/img/9c16fdfaaf51f3dec6606ae3a504f81938297953.jpeg)

> 解决方案

**redis高可用（集群）**

这个思想的含义是，既然redis有可能挂掉，那我多增设几台redis，这样一台挂掉之后其他的还可以继续工作，其实就是搭建的集群。

**限流降级**

这个解决方案的思想是，在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许一个线程查询数据和写缓存，其他线程等待。

**数据加热**

数据加热的含义就是在正式部署之前，我先把可能的数据先预先访问一遍，这样部分可能大量访问的数据就会加载到缓存中。在即将发生大并发访问前手动触发加载缓存不同的key，设置不同的过期时间，让缓存失效的时间点尽量均匀。