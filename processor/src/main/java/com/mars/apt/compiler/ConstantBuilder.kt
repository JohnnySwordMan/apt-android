package com.mars.apt.compiler

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier

/**
 * Created by JohnnySwordMan on 2/16/22
 */
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
            typeBuilder.addField(fieldSpec)
        }

    }

}