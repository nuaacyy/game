package tryhibernate.dao.impl

import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateCallback
import org.springframework.orm.hibernate5.support.HibernateDaoSupport
import tryhibernate.dao.GenericDao
import tryhibernate.persistence.Domain4Lab
import java.io.Serializable

open class GenericDaoImpl<T : Domain4Lab<*>, ID : Serializable>(val entityClass: Class<T>) : HibernateDaoSupport(), GenericDao<T, ID> {

    @Autowired
    fun superSessionFactory(sessionFactory: SessionFactory) {
        println("exist? " + sessionFactory)
        super.setSessionFactory(sessionFactory)
    }

    override fun saveOrUpdate(instance: T) {
        hibernateTemplate.saveOrUpdate(instance)
    }

    override fun delete(persistentInstance: T) {
        hibernateTemplate.delete(persistentInstance)
    }

    override fun findAll(): List<T> {
        return findAll(entityClass)
    }

    override fun <V : Domain4Lab<*>> findAll(clazz: Class<V>): List<V> {
        return hibernateTemplate.loadAll(clazz) as List<V>
    }

    override fun findById(id: ID): T {
        return findById(entityClass, id)
    }

    override fun <V : Domain4Lab<*>> findById(clazz: Class<V>, id: ID): V {
        return hibernateTemplate.get(clazz, id) as V
    }

    override fun deleteById(id: ID) {
        deleteById(entityClass, id)
    }

    override fun <V : Domain4Lab<*>> deleteById(clazz: Class<V>, id: ID) {
        hibernateTemplate.execute(object : HibernateCallback<Int> {
            @Throws(HibernateException::class)
            override fun doInHibernate(session: Session): Int? {
                val entityClassName = clazz.simpleName
                val hql = "DELETE FROM $entityClassName WHERE id = :id"

                val query = session.createQuery(hql)
                query.setLong("id", id as Long)

                return query.executeUpdate()
            }
        })
    }

    override fun save(transientInstance: Domain4Lab<*>): ID {
        return hibernateTemplate.save(transientInstance) as ID
    }

    override fun update(transientInstance: T) {
        hibernateTemplate.update(transientInstance)
    }

    override fun flush() {
        hibernateTemplate.flush()
    }

    override fun clear() {
        hibernateTemplate.clear()
    }

}