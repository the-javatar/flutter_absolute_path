package com.kasem.flutter_absolute_path

import android.content.Context
import android.net.Uri
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


class FlutterAbsolutePathPlugin() : MethodCallHandler, FlutterPlugin {

    private var context: Context? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_absolute_path")
            channel.setMethodCallHandler(FlutterAbsolutePathPlugin(registrar.context()))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "getAbsolutePath" -> {
                val uriString = call.argument<Any>("uri") as String
                val uri = Uri.parse(uriString)

                result.success(FileDirectory.getAbsolutePath(this.context, uri))
            }
            else -> result.notImplemented()
        }
    }

    @Override fun onAttachedToEngine(@NonNull binding: FlutterPluginBinding?) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    @Override fun onDetachedFromEngine(@NonNull binding: FlutterPluginBinding?) {
        context = null;
        if (flutterChannel != null) {
            flutterChannel.setMethodCallHandler(null);
            flutterChannel = null;
        }
    }

}
