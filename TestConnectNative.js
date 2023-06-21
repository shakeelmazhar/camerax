// If TestConnectNative is an package from npm, you can think this is index.js file
import {NativeModules, Platform} from 'react-native';

const testConnectNative = NativeModules.KotlinNativeModule;

const TestConnectNative = {
  sendMessage: msg => {
    testConnectNative.sendMessageToNative(msg);
  },

  sendCallback: callback => {
    testConnectNative.sendCallbackToNative(callback);
  },

  exitRN: tag => {
    if (Platform.OS === 'ios') {
      testConnectNative.dismissPresentedViewController(tag);
    } else {
      testConnectNative.finishActivity();
    }
  },

  goToSecondActivity: async(callback) => {
    await testConnectNative.goToSecondActivity(callback);
  },
};

export default TestConnectNative;