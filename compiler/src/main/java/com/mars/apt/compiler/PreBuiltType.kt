package com.mars.apt.compiler

import com.bennyhuo.aptutils.types.ClassType

/**
 * Created by JohnnySwordMan on 2021/12/31
 * compiler模块是Java模块，并不识别Android模块，因此它根本不认识Context是啥
 */
val CONTEXT = ClassType("android.content.Context")