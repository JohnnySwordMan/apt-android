package com.mars.apt.compiler

import com.bennyhuo.aptutils.types.packageName
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * Created by JohnnySwordMan on 2021/12/31
 *
 * typeElement就是Builder注解标识的类名
 */
class ActivityClass(val typeElement: TypeElement) {

    val simpleName = typeElement.simpleName.toString()

    val packageName = typeElement.packageName()

    // 类的属性
    val fields = mutableListOf<Field>()

    // 是否是抽象类
    val isAbstract = typeElement.modifiers.contains(Modifier.ABSTRACT)

    val builder = ActivityClassBuilder(this)
}