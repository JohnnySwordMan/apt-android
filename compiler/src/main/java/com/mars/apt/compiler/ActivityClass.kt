package com.mars.apt.compiler

import com.bennyhuo.aptutils.types.packageName
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * Created by JohnnySwordMan on 2021/12/31
 */
class ActivityClass(private val typeElement: TypeElement) {

    val simpleName = typeElement.simpleName.toString()

    val packageName = typeElement.packageName()

    // 类的属性
    val fields = mutableListOf<Field>()

    // 是否是抽象类
    val isAbstract = typeElement.modifiers.contains(Modifier.ABSTRACT)

    val builder = ActivityClassBuilder(this)

}

/**
 * 生成Activity
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

        JavaFile.builder(activityClass.packageName, typeBuilder.build()).build().writeTo(filer)
    }
}

// 生成常量
class ConstantBuilder(private val activityClass: ActivityClass) {

    /**
     * 注意：常量，初始化
     */
    fun build(typeBuilder: TypeSpec.Builder) {
        activityClass.fields.forEach { field ->
            val fieldSpec = FieldSpec.builder(
                String::class.java,
                field.prefix + field.name.toUpperCase(),
                Modifier.PUBLIC,
                Modifier.STATIC,
                Modifier.FINAL
            ).initializer("\$S", field.name).build()  // 必须是大写S
            typeBuilder.addField(fieldSpec).build()
        }

    }
}
