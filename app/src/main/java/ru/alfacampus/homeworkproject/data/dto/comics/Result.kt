package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("id")
    val id: Int,
    @SerialName("digitalId")
    val digitalId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("issueNumber")
    val issueNumber: String,
    @SerialName("variantDescription")
    val variantDescription: String,
    @SerialName("description")
    val description: String,
    @SerialName("modified")
    val modified: String,
    @SerialName("isbn")
    val isbn: String,
    @SerialName("upc")
    val upc: String,
    @SerialName("diamondCode")
    val diamondCode: String,
    @SerialName("ean")
    val ean: String,
    @SerialName("issn")
    val issn: String,
    @SerialName("format")
    val format: String,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("textObjects")
    val textObjects: List<TextObject>,
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("urls")
    val urls: List<Url>,
    @SerialName("series")
    val series: Series,
    @SerialName("variants")
    val variants: List<Variant>,
    @SerialName("dates")
    val dates: List<Date>,
    @SerialName("prices")
    val prices: List<Price>,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("creators")
    val creators: Creators,
    @SerialName("characters")
    val characters: Characters,
    @SerialName("stories")
    val stories: Stories,
    @SerialName("events")
    val events: Events
)
