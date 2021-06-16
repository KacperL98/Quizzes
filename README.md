### Quizzes

Cześć! 👋

Stworzyłem aplikację mobilną z Quizami. Wykorzystałem:

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
Jeżeli użytkownik przed pierwszym uruchomieniem wyłączy Internet to wyświetli mu się poniższy komunikat. W takim wypadku należy ponownie otworzyć aplikacje  :wink:

![12](https://user-images.githubusercontent.com/75754448/122305167-b8ac8700-cf06-11eb-8292-15cffdd56d44.png)


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

### 2. Przyciski

Zauważyłem, że Api zwraca różne ilości przycisków. Można ustawić to na sztywno i dodać do każdego przycisku odpowiednią zależność (tak też zrobiłem napoczątku).
Ostatecznie dodałem osobny ViewHolder i Adapter.Według mnie wygląda to znacznie lepiej, niż przy pierwszym pomyśle. Przede wszystkim jest mniej kodu i jest czytelniejszy :hand:

Jeżeli skończą się pytania to użytkownik zostanie przeniesiony do fragmentu ScoreQuizFragment

```Kotlin
    private val adapter: AnswerAdapter by lazy {
        AnswerAdapter { answer ->
            addPoint(answer)
            if (currentIndex != amountQuestions - 1) {
                currentIndex++
            } else {
                openScoreFragment()
            }
            viewModel.refreshQuestion()
        }
    }
```

### 3. Wyświetlanie wyniku w ScoreQuizFragment

Procent dodałem w res-values-strings

```Kotlin
    <string name="result"> %s%%</string>
```

Kiedy odpowiedź jest poprawna to zwraca mi z Api +1 pkt

```Kotlin
       private fun addPoint(answer: Answer) {
        if (answer.isCorrect != 0) {
            correctPoints += answer.isCorrect
        }
    }
```
Następnie przekazuję wartość w nawigacji

```Kotlin
    private fun openScoreFragment() {
        val result = (correctPoints * 100) / amountQuestions
        findNavController().navigate(
            R.id.action_detailsQuizFragment_to_scoreQuizFragment,
            bundleOf("result" to result,
            RESULT_LIST to quizId)
        )
    }
```
