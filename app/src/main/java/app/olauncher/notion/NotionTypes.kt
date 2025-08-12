package app.olauncher.notion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Represents the overall list response from Notion
@JsonClass(generateAdapter = true)
data class NotionBlockListResponse(
    val results: List<NotionBlock>,
    @Json(name = "next_cursor") val nextCursor: String?,
    @Json(name = "has_more") val hasMore: Boolean
)

// Represents a single Notion block
@JsonClass(generateAdapter = true)
data class NotionBlock(
    val id: String,
    val type: String,
    @Json(name = "created_time") val createdTime: String, // You might want a custom adapter for Date/Instant
    val paragraph: Paragraph?, // Nullable if not all blocks are paragraphs
    @Json(name = "to_do") val todo: Todo?
// Add other block types here as nullable properties or use a sealed class approach (more advanced)
)

// Represents the paragraph content
@JsonClass(generateAdapter = true)
data class Paragraph(
    @Json(name = "rich_text") val richText: List<RichTextItem>
)

@JsonClass(generateAdapter = true)
data class Todo(
    @Json(name = "rich_text") val richText: List<RichTextItem>,
    @Json(name = "checked") val checked: Boolean
)

// Represents a single rich text item
@JsonClass(generateAdapter = true)
data class RichTextItem(
    val type: String,
    val text: TextContent?, // Nullable if not all rich text items are of type 'text'
    @Json(name = "plain_text") val plainText: String
)

// Represents the actual text content within a rich text item
@JsonClass(generateAdapter = true)
data class TextContent(
    val content: String
)
