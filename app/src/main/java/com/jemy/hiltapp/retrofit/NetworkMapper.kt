package com.jemy.hiltapp.retrofit

import com.jemy.hiltapp.model.Blog
import com.jemy.hiltapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog =
        Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity =
        BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )

    fun mapFromEntityList(entityList: List<BlogNetworkEntity>): List<Blog> =
        entityList.map { mapFromEntity(it) }
}