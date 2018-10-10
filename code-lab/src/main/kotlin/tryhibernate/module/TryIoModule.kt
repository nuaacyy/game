package tryhibernate.module

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tryhibernate.service.TryIoService

@Component
class TryIoModule {

    @Autowired
    private lateinit var tryIoService: TryIoService

    fun doSth() {
        tryIoService.showSth()
    }

}