package ru.mobileup.samples

import ru.mobileup.samples.core.coreModule
import ru.mobileup.samples.features.document.documentModule
import ru.mobileup.samples.features.pin_code.pinCodeModule
import ru.mobileup.samples.features.photo.photoModule
import ru.mobileup.samples.features.uploader.uploaderModule
import ru.mobileup.samples.features.video.videoModule
import ru.mobileup.samples.features.yandex_map.yandexMapModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    photoModule,
    videoModule,
    documentModule,
uploaderModule,
    pinCodeModule,
    yandexMapModule
)