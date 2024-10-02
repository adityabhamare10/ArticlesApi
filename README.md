# Articles Android App
This Android application fetches articles from a news API, displays them in a RecyclerView, and allows users to search for articles by title.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Libraries and Dependencies](#libraries-and-dependencies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Demo Video](#demo-video)


## Overview
The Articles Fetcher app utilizes the [NewsAPI](https://newsapi.org) to fetch articles related to business in the US. NewsAPI is a simple HTTP REST API for searching and retrieving live articles from all over the web. Users can view a list of articles, read their descriptions, and perform a dynamic search to filter articles by title. If no articles match the search criteria, the app displays a "No results found" message.

## Features
- Using MVVM Architecture
- Fetch articles from [NewsAPI](https://newsapi.org)
- Search articles dynamically by title
- Display "No results found" if no articles match the search

## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, promoting a clear separation of concerns:

* Model: Manages the data and business logic. In this case, it includes the Repository class that interacts with the API and Room database.
* View: The user interface represented by Activity and Fragment classes that display the data and handle user interactions.
* ViewModel: Acts as a bridge between the View and Model, providing data to the UI and reacting to user inputs.
  
## Libraries and Dependencies
The application uses the following libraries and dependencies:

* Retrofit: For making network requests to the NewsAPI.
* Gson: For parsing JSON data from the API.
* Glide: For image loading and caching.
* Lifecycle: To manage UI-related data in a lifecycle-conscious way.

```kt

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

```
  
## Getting Started
To get started with the project, follow these steps:

1. Clone the repository:
```sh
git clone https://github.com/adityabhamare10/ArticlesApp.git
cd ArticlesApp
```
2. Open the project in Android Studio.


3. Add your API key:
   * Replace the placeholder API key in the Repository class with your actual API key from NewsAPI.
5. Run the application:
  * Connect an Android device or start an emulator.
  * Click on the Run button in Android Studio.
    
## Usage
Upon launching the app, users will see a list of articles.
Users can scroll through the articles and click on them to view more details.
A search bar is provided at the top of the screen. As the user types, the list will dynamically update to show articles matching the search query.
If no articles match the search, a "No results found" message will be displayed.

## Screenshots
![schooglinkImages](https://github.com/user-attachments/assets/0b5a2f23-4f5c-4047-a284-3c8a5bbde8e2)

## Demo Video
  
[![Watch the demo](images/demo_thumbnail.png)](https://drive.google.com/file/d/11kXlDhpfTM3nl0LwInlk2Cqs6CVwxfcl/view?usp=sharing)

