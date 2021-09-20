# Comics Explorer

Sample project to demonstrate my android skills for the tech interview at 'Shortcut'.

### Technologies
* `Architecture`: MVVM (I was planing to refactor it to Clean Arch but had not enough time so left it MVVM but kept packaging format as Clean as possible to make it easier to migrate when needed.)
* `Database`: Room
* `DI`: Dagger2 (Koin & Hilt are more quicker to setup but Dagger is a better fit to expanding projects )
* `Thread system`: Kotlin coroutines
* `Network`: Retrofit
* `Other`: KotlinDsl, Data/ViewBinding, Binding Adapters, LiveData, Kotlin Flow, Jetpack Nav, Extension functions. Take a look at [liveDataUtil.kt](app/src/main/java/com/shortcut/explorer/presentation/util/liveDataUtil.kt) and [FlowObserver.kt](app/src/main/java/com/shortcut/explorer/presentation/util/FlowObserver.kt)

---

### Tasks

- [x] browse through the comics,
- [x] see the comic details, including its description,
- [x] search for comics by the comic number as well as the the comic title,
- [x] get the comic explanation
- [x] favorite the comics, which would be available offline too,
- [x] send comics to others,
- [ ] get notifications when a new comic is published ''(Looks like a server is needed so skipped for the MVP),''
- [x] support multiple form factors.


### Important Notes

* Due to time constraints I could not complete all the tasks down to top, thus I tried to showcase them.
* Tried to keep the whole application testable but decided not to move on with TDD due to the project ambiguity.
* A showcase for tests is demonstrated here [RecentComicsRepositoryImplTest.kt](app/src/test/kotlin/com/shortcut/explorer/business/repositories/RecentComicsRepositoryImplTest.kt).
* I covered uncommon codes with comments, However skipped most of the code to save the time.
* List was meant to armed with Paging3 and Infinite scroll but the time was not enough.
* UI test were meant to be done via Espresso, Kluent, JUnit4-5 and ... .
* Used NavGraph for navigation and it should be implemented with FragmentFactory when adding more interactions with the fragments.
* Used Shared ViewModel and it should be replaced with single ViewModels if SharedViewModel went too complex to read.


 That's it  :smile:

 Thanks for reading.

