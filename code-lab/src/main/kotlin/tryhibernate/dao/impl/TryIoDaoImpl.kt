package tryhibernate.dao.impl

import org.springframework.stereotype.Repository
import tryhibernate.dao.TryIoDao
import tryhibernate.domain.TryIo

@Repository("tryIoDao")
class TryIoDaoImpl : GenericDaoImpl<TryIo, Long>(TryIo::class.java), TryIoDao {

}