package tryhibernate.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tryhibernate.dao.TryIoDao
import tryhibernate.domain.TryIo
import tryhibernate.service.TryIoService

@Service("tryIoService")
open class TryIoServiceImpl : TryIoService {

    @Autowired
    private lateinit var tryIoDao: TryIoDao

    @Transactional
    override fun showSth() {
        println("save")

        val tryIo = TryIo()
        tryIo.name = "aaa"
        tryIoDao.save(tryIo)
    }
}