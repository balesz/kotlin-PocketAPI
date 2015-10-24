package net.solutinno.pocket.model

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 */
data class ActionBasicParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property ref_id	(optional) A Twitter status id; this is used to show tweet attribution.
 * @property tags (optional) A comma-delimited list of one or more tags.
 * @property title (optional) The title of the item.
 * @property url (optional) The url of the item; provide this only if you do not have an item_id.
 */
data class ActionAddParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String? = null,
        val ref_id: Int? = null,
        val tags: String? = null,
        val title: String? = null,
        val url: String? = null)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property tags A comma-delimited list of one or more tags.
 */
data class ActionTagsParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String,
        val tags: String)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property old_tag The tag name that will be replaced.
 * @property new_tag The new tag name that will be added.
 */
data class ActionTagParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String? = null,
        val old_tag: String? = null,
        val new_tag: String? = null)

data class SendResult (
        val action_results: Array<SendResultItem>? = null,
        val status: Int? = null)

data class SendResultItem (
        val addResult: AddItem? = null,
        val basicResult: Boolean? = null)
