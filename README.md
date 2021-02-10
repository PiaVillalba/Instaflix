<h1 align="center">Instaflix</h1>

<p align="center">
  <a href="https://kotlinlang.org/"><img alt="API" src="https://img.shields.io/static/v1?style=flat-square&label=Kotlin&message=1.4.10&color=007ec6"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://ktlint.github.io/"><img alt="API" src="https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg"/></a>
  <a href="https://github.com/PiaVillalba/Instaflix/actions"><img alt="Build Status" src="https://github.com/PiaVillalba/Instaflix/workflows/CI/badge.svg"/></a> 
</p>


</br>

<p align="center">
<a href=''><img height="120" src='https://github.com/PiaVillalba/Instaflix/blob/master/google_play_budget.png'/></a>
<a href="https://www.themoviedb.org/"><img width="200" height="100" src="./movie_db_budget.png"/></a>
</p>

</br>

Instaflix is an application that shows the popular movies and series of the moment, filters by categories and you can see the detail of the selected multimedia.


## Features
* Splash
* Multimedia (Movies and Tv shows)
* Multimedia detail
* Filter by genres

## Tech stack 
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt (alpha)](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
  - [Navigation component](https://developer.android.com/guide/navigation)
- Architecture
  - MVP Architecture (Model - View - Presenter)
  - Android Architecture components
  - Modularization (features and a **Core** module for commons)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like cardView.
- Unit test
- Kotlin DSL 
- Single page pattern
- Deeplink 
- Continuos integration
  - Precommit verifications
  - Github actions
  - Pipeline 
  - Protection branch
- Ktlint for code style


## Architecture

Instaflix is based on MVP architecture and Clean Code Architecture using Repository pattern

![architecture](/clean_architecture_budget.png)



# License
```xml

Designed and developed by Maria Pía Villalba

```
