import React, {useEffect} from 'react';
import {
  NativeEventEmitter,
  DeviceEventEmitter,
  NativeModules,
  Text,
  TouchableOpacity,
  requireNativeComponent,
  Image,
} from 'react-native';
import TestConnectNative from './TestConnectNative';

const CustomNestedView = requireNativeComponent('CustomNestedView');

const {KotlinNativeModule} = NativeModules;
const eventEmitter = new NativeEventEmitter();

const NativeCustomModules = () => {
  // const getNativeModules = async () => {
  //   const data = {
  //     name: 'John',
  //     age: 30,
  //   };

  //   // Call the Native Module method and handle the result
  //   await KotlinNativeModule.doSomething(data)
  //     .then(result => {
  //       console.log(result); // Result received from the Native Module
  //     })
  //     .catch(error => {
  //       console.error(error); // Error received from the Native Module
  //     });
  // };

  // useEffect(() => {
  //   DeviceEventEmitter.addListener('MyEvent', eventData => {
  //     console.log('Received event:', eventData);
  //   });

  //   const buttonPressSubscription = eventEmitter.addListener(
  //     'buttonPressEvent',
  //     event => {
  //       console.log(event); // Access the props returned from Kotlin
  //     },
  //   );

  //   return () => {
  //     buttonPressSubscription.remove();
  //   };
  // }, []);

  const handleButtonPress = async () => {
    // KotlinNativeModule.goToSecondActivity();
    KotlinNativeModule.handleButtonPress();

    // Send a message to native code

    // TestConnectNative.sendMessage("Hi Native side");
    // // Send a callback to native code and receive a message from it
    // TestConnectNative.sendCallback(nativeMessage => {
    //   console.log(
    //     `This log is from js code callback with native message: "${nativeMessage}"`,
    //   );
    // });
    // // Exit RN to go back to native
    // TestConnectNative.exitRN("android");

    // TestConnectNative.goToSecondActivity()
    // await TestConnectNative.goToSecondActivity(nativeMessage => {
    //   console.log(
    //     `This log is from js code callback with native message: "${nativeMessage}"`,
    //   );
    // });
  };

  // useEffect(() => {
  //   const eventEmitter = new NativeEventEmitter(NativeModules.KotlinNativeModule);
  //   const eventListener = eventEmitter.addListener('EventReminder', event => {
  //     console.log(event.eventProperty); // "someValue"
  //   });

  //   return () => {
  //     eventListener.remove(); // Removes the listener when the component unmounts
  //   };
  // }, []);

  return (
    <>
      <CustomNestedView
        text="Click me"
        imageUrl="https://blog.hubspot.com/hs-fs/hubfs/parts-url-hero.jpg?width=595&height=400&name=parts-url-hero.jpg"
        style={{
          width: 200,
          height: 100,
          backgroundColor: 'green',
          marginHorizontal: 30,
        }}
      />
    </>
  );
};
export default NativeCustomModules;

// const CustomView = (onPress) => {

//   return (
// <View
//   style={{width: 100, height: 40, backgroundColor: 'red'}}
//   onPress={onPress}>
//   <Text>Click me</Text>
//   <Image />
// </View>
//   );
// }
