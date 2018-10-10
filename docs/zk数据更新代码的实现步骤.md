## 更新代码
* 在common包的zkdomain下创建当前Zk数据版本号对应的子包，比如当前是5，那么创建v5。复制当前更新依赖的zkdomain到目标子包下（必须是复制，不可以移动！）。并修改目标子包下的类的dataVersion字段为对应版本，比如5。
* 复制UpdateZkDataToLatest为UpdateZkDataTo<当前Zk数据版本号>，并把内部依赖的ZkDomain修改为当前版本号对应的包下面的类。
* 递增当前Zk数据版本号，也就是加1，当前是5，那么递增后就是6。
* 修改UpdateZkDataToLatest，做你想做的事情。
* 在TryUpdate类中增加复制出来的UpdateZkDataTo，比如上面的UpdateZkDataTo5。