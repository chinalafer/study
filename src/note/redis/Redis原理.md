### Redis原理

#### Expire原理

##### 过期键值对的结构

![7361383-70062a36d419fc17](https://gitee.com/lafer/laferImage/raw/master/img/7361383-70062a36d419fc17.webp)

redis 将所有设置了过期时间的键值对都放在了expires的一个字典中，key指向具体的 dict，value是过期时间（longlong）

##### 过期键的删除策略

1. **立即删除**。在设置键的过期时间时，创建一个回调事件，当过期时间达到时，由时间处理器自动执行键的删除操作。（**立即删除对cpu是最不友好的**）
2. **惰性删除**。键过期了就过期了，不管。每次从dict字典中按key取值时，先检查此key是否已经过期，如果过期了就删除它，并返回nil，如果没过期，就返回键值。（**浪费内存这对于性能非常依赖于内存大小的redis来说，是比较致命的**）
3. **定时删除**。每隔一段时间，对expires字典进行检查，删除里面的过期键。

第二种为被动删除，第一种和第三种为主动删除，且第一种实时性更高。下面对这三种删除策略进行具体分析。（**折中之法，限制删除操作执行的时长和频率，来减少删除操作对cpu的影响，定时删除也有效的减少了因惰性删除带来的内存浪费**）

##### redis使用的策略

redis使用的过期键值删除策略是：==惰性删除加上定期删除==，两者配合使用。