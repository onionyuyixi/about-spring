1 environmemt --> profiles properties
2 Xxxx ----> ConfigurableXxxx 自定义类
3 Xxxxx-----> XxxxxResolver 一般用来描述资源处理问题
4 Xxxxxx-----> AbstractXxxxx 一般采取模板模式 子类必须实现抽象方法  AbstractXXX一般是由ConfigurableXXX发展而来
5 X -----> DefalutX
6 Xxx--->XxxProcessor
7 Xx---->SimpleXx
8 Xxxxxxx--->StandardXxxxxx
9 Xxxx---->XxxxAware

InWord Abstract基本融合了以上的所有内容  距离正式的内容  只差 还没有实现的抽象方法