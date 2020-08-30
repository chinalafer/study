### 什么是JVM

jvm（Java Virtual Machine）通过在实际计算机模仿各种计算机功能来实现的，Java能够被称为“一次编译，到处运行”的原因就是Java屏蔽了很多的操作系统平台相关信息，使得Java只需要生成在JVM虚拟机运行的目标代码也就是所说的字节码，就可以在多种平台运行。

![1c950a7b02087bf4600fcdb16c143b2a13dfcfd1](https://gitee.com/lafer/laferImage/raw/master/img/1c950a7b02087bf4600fcdb16c143b2a13dfcfd1.jpeg)

组成部分包括堆、方法区、栈、本地方法栈、程序计算器等部分组成，其中堆和方法区是线程共享区，而栈和程序计算器、本地方法栈区是线程独占区。

![bf096b63f6246b6042db690ee7f81a4c500fa2f7](https://gitee.com/lafer/laferImage/raw/master/img/bf096b63f6246b6042db690ee7f81a4c500fa2f7.png)

### 类加载器

**作用：**加载Class文件

```java
public class ClassLoadTest {
    public static void main(String[] args) {
        ClassLoadTest classLoadTest1 = new ClassLoadTest();
        ClassLoadTest classLoadTest2 = new ClassLoadTest();
        ClassLoadTest classLoadTest3 = new ClassLoadTest();
        System.out.println(classLoadTest1.hashCode());
        System.out.println(classLoadTest2.hashCode());
        System.out.println(classLoadTest3.hashCode());
        // class com.lafer.studyjvm.ClassLoadTest
        System.out.println(classLoadTest1.getClass());
        // class com.lafer.studyjvm.ClassLoadTest
        System.out.println(classLoadTest2.getClass());
        // class com.lafer.studyjvm.ClassLoadTest
        System.out.println(classLoadTest3.getClass());
        // sun.misc.Launcher$AppClassLoader@14dad5dc
        System.out.println(classLoadTest1.getClass().getClassLoader());
        // sun.misc.Launcher$ExtClassLoader@7f31245a
        System.out.println(classLoadTest1.getClass().getClassLoader().getParent());
        // null
        System.out.println(classLoadTest1.getClass().getClassLoader().getParent().getParent());
    }
}
```

> 双亲委派模型

![微信图片_20200830223108](https://gitee.com/lafer/laferImage/raw/master/img/微信图片_20200830223108.png)

1、启动（Bootstrap）类加载器，负责将 <Java_Runtime_Home>/lib 下面的类库加载到内存中。

2、扩展（Extension）类加载器ExtClassLoader：负责将 < Java_Runtime_Home >/lib/ext 或者由系统变量 java.ext.dir 指定位置中的类库加载到内存中

3、应用（Application）类加载器AppClassLoader：负责将系统类路径（CLASSPATH）中指定的类库加载到内存中。

注意：

1、jvm加载的顺序：BoopStrap ClassLoder–>ExtClassLoader–>AppClassLoder（意味着自己定义的java.lang.String类不能被加载，会在BoopStrap ClassLoder加载阶段被加载）

2、类加载器之间的关系：AppClassLoader的父加载器为ExtClassLoader，ExtClassLoader的父加载器为null，BoopStrap ClassLoader为顶级加载器。

> 类加载的过程

从类的生命周期而言，一个类包括如下阶段：

![微信图片_20200830223618](https://gitee.com/lafer/laferImage/raw/master/img/微信图片_20200830223618.png)

加载、验证、准备、初始化和卸载这5个阶段的顺序是确定的，类的加载过程必须按照这种顺序进行，而解析阶段则不一定，它在某些情况下可能在初始化阶段后在开始，因为java支持运行时绑定。

**类加载时机**

 加载（loading）阶段，java虚拟机规范中没有进行约束，但初始化阶段，java虚拟机严格规定了有且只有如下5种情况必须立即进行初始化（初始化前，必须经过加载、验证、准备阶段）：

1、使用new实例化对象时，读取和设置类的静态变量、静态非字面值常量（静态字面值常量除外）时，调用静态方法时。

2、对内进行反射调用时。

3、当初始化一个类时，如果父类没有进行初始化，需要先初始化父类。

4、启动程序所使用的main方法所在类

5、当使用1.7的动态语音支持时。

**类加载的过程**

1.加载

在加载阶段，类加载器需要完成以下3件事情：
1、通过一个类的全限定名来获取定义此类的二进制字节流
2、将这两个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3、在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据访问入口

==简单来说就是通过类加载器在指定路径下面，从class字节码文件加载为class对象==

2.验证

验证是连接阶段的第一步，这一个阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。
1、文件格式验证：第一阶段要验证字节流是否符合Class文件的规范，并且能被当前虚拟机处理。
2、元数据验证：第二阶段是对字节码描述的信息进行语义分析，以保证其描述的信息符合Java语言规范的要求。
3、字节码验证：第三阶段是整个验证过程中最为复杂的一个阶段，主要目的是通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。在第二阶段对元数据信息中的数据类型做完校验后，这个阶段将对类的方法体进行校验分析，保证被校验类的方法再运行时不会做出危害虚拟机的安全事件。

3.准备

准备过程是正式的为类变量分配内存并设置初始值的阶段在，这些变量所使用的内存在方法区中进行分配。这个阶段中有两个容易产生混淆的概念要强调，首先，这个时候进行分配的仅包括类变量（被static修饰），而不包括实例变量，实例变量将会在对象实例化时随着对象一起分配在Java堆中。其次，这里所说的初始值“通常情况”下是数据类型为零。

4.解析

解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。

1、类或接口的解析
2、字段解析
3、类方法解析
4、接口方法解析

整个连接过程：==对类中的静态成员变量进行分配内存空间，如果有此基类，对此基类进行加载==

5.初始化

类初始化阶段是类加载器过程的最后一步，前面的类加载过程中，除了在加载阶段用户应用程序可以通过自定义类加载器参与之外，其余动作完全由虚拟机主导和控制。到了初始化阶段，才真正开始执行类中定义的Java程序代码。
在准备阶段，变量已经赋值过一次系统要求的初始值，而在初始化阶段，则根据程序员通过程序制定的主观计划去初始化变量和其他资源，或许可以从另外一个角度去表达：初始化阶段是执行类构造器（）方法的过程。

==对静态成员变量进行初始化==



