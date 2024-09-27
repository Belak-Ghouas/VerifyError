Using Contentsquare sdk which is built on Gradle 8.7 with AGP 8.5
When I enable enableAndroidTestCoverage = true , i have a crash due to some bytecode instrumentation, 
If i build an SDK with gradle prior to 8.2 no issue with this version
I suspect D8 dexer or R8 shrinker that is doing some strange things on the bytecode & after i enabledAndroidTestCoverage , some jacoco byteCode is instrumented in this class and cause the crash.
```
java.lang.VerifyError: Verifier rejected class com.contentsquare.android.sdk.f6: void com.contentsquare.android.sdk.f6.a(android.view.Window) failed to verify: void com.contentsquare.android.sdk.f6.a(android.view.Window): [0xAB] expected to be within a catch-all for an instruction where a monitor is held (declaration of 'com.contentsquare.android.sdk.f6' appears in /data/app/~~NpYj7LbTUz36SQln1eO7sg==/com.contentsquare.android.demo-0RtFsdxC3XloHroYjXuVpg==/base.apk!classes5.dex)
                                                                                                    	at com.contentsquare.android.sdk.d6$a.a(SourceFile:159)
                                                                                                    	at com.contentsquare.android.sdk.d6$a.a(SourceFile:62)
                                                                                                    	at com.contentsquare.android.sdk.i6.a(SourceFile:335)
                                                                                                    	at com.contentsquare.android.sdk.k6.onPreferenceChanged(SourceFile:65)
                                                                                                    	at com.contentsquare.android.core.features.preferences.PreferencesStore.onSharedPreferenceChanged(SourceFile:21)
                                                                                                    	at android.app.SharedPreferencesImpl$EditorImpl.notifyListeners(SharedPreferencesImpl.java:637)
                                                                                                    	at android.app.SharedPreferencesImpl$EditorImpl.lambda$notifyListeners$0$SharedPreferencesImpl$EditorImpl(SharedPreferencesImpl.java:643)
                                                                                                    	at android.app.SharedPreferencesImpl$EditorImpl$$ExternalSyntheticLambda0.run(Unknown Source:4)
                                                                                                    	at android.os.Handler.handleCallback(Handler.java:938)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:99)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:201)
                                                                                                    	at android.os.Looper.loop(Looper.java:288)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:7839)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1003)

```
