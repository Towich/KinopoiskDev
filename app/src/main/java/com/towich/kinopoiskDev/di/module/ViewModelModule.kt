package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.di.scope.ActivityScope
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
import com.towich.kinopoiskDev.util.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    @ActivityScope
    fun provideViewModelFactory(
        getMovies: GetMoviesUseCase,
        getMoviesPage: GetMoviesPageUseCase,
        getGenresUse: GetGenresUseCase,
        getCountries: GetCountriesUseCase,
        setFilters: SetFiltersUseCase,
        getFilters: GetFiltersUseCase,
        getCurrentMovie: GetCurrentMovieUseCase,
        setCurrentMovie: SetCurrentMovieUseCase,
        getActorsPage: GetActorsPageUseCase,
        getSeasonsPage: GetSeasonsPageUseCase,
        setCurrentSeason: SetCurrentSeasonUseCase,
        getCurrentSeason: GetCurrentSeasonUseCase,
        getEpisodesPage: GetEpisodesPageUseCase,
        getReviewsPage: GetReviewsPageUseCase,
        searchMoviesPage: SearchMoviesPageUseCase,
        getPosters: GetPostersUseCase,
        getQueries: GetQueriesUseCase,
        setQuery: SetQueryUseCase,
        deleteFirstQuery: DeleteFirstQueryUseCase,
        getQueriesCount: GetQueriesCountUseCase,
        shiftIds: ShiftIdsUseCase
    ): ViewModelFactory = ViewModelFactory(
        getMovies = getMovies,
        getMoviesPage = getMoviesPage,
        getGenres = getGenresUse,
        getCountries = getCountries,
        setFilters = setFilters,
        getFilters = getFilters,
        getCurrentMovie = getCurrentMovie,
        setCurrentMovie = setCurrentMovie,
        getActorsPage = getActorsPage,
        getSeasonsPage = getSeasonsPage,
        setCurrentSeason = setCurrentSeason,
        getCurrentSeason = getCurrentSeason,
        getEpisodesPage = getEpisodesPage,
        getReviewsPage = getReviewsPage,
        searchMoviesPage = searchMoviesPage,
        getPosters = getPosters,
        getQueries = getQueries,
        setQuery = setQuery,
        deleteFirstQuery = deleteFirstQuery,
        getQueriesCount = getQueriesCount,
        shiftIds = shiftIds
    )
}