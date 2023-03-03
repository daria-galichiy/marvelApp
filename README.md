# MARVEL APP

Marvel App is an application developed on the basis of an [open source API](https://developer.marvel.com/documentation/generalinfo) 
that allows you to view information about the Marvel universe.

## Application functionality

* The ability to choose the theme of the application
  
<img src="gif\1.gif" width="250" height="400" />

* The opportunity to get acquainted with the characters from the Marvel universe and the comics in which they participated

<img src="gif\2.gif" width="250" height="400" />

* The ability to search for characters and add them to Favorites

<img src="gif\3.gif" width="250" height="400" />

* The option to share a character card

<img src="gif\4.gif" width="250" height="400" />

* The function of removing a character from Favorites

<img src="gif\5.gif" width="250" height="400" />

* The ability to create your own characters

<img src="gif\6.gif" width="250" height="400" />

* The ability to edit characters

<img src="gif\7.gif" width="250" height="400" />

## Project structure

Marvel App is a multi-module application built on the MVVM architecture. It consists of the following modules:
* App
* Core  
  - core-data  
  - core-db  
  - core-di  
  - core-network  
  - navigation  
  - resources  
* Features
  - feature-characters  
  - feature-characters-description   
  - feature-created-characters  
  - feature-favorite-characters  
  - feature-splash-screen  

## Technology stack

* Kotlin
* MVVM
* Kotlin Coroutines, Flow
* Room
* Retrofit, OkHttp
* Dagger2
* Navigation component
* Glide
