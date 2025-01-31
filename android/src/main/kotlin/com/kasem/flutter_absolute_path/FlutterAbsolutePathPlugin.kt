package com.kasem.flutter_absolute_path

import android.content.Context
import android.net.Uri

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


class FlutterAbsolutePathPlugin() : MethodCallHandler, FlutterPlugin {

    private var context: Context? = null
    private var flutterChannel: MethodChannel? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_absolute_path")
            channel.setMethodCallHandler(FlutterAbsolutePathPlugin(registrar.context()))
        }
    }

    private fun onAttachedToEngine(applicationContext: Context?, messenger: BinaryMessenger?) {
        synchronized(initializationLock) {
            if (flutterChannel != null) {
                return
            }
            context = applicationContext
            flutterChannel =
                MethodChannel(
                    messenger,
                    "com.kasem.flutter_absolute_path"
                )
            flutterChannel.setMethodCallHandler(this)
        }
    }
    
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when {
            call.method == "getAbsolutePath" -> {
                val uriString = call.argument<Any>("uri") as String
                val uri = Uri.parse(uriString)

                result.success(FileDirectory.getAbsolutePath(this.context, uri))
            }
            else -> result.notImplemented()
        }
    }

    @Override fun onAttachedToEngine(binding: FlutterPluginBinding?) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    @Override fun onDetachedFromEngine(binding: FlutterPluginBinding?) {
        context = null;
        if (flutterChannel != null) {
            flutterChannel.setMethodCallHandler(null);
            flutterChannel = null;
        }
    }

}
