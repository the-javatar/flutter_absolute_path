import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_absolute_path_method_channel.dart';

abstract class FlutterAbsolutePathPlatform extends PlatformInterface {
  /// Constructs a FlutterAbsolutePathPlatform.
  FlutterAbsolutePathPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterAbsolutePathPlatform _instance =
      MethodChannelFlutterAbsolutePath();

  /// The default instance of [FlutterAbsolutePathPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterAbsolutePath].
  static FlutterAbsolutePathPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterAbsolutePathPlatform] when
  /// they register themselves.
  static set instance(FlutterAbsolutePathPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
