package com.mars.apt.compiler

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

/**
 * Created by JohnnySwordMan on 2/16/22
 */
class ActivityClassBuilder(private val activityClass: ActivityClass) {

    companion object {
        const val POSIX = "Builder"
    }

    fun build(filer: Filer) {
        if (activityClass.isAbstract) {
            return
        }
        // 生成Java类
        val typeBuilder = TypeSpec.classBuilder(activityClass.simpleName + POSIX)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)

        // 在该Java类中创建常量
        ConstantBuilder(activityClass).build(typeBuilder)
        StartMethodBuilder(activityClass).build(typeBuilder)

        JavaFile.builder(activityClass.packageName, typeBuilder.build()).build().writeTo(filer)
    }
}