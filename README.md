# Todo

Jetpack Compose 学习

性能：

* 很多场景已经超越原生，例如动画/例如从布局渲染的计算优化：禁止二次测量（固有尺寸测量（最大最小尺寸），防止测量次数指数式增长问题，可以覆盖多种场景）等，限制就是不灵活（不能根据不同情况重新测量），但是可以通过自定义布局来做
* 但是某些场景例如列表目前的性能不如原生的RecyclerView

未来：

* React Native和Flutter都是跨平台框架，同一套代码运行在多个平台，节省时间跟金钱成本、还有KPI
* 而Compose属于多平台，与跨平台框架目的不一样，推广Kotlin，让更多的人熟悉这种开发框架和语言，便于以后转型
* Compose基于原生，是更上层的东西，性能与原生媲美，与原生可以完全结合，未来可能成为Android开发的主流
* Compose当下不会那么快火起来

渲染原理：

* Gap Buffer算法，具体实现在SlotTable

State原理：

* StateObject与Snapshot