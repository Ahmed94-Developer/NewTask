package com.example.myapplication3.data.mapper

import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.domain.entities.Results

fun Results.mapFromEntity() = Result(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun Result.mapFromDomain() = Results(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<Results>.mapFromListModel(): List<Result> {
    return this.map {
        it.mapFromEntity()
    }
}

fun List<Result>.mapFromListDomain(): List<Results> {
    return this.map {
        it.mapFromDomain()
    }
}