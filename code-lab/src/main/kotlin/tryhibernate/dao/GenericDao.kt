package tryhibernate.dao

import java.io.Serializable
import tryhibernate.persistence.Domain4Lab

interface GenericDao<T : Domain4Lab<*>, ID : Serializable> {
    /**
     * 保存实体对象
     *
     * @param transientInstance 实体对象
     * @return 主键
     */
    abstract fun save(transientInstance: Domain4Lab<*>): ID

    /**
     * 利用ID单查实体
     *
     * @param entityClass
     * @param id 实体对象的ID
     * @return 实体
     */
    abstract fun findById(id: ID): T

    /**
     * 指定类型利用ID单查实体
     *
     * @param entityClass
     * @param id 实体对象的ID
     * @return 实体
     */
    abstract fun <V : Domain4Lab<*>> findById(clazz: Class<V>, id: ID): V

    /**
     * 找到所有对象
     *
     * @param entityClass
     * @return 实体集合。
     */
    abstract fun findAll(): List<T>

    /**
     * 指定类型获取所有对象
     *
     * @param clazz
     * @return
     */
    abstract fun <V : Domain4Lab<*>> findAll(clazz: Class<V>): List<V>

    /**
     * 根据ID删除特定对象。
     *
     * @param id
     */
    abstract fun deleteById(id: ID)

    /**
     * 根据ID删除特定对象。
     *
     * @param clazz
     * @param id
     */
    abstract fun <V : Domain4Lab<*>> deleteById(clazz: Class<V>, id: ID)

    /**
     * 保存实体 包括添加和修改
     *
     * @param instance 实体对象
     */
    abstract fun saveOrUpdate(instance: T)

    /**
     * 更新实体 可用于添加、修改、删除操作
     *
     * @param transientInstance 实体对象
     */
    abstract fun update(transientInstance: T)

    /**
     * 删除实体
     *
     * @param persistentInstance 实体对象
     */
    abstract fun delete(persistentInstance: T)

    /**
     * 立即刷新数据库
     */
    abstract fun flush()

    /**
     * 清空一级缓存
     */
    abstract fun clear()
}