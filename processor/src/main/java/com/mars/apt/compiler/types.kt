package com.mars.apt.compiler

import com.mars.apt.compiler.utils.AptManager
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import javax.lang.model.type.TypeMirror

// 因为java lib中是没有android相关的库的，因此这里需要通过className，拿到对应的TypeName
val CONTEXT = ClassType("android.content.Context")
val INTENT = ClassType("android.content.Intent")
val ACTIVITY_BUILDER = ClassType("com.mars.apt.runtime.ActivityBuilder")
val ACTIVITY = ClassType("android.app.Activity")
val BUNDLE = ClassType("android.os.Bundle")

/**
 * className ---> TypeElement ---> TypeMirror
 */
fun TypeMirror.isSubType(className: String): Boolean {
    val typeElement = AptManager.elements.getTypeElement(className)
    val typeMirror = typeElement.asType()
    return AptManager.types.isSubtype(this, typeMirror)
}

fun typeNameForString(): TypeName {
    return ClassName.get("java.lang", "String")
}
