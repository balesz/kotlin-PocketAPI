package net.solutinno.pocket.model

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 */
open class ActionParams (
        val action: String,
        var item_id: String? = null,
        var time: Long? = null)
{
    /**
     * @property ref_id	(optional) A Twitter status id; this is used to show tweet attribution.
     * @property tags (optional) A comma-delimited list of one or more tags.
     * @property title (optional) The title of the item.
     * @property url (optional) The url of the item; provide this only if you do not have an item_id.
     */
    class Add (
            action: String,
            item_id: String? = null,
            time: Long? = null,
            var ref_id: Int? = null,
            var tags: String? = null,
            var title: String? = null,
            var url: String? = null
    ) : ActionParams(action, item_id, time)

    /**
     * @property tags A comma-delimited list of one or more tags.
     */
    class Tags(
            action: String,
            item_id: String? = null,
            time: Long? = null,
            var tags: String? = null
    ) : ActionParams(action, item_id, time)

    /**
     * @property old_tag The tag name that will be replaced.
     * @property new_tag The new tag name that will be added.
     */
    class Tag (
            action: String,
            item_id: String? = null,
            time: Long? = null,
            var old_tag: String? = null,
            var new_tag: String? = null
    ) : ActionParams(action, item_id, time)
}
