package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.domain.DeleteFirstQueryUseCase
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.GetCurrentSeasonUseCase
import com.towich.kinopoiskDev.domain.GetEpisodesPageUseCase
import com.towich.kinopoiskDev.domain.GetFiltersUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.domain.GetPostersUseCase
import com.towich.kinopoiskDev.domain.GetQueriesCountUseCase
import com.towich.kinopoiskDev.domain.GetQueriesUseCase
import com.towich.kinopoiskDev.domain.GetReviewsPageUseCase
import com.towich.kinopoiskDev.domain.GetSeasonsPageUseCase
import com.towich.kinopoiskDev.domain.SearchMoviesPageUseCase
import com.towich.kinopoiskDev.domain.SetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.SetCurrentSeasonUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
import com.towich.kinopoiskDev.domain.SetQueryUseCase
import com.towich.kinopoiskDev.domain.ShiftIdsUseCase
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    @AppScope
    fun provideGetMoviesUseCase(repository: MainRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetMoviesPageUseCase(repository: MainRepository): GetMoviesPageUseCase {
        return GetMoviesPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetGenresUseCase(repository: MainRepository): GetGenresUseCase {
        return GetGenresUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetCountriesUseCase(repository: MainRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideSetFiltersUseCase(repository: MainRepository): SetFiltersUseCase {
        return SetFiltersUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetFiltersUseCase(repository: MainRepository): GetFiltersUseCase {
        return GetFiltersUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetCurrentMovieUseCase(repository: MainRepository): GetCurrentMovieUseCase {
        return GetCurrentMovieUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideSetCurrentMovieUseCase(repository: MainRepository): SetCurrentMovieUseCase {
        return SetCurrentMovieUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetActorsPageUseCase(repository: MainRepository): GetActorsPageUseCase {
        return GetActorsPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetSeasonsPageUseCase(repository: MainRepository): GetSeasonsPageUseCase {
        return GetSeasonsPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetCurrentSeasonUseCase(repository: MainRepository): GetCurrentSeasonUseCase {
        return GetCurrentSeasonUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideSetCurrentSeasonUseCase(repository: MainRepository): SetCurrentSeasonUseCase {
        return SetCurrentSeasonUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetEpisodesPageUseCase(repository: MainRepository): GetEpisodesPageUseCase {
        return GetEpisodesPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetReviewsPageUseCase(repository: MainRepository): GetReviewsPageUseCase {
        return GetReviewsPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideSearchMoviesPageUseCase(repository: MainRepository): SearchMoviesPageUseCase {
        return SearchMoviesPageUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetPostersUseCase(repository: MainRepository): GetPostersUseCase {
        return GetPostersUseCase(repository = repository)
    }

    @Provides
    @AppScope
    fun provideDeleteFirstQueryUseCase(repository: MainRepository): DeleteFirstQueryUseCase {
        return DeleteFirstQueryUseCase(repository = repository)
    }

    @Provides
    @AppScope
    fun provideGetQueriesCountUseCase(repository: MainRepository): GetQueriesCountUseCase {
        return GetQueriesCountUseCase(repository = repository)
    }

    @Provides
    @AppScope
    fun provideGetQueriesUseCase(repository: MainRepository): GetQueriesUseCase {
        return GetQueriesUseCase(repository = repository)
    }

    @Provides
    @AppScope
    fun provideSetQueryUseCase(repository: MainRepository): SetQueryUseCase {
        return SetQueryUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideShiftIdsUseCase(repository: MainRepository): ShiftIdsUseCase {
        return ShiftIdsUseCase(repository = repository)
    }
}