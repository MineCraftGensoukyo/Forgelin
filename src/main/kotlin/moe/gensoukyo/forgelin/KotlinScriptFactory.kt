package moe.gensoukyo.forgelin

import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine
import kotlin.script.experimental.jvm.util.scriptCompilationClasspathFromContext

const val JAR_EXT = ".jar"
class KotlinScriptFactory : KotlinJsr223JvmScriptEngineFactoryBase() {

    override fun getScriptEngine(): ScriptEngine {
        var jarName = KotlinScriptFactory::class.java.protectionDomain.codeSource.location.file
        jarName = jarName.substring(0, jarName.lastIndexOf(JAR_EXT)) + JAR_EXT
        jarName = jarName.substring(jarName.lastIndexOf('/') + 1)
        return KotlinJsr223JvmLocalScriptEngine(
                this,
                scriptCompilationClasspathFromContext(jarName, wholeClasspath = true),
                KotlinStandardJsr223ScriptTemplate::class.qualifiedName!!,
                { ctx, types ->
                    ScriptArgsWithTypes(arrayOf(ctx.getBindings(ScriptContext.ENGINE_SCOPE)), types ?: emptyArray())
                },
                arrayOf(Bindings::class)
        )
    }
}
