package com.mars.apt.compiler

import com.bennyhuo.aptutils.types.asJavaTypeName
import com.sun.tools.javac.code.Symbol

/**
 * Created by JohnnySwordMan on 2021/12/31
 */
class Field(private val symbol: Symbol.VarSymbol) {

    val prefix = "REQUIRED_"
    // 属性名称
    val name = symbol.qualifiedName.toString()
    // 是否是private
    val isPrivate = symbol.isPrivate
    // 是否是基本类型
    val isPrimitive = symbol.type.isPrimitive

    fun asJavaTypeName() = symbol.type.asJavaTypeName()
}