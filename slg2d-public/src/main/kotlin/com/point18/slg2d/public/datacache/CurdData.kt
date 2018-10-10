package com.point18.slg2d.public.datacache

import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.PublicMenagerDatabase
import xyz.ariane.util.IEntity
import xyz.ariane.util.memodb.EntityWrapper

// 插入
fun <E : IEntity> insert(publicCache: PublicCache, dataObj: EntityWrapper<E>) {
    publicCache.db.wdb.save(dataObj)
}

// 删除
fun <E : IEntity> delete(publicCache: PublicCache, dataObj: EntityWrapper<E>) {
    publicCache.db.wdb.delete(dataObj)
}

// 插入
fun <E : IEntity> insert(db: PublicMenagerDatabase, dataObj: EntityWrapper<E>) {
    db.wdb.save(dataObj)
}

// 删除
fun <E : IEntity> delete(db: PublicMenagerDatabase, dataObj: EntityWrapper<E>) {
    db.wdb.delete(dataObj)
}
