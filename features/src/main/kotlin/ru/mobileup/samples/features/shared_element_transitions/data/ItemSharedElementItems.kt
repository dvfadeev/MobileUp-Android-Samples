package ru.mobileup.samples.features.shared_element_transitions.data

import ru.mobileup.samples.features.image.domain.ImageUrl
import ru.mobileup.samples.features.shared_element_transitions.domain.ItemSharedElement

object ItemSharedElementItems {
    val items = listOf(
        ItemSharedElement(
            id = 1,
            title = "«Яндекс 360» представил мобильное приложение «Календарь»",
            text = "Сервис «Яндекс 360» выпустил мобильное приложение «Календарь», позволяющее следить за расписанием и планировать дела, не упуская ничего важного. Приложение поддерживает сразу несколько календарей, например личный или рабочий, и может работать даже без подключения к интернету, рассказали информационной службе Хабра в пресс-службе сервиса.\n" +
                    "Как отметили разработчики, с помощью «Календаря» можно создавать и редактировать события — от походов в театр до рабочих встреч. Встроенный поиск позволяет быстро находить нужные записи. Если встреча проводится по видеосвязи, «Календарь» автоматически создаст ссылку на «Телемост» для всех участников. Чтобы не путать личные и рабочие дела, события из разных календарей выделены разными цветами. В приложении также есть тёмная тема, снижающая нагрузку на глаза.\n" +
                    "\n" +
                    "Интерфейс «Календаря» оптимизирован для удобного управления с экрана смартфона. Добавить новое событие можно нажатием на нужный временной слот. Длительное нажатие позволяет быстро перенести его на другое время, а жесты сжатия и растягивания — изменить продолжительность. Напоминания помогут не пропустить важные встречи. Их можно настроить заранее, выбрав, за сколько времени до события будет отправлено уведомление, а также способ получения — по электронной почте или через push-уведомление.\n" +
                    "\n" +
                    "Планировать дела в «Календаре» можно даже в офлайне, например в самолёте. Без подключения к интернету пользователи могут просматривать, редактировать и удалять события, а также добавлять новые. Все изменения синхронизируются с сервером сразу после подключения к сети. Дополнительная опция для путешественников — автоматический переход на местное время при смене часового пояса. Если нужно сохранить старый часовой пояс, эту функцию можно отключить в настройках.\n" +
                    "\n" +
                    "Приложение «Яндекс Календарь» доступно для iOS и Android. Владельцы устройств на iOS уже могут установить виджет «Календаря», а на Android эта возможность появится в ближайших обновлениях. В планах разработчиков — добавить возможность планировать встречи с учётом занятости участников и новые режимы отображения календаря: на три дня и на неделю.",
            image = ImageUrl("https://habrastorage.org/r/w780/getpro/habr/upload_files/612/af2/c45/612af2c45036e303efb48ff45773f0f8.jpg"),
        ),
        ItemSharedElement(
            id = 2,
            title = "10-летний юбилей Android Studio",
            text = "Разработчики из Google и партнёры компании отмечают 10-летний юбилей с момента первого мажорного релиза Android Studio — интегрированной среды разработки (IDE) для создания, тестирования, отладки и упаковки Android-приложений для миллиардов пользователей на разнообразных мобильных устройствах.\n" +
                    "\n" +
                    "Когда мы приступили к разработке Android Studio, мы начали с этих трёх принципов:\n" +
                    "\n" +
                    "Во‑первых, мы хотели создать и выпустить полноценную IDE, а не просто плагин. До Android Studio пользователям приходилось загружать JDK, затем загружать Eclipse, затем настраивать его с помощью центра обновлений, чтобы он указывал на Android, устанавливать плагин Eclipse для Android, а затем настраивать этот плагин, чтобы он указывал на установку Android SDK. Мы не только хотели, чтобы все работало из коробки, но и хотели иметь возможность настраивать и улучшать все: от интегрированной системы управления зависимостями до предоставления проверок кода, которые были бы актуальны для разработчиков приложений Android, и до единого места для сообщения об ошибках.\n" +
                    "\n" +
                    "Во‑вторых, мы хотели построить его поверх активно поддерживаемой, открытой и лучшей в своем классе среды программирования Java. Незадолго до выпуска Android Studio мы все использовали IntelliJ и чувствовали, что она лучше с точки зрения редактирования кода.\n" +
                    "\n" +
                    "И в‑третьих, мы хотели не только предоставить систему сборки, которая лучше подходит для разработки приложений Android, но и позволить этой системе сборки работать согласованно как из командной строки, так и из IDE. Это было важно, потому что в предыдущей цепочке инструментов мы обнаружили, что существуют расхождения в поведении и возможностях между сборками в IDE с Eclipse и сборками CI с Ant.",
            image = ImageUrl("https://habrastorage.org/r/w1560/getpro/habr/upload_files/0a5/724/476/0a5724476f973ff40015bb2955ca4cdf.png")
        ),
        ItemSharedElement(
            id = 3,
            title = "Новости Android разработки 23.02.2021",
            text = "Неделька выдалась шикарной - нам показали новую версию Android, а также объявили о новом выпуске TheAndroidShow, посвященного Jetpack Compose. Ставлю на то, что там нам представят Beta Compose, расскажут когда же ждать релиза. Очень надеюсь что также будет стабилизация API для первой версии. Давайте разбираться.\nВ первой версии для разработчиков дают возможность ознакомиться с нововведениями, которые планируются внедрить в следующей версии Android. Как всегда, обновления касаются ограничений, направленных на обеспечение безопасности и приватности данных пользователей. Также добавили поддержку современных медиа форматов, снова обновили UI уведомлений и улучшили производительность. По традиции блокируется скрытые API для вызова рефлексией, а также добавлены новые API. \n" +
                    "\n" +
                    "Нас ждёт три Developer Preview каждый месяц, первую Beta увидим в мае, четвертая Beta, она же Platform Stability релиз, состоится в августе и весной нас ждёт релиз.",
            image = ImageUrl("https://habrastorage.org/r/w780/getpro/habr/upload_files/62a/991/404/62a991404a937ce7033774fa1facb3c6.jpg")
        ),
        ItemSharedElement(
            id = 4,
            title = "Google планирует выпустить Android 16 под кодовым названием Baklava",
            text = "Google намерена выпустить Android 16 под кодовым названием Baklava, что станет отходом от традиции называть версии мобильной операционной системы в честь десертов в алфавитном порядке, пишет Android Authority. Предыдущие три итерации Android имели кодовые наименования Tiramisu, Upside Down Cake и Vanilla Ice Cream, а название следующей версии при сохранении той же логики должно было начинаться с буквы «W».\nКорпорация годами придерживалась алфавитной схемы наименований Android: от Cupcake (Android 1.5) до Vanilla Ice Cream (Android 15). Такие кодовые имена предлагали забавный способ отслеживать прогресс разработки ОС.\n" +
                    "\n" +
                    "Baklava переводится как «пахлава». Восточную сладость готовят из слоёного теста, листы которого пропитывают маслом и выкладывают друг на друга в посуду. Android 16, как и прежде, будет названа в честь десерта, но её наименование нарушает алфавитный порядок. В Android Authority предполагали, что новую ОС назовут в честь Waffle Sundae или Whoopie Pie.\nНазвание следующей итерации Android узнали благодаря упоминаниям в коде Android Open Source Project (AOSP). В поле min-sdk-version сейчас указано наименование Baklava так же, как в прошлой версии там было написано VanillaIceCream. Google изменила это название на Android 15 после стабильного релиза ОС. Автор Android Authority полагает, что аналогичным образом должно произойти и с Android 16.\n" +
                    "\n" +
                    "Инженер Google также случайно подтвердил кодовое название Android 16, добавив проверку, чтобы увидеть, совпадает ли имя SDK платформы с Baklava.",
            image = ImageUrl("https://habrastorage.org/r/w780/webt/w6/pn/h_/w6pnh_sp6ajimud8zjnakdoh2ae.jpeg")
        ),
        ItemSharedElement(
            id = 5,
            title = "Google устранила уязвимость в Android, которая позволяла злоумышленникам внедрять вредоносное ПО через NFC",
            text = "Google в конце октября 2019 года устранила уязвимость CVE-2019-2114, которая могла использоваться злоумышленниками для распространения вредоносного программного обеспечения между устройствами на ОС Android, находящимися недалеко друг от друга. Проблема заключалась в некорректной работе функции NFC Beaming в режиме «Android beam: enabled», которая позволяла передавать по NFC на другое устройство APK-файлы приложений. Причем сам сервис Android Beam для операционный системы по уровню доверия был в белом списке, а передаваемые файлы воспринимались так же, как приложения из Google Play Store. Данной уязвимости были подвержены все устройства на базе Android Oreo (8.0) и более поздних версий.\nРасширенный функционал технологии NFC (Near Field Communication) работает на Android-устройствах с помощью внутреннего сервиса операционной системы Android Beam. Данный сервис также позволяет передавать определенные данные между двумя устройствами на Android. Это могут быть файлы с изображениями, видеофрагменты, а также другие файлы и APK-приложения, которые могут быть скомпрометированы или специально заражены.\n" +
                    "\n" +
                    "После выполнения процесса передачи по NFC, APK-файлы приложений сохраняются в памяти внутреннего накопителя устройства-получателя. Однако, если получателем является устройство на базе Android Oreo (8.0) и более поздних версий, то на экране этого устройства при этом не появляется соответствующий запрос или уведомление на разрешение установки ПО из неизвестного источника. А вместо этого на экран второго устройства выводится сообщение, позволяющее его пользователю одним нажатием установить программу, полученную с другого устройства через NFC.\n" +
                    "\n" +
                    "Конечно, данная проблема не является серьезным изъяном в системе безопасности Android, так как пользователю все равно нужно подтвердить запуск полученного файла. Однако, в этом случае был пропущен важный шаг — ведь устройствам на Android по умолчанию не разрешается устанавливать приложения из «неизвестных источников», если не активирована соответствующая опция в настройках безопасности устройства. А в случае с Android Beam если такая опия была выключена, то все равно приложения по NFC можно было получить и начать устанавливать без всяких предупреждений системы безопасности ОС.\n" +
                    "\n" +
                    "Оказалось, что разработчики не учли такой момент — сервис Android Beam по умолчанию имел в системе Android Oreo (8.0) и более поздних версий самый высокий уровень доверия и все передаваемые через него файлы воспринимались как официальные приложения из Play Store. Хотя функционал этого сервиса изначально не предназначался для установки приложений с его помощью. Данной уязвимости был присвоен номер CVE-2019-2114, она была впервые зарегистрирована 30 января 2019 года. На данный момент эта уязвимость устранена в октябрьском пакете обновлений для ОС Android. В системе Google эта уязвимость имеет данные: Google Bug # 123651515 (Android ID # A-123700348).\n" +
                    "\n" +
                    "Данная уязвимость соответствовала требованиям программы Android Security Rewards, а Google выплатил за ее нахождение и предоставление информации вознаграждение.",
            image = ImageUrl("https://habrastorage.org/webt/cy/_5/ql/cy_5qlihhcnt5tv5amcsospgtju.jpeg")
        ),
        ItemSharedElement(
            id = 6,
            title = "Google Play перестанет поддерживать Android 4.4 KitKat",
            text = "Android 4.4 KitKat вышел в 2013 году и до сих пор поддерживается Google Play. В официальном блоге Android разработчиков опубликовали анонс, что версия Google Play Services перестанут поддерживать Android 4.4 (API Level 19 и 20), начиная с версии 23.30.99.\n" +
                    "\n" +
                    "Связано это с тем что доля активных в Google Play устройств на этой версии операционной системы снизилась до значений менее 1% в июле 2023. Хотя по официальной информации от Google доля Android 4.4 составляет 0.5% по состоянию на 30 мая 2023.\nХочется увидеть подобные перемены и в Android Jetpack. Часть библиотек там до сих пор поддерживают Android 4.0, который вышел в 2011 году! Поддержка старых версий заставляет носить с собой больше кода. Да, современные инструменты минификации в Android убирают этот код в релизе, но при разработке все также остаётся много ненужного API.\n" +
                    "\n" +
                    "Лично я считаю что требование с тем что в Android 14 можно установить приложения только с поддержкой Android 6.0 и новее должно полностью сочетаться с минимальными поддерживаемыми версиями библиотек Android Jetpack и Google Play Services, что останавливать поддержку старых версий ОС.",
            image = ImageUrl("https://habrastorage.org/r/w1560/getpro/habr/upload_files/963/a76/44c/963a7644c4cfe8c7d24fe11a7fd27392.png")
        )
    )
}