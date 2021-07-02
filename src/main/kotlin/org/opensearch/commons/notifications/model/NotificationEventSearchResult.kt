/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

/*
 * Copyright 2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */

package org.opensearch.commons.notifications.model

import org.apache.lucene.search.TotalHits
import org.opensearch.action.search.SearchResponse
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.xcontent.XContentParser
import org.opensearch.commons.notifications.NotificationConstants.EVENT_LIST_TAG

/**
 * Notification Event search results
 */
class NotificationEventSearchResult : SearchResults<NotificationEventInfo> {

    /**
     * single item result constructor
     */
    constructor(objectItem: NotificationEventInfo) : super(EVENT_LIST_TAG, objectItem)

    /**
     * multiple items result constructor
     */
    constructor(objectList: List<NotificationEventInfo>) : this(
        0,
        objectList.size.toLong(),
        TotalHits.Relation.EQUAL_TO,
        objectList
    )

    /**
     * all param constructor
     */
    constructor(
        startIndex: Long,
        totalHits: Long,
        totalHitRelation: TotalHits.Relation,
        objectList: List<NotificationEventInfo>
    ) : super(startIndex, totalHits, totalHitRelation, EVENT_LIST_TAG, objectList)

    /**
     * Constructor used in transport action communication.
     * @param input StreamInput stream to deserialize data from.
     */
    constructor(input: StreamInput) : super(input, NotificationEventInfo.reader)

    /**
     * Construct object from XContentParser
     */
    constructor(parser: XContentParser) : super(parser, EVENT_LIST_TAG)

    /**
     * Construct object from SearchResponse
     */
    constructor(from: Long, response: SearchResponse, searchHitParser: SearchHitParser<NotificationEventInfo>) : super(
        from,
        response,
        searchHitParser,
        EVENT_LIST_TAG
    )

    /**
     * {@inheritDoc}
     */
    override fun parseItem(parser: XContentParser): NotificationEventInfo {
        return NotificationEventInfo.parse(parser)
    }
}