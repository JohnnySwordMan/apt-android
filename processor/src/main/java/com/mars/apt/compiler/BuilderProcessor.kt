package com.mars.apt.compiler

import com.mars.apt.annotation.Builder
import com.mars.apt.annotation.Inject
import com.mars.apt.compiler.utils.AptManager
import com.sun.tools.javac.code.Symbol
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

/**
 * Created by JohnnySwordMan on 2021/12/29
 */
class BuilderProcessor : AbstractProcessor() {

    private val annotations = setOf(Builder::class.java)

    /**
     * 当前正使用的Java版本
     */
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    /**
     * 要处理的注解类型的名称
     */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return annotations.mapTo(HashSet(), Class<*>::getCanonicalName)
    }

    /**
     * 初始化处理器
     * ProcessingEnvironment是注解处理工具集合，包含很多工具类
     * 例如：Filer可以用来编写新文件、Message可以用来打印错误信息、Elements可以处理Element的工具类
     */
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        AptManager.init(processingEnv)
        com.bennyhuo.aptutils.AptContext.init(processingEnv)
    }


    override fun process(
        annotations: MutableSet<out TypeElement>,
        env: RoundEnvironment
    ): Boolean {
        val map = HashMap<Element, ActivityClass>()
        env.getElementsAnnotatedWith(Builder::class.java)
            .filter { it.kind.isClass }  // Builder只能标注类
            .forEach { element ->
                // 日志打印：BuilderProcessor element = MainActivity
                println("BuilderProcessor type element = ${element.simpleName}")
                val type = element.asType()
                when {
                    type.isSubType("android.app.Activity") -> {
                        map[element] = ActivityClass(element as TypeElement)
                    }
                }

            }
        env.getElementsAnnotatedWith(Inject::class.java)
            .filter { it.kind.isField }
            .forEach { element ->
                // 上一层element
                println("BuilderProcessor field element = $element")
                // 从这里可以看出Builder和Inject是绑定的
                map[element.enclosingElement]?.fields?.add(Field(element as Symbol.VarSymbol))
            }
        // 写到文件中
        map.values.forEach {
            it.builder.build(AptManager.filer)
        }
        return true
    }
}