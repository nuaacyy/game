package tryhibernate

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import tryhibernate.config.AppConfig
import tryhibernate.module.TryIoModule

fun main(args: Array<String>) {
    val bs = Bootstrap()
    bs.boot()
}

class Bootstrap {

    fun boot() {
        val appCtx = AnnotationConfigApplicationContext(AppConfig::class.java)
//		appCtx.register()

        val m = appCtx.getBean("tryIoModule") as TryIoModule
        m.doSth()
    }
}