# Dependency Injection using Hilt

### Intro

- Built on top of Dagger 2.
- Hilt generates Dagger code for you.
- Standard way of implementing DI in android.

### Dagger vs Hilt

| Dagger | Hilt |
| --- | --- |
| Scopes - Singleton Scope, Activity Scope, Fragment Scope | Predefined Scopes |
| Components - Application Component, Activity Component etc. | Predefined Components |
| Component Dependencies - Sub Components, Dependency Attribute | Proper heirarchy defined. Parent child relationship. |
| Runtime bindings for Application Context, Activity Context | By default |
| Modules for providing dependencies |  |

### Dependency

```kotlin
plugins {
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

//Hilt
implementation("com.google.dagger:hilt-android:2.44.2")
kapt("com.google.dagger:hilt-android-compiler:2.44.2")
```

```kotlin
//build.gradle (Project: AppName)
plugins {
    id 'com.google.dagger.hilt.android' version '2.44.2' apply false
}
```

### @HiltAndroidApp

- Necessary to define application class.
- From there all code generation work starts. Any work related to DI is triggered from there.
- `@HiltAndroidApp` behind the scenes generate dagger code for us.

```kotlin
@HiltAndroidApp
class UserApplication : Application() {

@Inject
lateinit var  userRepository: UserRepository

override fun onCreate() {
	super.onCreate()
	userRepository.saveUser("test mail", "1234")
    }

}
```

### @AndroidEntryPoint

- It will tell dagger that in this activity, inject at that field.
- For fragment as well use `@AndroidEntryPoint` annotation. Is fragment host activity is not using injection but fragment is then we also have to mark activity with `@AndroidEntryPoint` .

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

		@Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
				userRepository.save("name", "number")
    }
}
```

### Module & @Installin

Hilt ***component class*** is a class responsible for injecting the bindings into the corresponding Android classes. This is same component which we referred in the @InstallIn annotation while creating a Hilt Module.

```kotlin
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

 @Provides
 fun provideContactDB(@ApplicationContext context: Context): ContactDatabase {
  return Room.databaseBuilder(context, ContactDatabase::class.java, "ContactDB").build()
   }
}
```

<p align="center">
<img width="600" src="https://i.ibb.co/c1ZCtVs/component-hierarchy.png">
</p>

Parent child relationship.

### Scope

By default, all bindings in Hilt are unscoped. This means every time I inject a binding, a new instance of that type will be created.

But what if I want the same instance of a binding throughout my application or I want the same instance of a binding in all fragments of an Activity. For such cases, Hilt gives us an option to scope a binding. It creates a scoped binding once per instance of the component that the binding is scoped and all requests for that binding share the same instance.

<p align="center">
<img width="600" src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/00f41095-77a8-4dfc-acbe-40ce1b87c787/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T061542Z&X-Amz-Expires=86400&X-Amz-Signature=ab07b4818ffc02f49b16fea4f79b6da41b0016f208e89c6f5506bfc0ef70f97f&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject">
</p>

### @HiltViewModel

Behind the scenes will create factory for us, so we’ll be able to create object. In hilt we don’t need to make factories. hilt will create for us.

### Predefined Qualifiers

`@ApplicationContext` - application context

`@ActivityContext` - activity context

```kotlin
@Provides
 fun provideContactDB(@ApplicationContext context: Context): ContactDatabase {
  return Room.databaseBuilder(context, ContactDatabase::class.java, "ContactDB").build()
   }
```
