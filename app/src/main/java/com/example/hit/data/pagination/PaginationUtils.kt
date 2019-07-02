package com.example.hit.data.pagination

import androidx.paging.PagedList

const val QUERY_KEY = "query"
const val PAGE_NUMBER_KEY = "page"
const val PER_PAGE_QUANTITY_KEY = "per-page"

const val INITIAL_PAGE = 1
const val DEFAULT_PAGE_SIZE = 20
const val PAGINATION_OFFSET = 3

val DEFAULT_CONFIG: PagedList.Config
    get() = PagedList.Config.Builder()
        .setPageSize(DEFAULT_PAGE_SIZE)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(PAGINATION_OFFSET)
        .build()