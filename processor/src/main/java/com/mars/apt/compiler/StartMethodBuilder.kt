package com.mars.apt.compiler

import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier

/**
 * Created by JohnnySwordMan on 2/16/22
 *
 */
class StartMethodBuilder(private val activityClass: ActivityClass) {

    fun build(typeBuilder: TypeSpec.Builder) {
        val methodBuilderTemp = MethodSpec.methodBuilder("start")
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.VOID)
            .addParameter(CONTEXT.java, "context")


        typeBuilder
            .addMethod(methodBuilderTemp.build())
            .addMethod(createStartMethod().build())
//            .addMethod(createInjectMethod())
    }

//    private fun createInjectMethod(): MethodSpec {
//        val methodBuilder = MethodSpec.methodBuilder("inject")
//            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//            .returns(TypeName.VOID)
//            .addParameter(CONTEXT.java, "context")
//        return methodBuilder.build()
//    }

    /**
     * public static void start(Context context, String username, String password) {
     *      Intent intent = new Intent(context, LoginActivity.class);
     *      intent.putExtra("username", username);
     *      intent.putExtra("password", password);
     *      ActivityBuilder.INSTANCE.startActivity(context, intent);
     * }
     */
    private fun createStartMethod(): MethodSpec.Builder {
        val methodBuilder = MethodSpec.methodBuilder("start")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(TypeName.VOID)
            .addParameter(CONTEXT.java, "context")

        // 这里context可以固定
        methodBuilder.addStatement(
            "\$T intent = new \$T(context, \$T.class)",
            INTENT.java,
            INTENT.java,
            activityClass.typeElement
        )

        activityClass.fields.forEach {
            // StartMethodBuilder---field name= username
            println("StartMethodBuilder---field name= ${it.name}")
            // TODO 这里不应该固定是String
            methodBuilder.addParameter(typeNameForString(), it.name)
            // 这里使用L
            methodBuilder.addStatement("intent.putExtra(\$S, \$L)", it.name, it.name)
        }
        methodBuilder.addStatement(
            "\$T.INSTANCE.startActivity(context, intent)",
            ACTIVITY_BUILDER.java
        )
        return methodBuilder
    }

}