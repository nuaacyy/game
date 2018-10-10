## Tips
* 自己能收到自己的Joined和Up事件！
* 第一个节点是没有Joined事件的，直接Up了。
* 订阅member声明周期事件是通过`Cluster.get(getContext().system()).subscribe()`方法。解除订阅是`unsubscribe()`方法。
* 订阅时，`ClusterEvent.initialStateAsEvents()`的作用是将节点状态直接转换为Event。这样就不会有CurrentClusterState消息。能更统一的处理。
