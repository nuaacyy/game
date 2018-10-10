package com.point18.slg2d.world.area4data

import xyz.ariane.util.IEntity
import xyz.ariane.util.memodb.EntityWrapper

// 插入
fun <E: IEntity> insert(areaCache: AreaCache, dataObj: EntityWrapper<E>) {
    areaCache.db.wdb.save(dataObj)
}

// 删除
fun <E: IEntity> delete(areaCache: AreaCache, dataObj: EntityWrapper<E>) {
    areaCache.db.wdb.delete(dataObj)
}
