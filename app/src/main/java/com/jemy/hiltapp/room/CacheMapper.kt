package com.jemy.hiltapp.room

import com.jemy.hiltapp.model.Blog
import com.jemy.hiltapp.retrofit.BlogNetworkEntity
import com.jemy.hiltapp.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog =
        Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity =
        BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )

    fun mapFromEntityList(entityList: List<BlogCacheEntity>): List<Blog> =
        entityList.map { mapFromEntity(it) }
}