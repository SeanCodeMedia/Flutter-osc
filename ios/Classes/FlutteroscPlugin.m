#import "FlutteroscPlugin.h"
#if __has_include(<flutterosc/flutterosc-Swift.h>)
#import <flutterosc/flutterosc-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutterosc-Swift.h"
#endif

@implementation FlutteroscPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutteroscPlugin registerWithRegistrar:registrar];
}
@end
