import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_absolute_path_platform_interface.dart';

/// An implementation of [FlutterAbsolutePathPlatform] that uses method channels.
class MethodChannelFlutterAbsolutePath extends FlutterAbsolutePathPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_absolute_path');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
