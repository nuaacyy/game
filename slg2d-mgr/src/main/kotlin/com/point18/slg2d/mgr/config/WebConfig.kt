package com.point18.slg2d.mgr.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@ComponentScan(basePackages = ["com.point18.slg2d.mgr.controller"])
open class WebConfig : WebMvcConfigurerAdapter() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry
            ?.addResourceHandler("/static/**")
            ?.addResourceLocations("classpath:/react/static/static/")
            ?.setCachePeriod(0)
    }

    override fun addViewControllers(registry: ViewControllerRegistry?) {
        registry!!.addViewController("/").setViewName("redirect:/static/index.html")
//        registry!!.addViewController("/static/").setViewName("redirect:/static/index.html")
    }

}