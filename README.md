### Quizzes

Cześć! 👋

Stworzyłem aplikację mobilną z Quizami. Wykorzystałem biblioteki:

:white_check_mark:API

:white_check_mark:Retrofit

:white_check_mark:Glide

:white_check_mark:ViewBinding

:white_check_mark:Kotlin Coroutines

:white_check_mark:Hilt

:white_check_mark:Architecture Components (LiveData, ViewModel)

:white_check_mark:Room

:white_check_mark:Lottie

### Widoki apliakcji

![img](https://user-images.githubusercontent.com/75754448/122287264-09b18080-cef1-11eb-855a-a7af239e2af0.png)



Gdy użytkownik będzie offline to wyświetli się poniższy napis, natomiast gdy będzie online to wróci do normalnego stanu i dane załadują się automatycznie.


  ![eeee](https://user-images.githubusercontent.com/75754448/122290751-59de1200-cef4-11eb-8f41-811415321217.png)

### 1. Pobieranie obrazków

```Kotlin
            val url =
                item.mainPhoto.url
                    .replace(
                        "https://filerepo.grupawp.pl/",
                        "http://i.wpimg.pl/" +
                                "${item.mainPhoto.width}x${item.mainPhoto.height}/filerepo.grupawp.pl/"
                    )
            Glide.with(root.context)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.quiz_list)
                .into(imgTitleQuiz)
```
