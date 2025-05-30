package ru.mobileup.samples.core.media

import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable
import ru.mobileup.samples.core.utils.UriSerializer

@Serializable
@JvmInline
value class ImageResource(
    @Serializable(with = UriSerializer::class)
    val uri: Uri
) {
    constructor(string: String) : this(string.toUri())

    companion object {
        val MOCK_LIST = listOf(
            ImageResource("https://images.unsplash.com/photo-1529778873920-4da4926a72c2?q=80&w=2853&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            ImageResource("https://plus.unsplash.com/premium_photo-1661674514856-17f29bd480b6?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            ImageResource("https://images.unsplash.com/photo-1611267254323-4db7b39c732c?q=80&w=2848&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            ImageResource("https://images.unsplash.com/photo-1556582305-528bffcf7af0?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            ImageResource("https://images.unsplash.com/photo-1541781774459-bb2af2f05b55?q=80&w=3260&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
        )
    }
}