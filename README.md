**News Demo App**

A modern Android application that allows users to browse, read, and manage news articles with ease.
Built using the latest Android technologies and following best practices, this app offers a seamless experience for users and developers alike.

---

**Features:**

**Browse News Articles**

View a list of news articles with titles, summaries, and images.

Filter news by category such as Data Source, Country, and Language.

Search for news articles using keywords.

Tap on an article to read its full content on a detailed page.

**Bookmarking**

Bookmark articles for later reading.

Swipe to delete bookmarks with ease.

**Offline Support**

Access bookmarked articles even when offline.

**Orientation Handling**

Smooth orientation changes without disrupting the user experience.

---


**News Demo App Architecture:**


![News App Architecture](https://github.com/Veer0033/JetpackComposeNewsApp/blob/main/Assets/JetpackComposeNews_App_MVVM_Arch.png)

---


**News Demo App Screenshots:**


![News App Screenshots](https://github.com/Veer0033/JetpackComposeNewsApp/blob/main/Assets/app_screenshots.png)


--- 


**News  DemoApp APK Analysis:**


![News App APK Analysis](https://github.com/Veer0033/JetpackComposeNewsApp/blob/main/Assets/APK_Analysis.png)


---


**News Demo App APK Lint Report:**


![News App Lint Report](https://github.com/Veer0033/JetpackComposeNewsApp/blob/main/Assets/Lint_report.png)

---


**Installation:**

Clone the repository:

git clone https://github.com/Veer0033/JetpackComposeNewsApp.git

Open the project in Android Studio.

Visit [NewsAPI](https://newsapi.org) and sign up for an API key, Copy the API key provided


Open the build.gradle.kts file in the app module. Find the following line 

**buildConfigField("String", "API_KEY", "\"<Add your API Key>\"")**

Replace "Add your API Key" with the API key you obtained

Build and Run the News Demo on an Android device or emulator.

**Note:**
I have currently set up my API key, which allows for up to 50 requests per day.
You may need to obtain your own key by signing up on the website and follow the provided instructions to replace it.

---


This app follows the **MVVM architecture** pattern, ensuring a clean separation of concerns and making the app scalable and maintainable.


**Technologies Used:**

**Core Components:**

**Kotlin:**  The programming language used for all the development.

**Jetpack Compose:**  Modern UI toolkit for building native Android interfaces of the app.

**Jetpack Navigation:**  Simplifies in-app navigation and ensures type safety.

**Coroutine & Coroutine Flow:**  Manage asynchronous tasks with ease.

**Dagger Hilt:**  Android's own Dependency injection framework.

**Room Database:**  Local database for storing/updating/deleting bookmarked articles.

**Retrofit:**  For making HTTP requests to fetch news articles and its related information.

**buildSrc:**  module in an News demo app allows you to define and manage project dependencies and plugins in a single place, ensuring consistency across multiple modules in future and helps in reducing duplication and simplifying Gradle script maintenance.

**AndroidX:**  Components They ensure that your app is compatible with older Android versions,provides updated and improved versions of support libraries, offering better performance, bug fixes, and new features.

---

**Testing:**

**Espresso:**  For UI testing of news screen and status like Loading

**JUnit:**  For unit testing of functions.

**Mockito:**  For mocking dependencies in tests.

**Coroutine Test:**  For testing coroutines dispatchers.

**Turbine Test:**  For testing Flow.

---

**Optimization:**

**APK Analysis and Size Reduction:**   Focused on keeping the APK size minimal without compromising features, Please find attached screenshot for your reference. 

**Lint Reports:**  Ensured code quality by addressing all lint warnings and errors possible, Please find attached screenshot for your reference. 

**Proguard:**  Configured to obfuscate and shrink the APK for production.

---

**Code Documentation:**

**Code Commenting:**  Comprehensive comments throughout the project to explain the purpose and functionality of key components.

**Documentation:**  Detailed documentation, including architecture diagrams and entity relationship schemas, will be provided separately.

---


**How to Use the News Demo App:**

**Browsing Articles:**

Launch the app and browse through the list of news articles on Top News screen.

Tap on an article to view the full content.

**Bookmarking Articles:**

Go to News details and Click on Bookmark icon on right bottom to bookmark it.

Swipe left or right to delete a bookmark.

**Filtering:** 

Use the filter options to narrow down articles by Country, Language, Source.

**Searching:**

Use the search bar to find articles by keywords.
